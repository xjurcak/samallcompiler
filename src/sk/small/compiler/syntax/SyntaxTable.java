package sk.small.compiler.syntax;

import sk.small.compiler.lexic.Id;
import sk.small.compiler.lexic.Token;
import sk.small.compiler.lexic.TokenType;
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
          //                             a    b     d    f     g  h     i  j   k       l   n    o  r     t    w
          //    (   )   +   ,   -    ; AND START  END FALSE  := THEN  IF  ID ELSE NUMBER NOT  OR READ TRUE WRITE
            {   0,  0,  0,  0,  0,  -5,  0,    1,  -2,    0,  0,   0,  0,  0,   0,     0,  0,  0,   0,   0,    0,  -1},  //P
            {   0,  0,  0,  0,  0,  -5,  0,    0,  -2,    0,  0,   0,  2,  2,   0,     0,  0,  0,   2,   0,    2,  -1},  //S
            {   0,  0,  0,  0,  0,  -5,  0,    0,  -2,    0,  0,   0,  8,  5,   0,     0,  0,  0,   6,   0,    7,  -1},  //T
            {   0,  0,  0,  0,  0,   9,  0,    0,  -3,    0,  0,   0,  0,  0,  10,     0,  0,  0,   0,   0,    0,  -1},  //Y
            {   0,  0,  0,  0,  0,  -5,  0,    0,  -2,    0,  0,   0,  0, 11,   0,     0,  0,  0,   0,   0,    0,  -1},  //L
            {   0, 13,  0, 12,  0,  -5,  0,    0,  -2,    0,  0,   0,  0,  0,   0,     0,  0,  0,   0,   0,    0,  -1},  //M
            {  14,  0, 14,  0, 14,  -5,  0,    0,  -2,    0,  0,   0,  0, 14,   0,    14,  0,  0,   0,   0,    0,  -1},  //X
            {   0, 16,  0, 15,  0,  -5,  0,    0,  -2,    0,  0,   0,  0,  0,   0,     0,  0,  0,   0,   0,    0,  -1},  //K
            {  17,  0, 17,  0, 17,  -5,  0,    0,  -2,    0,  0,   0,  0, 17,   0,    17,  0,  0,   0,   0,    0,  -1},  //E
            {   0, 19, 18, 19, 18,  19,  0,    0,  -4,    0,  0,   0,  0,  0,   0,     0,  0,  0,   0,   0,    0,  -1},  //J
            {  20,  0, 22,  0, 22,  -5,  0,    0,  -2,    0,  0,   0,  0, 21,   0,    22,  0,  0,   0,   0,    0,  -1},  //F
            {   0,  0, 23,  0, 24,  -5,  0,    0,  -2,    0,  0,   0,  0,  0,   0,     0,  0,  0,   0,   0,    0,  -1},  //O
            {  25,  0,  0,  0,  0,  -5,  0,    0,  -2,   25,  0,   0,  0,  0,   0,     0, 25,  0,   0,  25,    0,  -1},  //B
            {   0, 27,  0,  0,  0,  -5,  0,    0,  -2,    0,  0,  27,  0,  0,   0,     0,  0, 26,   0,   0,    0,  -1},  //D
            {  28,  0,  0,  0,  0,  -5,  0,    0,  -2,   28,  0,   0,  0,  0,   0,     0, 28,  0,   0,  28,    0,  -1},  //R
            {   0, 30,  0,  0,  0,  -5, 29,    0,  -2,    0,  0,  30,  0,  0,   0,     0,  0, 30,   0,   0,    0,  -1},  //Q
            {  32,  0,  0,  0,  0,  -5,  0,    0,  -2,   34,  0,   0,  0,  0,   0,     0, 31,  0,   0,  33,    0,  -1},  //A
            {   0,  0,  0,  0,  0,  -5,  0,    0,  -2,    0,  0,   0,  0, 38,   0,     0,  0,  0,   0,   0,    0,  -1},  //I
            {   0,  0, 35,  0, 36,  -5,  0,    0,  -2,    0,  0,   0,  0,  0,   0,    37,  0,  0,   0,   0,    0,  -1},  //N
            {   0,  0,  0,  0,  0,  -5,  0,    0,   4,    0,  0,   0,  3,  3,   0,     0,  0,  0,   3,   0,    3,  -1},  //Z
            
            {  39, -6, -6, -6, -6,  -6, -6,   -6,  -6,   -6, -6,  -6, -6, -6,  -6,    -6, -6, -6,  -6,  -6,   -6,  -1},  //(
            {  -6, 39, -6, -6, -6,  -6, -6,   -6,  -6,   -6, -6,  -6, -6, -6,  -6,    -6, -6, -6,  -6,  -6,   -6,  -1},  //)
            {  -6, -6, 39, -6, -6,  -6, -6,   -6,  -6,   -6, -6,  -6, -6, -6,  -6,    -6, -6, -6,  -6,  -6,   -6,  -1},  //+
            {  -6, -6, -6, 39, -6,  -6, -6,   -6,  -6,   -6, -6,  -6, -6, -6,  -6,    -6, -6, -6,  -6,  -6,   -6,  -1},  //,
            {  -6, -6, -6, -6, 39,  -6, -6,   -6,  -6,   -6, -6,  -6, -6, -6,  -6,    -6, -6, -6,  -6,  -6,   -6,  -1},  //-
            {  -7, -7, -7, -7, -7,  39, -7,   -7,  -7,   -7, -7,  -7, -7, -7,  -7,    -7, -7, -7,  -7,  -7,   -7,  -1},  //;
            {  -6, -6, -6, -6, -6,  -6, 39,   -6,  -6,   -6, -6,  -6, -6, -6,  -6,    -6, -6, -6,  -6,  -6,   -6,  -1},  //AND
            {  -6, -6, -6, -6, -6,  -6, -6,   39,  -6,   -6, -6,  -6, -6, -6,  -6,    -6, -6, -6,  -6,  -6,   -6,  -1},  //START
            {  -6, -6, -6, -6, -6,  -6, -6,   -6,  39,   -6, -6,  -6, -6, -6,  -6,    -6, -6, -6,  -6,  -6,   -6,  -1},  //END
            {  -6, -6, -6, -6, -6,  -6, -6,   -6,  -6,   39, -6,  -6, -6, -6,  -6,    -6, -6, -6,  -6,  -6,   -6,  -1},  //FALSE
            {  -6, -6, -6, -6, -6,  -6, -6,   -6,  -6,   -6, 39,  -6, -6, -6,  -6,    -6, -6, -6,  -6,  -6,   -6,  -1},  //:=
            {  -6, -6, -6, -6, -6,  -6, -6,   -6,  -6,   -6, -6,  39, -6, -6,  -6,    -6, -6, -6,  -6,  -6,   -6,  -1},  //THEN
            {  -6, -6, -6, -6, -6,  -6, -6,   -6,  -6,   -6, -6,  -6, 39, -6,  -6,    -6, -6, -6,  -6,  -6,   -6,  -1},  //IF
            {  -6, -6, -6, -6, -6,  -6, -6,   -6,  -6,   -6, -6,  -6, -6, 39,  -6,    -6, -6, -6,  -6,  -6,   -6,  -1},  //ID
            {  -6, -6, -6, -6, -6,  -6, -6,   -6,  -6,   -6, -6,  -6, -6, -6,  39,    -6, -6, -6,  -6,  -6,   -6,  -1},  //ELSE
            {  -6, -6, -6, -6, -6,  -6, -6,   -6,  -6,   -6, -6,  -6, -6, -6,  -6,    39, -6, -6,  -6,  -6,   -6,  -1},  //NUMBER
            {  -6, -6, -6, -6, -6,  -6, -6,   -6,  -6,   -6, -6,  -6, -6, -6,  -6,    -6, 39, -6,  -6,  -6,   -6,  -1},  //NOT
            {  -6, -6, -6, -6, -6,  -6, -6,   -6,  -6,   -6, -6,  -6, -6, -6,  -6,    -6, -6, 39,  -6,  -6,   -6,  -1},  //OR
            {  -6, -6, -6, -6, -6,  -6, -6,   -6,  -6,   -6, -6,  -6, -6, -6,  -6,    -6, -6, -6,  39,  -6,   -6,  -1},  //READ
            {  -6, -6, -6, -6, -6,  -6, -6,   -6,  -6,   -6, -6,  -6, -6, -6,  -6,    -6, -6, -6,  -6,  39,   -6,  -1},  //TRUE
            {  -6, -6, -6, -6, -6,  -6, -6,   -6,  -6,   -6, -6,  -6, -6, -6,  -6,    -6, -6, -6,  -6,  -6,   39,  -1},  //WRITE
            {  -6, -6, -6, -6, -6,  -6, -6,   -6,  -6,   -6, -6,  -6, -6, -6,  -6,    -6, -6, -6,  -6,  -6,   -6,  40},  //

    };

    private static final HashMap<TokenType, Integer> columnMapper;
    private static final HashMap<Object, Integer> rowMapper;

    static {
        columnMapper = new HashMap<TokenType, Integer>();
        columnMapper.put(TokenType.LP, 0);
        columnMapper.put(TokenType.RP, 1);
        columnMapper.put(TokenType.PLUS, 2);
        columnMapper.put(TokenType.COMMA, 3);
        columnMapper.put(TokenType.MINUS, 4);
        columnMapper.put(TokenType.STATEMENT_END, 5);
        columnMapper.put(TokenType.AND, 6);
        columnMapper.put(TokenType.BEGIN, 7);
        columnMapper.put(TokenType.END, 8);
        columnMapper.put(TokenType.FALSE, 9);
        columnMapper.put(TokenType.ASSIGN, 10);
        columnMapper.put(TokenType.THEN, 11);
        columnMapper.put(TokenType.IF, 12);
        columnMapper.put(TokenType.ID, 13);
        columnMapper.put(TokenType.ELSE, 14);
        columnMapper.put(TokenType.NUMBER, 15);
        columnMapper.put(TokenType.NOT, 16);
        columnMapper.put(TokenType.OR, 17);
        columnMapper.put(TokenType.READ, 18);
        columnMapper.put(TokenType.TRUE, 19);
        columnMapper.put(TokenType.WRITE, 20);
        columnMapper.put(TokenType.EOF, 21);

        rowMapper = new HashMap<Object, Integer>();
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
        rowMapper.put(TokenType.LP, 20);
        rowMapper.put(TokenType.RP, 21);
        rowMapper.put(TokenType.PLUS, 22);
        rowMapper.put(TokenType.COMMA, 23);
        rowMapper.put(TokenType.MINUS, 24);
        rowMapper.put(TokenType.STATEMENT_END, 25);
        rowMapper.put(TokenType.AND, 26);
        rowMapper.put(TokenType.BEGIN, 27);
        rowMapper.put(TokenType.END, 28);
        rowMapper.put(TokenType.FALSE, 29);
        rowMapper.put(TokenType.ASSIGN, 30);
        rowMapper.put(TokenType.THEN, 31);
        rowMapper.put(TokenType.IF, 32);
        rowMapper.put(TokenType.ID, 33);
        rowMapper.put(TokenType.ELSE, 34);
        rowMapper.put(TokenType.NUMBER, 35);
        rowMapper.put(TokenType.NOT, 36);
        rowMapper.put(TokenType.OR, 37);
        rowMapper.put(TokenType.READ, 38);
        rowMapper.put(TokenType.TRUE, 39);
        rowMapper.put(TokenType.WRITE, 40);
        rowMapper.put(TokenType.EOF, 41);

    }

    public static int getRule(Object nonterminal, Token terminal) {
        Log.d(LOGTAG, "getRule( " + nonterminal + ", " + terminal + " )");
        int row = rowMapper.get(nonterminal);
        if(columnMapper.containsKey(terminal.getTokenType())){
            int column = columnMapper.get(terminal.getTokenType());
            return table[row][column];
        } else {
            return -1;
        }
    }
}
