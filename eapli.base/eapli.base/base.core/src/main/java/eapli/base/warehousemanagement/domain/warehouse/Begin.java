package eapli.base.warehousemanagement.domain.warehouse;

import eapli.framework.domain.model.ValueObject;

public class Begin extends Size implements ValueObject {
    public Begin(int lsquare, int wsquare) {
        super(lsquare, wsquare);
    }
}
