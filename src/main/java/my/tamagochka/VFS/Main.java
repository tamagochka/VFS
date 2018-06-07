package my.tamagochka.VFS;

public class Main {

    public static void main(String[] args) {
/*
        Filesystem fs = new Filesystem("test");
        System.out.println(fs.isExist("."));
        System.out.println(fs.isExist("123.txt"));
        System.out.println(fs.getAbsolutePath("."));
        Iterator<String> i = fs.getFileIterator(".", true);
        while(i.hasNext()) {
            System.out.println(i.next());
        }
*/
//        Directory dir = new DirectoryFS("test\\test_dir_3");
        Directory dir = new DirectoryFS("test");
        System.out.println(dir.listEntities());
        System.out.println(dir.listFiles());
        System.out.println(dir.listDirs());

        System.out.println("size dir: " + dir.getSize());

        System.out.println("iterator test:");
        for(Entity e : dir) {
            if(e instanceof File)
                System.out.println(e + " - file");
            else
                System.out.println(e + " - dirs");

        }

        File f = new FileFS("test\\12345.txt");
        System.out.println(f.getSize());








    }
}
