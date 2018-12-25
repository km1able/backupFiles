
import java.io.*;

public class CmdInjection {

    public static void main(String[] args)

            throws IOException {
/*
        if (args.length != 1) {
            System.out.println("No arguments");
            System.exit(1);
        }

*/ 
        // end IOException
        Runtime runtime = Runtime.getRuntime();
        Process proc = runtime.exec(args);

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
