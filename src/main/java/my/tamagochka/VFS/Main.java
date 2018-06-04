package my.tamagochka.VFS;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

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

        java.io.File f = new File("test");
        File fs[] = f.listFiles();
        System.out.println(fs[1].getPath());
        System.out.println(fs[1].getName());







    }
}
