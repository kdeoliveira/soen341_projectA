package file_manipulation.counter;

import java.io.*;

import file_manipulation.Data;

public abstract class DataCounter extends Data implements Countable{
    protected static final char NONE = '\0';
    protected static final char SPACE = ' ';
    protected static final char NEWLINE = '\n';
    protected static final char RETURN = '\r';

    protected String VERBOSEMESSAGE = null;

    public DataCounter(){
        super();
    }

    public DataCounter(File file) throws IOException{
        super(file);
    }

    protected abstract void setDefaultOutput();
    protected abstract void setOutput(char[] c);

    protected void fork() throws IOException{
        if(this.flagVerbose)    this.setDefaultOutput();
        count(this.file.read());
    }

    @Override
    public int[] getCounter(){
        return this.counter;
    }

    public String getVerbose(){
        return this.VERBOSEMESSAGE;
    }

    public void setVerboseMessage(String str){
        CharSequence sformat = "%s";
        if(str.contains(sformat))       this.VERBOSEMESSAGE = str;
    }

}
