package file_manipulation;

import java.io.*;

import file_manipulation.exception.InvalidArgumentUtil;

//Abstract super class for all file operations
public abstract class FileUtil {
    protected File srcPath = null;
    protected File destPath = null;
    protected String optionnal = null;
    protected boolean VERBOSE = false;
    protected String VERBOSEMESSAGE = null;
    protected Administrator arguments;
    protected int counter = 0;

    protected static final String FILESOURCE = "source file";
    protected static final String DESTSOURCE = "destination file";

    public FileUtil(){
        arguments = null;
    }
    public boolean isValid(){
        if (this.srcPath != null)
            return this.srcPath.canRead();
        
        return false;
    }

    // Parse arguments 
    protected void processArguments(int numberOfArguments){
        optionnal = arguments.getOptions();
       try{
            if(arguments.isValid(1, numberOfArguments))
                this.assignFileAttributes();
            else
                throw this.throwInvalidArgument();

        }
        catch(InvalidArgumentUtil iau){
            iau.printError();
        }
    }

    protected abstract InvalidArgumentUtil throwInvalidArgument();

    private void assignFileAttributes(){
        if(arguments.argumentSize() == 1){
            srcPath = new File(arguments.getArguments().get(0));
        }
        if(arguments.argumentSize() == 2){
            srcPath = new File(arguments.getArguments().get(0));
            destPath = new File(arguments.getArguments().get(1));
        }
    }

    protected void execOptions() throws InvalidArgumentUtil{  
        if(optionnal == null)    
            return;
    
        if(OPTIONS.HELP.contains(optionnal))
            OPTIONS.HELP.printHelper(FILESOURCE);
        
        else if(OPTIONS.BANNER.contains(optionnal))
            OPTIONS.BANNER.printBanner(this.getClass().getName());

        else if(OPTIONS.VERBOSE.contains(optionnal))
            this.VERBOSE = true;
    
        else
            throw new InvalidArgumentUtil("Invalid operand", OPTIONS.HELP.usage(FILESOURCE));
    }

    public abstract int execute() throws IOException;
    
    public int getCounter(){
        return this.counter;
    }

    public void setVerboseMessage(String str){
        CharSequence sformat = "%s";
        
        if(str.contains(sformat))
            this.VERBOSEMESSAGE = str;
        
    }
}
