package eapli.base.SPOMSPProtocol;

import eapli.framework.util.Utility;

@Utility
public class MessageParser {

    private MessageParser(){

    }


    public static SPOMSPRequest parse(byte[] message){

        SPOMSPRequest spomspRequest = null;

        if (message[Constants.VERSION_OFFSET] == 0){
                spomspRequest = redirect_parse(message);
        }else {
            System.out.println("Error. Different version of protocol");
        }

        return spomspRequest;
    }

    public static SPOMSPRequest redirect_parse(byte[] message){

        if (message[Constants.CODE_OFFSET] == Constants.COMMTEST) {
            return new TestRequest(message);
        } else if (message[Constants.CODE_OFFSET] == Constants.DISCONN) {
            return new EndRequest(message);
        } else if (message[Constants.CODE_OFFSET] == Constants.AGV_HELLO) {
            return new TestRequest(message);
        }

        return null;
    }

}
