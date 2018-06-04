package my.tamagochka.VFS;

import java.util.*;

public class DirectoryFS implements Directory {

    private String path;

    public DirectoryFS(String path) {
        this.path = path;
    }

    public DirectoryFS() {
        this.path = null;
    }

    @Override
    public List<Directory> listDirs() {
        java.io.File dir = new java.io.File(path);
        List<Directory> dirs = new LinkedList<>();
        Arrays.asList(dir.listFiles(pathname -> pathname.isDirectory())).forEach(f -> dirs.add(new DirectoryFS(f.getPath())));
        return dirs;
    }

    @Override
    public List<File> listFiles() {
        java.io.File dir = new java.io.File(path);
        List<File> files = new LinkedList<>();
        Arrays.asList(dir.listFiles(pathname -> !pathname.isDirectory())).forEach(f -> files.add(new FileFS(f.getPath())));
        return files;
    }

    @Override
    public List<Entity> listEntities() {
        List<Entity> e = new LinkedList<>();
        e.addAll(listDirs());
        e.addAll(listFiles());
        return e;
    }

    @Override
    public void create() {

    }

    @Override
    public void delete() {

    }

    @Override
    public void rename() {

    }

    @Override
    public void copy() {

    }

    @Override
    public void move() {

    }

    @Override
    public boolean isExist() {
        java.io.File dir = new java.io.File(path);
        return dir.exists();
    }

    @Override
    public String getAbsolutePath() {
        java.io.File dir = new java.io.File(path);
        return dir.getAbsolutePath();
    }

    @Override
    public long getSize() {
        return 0;
    }

    @Override
    public String toString() {
        return path;
    }

    @Override
    public Iterator<Entity> iterator() {
        return new EntitiesIterator(true, true, true);
    }

    private class EntitiesIterator implements Iterator<Entity> {

        private Queue<Entity> entities = new LinkedList<>();
        boolean iterateFiles = true, iterateDirs = true, iterateSubdirs = true;

        public EntitiesIterator(boolean iterateFiles, boolean iterateDirs, boolean iterateSubdirs) {
            this.iterateFiles = iterateFiles;
            this.iterateDirs = iterateDirs;
            this.iterateSubdirs = iterateSubdirs;
            entities.addAll(listDirs());
            if(iterateFiles) entities.addAll(listFiles());
        }

        public boolean hasNext() {
            if(!(iterateDirs || iterateFiles))
                return false;
            else
                return !entities.isEmpty();
        }

        public Entity next() {
            Entity entity = null;
            do {
                entity = entities.poll();
                if(entity instanceof Directory && iterateSubdirs) {
                    entities.addAll(((Directory) entity).listDirs());
                    if(iterateFiles) entities.addAll(((Directory) entity).listFiles());
                }
//                if(entity instanceof  Directory && !iterateDirs) continue;
            } while(!(entity instanceof File) && !iterateDirs && !entities.isEmpty());
            return entity;
        }

        public void remove() {

        }
    }
}
