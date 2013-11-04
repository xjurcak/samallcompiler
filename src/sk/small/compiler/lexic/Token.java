package sk.small.compiler.lexic;

/**
 * Created with IntelliJ IDEA.
 * User: Ondrej Jurcak (xjurcak)
 * Date: 11/1/13
 * Time: 10:41 PM
 */
public class Token {

    private String name;

    public Token(String name) {
        this.name = name;
    }

    public Token(char name) {
        this.name = Character.toString(name);
    }



    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Token name: \"" + name + "\"";
    }
}