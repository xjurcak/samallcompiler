package sk.small.compiler.syntax;

import sk.small.compiler.lexic.Id;
import sk.small.compiler.lexic.Token;
import sk.small.compiler.lexic.Word;
import sk.small.compiler.util.Log;

import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: Ondrej Jurcak (xjurcak)
 * Date: 11/4/13
 * Time: 5:20 PM
 */
public class SyntaxTable {

    public static final String LOGTAG = SyntaxTable.class.getSimpleName();

    private static final int[][] table = new int[][]{
          //                            a    b    d    f     g  h     i  j   k       l   n    o  r     t    w
          //    (   )   +   ,   -   ; AND START END FALSE  := THEN  IF  ID ELSE NUMBER NOT  OR READ TRUE WRITE
            {   0,  0,  0,  0,  0,  0,  0,    1,  0,    0,  0,   0,  0,  0,   0,     0,  0,  0,   0,   0,    0,  0},  //P
            {   0,  0,  0,  0,  0,  0,  0,    0,  4,    0,  0,   0,  2,  2,   0,     0,  0,  0,   2,   0,    2,  0},  //S
            {   0,  0,  0,  0,  0,  0,  0,    0,  0,    0,  0,   0,  8,  5,   0,     0,  0,  0,   6,   0,    7,  0},  //T
            {   0,  0,  0,  0,  0,  9,  0,    0,  0,    0,  0,   0,  0,  0,  10,     0,  0,  0,   0,   0,    0,  0},  //Y
            {   0,  0,  0,  0,  0,  0,  0,    0,  0,    0,  0,   0,  0, 11,   0,     0,  0,  0,   0,   0,    0,  0},  //L
            {   0, 13,  0, 12,  0,  0,  0,    0,  0,    0,  0,   0,  0,  0,   0,     0,  0,  0,   0,   0,    0,  0},  //M
            {  14,  0, 14,  0, 14,  0,  0,    0,  0,    0,  0,   0,  0, 14,   0,    14,  0,  0,   0,   0,    0,  0},  //X
            {   0, 16,  0, 15,  0,  0,  0,    0,  0,    0,  0,   0,  0,  0,   0,     0,  0,  0,   0,   0,    0,  0},  //K
            {  17,  0, 17,  0, 17,  0,  0,    0,  0,    0,  0,   0,  0, 17,   0,    17,  0,  0,   0,   0,    0,  0},  //E
            {   0, 19, 18, 19, 18, 19,  0,    0,  0,    0,  0,   0,  0,  0,   0,     0,  0,  0,   0,   0,    0,  0},  //J
            {  20,  0, 22,  0, 22,  0,  0,    0,  0,    0,  0,   0,  0, 21,   0,    22,  0,  0,   0,   0,    0,  0},  //F
            {   0,  0, 23,  0, 24,  0,  0,    0,  0,    0,  0,   0,  0,  0,   0,     0,  0,  0,   0,   0,    0,  0},  //O
            {  25,  0,  0,  0,  0,  0,  0,    0,  0,   25,  0,   0,  0,  0,   0,     0, 25,  0,   0,  25,    0,  0},  //B
            {   0, 27,  0,  0,  0,  0,  0,    0,  0,    0,  0,  27,  0,  0,   0,     0,  0, 26,   0,   0,    0,  0},  //D
            {  28,  0,  0,  0,  0,  0,  0,    0,  0,   28,  0,   0,  0,  0,   0,     0, 28,  0,   0,  28,    0,  0},  //R
            {   0, 30,  0,  0,  0,  0, 29,    0,  0,    0,  0,  30,  0,  0,   0,     0,  0, 30,   0,   0,    0,  0},  //Q
            {  32,  0,  0,  0,  0,  0,  0,    0,  0,   34,  0,   0,  0,  0,   0,     0, 31,  0,   0,  33,    0,  0},  //A
            {   0,  0,  0,  0,  0,  0,  0,    0,  0,    0,  0,   0,  0, 38,   0,     0,  0,  0,   0,   0,    0,  0},  //I
            {   0,  0, 35,  0, 36,  0,  0,    0,  0,    0,  0,   0,  0,  0,   0,    37,  0,  0,   0,   0,    0,  0},  //N
            {   0,  0,  0,  0,  0,  0,  0,    0,  4,    0,  0,   0,  3,  3,   0,     0,  0,  0,   3,   0,    3,  0},  //Z

    };

    private static final HashMap<String, Integer> columnMapper;
    private static final HashMap<Character, Integer> rowMapper;

    static {
        columnMapper = new HashMap<String, Integer>();
        columnMapper.put(Word.LP.getName(), 0);
        columnMapper.put(Word.RP.getName(), 1);
        columnMapper.put(Word.PLUS.getName(), 2);
        columnMapper.put(Word.COMMA.getName(), 3);
        columnMapper.put(Word.MINUS.getName(), 4);
        columnMapper.put(Word.STATEMENT_END.getName(), 5);
        columnMapper.put(Word.AND.getName(), 6);
        columnMapper.put(Word.BEGIN.getName(), 7);
        columnMapper.put(Word.END.getName(), 8);
        columnMapper.put(Word.FALSE.getName(), 9);
        columnMapper.put(Word.ASSIGN.getName(), 10);
        columnMapper.put(Word.THEN.getName(), 11);
        columnMapper.put(Word.IF.getName(), 12);
        columnMapper.put(new Id(0).getName(), 13);
        columnMapper.put(Word.ELSE.getName(), 14);
        columnMapper.put(new sk.small.compiler.lexic.Number(0).getName(), 15);
        columnMapper.put(Word.NOT.getName(), 16);
        columnMapper.put(Word.OR.getName(), 17);
        columnMapper.put(Word.READ.getName(), 18);
        columnMapper.put(Word.TRUE.getName(), 19);
        columnMapper.put(Word.WRITE.getName(), 20);
        columnMapper.put(Word.EOF.getName(), 21);

        rowMapper = new HashMap<Character, Integer>();
        rowMapper.put('P', 0);
        rowMapper.put('S', 1);
        rowMapper.put('T', 2);
        rowMapper.put('Y', 3);
        rowMapper.put('L', 4);
        rowMapper.put('M', 5);
        rowMapper.put('X', 6);
        rowMapper.put('K', 7);
        rowMapper.put('E', 8);
        rowMapper.put('J', 9);
        rowMapper.put('F', 10);
        rowMapper.put('O', 11);
        rowMapper.put('B', 12);
        rowMapper.put('D', 13);
        rowMapper.put('R', 14);
        rowMapper.put('Q', 15);
        rowMapper.put('A', 16);
        rowMapper.put('I', 17);
        rowMapper.put('N', 18);
        rowMapper.put('Z', 19);

    }

    public static int getRule(char nonterminal, Token terminal) {
        Log.d(LOGTAG, "getRule( " + nonterminal + ", " + terminal.getName() + " )");
        int row = rowMapper.get(nonterminal);
        if(columnMapper.containsKey(terminal.getName())){
            int column = columnMapper.get(terminal.getName());
            return table[row][column];
        } else {
            return -1;
        }
    }
}
