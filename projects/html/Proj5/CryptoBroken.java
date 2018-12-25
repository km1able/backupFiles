import java.util.Arrays;

public class CryptoBroken {
    public static void main(String args[]) throws Exception {
        // Creating a KeyGenerator object

        // Computing the Mac
        String inputArgs = args[0];
        byte[] inBytes = inputArgs.getBytes();
        for (int i = 0; i < inBytes.length; i++) {
            inBytes[i]++;
        }
        System.out.println("Input password is: " + args[0]);
        System.out.println("Self made crypto algorithm: ");
        System.out.println(Arrays.toString(inBytes));

    }

}
