package sk.small.compiler.lexic;

import sk.small.compiler.errors.ErrorReporter;
import sk.small.compiler.errors.LexicException;
import sk.small.compiler.util.Log;

import java.io.IOException;
import java.io.InputStream;

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
    private ErrorReporter errorReporter;

    private int lineNumber = 1;
    private int lexemeLineStartIndex = 0;

    public int getLexemeLineEndIndex() {
        return charLinePositon - 1;
    }

    public int getLexemeLineStartIndex() {
        return lexemeLineStartIndex;
    }

    private int charLinePositon = 0;

    public Lexicator(InputStream is, ErrorReporter errorReporter) throws IOException {
        this.inputStream = is;
        this.errorReporter = errorReporter;

        buffer = new Buffer(inputStream);
        nextChar();
    }

    public Token nextToken() throws IOException{
        Log.d(LOGTAG, "nextToken() start. Lexeme start with character: " + charToString(chr));
        Token t = readToken();
        Log.d(LOGTAG, "nextToken() end. Token: " + t);
        return t;
    }

    public int getLineNumber() {
        return lineNumber;
    }

    private Token readToken() throws IOException{

        lexeme = new StringBuffer();

        if(chr == Buffer.EOF)
            return Word.EOF;

        //skip all empty characters
        if( chr == ' ' || chr == '\t' || chr == '\n' || chr == '\r'){
            if( chr == '\r'){
                lineNumber ++;
                charLinePositon = 0;
            }

            Log.d(LOGTAG, "skip char: " + charToString(chr));
            while ( nextChar() != -1) {
                if( chr == ' ' || chr == '\t' || chr == '\n' || chr == '\r')  {
                    Log.d(LOGTAG, "skip char: " + charToString(chr));
                    state = 0;
                    continue;
                } else {
                    break;
                }
            }
        }

        lexemeLineStartIndex = charLinePositon - 1;

        if ( chr == ';') {
            nextChar();
            return Word.STATEMENT_END;
        } else if ( chr == ',') {
            nextChar();
            return Word.COMMA;
        }else if ( chr == '(') {
            nextChar();
            return Word.LP;
        }else if ( chr == ')') {
            nextChar();
            return Word.RP;
        }else if ( chr == '+') {
            nextChar();
            return Word.PLUS;
        }else if ( chr == '-') {
            nextChar();
            return Word.MINUS;
        }else if ( chr == ':' ) {     // assigment :=
            nextChar();
            if( chr == '=' ) {
                nextChar();
                return Word.ASSIGN;
            } else {
                //rise error and return correct token
                errorReporter.reportError(new LexicException("Error: unknow token:  ':', correct token is ':='"));
                return new Token(TokenType.ASSIGN);
            }
        } else if (Character.isLetter(chr)){
            do{
                lexeme.append((char)chr);
                nextChar();
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
                nextChar();
            } while ( Character.isDigit(chr));

            if(Character.isLetter(chr)) { //also error not allowed 1111jjfh
                errorReporter.reportError(new LexicException("Error: unexpected end character for number: " + (char)chr ));
            }
            Log.d(LOGTAG, "End read number value: " + number);
            return new Number(number);
        } else{
                // error unknow word
                if ( chr == '=' ){
                    errorReporter.reportError(new LexicException("Error: unknow token: '=', missing ':'"));
                    nextChar();
                    return Word.ASSIGN;
                }

                Token t = new Token(TokenType.UNKNOWN);
                errorReporter.reportError(new LexicException("Error: unknow token: '" + (char)chr + "'"));

            nextChar();
            return t;
        }
    }

    private int nextChar() throws IOException {

        charLinePositon++;
        chr = buffer.readNext();
        return chr;
    }

    private String charToString(byte ch){
		
    	String str;
    	
    	switch (ch) {
		case ' ':
			str = "Space 32 ";
			break;
		case '\t':
			str = "TAB 9";
			break;
		case '\n':
			str = "LF 10";
			break;
		case '\r':
			str = "CR 13";
			break;

		default:
			str = "";
			break;
		}
    	
    	return str;
    	
    }
}
