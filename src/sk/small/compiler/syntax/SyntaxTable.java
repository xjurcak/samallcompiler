package sk.small.compiler.syntax;

import sk.small.compiler.lexic.Token;
import sk.small.compiler.lexic.Word;

import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: Ondrej Jurcak (xjurcak)
 * Date: 11/4/13
 * Time: 5:20 PM
 */
public class SyntaxTable {

    private static final int[][] table = new int[][]{
        {0, 0, 1, 0, 0},
        {0, 0, 0, 0, 0},
        {0, 0, 0, 1, 0},
        {0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0}
    };

    private static final HashMap<String, Integer> columnMapper;
    private static final HashMap<Character, Integer> rowMapper;

    static {
        columnMapper = new HashMap<String, Integer>();
        columnMapper.put(Word.BEGIN.getName(), 0);
        columnMapper.put(Word.END.getName(), 1);

        rowMapper = new HashMap<Character, Integer>();

    }

    public static int getRule(char nonterminal, Token terminal) {
        return table[rowMapper.get(nonterminal)][columnMapper.get(terminal.getName())];
    }
}
