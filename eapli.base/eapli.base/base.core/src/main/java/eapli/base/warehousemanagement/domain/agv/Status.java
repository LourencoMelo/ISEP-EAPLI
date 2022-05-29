package eapli.base.warehousemanagement.domain.agv;

public enum Status{

    /**
     * AGV is Charging on the AGVDock
      */
    CHARGING,

    /**
     * AGV is turned on but not ready
     */
    ON,

    /**
     * AGV is Ready
     */
    READY,

    /**
     * AGV is Busy
     */
    BUSY,

    /**
     * LOW BATTERY NEEDS CHARCHING
     */
    LOWBATTERY,

}
