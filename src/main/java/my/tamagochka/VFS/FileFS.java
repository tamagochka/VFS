package my.tamagochka.VFS;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class FileFS extends EntityFS implements File {

    public FileFS(String path) { super(path); }

    @Override
    public String readLine(int number) throws IOException {
        if(!isExist()) return null;
        InputStream is = new BufferedInputStream(new FileInputStream(getEntity()));
        byte buffer[] = new byte[1024];
        byte string[] = new byte[1048576];
        int lenString = 0;
        int scannedLines = 0;
        int readChars = 0;
        while((readChars = is.read(buffer)) != -1) {
            for(int i = 0; i < readChars; i++)
                if(buffer[i] == '\r') {
                    if(number == scannedLines)
                        return new String(string, 0, lenString);
                    else {
                        lenString = 0;
                        i++;
                        scannedLines++;
                    }
                } else {
                    string[lenString] = buffer[i];
                    lenString++;
                }
        }
        is.close();
        if(scannedLines == number) return new String(string, 0, lenString);
        return null;
    }

    private InputStream inputStream = null;
    private boolean EOFFlag = false;

    public void resetFilePosition() throws IOException {
        if(inputStream != null) inputStream.close();
        inputStream = new BufferedInputStream(new FileInputStream(getEntity()));
        EOFFlag = false;
    }

    public boolean EOF() { return EOFFlag; }

    @Override
    public String readLine() throws IOException {
        if(EOFFlag || !isExist()) return null;
        if(inputStream == null) resetFilePosition();
        byte string[] = new byte[1048576];
        int b = inputStream.read();
        int i = 0;
        while((b != '\r') && (b != -1)) {
            string[i] = (byte) b;
            b = inputStream.read();
            i++;
        }
        b = inputStream.read();
        if(b == -1) EOFFlag = true;
        return new String(string, 0, i);
    }

    public void seek(long position) throws IOException {
        resetFilePosition();
        inputStream.skip(position);
        if(inputStream.available() == 0) EOFFlag = true;
    }

    public void skip(long count) throws IOException {
        if(inputStream == null) resetFilePosition();
        inputStream.skip(count);
        if(inputStream.available() == 0) EOFFlag = true;
    }

    @Override
    public byte[] readBytes(int count) throws IOException {
        if(EOFFlag || !isExist()) return null;
        if(inputStream == null) resetFilePosition();
        byte bytes[] = new byte[count];
        if(inputStream.read(bytes) == -1) return null;
        if(inputStream.available() == 0) EOFFlag = true;
        return bytes;
    }

    @Override
    public void writeLine(String line) {

    }

    @Override
    public void writeBytes(byte[] bytes) {

    }

    @Override
    public long countLines() throws IOException {
        if(!isExist()) return 0;
        InputStream is = new BufferedInputStream(new FileInputStream(getEntity()));
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
    }

    @Override
    public Directory getDirectory() {
        return new DirectoryFS(getParent());
    }

    @Override
    public boolean create(boolean replace) throws IOException {
        if(replace && isExist()) {
            if(getEntity().isDirectory() || !delete()) return false;
        }
        return getEntity().createNewFile();
    }

    @Override
    public boolean delete() {
        return isExist() && !getEntity().isDirectory() && getEntity().delete();
    }

    @Override
    public File copy(Entity target, boolean replace) throws IOException{
        if(target == null) throw new NullPointerException();
        if(!isExist()) return null;
        File targetFile = target instanceof Directory ?
                new FileFS(target.getPath() + java.io.File.separator + getName()) :
                new FileFS(target.getPath());
        if(!replace && targetFile.isExist()) return null;
        Files.copy(getEntity().toPath(), ((FileFS) targetFile).getEntity().toPath(), StandardCopyOption.REPLACE_EXISTING);
        return targetFile;
    }

    @Override
    public File copy(String target, boolean replace) throws IOException {
        Entity targetEntity = EntityFS.isDirectory(target) ? new DirectoryFS(target) : new FileFS(target);
        return copy(targetEntity, replace);
    }

    @Override
    public Entity move(Entity target, boolean replace) throws IOException {
        return null;
    }

    @Override
    public Entity move(String target, boolean replace) throws IOException {
        File targetFile = copy(target, replace);
        if(targetFile != null && delete()) return targetFile;
        return null;
    }

    @Override
    public Entity rename(Entity target, boolean replace) throws IOException{
        return null;
    }

    @Override
    public Entity rename(String target, boolean replace) throws IOException {
        return move(getParent() + java.io.File.separator + target, replace);
    }

    @Override
    public long getSize() {
        return getEntity().length();
    }

    @Override
    public String toString() {
        return super.toString();
    }

}
