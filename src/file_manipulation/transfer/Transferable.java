package file_manipulation.transfer;

import java.io.IOException;

@FunctionalInterface
public interface Transferable {
    public abstract void transfer(int ch) throws IOException;
}
