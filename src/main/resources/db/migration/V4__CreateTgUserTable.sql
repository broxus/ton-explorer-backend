CREATE TABLE tg_user
(
    id             bigint       NOT NULL,
    first_name     varchar(255) NOT NULL,
    last_name      varchar(255),
    username       varchar(32),
    photo_url      TEXT,
    last_auth_date bigint       NOT NULL,
    language_code  varchar(35),
    CONSTRAINT tg_user_pk PRIMARY KEY (id)
);
