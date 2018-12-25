import java.util.Arrays;
import java.security.MessageDigest;
import java.io.*;

public class DataUncrypt {
    public static void main(String args[]) throws Exception {

        if (args.length != 3) {
            System.out.println("Improper amount of input arguments");
            return;
        }

        File input = new File("userInput_uncrypt.txt");
        BufferedWriter writer = new BufferedWriter(new FileWriter(input));

        String stringMe = "";
        for (int i = 0; i < args.length; i++) {
            System.out.println(args[i]);
            stringMe += args[i];
            stringMe += ';';
        }
        System.out.println(stringMe);
        writer.write(stringMe);

        writer.close();
        return;
    }

}