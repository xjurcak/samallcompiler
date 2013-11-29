package sk.small.compiler.syntax;

import sk.small.compiler.lexic.*;
import sk.small.compiler.lexic.Number;
import sk.small.compiler.util.Log;

import java.io.IOException;
import java.util.Stack;

/**
 * Created with IntelliJ IDEA.
 * User: Ondrej Jurcak (xjurcak)
 * Date: 11/4/13
 * Time: 5:16 PM
 */
public class SyntaxAnalyzator {
    private static final String LOGTAG = SyntaxAnalyzator.class.getSimpleName();

    private final Lexicator lexicator;
    private Token token;
    private Stack<Object> stack = new Stack<Object>();

    public SyntaxAnalyzator(Lexicator lexicator) throws IOException {
        this.lexicator = lexicator;

        //push start nonterminal
        stack.push(TokenType.EOF);
        stack.push('P');
        token = lexicator.nextToken();
    }

    public boolean check() throws IOException {

        do {
            Log.d(LOGTAG, "Stack: " + stack);
            Object o = stack.pop();
            if(o instanceof Character) { //nonterminal so ve need expand new rule
                int rule = SyntaxTable.getRule((Character)o, token);
                Log.d(LOGTAG, "input: " + token + " rule: " + rule + " nonterminal: " + (Character)o );
                if(rule == 0){
                    Log.e(LOGTAG, "Error: no rule for nonterminal: " + (Character)o + " and input: " + token);
                    //not skip just remove nonterminal from stack
                    //token = lexicator.nextToken();
                } else {
                    applyRule(rule);
                }
            } else {
                if(token.getTokenType() == (TokenType)o){
                    //we can move to next Token
                    Log.d(LOGTAG, "input: " + token + " rule: pop terminal and get next token");
                    token = lexicator.nextToken();
                } else if (token.getTokenType() == TokenType.UNKNOWN) {
                    Log.e(LOGTAG, "Error: we expect : " + o + " and input is: " + token);
                    //unknown token so we rise an error and consider it as misspeling
                    token = lexicator.nextToken();
                } else{
                    Log.e(LOGTAG, "Error: we expect : " + o + " and input is: " + token);
                    //Missing token no skip
                }
            }


        } while (!stack.empty());

        if(token.getTokenType() == TokenType.EOF)
            return true;
        else
            return false;
    }

    private void applyRule(int rule) {
        switch (rule) {
            case 1:     //1. P -> bSd
                stack.push(TokenType.END);
                stack.push('S');
                stack.push(TokenType.BEGIN);
                break;
            case 2:     //2. S -> TZ
                stack.push('Z');
                stack.push('T');
                break;
            case 3:     //3. Z -> TZ
                stack.push('Z');
                stack.push('T');
                break;
            case 4:     //4. S -> e
                break;
            case 5:     //5. T -> IgE;
                stack.push(TokenType.STATEMENT_END);
                stack.push('E');
                stack.push(TokenType.ASSIGN);
                stack.push('I');
                break;
            case 6:     //6. T -> r(L);
                stack.push(TokenType.STATEMENT_END);
                stack.push(TokenType.RP);
                stack.push('L');
                stack.push(TokenType.LP);
                stack.push(TokenType.READ);
                break;
            case 7:     // 7. T -> w(X);
                stack.push(TokenType.STATEMENT_END);
                stack.push(TokenType.RP);
                stack.push('X');
                stack.push(TokenType.LP);
                stack.push(TokenType.WRITE);
                break;
            case 8:     //8. T -> iBhTY
                stack.push('Y');
                stack.push('T');
                stack.push(TokenType.THEN);
                stack.push('B');
                stack.push(TokenType.IF);
                break;
            case 9:     //9. Y -> ;
                stack.push(TokenType.STATEMENT_END);
                break;
            case 10:     //10. Y -> kT;
                stack.push(TokenType.STATEMENT_END);
                stack.push('T');
                stack.push(TokenType.ELSE);
                break;
            case 11:    //11. L -> IM
                stack.push('M');
                stack.push('I');
                break;
            case 12:    //12. M -> ,IM
                stack.push('M');
                stack.push('I');
                stack.push(TokenType.COMMA);
                break;
            case 13:    //13. M -> e
                break;
            case 14:    //14. X -> EK
                stack.push('K');
                stack.push('E');
                break;
            case 15:    //15. K -> ,EK
                stack.push('K');
                stack.push('E');
                stack.push(TokenType.COMMA);
                break;
            case 16:    //16. K -> e
                break;
            case 17:    //17. E -> FJ
                stack.push('J');
                stack.push('F');
                break;
            case 18:    //18. J -> OFJ
                stack.push('J');
                stack.push('F');
                stack.push('O');
                break;
            case 19:    //19. J -> e
                break;
            case 20:    //20. F -> (E)
                stack.push(TokenType.RP);
                stack.push('E');
                stack.push(TokenType.LP);
                break;
            case 21:    //21. F -> I
                stack.push('I');
                break;
            case 22:    //22. F -> N
                stack.push('N');
                break;
            case 23:    //23. O -> +
                stack.push(TokenType.PLUS);
                break;
            case 24:    //24. O -> -
                stack.push(TokenType.MINUS);
                break;
            case 25:    //25. B -> RD
                stack.push('D');
                stack.push('R');
                break;
            case 26:    //26.    D -> oRD
                stack.push('D');
                stack.push('R');
                stack.push(TokenType.OR);
                break;
            case 27:    //27.    D -> e
                break;
            case 28:    //28.    R -> AQ
                stack.push('Q');
                stack.push('A');
                break;
            case 29:    //29.    Q -> aAQ
                stack.push('Q');
                stack.push('A');
                stack.push(TokenType.AND);
                break;
            case 30:    //30.    Q -> e
                break;
            case 31:    //31.    A -> nA
                stack.push('A');
                stack.push(TokenType.NOT);
                break;
            case 32:    //32.    A -> (B)
                stack.push(TokenType.RP);
                stack.push('B');
                stack.push(TokenType.LP);
                break;
            case 33:    //33.    A -> t
                stack.push(TokenType.TRUE);
                break;
            case 34:    //34.    A -> f
                stack.push(TokenType.FALSE);
                break;
            case 35:    //35.    N -> +l
                stack.push(new Number(0)); //doesnt mather what number we put here we need only token name for compare
                stack.push(TokenType.PLUS);
                break;
            case 36:    //36.    N -> -l
                stack.push(new Number(0)); //doesnt mather what number we put here we need only token name for compare
                stack.push(TokenType.PLUS);
                break;
            case 37:    //37.    N -> l
                stack.push(TokenType.NUMBER); //doesnt mather what number we put here we need only token name for compare
                break;
            case 38:    //38.    I -> id token
                stack.push(TokenType.ID);
                break;
        }
    }
}
