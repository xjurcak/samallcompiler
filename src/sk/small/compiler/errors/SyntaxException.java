package sk.small.compiler.errors;

/**
 * Created with IntelliJ IDEA.
 * User: Ondrej Jurcak (xjurcak)
 * Date: 11/29/13
 * Time: 4:56 PM
 */
public class SyntaxException extends CompilerException{

    public SyntaxException(String message) {
        super(Type.SYNTAX, message);
    }
}
