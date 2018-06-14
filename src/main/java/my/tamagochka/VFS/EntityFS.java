package my.tamagochka.VFS;

import java.io.File;
import java.io.IOException;

public abstract class EntityFS implements Entity {

    private String path;
    private File entity;

    public abstract boolean create(boolean replace) throws IOException;
    public abstract boolean delete();
    public abstract boolean rename(String target, boolean replace) throws IOException;
    public abstract Entity copy(String target, boolean replace) throws IOException;
    public abstract boolean move(String target, boolean replace) throws IOException;
    public abstract long getSize();

    EntityFS(String path) { setPath(path); }

    void setPath(String path) {
        this.path = path;
        if(path != null) entity = new File(path);
    }

    String getPath() {
        return path;
    }
    File getEntity() { return entity; }
    public boolean isExist() { return entity != null && entity.exists(); }
    public String getAbsolutePath() { return entity != null ? entity.getAbsolutePath() : null; }

}
