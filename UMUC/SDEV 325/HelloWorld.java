import java.text.SimpleDateFormat; 
import java.util.Date; 
import java.io.*; 
import java.net.*; 



public class HelloWorld {
    public static void main (String[] args) {
        System.out.println("Hello, World"); 
        printFunction("My name is Dolly"); 
        addFunction(4, 5); 
        printTime(); 
        
        //try to open a socket
        Socket smtpSocket = null; 
        DataOutputStream os = null; 
        DataInputStream is = null; 
        try {
            System.out.println("Opening socket..."); 
            smtpSocket = new Socket("myHost", 25); 
            os = new DataOutputStream (smtpSocket.getOutputStream()); 
            is = new DataInputStream (smtpSocket.getInputStream()); 
            System.out.println("Socket opened on port 25"); 
        }
        catch (UnknownHostException e) {
            System.err.println("Error: Unknown Host"); 
        }
        catch (IOException e) {
            System.err.println("No I/O Connection"); 
        }
    }
    private static  void printFunction (String var) {
        System.out.println (var); 
    }
    
    private static void addFunction (int x, int y) {
        int thisVar = x+y; 
        System.out.println("The sum is: " + thisVar); 
    }
    private static void printTime () {
       java.util.Date date = new java.util.Date(); 
       System.out.println(date); 
    }
} //end class: HelloWorld


