import java.util.Arrays;
import java.security.MessageDigest;

public class CryptoWork {
    public static void main(String args[]) throws Exception {
        // Creating a KeyGenerator object

        // Computing the Mac
        String[] inputArgs = args;
        for (int i = 0; i < args.length; i++) {
            System.out.println(args[i]);
        }

        MessageDigest md = MessageDigest.getInstance("SHA-256");
        // pass data to MessageDigestObject
        md.update(args[0].getBytes());
        // compute message digest
        byte[] digest = md.digest();
        System.out.println("Hashed password in bytes: ");
        System.out.println(Arrays.toString(digest));
    }
}