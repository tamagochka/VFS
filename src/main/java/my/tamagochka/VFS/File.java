package my.tamagochka.VFS;

import java.io.IOException;

public interface File extends Entity {
    String readLine();
    byte[] readBytes();
    void writeLine(String line);
    void writeBytes(byte bytes[]);
    long countLines() throws IOException;
}
