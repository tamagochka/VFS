package my.tamagochka.VFS;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        File s = new FileFS("test\\1.txt");
        if(s.rename("3.txt", true)) {
            System.out.println("file was renamed");
            System.out.println(((FileFS) s).getPath());
        } else
            System.out.println("file was not renamed");


/*
        File d = (File) s.copy("test\\6.txt", false);
        if(d == null)
            System.out.println("no copied!!!");
        else
            System.out.println(d.getAbsolutePath());
*/



    }
}
