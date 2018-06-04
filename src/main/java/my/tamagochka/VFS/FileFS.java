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
}
