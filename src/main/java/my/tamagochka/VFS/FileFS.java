package my.tamagochka.VFS;

import com.sun.istack.internal.NotNull;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class FileFS extends EntityFS implements File {

    public FileFS(String path) { super(path); }

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

    // сделать методы copy move и rename с параметром target типа Entity

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
    public boolean create(boolean replace) throws IOException {
        if(replace && isExist()) {
            if (!getEntity().delete()) return false;
        }
        return getEntity().createNewFile();
    }

    @Override
    public boolean delete() {
        if(isExist() && getEntity().delete()) {
            setPath(null);
            return true;
        }
        return false;
    }

    @Override
    public File copy(String target, boolean replace) throws IOException {
        if(!isExist()) return null;
        java.io.File sourceFile = getEntity();
        java.io.File targetCheck = new java.io.File(target);
        String targetPath = null;
        if(targetCheck.isDirectory())
            targetPath = target + java.io.File.separator + sourceFile.getName();
        else
            targetPath = target;
        java.io.File targetFile = new java.io.File(targetPath);
        if(!replace && targetFile.exists()) return null;
        Files.copy(sourceFile.toPath(), targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
        return new FileFS(targetPath);
    }

    public File copy(Entity target, boolean replace) {
        if(!isExist() || !target.isExist()) return null;
        if(target instanceof Directory) {




        }
        if(!replace && target.isExist()) return null;



        return null;
    }

    @Override
    public boolean move(String target, boolean replace) throws IOException{
        if(copy(target, replace) != null) {
            if(delete()) {
                setPath(target);
                return true;
            }
        } return false;
    }

    @Override
    public boolean rename(String target, boolean replace) throws IOException {
        java.io.File sourceFile = getEntity();
        return move(sourceFile.getParent() == null ? "" : sourceFile.getParent() + java.io.File.separator + target, replace);
    }

    @Override
    public Directory getDirectory() {
        return new DirectoryFS();
    }

    @Override
    public long getSize() {
        return getEntity().length();
    }

    @Override
    public String toString() {
        return getPath();
    }
}
