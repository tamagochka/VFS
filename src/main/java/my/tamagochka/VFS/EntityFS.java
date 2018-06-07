package my.tamagochka.VFS;

public abstract class EntityFS implements Entity {

    private String path;

    public abstract void create();
    public abstract void delete();
    public abstract void rename();
    public abstract void copy();
    public abstract void move();
    public abstract long getSize();

    public boolean isExist() {
        java.io.File entity = new java.io.File(path);
        return entity.exists();
    }

    public String getAbsolutePath() {
        java.io.File entity = new java.io.File(path);
        return entity.getAbsolutePath();
    }

}
