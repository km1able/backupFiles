import java.util.Arrays;
import java.security.MessageDigest;
import java.io.*; 

public class DataCrypt {
    public static void main(String args[]) throws Exception{
        
        if (args.length != 3) {
            System.out.println("Improper amount of input arguments"); 
            return; 
       }
      
        File input = new File ("userInput.txt"); 
        BufferedWriter writer = new BufferedWriter(new FileWriter(input)); 

        String[] inputArgs = args;
        for (int i = 0; i < args.length; i++) {
            System.out.println(args[i]);
        }
        for (int j = 0; j<args.length; j++) {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            // pass data to MessageDigestObject
            md.update(args[j].getBytes());
            // compute message digest
            byte[] digest = md.digest();
            String stringMe=""; 
            for (int i = 0; i<digest.length; i++) {
               // System.out.println(digest[i]); 
              //  stringMe += digest[i].toString(); 
                stringMe +=String.valueOf(digest[i]); 
            }
            stringMe+=';'; 
            System.out.println(stringMe); 
            writer.write(stringMe); 
           /*
            System.out.println("Hashed password in bytes: ");
            System.out.println(Arrays.toString(digest));
            */ 
        }
        writer.close(); 
        return; 
    }
    
}