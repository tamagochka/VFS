package my.tamagochka.VFS;

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
    public long getSize() {
        java.io.File file = new java.io.File(super.getPath());
        return file.length();
    }

    @Override
    public String toString() {
        return super.getPath();
    }
}
