package sk.small.compiler.lexic;

/**
 * Created with IntelliJ IDEA.
 * User: Ondrej Jurcak (xjurcak)
 * Date: 11/29/13
 * Time: 12:32 PM
 */
public enum TokenType {
    UNKNOWN,
    NUMBER,
    ID,
    BEGIN,
    END,
    ASSIGN,
    READ,
    WRITE,
    LP,  //left parenthesis
    RP,  //left parenthesis
    IF,
    THEN,
    ELSE,
    OR,
    AND,
    PLUS,
    MINUS,
    STATEMENT_END,
    COMMA,
    TRUE,
    FALSE,
    NOT,
    EOF,
}
