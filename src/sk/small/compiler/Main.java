package sk.small.compiler;

import sk.small.compiler.lexic.Lexicator;
import sk.small.compiler.lexic.Token;
import sk.small.compiler.syntax.SyntaxAnalyzator;
import sk.small.compiler.util.Log;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class Main {

    private static final String LOGTAG = Main.class.getSimpleName();

    public static void main(String[] args) {
        try {
            InputStream is = new FileInputStream("C:\\Users\\xjurcako\\Documents\\skola\\softjazyky\\SmallCompilerProject\\test\\example.small");
            Lexicator lexicator = new Lexicator(is);
            SyntaxAnalyzator analyzator = new SyntaxAnalyzator(lexicator);
            boolean result = analyzator.check();
            Log.d(LOGTAG, "Analyzator check reslut: " + result);

        } catch (FileNotFoundException e) {
            Log.e(LOGTAG, e.getMessage());
        } catch (IOException e) {
            Log.e(LOGTAG, e.getMessage());
        }
    }
}
