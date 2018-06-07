package my.tamagochka.VFS;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

public class FileFS extends EntityFS implements File {

    public FileFS(String path) { super(path); }

    public FileFS() {
        super(null);
    }

    @Override
    public String readLine() {
        return null;
    }

    @Override
    public byte[] readBytes() {
        return new byte[0];
    }

    @Override
    public void writeLine(String line) {

    }

    @Override
    public void writeBytes(byte[] bytes) {

    }

    @Override
    public long countLines() throws IOException {
        InputStream is = new BufferedInputStream(new FileInputStream(super.getPath()));
        byte c[] = new byte[1024];
        int count = 0;
        int readChars = 0;
        boolean endsWithoutNewline = false;
        while((readChars = is.read(c)) != -1) {
            for(int i = 0; i < readChars; i++) {
                if(c[i] == '\n')
                    count++;
            }
            endsWithoutNewline = (c[readChars - 1] != '\n');
        }
        if(endsWithoutNewline) count++;
        is.close();
        return count;

/*      // about 1 million nanosecond
        java.io.File f = new java.io.File(super.getPath());
        Path p = f.toPath();
        Stream<String> lines = Files.lines(p, Charset.defaultCharset());
        long numOfLines = lines.count();
        return numOfLines;
*/
    }

    @Override
    public void create() {

    }

    @Override
    public void delete() {

    }

    @Override
    public void rename() {

    }

    @Override
    public void copy() {

    }

    @Override
    public void move() {

    }

    @Override
    public long getSize() {
        java.io.File file = new java.io.File(super.getPath());
        return file.length();
    }

    @Override
    public String toString() {
        return super.getPath();
    }
}
