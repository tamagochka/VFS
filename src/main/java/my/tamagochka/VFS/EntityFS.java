package my.tamagochka.VFS;

import java.io.File;
import java.io.IOException;

public abstract class EntityFS implements Entity {

    private String path;
    private File entity;

    @Override public abstract boolean create(boolean replace) throws IOException;
    @Override public abstract boolean delete();
    @Override public abstract boolean rename(String target, boolean replace) throws IOException;
    @Override public abstract Entity copy(String target, boolean replace) throws IOException;
    @Override public abstract boolean move(String target, boolean replace) throws IOException;
    @Override public abstract long getSize();

    protected EntityFS(String path) { setPath(path); }

    protected void setPath(String path) {
        this.path = path;
        if(path != null) entity = new File(path);
            else entity = null;
    }

    protected String getPath() {
        return path;
    }
    protected File getEntity() { return entity; }
    @Override public boolean isExist() { return entity != null && entity.exists(); }
    @Override public String getAbsolutePath() { return entity != null ? entity.getAbsolutePath() : null; }

}
