package eapli.base.customermanagement.domain;

import java.util.Random;

/**
 * Raw password class
 */
public class RawPassword {

    public String rawPassword;

    private static final String UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    private static final String LOWER = UPPER.toLowerCase();

    private static final String NUMBER = "0123456789";

    private static final String ALPHANUMERICAL = UPPER + LOWER + NUMBER;

    private static final int ALPHANUMERICALSIZE = ALPHANUMERICAL.length();


    /**
     * Creates a random 7 characters-long Alphanumerical password
     * @return password
     */
    public static String createPwd(){
        String pwd = "";
        Random random = new Random();
        for(int i = 0; i < 7; i++){
            int n = random.nextInt(ALPHANUMERICALSIZE);
            pwd = pwd + ALPHANUMERICAL.charAt(n);
        }
        return pwd;
    }

    public RawPassword(){
        this.rawPassword = createPwd();
    }
}
