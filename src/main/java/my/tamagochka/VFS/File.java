package my.tamagochka.VFS;

import java.io.IOException;

public interface File extends Entity {

    String readLine() throws IOException;
    String readLine(int number) throws IOException;

    byte[] readBytes(int count) throws IOException;

    void writeLine(String line);
    void writeBytes(byte bytes[]);

    long countLines() throws IOException;

    Directory getDirectory();

}
