package sk.small.compiler.lexic;

import sk.small.compiler.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: xjurcako
 * Date: 11/1/13
 * Time: 10:37 PM
 */
public class Lexicator {

    private static final String LOGTAG = Lexicator.class.getSimpleName();

    private final InputStream inputStream;
    StringBuffer lexeme;
    private final Buffer buffer;
    private int state = 0;
    byte chr;

    public Lexicator(InputStream is) throws IOException {
        this.inputStream = is;
        buffer = new Buffer(inputStream);
        chr = buffer.readNext();
    }

    public Token nextToken() throws IOException{
        Log.d(LOGTAG, "nextToken() start. Lexeme start with character: " + (char)chr);
        Token t = readToken();
        Log.d(LOGTAG, "nextToken() end. Token: " + t);
        return t;
    }

    private Token readToken() throws IOException{

        lexeme = new StringBuffer();

        if(chr == Buffer.EOF)
            return null;

        //skip all empty characters
        if( chr == ' ' || chr == '\t' || chr == '\n'){
            Log.d(LOGTAG, "skip char: " + (char) chr);
            while ( (chr = buffer.readNext()) != -1) {
                if( chr == ' ' || chr == '\t' || chr == '\n')  {
                    Log.d(LOGTAG, "skip char: " + (char) chr);
                    state = 0;
                    continue;
                } else {
                    break;
                }
            }
        }

        if ( chr == ';') {
            chr = buffer.readNext();
            return Word.STATEMENT_END;
        } else if ( chr == ',') {
            chr = buffer.readNext();
            return Word.COMMA;
        }else if ( chr == '(') {
            chr = buffer.readNext();
            return Word.LP;
        }else if ( chr == ')') {
            chr = buffer.readNext();
            return Word.RP;
        }else if ( chr == '+') {
            chr = buffer.readNext();
            return Word.PLUS;
        }else if ( chr == '-') {
            chr = buffer.readNext();
            return Word.MINUS;
        }else if ( chr == ':' ) {     // assigment :=
            chr = buffer.readNext();
            if( chr == '=' ) {
                chr = buffer.readNext();
                return Word.ASSIGN;
            } else {
                Log.e(LOGTAG, "Error: unknow token:  ':'");
                return new Token(":");
            }
        } else if (Character.isLetter(chr)){
            do{
                lexeme.append((char)chr);
                chr = buffer.readNext();
            } while ( Character.isLetterOrDigit(chr) );

            String str = lexeme.toString();
            if( "BEGIN".equals(str))
                return Word.BEGIN;
            else if ( "END".equals(str) )
                return Word.END;
            else if ( "IF".equals(str) )
                return Word.IF;
            else if ( "THEN".equals(str) )
                return Word.THEN;
            else if ( "ELSE".equals(str) )
                return Word.ELSE;
            else if ( "READ".equals(str) )
                return Word.READ;
            else if ( "WRITE".equals(str) )
                return Word.WRITE;
            else if ( "OR".equals(str) )
                return Word.OR;
            else if ( "AND".equals(str) )
                return Word.AND;
            else if ( "TRUE".equals(str) )
                return Word.TRUE;
            else if ( "FALSE".equals(str) )
                return Word.FALSE;
            else if ( "NOT".equals(str) )
                return Word.NOT;
            //todo add lexeme to symbol table
            return new Id(1);
        }  else if( Character.isDigit(chr) && chr != '0' ){ //start read number
            Log.d(LOGTAG, "Starting read first char: " + (char)chr);

            int number = 0;

            do {
                number = number * 10 +  Character.digit(chr, 10);
                chr = buffer.readNext();
            } while ( Character.isDigit(chr));

            if(Character.isLetter(chr)) { //also error not allowed 1111jjfh
                Log.e(LOGTAG, "Error: unexpected end character for number: " + (char)chr);
            }
            Log.d(LOGTAG, "End read number value: " + number);
            return new Number(number);
        } else {
            // error unknow word
            Token t = new Token((char)chr);
            Log.e(LOGTAG, "Error: unknow token: " + t);
            chr = buffer.readNext();
            return t;
        }
    }
}
