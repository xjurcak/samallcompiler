package sk.small.compiler.util;

import sk.small.compiler.errors.CompilerException;
import sk.small.compiler.errors.ErrorReporter;

import java.util.Date;
import java.util.List;
import java.util.logging.*;

/**
 * Created with IntelliJ IDEA.
 * User: Ondrej Jurcak (xjurcak)
 * Date: 11/3/13
 * Time: 12:49 PM
 */
public class Log {

    private static final Logger logger = Logger.getLogger("SmallCompiler");
    private static final Level LEVEL = Level.ALL;

    static {
        Handler handler = new ConsoleHandler();
        handler.setFormatter(new Formatter() {

            private final Date dat = new Date();

            @Override
            public String format(LogRecord record) {
                dat.setTime(record.getMillis());
                return String.format("%1$tb %1$td, %1$tY %1$tl:%1$tM:%1$tS %1$Tp %2$s: %3$s%n", dat, record.getLevel().getLocalizedName(), record.getMessage());
            }
        });

        handler.setLevel(LEVEL);
        logger.addHandler(handler);
        logger.setLevel(LEVEL);
        logger.setUseParentHandlers(false);
    }

    public static void d(String tag, String message){
        logger.log(Level.FINE, tag + ": " + message);
        //System.out.printf(tag + ": " + message + "\n");
    }

    public static void e(String tag, String message){
        logger.log(Level.SEVERE, tag + ": " + message);
    }

    public static void e(String tag, List<CompilerException> errors){

        for(CompilerException e: errors){
            Log.e(tag, e.getType().name() + ":" + e.getMessage());
        }
    }
}
