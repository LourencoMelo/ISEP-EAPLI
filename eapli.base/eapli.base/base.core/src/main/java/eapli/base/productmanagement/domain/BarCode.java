package eapli.base.productmanagement.domain;

public class BarCode {

    private final String format;
    private final long code;


    public BarCode(String format, long code) {
        this.format = format;
        this.code = code;
    }

    public BarCode() {
        this.format = null;
        this.code = 0;
    }

    @Override
    public String toString() {
        return format + " : " + code;
    }
}
