package my.tamagochka.VFS;

import java.util.Iterator;
import java.util.List;

public interface Directory extends Entity, Iterable<Entity> {
    List listDirs();
    List listFiles();
    List<Entity> listEntities();
    Iterator<Entity> iterator();
}
