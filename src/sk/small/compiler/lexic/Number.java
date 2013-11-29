package sk.small.compiler.lexic;

/**
 * Created with IntelliJ IDEA.
 * User: Ondrej Jurcak (xjurcak)
 * Date: 11/2/13
 * Time: 10:33 PM
 */
public class Number extends Token{

    private int number;

    public Number(int number){
        super(TokenType.NUMBER);
        this.number = number;

    }

    public int getNumber() {
        return number;
    }
}
