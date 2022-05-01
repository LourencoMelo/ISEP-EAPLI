package eapli.base.warehousemanagement.domain.warehouse;

public class Size{
    private int lsquare;
    private int wsquare;

    public Size(int lsquare, int wsquare) {
        this.lsquare = lsquare;
        this.wsquare = wsquare;
    }

    @Override
    public String toString() {
        return "Size{" +
                "lsquare=" + lsquare +
                ", wsquare=" + wsquare +
                '}';
    }
}
