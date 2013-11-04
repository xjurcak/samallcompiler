package sk.small.compiler;

import sk.small.compiler.lexic.Lexicator;
import sk.small.compiler.lexic.Token;
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
            Token t;
            while ((t = lexicator.nextToken()) != null ){
                 //Log.d(LOGTAG, "Token recognized: " + t);
            }

        } catch (FileNotFoundException e) {
            Log.e(LOGTAG, e.getMessage());
        } catch (IOException e) {
            Log.e(LOGTAG, e.getMessage());
        }
    }
}
