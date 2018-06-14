package my.tamagochka.VFS;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

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
    }

    @Override
    public boolean create(boolean replace) throws IOException {
        java.io.File file = new java.io.File(super.getPath());
        if(replace && file.exists()) {
            if (!file.delete()) return false;
        }
        return file.createNewFile();
    }

    @Override
    public boolean delete() {
        java.io.File file = new java.io.File(super.getPath());
        if(file.delete()) {
            super.setPath(null);
            return true;
        }
        return false;
    }

    @Override
    public boolean rename(String target, boolean replace) throws IOException {
        java.io.File sourceFile = new java.io.File(super.getPath());
        return move(sourceFile.getParent() == null ? "" : sourceFile.getParent() + java.io.File.separator + target, replace);
    }

    @Override
    public File copy(String target, boolean replace) throws IOException {
        java.io.File sourceFile = new java.io.File(super.getPath());
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

    @Override
    public boolean move(String target, boolean replace) throws IOException{
        if(copy(target, replace) != null) {
            if(delete()) {
                super.setPath(target);
                return true;
            }
        } return false;
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
