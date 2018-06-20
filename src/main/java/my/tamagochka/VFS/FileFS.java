package my.tamagochka.VFS;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class FileFS extends EntityFS implements File {

    public FileFS(String path) { super(path); }

    public String readLine(int number) throws IOException {
        if(!isExist()) return null;
        InputStream is = new BufferedInputStream(new FileInputStream(getPath()));
        byte buffer[] = new byte[1024];
        byte string[] = new byte[1048576];
        int lenString = 0;
        int scannedLines = 0;
        int readChars = 0;
        while((readChars = is.read(buffer)) != -1) {
            for(int i = 0; i < readChars; i++)
                if(buffer[i] == '\n') {
                    if(number == scannedLines)
                        return new String(string, 0, lenString);
                    else {
                        lenString = 0;
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

    @Override
    public String readLine() throws IOException {
        if(!isExist()) return null;
        InputStream inputStream = new BufferedInputStream(new FileInputStream(getPath()));
        byte string[] = new byte[1048576];




        int b = is.read();
        int i = 0;
        while(b != -1 && b != '\n') {
            buffer[i] = (byte) b;
            i++;
            b = is.read();
        }
        String str = new String(buffer, 0, i);
        System.out.println(str);
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
        if(!isExist()) return 0;
        InputStream is = new BufferedInputStream(new FileInputStream(getPath()));
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
