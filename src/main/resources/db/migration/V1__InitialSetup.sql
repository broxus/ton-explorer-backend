CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE block
(
    workchain          SMALLINT NOT NULL,
    shard              BIGINT   NOT NULL,
    seqno              INT      NOT NULL,
    roothash           BYTEA    NOT NULL,
    filehash           BYTEA    NOT NULL,
    key_block          BOOLEAN  NOT NULL,
    time               INT      NOT NULL,
    transactions_count INT      NOT NULL,
    CONSTRAINT block_pk PRIMARY KEY (workchain, shard, seqno)
);

CREATE TYPE transaction_type AS ENUM
    ('merge_install', 'merge_prepare', 'ordinary', 'split_prepare', 'tick_tok', 'split_install', 'unknown');

CREATE TABLE transaction
(
    workchain              SMALLINT         NOT NULL,
    account_id             BYTEA            NOT NULL,
    hash                   BYTEA            NOT NULL,
    lt                     BIGINT           NOT NULL,
    block_shard            BIGINT           NOT NULL,
    block_seqno            INT              NOT NULL,
    transaction_type       transaction_type NOT NULL,
    balance_change         DECIMAL          NOT NULL,
    time                   INT              NOT NULL,
    total_fees             DECIMAL          NOT NULL,
    storage_fees_collected DECIMAL,
    storage_fees_due       DECIMAL,
    due_fees_collected     DECIMAL,
    gas_fees               DECIMAL,
    total_fwd_fees         DECIMAL,
    total_action_fees      DECIMAL,
    req_fwd_fees           DECIMAL,
    msg_fees               DECIMAL,
    fwd_fees               DECIMAL,
    aborted                BOOLEAN,
    destroyed              BOOLEAN,
    credit_first           BOOLEAN,
    is_tock                BOOLEAN,
    CONSTRAINT transaction_pk PRIMARY KEY (workchain, account_id, hash, lt),
    CONSTRAINT transaction_block_fk
        FOREIGN KEY (workchain, block_shard, block_seqno)
            REFERENCES block (workchain, shard, seqno)
);

CREATE TYPE message_type AS ENUM ('internal', 'external_in', 'external_out');

CREATE TABLE message
(
    body_hash              BYTEA        NOT NULL,
    out                    BOOLEAN      NOT NULL,
    n                      SMALLSERIAL  NOT NULL,
    transaction_workchain  SMALLINT     NOT NULL,
    transaction_account_id BYTEA        NOT NULL,
    transaction_hash       BYTEA        NOT NULL,
    transaction_lt         BIGINT       NOT NULL,
    message_type           message_type NOT NULL,
    transaction_time       INT          NOT NULL,
    value                  DECIMAL,
    ihr_fee                DECIMAL,
    fwd_fee                DECIMAL,
    import_fee             DECIMAL,
    src_workchain          INT,
    src_address            BYTEA,
    dst_workchain          INT,
    dst_address            BYTEA,
    bounce                 BOOLEAN,
    bounced                BOOLEAN,
    created_lt             BIGINT,
    created_at             INT,
    CONSTRAINT message_pk PRIMARY KEY (body_hash, out, n,
                                       transaction_workchain, transaction_account_id,
                                       transaction_hash, transaction_lt),
    CONSTRAINT message_transaction_fk
        FOREIGN KEY (transaction_workchain, transaction_account_id, transaction_hash, transaction_lt)
            REFERENCES transaction (workchain, account_id, hash, lt)
);

CREATE TYPE stake_transaction_type AS ENUM ('send', 'recover');

CREATE TABLE stake_transaction
(
    elector_workchain  SMALLINT               NOT NULL,
    elector_account_id BYTEA                  NOT NULL,
    transaction_hash   BYTEA                  NOT NULL,
    transaction_lt     BIGINT                 NOT NULL,
    transaction_type   stake_transaction_type NOT NULL,
    account_workchain  SMALLINT               NOT NULL,
    account_address    BYTEA                  NOT NULL,
    value              DECIMAL                NOT NULL,
    time               INT                    NOT NULL,
    election_time      INT,
    public_key         BYTEA,
    CONSTRAINT stake_transaction_pk PRIMARY KEY (elector_workchain, elector_account_id, transaction_hash,
                                                 transaction_lt)
);

CREATE TABLE account
(
    workchain SMALLINT NOT NULL,
    address   BYTEA    NOT NULL,
    balance   DECIMAL  NOT NULL,
    created   INT      NOT NULL,
    updated   INT      NOT NULL,
    CONSTRAINT account_pk PRIMARY KEY (workchain, address)
);

CREATE OR REPLACE FUNCTION update_account_balance() RETURNS TRIGGER AS
$$
BEGIN
    LOCK TABLE account IN SHARE ROW EXCLUSIVE MODE;
    INSERT INTO
        account AS EXISTING (workchain, address, balance, created, updated)
    VALUES (NEW.workchain, NEW.account_id, NEW.balance_change, NEW.time, NEW.time)
    ON CONFLICT (workchain, address) DO UPDATE SET updated = EXCLUDED.updated,
                                                   balance = (EXISTING.balance + NEW.balance_change);
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER transaction_tg_update_account_balance
    AFTER INSERT
    ON transaction
    FOR EACH ROW
EXECUTE PROCEDURE update_account_balance();
