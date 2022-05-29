package eapli.base.ordermanagement.domain;

/**
 * Enum with possible orders status
 */
public enum OrderStatus {

    /**
     * Waiting for payment by the customer
     */
    PAYMENT_PENDING,

    /**
     * Paid by the customer
     */
    PAID,

    /**
     * Waiting to be prepared
     */
    PREPARE_PENDING,


    /**
     * In preparation
     */
    IN_PREPARATION,

    /**
     * Prepared by the AGV
     */
    PREPARED,

    /**
     * Dispatched for customer delivery
     */
    DISPATCHED,

    /**
     * Being delivered to the customer
     */
    BEING_DELIVERED,

    /**
     * Delivered to the customer
     */
    DELIVERED,

    /**
     * Undelivered
     */
    UNDELIVERED,

    /**
     * Canceled
     */
    CANCELED,
}
