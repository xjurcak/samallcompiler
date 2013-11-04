package sk.small.compiler.syntax;

import sk.small.compiler.lexic.*;
import sk.small.compiler.lexic.Number;

import java.io.IOException;
import java.util.Stack;

/**
 * Created with IntelliJ IDEA.
 * User: Ondrej Jurcak (xjurcak)
 * Date: 11/4/13
 * Time: 5:16 PM
 */
public class SyntaxAnalyzator {

    private final Lexicator lexicator;
    private Token token;
    private Stack<Object> stack = new Stack<Object>();

    public SyntaxAnalyzator(Lexicator lexicator) throws IOException {
        this.lexicator = lexicator;

        //push start nonterminal
        stack.push(0);
        token = lexicator.nextToken();
    }

    public boolean check() throws IOException {

        Object o = stack.pop();

        if(o instanceof Character) { //nonterminal so ve need expand new rule
            int rule = SyntaxTable.getRule((Character)o, token);
            applyRule(rule);
        } else {
            if(token.equals(o)){
                //we can move to next Token
                token = lexicator.nextToken();
            }
        }

        return true;
    }

    /*

        S -> TS
        S -> e
        T -> IgE;
        T -> r(L);
        T -> w(X);
        T -> iBhTY
        Y -> ;|kT;
        L -> IM
        M -> ,IM|e
        X -> EK
        K -> ,EK|e
        E -> FJ
        J -> OFJ|e
        F -> (E)
        F -> I|N
        O -> +|-
        B -> RD
        D -> oRD|e
        R -> AQ
        Q -> aAQ|e
        A -> nA|(B)|t|f
        I -> ZG
        G -> ZG|WG|e
        N -> +H|-H|H
        H -> UC
        C -> WC|e
        U -> 1|2|3|4|5|6|7|8|9
        W -> 0|1|2|3|4|5|6|7|8|9
        Z -> c
    *
    * */

    private void applyRule(int rule) {
        switch (rule) {
            case 1:     //1. P -> bSd
                stack.push(Word.END);
                stack.push('S');
                stack.push(Word.BEGIN);
                break;
            case 2:     //2. S -> TS
                stack.push('S');
                stack.push('T');
                break;
            case 3:     //3. S -> e
                break;
            case 4:     //4. T -> IgE;
                stack.push('E');
                stack.push(Word.ASSIGN);
                stack.push('I');
                break;
            case 5:     //5. T -> r(L);
                stack.push(Word.RP);
                stack.push('L');
                stack.push(Word.LP);
                stack.push(Word.READ);
                break;
            case 6:     // 6. T -> w(X);
                stack.push(Word.RP);
                stack.push('X');
                stack.push(Word.LP);
                stack.push(Word.WRITE);
                break;
            case 7:     //7. T -> iBhTY
                stack.push('Y');
                stack.push('T');
                stack.push(Word.THEN);
                stack.push('B');
                stack.push(Word.IF);
                break;
            case 8:     //8. Y -> ;
                stack.push(Word.STATEMENT_END);
                break;
            case 9:     //9. Y -> kT;
                stack.push('T');
                stack.push(Word.ELSE);
                break;
            case 10:    //10. L -> IM
                stack.push('M');
                stack.push('I');
                break;
            case 11:    //11. M -> ,IM
                stack.push('M');
                stack.push('I');
                stack.push(Word.COMMA);
                break;
            case 12:    //12. M -> e
                break;
            case 13:    //13. X -> EK
                stack.push('K');
                stack.push('E');
                break;
            case 14:    //14. K -> ,EK
                stack.push('K');
                stack.push('E');
                stack.push(Word.COMMA);
                break;
            case 15:    //15. K -> e
                break;
            case 16:    //16. E -> FJ
                stack.push('J');
                stack.push('F');
                break;
            case 17:    //17. J -> OFJ
                stack.push('J');
                stack.push('F');
                stack.push('O');
                break;
            case 18:    //18. J -> e
                break;
            case 19:    //19. F -> (E)
                stack.push(Word.RP);
                stack.push('E');
                stack.push(Word.LP);
                break;
            case 20:    //20. F -> I
                stack.push('I');
                break;
            case 21:    //21. F -> N
                stack.push('I');
                break;
            case 22:    //22. O -> +
                stack.push(Word.PLUS);
                break;
            case 23:    //23. O -> -
                stack.push(Word.MINUS);
                break;
            case 24:    //24. B -> RD
                stack.push('D');
                stack.push('R');
                break;
            case 25:    //25.    D -> oRD|e
                stack.push('D');
                stack.push('R');
                stack.push(Word.OR);
                break;
            case 26:    //26.    D -> e
                break;
            case 27:    //27.    R -> AQ
                stack.push('Q');
                stack.push('A');
                break;
            case 28:    //28.    Q -> aAQ
                stack.push('Q');
                stack.push('A');
                stack.push(Word.AND);
                break;
            case 29:    //29.    Q -> e
                break;
            case 30:    //30.    A -> nA
                stack.push('A');
                stack.push(Word.NOT);
                break;
            case 31:    //31.    A -> (B)
                stack.push(Word.RP);
                stack.push('B');
                stack.push(Word.LP);
                break;
            case 32:    //32.    A -> t
                stack.push(Word.TRUE);
                break;
            case 33:    //33.    A -> f
                stack.push(Word.FALSE);
                break;
            case 34:    //34.    N -> +l
                stack.push(new Number(0)); //doesnt mather what number we put here we need only token name for compare
                stack.push(Word.PLUS);
                break;
            case 35:    //35.    N -> -l
                stack.push(new Number(0)); //doesnt mather what number we put here we need only token name for compare
                stack.push(Word.PLUS);
                break;
            case 36:    //36.    N -> l
                stack.push(new Number(0)); //doesnt mather what number we put here we need only token name for compare
                break;
            case 37:    //37.    I -> id token
                stack.push(new Id(0));
                break;
        }
    }
}
