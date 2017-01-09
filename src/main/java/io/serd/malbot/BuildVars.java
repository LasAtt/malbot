package io.serd.malbot;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Atte Lassila
 */
public class BuildVars {

    public static String token = System.getProperty("bot.token");
    public static String botName = System.getProperty("bot.username");
    
    public static String malUser = System.getProperty("mal.user");
    public static String malPass = System.getProperty("mal.pass");
}
