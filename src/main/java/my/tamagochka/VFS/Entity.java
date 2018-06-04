package my.tamagochka.VFS;

public interface Entity {
    void create();
    void delete();
    void rename();
    void copy();
    void move();
    boolean isExist();
    String getAbsolutePath();
    long getSize();
}
