package sk.small.compiler.util;

import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: Ondrej Jurcak (xjurcak)
 * Date: 11/3/13
 * Time: 12:49 PM
 */
public class Log {

    private static final Logger logger = Logger.getLogger("SmallCompiler");

    static {
        logger.addHandler(new ConsoleHandler());
        logger.setLevel(Level.ALL);
    }

    public static void d(String tag, String message){
        //logger.log(Level.FINE, tag + ": " + message);
        System.out.printf(tag + ": " + message + "\n");
    }

    public static void e(String tag, String message){
        logger.log(Level.SEVERE, tag + ": " + message);
    }
}
