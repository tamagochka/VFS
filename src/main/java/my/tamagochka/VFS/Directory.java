package my.tamagochka.VFS;

import java.util.List;

public interface Directory extends Entity {
    List<Directory> listDirs();
    List<File> listFiles();
    List<Entity> listEntities();
}
