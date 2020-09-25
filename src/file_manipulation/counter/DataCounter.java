package file_manipulation.counter;

import java.io.*;

public abstract class DataCounter implements Closeable, Countable{
    protected static final int EOF = -1;
    protected static final char NONE = '\0';
    protected static final char SPACE = ' ';
    protected static final char NEWLINE = '\n';
    protected static final char RETURN = '\r';
    protected String VERBOSEMESSAGE = null;
    protected int counter = 0;
    private int size = 0;
    protected char outputChar;
    FileInputStream file;

    public DataCounter(){
        file = null;
        outputChar = NONE;
    }

    public DataCounter(File file) throws IOException{
        outputChar = NONE;
        this.file = new FileInputStream(file);
        this.size = this.file.available();
    }

    public void setFile(File file) throws IOException{
        this.file = new FileInputStream(file);
    }

    public void counter(char ch) throws IOException{
        this.outputChar = ch;
        count();
    }
    public void counter() throws IOException{
        this.outputChar = NONE;
        count();
    }

    protected abstract void count() throws IOException;

    public void counter(char character1, char character2, char character3) throws IOException{
        throw new UnsupportedOperationException();
    }

    public int getCounter(){
        return this.counter;
    }

    public int size(){
        return this.size;
    }

    public String getVerbose(){
        return this.VERBOSEMESSAGE;
    }

    public void setVerboseMessage(String str){
        CharSequence sformat = "%s";
        
        if(str.contains(sformat))
            this.VERBOSEMESSAGE = str;
        
    }

    public void close() throws IOException{
            file.close();
    }
    public void test(){
        System.out.println("Test for reflection super");
    }
}
