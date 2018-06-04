package my.tamagochka.VFS;

import java.io.FileFilter;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class DirectoryFS implements Directory {

    private String path;

    public DirectoryFS(String path) {
        this.path = path;
    }

    public DirectoryFS() {
        this.path = null;
    }

    public List<Directory> listDirs() {
        return null;
    }

    public List<File> listFiles() {
        java.io.File dir = new java.io.File(path);
        dir.listFiles(f -> {

            return false;
        });



        return null;
    }

    public List<Entity> listEntities() {
        java.io.File dir = new java.io.File(path);
        List<Entity> files = new LinkedList<>();
        for(java.io.File f : dir.listFiles()) {
            if(f.isDirectory())
                files.add(new DirectoryFS(f.getPath()));
            else
                files.add(new FileFS(f.getPath()));
        }
        return files;
    }

    public void create() {

    }

    public void delete() {

    }

    public void rename() {

    }

    public void copy() {

    }

    public void move() {

    }

    public boolean isExist() {
        java.io.File dir = new java.io.File(path);
        return dir.exists();
    }

    public String getAbsolutePath() {
        java.io.File dir = new java.io.File(path);
        return dir.getAbsolutePath();
    }

    public long getSize() {
        return 0;
    }

    private class EntitiesIterator implements Iterator<Entity> {

        public boolean hasNext() {
            return false;
        }

        public Entity next() {
            return null;
        }

        public void remove() {

        }
    }
}
