package my.tamagochka.VFS;

import java.io.IOException;

public abstract class EntityFS implements Entity {

    private String path;

    public abstract boolean create(boolean replace) throws IOException;
    public abstract boolean delete();
    public abstract boolean rename(String target, boolean replace) throws IOException;
    public abstract Entity copy(String target, boolean replace) throws IOException;
    public abstract boolean move(String target, boolean replace) throws IOException;
    public abstract long getSize();

    public EntityFS(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public boolean isExist() {
        java.io.File entity = new java.io.File(path);
        return entity.exists();
    }

    public String getAbsolutePath() {
        java.io.File entity = new java.io.File(path);
        return entity.getAbsolutePath();
    }

}
