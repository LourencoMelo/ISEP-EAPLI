package eapli.base.warehousemanagement.domain.agv;

import eapli.framework.domain.model.ValueObject;

public class Position implements ValueObject {

    /**
     * Position X
     */
    private int x;
    /**
     * Position Y
     */
    private int y;

    /**
     * Construtor for Position
     * @param x
     * @param y
     */
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
