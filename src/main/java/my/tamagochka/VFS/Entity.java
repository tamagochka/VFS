package my.tamagochka.VFS;

import java.io.IOException;

public interface Entity {
    boolean create(boolean replace) throws IOException;
    boolean delete();
    boolean rename(String target, boolean replace) throws IOException;
    Entity copy(String target, boolean replace) throws IOException;
    boolean move(String target, boolean replace) throws IOException;
    boolean isExist();
    String getAbsolutePath();
    long getSize();
}
