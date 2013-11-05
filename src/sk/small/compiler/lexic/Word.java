package sk.small.compiler.lexic;

/**
 * Created with IntelliJ IDEA.
 * User: Ondrej Jurcak (xjurcak)
 * Date: 11/2/13
 * Time: 10:01 PM
 */
public class Word extends Token {

    public static final Word BEGIN = new Word("BEGIN");
    public static final Word END = new Word("END");
    public static final Word ASSIGN = new Word(":=");
    public static final Word READ = new Word("READ");
    public static final Word WRITE = new Word("WRITE");
    public static final Word LP = new Word("(");  //left parenthesis
    public static final Word RP = new Word(")");  //left parenthesis
    public static final Word IF = new Word("IF");
    public static final Word THEN = new Word("THEN");
    public static final Word ELSE = new Word("ELSE");
    public static final Word OR = new Word("OR");
    public static final Word AND = new Word("AND");
    public static final Word PLUS = new Word("+");
    public static final Word MINUS = new Word("-");
    public static final Word STATEMENT_END = new Word(";");
    public static final Word COMMA = new Word(",");
    public static final Word TRUE = new Word("TRUE");
    public static final Word FALSE = new Word("FALSE");
    public static final Word NOT = new Word("NOT");
    public static final Word EOF = new Word("EOF");

    public Word(String name) {
        super(name);
    }
}
