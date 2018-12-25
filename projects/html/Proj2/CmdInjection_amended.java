
import java.io.*;

public class CmdInjection_amended {

    public static void main(String[] args)

            throws IOException {

        if (args.length != 1) {
            System.out.println("No arguments");
            System.exit(1);
        }

        // end IOException
        boolean varTest = false;
        String argString = null;
        Runtime runtime = Runtime.getRuntime();
        System.out.println("Length of args: " + args.length);
        System.out.println("args is: " + args[0]);
        System.out.println("argString is: " + argString);
        Integer toInt = Integer.parseInt(args[0]);
        System.out.println("thatat");
        Process proc = runtime.exec("ls");
        if (toInt == 1) {
            proc = runtime.exec("ls -l");

        } else if (toInt == 2) {
            proc = runtime.exec("ls -x");

        } else {
            System.out.println("Found incorrect input, either '1' or '2' ");
            System.exit(1);
        }
        System.out.println("we made it here..");
        InputStream is = proc.getInputStream();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);

        String line;
        while ((line = br.readLine()) != null) {
            System.out.println(line);
        }

        System.out.println("Hello");
        System.out.println("THere");
    }
} // end class: CmdInjection
