package my.tamagochka.VFS;

import java.io.IOException;

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

        File f = new FileFS("test\\2.txt");
        System.out.println("file size: " + f.getSize());


        try {
            long average = 0;

            for(int i = 0; i < 100; i++) {

                long start = System.nanoTime();
                System.out.println(f.countLines());
                long end = System.nanoTime();
                average += (end - start);
            }
            System.out.println("average time to count: " + (average / 100));
        } catch(IOException e) {
            e.printStackTrace();
        }








    }
}
