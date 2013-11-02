package sk.small.compiler.lexic;

/**
 * Created with IntelliJ IDEA.
 * User: Ondrej Jurcak (xjurcak)
 * Date: 11/2/13
 * Time: 10:30 PM
 */
public class Id extends Token {

    public int getTableId() {
        return tableId;
    }

    private int tableId;

    public Id(int tableId){
        super("id");
        this.tableId = tableId;
    }
}
