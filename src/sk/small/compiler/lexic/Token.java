package sk.small.compiler.lexic;

/**
 * Created with IntelliJ IDEA.
 * User: Ondrej Jurcak (xjurcak)
 * Date: 11/1/13
 * Time: 10:41 PM
 */
public class Token {

    private TokenType tokenType;

    public Token(TokenType id) {
        this.tokenType = id;
    }

    public TokenType getTokenType(){
        return tokenType;
    }

    @Override
    public String toString() {
        return "\"" + tokenType.name() + "\"";
    }
}
