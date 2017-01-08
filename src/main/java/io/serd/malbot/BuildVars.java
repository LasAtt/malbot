package io.serd.malbot;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Atte Lassila
 */
public class BuildVars {

    public static String token;
    public static String botName = "weeblistbot";

    public static String externalUrl;
    public static String internalUrl;

    public static String pathToCertificateStore;
    public static String certificateStorePassword;

    static {
        token = System.getProperty("token");
        System.out.println(token);
        externalUrl = System.getProperty("exturl");
        System.out.println(externalUrl);
        internalUrl = System.getProperty("inturl");
        pathToCertificateStore = System.getProperty("certstorepath");
        certificateStorePassword = System.getProperty("certstorepass");
    }
}
