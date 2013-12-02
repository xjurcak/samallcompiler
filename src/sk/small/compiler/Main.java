package sk.small.compiler;

import sk.small.compiler.errors.ErrorReporter;
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
            ErrorReporter errorReporter = new ErrorReporter();

            InputStream is = new FileInputStream("test\\example.small");
            Lexicator lexicator = new Lexicator(is, errorReporter);

            SyntaxAnalyzator analyzator = new SyntaxAnalyzator(lexicator, errorReporter);

            boolean result = analyzator.check();

            Log.d(LOGTAG, "<<<<<<<<<<<<<<< Error report >>>>>>>>>>>>>>>>.");
            Log.e(LOGTAG, errorReporter.getErrors());

            if(errorReporter.getErrors().isEmpty()) {
                Log.d(LOGTAG, "Compilation Success.");
            } else {
                Log.d(LOGTAG, "Compilation Failed. Errors count: " + errorReporter.getErrors().size());
            }

            Log.d(LOGTAG, "<<<<<<<<<<<<<<< Error report >>>>>>>>>>>>>>>>.");


        } catch (FileNotFoundException e) {
            Log.e(LOGTAG, e.getMessage());
        } catch (IOException e) {
            Log.e(LOGTAG, e.getMessage());
        }
    }
}
