package my.tamagochka.VFS;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        try {
            FileFS f = new FileFS("test" + java.io.File.separator + "1.txt");
            System.out.println("line: " + f.readLine(0));
        } catch (IOException e) {
            System.out.println("Exception!");
        }





    }
}
