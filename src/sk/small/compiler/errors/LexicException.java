package sk.small.compiler.errors;

/**
 * Created with IntelliJ IDEA.
 * User: Ondrej Jurcak (xjurcak)
 * Date: 11/29/13
 * Time: 4:56 PM
 */
public class LexicException extends CompilerException{

    public LexicException(String message) {
        super(Type.LEXICATOR, message);
    }
}
