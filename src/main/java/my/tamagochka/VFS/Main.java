package my.tamagochka.VFS;

import com.sun.org.apache.xerces.internal.impl.dv.util.HexBin;

import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        try {
            FileFS f = new FileFS("test" + java.io.File.separator + "1.txt");

            while(!f.EOF()) {
                f.skip(1);
                System.out.println(HexBin.encode(f.readBytes(1)));
            }




        } catch (IOException e) {
            System.out.println("Exception!");
        }





    }
}
