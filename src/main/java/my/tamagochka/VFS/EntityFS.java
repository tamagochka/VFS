package my.tamagochka.VFS;

import java.io.File;
import java.io.IOException;

public abstract class EntityFS implements Entity {

    private File entity;

    public static boolean isDirectory(String path) {
        File entity = new File(path);
        return entity.isDirectory();
    }

    protected EntityFS(String path) {
        if(path == null) throw new NullPointerException();
        entity = new File(path);
    }

    protected File getEntity() {
        return entity;
    }

    @Override public abstract boolean create(boolean replace) throws IOException;
    @Override public abstract boolean delete();

    @Override public abstract Entity copy(Entity target, boolean replace) throws IOException;
    @Override public abstract Entity copy(String target, boolean replace) throws IOException;

    @Override public abstract Entity move(Entity target, boolean replace) throws IOException;
    @Override public abstract Entity move(String target, boolean replace) throws IOException;

    @Override public abstract Entity rename(Entity target, boolean replace) throws IOException;
    @Override public abstract Entity rename(String target, boolean replace) throws IOException;

    @Override public String getName() { return entity.getName(); }
    @Override public String getParent() { return entity.getParent() == null ? "" : entity.getParent(); }
    @Override public String getPath() { return entity.getPath(); }
    @Override public String getAbsolutePath() { return entity.getAbsolutePath(); }

    @Override public boolean isExist() { return entity != null && entity.exists(); }

    @Override public abstract long getSize();

    @Override public String toString() { return getPath(); }

}
