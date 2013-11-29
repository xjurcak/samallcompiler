package sk.small.compiler.errors;

import sk.small.compiler.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Ondrej Jurcak (xjurcak)
 * Date: 11/29/13
 * Time: 4:19 PM
 */
public class ErrorReporter {

    private static final String LOGTAG = ErrorReporter.class.getSimpleName();

    public List<CompilerException> getErrors() {
        return errors;
    }

    private List<CompilerException> errors = new ArrayList<CompilerException>();

    public void reportError(CompilerException e){
        Log.e(LOGTAG, e.getType().name() + ":" + e.getMessage());
        errors.add(e);
    }
}
