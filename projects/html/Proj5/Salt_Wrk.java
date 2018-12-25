import java.util.Arrays;
import java.security.MessageDigest;

public class Salt_Wrk {
    public static void main(String args[]) throws Exception {
        // Creating a KeyGenerator object

        MessageDigest md = MessageDigest.getInstance("MD5");
        // pass data to MessageDigestObject
        String salt = "DEEFXJjj2255552232223jalkjiKJJKKK";
        String updatePass = args[0] + salt;
        System.out.println(updatePass);
        md.update(args[0].getBytes());
        md.update(salt.getBytes());
        // compute message digest
        byte[] digest = md.digest();
        System.out.println("Salted Hash Password:  ");
        System.out.println(Arrays.toString(digest));
    }
}