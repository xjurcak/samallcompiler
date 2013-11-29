package sk.small.compiler.errors;

/**
 * Created with IntelliJ IDEA.
 * User: Ondrej Jurcak (xjurcak)
 * Date: 11/29/13
 * Time: 4:22 PM
 */
public class CompilerException extends Exception {

    public enum Type {
        LEXICATOR,
        SYNTAX
    }

    private Type type;

    public CompilerException( Type type, String message) {
        super(message);
        this.type = type;
    }

    public Type getType() {
        return type;
    }
}
