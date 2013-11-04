package sk.small.compiler.lexic;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Iterator;

/**
 * Created with IntelliJ IDEA.
 * User: Ondrej Jurcak (xjurcak)
 * Date: 11/2/13
 * Time: 11:09 PM
 */
public class Buffer{

    private static final int BUFFER_LENGTH = 10;
    public static final int EOF = -1;

    private byte[] buffer_one = null;

    private int forward = 0;

    private final InputStream inputStream;

    public Buffer(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    private boolean readNextBuffer(byte[] buffer) throws IOException {
        if(inputStream == null)
            throw new NullPointerException("InputStream doesn't set");

        int i = inputStream.read(buffer);
        if(i != -1 && i < buffer.length)
            buffer[i] = EOF;
        return i > 0;
    }

    public byte readNext() throws IOException {
        if(buffer_one == null){
            buffer_one = new byte[BUFFER_LENGTH];
            if(!readNextBuffer(buffer_one))
                return EOF;
            forward = 0;
        }

        if(forward >= buffer_one.length){
            //we reach end of first buffer so we need load next
            if(!readNextBuffer(buffer_one))
                return EOF;
            forward = 0;
        }

        return buffer_one[forward++]; //increas forward after read
    }
}
