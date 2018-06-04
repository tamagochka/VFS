package my.tamagochka.VFS;

public class FileFS implements File {

    private String path;

    public FileFS(String path) {
        this.path = path;
    }

    public FileFS() {
        this.path = null;
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
    public long countLines() {
        return 0;
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
    public boolean isExist() {
        return false;
    }

    @Override
    public String getAbsolutePath() {
        return null;
    }

    @Override
    public long getSize() {
        java.io.File file = new java.io.File(path);
        return file.length();
    }

    @Override
    public String toString() {
        return path;
    }
}
