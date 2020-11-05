package com.broxus.ton;

public class TonApi {
    /**
     * This class is a base class for all tonlib interface classes.
     */
    public abstract static class Object {
        /**
         * @return string representation of the object.
         */
        public native String toString();

        /**
         * @return identifier uniquely determining type of the object.
         */
        public abstract int getConstructor();
    }

    /**
     * This class is a base class for all tonlib interface function-classes.
     */
    public abstract static class Function extends Object {
        /**
         * @return string representation of the object.
         */
        public native String toString();
    }

    /**
     * 
     */
    public static class AccountAddress extends Object {
        public String accountAddress;

        /**
         * 
         */
        public AccountAddress() {
        }

        public AccountAddress(String accountAddress) {
            this.accountAddress = accountAddress;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = 755613099;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    /**
     * 
     */
    public static class AccountList extends Object {
        public FullAccountState[] accounts;

        /**
         * 
         */
        public AccountList() {
        }

        public AccountList(FullAccountState[] accounts) {
            this.accounts = accounts;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = 2017374805;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    /**
     * 
     */
    public static class AccountRevisionList extends Object {
        public FullAccountState[] revisions;

        /**
         * 
         */
        public AccountRevisionList() {
        }

        public AccountRevisionList(FullAccountState[] revisions) {
            this.revisions = revisions;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = 527197386;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    public abstract static class AccountState extends Object {
    }

    public static class RawAccountState extends AccountState {
        public byte[] code;
        public byte[] data;
        public byte[] frozenHash;

        /**
         * 
         */
        public RawAccountState() {
        }

        public RawAccountState(byte[] code, byte[] data, byte[] frozenHash) {
            this.code = code;
            this.data = data;
            this.frozenHash = frozenHash;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = -531917254;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    public static class WalletV3AccountState extends AccountState {
        public long walletId;
        public int seqno;

        /**
         * 
         */
        public WalletV3AccountState() {
        }

        public WalletV3AccountState(long walletId, int seqno) {
            this.walletId = walletId;
            this.seqno = seqno;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = -1619351478;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    public static class WalletHighloadV1AccountState extends AccountState {
        public long walletId;
        public int seqno;

        /**
         * 
         */
        public WalletHighloadV1AccountState() {
        }

        public WalletHighloadV1AccountState(long walletId, int seqno) {
            this.walletId = walletId;
            this.seqno = seqno;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = 1616372956;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    public static class WalletHighloadV2AccountState extends AccountState {
        public long walletId;

        /**
         * 
         */
        public WalletHighloadV2AccountState() {
        }

        public WalletHighloadV2AccountState(long walletId) {
            this.walletId = walletId;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = -1803723441;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    public static class DnsAccountState extends AccountState {
        public long walletId;

        /**
         * 
         */
        public DnsAccountState() {
        }

        public DnsAccountState(long walletId) {
            this.walletId = walletId;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = 1727715434;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    public static class RwalletAccountState extends AccountState {
        public long walletId;
        public int seqno;
        public long unlockedBalance;
        public RwalletConfig config;

        /**
         * 
         */
        public RwalletAccountState() {
        }

        public RwalletAccountState(long walletId, int seqno, long unlockedBalance, RwalletConfig config) {
            this.walletId = walletId;
            this.seqno = seqno;
            this.unlockedBalance = unlockedBalance;
            this.config = config;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = -739540008;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    public static class PchanAccountState extends AccountState {
        public PchanConfig config;
        public PchanState state;
        public String description;

        /**
         * 
         */
        public PchanAccountState() {
        }

        public PchanAccountState(PchanConfig config, PchanState state, String description) {
            this.config = config;
            this.state = state;
            this.description = description;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = 1612869496;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    public static class UninitedAccountState extends AccountState {
        public byte[] frozenHash;

        /**
         * 
         */
        public UninitedAccountState() {
        }

        public UninitedAccountState(byte[] frozenHash) {
            this.frozenHash = frozenHash;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = 1522374408;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    public abstract static class Action extends Object {
    }

    public static class ActionNoop extends Action {

        /**
         * 
         */
        public ActionNoop() {
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = 1135848603;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    public static class ActionMsg extends Action {
        public MsgMessage[] messages;
        public boolean allowSendToUninited;

        /**
         * 
         */
        public ActionMsg() {
        }

        public ActionMsg(MsgMessage[] messages, boolean allowSendToUninited) {
            this.messages = messages;
            this.allowSendToUninited = allowSendToUninited;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = 246839120;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    public static class ActionDns extends Action {
        public DnsAction[] actions;

        /**
         * 
         */
        public ActionDns() {
        }

        public ActionDns(DnsAction[] actions) {
            this.actions = actions;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = 1193750561;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    public static class ActionPchan extends Action {
        public PchanAction action;

        /**
         * 
         */
        public ActionPchan() {
        }

        public ActionPchan(PchanAction action) {
            this.action = action;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = -1490172447;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    public static class ActionRwallet extends Action {
        public RwalletActionInit action;

        /**
         * 
         */
        public ActionRwallet() {
        }

        public ActionRwallet(RwalletActionInit action) {
            this.action = action;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = -117295163;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    /**
     * 
     */
    public static class AdnlAddress extends Object {
        public String adnlAddress;

        /**
         * 
         */
        public AdnlAddress() {
        }

        public AdnlAddress(String adnlAddress) {
            this.adnlAddress = adnlAddress;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = 70358284;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    /**
     * 
     */
    public static class Bip39Hints extends Object {
        public String[] words;

        /**
         * 
         */
        public Bip39Hints() {
        }

        public Bip39Hints(String[] words) {
            this.words = words;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = 1012243456;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    /**
     * 
     */
    public static class Config extends Object {
        public String config;
        public String blockchainName;
        public boolean useCallbacksForNetwork;
        public boolean ignoreCache;

        /**
         * 
         */
        public Config() {
        }

        public Config(String config, String blockchainName, boolean useCallbacksForNetwork, boolean ignoreCache) {
            this.config = config;
            this.blockchainName = blockchainName;
            this.useCallbacksForNetwork = useCallbacksForNetwork;
            this.ignoreCache = ignoreCache;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = -1538391496;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    /**
     * 
     */
    public static class Data extends Object {
        public byte[] bytes;

        /**
         * 
         */
        public Data() {
        }

        public Data(byte[] bytes) {
            this.bytes = bytes;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = -414733967;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    /**
     * 
     */
    public static class Error extends Object {
        public int code;
        public String message;

        /**
         * 
         */
        public Error() {
        }

        public Error(int code, String message) {
            this.code = code;
            this.message = message;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = -1679978726;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    /**
     * 
     */
    public static class ExportedEncryptedKey extends Object {
        public byte[] data;

        /**
         * 
         */
        public ExportedEncryptedKey() {
        }

        public ExportedEncryptedKey(byte[] data) {
            this.data = data;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = 2024406612;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    /**
     * 
     */
    public static class ExportedKey extends Object {
        public String[] wordList;

        /**
         * 
         */
        public ExportedKey() {
        }

        public ExportedKey(String[] wordList) {
            this.wordList = wordList;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = -1449248297;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    /**
     * 
     */
    public static class ExportedPemKey extends Object {
        public String pem;

        /**
         * 
         */
        public ExportedPemKey() {
        }

        public ExportedPemKey(String pem) {
            this.pem = pem;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = 1425473725;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    /**
     * 
     */
    public static class ExportedUnencryptedKey extends Object {
        public byte[] data;

        /**
         * 
         */
        public ExportedUnencryptedKey() {
        }

        public ExportedUnencryptedKey(byte[] data) {
            this.data = data;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = 730045160;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    /**
     * 
     */
    public static class Fees extends Object {
        public long inFwdFee;
        public long storageFee;
        public long gasFee;
        public long fwdFee;

        /**
         * 
         */
        public Fees() {
        }

        public Fees(long inFwdFee, long storageFee, long gasFee, long fwdFee) {
            this.inFwdFee = inFwdFee;
            this.storageFee = storageFee;
            this.gasFee = gasFee;
            this.fwdFee = fwdFee;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = 1676273340;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    /**
     * 
     */
    public static class FullAccountState extends Object {
        public AccountAddress address;
        public long balance;
        public InternalTransactionId lastTransactionId;
        public TonBlockIdExt blockId;
        public long syncUtime;
        public AccountState accountState;
        public int revision;

        /**
         * 
         */
        public FullAccountState() {
        }

        public FullAccountState(AccountAddress address, long balance, InternalTransactionId lastTransactionId, TonBlockIdExt blockId, long syncUtime, AccountState accountState, int revision) {
            this.address = address;
            this.balance = balance;
            this.lastTransactionId = lastTransactionId;
            this.blockId = blockId;
            this.syncUtime = syncUtime;
            this.accountState = accountState;
            this.revision = revision;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = 1456618057;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    public abstract static class InitialAccountState extends Object {
    }

    public static class RawInitialAccountState extends InitialAccountState {
        public byte[] code;
        public byte[] data;

        /**
         * 
         */
        public RawInitialAccountState() {
        }

        public RawInitialAccountState(byte[] code, byte[] data) {
            this.code = code;
            this.data = data;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = -337945529;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    public static class WalletV3InitialAccountState extends InitialAccountState {
        public String publicKey;
        public long walletId;

        /**
         * 
         */
        public WalletV3InitialAccountState() {
        }

        public WalletV3InitialAccountState(String publicKey, long walletId) {
            this.publicKey = publicKey;
            this.walletId = walletId;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = -118074048;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    public static class WalletHighloadV1InitialAccountState extends InitialAccountState {
        public String publicKey;
        public long walletId;

        /**
         * 
         */
        public WalletHighloadV1InitialAccountState() {
        }

        public WalletHighloadV1InitialAccountState(String publicKey, long walletId) {
            this.publicKey = publicKey;
            this.walletId = walletId;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = -327901626;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    public static class WalletHighloadV2InitialAccountState extends InitialAccountState {
        public String publicKey;
        public long walletId;

        /**
         * 
         */
        public WalletHighloadV2InitialAccountState() {
        }

        public WalletHighloadV2InitialAccountState(String publicKey, long walletId) {
            this.publicKey = publicKey;
            this.walletId = walletId;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = 1966373161;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    public static class RwalletInitialAccountState extends InitialAccountState {
        public String initPublicKey;
        public String publicKey;
        public long walletId;

        /**
         * 
         */
        public RwalletInitialAccountState() {
        }

        public RwalletInitialAccountState(String initPublicKey, String publicKey, long walletId) {
            this.initPublicKey = initPublicKey;
            this.publicKey = publicKey;
            this.walletId = walletId;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = 1169755156;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    public static class DnsInitialAccountState extends InitialAccountState {
        public String publicKey;
        public long walletId;

        /**
         * 
         */
        public DnsInitialAccountState() {
        }

        public DnsInitialAccountState(String publicKey, long walletId) {
            this.publicKey = publicKey;
            this.walletId = walletId;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = 1842062527;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    public static class PchanInitialAccountState extends InitialAccountState {
        public PchanConfig config;

        /**
         * 
         */
        public PchanInitialAccountState() {
        }

        public PchanInitialAccountState(PchanConfig config) {
            this.config = config;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = -1304552124;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    public abstract static class InputKey extends Object {
    }

    public static class InputKeyRegular extends InputKey {
        public Key key;
        public byte[] localPassword;

        /**
         * 
         */
        public InputKeyRegular() {
        }

        public InputKeyRegular(Key key, byte[] localPassword) {
            this.key = key;
            this.localPassword = localPassword;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = -555399522;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    public static class InputKeyFake extends InputKey {

        /**
         * 
         */
        public InputKeyFake() {
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = -1074054722;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    /**
     * 
     */
    public static class Key extends Object {
        public String publicKey;
        public byte[] secret;

        /**
         * 
         */
        public Key() {
        }

        public Key(String publicKey, byte[] secret) {
            this.publicKey = publicKey;
            this.secret = secret;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = -1978362923;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    public abstract static class KeyStoreType extends Object {
    }

    public static class KeyStoreTypeDirectory extends KeyStoreType {
        public String directory;

        /**
         * 
         */
        public KeyStoreTypeDirectory() {
        }

        public KeyStoreTypeDirectory(String directory) {
            this.directory = directory;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = -378990038;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    public static class KeyStoreTypeInMemory extends KeyStoreType {

        /**
         * 
         */
        public KeyStoreTypeInMemory() {
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = -2106848825;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    /**
     * This class is an abstract base class.
     * Describes a stream to which tonlib internal log is written.
     */
    public abstract static class LogStream extends Object {
    }

    /**
     * The log is written to stderr or an OS specific log.
     */
    public static class LogStreamDefault extends LogStream {

        /**
         * The log is written to stderr or an OS specific log.
         */
        public LogStreamDefault() {
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = 1390581436;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    /**
     * The log is written to a file.
     */
    public static class LogStreamFile extends LogStream {
        /**
         * Path to the file to where the internal tonlib log will be written.
         */
        public String path;
        /**
         * Maximum size of the file to where the internal tonlib log is written before the file will be auto-rotated.
         */
        public long maxFileSize;

        /**
         * The log is written to a file.
         */
        public LogStreamFile() {
        }

        /**
         * The log is written to a file.
         *
         * @param path Path to the file to where the internal tonlib log will be written.
         * @param maxFileSize Maximum size of the file to where the internal tonlib log is written before the file will be auto-rotated.
         */
        public LogStreamFile(String path, long maxFileSize) {
            this.path = path;
            this.maxFileSize = maxFileSize;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = -1880085930;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    /**
     * The log is written nowhere.
     */
    public static class LogStreamEmpty extends LogStream {

        /**
         * The log is written nowhere.
         */
        public LogStreamEmpty() {
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = -499912244;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    /**
     * Contains a list of available tonlib internal log tags.
     */
    public static class LogTags extends Object {
        /**
         * List of log tags.
         */
        public String[] tags;

        /**
         * Contains a list of available tonlib internal log tags.
         */
        public LogTags() {
        }

        /**
         * Contains a list of available tonlib internal log tags.
         *
         * @param tags List of log tags.
         */
        public LogTags(String[] tags) {
            this.tags = tags;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = -1604930601;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    /**
     * Contains a tonlib internal log verbosity level.
     */
    public static class LogVerbosityLevel extends Object {
        /**
         * Log verbosity level.
         */
        public int verbosityLevel;

        /**
         * Contains a tonlib internal log verbosity level.
         */
        public LogVerbosityLevel() {
        }

        /**
         * Contains a tonlib internal log verbosity level.
         *
         * @param verbosityLevel Log verbosity level.
         */
        public LogVerbosityLevel(int verbosityLevel) {
            this.verbosityLevel = verbosityLevel;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = 1734624234;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    /**
     * 
     */
    public static class Ok extends Object {

        /**
         * 
         */
        public Ok() {
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = -722616727;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    /**
     * 
     */
    public static class Options extends Object {
        public Config config;
        public KeyStoreType keystoreType;

        /**
         * 
         */
        public Options() {
        }

        public Options(Config config, KeyStoreType keystoreType) {
            this.config = config;
            this.keystoreType = keystoreType;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = -1924388359;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    public abstract static class SyncState extends Object {
    }

    public static class SyncStateDone extends SyncState {

        /**
         * 
         */
        public SyncStateDone() {
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = 1408448777;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    public static class SyncStateInProgress extends SyncState {
        public int fromSeqno;
        public int toSeqno;
        public int currentSeqno;

        /**
         * 
         */
        public SyncStateInProgress() {
        }

        public SyncStateInProgress(int fromSeqno, int toSeqno, int currentSeqno) {
            this.fromSeqno = fromSeqno;
            this.toSeqno = toSeqno;
            this.currentSeqno = currentSeqno;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = 107726023;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    /**
     * 
     */
    public static class TransactionSearchResult extends Object {
        public int syncUtime;
        public boolean found;
        public long lt;
        public byte[] hash;

        /**
         * 
         */
        public TransactionSearchResult() {
        }

        public TransactionSearchResult(int syncUtime, boolean found, long lt, byte[] hash) {
            this.syncUtime = syncUtime;
            this.found = found;
            this.lt = lt;
            this.hash = hash;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = -113978227;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    /**
     * 
     */
    public static class UnpackedAccountAddress extends Object {
        public int workchainId;
        public boolean bounceable;
        public boolean testnet;
        public byte[] addr;

        /**
         * 
         */
        public UnpackedAccountAddress() {
        }

        public UnpackedAccountAddress(int workchainId, boolean bounceable, boolean testnet, byte[] addr) {
            this.workchainId = workchainId;
            this.bounceable = bounceable;
            this.testnet = testnet;
            this.addr = addr;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = 1892946998;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    public abstract static class Update extends Object {
    }

    public static class UpdateSendLiteServerQuery extends Update {
        public long id;
        public byte[] data;

        /**
         * 
         */
        public UpdateSendLiteServerQuery() {
        }

        public UpdateSendLiteServerQuery(long id, byte[] data) {
            this.id = id;
            this.data = data;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = -1555130916;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    public static class UpdateSyncState extends Update {
        public SyncState syncState;

        /**
         * 
         */
        public UpdateSyncState() {
        }

        public UpdateSyncState(SyncState syncState) {
            this.syncState = syncState;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = 1204298718;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    public abstract static class DnsAction extends Object {
    }

    public static class DnsActionDeleteAll extends DnsAction {

        /**
         * 
         */
        public DnsActionDeleteAll() {
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = 1067356318;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    public static class DnsActionDelete extends DnsAction {
        public String name;
        public int category;

        /**
         * 
         */
        public DnsActionDelete() {
        }

        public DnsActionDelete(String name, int category) {
            this.name = name;
            this.category = category;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = 775206882;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    public static class DnsActionSet extends DnsAction {
        public DnsEntry entry;

        /**
         * 
         */
        public DnsActionSet() {
        }

        public DnsActionSet(DnsEntry entry) {
            this.entry = entry;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = -1374965309;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    /**
     * 
     */
    public static class DnsEntry extends Object {
        public String name;
        public int category;
        public DnsEntryData entry;

        /**
         * 
         */
        public DnsEntry() {
        }

        public DnsEntry(String name, int category, DnsEntryData entry) {
            this.name = name;
            this.category = category;
            this.entry = entry;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = -1842435400;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    public abstract static class DnsEntryData extends Object {
    }

    public static class DnsEntryDataUnknown extends DnsEntryData {
        public byte[] bytes;

        /**
         * 
         */
        public DnsEntryDataUnknown() {
        }

        public DnsEntryDataUnknown(byte[] bytes) {
            this.bytes = bytes;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = -1285893248;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    public static class DnsEntryDataText extends DnsEntryData {
        public String text;

        /**
         * 
         */
        public DnsEntryDataText() {
        }

        public DnsEntryDataText(String text) {
            this.text = text;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = -792485614;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    public static class DnsEntryDataNextResolver extends DnsEntryData {
        public AccountAddress resolver;

        /**
         * 
         */
        public DnsEntryDataNextResolver() {
        }

        public DnsEntryDataNextResolver(AccountAddress resolver) {
            this.resolver = resolver;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = 330382792;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    public static class DnsEntryDataSmcAddress extends DnsEntryData {
        public AccountAddress smcAddress;

        /**
         * 
         */
        public DnsEntryDataSmcAddress() {
        }

        public DnsEntryDataSmcAddress(AccountAddress smcAddress) {
            this.smcAddress = smcAddress;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = -1759937982;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    public static class DnsEntryDataAdnlAddress extends DnsEntryData {
        public AdnlAddress adnlAddress;

        /**
         * 
         */
        public DnsEntryDataAdnlAddress() {
        }

        public DnsEntryDataAdnlAddress(AdnlAddress adnlAddress) {
            this.adnlAddress = adnlAddress;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = -1114064368;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    /**
     * 
     */
    public static class DnsResolved extends Object {
        public DnsEntry[] entries;

        /**
         * 
         */
        public DnsResolved() {
        }

        public DnsResolved(DnsEntry[] entries) {
            this.entries = entries;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = 2090272150;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    /**
     * 
     */
    public static class FtabiDecodedInput extends Object {
        public FtabiValue[] headerValues;
        public FtabiValue[] values;

        /**
         * 
         */
        public FtabiDecodedInput() {
        }

        public FtabiDecodedInput(FtabiValue[] headerValues, FtabiValue[] values) {
            this.headerValues = headerValues;
            this.values = values;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = -732580393;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    /**
     * 
     */
    public static class FtabiDecodedOutput extends Object {
        public FtabiValue[] values;

        /**
         * 
         */
        public FtabiDecodedOutput() {
        }

        public FtabiDecodedOutput(FtabiValue[] values) {
            this.values = values;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = -654813471;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    /**
     * 
     */
    public static class FtabiFunction extends Object {
        public String name;
        public FtabiParam[] headerParams;
        public FtabiParam[] inputParams;
        public FtabiParam[] outputParams;
        public int inputId;
        public int outputId;

        /**
         * 
         */
        public FtabiFunction() {
        }

        public FtabiFunction(String name, FtabiParam[] headerParams, FtabiParam[] inputParams, FtabiParam[] outputParams, int inputId, int outputId) {
            this.name = name;
            this.headerParams = headerParams;
            this.inputParams = inputParams;
            this.outputParams = outputParams;
            this.inputId = inputId;
            this.outputId = outputId;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = -1465955738;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    public abstract static class FtabiFunctionCall extends Object {
    }

    public static class FtabiFunctionCallExternal extends FtabiFunctionCall {
        public FtabiValue[] headerValues;
        public FtabiValue[] inputValues;

        /**
         * 
         */
        public FtabiFunctionCallExternal() {
        }

        public FtabiFunctionCallExternal(FtabiValue[] headerValues, FtabiValue[] inputValues) {
            this.headerValues = headerValues;
            this.inputValues = inputValues;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = -263415616;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    public static class FtabiFunctionCallExternalSigned extends FtabiFunctionCall {
        public FtabiValue[] headerValues;
        public FtabiValue[] inputValues;
        public InputKey key;

        /**
         * 
         */
        public FtabiFunctionCallExternalSigned() {
        }

        public FtabiFunctionCallExternalSigned(FtabiValue[] headerValues, FtabiValue[] inputValues, InputKey key) {
            this.headerValues = headerValues;
            this.inputValues = inputValues;
            this.key = key;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = 243828214;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    public static class FtabiFunctionCallInternal extends FtabiFunctionCall {
        public FtabiValue[] headerValues;
        public FtabiValue[] inputValues;

        /**
         * 
         */
        public FtabiFunctionCallInternal() {
        }

        public FtabiFunctionCallInternal(FtabiValue[] headerValues, FtabiValue[] inputValues) {
            this.headerValues = headerValues;
            this.inputValues = inputValues;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = 260495355;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    public static class FtabiFunctionCallInternalSigned extends FtabiFunctionCall {
        public FtabiValue[] headerValues;
        public FtabiValue[] inputValues;
        public InputKey key;

        /**
         * 
         */
        public FtabiFunctionCallInternalSigned() {
        }

        public FtabiFunctionCallInternalSigned(FtabiValue[] headerValues, FtabiValue[] inputValues, InputKey key) {
            this.headerValues = headerValues;
            this.inputValues = inputValues;
            this.key = key;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = 98357086;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    /**
     * 
     */
    public static class FtabiFunctionId extends Object {
        public int id;

        /**
         * 
         */
        public FtabiFunctionId() {
        }

        public FtabiFunctionId(int id) {
            this.id = id;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = -1914917873;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    /**
     * 
     */
    public static class FtabiFunctionSignature extends Object {
        public String data;

        /**
         * 
         */
        public FtabiFunctionSignature() {
        }

        public FtabiFunctionSignature(String data) {
            this.data = data;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = -169190151;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    /**
     * 
     */
    public static class FtabiMessageBody extends Object {
        public byte[] data;

        /**
         * 
         */
        public FtabiMessageBody() {
        }

        public FtabiMessageBody(byte[] data) {
            this.data = data;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = -863202997;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    public abstract static class FtabiParam extends Object {
    }

    public static class FtabiParamUint extends FtabiParam {
        public String name;
        public int size;

        /**
         * 
         */
        public FtabiParamUint() {
        }

        public FtabiParamUint(String name, int size) {
            this.name = name;
            this.size = size;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = -1386281234;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    public static class FtabiParamInt extends FtabiParam {
        public String name;
        public int size;

        /**
         * 
         */
        public FtabiParamInt() {
        }

        public FtabiParamInt(String name, int size) {
            this.name = name;
            this.size = size;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = -1613007286;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    public static class FtabiParamBool extends FtabiParam {
        public String name;

        /**
         * 
         */
        public FtabiParamBool() {
        }

        public FtabiParamBool(String name) {
            this.name = name;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = 1933949968;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    public static class FtabiParamTuple extends FtabiParam {
        public String name;
        public FtabiParam[] itemTypes;

        /**
         * 
         */
        public FtabiParamTuple() {
        }

        public FtabiParamTuple(String name, FtabiParam[] itemTypes) {
            this.name = name;
            this.itemTypes = itemTypes;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = -81737302;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    public static class FtabiParamArray extends FtabiParam {
        public String name;
        public FtabiParam itemType;

        /**
         * 
         */
        public FtabiParamArray() {
        }

        public FtabiParamArray(String name, FtabiParam itemType) {
            this.name = name;
            this.itemType = itemType;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = 566778251;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    public static class FtabiParamFixedArray extends FtabiParam {
        public String name;
        public FtabiParam itemType;
        public int size;

        /**
         * 
         */
        public FtabiParamFixedArray() {
        }

        public FtabiParamFixedArray(String name, FtabiParam itemType, int size) {
            this.name = name;
            this.itemType = itemType;
            this.size = size;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = -617051548;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    public static class FtabiParamCell extends FtabiParam {
        public String name;

        /**
         * 
         */
        public FtabiParamCell() {
        }

        public FtabiParamCell(String name) {
            this.name = name;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = 623390677;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    public static class FtabiParamMap extends FtabiParam {
        public String name;
        public FtabiParam keyType;
        public FtabiParam valueType;

        /**
         * 
         */
        public FtabiParamMap() {
        }

        public FtabiParamMap(String name, FtabiParam keyType, FtabiParam valueType) {
            this.name = name;
            this.keyType = keyType;
            this.valueType = valueType;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = 292933612;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    public static class FtabiParamAddress extends FtabiParam {
        public String name;

        /**
         * 
         */
        public FtabiParamAddress() {
        }

        public FtabiParamAddress(String name) {
            this.name = name;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = 699178911;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    public static class FtabiParamBytes extends FtabiParam {
        public String name;

        /**
         * 
         */
        public FtabiParamBytes() {
        }

        public FtabiParamBytes(String name) {
            this.name = name;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = -1132170624;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    public static class FtabiParamFixedBytes extends FtabiParam {
        public String name;
        public int size;

        /**
         * 
         */
        public FtabiParamFixedBytes() {
        }

        public FtabiParamFixedBytes(String name, int size) {
            this.name = name;
            this.size = size;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = -863091922;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    public static class FtabiParamGram extends FtabiParam {
        public String name;

        /**
         * 
         */
        public FtabiParamGram() {
        }

        public FtabiParamGram(String name) {
            this.name = name;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = -1002119795;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    public static class FtabiParamTime extends FtabiParam {
        public String name;

        /**
         * 
         */
        public FtabiParamTime() {
        }

        public FtabiParamTime(String name) {
            this.name = name;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = -1259393718;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    public static class FtabiParamExpire extends FtabiParam {
        public String name;

        /**
         * 
         */
        public FtabiParamExpire() {
        }

        public FtabiParamExpire(String name) {
            this.name = name;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = 1765259934;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    public static class FtabiParamPublicKey extends FtabiParam {
        public String name;

        /**
         * 
         */
        public FtabiParamPublicKey() {
        }

        public FtabiParamPublicKey(String name) {
            this.name = name;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = 1625468280;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    public abstract static class FtabiValue extends Object {
    }

    public static class FtabiValueInt extends FtabiValue {
        public FtabiParam param;
        public long value;

        /**
         * 
         */
        public FtabiValueInt() {
        }

        public FtabiValueInt(FtabiParam param, long value) {
            this.param = param;
            this.value = value;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = -40626997;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    public static class FtabiValueBigInt extends FtabiValue {
        public FtabiParam param;
        public byte[] value;

        /**
         * 
         */
        public FtabiValueBigInt() {
        }

        public FtabiValueBigInt(FtabiParam param, byte[] value) {
            this.param = param;
            this.value = value;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = 1080761623;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    public static class FtabiValueBool extends FtabiValue {
        public FtabiParam param;
        public boolean value;

        /**
         * 
         */
        public FtabiValueBool() {
        }

        public FtabiValueBool(FtabiParam param, boolean value) {
            this.param = param;
            this.value = value;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = 223221343;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    public static class FtabiValueTuple extends FtabiValue {
        public FtabiParam param;
        public FtabiValue[] values;

        /**
         * 
         */
        public FtabiValueTuple() {
        }

        public FtabiValueTuple(FtabiParam param, FtabiValue[] values) {
            this.param = param;
            this.values = values;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = -1628023961;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    public static class FtabiValueCell extends FtabiValue {
        public FtabiParam param;
        public TvmCell value;

        /**
         * 
         */
        public FtabiValueCell() {
        }

        public FtabiValueCell(FtabiParam param, TvmCell value) {
            this.param = param;
            this.value = value;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = -100198821;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    public static class FtabiValueAddress extends FtabiValue {
        public FtabiParam param;
        public AccountAddress value;

        /**
         * 
         */
        public FtabiValueAddress() {
        }

        public FtabiValueAddress(FtabiParam param, AccountAddress value) {
            this.param = param;
            this.value = value;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = 247798540;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    public static class FtabiValueBytes extends FtabiValue {
        public FtabiParam param;
        public byte[] value;

        /**
         * 
         */
        public FtabiValueBytes() {
        }

        public FtabiValueBytes(FtabiParam param, byte[] value) {
            this.param = param;
            this.value = value;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = -1387866850;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    public static class FtabiValueGram extends FtabiValue {
        public FtabiParam param;
        public long value;

        /**
         * 
         */
        public FtabiValueGram() {
        }

        public FtabiValueGram(FtabiParam param, long value) {
            this.param = param;
            this.value = value;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = 1730182437;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    public static class FtabiValueTime extends FtabiValue {
        public FtabiParam param;
        public long value;

        /**
         * 
         */
        public FtabiValueTime() {
        }

        public FtabiValueTime(FtabiParam param, long value) {
            this.param = param;
            this.value = value;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = 1536525219;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    public static class FtabiValueExpire extends FtabiValue {
        public FtabiParam param;
        public int value;

        /**
         * 
         */
        public FtabiValueExpire() {
        }

        public FtabiValueExpire(FtabiParam param, int value) {
            this.param = param;
            this.value = value;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = 135783732;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    public static class FtabiValuePublicKey extends FtabiValue {
        public FtabiParam param;
        public String value;

        /**
         * 
         */
        public FtabiValuePublicKey() {
        }

        public FtabiValuePublicKey(FtabiParam param, String value) {
            this.param = param;
            this.value = value;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = -613404399;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    /**
     * 
     */
    public static class InternalTransactionId extends Object {
        public long lt;
        public byte[] hash;

        /**
         * 
         */
        public InternalTransactionId() {
        }

        public InternalTransactionId(long lt, byte[] hash) {
            this.lt = lt;
            this.hash = hash;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = -989527262;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    public static class LiteServerAccount extends Object {
        public LiteServerMessageAddressInt addr;
        public LiteServerStorageInfo storageStat;
        public long lastTransLt;
        public byte[] lastTransHash;
        public LiteServerCurrencyCollection balance;
        public LiteServerAccountState state;

        public LiteServerAccount() {
        }

        public LiteServerAccount(LiteServerMessageAddressInt addr, LiteServerStorageInfo storageStat, long lastTransLt, byte[] lastTransHash, LiteServerCurrencyCollection balance, LiteServerAccountState state) {
            this.addr = addr;
            this.storageStat = storageStat;
            this.lastTransLt = lastTransLt;
            this.lastTransHash = lastTransHash;
            this.balance = balance;
            this.state = state;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = -1864958969;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    /**
     * 
     */
    public static class LiteServerAccountId extends Object {
        public int workchain;
        public byte[] id;

        /**
         * 
         */
        public LiteServerAccountId() {
        }

        public LiteServerAccountId(int workchain, byte[] id) {
            this.workchain = workchain;
            this.id = id;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = 1642990559;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    public abstract static class LiteServerAccountState extends Object {
    }

    public static class LiteServerAccountStateUninit extends LiteServerAccountState {

        /**
         * 
         */
        public LiteServerAccountStateUninit() {
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = 1967894676;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    public static class LiteServerAccountStateActive extends LiteServerAccountState {
        public boolean hasSplitDepth;
        public int splitDepth;
        public LiteServerTickTock special;
        public boolean hasCode;
        public byte[] code;
        public boolean hasData;
        public byte[] data;
        public LiteServerSimpleLib[] library;

        public LiteServerAccountStateActive() {
        }

        public LiteServerAccountStateActive(boolean hasSplitDepth, int splitDepth, LiteServerTickTock special, boolean hasCode, byte[] code, boolean hasData, byte[] data, LiteServerSimpleLib[] library) {
            this.hasSplitDepth = hasSplitDepth;
            this.splitDepth = splitDepth;
            this.special = special;
            this.hasCode = hasCode;
            this.code = code;
            this.hasData = hasData;
            this.data = data;
            this.library = library;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = -1395355626;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    public static class LiteServerAccountStateFrozen extends LiteServerAccountState {
        public byte[] stateHash;

        /**
         * 
         */
        public LiteServerAccountStateFrozen() {
        }

        public LiteServerAccountStateFrozen(byte[] stateHash) {
            this.stateHash = stateHash;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = 899701396;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    /**
     * 
     */
    public static class LiteServerAllShardsInfo extends Object {
        public int minShardGenUtime;
        public int maxShardGenUtime;
        public LiteServerShardHash[] shards;

        /**
         * 
         */
        public LiteServerAllShardsInfo() {
        }

        public LiteServerAllShardsInfo(int minShardGenUtime, int maxShardGenUtime, LiteServerShardHash[] shards) {
            this.minShardGenUtime = minShardGenUtime;
            this.maxShardGenUtime = maxShardGenUtime;
            this.shards = shards;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = -59710865;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    public static class LiteServerBlock extends Object {
        public TonBlockIdExt id;
        public TonBlockIdExt masterchainId;
        public int globalId;
        public LiteServerBlockInfo info;
        public LiteServerValueFlow valueFlow;
        public LiteServerBlockExtra blockExtra;
        public TonBlockIdExt[] previous;
        public TonBlockId[] next;
        public LiteServerTransaction[] transactions;

        public LiteServerBlock() {
        }

        public LiteServerBlock(TonBlockIdExt id, TonBlockIdExt masterchainId, int globalId, LiteServerBlockInfo info, LiteServerValueFlow valueFlow, LiteServerBlockExtra blockExtra, TonBlockIdExt[] previous, TonBlockId[] next, LiteServerTransaction[] transactions) {
            this.id = id;
            this.masterchainId = masterchainId;
            this.globalId = globalId;
            this.info = info;
            this.valueFlow = valueFlow;
            this.blockExtra = blockExtra;
            this.previous = previous;
            this.next = next;
            this.transactions = transactions;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = -1842027946;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    public static class LiteServerBlockExtra extends Object {
        public LiteServerInMsgDescrItem[] inMsgDescr;
        public LiteServerOutMsgDescrItem[] outMsgDescr;
        public LiteServerBlockExtraAccount[] accounts;
        public byte[] randSeed;
        public byte[] createdBy;
        public LiteServerMcBlockExtra custom;

        public LiteServerBlockExtra() {
        }

        public LiteServerBlockExtra(LiteServerInMsgDescrItem[] inMsgDescr, LiteServerOutMsgDescrItem[] outMsgDescr, LiteServerBlockExtraAccount[] accounts, byte[] randSeed, byte[] createdBy, LiteServerMcBlockExtra custom) {
            this.inMsgDescr = inMsgDescr;
            this.outMsgDescr = outMsgDescr;
            this.accounts = accounts;
            this.randSeed = randSeed;
            this.createdBy = createdBy;
            this.custom = custom;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = -1526595903;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    public static class LiteServerBlockExtraAccount extends Object {
        public byte[] addr;
        public int transactionCnt;
        public LiteServerHashUpdate stateUpdate;

        public LiteServerBlockExtraAccount() {
        }

        public LiteServerBlockExtraAccount(byte[] addr, int transactionCnt, LiteServerHashUpdate stateUpdate) {
            this.addr = addr;
            this.transactionCnt = transactionCnt;
            this.stateUpdate = stateUpdate;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = -1663207366;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    /**
     * 
     */
    public static class LiteServerBlockHeader extends Object {
        public TonBlockIdExt id;
        public int mode;
        public byte[] headerProof;

        /**
         * 
         */
        public LiteServerBlockHeader() {
        }

        public LiteServerBlockHeader(TonBlockIdExt id, int mode, byte[] headerProof) {
            this.id = id;
            this.mode = mode;
            this.headerProof = headerProof;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = 2071862837;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    public static class LiteServerBlockInfo extends Object {
        public int version;
        public boolean notMaster;
        public boolean afterMerge;
        public boolean beforeSplit;
        public boolean afterSplit;
        public boolean wantSplit;
        public boolean wantMerge;
        public boolean keyBlock;
        public boolean vertSeqnoIncr;
        public int flags;
        public int seqNo;
        public int vertSeqNo;
        public int genUtime;
        public long startLt;
        public long endLt;
        public int genValidatorListHashShort;
        public int genCatchainSeqno;
        public int minRefMcSeqno;
        public int prevKeyBlockSeqno;
        public LiteServerGlobalVersion genSoftware;
        public LiteServerExtBlockRef masterRef;

        public LiteServerBlockInfo() {
        }

        public LiteServerBlockInfo(int version, boolean notMaster, boolean afterMerge, boolean beforeSplit, boolean afterSplit, boolean wantSplit, boolean wantMerge, boolean keyBlock, boolean vertSeqnoIncr, int flags, int seqNo, int vertSeqNo, int genUtime, long startLt, long endLt, int genValidatorListHashShort, int genCatchainSeqno, int minRefMcSeqno, int prevKeyBlockSeqno, LiteServerGlobalVersion genSoftware, LiteServerExtBlockRef masterRef) {
            this.version = version;
            this.notMaster = notMaster;
            this.afterMerge = afterMerge;
            this.beforeSplit = beforeSplit;
            this.afterSplit = afterSplit;
            this.wantSplit = wantSplit;
            this.wantMerge = wantMerge;
            this.keyBlock = keyBlock;
            this.vertSeqnoIncr = vertSeqnoIncr;
            this.flags = flags;
            this.seqNo = seqNo;
            this.vertSeqNo = vertSeqNo;
            this.genUtime = genUtime;
            this.startLt = startLt;
            this.endLt = endLt;
            this.genValidatorListHashShort = genValidatorListHashShort;
            this.genCatchainSeqno = genCatchainSeqno;
            this.minRefMcSeqno = minRefMcSeqno;
            this.prevKeyBlockSeqno = prevKeyBlockSeqno;
            this.genSoftware = genSoftware;
            this.masterRef = masterRef;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = -577933135;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    public abstract static class LiteServerBlockLink extends Object {
    }

    public static class LiteServerBlockLinkBack extends LiteServerBlockLink {
        public boolean toKeyBlock;
        public TonBlockIdExt from;
        public TonBlockIdExt to;
        public byte[] destProof;
        public byte[] proof;
        public byte[] stateProof;

        /**
         * 
         */
        public LiteServerBlockLinkBack() {
        }

        public LiteServerBlockLinkBack(boolean toKeyBlock, TonBlockIdExt from, TonBlockIdExt to, byte[] destProof, byte[] proof, byte[] stateProof) {
            this.toKeyBlock = toKeyBlock;
            this.from = from;
            this.to = to;
            this.destProof = destProof;
            this.proof = proof;
            this.stateProof = stateProof;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = 702709257;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    public static class LiteServerBlockLinkForward extends LiteServerBlockLink {
        public boolean toKeyBlock;
        public TonBlockIdExt from;
        public TonBlockIdExt to;
        public byte[] destProof;
        public byte[] configProof;
        public LiteServerSignatureSet signatures;

        /**
         * 
         */
        public LiteServerBlockLinkForward() {
        }

        public LiteServerBlockLinkForward(boolean toKeyBlock, TonBlockIdExt from, TonBlockIdExt to, byte[] destProof, byte[] configProof, LiteServerSignatureSet signatures) {
            this.toKeyBlock = toKeyBlock;
            this.from = from;
            this.to = to;
            this.destProof = destProof;
            this.configProof = configProof;
            this.signatures = signatures;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = 1391877064;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    public static class LiteServerBlockState extends Object {
        public int utime;
        public long lt;
        public LiteServerCurrencyCollection totalBalance;
        public LiteServerCurrencyCollection totalValidatorFees;
        public LiteServerCurrencyCollection globalBalance;
        public LiteServerAccount[] accounts;

        public LiteServerBlockState() {
        }

        public LiteServerBlockState(int utime, long lt, LiteServerCurrencyCollection totalBalance, LiteServerCurrencyCollection totalValidatorFees, LiteServerCurrencyCollection globalBalance, LiteServerAccount[] accounts) {
            this.utime = utime;
            this.lt = lt;
            this.totalBalance = totalBalance;
            this.totalValidatorFees = totalValidatorFees;
            this.globalBalance = globalBalance;
            this.accounts = accounts;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = 734361122;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    /**
     * 
     */
    public static class LiteServerBlockTransactions extends Object {
        public TonBlockIdExt id;
        public int reqCount;
        public boolean incomplete;
        public LiteServerTransactionId[] ids;
        public byte[] proof;

        /**
         * 
         */
        public LiteServerBlockTransactions() {
        }

        public LiteServerBlockTransactions(TonBlockIdExt id, int reqCount, boolean incomplete, LiteServerTransactionId[] ids, byte[] proof) {
            this.id = id;
            this.reqCount = reqCount;
            this.incomplete = incomplete;
            this.ids = ids;
            this.proof = proof;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = 1592395672;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    /**
     * 
     */
    public static class LiteServerConfigBlockCreateFees extends Object {
        public byte[] masterchainBlockFee;
        public byte[] basechainBlockFee;

        /**
         * 
         */
        public LiteServerConfigBlockCreateFees() {
        }

        public LiteServerConfigBlockCreateFees(byte[] masterchainBlockFee, byte[] basechainBlockFee) {
            this.masterchainBlockFee = masterchainBlockFee;
            this.basechainBlockFee = basechainBlockFee;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = -1992997217;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    public static class LiteServerConfigBlockLimits extends Object {
        public LiteServerConfigParamLimits bytes;
        public LiteServerConfigParamLimits gas;
        public LiteServerConfigParamLimits ltDelta;

        public LiteServerConfigBlockLimits() {
        }

        public LiteServerConfigBlockLimits(LiteServerConfigParamLimits bytes, LiteServerConfigParamLimits gas, LiteServerConfigParamLimits ltDelta) {
            this.bytes = bytes;
            this.gas = gas;
            this.ltDelta = ltDelta;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = 1130000416;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    public abstract static class LiteServerConfigCatchainConfig extends Object {
    }

    public static class LiteServerConfigCatchainConfigRegular extends LiteServerConfigCatchainConfig {
        public int mcCatchainLifetime;
        public int shardCatchainLifetime;
        public int shardValidatorsLifetime;
        public int shardValidatorsNum;

        public LiteServerConfigCatchainConfigRegular() {
        }

        public LiteServerConfigCatchainConfigRegular(int mcCatchainLifetime, int shardCatchainLifetime, int shardValidatorsLifetime, int shardValidatorsNum) {
            this.mcCatchainLifetime = mcCatchainLifetime;
            this.shardCatchainLifetime = shardCatchainLifetime;
            this.shardValidatorsLifetime = shardValidatorsLifetime;
            this.shardValidatorsNum = shardValidatorsNum;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = -872511585;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    public static class LiteServerConfigCatchainConfigNew extends LiteServerConfigCatchainConfig {
        public int flags;
        public boolean shuffleMcValidators;
        public int mcCatchainLifetime;
        public int shardCatchainLifetime;
        public int shardValidatorsLifetime;
        public int shardValidatorsNum;

        public LiteServerConfigCatchainConfigNew() {
        }

        public LiteServerConfigCatchainConfigNew(int flags, boolean shuffleMcValidators, int mcCatchainLifetime, int shardCatchainLifetime, int shardValidatorsLifetime, int shardValidatorsNum) {
            this.flags = flags;
            this.shuffleMcValidators = shuffleMcValidators;
            this.mcCatchainLifetime = mcCatchainLifetime;
            this.shardCatchainLifetime = shardCatchainLifetime;
            this.shardValidatorsLifetime = shardValidatorsLifetime;
            this.shardValidatorsNum = shardValidatorsNum;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = 728412637;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    /**
     * 
     */
    public static class LiteServerConfigComplaintPricing extends Object {
        public byte[] deposit;
        public byte[] bitPrice;
        public byte[] cellPrice;

        /**
         * 
         */
        public LiteServerConfigComplaintPricing() {
        }

        public LiteServerConfigComplaintPricing(byte[] deposit, byte[] bitPrice, byte[] cellPrice) {
            this.deposit = deposit;
            this.bitPrice = bitPrice;
            this.cellPrice = cellPrice;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = -2052274580;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    public abstract static class LiteServerConfigConsensusConfig extends Object {
    }

    public static class LiteServerConfigConsensusConfigRegular extends LiteServerConfigConsensusConfig {
        public int roundCandidates;
        public int nextCandidateDelayMs;
        public int consensusTimeoutMs;
        public int fastAttempts;
        public int attemptDuration;
        public int catchainMaxDeps;
        public int maxBlockBytes;
        public int maxCollatedBytes;

        public LiteServerConfigConsensusConfigRegular() {
        }

        public LiteServerConfigConsensusConfigRegular(int roundCandidates, int nextCandidateDelayMs, int consensusTimeoutMs, int fastAttempts, int attemptDuration, int catchainMaxDeps, int maxBlockBytes, int maxCollatedBytes) {
            this.roundCandidates = roundCandidates;
            this.nextCandidateDelayMs = nextCandidateDelayMs;
            this.consensusTimeoutMs = consensusTimeoutMs;
            this.fastAttempts = fastAttempts;
            this.attemptDuration = attemptDuration;
            this.catchainMaxDeps = catchainMaxDeps;
            this.maxBlockBytes = maxBlockBytes;
            this.maxCollatedBytes = maxCollatedBytes;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = -892608804;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    public static class LiteServerConfigConsensusConfigNew extends LiteServerConfigConsensusConfig {
        public int flags;
        public boolean newCatchainIds;
        public int roundCandidates;
        public int nextCandidateDelayMs;
        public int consensusTimeoutMs;
        public int fastAttempts;
        public int attemptDuration;
        public int catchainMaxDeps;
        public int maxBlockBytes;
        public int maxCollatedBytes;

        public LiteServerConfigConsensusConfigNew() {
        }

        public LiteServerConfigConsensusConfigNew(int flags, boolean newCatchainIds, int roundCandidates, int nextCandidateDelayMs, int consensusTimeoutMs, int fastAttempts, int attemptDuration, int catchainMaxDeps, int maxBlockBytes, int maxCollatedBytes) {
            this.flags = flags;
            this.newCatchainIds = newCatchainIds;
            this.roundCandidates = roundCandidates;
            this.nextCandidateDelayMs = nextCandidateDelayMs;
            this.consensusTimeoutMs = consensusTimeoutMs;
            this.fastAttempts = fastAttempts;
            this.attemptDuration = attemptDuration;
            this.catchainMaxDeps = catchainMaxDeps;
            this.maxBlockBytes = maxBlockBytes;
            this.maxCollatedBytes = maxCollatedBytes;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = -1091186832;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    public static class LiteServerConfigFundamentalSmcAddresses extends Object {
        public LiteServerAccountId[] addresses;

        public LiteServerConfigFundamentalSmcAddresses() {
        }

        public LiteServerConfigFundamentalSmcAddresses(LiteServerAccountId[] addresses) {
            this.addresses = addresses;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = 698330230;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    public abstract static class LiteServerConfigGasLimitsPrices extends Object {
    }

    public static class LiteServerConfigGasPrices extends LiteServerConfigGasLimitsPrices {
        public long gasPrice;
        public long gasLimit;
        public long gasCredit;
        public long blockGasLimit;
        public long freezeDueLimit;
        public long deleteDueLimit;

        public LiteServerConfigGasPrices() {
        }

        public LiteServerConfigGasPrices(long gasPrice, long gasLimit, long gasCredit, long blockGasLimit, long freezeDueLimit, long deleteDueLimit) {
            this.gasPrice = gasPrice;
            this.gasLimit = gasLimit;
            this.gasCredit = gasCredit;
            this.blockGasLimit = blockGasLimit;
            this.freezeDueLimit = freezeDueLimit;
            this.deleteDueLimit = deleteDueLimit;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = -152003694;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    public static class LiteServerConfigGasPricesExt extends LiteServerConfigGasLimitsPrices {
        public long gasPrice;
        public long gasLimit;
        public long specialGasLimit;
        public long gasCredit;
        public long blockGasLimit;
        public long freezeDueLimit;
        public long deleteDueLimit;

        public LiteServerConfigGasPricesExt() {
        }

        public LiteServerConfigGasPricesExt(long gasPrice, long gasLimit, long specialGasLimit, long gasCredit, long blockGasLimit, long freezeDueLimit, long deleteDueLimit) {
            this.gasPrice = gasPrice;
            this.gasLimit = gasLimit;
            this.specialGasLimit = specialGasLimit;
            this.gasCredit = gasCredit;
            this.blockGasLimit = blockGasLimit;
            this.freezeDueLimit = freezeDueLimit;
            this.deleteDueLimit = deleteDueLimit;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = 844544283;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    public static class LiteServerConfigGasFlatPfx extends LiteServerConfigGasLimitsPrices {
        public long flatGasLimit;
        public long flatGasPrice;
        public LiteServerConfigGasLimitsPrices other;

        public LiteServerConfigGasFlatPfx() {
        }

        public LiteServerConfigGasFlatPfx(long flatGasLimit, long flatGasPrice, LiteServerConfigGasLimitsPrices other) {
            this.flatGasLimit = flatGasLimit;
            this.flatGasPrice = flatGasPrice;
            this.other = other;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = 1894954367;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    public static class LiteServerConfigInfo extends Object {
        public LiteServerAccountId configAddr;
        public LiteServerAccountId electorAddr;
        public LiteServerAccountId minterAddr;
        public LiteServerAccountId feeCollectorAddr;
        public LiteServerAccountId dnsRootAddr;
        public LiteServerConfigMintPrice mintPrice;
        public LiteServerConfigToMint toMint;
        public LiteServerGlobalVersion globalVersion;
        public LiteServerConfigParams mandatoryParams;
        public LiteServerConfigParams criticalParams;
        public LiteServerConfigVotingSetup configVotingSetup;
        public LiteServerConfigWorkchains workchains;
        public LiteServerConfigComplaintPricing complaintPricing;
        public LiteServerConfigBlockCreateFees blockCreateFees;
        public LiteServerConfigValidatorsTimings validatorsTimings;
        public LiteServerConfigValidatorsQuantityLimits validatorsQuantityLimits;
        public LiteServerConfigValidatorsStakeLimits validatorsStakeLimits;
        public LiteServerConfigStoragePrices storagePrices;
        public LiteServerConfigGasLimitsPrices masterchainGasPrices;
        public LiteServerConfigGasLimitsPrices basechainGasPrices;
        public LiteServerConfigBlockLimits masterchainBlockLimits;
        public LiteServerConfigBlockLimits basechainBlockLimits;
        public LiteServerConfigMsgForwardPrices masterchainMsgForwardPrices;
        public LiteServerConfigMsgForwardPrices basechainMsgForwardPrices;
        public LiteServerConfigCatchainConfig catchainConfig;
        public LiteServerConfigConsensusConfig consensusConfig;
        public LiteServerConfigFundamentalSmcAddresses fundamentalSmcAddresses;
        public LiteServerValidatorSet prevVset;
        public LiteServerValidatorSet prevTempVset;
        public LiteServerValidatorSet currVset;
        public LiteServerValidatorSet curTempVset;
        public LiteServerValidatorSet nextVset;
        public LiteServerValidatorSet nextTempVset;

        public LiteServerConfigInfo() {
        }

        public LiteServerConfigInfo(LiteServerAccountId configAddr, LiteServerAccountId electorAddr, LiteServerAccountId minterAddr, LiteServerAccountId feeCollectorAddr, LiteServerAccountId dnsRootAddr, LiteServerConfigMintPrice mintPrice, LiteServerConfigToMint toMint, LiteServerGlobalVersion globalVersion, LiteServerConfigParams mandatoryParams, LiteServerConfigParams criticalParams, LiteServerConfigVotingSetup configVotingSetup, LiteServerConfigWorkchains workchains, LiteServerConfigComplaintPricing complaintPricing, LiteServerConfigBlockCreateFees blockCreateFees, LiteServerConfigValidatorsTimings validatorsTimings, LiteServerConfigValidatorsQuantityLimits validatorsQuantityLimits, LiteServerConfigValidatorsStakeLimits validatorsStakeLimits, LiteServerConfigStoragePrices storagePrices, LiteServerConfigGasLimitsPrices masterchainGasPrices, LiteServerConfigGasLimitsPrices basechainGasPrices, LiteServerConfigBlockLimits masterchainBlockLimits, LiteServerConfigBlockLimits basechainBlockLimits, LiteServerConfigMsgForwardPrices masterchainMsgForwardPrices, LiteServerConfigMsgForwardPrices basechainMsgForwardPrices, LiteServerConfigCatchainConfig catchainConfig, LiteServerConfigConsensusConfig consensusConfig, LiteServerConfigFundamentalSmcAddresses fundamentalSmcAddresses, LiteServerValidatorSet prevVset, LiteServerValidatorSet prevTempVset, LiteServerValidatorSet currVset, LiteServerValidatorSet curTempVset, LiteServerValidatorSet nextVset, LiteServerValidatorSet nextTempVset) {
            this.configAddr = configAddr;
            this.electorAddr = electorAddr;
            this.minterAddr = minterAddr;
            this.feeCollectorAddr = feeCollectorAddr;
            this.dnsRootAddr = dnsRootAddr;
            this.mintPrice = mintPrice;
            this.toMint = toMint;
            this.globalVersion = globalVersion;
            this.mandatoryParams = mandatoryParams;
            this.criticalParams = criticalParams;
            this.configVotingSetup = configVotingSetup;
            this.workchains = workchains;
            this.complaintPricing = complaintPricing;
            this.blockCreateFees = blockCreateFees;
            this.validatorsTimings = validatorsTimings;
            this.validatorsQuantityLimits = validatorsQuantityLimits;
            this.validatorsStakeLimits = validatorsStakeLimits;
            this.storagePrices = storagePrices;
            this.masterchainGasPrices = masterchainGasPrices;
            this.basechainGasPrices = basechainGasPrices;
            this.masterchainBlockLimits = masterchainBlockLimits;
            this.basechainBlockLimits = basechainBlockLimits;
            this.masterchainMsgForwardPrices = masterchainMsgForwardPrices;
            this.basechainMsgForwardPrices = basechainMsgForwardPrices;
            this.catchainConfig = catchainConfig;
            this.consensusConfig = consensusConfig;
            this.fundamentalSmcAddresses = fundamentalSmcAddresses;
            this.prevVset = prevVset;
            this.prevTempVset = prevTempVset;
            this.currVset = currVset;
            this.curTempVset = curTempVset;
            this.nextVset = nextVset;
            this.nextTempVset = nextTempVset;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = 984194059;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    /**
     * 
     */
    public static class LiteServerConfigMintPrice extends Object {
        public byte[] mintNewPrice;
        public byte[] mintAddPrice;

        /**
         * 
         */
        public LiteServerConfigMintPrice() {
        }

        public LiteServerConfigMintPrice(byte[] mintNewPrice, byte[] mintAddPrice) {
            this.mintNewPrice = mintNewPrice;
            this.mintAddPrice = mintAddPrice;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = 495343278;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    public static class LiteServerConfigMsgForwardPrices extends Object {
        public long lumpPrice;
        public long bitPrice;
        public long cellPrice;
        public long ihrPriceFactor;
        public int firstFrac;
        public int nextFrac;

        public LiteServerConfigMsgForwardPrices() {
        }

        public LiteServerConfigMsgForwardPrices(long lumpPrice, long bitPrice, long cellPrice, long ihrPriceFactor, int firstFrac, int nextFrac) {
            this.lumpPrice = lumpPrice;
            this.bitPrice = bitPrice;
            this.cellPrice = cellPrice;
            this.ihrPriceFactor = ihrPriceFactor;
            this.firstFrac = firstFrac;
            this.nextFrac = nextFrac;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = -502479723;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    /**
     * 
     */
    public static class LiteServerConfigParamLimits extends Object {
        public int underload;
        public int softLimit;
        public int hardLimit;

        /**
         * 
         */
        public LiteServerConfigParamLimits() {
        }

        public LiteServerConfigParamLimits(int underload, int softLimit, int hardLimit) {
            this.underload = underload;
            this.softLimit = softLimit;
            this.hardLimit = hardLimit;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = 2061631375;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    /**
     * 
     */
    public static class LiteServerConfigParams extends Object {
        public int[] ids;

        /**
         * 
         */
        public LiteServerConfigParams() {
        }

        public LiteServerConfigParams(int[] ids) {
            this.ids = ids;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = -2060791245;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    /**
     * 
     */
    public static class LiteServerConfigProposalSetup extends Object {
        public int minTotRounds;
        public int maxTotRounds;
        public int minWins;
        public int maxLosses;
        public int minStoreSec;
        public int maxStoreSec;
        public int bitPrice;
        public int cellPrice;

        /**
         * 
         */
        public LiteServerConfigProposalSetup() {
        }

        public LiteServerConfigProposalSetup(int minTotRounds, int maxTotRounds, int minWins, int maxLosses, int minStoreSec, int maxStoreSec, int bitPrice, int cellPrice) {
            this.minTotRounds = minTotRounds;
            this.maxTotRounds = maxTotRounds;
            this.minWins = minWins;
            this.maxLosses = maxLosses;
            this.minStoreSec = minStoreSec;
            this.maxStoreSec = maxStoreSec;
            this.bitPrice = bitPrice;
            this.cellPrice = cellPrice;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = -583170520;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    /**
     * 
     */
    public static class LiteServerConfigStoragePrice extends Object {
        public int utimeSince;
        public long bitPricePs;
        public long cellPricePs;
        public long mcBitPricePs;
        public long mcCellPricePs;

        /**
         * 
         */
        public LiteServerConfigStoragePrice() {
        }

        public LiteServerConfigStoragePrice(int utimeSince, long bitPricePs, long cellPricePs, long mcBitPricePs, long mcCellPricePs) {
            this.utimeSince = utimeSince;
            this.bitPricePs = bitPricePs;
            this.cellPricePs = cellPricePs;
            this.mcBitPricePs = mcBitPricePs;
            this.mcCellPricePs = mcCellPricePs;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = -175087835;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    /**
     * 
     */
    public static class LiteServerConfigStoragePrices extends Object {
        public LiteServerConfigStoragePrice[] prices;

        /**
         * 
         */
        public LiteServerConfigStoragePrices() {
        }

        public LiteServerConfigStoragePrices(LiteServerConfigStoragePrice[] prices) {
            this.prices = prices;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = -657521602;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    /**
     * 
     */
    public static class LiteServerConfigToMint extends Object {
        public LiteServerCurrencyCollectionItem[] currencyCollection;

        /**
         * 
         */
        public LiteServerConfigToMint() {
        }

        public LiteServerConfigToMint(LiteServerCurrencyCollectionItem[] currencyCollection) {
            this.currencyCollection = currencyCollection;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = 778532670;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    /**
     * 
     */
    public static class LiteServerConfigValidatorsQuantityLimits extends Object {
        public int maxValidators;
        public int maxMainValidators;
        public int minValidators;

        /**
         * 
         */
        public LiteServerConfigValidatorsQuantityLimits() {
        }

        public LiteServerConfigValidatorsQuantityLimits(int maxValidators, int maxMainValidators, int minValidators) {
            this.maxValidators = maxValidators;
            this.maxMainValidators = maxMainValidators;
            this.minValidators = minValidators;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = 907095261;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    /**
     * 
     */
    public static class LiteServerConfigValidatorsStakeLimits extends Object {
        public byte[] minStake;
        public byte[] maxStake;
        public byte[] minTotalStake;
        public int maxStakeFactor;

        /**
         * 
         */
        public LiteServerConfigValidatorsStakeLimits() {
        }

        public LiteServerConfigValidatorsStakeLimits(byte[] minStake, byte[] maxStake, byte[] minTotalStake, int maxStakeFactor) {
            this.minStake = minStake;
            this.maxStake = maxStake;
            this.minTotalStake = minTotalStake;
            this.maxStakeFactor = maxStakeFactor;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = -1499275977;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    /**
     * 
     */
    public static class LiteServerConfigValidatorsTimings extends Object {
        public int validatorsElectedFor;
        public int electionsStartBefore;
        public int electionsEndBefore;
        public int stakeHeldFor;

        /**
         * 
         */
        public LiteServerConfigValidatorsTimings() {
        }

        public LiteServerConfigValidatorsTimings(int validatorsElectedFor, int electionsStartBefore, int electionsEndBefore, int stakeHeldFor) {
            this.validatorsElectedFor = validatorsElectedFor;
            this.electionsStartBefore = electionsStartBefore;
            this.electionsEndBefore = electionsEndBefore;
            this.stakeHeldFor = stakeHeldFor;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = -1723632552;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    /**
     * 
     */
    public static class LiteServerConfigVotingSetup extends Object {
        public LiteServerConfigProposalSetup normalParams;
        public LiteServerConfigProposalSetup criticalParams;

        /**
         * 
         */
        public LiteServerConfigVotingSetup() {
        }

        public LiteServerConfigVotingSetup(LiteServerConfigProposalSetup normalParams, LiteServerConfigProposalSetup criticalParams) {
            this.normalParams = normalParams;
            this.criticalParams = criticalParams;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = 1276024611;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    /**
     * 
     */
    public static class LiteServerConfigWorkchainFormat extends Object {
        public int vmVersion;
        public long vmMode;

        /**
         * 
         */
        public LiteServerConfigWorkchainFormat() {
        }

        public LiteServerConfigWorkchainFormat(int vmVersion, long vmMode) {
            this.vmVersion = vmVersion;
            this.vmMode = vmMode;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = 450603463;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    public static class LiteServerConfigWorkchainInfo extends Object {
        public int enabledSince;
        public int actualMinSplit;
        public int minSplit;
        public int maxSplit;
        public boolean basic;
        public boolean active;
        public boolean acceptMsgs;
        public int flags;
        public byte[] zerostateRootHash;
        public byte[] zerostateFileHash;
        public int version;
        public LiteServerConfigWorkchainFormat format;

        public LiteServerConfigWorkchainInfo() {
        }

        public LiteServerConfigWorkchainInfo(int enabledSince, int actualMinSplit, int minSplit, int maxSplit, boolean basic, boolean active, boolean acceptMsgs, int flags, byte[] zerostateRootHash, byte[] zerostateFileHash, int version, LiteServerConfigWorkchainFormat format) {
            this.enabledSince = enabledSince;
            this.actualMinSplit = actualMinSplit;
            this.minSplit = minSplit;
            this.maxSplit = maxSplit;
            this.basic = basic;
            this.active = active;
            this.acceptMsgs = acceptMsgs;
            this.flags = flags;
            this.zerostateRootHash = zerostateRootHash;
            this.zerostateFileHash = zerostateFileHash;
            this.version = version;
            this.format = format;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = 1357810245;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    /**
     * 
     */
    public static class LiteServerConfigWorkchains extends Object {
        public LiteServerConfigWorkchainInfo[] workchains;

        /**
         * 
         */
        public LiteServerConfigWorkchains() {
        }

        public LiteServerConfigWorkchains(LiteServerConfigWorkchainInfo[] workchains) {
            this.workchains = workchains;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = -1771137520;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    /**
     * 
     */
    public static class LiteServerCurrencyCollection extends Object {
        public byte[] grams;
        public LiteServerCurrencyCollectionItem[] other;

        /**
         * 
         */
        public LiteServerCurrencyCollection() {
        }

        public LiteServerCurrencyCollection(byte[] grams, LiteServerCurrencyCollectionItem[] other) {
            this.grams = grams;
            this.other = other;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = 1502552548;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    /**
     * 
     */
    public static class LiteServerCurrencyCollectionItem extends Object {
        public int currency;
        public byte[] value;

        /**
         * 
         */
        public LiteServerCurrencyCollectionItem() {
        }

        public LiteServerCurrencyCollectionItem(int currency, byte[] value) {
            this.currency = currency;
            this.value = value;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = -1825120109;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    /**
     * 
     */
    public static class LiteServerCurrentTime extends Object {
        public int now;

        /**
         * 
         */
        public LiteServerCurrentTime() {
        }

        public LiteServerCurrentTime(int now) {
            this.now = now;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = -1927982839;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    /**
     * 
     */
    public static class LiteServerExtBlockRef extends Object {
        public long endLt;
        public int seqno;
        public byte[] rootHash;
        public byte[] fileHash;

        /**
         * 
         */
        public LiteServerExtBlockRef() {
        }

        public LiteServerExtBlockRef(long endLt, int seqno, byte[] rootHash, byte[] fileHash) {
            this.endLt = endLt;
            this.seqno = seqno;
            this.rootHash = rootHash;
            this.fileHash = fileHash;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = -868925511;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    /**
     * 
     */
    public static class LiteServerGlobalVersion extends Object {
        public int version;
        public long capabilities;

        /**
         * 
         */
        public LiteServerGlobalVersion() {
        }

        public LiteServerGlobalVersion(int version, long capabilities) {
            this.version = version;
            this.capabilities = capabilities;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = -1307061908;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    /**
     * 
     */
    public static class LiteServerHashUpdate extends Object {
        public byte[] oldHash;
        public byte[] newHash;

        /**
         * 
         */
        public LiteServerHashUpdate() {
        }

        public LiteServerHashUpdate(byte[] oldHash, byte[] newHash) {
            this.oldHash = oldHash;
            this.newHash = newHash;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = -1080485507;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    public static class LiteServerInMsgDescrItem extends Object {
        public byte[] id;
        public byte[] feesCollected;
        public LiteServerCurrencyCollection valueImported;

        public LiteServerInMsgDescrItem() {
        }

        public LiteServerInMsgDescrItem(byte[] id, byte[] feesCollected, LiteServerCurrencyCollection valueImported) {
            this.id = id;
            this.feesCollected = feesCollected;
            this.valueImported = valueImported;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = 76505594;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    /**
     * 
     */
    public static class LiteServerInfo extends Object {
        public long now;
        public int version;
        public long capabilities;

        /**
         * 
         */
        public LiteServerInfo() {
        }

        public LiteServerInfo(long now, int version, long capabilities) {
            this.now = now;
            this.version = version;
            this.capabilities = capabilities;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = -1250165133;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    /**
     * 
     */
    public static class LiteServerMasterchainInfo extends Object {
        public TonBlockIdExt last;
        public byte[] stateRootHash;
        public TonZeroStateIdExt init;

        /**
         * 
         */
        public LiteServerMasterchainInfo() {
        }

        public LiteServerMasterchainInfo(TonBlockIdExt last, byte[] stateRootHash, TonZeroStateIdExt init) {
            this.last = last;
            this.stateRootHash = stateRootHash;
            this.init = init;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = 1212773193;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    public static class LiteServerMcBlockExtra extends Object {
        public boolean keyBlock;
        public LiteServerAllShardsInfo shardHashes;
        public LiteServerConfigInfo config;

        public LiteServerMcBlockExtra() {
        }

        public LiteServerMcBlockExtra(boolean keyBlock, LiteServerAllShardsInfo shardHashes, LiteServerConfigInfo config) {
            this.keyBlock = keyBlock;
            this.shardHashes = shardHashes;
            this.config = config;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = -1162272657;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    /**
     * 
     */
    public static class LiteServerMessage extends Object {
        public byte[] hash;
        public LiteServerMessageInfo info;
        public byte[] init;
        public byte[] body;

        /**
         * 
         */
        public LiteServerMessage() {
        }

        public LiteServerMessage(byte[] hash, LiteServerMessageInfo info, byte[] init, byte[] body) {
            this.hash = hash;
            this.info = info;
            this.init = init;
            this.body = body;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = -2140704406;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    public abstract static class LiteServerMessageAddressExt extends Object {
    }

    public static class LiteServerMessageAddressExtNone extends LiteServerMessageAddressExt {

        /**
         * 
         */
        public LiteServerMessageAddressExtNone() {
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = 1775319726;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    public static class LiteServerMessageAddressExtSome extends LiteServerMessageAddressExt {
        public int len;
        public byte[] externalAddress;

        /**
         * 
         */
        public LiteServerMessageAddressExtSome() {
        }

        public LiteServerMessageAddressExtSome(int len, byte[] externalAddress) {
            this.len = len;
            this.externalAddress = externalAddress;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = 611575758;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    public abstract static class LiteServerMessageAddressInt extends Object {
    }

    public static class LiteServerMessageAddressIntStd extends LiteServerMessageAddressInt {
        public int workchain;
        public byte[] address;

        /**
         * 
         */
        public LiteServerMessageAddressIntStd() {
        }

        public LiteServerMessageAddressIntStd(int workchain, byte[] address) {
            this.workchain = workchain;
            this.address = address;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = 384933519;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    public static class LiteServerMessageAddressIntStdAnycast extends LiteServerMessageAddressInt {
        public LiteServerMessageAnycast anycast;
        public int workchain;
        public byte[] address;

        /**
         * 
         */
        public LiteServerMessageAddressIntStdAnycast() {
        }

        public LiteServerMessageAddressIntStdAnycast(LiteServerMessageAnycast anycast, int workchain, byte[] address) {
            this.anycast = anycast;
            this.workchain = workchain;
            this.address = address;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = -778188336;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    public static class LiteServerMessageAddressIntVar extends LiteServerMessageAddressInt {
        public int workchain;
        public int addrLen;
        public byte[] address;

        /**
         * 
         */
        public LiteServerMessageAddressIntVar() {
        }

        public LiteServerMessageAddressIntVar(int workchain, int addrLen, byte[] address) {
            this.workchain = workchain;
            this.addrLen = addrLen;
            this.address = address;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = 105611358;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    public static class LiteServerMessageAddressIntVarAnycast extends LiteServerMessageAddressInt {
        public LiteServerMessageAnycast anycast;
        public int workchain;
        public int addrLen;
        public byte[] address;

        /**
         * 
         */
        public LiteServerMessageAddressIntVarAnycast() {
        }

        public LiteServerMessageAddressIntVarAnycast(LiteServerMessageAnycast anycast, int workchain, int addrLen, byte[] address) {
            this.anycast = anycast;
            this.workchain = workchain;
            this.addrLen = addrLen;
            this.address = address;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = 921519484;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    /**
     * 
     */
    public static class LiteServerMessageAnycast extends Object {
        public int depth;
        public byte[] rewritePfx;

        /**
         * 
         */
        public LiteServerMessageAnycast() {
        }

        public LiteServerMessageAnycast(int depth, byte[] rewritePfx) {
            this.depth = depth;
            this.rewritePfx = rewritePfx;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = 476484290;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    public abstract static class LiteServerMessageInfo extends Object {
    }

    public static class LiteServerMessageInfoInt extends LiteServerMessageInfo {
        public boolean ihrDisabled;
        public boolean bounce;
        public boolean bounced;
        public LiteServerMessageAddressInt src;
        public LiteServerMessageAddressInt dest;
        public byte[] value;
        public byte[] ihrFee;
        public byte[] fwdFee;
        public long createdLt;
        public int createdAt;

        /**
         * 
         */
        public LiteServerMessageInfoInt() {
        }

        public LiteServerMessageInfoInt(boolean ihrDisabled, boolean bounce, boolean bounced, LiteServerMessageAddressInt src, LiteServerMessageAddressInt dest, byte[] value, byte[] ihrFee, byte[] fwdFee, long createdLt, int createdAt) {
            this.ihrDisabled = ihrDisabled;
            this.bounce = bounce;
            this.bounced = bounced;
            this.src = src;
            this.dest = dest;
            this.value = value;
            this.ihrFee = ihrFee;
            this.fwdFee = fwdFee;
            this.createdLt = createdLt;
            this.createdAt = createdAt;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = -1458922202;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    public static class LiteServerMessageInfoExtIn extends LiteServerMessageInfo {
        public LiteServerMessageAddressExt src;
        public LiteServerMessageAddressInt dest;
        public byte[] importFee;

        /**
         * 
         */
        public LiteServerMessageInfoExtIn() {
        }

        public LiteServerMessageInfoExtIn(LiteServerMessageAddressExt src, LiteServerMessageAddressInt dest, byte[] importFee) {
            this.src = src;
            this.dest = dest;
            this.importFee = importFee;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = -1368802839;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    public static class LiteServerMessageInfoExtOut extends LiteServerMessageInfo {
        public LiteServerMessageAddressInt src;
        public LiteServerMessageAddressExt dest;
        public long createdLt;
        public int createdAt;

        /**
         * 
         */
        public LiteServerMessageInfoExtOut() {
        }

        public LiteServerMessageInfoExtOut(LiteServerMessageAddressInt src, LiteServerMessageAddressExt dest, long createdLt, int createdAt) {
            this.src = src;
            this.dest = dest;
            this.createdLt = createdLt;
            this.createdAt = createdAt;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = -1768661409;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    public static class LiteServerOutMsgDescrItem extends Object {
        public byte[] id;
        public LiteServerCurrencyCollection value;

        public LiteServerOutMsgDescrItem() {
        }

        public LiteServerOutMsgDescrItem(byte[] id, LiteServerCurrencyCollection value) {
            this.id = id;
            this.value = value;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = 1134730727;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    /**
     * 
     */
    public static class LiteServerPartialBlockProof extends Object {
        public boolean complete;
        public TonBlockIdExt from;
        public TonBlockIdExt to;
        public LiteServerBlockLink[] steps;

        /**
         * 
         */
        public LiteServerPartialBlockProof() {
        }

        public LiteServerPartialBlockProof(boolean complete, TonBlockIdExt from, TonBlockIdExt to, LiteServerBlockLink[] steps) {
            this.complete = complete;
            this.from = from;
            this.to = to;
            this.steps = steps;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = 1208677632;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    public static class LiteServerPastElections extends Object {
        public LiteServerPastElectionsItem[] elections;

        public LiteServerPastElections() {
        }

        public LiteServerPastElections(LiteServerPastElectionsItem[] elections) {
            this.elections = elections;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = -1755276243;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    public static class LiteServerPastElectionsItem extends Object {
        public int electionId;
        public int unfreezeAt;
        public int stakeHeldFor;
        public byte[] vsetHash;
        public byte[] totalStake;
        public byte[] totalBonuses;

        public LiteServerPastElectionsItem() {
        }

        public LiteServerPastElectionsItem(int electionId, int unfreezeAt, int stakeHeldFor, byte[] vsetHash, byte[] totalStake, byte[] totalBonuses) {
            this.electionId = electionId;
            this.unfreezeAt = unfreezeAt;
            this.stakeHeldFor = stakeHeldFor;
            this.vsetHash = vsetHash;
            this.totalStake = totalStake;
            this.totalBonuses = totalBonuses;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = -2029978281;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    public static class LiteServerShardFeesItem extends Object {
        public LiteServerCurrencyCollection fees;
        public LiteServerCurrencyCollection create;

        public LiteServerShardFeesItem() {
        }

        public LiteServerShardFeesItem(LiteServerCurrencyCollection fees, LiteServerCurrencyCollection create) {
            this.fees = fees;
            this.create = create;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = -667853261;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    /**
     * 
     */
    public static class LiteServerShardHash extends Object {
        public int workchain;
        public long id;
        public TonBlockIdExt topBlockId;
        public long startLt;
        public long endLt;
        public boolean beforeSplit;
        public boolean beforeMerge;
        public boolean wantSplit;
        public boolean wantMerge;
        public int nextCatchainSeqno;
        public long nextValidatorShard;
        public int minRefMcSeqno;
        public int genUtime;
        public byte[] feesCollected;
        public byte[] fundsCollected;

        /**
         * 
         */
        public LiteServerShardHash() {
        }

        public LiteServerShardHash(int workchain, long id, TonBlockIdExt topBlockId, long startLt, long endLt, boolean beforeSplit, boolean beforeMerge, boolean wantSplit, boolean wantMerge, int nextCatchainSeqno, long nextValidatorShard, int minRefMcSeqno, int genUtime, byte[] feesCollected, byte[] fundsCollected) {
            this.workchain = workchain;
            this.id = id;
            this.topBlockId = topBlockId;
            this.startLt = startLt;
            this.endLt = endLt;
            this.beforeSplit = beforeSplit;
            this.beforeMerge = beforeMerge;
            this.wantSplit = wantSplit;
            this.wantMerge = wantMerge;
            this.nextCatchainSeqno = nextCatchainSeqno;
            this.nextValidatorShard = nextValidatorShard;
            this.minRefMcSeqno = minRefMcSeqno;
            this.genUtime = genUtime;
            this.feesCollected = feesCollected;
            this.fundsCollected = fundsCollected;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = 297431349;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    /**
     * 
     */
    public static class LiteServerSignature extends Object {
        public byte[] nodeIdShort;
        public byte[] signature;

        /**
         * 
         */
        public LiteServerSignature() {
        }

        public LiteServerSignature(byte[] nodeIdShort, byte[] signature) {
            this.nodeIdShort = nodeIdShort;
            this.signature = signature;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = 2035140928;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    /**
     * 
     */
    public static class LiteServerSignatureSet extends Object {
        public int validatorSetHash;
        public int catchainSeqno;
        public LiteServerSignature[] signatures;

        /**
         * 
         */
        public LiteServerSignatureSet() {
        }

        public LiteServerSignatureSet(int validatorSetHash, int catchainSeqno, LiteServerSignature[] signatures) {
            this.validatorSetHash = validatorSetHash;
            this.catchainSeqno = catchainSeqno;
            this.signatures = signatures;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = 1509509708;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    /**
     * 
     */
    public static class LiteServerSimpleLib extends Object {
        public byte[] key;
        public boolean isPublic;
        public byte[] root;

        /**
         * 
         */
        public LiteServerSimpleLib() {
        }

        public LiteServerSimpleLib(byte[] key, boolean isPublic, byte[] root) {
            this.key = key;
            this.isPublic = isPublic;
            this.root = root;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = 1033062893;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    public static class LiteServerStorageInfo extends Object {
        public LiteServerStorageUsed storageUsed;
        public int lastPaid;
        public boolean hasDuePayment;
        public byte[] duePayment;

        public LiteServerStorageInfo() {
        }

        public LiteServerStorageInfo(LiteServerStorageUsed storageUsed, int lastPaid, boolean hasDuePayment, byte[] duePayment) {
            this.storageUsed = storageUsed;
            this.lastPaid = lastPaid;
            this.hasDuePayment = hasDuePayment;
            this.duePayment = duePayment;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = 632806625;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    public static class LiteServerStorageUsed extends Object {
        public byte[] cells;
        public byte[] bits;
        public byte[] publicCells;

        public LiteServerStorageUsed() {
        }

        public LiteServerStorageUsed(byte[] cells, byte[] bits, byte[] publicCells) {
            this.cells = cells;
            this.bits = bits;
            this.publicCells = publicCells;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = 1515115446;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    /**
     * 
     */
    public static class LiteServerStorageUsedShort extends Object {
        public byte[] cells;
        public byte[] bits;

        /**
         * 
         */
        public LiteServerStorageUsedShort() {
        }

        public LiteServerStorageUsedShort(byte[] cells, byte[] bits) {
            this.cells = cells;
            this.bits = bits;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = 1147594857;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    /**
     * 
     */
    public static class LiteServerTickTock extends Object {
        public boolean tick;
        public boolean tock;

        /**
         * 
         */
        public LiteServerTickTock() {
        }

        public LiteServerTickTock(boolean tick, boolean tock) {
            this.tick = tick;
            this.tock = tock;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = -1117275130;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    public static class LiteServerTransaction extends Object {
        public int workchain;
        public byte[] account;
        public byte[] hash;
        public long lt;
        public byte[] prevTransHash;
        public long prevTransLt;
        public int now;
        public int outmsgCnt;
        public int origStatus;
        public int endStatus;
        public LiteServerMessage inMsg;
        public LiteServerMessage[] outMsgs;
        public byte[] totalFees;
        public LiteServerHashUpdate hashUpdate;
        public LiteServerTransactionDescr desc;

        public LiteServerTransaction() {
        }

        public LiteServerTransaction(int workchain, byte[] account, byte[] hash, long lt, byte[] prevTransHash, long prevTransLt, int now, int outmsgCnt, int origStatus, int endStatus, LiteServerMessage inMsg, LiteServerMessage[] outMsgs, byte[] totalFees, LiteServerHashUpdate hashUpdate, LiteServerTransactionDescr desc) {
            this.workchain = workchain;
            this.account = account;
            this.hash = hash;
            this.lt = lt;
            this.prevTransHash = prevTransHash;
            this.prevTransLt = prevTransLt;
            this.now = now;
            this.outmsgCnt = outmsgCnt;
            this.origStatus = origStatus;
            this.endStatus = endStatus;
            this.inMsg = inMsg;
            this.outMsgs = outMsgs;
            this.totalFees = totalFees;
            this.hashUpdate = hashUpdate;
            this.desc = desc;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = -552014952;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    public static class LiteServerTransactionActionPhase extends Object {
        public boolean success;
        public boolean valid;
        public boolean noFunds;
        public int statusChange;
        public boolean hasTotalFwdFees;
        public byte[] totalFwdFees;
        public boolean hasTotalActionFees;
        public byte[] totalActionFees;
        public int resultCode;
        public boolean hasResultArg;
        public int resultArg;
        public int totActions;
        public int specActions;
        public int skippedActions;
        public int msgsCreated;
        public byte[] actionListHash;
        public LiteServerStorageUsedShort totMsgSize;

        public LiteServerTransactionActionPhase() {
        }

        public LiteServerTransactionActionPhase(boolean success, boolean valid, boolean noFunds, int statusChange, boolean hasTotalFwdFees, byte[] totalFwdFees, boolean hasTotalActionFees, byte[] totalActionFees, int resultCode, boolean hasResultArg, int resultArg, int totActions, int specActions, int skippedActions, int msgsCreated, byte[] actionListHash, LiteServerStorageUsedShort totMsgSize) {
            this.success = success;
            this.valid = valid;
            this.noFunds = noFunds;
            this.statusChange = statusChange;
            this.hasTotalFwdFees = hasTotalFwdFees;
            this.totalFwdFees = totalFwdFees;
            this.hasTotalActionFees = hasTotalActionFees;
            this.totalActionFees = totalActionFees;
            this.resultCode = resultCode;
            this.hasResultArg = hasResultArg;
            this.resultArg = resultArg;
            this.totActions = totActions;
            this.specActions = specActions;
            this.skippedActions = skippedActions;
            this.msgsCreated = msgsCreated;
            this.actionListHash = actionListHash;
            this.totMsgSize = totMsgSize;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = -1527491167;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    public abstract static class LiteServerTransactionAdditionalInfo extends Object {
    }

    public static class LiteServerTransactionAdditionalInfoStakeSend extends LiteServerTransactionAdditionalInfo {
        public int status;
        public byte[] pubkey;
        public int electTime;
        public int maxFactor;
        public byte[] adnlAddr;

        /**
         * 
         */
        public LiteServerTransactionAdditionalInfoStakeSend() {
        }

        public LiteServerTransactionAdditionalInfoStakeSend(int status, byte[] pubkey, int electTime, int maxFactor, byte[] adnlAddr) {
            this.status = status;
            this.pubkey = pubkey;
            this.electTime = electTime;
            this.maxFactor = maxFactor;
            this.adnlAddr = adnlAddr;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = -1826436960;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    public static class LiteServerTransactionAdditionalInfoStakeRecover extends LiteServerTransactionAdditionalInfo {
        public boolean success;

        /**
         * 
         */
        public LiteServerTransactionAdditionalInfoStakeRecover() {
        }

        public LiteServerTransactionAdditionalInfoStakeRecover(boolean success) {
            this.success = success;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = 803664671;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    public abstract static class LiteServerTransactionBouncePhase extends Object {
    }

    public static class LiteServerTransactionBouncePhaseNegFunds extends LiteServerTransactionBouncePhase {

        /**
         * 
         */
        public LiteServerTransactionBouncePhaseNegFunds() {
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = 943631670;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    public static class LiteServerTransactionBouncePhaseNoFunds extends LiteServerTransactionBouncePhase {
        public LiteServerStorageUsedShort msgSize;
        public byte[] reqFwdFees;

        public LiteServerTransactionBouncePhaseNoFunds() {
        }

        public LiteServerTransactionBouncePhaseNoFunds(LiteServerStorageUsedShort msgSize, byte[] reqFwdFees) {
            this.msgSize = msgSize;
            this.reqFwdFees = reqFwdFees;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = 1618732767;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    public static class LiteServerTransactionBouncePhaseOk extends LiteServerTransactionBouncePhase {
        public LiteServerStorageUsedShort msgSize;
        public byte[] msgFees;
        public byte[] fwdFees;

        public LiteServerTransactionBouncePhaseOk() {
        }

        public LiteServerTransactionBouncePhaseOk(LiteServerStorageUsedShort msgSize, byte[] msgFees, byte[] fwdFees) {
            this.msgSize = msgSize;
            this.msgFees = msgFees;
            this.fwdFees = fwdFees;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = 1662374791;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    public abstract static class LiteServerTransactionComputePhase extends Object {
    }

    public static class LiteServerTransactionComputePhaseSkipped extends LiteServerTransactionComputePhase {
        public int reason;

        /**
         * 
         */
        public LiteServerTransactionComputePhaseSkipped() {
        }

        public LiteServerTransactionComputePhaseSkipped(int reason) {
            this.reason = reason;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = -2040622487;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    public static class LiteServerTransactionComputePhaseVm extends LiteServerTransactionComputePhase {
        public boolean success;
        public boolean msgStateUsed;
        public boolean accountActivated;
        public byte[] gasFees;
        public byte[] gasUsed;
        public byte[] gasLimit;
        public boolean hasGasCredit;
        public byte[] gasCredit;
        public int mode;
        public int exitCode;
        public boolean hasExitArg;
        public int exitArg;
        public int vmSteps;
        public byte[] vmInitStateHash;
        public byte[] vmFinalStateHash;

        public LiteServerTransactionComputePhaseVm() {
        }

        public LiteServerTransactionComputePhaseVm(boolean success, boolean msgStateUsed, boolean accountActivated, byte[] gasFees, byte[] gasUsed, byte[] gasLimit, boolean hasGasCredit, byte[] gasCredit, int mode, int exitCode, boolean hasExitArg, int exitArg, int vmSteps, byte[] vmInitStateHash, byte[] vmFinalStateHash) {
            this.success = success;
            this.msgStateUsed = msgStateUsed;
            this.accountActivated = accountActivated;
            this.gasFees = gasFees;
            this.gasUsed = gasUsed;
            this.gasLimit = gasLimit;
            this.hasGasCredit = hasGasCredit;
            this.gasCredit = gasCredit;
            this.mode = mode;
            this.exitCode = exitCode;
            this.hasExitArg = hasExitArg;
            this.exitArg = exitArg;
            this.vmSteps = vmSteps;
            this.vmInitStateHash = vmInitStateHash;
            this.vmFinalStateHash = vmFinalStateHash;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = 876322120;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    public static class LiteServerTransactionCreditPhase extends Object {
        public boolean hasDueFeesCollected;
        public byte[] dueFeesCollected;
        public LiteServerCurrencyCollection credit;

        public LiteServerTransactionCreditPhase() {
        }

        public LiteServerTransactionCreditPhase(boolean hasDueFeesCollected, byte[] dueFeesCollected, LiteServerCurrencyCollection credit) {
            this.hasDueFeesCollected = hasDueFeesCollected;
            this.dueFeesCollected = dueFeesCollected;
            this.credit = credit;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = -2111145434;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    public abstract static class LiteServerTransactionDescr extends Object {
    }

    public static class LiteServerTransactionDescrOrdinary extends LiteServerTransactionDescr {
        public boolean creditFirst;
        public LiteServerTransactionStoragePhase storagePh;
        public LiteServerTransactionCreditPhase creditPh;
        public LiteServerTransactionComputePhase computePh;
        public LiteServerTransactionActionPhase action;
        public boolean aborted;
        public LiteServerTransactionBouncePhase bounce;
        public boolean destroyed;
        public LiteServerTransactionAdditionalInfo additional;

        public LiteServerTransactionDescrOrdinary() {
        }

        public LiteServerTransactionDescrOrdinary(boolean creditFirst, LiteServerTransactionStoragePhase storagePh, LiteServerTransactionCreditPhase creditPh, LiteServerTransactionComputePhase computePh, LiteServerTransactionActionPhase action, boolean aborted, LiteServerTransactionBouncePhase bounce, boolean destroyed, LiteServerTransactionAdditionalInfo additional) {
            this.creditFirst = creditFirst;
            this.storagePh = storagePh;
            this.creditPh = creditPh;
            this.computePh = computePh;
            this.action = action;
            this.aborted = aborted;
            this.bounce = bounce;
            this.destroyed = destroyed;
            this.additional = additional;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = -1678824929;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    public static class LiteServerTransactionDescrStorage extends LiteServerTransactionDescr {
        public LiteServerTransactionStoragePhase storagePh;

        public LiteServerTransactionDescrStorage() {
        }

        public LiteServerTransactionDescrStorage(LiteServerTransactionStoragePhase storagePh) {
            this.storagePh = storagePh;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = 2127002360;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    public static class LiteServerTransactionDescrTickTock extends LiteServerTransactionDescr {
        public boolean isTock;
        public LiteServerTransactionStoragePhase storagePh;
        public LiteServerTransactionComputePhase computePh;
        public LiteServerTransactionActionPhase action;
        public boolean aborted;
        public boolean destroyed;

        public LiteServerTransactionDescrTickTock() {
        }

        public LiteServerTransactionDescrTickTock(boolean isTock, LiteServerTransactionStoragePhase storagePh, LiteServerTransactionComputePhase computePh, LiteServerTransactionActionPhase action, boolean aborted, boolean destroyed) {
            this.isTock = isTock;
            this.storagePh = storagePh;
            this.computePh = computePh;
            this.action = action;
            this.aborted = aborted;
            this.destroyed = destroyed;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = -858342831;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    public static class LiteServerTransactionDescrSplitPrepare extends LiteServerTransactionDescr {
        public LiteServerTransactionStoragePhase storagePh;
        public LiteServerTransactionComputePhase computePh;
        public LiteServerTransactionActionPhase action;
        public boolean aborted;
        public boolean destroyed;

        public LiteServerTransactionDescrSplitPrepare() {
        }

        public LiteServerTransactionDescrSplitPrepare(LiteServerTransactionStoragePhase storagePh, LiteServerTransactionComputePhase computePh, LiteServerTransactionActionPhase action, boolean aborted, boolean destroyed) {
            this.storagePh = storagePh;
            this.computePh = computePh;
            this.action = action;
            this.aborted = aborted;
            this.destroyed = destroyed;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = 2069917366;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    public static class LiteServerTransactionDescrSplitInstall extends LiteServerTransactionDescr {
        public boolean installed;

        public LiteServerTransactionDescrSplitInstall() {
        }

        public LiteServerTransactionDescrSplitInstall(boolean installed) {
            this.installed = installed;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = -401908743;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    public static class LiteServerTransactionDescrMergePrepare extends LiteServerTransactionDescr {
        public LiteServerTransactionStoragePhase storagePh;
        public boolean aborted;

        public LiteServerTransactionDescrMergePrepare() {
        }

        public LiteServerTransactionDescrMergePrepare(LiteServerTransactionStoragePhase storagePh, boolean aborted) {
            this.storagePh = storagePh;
            this.aborted = aborted;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = 632204890;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    public static class LiteServerTransactionDescrMergeInstall extends LiteServerTransactionDescr {
        public LiteServerTransactionStoragePhase storagePh;
        public LiteServerTransactionCreditPhase creditPh;
        public LiteServerTransactionComputePhase computePh;
        public LiteServerTransactionActionPhase action;
        public boolean aborted;
        public boolean destroyed;

        public LiteServerTransactionDescrMergeInstall() {
        }

        public LiteServerTransactionDescrMergeInstall(LiteServerTransactionStoragePhase storagePh, LiteServerTransactionCreditPhase creditPh, LiteServerTransactionComputePhase computePh, LiteServerTransactionActionPhase action, boolean aborted, boolean destroyed) {
            this.storagePh = storagePh;
            this.creditPh = creditPh;
            this.computePh = computePh;
            this.action = action;
            this.aborted = aborted;
            this.destroyed = destroyed;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = -125101539;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    /**
     * 
     */
    public static class LiteServerTransactionId extends Object {
        public int mode;
        public byte[] account;
        public long lt;
        public byte[] hash;

        /**
         * 
         */
        public LiteServerTransactionId() {
        }

        public LiteServerTransactionId(int mode, byte[] account, long lt, byte[] hash) {
            this.mode = mode;
            this.account = account;
            this.lt = lt;
            this.hash = hash;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = 784494759;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    /**
     * 
     */
    public static class LiteServerTransactionId3 extends Object {
        public byte[] account;
        public long lt;

        /**
         * 
         */
        public LiteServerTransactionId3() {
        }

        public LiteServerTransactionId3(byte[] account, long lt) {
            this.account = account;
            this.lt = lt;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = 595305392;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    /**
     * 
     */
    public static class LiteServerTransactionList extends Object {
        public TonBlockIdExt[] ids;
        public LiteServerTransaction[] transactions;

        /**
         * 
         */
        public LiteServerTransactionList() {
        }

        public LiteServerTransactionList(TonBlockIdExt[] ids, LiteServerTransaction[] transactions) {
            this.ids = ids;
            this.transactions = transactions;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = -1817735918;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    public static class LiteServerTransactionStoragePhase extends Object {
        public byte[] storageFeesCollected;
        public boolean hasStorageFeesDue;
        public byte[] storageFeesDue;
        public int statusChange;

        public LiteServerTransactionStoragePhase() {
        }

        public LiteServerTransactionStoragePhase(byte[] storageFeesCollected, boolean hasStorageFeesDue, byte[] storageFeesDue, int statusChange) {
            this.storageFeesCollected = storageFeesCollected;
            this.hasStorageFeesDue = hasStorageFeesDue;
            this.storageFeesDue = storageFeesDue;
            this.statusChange = statusChange;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = 1551666353;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    /**
     * 
     */
    public static class LiteServerValidator extends Object {
        public byte[] pubkey;
        public byte[] adnlAddr;
        public long weight;
        public long cumWeight;

        /**
         * 
         */
        public LiteServerValidator() {
        }

        public LiteServerValidator(byte[] pubkey, byte[] adnlAddr, long weight, long cumWeight) {
            this.pubkey = pubkey;
            this.adnlAddr = adnlAddr;
            this.weight = weight;
            this.cumWeight = cumWeight;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = -2019586036;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    public static class LiteServerValidatorSet extends Object {
        public int utimeSince;
        public int utimeUntil;
        public int total;
        public int main;
        public long totalWeight;
        public LiteServerValidator[] list;

        public LiteServerValidatorSet() {
        }

        public LiteServerValidatorSet(int utimeSince, int utimeUntil, int total, int main, long totalWeight, LiteServerValidator[] list) {
            this.utimeSince = utimeSince;
            this.utimeUntil = utimeUntil;
            this.total = total;
            this.main = main;
            this.totalWeight = totalWeight;
            this.list = list;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = -248595103;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    public static class LiteServerValueFlow extends Object {
        public LiteServerCurrencyCollection fromPrevBlk;
        public LiteServerCurrencyCollection toNextBlk;
        public LiteServerCurrencyCollection imported;
        public LiteServerCurrencyCollection exported;
        public LiteServerCurrencyCollection feesCollected;
        public LiteServerCurrencyCollection feesImported;
        public LiteServerCurrencyCollection recovered;
        public LiteServerCurrencyCollection created;
        public LiteServerCurrencyCollection minted;

        public LiteServerValueFlow() {
        }

        public LiteServerValueFlow(LiteServerCurrencyCollection fromPrevBlk, LiteServerCurrencyCollection toNextBlk, LiteServerCurrencyCollection imported, LiteServerCurrencyCollection exported, LiteServerCurrencyCollection feesCollected, LiteServerCurrencyCollection feesImported, LiteServerCurrencyCollection recovered, LiteServerCurrencyCollection created, LiteServerCurrencyCollection minted) {
            this.fromPrevBlk = fromPrevBlk;
            this.toNextBlk = toNextBlk;
            this.imported = imported;
            this.exported = exported;
            this.feesCollected = feesCollected;
            this.feesImported = feesImported;
            this.recovered = recovered;
            this.created = created;
            this.minted = minted;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = 861833926;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    public abstract static class MsgData extends Object {
    }

    public static class MsgDataRaw extends MsgData {
        public byte[] body;
        public byte[] initState;

        /**
         * 
         */
        public MsgDataRaw() {
        }

        public MsgDataRaw(byte[] body, byte[] initState) {
            this.body = body;
            this.initState = initState;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = -1928962698;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    public static class MsgDataText extends MsgData {
        public byte[] text;

        /**
         * 
         */
        public MsgDataText() {
        }

        public MsgDataText(byte[] text) {
            this.text = text;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = -341560688;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    public static class MsgDataDecryptedText extends MsgData {
        public byte[] text;

        /**
         * 
         */
        public MsgDataDecryptedText() {
        }

        public MsgDataDecryptedText(byte[] text) {
            this.text = text;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = -1289133895;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    public static class MsgDataEncryptedText extends MsgData {
        public byte[] text;

        /**
         * 
         */
        public MsgDataEncryptedText() {
        }

        public MsgDataEncryptedText(byte[] text) {
            this.text = text;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = -296612902;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    /**
     * 
     */
    public static class MsgDataDecrypted extends Object {
        public byte[] proof;
        public MsgData data;

        /**
         * 
         */
        public MsgDataDecrypted() {
        }

        public MsgDataDecrypted(byte[] proof, MsgData data) {
            this.proof = proof;
            this.data = data;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = 195649769;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    /**
     * 
     */
    public static class MsgDataDecryptedArray extends Object {
        public MsgDataDecrypted[] elements;

        /**
         * 
         */
        public MsgDataDecryptedArray() {
        }

        public MsgDataDecryptedArray(MsgDataDecrypted[] elements) {
            this.elements = elements;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = -480491767;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    /**
     * 
     */
    public static class MsgDataEncrypted extends Object {
        public AccountAddress source;
        public MsgData data;

        /**
         * 
         */
        public MsgDataEncrypted() {
        }

        public MsgDataEncrypted(AccountAddress source, MsgData data) {
            this.source = source;
            this.data = data;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = 564215121;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    /**
     * 
     */
    public static class MsgDataEncryptedArray extends Object {
        public MsgDataEncrypted[] elements;

        /**
         * 
         */
        public MsgDataEncryptedArray() {
        }

        public MsgDataEncryptedArray(MsgDataEncrypted[] elements) {
            this.elements = elements;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = 608655794;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    /**
     * 
     */
    public static class MsgMessage extends Object {
        public AccountAddress destination;
        public String publicKey;
        public long amount;
        public MsgData data;

        /**
         * 
         */
        public MsgMessage() {
        }

        public MsgMessage(AccountAddress destination, String publicKey, long amount, MsgData data) {
            this.destination = destination;
            this.publicKey = publicKey;
            this.amount = amount;
            this.data = data;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = -2110533580;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    /**
     * 
     */
    public static class OptionsConfigInfo extends Object {
        public long defaultWalletId;
        public String defaultRwalletInitPublicKey;

        /**
         * 
         */
        public OptionsConfigInfo() {
        }

        public OptionsConfigInfo(long defaultWalletId, String defaultRwalletInitPublicKey) {
            this.defaultWalletId = defaultWalletId;
            this.defaultRwalletInitPublicKey = defaultRwalletInitPublicKey;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = 129457942;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    /**
     * 
     */
    public static class OptionsInfo extends Object {
        public OptionsConfigInfo configInfo;

        /**
         * 
         */
        public OptionsInfo() {
        }

        public OptionsInfo(OptionsConfigInfo configInfo) {
            this.configInfo = configInfo;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = -64676736;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    public abstract static class PchanAction extends Object {
    }

    public static class PchanActionInit extends PchanAction {
        public long incA;
        public long incB;
        public long minA;
        public long minB;

        /**
         * 
         */
        public PchanActionInit() {
        }

        public PchanActionInit(long incA, long incB, long minA, long minB) {
            this.incA = incA;
            this.incB = incB;
            this.minA = minA;
            this.minB = minB;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = 439088778;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    public static class PchanActionClose extends PchanAction {
        public long extraA;
        public long extraB;
        public PchanPromise promise;

        /**
         * 
         */
        public PchanActionClose() {
        }

        public PchanActionClose(long extraA, long extraB, PchanPromise promise) {
            this.extraA = extraA;
            this.extraB = extraB;
            this.promise = promise;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = 1671187222;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    public static class PchanActionTimeout extends PchanAction {

        /**
         * 
         */
        public PchanActionTimeout() {
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = 1998487795;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    /**
     * 
     */
    public static class PchanConfig extends Object {
        public String alicePublicKey;
        public AccountAddress aliceAddress;
        public String bobPublicKey;
        public AccountAddress bobAddress;
        public int initTimeout;
        public int closeTimeout;
        public long channelId;

        /**
         * 
         */
        public PchanConfig() {
        }

        public PchanConfig(String alicePublicKey, AccountAddress aliceAddress, String bobPublicKey, AccountAddress bobAddress, int initTimeout, int closeTimeout, long channelId) {
            this.alicePublicKey = alicePublicKey;
            this.aliceAddress = aliceAddress;
            this.bobPublicKey = bobPublicKey;
            this.bobAddress = bobAddress;
            this.initTimeout = initTimeout;
            this.closeTimeout = closeTimeout;
            this.channelId = channelId;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = -2071530442;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    /**
     * 
     */
    public static class PchanPromise extends Object {
        public byte[] signature;
        public long promiseA;
        public long promiseB;
        public long channelId;

        /**
         * 
         */
        public PchanPromise() {
        }

        public PchanPromise(byte[] signature, long promiseA, long promiseB, long channelId) {
            this.signature = signature;
            this.promiseA = promiseA;
            this.promiseB = promiseB;
            this.channelId = channelId;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = -1576102819;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    public abstract static class PchanState extends Object {
    }

    public static class PchanStateInit extends PchanState {
        public boolean signedA;
        public boolean signedB;
        public long minA;
        public long minB;
        public long expireAt;
        public long A;
        public long B;

        /**
         * 
         */
        public PchanStateInit() {
        }

        public PchanStateInit(boolean signedA, boolean signedB, long minA, long minB, long expireAt, long A, long B) {
            this.signedA = signedA;
            this.signedB = signedB;
            this.minA = minA;
            this.minB = minB;
            this.expireAt = expireAt;
            this.A = A;
            this.B = B;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = -1188426504;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    public static class PchanStateClose extends PchanState {
        public boolean signedA;
        public boolean signedB;
        public long minA;
        public long minB;
        public long expireAt;
        public long A;
        public long B;

        /**
         * 
         */
        public PchanStateClose() {
        }

        public PchanStateClose(boolean signedA, boolean signedB, long minA, long minB, long expireAt, long A, long B) {
            this.signedA = signedA;
            this.signedB = signedB;
            this.minA = minA;
            this.minB = minB;
            this.expireAt = expireAt;
            this.A = A;
            this.B = B;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = 887226867;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    public static class PchanStatePayout extends PchanState {
        public long A;
        public long B;

        /**
         * 
         */
        public PchanStatePayout() {
        }

        public PchanStatePayout(long A, long B) {
            this.A = A;
            this.B = B;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = 664671303;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    /**
     * 
     */
    public static class QueryFees extends Object {
        public Fees sourceFees;
        public Fees[] destinationFees;

        /**
         * 
         */
        public QueryFees() {
        }

        public QueryFees(Fees sourceFees, Fees[] destinationFees) {
            this.sourceFees = sourceFees;
            this.destinationFees = destinationFees;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = 1614616510;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    /**
     * 
     */
    public static class QueryInfo extends Object {
        public long id;
        public long validUntil;
        public byte[] bodyHash;
        public byte[] body;
        public byte[] initState;

        /**
         * 
         */
        public QueryInfo() {
        }

        public QueryInfo(long id, long validUntil, byte[] bodyHash, byte[] body, byte[] initState) {
            this.id = id;
            this.validUntil = validUntil;
            this.bodyHash = bodyHash;
            this.body = body;
            this.initState = initState;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = 1451875440;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    /**
     * 
     */
    public static class RawFullAccountState extends Object {
        public long balance;
        public byte[] code;
        public byte[] data;
        public InternalTransactionId lastTransactionId;
        public TonBlockIdExt blockId;
        public byte[] frozenHash;
        public long syncUtime;

        /**
         * 
         */
        public RawFullAccountState() {
        }

        public RawFullAccountState(long balance, byte[] code, byte[] data, InternalTransactionId lastTransactionId, TonBlockIdExt blockId, byte[] frozenHash, long syncUtime) {
            this.balance = balance;
            this.code = code;
            this.data = data;
            this.lastTransactionId = lastTransactionId;
            this.blockId = blockId;
            this.frozenHash = frozenHash;
            this.syncUtime = syncUtime;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = -1465398385;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    /**
     * 
     */
    public static class RawMessage extends Object {
        public AccountAddress source;
        public AccountAddress destination;
        public long value;
        public long fwdFee;
        public long ihrFee;
        public long createdLt;
        public boolean bounce;
        public boolean bounced;
        public byte[] bodyHash;
        public MsgData msgData;

        /**
         * 
         */
        public RawMessage() {
        }

        public RawMessage(AccountAddress source, AccountAddress destination, long value, long fwdFee, long ihrFee, long createdLt, boolean bounce, boolean bounced, byte[] bodyHash, MsgData msgData) {
            this.source = source;
            this.destination = destination;
            this.value = value;
            this.fwdFee = fwdFee;
            this.ihrFee = ihrFee;
            this.createdLt = createdLt;
            this.bounce = bounce;
            this.bounced = bounced;
            this.bodyHash = bodyHash;
            this.msgData = msgData;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = 1337874549;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    /**
     * 
     */
    public static class RawTransaction extends Object {
        public long utime;
        public byte[] data;
        public InternalTransactionId transactionId;
        public boolean aborted;
        public boolean destroyed;
        public long fee;
        public long storageFee;
        public long otherFee;
        public RawMessage inMsg;
        public RawMessage[] outMsgs;

        /**
         * 
         */
        public RawTransaction() {
        }

        public RawTransaction(long utime, byte[] data, InternalTransactionId transactionId, boolean aborted, boolean destroyed, long fee, long storageFee, long otherFee, RawMessage inMsg, RawMessage[] outMsgs) {
            this.utime = utime;
            this.data = data;
            this.transactionId = transactionId;
            this.aborted = aborted;
            this.destroyed = destroyed;
            this.fee = fee;
            this.storageFee = storageFee;
            this.otherFee = otherFee;
            this.inMsg = inMsg;
            this.outMsgs = outMsgs;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = -416313446;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    /**
     * 
     */
    public static class RawTransactions extends Object {
        public RawTransaction[] transactions;
        public InternalTransactionId previousTransactionId;

        /**
         * 
         */
        public RawTransactions() {
        }

        public RawTransactions(RawTransaction[] transactions, InternalTransactionId previousTransactionId) {
            this.transactions = transactions;
            this.previousTransactionId = previousTransactionId;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = -2063931155;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    /**
     * 
     */
    public static class RwalletActionInit extends Object {
        public RwalletConfig config;

        /**
         * 
         */
        public RwalletActionInit() {
        }

        public RwalletActionInit(RwalletConfig config) {
            this.config = config;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = 624147819;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    /**
     * 
     */
    public static class RwalletConfig extends Object {
        public long startAt;
        public RwalletLimit[] limits;

        /**
         * 
         */
        public RwalletConfig() {
        }

        public RwalletConfig(long startAt, RwalletLimit[] limits) {
            this.startAt = startAt;
            this.limits = limits;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = -85490534;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    /**
     * 
     */
    public static class RwalletLimit extends Object {
        public int seconds;
        public long value;

        /**
         * 
         */
        public RwalletLimit() {
        }

        public RwalletLimit(int seconds, long value) {
            this.seconds = seconds;
            this.value = value;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = 1222571646;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    /**
     * 
     */
    public static class SmcInfo extends Object {
        public long id;

        /**
         * 
         */
        public SmcInfo() {
        }

        public SmcInfo(long id) {
            this.id = id;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = 1134270012;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    public abstract static class SmcMethodId extends Object {
    }

    public static class SmcMethodIdNumber extends SmcMethodId {
        public int number;

        /**
         * 
         */
        public SmcMethodIdNumber() {
        }

        public SmcMethodIdNumber(int number) {
            this.number = number;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = -1541162500;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    public static class SmcMethodIdName extends SmcMethodId {
        public String name;

        /**
         * 
         */
        public SmcMethodIdName() {
        }

        public SmcMethodIdName(String name) {
            this.name = name;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = -249036908;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    /**
     * 
     */
    public static class SmcRunResult extends Object {
        public long gasUsed;
        public TvmStackEntry[] stack;
        public int exitCode;

        /**
         * 
         */
        public SmcRunResult() {
        }

        public SmcRunResult(long gasUsed, TvmStackEntry[] stack, int exitCode) {
            this.gasUsed = gasUsed;
            this.stack = stack;
            this.exitCode = exitCode;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = 1413805043;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    /**
     * 
     */
    public static class TonBlockId extends Object {
        public int workchain;
        public long shard;
        public int seqno;

        /**
         * 
         */
        public TonBlockId() {
        }

        public TonBlockId(int workchain, long shard, int seqno) {
            this.workchain = workchain;
            this.shard = shard;
            this.seqno = seqno;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = -1441288624;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    /**
     * 
     */
    public static class TonBlockIdExt extends Object {
        public int workchain;
        public long shard;
        public int seqno;
        public byte[] rootHash;
        public byte[] fileHash;

        /**
         * 
         */
        public TonBlockIdExt() {
        }

        public TonBlockIdExt(int workchain, long shard, int seqno, byte[] rootHash, byte[] fileHash) {
            this.workchain = workchain;
            this.shard = shard;
            this.seqno = seqno;
            this.rootHash = rootHash;
            this.fileHash = fileHash;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = 2031156378;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    /**
     * 
     */
    public static class TonBlockIds extends Object {
        public TonBlockId[] ids;

        /**
         * 
         */
        public TonBlockIds() {
        }

        public TonBlockIds(TonBlockId[] ids) {
            this.ids = ids;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = -1589584321;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    /**
     * 
     */
    public static class TonZeroStateIdExt extends Object {
        public int workchain;
        public byte[] rootHash;
        public byte[] fileHash;

        /**
         * 
         */
        public TonZeroStateIdExt() {
        }

        public TonZeroStateIdExt(int workchain, byte[] rootHash, byte[] fileHash) {
            this.workchain = workchain;
            this.rootHash = rootHash;
            this.fileHash = fileHash;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = -460522856;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    /**
     * 
     */
    public static class TvmCell extends Object {
        public byte[] bytes;

        /**
         * 
         */
        public TvmCell() {
        }

        public TvmCell(byte[] bytes) {
            this.bytes = bytes;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = -413424735;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    /**
     * 
     */
    public static class TvmList extends Object {
        public TvmStackEntry[] elements;

        /**
         * 
         */
        public TvmList() {
        }

        public TvmList(TvmStackEntry[] elements) {
            this.elements = elements;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = 1270320392;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    /**
     * 
     */
    public static class TvmNumberDecimal extends Object {
        public String number;

        /**
         * 
         */
        public TvmNumberDecimal() {
        }

        public TvmNumberDecimal(String number) {
            this.number = number;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = 1172477619;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    /**
     * 
     */
    public static class TvmSlice extends Object {
        public byte[] bytes;

        /**
         * 
         */
        public TvmSlice() {
        }

        public TvmSlice(byte[] bytes) {
            this.bytes = bytes;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = 537299687;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    public abstract static class TvmStackEntry extends Object {
    }

    public static class TvmStackEntrySlice extends TvmStackEntry {
        public TvmSlice slice;

        /**
         * 
         */
        public TvmStackEntrySlice() {
        }

        public TvmStackEntrySlice(TvmSlice slice) {
            this.slice = slice;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = 1395485477;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    public static class TvmStackEntryCell extends TvmStackEntry {
        public TvmCell cell;

        /**
         * 
         */
        public TvmStackEntryCell() {
        }

        public TvmStackEntryCell(TvmCell cell) {
            this.cell = cell;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = 1303473952;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    public static class TvmStackEntryNumber extends TvmStackEntry {
        public TvmNumberDecimal number;

        /**
         * 
         */
        public TvmStackEntryNumber() {
        }

        public TvmStackEntryNumber(TvmNumberDecimal number) {
            this.number = number;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = 1358642622;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    public static class TvmStackEntryTuple extends TvmStackEntry {
        public TvmTuple tuple;

        /**
         * 
         */
        public TvmStackEntryTuple() {
        }

        public TvmStackEntryTuple(TvmTuple tuple) {
            this.tuple = tuple;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = -157391908;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    public static class TvmStackEntryList extends TvmStackEntry {
        public TvmList list;

        /**
         * 
         */
        public TvmStackEntryList() {
        }

        public TvmStackEntryList(TvmList list) {
            this.list = list;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = -1186714229;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    public static class TvmStackEntryUnsupported extends TvmStackEntry {

        /**
         * 
         */
        public TvmStackEntryUnsupported() {
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = 378880498;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    /**
     * 
     */
    public static class TvmTuple extends Object {
        public TvmStackEntry[] elements;

        /**
         * 
         */
        public TvmTuple() {
        }

        public TvmTuple(TvmStackEntry[] elements) {
            this.elements = elements;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = -1363953053;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    /**
     * Adds a message to tonlib internal log. This is an offline method. Can be called before authorization. Can be called synchronously.
     *
     * <p> Returns {@link Ok Ok} </p>
     */
    public static class AddLogMessage extends Function {
        /**
         * Minimum verbosity level needed for the message to be logged, 0-1023.
         */
        public int verbosityLevel;
        /**
         * Text of a message to log.
         */
        public String text;

        /**
         * Default constructor for a function, which adds a message to tonlib internal log. This is an offline method. Can be called before authorization. Can be called synchronously.
         *
         * <p> Returns {@link Ok Ok} </p>
         */
        public AddLogMessage() {
        }

        /**
         * Creates a function, which adds a message to tonlib internal log. This is an offline method. Can be called before authorization. Can be called synchronously.
         *
         * <p> Returns {@link Ok Ok} </p>
         *
         * @param verbosityLevel Minimum verbosity level needed for the message to be logged, 0-1023.
         * @param text Text of a message to log.
         */
        public AddLogMessage(int verbosityLevel, String text) {
            this.verbosityLevel = verbosityLevel;
            this.text = text;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = 1597427692;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    /**
     * 
     *
     * <p> Returns {@link Key Key} </p>
     */
    public static class ChangeLocalPassword extends Function {
        public InputKey inputKey;
        public byte[] newLocalPassword;

        /**
         * Default constructor for a function, which 
         *
         * <p> Returns {@link Key Key} </p>
         */
        public ChangeLocalPassword() {
        }

        public ChangeLocalPassword(InputKey inputKey, byte[] newLocalPassword) {
            this.inputKey = inputKey;
            this.newLocalPassword = newLocalPassword;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = -401590337;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    /**
     * 
     *
     * <p> Returns {@link Ok Ok} </p>
     */
    public static class Close extends Function {

        /**
         * Default constructor for a function, which 
         *
         * <p> Returns {@link Ok Ok} </p>
         */
        public Close() {
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = -1187782273;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    /**
     * 
     *
     * <p> Returns {@link TonBlockIds TonBlockIds} </p>
     */
    public static class ComputeLastBlockIds extends Function {
        public TonBlockId[] topBlocks;

        /**
         * Default constructor for a function, which 
         *
         * <p> Returns {@link TonBlockIds TonBlockIds} </p>
         */
        public ComputeLastBlockIds() {
        }

        public ComputeLastBlockIds(TonBlockId[] topBlocks) {
            this.topBlocks = topBlocks;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = -1462062750;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    /**
     * 
     *
     * <p> Returns {@link Key Key} </p>
     */
    public static class CreateNewKey extends Function {
        public byte[] localPassword;
        public byte[] mnemonicPassword;
        public byte[] randomExtraSeed;

        /**
         * Default constructor for a function, which 
         *
         * <p> Returns {@link Key Key} </p>
         */
        public CreateNewKey() {
        }

        public CreateNewKey(byte[] localPassword, byte[] mnemonicPassword, byte[] randomExtraSeed) {
            this.localPassword = localPassword;
            this.mnemonicPassword = mnemonicPassword;
            this.randomExtraSeed = randomExtraSeed;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = -1861385712;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    /**
     * 
     *
     * <p> Returns {@link QueryInfo QueryInfo} </p>
     */
    public static class CreateQuery extends Function {
        public InputKey privateKey;
        public AccountAddress address;
        public int timeout;
        public Action action;
        public InitialAccountState initialAccountState;

        /**
         * Default constructor for a function, which 
         *
         * <p> Returns {@link QueryInfo QueryInfo} </p>
         */
        public CreateQuery() {
        }

        public CreateQuery(InputKey privateKey, AccountAddress address, int timeout, Action action, InitialAccountState initialAccountState) {
            this.privateKey = privateKey;
            this.address = address;
            this.timeout = timeout;
            this.action = action;
            this.initialAccountState = initialAccountState;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = -242540347;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    /**
     * 
     *
     * <p> Returns {@link Data Data} </p>
     */
    public static class Decrypt extends Function {
        public byte[] encryptedData;
        public byte[] secret;

        /**
         * Default constructor for a function, which 
         *
         * <p> Returns {@link Data Data} </p>
         */
        public Decrypt() {
        }

        public Decrypt(byte[] encryptedData, byte[] secret) {
            this.encryptedData = encryptedData;
            this.secret = secret;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = 357991854;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    /**
     * 
     *
     * <p> Returns {@link Ok Ok} </p>
     */
    public static class DeleteAllKeys extends Function {

        /**
         * Default constructor for a function, which 
         *
         * <p> Returns {@link Ok Ok} </p>
         */
        public DeleteAllKeys() {
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = 1608776483;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    /**
     * 
     *
     * <p> Returns {@link Ok Ok} </p>
     */
    public static class DeleteKey extends Function {
        public Key key;

        /**
         * Default constructor for a function, which 
         *
         * <p> Returns {@link Ok Ok} </p>
         */
        public DeleteKey() {
        }

        public DeleteKey(Key key) {
            this.key = key;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = -1579595571;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    /**
     * 
     *
     * <p> Returns {@link DnsResolved DnsResolved} </p>
     */
    public static class DnsResolve extends Function {
        public AccountAddress accountAddress;
        public String name;
        public int category;
        public int ttl;

        /**
         * Default constructor for a function, which 
         *
         * <p> Returns {@link DnsResolved DnsResolved} </p>
         */
        public DnsResolve() {
        }

        public DnsResolve(AccountAddress accountAddress, String name, int category, int ttl) {
            this.accountAddress = accountAddress;
            this.name = name;
            this.category = category;
            this.ttl = ttl;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = -149238065;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    /**
     * 
     *
     * <p> Returns {@link Data Data} </p>
     */
    public static class Encrypt extends Function {
        public byte[] decryptedData;
        public byte[] secret;

        /**
         * Default constructor for a function, which 
         *
         * <p> Returns {@link Data Data} </p>
         */
        public Encrypt() {
        }

        public Encrypt(byte[] decryptedData, byte[] secret) {
            this.decryptedData = decryptedData;
            this.secret = secret;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = -1821422820;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    /**
     * 
     *
     * <p> Returns {@link ExportedEncryptedKey ExportedEncryptedKey} </p>
     */
    public static class ExportEncryptedKey extends Function {
        public InputKey inputKey;
        public byte[] keyPassword;

        /**
         * Default constructor for a function, which 
         *
         * <p> Returns {@link ExportedEncryptedKey ExportedEncryptedKey} </p>
         */
        public ExportEncryptedKey() {
        }

        public ExportEncryptedKey(InputKey inputKey, byte[] keyPassword) {
            this.inputKey = inputKey;
            this.keyPassword = keyPassword;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = 218237311;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    /**
     * 
     *
     * <p> Returns {@link ExportedKey ExportedKey} </p>
     */
    public static class ExportKey extends Function {
        public InputKey inputKey;

        /**
         * Default constructor for a function, which 
         *
         * <p> Returns {@link ExportedKey ExportedKey} </p>
         */
        public ExportKey() {
        }

        public ExportKey(InputKey inputKey) {
            this.inputKey = inputKey;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = -1622353549;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    /**
     * 
     *
     * <p> Returns {@link ExportedPemKey ExportedPemKey} </p>
     */
    public static class ExportPemKey extends Function {
        public InputKey inputKey;
        public byte[] keyPassword;

        /**
         * Default constructor for a function, which 
         *
         * <p> Returns {@link ExportedPemKey ExportedPemKey} </p>
         */
        public ExportPemKey() {
        }

        public ExportPemKey(InputKey inputKey, byte[] keyPassword) {
            this.inputKey = inputKey;
            this.keyPassword = keyPassword;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = -643259462;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    /**
     * 
     *
     * <p> Returns {@link ExportedUnencryptedKey ExportedUnencryptedKey} </p>
     */
    public static class ExportUnencryptedKey extends Function {
        public InputKey inputKey;

        /**
         * Default constructor for a function, which 
         *
         * <p> Returns {@link ExportedUnencryptedKey ExportedUnencryptedKey} </p>
         */
        public ExportUnencryptedKey() {
        }

        public ExportUnencryptedKey(InputKey inputKey) {
            this.inputKey = inputKey;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = -634665152;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    /**
     * 
     *
     * <p> Returns {@link TransactionSearchResult TransactionSearchResult} </p>
     */
    public static class FindTransaction extends Function {
        public AccountAddress accountAddress;
        public byte[] messageId;
        public int after;

        /**
         * Default constructor for a function, which 
         *
         * <p> Returns {@link TransactionSearchResult TransactionSearchResult} </p>
         */
        public FindTransaction() {
        }

        public FindTransaction(AccountAddress accountAddress, byte[] messageId, int after) {
            this.accountAddress = accountAddress;
            this.messageId = messageId;
            this.after = after;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = 2034521355;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    /**
     * 
     *
     * <p> Returns {@link FtabiFunctionId FtabiFunctionId} </p>
     */
    public static class FtabiComputeFunctionId extends Function {
        public FtabiFunctionSignature signature;

        /**
         * Default constructor for a function, which 
         *
         * <p> Returns {@link FtabiFunctionId FtabiFunctionId} </p>
         */
        public FtabiComputeFunctionId() {
        }

        public FtabiComputeFunctionId(FtabiFunctionSignature signature) {
            this.signature = signature;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = -1043557717;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    /**
     * 
     *
     * <p> Returns {@link FtabiFunctionSignature FtabiFunctionSignature} </p>
     */
    public static class FtabiComputeFunctionSignature extends Function {
        public String name;
        public FtabiParam[] inputs;
        public FtabiParam[] outputs;

        /**
         * Default constructor for a function, which 
         *
         * <p> Returns {@link FtabiFunctionSignature FtabiFunctionSignature} </p>
         */
        public FtabiComputeFunctionSignature() {
        }

        public FtabiComputeFunctionSignature(String name, FtabiParam[] inputs, FtabiParam[] outputs) {
            this.name = name;
            this.inputs = inputs;
            this.outputs = outputs;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = 1336625892;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    /**
     * 
     *
     * <p> Returns {@link FtabiFunction FtabiFunction} </p>
     */
    public static class FtabiCreateFunction extends Function {
        public String name;
        public FtabiParam[] header;
        public FtabiParam[] inputs;
        public FtabiParam[] outputs;

        /**
         * Default constructor for a function, which 
         *
         * <p> Returns {@link FtabiFunction FtabiFunction} </p>
         */
        public FtabiCreateFunction() {
        }

        public FtabiCreateFunction(String name, FtabiParam[] header, FtabiParam[] inputs, FtabiParam[] outputs) {
            this.name = name;
            this.header = header;
            this.inputs = inputs;
            this.outputs = outputs;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = 1409376578;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    /**
     * 
     *
     * <p> Returns {@link FtabiMessageBody FtabiMessageBody} </p>
     */
    public static class FtabiCreateMessageBody extends Function {
        public FtabiFunction function;
        public FtabiFunctionCall call;

        /**
         * Default constructor for a function, which 
         *
         * <p> Returns {@link FtabiMessageBody FtabiMessageBody} </p>
         */
        public FtabiCreateMessageBody() {
        }

        public FtabiCreateMessageBody(FtabiFunction function, FtabiFunctionCall call) {
            this.function = function;
            this.call = call;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = -347250057;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    /**
     * 
     *
     * <p> Returns {@link FtabiDecodedInput FtabiDecodedInput} </p>
     */
    public static class FtabiDecodeInput extends Function {
        public FtabiFunction function;
        public byte[] data;
        public boolean internal;

        /**
         * Default constructor for a function, which 
         *
         * <p> Returns {@link FtabiDecodedInput FtabiDecodedInput} </p>
         */
        public FtabiDecodeInput() {
        }

        public FtabiDecodeInput(FtabiFunction function, byte[] data, boolean internal) {
            this.function = function;
            this.data = data;
            this.internal = internal;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = 1326318319;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    /**
     * 
     *
     * <p> Returns {@link FtabiDecodedOutput FtabiDecodedOutput} </p>
     */
    public static class FtabiDecodeOutput extends Function {
        public FtabiFunction function;
        public byte[] data;

        /**
         * Default constructor for a function, which 
         *
         * <p> Returns {@link FtabiDecodedOutput FtabiDecodedOutput} </p>
         */
        public FtabiDecodeOutput() {
        }

        public FtabiDecodeOutput(FtabiFunction function, byte[] data) {
            this.function = function;
            this.data = data;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = -81762598;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    /**
     * 
     *
     * <p> Returns {@link FtabiDecodedOutput FtabiDecodedOutput} </p>
     */
    public static class FtabiRunLocal extends Function {
        public AccountAddress address;
        public FtabiFunction function;
        public FtabiFunctionCall call;

        /**
         * Default constructor for a function, which 
         *
         * <p> Returns {@link FtabiDecodedOutput FtabiDecodedOutput} </p>
         */
        public FtabiRunLocal() {
        }

        public FtabiRunLocal(AccountAddress address, FtabiFunction function, FtabiFunctionCall call) {
            this.address = address;
            this.function = function;
            this.call = call;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = 866176614;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    /**
     * 
     *
     * <p> Returns {@link AccountAddress AccountAddress} </p>
     */
    public static class GetAccountAddress extends Function {
        public InitialAccountState initialAccountState;
        public int revision;
        public int workchainId;

        /**
         * Default constructor for a function, which 
         *
         * <p> Returns {@link AccountAddress AccountAddress} </p>
         */
        public GetAccountAddress() {
        }

        public GetAccountAddress(InitialAccountState initialAccountState, int revision, int workchainId) {
            this.initialAccountState = initialAccountState;
            this.revision = revision;
            this.workchainId = workchainId;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = 512468424;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    /**
     * 
     *
     * <p> Returns {@link FullAccountState FullAccountState} </p>
     */
    public static class GetAccountState extends Function {
        public AccountAddress accountAddress;

        /**
         * Default constructor for a function, which 
         *
         * <p> Returns {@link FullAccountState FullAccountState} </p>
         */
        public GetAccountState() {
        }

        public GetAccountState(AccountAddress accountAddress) {
            this.accountAddress = accountAddress;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = -2116357050;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    /**
     * 
     *
     * <p> Returns {@link Bip39Hints Bip39Hints} </p>
     */
    public static class GetBip39Hints extends Function {
        public String prefix;

        /**
         * Default constructor for a function, which 
         *
         * <p> Returns {@link Bip39Hints Bip39Hints} </p>
         */
        public GetBip39Hints() {
        }

        public GetBip39Hints(String prefix) {
            this.prefix = prefix;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = -1889640982;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    /**
     * Returns information about currently used log stream for internal logging of tonlib. This is an offline method. Can be called before authorization. Can be called synchronously.
     *
     * <p> Returns {@link LogStream LogStream} </p>
     */
    public static class GetLogStream extends Function {

        /**
         * Default constructor for a function, which returns information about currently used log stream for internal logging of tonlib. This is an offline method. Can be called before authorization. Can be called synchronously.
         *
         * <p> Returns {@link LogStream LogStream} </p>
         */
        public GetLogStream() {
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = 1167608667;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    /**
     * Returns current verbosity level for a specified tonlib internal log tag. This is an offline method. Can be called before authorization. Can be called synchronously.
     *
     * <p> Returns {@link LogVerbosityLevel LogVerbosityLevel} </p>
     */
    public static class GetLogTagVerbosityLevel extends Function {
        /**
         * Logging tag to change verbosity level.
         */
        public String tag;

        /**
         * Default constructor for a function, which returns current verbosity level for a specified tonlib internal log tag. This is an offline method. Can be called before authorization. Can be called synchronously.
         *
         * <p> Returns {@link LogVerbosityLevel LogVerbosityLevel} </p>
         */
        public GetLogTagVerbosityLevel() {
        }

        /**
         * Creates a function, which returns current verbosity level for a specified tonlib internal log tag. This is an offline method. Can be called before authorization. Can be called synchronously.
         *
         * <p> Returns {@link LogVerbosityLevel LogVerbosityLevel} </p>
         *
         * @param tag Logging tag to change verbosity level.
         */
        public GetLogTagVerbosityLevel(String tag) {
            this.tag = tag;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = 951004547;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    /**
     * Returns list of available tonlib internal log tags, for example, [&quot;actor&quot;, &quot;binlog&quot;, &quot;connections&quot;, &quot;notifications&quot;, &quot;proxy&quot;]. This is an offline method. Can be called before authorization. Can be called synchronously.
     *
     * <p> Returns {@link LogTags LogTags} </p>
     */
    public static class GetLogTags extends Function {

        /**
         * Default constructor for a function, which returns list of available tonlib internal log tags, for example, [&quot;actor&quot;, &quot;binlog&quot;, &quot;connections&quot;, &quot;notifications&quot;, &quot;proxy&quot;]. This is an offline method. Can be called before authorization. Can be called synchronously.
         *
         * <p> Returns {@link LogTags LogTags} </p>
         */
        public GetLogTags() {
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = -254449190;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    /**
     * Returns current verbosity level of the internal logging of tonlib. This is an offline method. Can be called before authorization. Can be called synchronously.
     *
     * <p> Returns {@link LogVerbosityLevel LogVerbosityLevel} </p>
     */
    public static class GetLogVerbosityLevel extends Function {

        /**
         * Default constructor for a function, which returns current verbosity level of the internal logging of tonlib. This is an offline method. Can be called before authorization. Can be called synchronously.
         *
         * <p> Returns {@link LogVerbosityLevel LogVerbosityLevel} </p>
         */
        public GetLogVerbosityLevel() {
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = 594057956;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    /**
     * 
     *
     * <p> Returns {@link AccountRevisionList AccountRevisionList} </p>
     */
    public static class GuessAccount extends Function {
        public String publicKey;
        public String rwalletInitPublicKey;

        /**
         * Default constructor for a function, which 
         *
         * <p> Returns {@link AccountRevisionList AccountRevisionList} </p>
         */
        public GuessAccount() {
        }

        public GuessAccount(String publicKey, String rwalletInitPublicKey) {
            this.publicKey = publicKey;
            this.rwalletInitPublicKey = rwalletInitPublicKey;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = -1737659296;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    /**
     * 
     *
     * <p> Returns {@link AccountRevisionList AccountRevisionList} </p>
     */
    public static class GuessAccountRevision extends Function {
        public InitialAccountState initialAccountState;
        public int workchainId;

        /**
         * Default constructor for a function, which 
         *
         * <p> Returns {@link AccountRevisionList AccountRevisionList} </p>
         */
        public GuessAccountRevision() {
        }

        public GuessAccountRevision(InitialAccountState initialAccountState, int workchainId) {
            this.initialAccountState = initialAccountState;
            this.workchainId = workchainId;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = 1857589922;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    /**
     * 
     *
     * <p> Returns {@link Key Key} </p>
     */
    public static class ImportEncryptedKey extends Function {
        public byte[] localPassword;
        public byte[] keyPassword;
        public ExportedEncryptedKey exportedEncryptedKey;

        /**
         * Default constructor for a function, which 
         *
         * <p> Returns {@link Key Key} </p>
         */
        public ImportEncryptedKey() {
        }

        public ImportEncryptedKey(byte[] localPassword, byte[] keyPassword, ExportedEncryptedKey exportedEncryptedKey) {
            this.localPassword = localPassword;
            this.keyPassword = keyPassword;
            this.exportedEncryptedKey = exportedEncryptedKey;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = 656724958;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    /**
     * 
     *
     * <p> Returns {@link Key Key} </p>
     */
    public static class ImportKey extends Function {
        public byte[] localPassword;
        public byte[] mnemonicPassword;
        public ExportedKey exportedKey;

        /**
         * Default constructor for a function, which 
         *
         * <p> Returns {@link Key Key} </p>
         */
        public ImportKey() {
        }

        public ImportKey(byte[] localPassword, byte[] mnemonicPassword, ExportedKey exportedKey) {
            this.localPassword = localPassword;
            this.mnemonicPassword = mnemonicPassword;
            this.exportedKey = exportedKey;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = -1607900903;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    /**
     * 
     *
     * <p> Returns {@link Key Key} </p>
     */
    public static class ImportPemKey extends Function {
        public byte[] localPassword;
        public byte[] keyPassword;
        public ExportedPemKey exportedKey;

        /**
         * Default constructor for a function, which 
         *
         * <p> Returns {@link Key Key} </p>
         */
        public ImportPemKey() {
        }

        public ImportPemKey(byte[] localPassword, byte[] keyPassword, ExportedPemKey exportedKey) {
            this.localPassword = localPassword;
            this.keyPassword = keyPassword;
            this.exportedKey = exportedKey;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = 76385617;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    /**
     * 
     *
     * <p> Returns {@link Key Key} </p>
     */
    public static class ImportUnencryptedKey extends Function {
        public byte[] localPassword;
        public ExportedUnencryptedKey exportedUnencryptedKey;

        /**
         * Default constructor for a function, which 
         *
         * <p> Returns {@link Key Key} </p>
         */
        public ImportUnencryptedKey() {
        }

        public ImportUnencryptedKey(byte[] localPassword, ExportedUnencryptedKey exportedUnencryptedKey) {
            this.localPassword = localPassword;
            this.exportedUnencryptedKey = exportedUnencryptedKey;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = -1184671467;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    /**
     * 
     *
     * <p> Returns {@link OptionsInfo OptionsInfo} </p>
     */
    public static class Init extends Function {
        public Options options;

        /**
         * Default constructor for a function, which 
         *
         * <p> Returns {@link OptionsInfo OptionsInfo} </p>
         */
        public Init() {
        }

        public Init(Options options) {
            this.options = options;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = -1000594762;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    /**
     * 
     *
     * <p> Returns {@link Data Data} </p>
     */
    public static class Kdf extends Function {
        public byte[] password;
        public byte[] salt;
        public int iterations;

        /**
         * Default constructor for a function, which 
         *
         * <p> Returns {@link Data Data} </p>
         */
        public Kdf() {
        }

        public Kdf(byte[] password, byte[] salt, int iterations) {
            this.password = password;
            this.salt = salt;
            this.iterations = iterations;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = -1667861635;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    /**
     * 
     *
     * <p> Returns {@link LiteServerAccount LiteServerAccount} </p>
     */
    public static class LiteServerGetAccount extends Function {
        public TonBlockIdExt id;
        public LiteServerAccountId account;

        /**
         * Default constructor for a function, which 
         *
         * <p> Returns {@link LiteServerAccount LiteServerAccount} </p>
         */
        public LiteServerGetAccount() {
        }

        public LiteServerGetAccount(TonBlockIdExt id, LiteServerAccountId account) {
            this.id = id;
            this.account = account;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = 1664425630;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    /**
     * 
     *
     * <p> Returns {@link LiteServerBlock LiteServerBlock} </p>
     */
    public static class LiteServerGetBlock extends Function {
        public TonBlockIdExt id;

        /**
         * Default constructor for a function, which 
         *
         * <p> Returns {@link LiteServerBlock LiteServerBlock} </p>
         */
        public LiteServerGetBlock() {
        }

        public LiteServerGetBlock(TonBlockIdExt id) {
            this.id = id;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = -843415714;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    /**
     * 
     *
     * <p> Returns {@link LiteServerBlockHeader LiteServerBlockHeader} </p>
     */
    public static class LiteServerGetBlockHeader extends Function {
        public TonBlockIdExt id;
        public int mode;

        /**
         * Default constructor for a function, which 
         *
         * <p> Returns {@link LiteServerBlockHeader LiteServerBlockHeader} </p>
         */
        public LiteServerGetBlockHeader() {
        }

        public LiteServerGetBlockHeader(TonBlockIdExt id, int mode) {
            this.id = id;
            this.mode = mode;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = -1720777562;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    /**
     * 
     *
     * <p> Returns {@link LiteServerPartialBlockProof LiteServerPartialBlockProof} </p>
     */
    public static class LiteServerGetBlockProof extends Function {
        public int mode;
        public TonBlockIdExt knownBlock;
        public TonBlockIdExt targetBlock;

        /**
         * Default constructor for a function, which 
         *
         * <p> Returns {@link LiteServerPartialBlockProof LiteServerPartialBlockProof} </p>
         */
        public LiteServerGetBlockProof() {
        }

        public LiteServerGetBlockProof(int mode, TonBlockIdExt knownBlock, TonBlockIdExt targetBlock) {
            this.mode = mode;
            this.knownBlock = knownBlock;
            this.targetBlock = targetBlock;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = 2067963957;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    /**
     * 
     *
     * <p> Returns {@link LiteServerConfigInfo LiteServerConfigInfo} </p>
     */
    public static class LiteServerGetConfigAll extends Function {
        public TonBlockIdExt id;

        /**
         * Default constructor for a function, which 
         *
         * <p> Returns {@link LiteServerConfigInfo LiteServerConfigInfo} </p>
         */
        public LiteServerGetConfigAll() {
        }

        public LiteServerGetConfigAll(TonBlockIdExt id) {
            this.id = id;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = 2136311253;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    /**
     * 
     *
     * <p> Returns {@link LiteServerConfigInfo LiteServerConfigInfo} </p>
     */
    public static class LiteServerGetConfigParams extends Function {
        public int mode;
        public TonBlockIdExt id;
        public int[] paramList;

        /**
         * Default constructor for a function, which 
         *
         * <p> Returns {@link LiteServerConfigInfo LiteServerConfigInfo} </p>
         */
        public LiteServerGetConfigParams() {
        }

        public LiteServerGetConfigParams(int mode, TonBlockIdExt id, int[] paramList) {
            this.mode = mode;
            this.id = id;
            this.paramList = paramList;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = -1110118394;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    /**
     * 
     *
     * <p> Returns {@link LiteServerInfo LiteServerInfo} </p>
     */
    public static class LiteServerGetInfo extends Function {

        /**
         * Default constructor for a function, which 
         *
         * <p> Returns {@link LiteServerInfo LiteServerInfo} </p>
         */
        public LiteServerGetInfo() {
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = 1435327470;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    /**
     * 
     *
     * <p> Returns {@link LiteServerMasterchainInfo LiteServerMasterchainInfo} </p>
     */
    public static class LiteServerGetMasterchainInfo extends Function {

        /**
         * Default constructor for a function, which 
         *
         * <p> Returns {@link LiteServerMasterchainInfo LiteServerMasterchainInfo} </p>
         */
        public LiteServerGetMasterchainInfo() {
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = -1984567762;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    /**
     * 
     *
     * <p> Returns {@link LiteServerTransaction LiteServerTransaction} </p>
     */
    public static class LiteServerGetOneTransaction extends Function {
        public TonBlockIdExt id;
        public LiteServerAccountId account;
        public long lt;

        /**
         * Default constructor for a function, which 
         *
         * <p> Returns {@link LiteServerTransaction LiteServerTransaction} </p>
         */
        public LiteServerGetOneTransaction() {
        }

        public LiteServerGetOneTransaction(TonBlockIdExt id, LiteServerAccountId account, long lt) {
            this.id = id;
            this.account = account;
            this.lt = lt;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = 2026128641;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    /**
     * 
     *
     * <p> Returns {@link LiteServerPastElections LiteServerPastElections} </p>
     */
    public static class LiteServerGetPastElections extends Function {
        public TonBlockIdExt id;
        public byte[] electorAddr;

        /**
         * Default constructor for a function, which 
         *
         * <p> Returns {@link LiteServerPastElections LiteServerPastElections} </p>
         */
        public LiteServerGetPastElections() {
        }

        public LiteServerGetPastElections(TonBlockIdExt id, byte[] electorAddr) {
            this.id = id;
            this.electorAddr = electorAddr;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = 840011655;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    /**
     * 
     *
     * <p> Returns {@link LiteServerBlockState LiteServerBlockState} </p>
     */
    public static class LiteServerGetState extends Function {
        public TonBlockIdExt id;

        /**
         * Default constructor for a function, which 
         *
         * <p> Returns {@link LiteServerBlockState LiteServerBlockState} </p>
         */
        public LiteServerGetState() {
        }

        public LiteServerGetState(TonBlockIdExt id) {
            this.id = id;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = -1749927932;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    /**
     * 
     *
     * <p> Returns {@link LiteServerCurrentTime LiteServerCurrentTime} </p>
     */
    public static class LiteServerGetTime extends Function {

        /**
         * Default constructor for a function, which 
         *
         * <p> Returns {@link LiteServerCurrentTime LiteServerCurrentTime} </p>
         */
        public LiteServerGetTime() {
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = 380459572;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    /**
     * 
     *
     * <p> Returns {@link LiteServerTransactionList LiteServerTransactionList} </p>
     */
    public static class LiteServerGetTransactions extends Function {
        public int count;
        public LiteServerAccountId account;
        public long lt;
        public byte[] hash;

        /**
         * Default constructor for a function, which 
         *
         * <p> Returns {@link LiteServerTransactionList LiteServerTransactionList} </p>
         */
        public LiteServerGetTransactions() {
        }

        public LiteServerGetTransactions(int count, LiteServerAccountId account, long lt, byte[] hash) {
            this.count = count;
            this.account = account;
            this.lt = lt;
            this.hash = hash;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = 1497798575;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    /**
     * 
     *
     * <p> Returns {@link LiteServerBlockTransactions LiteServerBlockTransactions} </p>
     */
    public static class LiteServerListBlockTransactions extends Function {
        public TonBlockIdExt id;
        public int mode;
        public int count;
        public LiteServerTransactionId3 after;
        public boolean reverseOrder;
        public boolean wantProof;

        /**
         * Default constructor for a function, which 
         *
         * <p> Returns {@link LiteServerBlockTransactions LiteServerBlockTransactions} </p>
         */
        public LiteServerListBlockTransactions() {
        }

        public LiteServerListBlockTransactions(TonBlockIdExt id, int mode, int count, LiteServerTransactionId3 after, boolean reverseOrder, boolean wantProof) {
            this.id = id;
            this.mode = mode;
            this.count = count;
            this.after = after;
            this.reverseOrder = reverseOrder;
            this.wantProof = wantProof;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = 1372000244;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    /**
     * 
     *
     * <p> Returns {@link LiteServerBlock LiteServerBlock} </p>
     */
    public static class LiteServerLookupBlock extends Function {
        public int mode;
        public TonBlockId id;
        public long lt;
        public int utime;

        /**
         * Default constructor for a function, which 
         *
         * <p> Returns {@link LiteServerBlock LiteServerBlock} </p>
         */
        public LiteServerLookupBlock() {
        }

        public LiteServerLookupBlock(int mode, TonBlockId id, long lt, int utime) {
            this.mode = mode;
            this.id = id;
            this.lt = lt;
            this.utime = utime;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = 1635869919;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    /**
     * 
     *
     * <p> Returns {@link MsgDataDecryptedArray MsgDataDecryptedArray} </p>
     */
    public static class MsgDecrypt extends Function {
        public InputKey inputKey;
        public MsgDataEncryptedArray data;

        /**
         * Default constructor for a function, which 
         *
         * <p> Returns {@link MsgDataDecryptedArray MsgDataDecryptedArray} </p>
         */
        public MsgDecrypt() {
        }

        public MsgDecrypt(InputKey inputKey, MsgDataEncryptedArray data) {
            this.inputKey = inputKey;
            this.data = data;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = 223596297;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    /**
     * 
     *
     * <p> Returns {@link MsgData MsgData} </p>
     */
    public static class MsgDecryptWithProof extends Function {
        public byte[] proof;
        public MsgDataEncrypted data;

        /**
         * Default constructor for a function, which 
         *
         * <p> Returns {@link MsgData MsgData} </p>
         */
        public MsgDecryptWithProof() {
        }

        public MsgDecryptWithProof(byte[] proof, MsgDataEncrypted data) {
            this.proof = proof;
            this.data = data;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = -2111649663;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    /**
     * 
     *
     * <p> Returns {@link Ok Ok} </p>
     */
    public static class OnLiteServerQueryError extends Function {
        public long id;
        public Error error;

        /**
         * Default constructor for a function, which 
         *
         * <p> Returns {@link Ok Ok} </p>
         */
        public OnLiteServerQueryError() {
        }

        public OnLiteServerQueryError(long id, Error error) {
            this.id = id;
            this.error = error;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = -677427533;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    /**
     * 
     *
     * <p> Returns {@link Ok Ok} </p>
     */
    public static class OnLiteServerQueryResult extends Function {
        public long id;
        public byte[] bytes;

        /**
         * Default constructor for a function, which 
         *
         * <p> Returns {@link Ok Ok} </p>
         */
        public OnLiteServerQueryResult() {
        }

        public OnLiteServerQueryResult(long id, byte[] bytes) {
            this.id = id;
            this.bytes = bytes;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = 2056444510;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    /**
     * 
     *
     * <p> Returns {@link OptionsConfigInfo OptionsConfigInfo} </p>
     */
    public static class OptionsSetConfig extends Function {
        public Config config;

        /**
         * Default constructor for a function, which 
         *
         * <p> Returns {@link OptionsConfigInfo OptionsConfigInfo} </p>
         */
        public OptionsSetConfig() {
        }

        public OptionsSetConfig(Config config) {
            this.config = config;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = 1870064579;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    /**
     * 
     *
     * <p> Returns {@link OptionsConfigInfo OptionsConfigInfo} </p>
     */
    public static class OptionsValidateConfig extends Function {
        public Config config;

        /**
         * Default constructor for a function, which 
         *
         * <p> Returns {@link OptionsConfigInfo OptionsConfigInfo} </p>
         */
        public OptionsValidateConfig() {
        }

        public OptionsValidateConfig(Config config) {
            this.config = config;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = -346965447;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    /**
     * 
     *
     * <p> Returns {@link AccountAddress AccountAddress} </p>
     */
    public static class PackAccountAddress extends Function {
        public UnpackedAccountAddress accountAddress;

        /**
         * Default constructor for a function, which 
         *
         * <p> Returns {@link AccountAddress AccountAddress} </p>
         */
        public PackAccountAddress() {
        }

        public PackAccountAddress(UnpackedAccountAddress accountAddress) {
            this.accountAddress = accountAddress;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = -1388561940;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    /**
     * 
     *
     * <p> Returns {@link Data Data} </p>
     */
    public static class PchanPackPromise extends Function {
        public PchanPromise promise;

        /**
         * Default constructor for a function, which 
         *
         * <p> Returns {@link Data Data} </p>
         */
        public PchanPackPromise() {
        }

        public PchanPackPromise(PchanPromise promise) {
            this.promise = promise;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = -851703103;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    /**
     * 
     *
     * <p> Returns {@link PchanPromise PchanPromise} </p>
     */
    public static class PchanSignPromise extends Function {
        public InputKey inputKey;
        public PchanPromise promise;

        /**
         * Default constructor for a function, which 
         *
         * <p> Returns {@link PchanPromise PchanPromise} </p>
         */
        public PchanSignPromise() {
        }

        public PchanSignPromise(InputKey inputKey, PchanPromise promise) {
            this.inputKey = inputKey;
            this.promise = promise;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = 1814322974;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    /**
     * 
     *
     * <p> Returns {@link PchanPromise PchanPromise} </p>
     */
    public static class PchanUnpackPromise extends Function {
        public byte[] data;

        /**
         * Default constructor for a function, which 
         *
         * <p> Returns {@link PchanPromise PchanPromise} </p>
         */
        public PchanUnpackPromise() {
        }

        public PchanUnpackPromise(byte[] data) {
            this.data = data;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = -1250106157;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    /**
     * 
     *
     * <p> Returns {@link Ok Ok} </p>
     */
    public static class PchanValidatePromise extends Function {
        public byte[] publicKey;
        public PchanPromise promise;

        /**
         * Default constructor for a function, which 
         *
         * <p> Returns {@link Ok Ok} </p>
         */
        public PchanValidatePromise() {
        }

        public PchanValidatePromise(byte[] publicKey, PchanPromise promise) {
            this.publicKey = publicKey;
            this.promise = promise;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = 258262242;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    /**
     * 
     *
     * <p> Returns {@link QueryFees QueryFees} </p>
     */
    public static class QueryEstimateFees extends Function {
        public long id;
        public boolean ignoreChksig;

        /**
         * Default constructor for a function, which 
         *
         * <p> Returns {@link QueryFees QueryFees} </p>
         */
        public QueryEstimateFees() {
        }

        public QueryEstimateFees(long id, boolean ignoreChksig) {
            this.id = id;
            this.ignoreChksig = ignoreChksig;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = -957002175;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    /**
     * 
     *
     * <p> Returns {@link Ok Ok} </p>
     */
    public static class QueryForget extends Function {
        public long id;

        /**
         * Default constructor for a function, which 
         *
         * <p> Returns {@link Ok Ok} </p>
         */
        public QueryForget() {
        }

        public QueryForget(long id) {
            this.id = id;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = -1211985313;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    /**
     * 
     *
     * <p> Returns {@link QueryInfo QueryInfo} </p>
     */
    public static class QueryGetInfo extends Function {
        public long id;

        /**
         * Default constructor for a function, which 
         *
         * <p> Returns {@link QueryInfo QueryInfo} </p>
         */
        public QueryGetInfo() {
        }

        public QueryGetInfo(long id) {
            this.id = id;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = -799333669;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    /**
     * 
     *
     * <p> Returns {@link Ok Ok} </p>
     */
    public static class QuerySend extends Function {
        public long id;

        /**
         * Default constructor for a function, which 
         *
         * <p> Returns {@link Ok Ok} </p>
         */
        public QuerySend() {
        }

        public QuerySend(long id) {
            this.id = id;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = 925242739;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    /**
     * 
     *
     * <p> Returns {@link Ok Ok} </p>
     */
    public static class RawCreateAndSendMessage extends Function {
        public AccountAddress destination;
        public byte[] initialAccountState;
        public byte[] data;

        /**
         * Default constructor for a function, which 
         *
         * <p> Returns {@link Ok Ok} </p>
         */
        public RawCreateAndSendMessage() {
        }

        public RawCreateAndSendMessage(AccountAddress destination, byte[] initialAccountState, byte[] data) {
            this.destination = destination;
            this.initialAccountState = initialAccountState;
            this.data = data;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = -772224603;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    /**
     * 
     *
     * <p> Returns {@link QueryInfo QueryInfo} </p>
     */
    public static class RawCreateQuery extends Function {
        public AccountAddress destination;
        public byte[] initCode;
        public byte[] initData;
        public byte[] body;

        /**
         * Default constructor for a function, which 
         *
         * <p> Returns {@link QueryInfo QueryInfo} </p>
         */
        public RawCreateQuery() {
        }

        public RawCreateQuery(AccountAddress destination, byte[] initCode, byte[] initData, byte[] body) {
            this.destination = destination;
            this.initCode = initCode;
            this.initData = initData;
            this.body = body;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = -1928557909;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    /**
     * 
     *
     * <p> Returns {@link RawFullAccountState RawFullAccountState} </p>
     */
    public static class RawGetAccountState extends Function {
        public AccountAddress accountAddress;

        /**
         * Default constructor for a function, which 
         *
         * <p> Returns {@link RawFullAccountState RawFullAccountState} </p>
         */
        public RawGetAccountState() {
        }

        public RawGetAccountState(AccountAddress accountAddress) {
            this.accountAddress = accountAddress;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = -1327847118;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    /**
     * 
     *
     * <p> Returns {@link RawTransactions RawTransactions} </p>
     */
    public static class RawGetTransactions extends Function {
        public InputKey privateKey;
        public AccountAddress accountAddress;
        public InternalTransactionId fromTransactionId;

        /**
         * Default constructor for a function, which 
         *
         * <p> Returns {@link RawTransactions RawTransactions} </p>
         */
        public RawGetTransactions() {
        }

        public RawGetTransactions(InputKey privateKey, AccountAddress accountAddress, InternalTransactionId fromTransactionId) {
            this.privateKey = privateKey;
            this.accountAddress = accountAddress;
            this.fromTransactionId = fromTransactionId;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = 1029612317;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    /**
     * 
     *
     * <p> Returns {@link Ok Ok} </p>
     */
    public static class RawSendMessage extends Function {
        public byte[] body;

        /**
         * Default constructor for a function, which 
         *
         * <p> Returns {@link Ok Ok} </p>
         */
        public RawSendMessage() {
        }

        public RawSendMessage(byte[] body) {
            this.body = body;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = -1789427488;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    /**
     * 
     *
     * <p> Returns {@link Ok Ok} </p>
     */
    public static class RunTests extends Function {
        public String dir;

        /**
         * Default constructor for a function, which 
         *
         * <p> Returns {@link Ok Ok} </p>
         */
        public RunTests() {
        }

        public RunTests(String dir) {
            this.dir = dir;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = -2039925427;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    /**
     * Sets new log stream for internal logging of tonlib. This is an offline method. Can be called before authorization. Can be called synchronously.
     *
     * <p> Returns {@link Ok Ok} </p>
     */
    public static class SetLogStream extends Function {
        /**
         * New log stream.
         */
        public LogStream logStream;

        /**
         * Default constructor for a function, which sets new log stream for internal logging of tonlib. This is an offline method. Can be called before authorization. Can be called synchronously.
         *
         * <p> Returns {@link Ok Ok} </p>
         */
        public SetLogStream() {
        }

        /**
         * Creates a function, which sets new log stream for internal logging of tonlib. This is an offline method. Can be called before authorization. Can be called synchronously.
         *
         * <p> Returns {@link Ok Ok} </p>
         *
         * @param logStream New log stream.
         */
        public SetLogStream(LogStream logStream) {
            this.logStream = logStream;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = -1364199535;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    /**
     * Sets the verbosity level for a specified tonlib internal log tag. This is an offline method. Can be called before authorization. Can be called synchronously.
     *
     * <p> Returns {@link Ok Ok} </p>
     */
    public static class SetLogTagVerbosityLevel extends Function {
        /**
         * Logging tag to change verbosity level.
         */
        public String tag;
        /**
         * New verbosity level; 1-1024.
         */
        public int newVerbosityLevel;

        /**
         * Default constructor for a function, which sets the verbosity level for a specified tonlib internal log tag. This is an offline method. Can be called before authorization. Can be called synchronously.
         *
         * <p> Returns {@link Ok Ok} </p>
         */
        public SetLogTagVerbosityLevel() {
        }

        /**
         * Creates a function, which sets the verbosity level for a specified tonlib internal log tag. This is an offline method. Can be called before authorization. Can be called synchronously.
         *
         * <p> Returns {@link Ok Ok} </p>
         *
         * @param tag Logging tag to change verbosity level.
         * @param newVerbosityLevel New verbosity level; 1-1024.
         */
        public SetLogTagVerbosityLevel(String tag, int newVerbosityLevel) {
            this.tag = tag;
            this.newVerbosityLevel = newVerbosityLevel;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = -2095589738;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    /**
     * Sets the verbosity level of the internal logging of tonlib. This is an offline method. Can be called before authorization. Can be called synchronously.
     *
     * <p> Returns {@link Ok Ok} </p>
     */
    public static class SetLogVerbosityLevel extends Function {
        /**
         * New value of the verbosity level for logging. Value 0 corresponds to fatal errors, value 1 corresponds to errors, value 2 corresponds to warnings and debug warnings, value 3 corresponds to informational, value 4 corresponds to debug, value 5 corresponds to verbose debug, value greater than 5 and up to 1023 can be used to enable even more logging.
         */
        public int newVerbosityLevel;

        /**
         * Default constructor for a function, which sets the verbosity level of the internal logging of tonlib. This is an offline method. Can be called before authorization. Can be called synchronously.
         *
         * <p> Returns {@link Ok Ok} </p>
         */
        public SetLogVerbosityLevel() {
        }

        /**
         * Creates a function, which sets the verbosity level of the internal logging of tonlib. This is an offline method. Can be called before authorization. Can be called synchronously.
         *
         * <p> Returns {@link Ok Ok} </p>
         *
         * @param newVerbosityLevel New value of the verbosity level for logging. Value 0 corresponds to fatal errors, value 1 corresponds to errors, value 2 corresponds to warnings and debug warnings, value 3 corresponds to informational, value 4 corresponds to debug, value 5 corresponds to verbose debug, value greater than 5 and up to 1023 can be used to enable even more logging.
         */
        public SetLogVerbosityLevel(int newVerbosityLevel) {
            this.newVerbosityLevel = newVerbosityLevel;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = -303429678;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    /**
     * 
     *
     * <p> Returns {@link TvmCell TvmCell} </p>
     */
    public static class SmcGetCode extends Function {
        public long id;

        /**
         * Default constructor for a function, which 
         *
         * <p> Returns {@link TvmCell TvmCell} </p>
         */
        public SmcGetCode() {
        }

        public SmcGetCode(long id) {
            this.id = id;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = -2115626088;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    /**
     * 
     *
     * <p> Returns {@link TvmCell TvmCell} </p>
     */
    public static class SmcGetData extends Function {
        public long id;

        /**
         * Default constructor for a function, which 
         *
         * <p> Returns {@link TvmCell TvmCell} </p>
         */
        public SmcGetData() {
        }

        public SmcGetData(long id) {
            this.id = id;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = -427601079;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    /**
     * 
     *
     * <p> Returns {@link TvmCell TvmCell} </p>
     */
    public static class SmcGetState extends Function {
        public long id;

        /**
         * Default constructor for a function, which 
         *
         * <p> Returns {@link TvmCell TvmCell} </p>
         */
        public SmcGetState() {
        }

        public SmcGetState(long id) {
            this.id = id;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = -214390293;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    /**
     * 
     *
     * <p> Returns {@link SmcInfo SmcInfo} </p>
     */
    public static class SmcLoad extends Function {
        public AccountAddress accountAddress;

        /**
         * Default constructor for a function, which 
         *
         * <p> Returns {@link SmcInfo SmcInfo} </p>
         */
        public SmcLoad() {
        }

        public SmcLoad(AccountAddress accountAddress) {
            this.accountAddress = accountAddress;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = -903491521;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    /**
     * 
     *
     * <p> Returns {@link SmcRunResult SmcRunResult} </p>
     */
    public static class SmcRunGetMethod extends Function {
        public long id;
        public SmcMethodId method;
        public TvmStackEntry[] stack;

        /**
         * Default constructor for a function, which 
         *
         * <p> Returns {@link SmcRunResult SmcRunResult} </p>
         */
        public SmcRunGetMethod() {
        }

        public SmcRunGetMethod(long id, SmcMethodId method, TvmStackEntry[] stack) {
            this.id = id;
            this.method = method;
            this.stack = stack;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = -255261270;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    /**
     * 
     *
     * <p> Returns {@link TonBlockIdExt TonBlockIdExt} </p>
     */
    public static class Sync extends Function {

        /**
         * Default constructor for a function, which 
         *
         * <p> Returns {@link TonBlockIdExt TonBlockIdExt} </p>
         */
        public Sync() {
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = -1875977070;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    /**
     * 
     *
     * <p> Returns {@link UnpackedAccountAddress UnpackedAccountAddress} </p>
     */
    public static class UnpackAccountAddress extends Function {
        public String accountAddress;

        /**
         * Default constructor for a function, which 
         *
         * <p> Returns {@link UnpackedAccountAddress UnpackedAccountAddress} </p>
         */
        public UnpackAccountAddress() {
        }

        public UnpackAccountAddress(String accountAddress) {
            this.accountAddress = accountAddress;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = -682459063;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

    /**
     * 
     *
     * <p> Returns {@link Object Object} </p>
     */
    public static class WithBlock extends Function {
        public TonBlockIdExt id;
        public Function function;

        /**
         * Default constructor for a function, which 
         *
         * <p> Returns {@link Object Object} </p>
         */
        public WithBlock() {
        }

        public WithBlock(TonBlockIdExt id, Function function) {
            this.id = id;
            this.function = function;
        }

        /**
         * Identifier uniquely determining type of the object.
         */
        public static final int CONSTRUCTOR = -789093723;

        /**
         * @return this.CONSTRUCTOR
         */
        @Override
        public int getConstructor() {
            return CONSTRUCTOR;
        }
    }

}
