package my.tamagochka.VFS;

import java.util.*;

public class DirectoryFS extends EntityFS implements Directory {

    public DirectoryFS(String path) { super(path); }

    @Override
    public List<Directory> listDirs() {
        java.io.File dir = new java.io.File(super.getPath());
        List<Directory> dirs = new LinkedList<>();
        Arrays.asList(dir.listFiles(pathname -> pathname.isDirectory())).forEach(f -> dirs.add(new DirectoryFS(f.getPath())));
        return dirs;
    }

    @Override
    public List<File> listFiles() {
        java.io.File dir = new java.io.File(super.getPath());
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
    public boolean create(boolean replace) {
        return false;
    }

    @Override
    public boolean delete() {
        return false;
    }

    @Override
    public Directory copy(Entity target, boolean replace) {
        return null;
    }

    @Override
    public Directory copy(String target, boolean replace) {
        return null;
    }

    @Override
    public Directory move(Entity target, boolean replace) {
        return null;
    }

    @Override
    public Directory move(String target, boolean replace) {
        return null;
    }

    @Override
    public Directory rename(Entity newName, boolean replace) {
        return null;
    }

    @Override
    public Directory rename(String newName, boolean replace) {
        return null;
    }

    @Override
    public long getSize() {
        long size = 0;
        for(Iterator<Entity> i = getFileIterator(true, false, true); i.hasNext();) {
            File file = (File) i.next();
            size = size + file.getSize();
        }
        return size;
    }

    public Iterator<Entity> getFileIterator(boolean iterateFiles, boolean iterateDirs, boolean iterateSubdirs) {
        return new EntitiesIterator(iterateFiles, iterateDirs, iterateSubdirs);
    }

    @Override
    public String toString() {
        return super.getPath();
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
            } while(!(entity instanceof File) && !iterateDirs && !entities.isEmpty());
            return entity;
        }

        public void remove() {








        }
    }
}
