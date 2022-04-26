package eapli.base.warehousemanagement.domain;

import eapli.framework.domain.model.ValueObject;

public class Depth extends Size implements ValueObject {
    public Depth(int lsquare, int wsquare) {
        super(lsquare, wsquare);
    }
}
