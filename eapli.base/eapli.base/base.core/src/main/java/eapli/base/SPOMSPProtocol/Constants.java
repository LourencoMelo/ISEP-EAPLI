package eapli.base.SPOMSPProtocol;

public class Constants {

    /**
     * Current system version supported
     */
    public static final int CURRENT_VERSION = 0;

    /**
     * Offsets from messages
     */
    public static final int VERSION_OFFSET = 0;
    public static final int CODE_OFFSET = 1;
    public static final int LENGHT_1_OFFSET = 2;
    public static final int LENGHT_2_OFFSET = 3;
    public static final int DATA_OFFSET = 4;

    /**
     * Message Codes
     */
    public static final int COMMTEST = 0;
    public static final int DISCONN = 1;
    public static final int ACK = 2;
}
