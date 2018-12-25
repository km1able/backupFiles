import java.util.Arrays;
import java.security.MessageDigest;

//creates non-salted hash 

public class Salt_Brk {
    public static void main(String args[]) throws Exception {
        // Creating a KeyGenerator object

        MessageDigest md = MessageDigest.getInstance("MD5");
        // pass data to MessageDigestObject

        md.update(args[0].getBytes());
        // compute message digest
        byte[] digest = md.digest();
        System.out.println("Hashed password: ");
        // System.out.println(digest);
        System.out.println(Arrays.toString(digest));

    }
}