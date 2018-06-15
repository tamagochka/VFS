package my.tamagochka.VFS;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class FileFS extends EntityFS implements File {

    public FileFS(String path) { super(path); }

    @Override
    public String readLine() throws IOException {
        if(!isExist()) return null;
        InputStream is = new BufferedInputStream(new FileInputStream(getPath()));
        char c[] = new char[1024];
        int readChars = 0;
        StringBuilder str = new StringBuilder();
        while((readChars = is.read(c)) != -1) {
            int i = 0;
            while(i < readChars && c[i] != '\n') {
                str.append(c[i]);
                i++;
            }
            if(c[i] == '\n') break;
        }
        System.out.println(str.toString());


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
