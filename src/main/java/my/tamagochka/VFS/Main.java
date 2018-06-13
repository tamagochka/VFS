package my.tamagochka.VFS;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        File s = new FileFS("test\\7.txt");
        if(s.move("6.txt", true))
            System.out.println("file was moved");
        else
            System.out.println("file was not moved");


/*
        File d = (File) s.copy("test\\6.txt", false);
        if(d == null)
            System.out.println("no copied!!!");
        else
            System.out.println(d.getAbsolutePath());
*/



    }
}
