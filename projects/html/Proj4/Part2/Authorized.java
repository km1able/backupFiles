import java.io.*;

public class Authorized {
    public static void main(String args[]) throws Exception {

        String firstName = "Kelly";
        String lastName = "McCutchen";
        String dataVal = "password";
        String toReturn = "This user has been authorized...";
        String storedVal = "Sauron";
        boolean fName = false;
        boolean lName = false;
        boolean dVal = false;
        if (args[0].contains(firstName))
            fName = true;
        if (args[1].contains(lastName))
            lName = true;
        if (args[2].contains(dataVal))
            dVal = true;

        if (fName && lName && dVal) {
            System.out.println(toReturn);
            System.out.println("Stored value is: " + storedVal);
        } else
            System.out.println("User has not been authenticated, please enter correct information");
    }

}
