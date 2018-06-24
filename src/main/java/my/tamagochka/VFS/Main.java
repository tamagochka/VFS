package my.tamagochka.VFS;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        try {
            FileFS f = new FileFS("test" + java.io.File.separator + "8.txt");

            while(!f.EOF()) {
                System.out.println("'" + f.readLine() + "'");
            }
            f.resetFilePos();

            while(!f.EOF()) {
                System.out.println("'" + f.readLine() + "'");
            }



/*
            System.out.println("line: '" + f.readLine() + "'");
            System.out.println("line: '" + f.readLine() + "'");
            System.out.println("line: '" + f.readLine() + "'");
*/


/*
            System.out.println("line: '" + f.readLine(0)+ "'");
            System.out.println("line: '" + f.readLine(1)+ "'");
            System.out.println("line: '" + f.readLine(2)+ "'");
*/


        } catch (IOException e) {
            System.out.println("Exception!");
        }





    }
}
