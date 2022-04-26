package eapli.base.warehousemanagement.domain;

import eapli.framework.domain.model.ValueObject;

public class End extends Size implements ValueObject {

    public End(int lsquare, int wsquare) {
        super(lsquare, wsquare);
    }
}
