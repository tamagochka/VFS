package my.tamagochka.VFS;

import java.util.Iterator;
import java.util.List;

public interface Directory extends Entity, Iterable<Entity> {
    List<Directory> listDirs();
    List<File> listFiles();
    List<Entity> listEntities();
    Iterator<Entity> iterator();
}
