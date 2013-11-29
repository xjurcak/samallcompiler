package sk.small.compiler.lexic;

/**
 * Created with IntelliJ IDEA.
 * User: Ondrej Jurcak (xjurcak)
 * Date: 11/2/13
 * Time: 10:01 PM
 */
public class Word extends Token {

    public static final Word BEGIN = new Word(TokenType.BEGIN, "BEGIN");
    public static final Word END = new Word(TokenType.END, "END");
    public static final Word ASSIGN = new Word(TokenType.ASSIGN,":=");
    public static final Word READ = new Word(TokenType.READ,"READ");
    public static final Word WRITE = new Word(TokenType.WRITE,"WRITE");
    public static final Word LP = new Word(TokenType.LP,"(");  //left parenthesis
    public static final Word RP = new Word(TokenType.RP,")");  //left parenthesis
    public static final Word IF = new Word(TokenType.IF,"IF");
    public static final Word THEN = new Word(TokenType.THEN,"THEN");
    public static final Word ELSE = new Word(TokenType.ELSE,"ELSE");
    public static final Word OR = new Word(TokenType.OR,"OR");
    public static final Word AND = new Word(TokenType.AND,"AND");
    public static final Word PLUS = new Word(TokenType.PLUS,"+");
    public static final Word MINUS = new Word(TokenType.MINUS,"-");
    public static final Word STATEMENT_END = new Word(TokenType.STATEMENT_END,";");
    public static final Word COMMA = new Word(TokenType.COMMA,",");
    public static final Word TRUE = new Word(TokenType.TRUE,"TRUE");
    public static final Word FALSE = new Word(TokenType.FALSE,"FALSE");
    public static final Word NOT = new Word(TokenType.NOT,"NOT");
    public static final Word EOF = new Word(TokenType.EOF,"EOF");

    public String getWord() {
        return word;
    }

    private String word;

    public Word(TokenType type, String word) {
        super(type);
    }


}
