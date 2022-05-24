package eapli.base.SPOMSPProtocol;

public abstract class SPOMSPRequest {

    protected final byte[] request;

    protected SPOMSPRequest(byte[] request){
        this.request = request;
    }

    public abstract byte[] execute();


}
