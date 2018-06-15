package my.tamagochka.VFS;

import java.io.IOException;

public interface Entity {

    boolean create(boolean replace) throws IOException;
    boolean delete();

    Entity copy(Entity target, boolean replace) throws IOException;
    Entity copy(String target, boolean replace) throws IOException;

    Entity move(Entity target, boolean replace) throws IOException;
    Entity move(String target, boolean replace) throws IOException;

    Entity rename(Entity target, boolean replace) throws IOException;
    Entity rename(String target, boolean replace) throws IOException;

    String getName();
    String getParent();
    String getPath();
    String getAbsolutePath();

    boolean isExist();

    long getSize();

}
