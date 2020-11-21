## FreeTON Blockchain Explorer (backend)

This application interacts directly with C++ node.  
Indexes information contained in the blockchain: full history and update index in real-time.  
Provides an api that allows to make queries to the index and С++ node.  
URL: https://ton-explorer.com  
API docs: https://ton-explorer.com/docs  
Frontend: https://github.com/broxus/ton-explorer-frontend

Supported OS: Linux.  
You must have installed jdk8 and sbt (scala-sbt.org).  
Also required PostgreSQL 11  

**Tip:** For faster indexing speed, it is critical to deploy the application on the same network with node.

1. clone this repository<br/> 
`git clone git@github.com:broxus/ton-explorer-backend.git`  
`cd ton-explorer-backend`
2. build application<br/>
`sbt -mem 2048 stage`
3. Copy the contents of the folder `ton-explorer-backend/target/universal/stage` to a convenient location.  
For example to `~/apps/backend`
4. Create `conf` folder and copy `application.conf` file  
`mkdir ~/apps/backend/conf`  
`cp ton-explorer-backend/src/main/resources/application.conf ~/apps/backend/conf`  
5. Edit ~/apps/backend/conf/application.conf
    1. Set `ton.lite-client` using contents of `mainnet.config.json` file of you C++ node.
    2. Set database credentials: `db-config.url`, `db-config.user`, `db-config.password`.
    3. Enable indexer: `indexer.enabled=true`
    4. Set `indexer.threads-per-shard` equals number of processor cores
6. Start application
`./bin/blockchainexplorer -Xmx8G -Xms8G -Dconfig.file=~/apps/backend/conf/application.conf`

Synchronization will take a lot of time.  
In case 8 cpu cores and application works in one network with node its take about 12 hours.  
You can track the status in the logs or use API http://localhost:9000/api/stats

#### After full syncronization create indexes.
**WARNING**: do it only after full sync.
```
CREATE INDEX IF NOT EXISTS transaction__account ON transaction (workchain, account_id);
CREATE INDEX IF NOT EXISTS message__account ON message (transaction_workchain, transaction_account_id);
CREATE INDEX IF NOT EXISTS transaction__transaction_type ON transaction (transaction_type); 
CREATE INDEX IF NOT EXISTS block__workchain ON block (workchain); 
CREATE INDEX IF NOT EXISTS block__seqno ON block (seqno); 
CREATE INDEX IF NOT EXISTS block__roothash ON block (roothash); 
CREATE INDEX IF NOT EXISTS block__filehash ON block (filehash); 
CREATE INDEX IF NOT EXISTS block__time ON block (time); 
CREATE INDEX IF NOT EXISTS block__transactions_count ON block (transactions_count);  
CREATE INDEX IF NOT EXISTS block__workchain_shard ON block (workchain, shard); 
CREATE INDEX IF NOT EXISTS transaction__workchain ON transaction (workchain);
CREATE INDEX IF NOT EXISTS transaction__hash ON transaction (hash);
CREATE INDEX IF NOT EXISTS transaction__time ON transaction (time);
CREATE INDEX IF NOT EXISTS message__body_hash ON message (body_hash); 
CREATE INDEX IF NOT EXISTS message__transaction_time ON message (transaction_time);
CREATE INDEX IF NOT EXISTS account__created ON account (created); 
CREATE INDEX IF NOT EXISTS account__updated ON account (updated); 
CREATE INDEX IF NOT EXISTS stake_transaction__public_key ON stake_transaction (public_key);
CREATE INDEX IF NOT EXISTS stake_transaction__election_time ON stake_transaction (election_time);
CREATE INDEX IF NOT EXISTS stake_transaction__account ON stake_transaction (account_workchain, account_address);
CREATE INDEX IF NOT EXISTS stake_transaction__time ON stake_transaction (time);
CREATE INDEX IF NOT EXISTS stake_transaction__transaction_hash ON stake_transaction (transaction_hash);
CREATE INDEX IF NOT EXISTS stake_transaction__transaction_type ON stake_transaction (transaction_type);
```
