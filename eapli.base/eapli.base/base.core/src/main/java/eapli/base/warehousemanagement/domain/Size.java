package eapli.base.warehousemanagement.domain;

import eapli.framework.domain.model.ValueObject;

public class Size implements ValueObject{
    private int lsquare;
    private int wsquare;

    public Size(int lsquare, int wsquare) {
        this.lsquare = lsquare;
        this.wsquare = wsquare;
    }
}
