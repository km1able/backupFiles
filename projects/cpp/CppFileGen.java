import java.io.*;

/**
 * This program generates CPP .cpp and .hpp files for generic classes Given
 * input class mame, creates Constructor (0 args), (1 arg) Destructor (0 args)
 * Copy Operator
 * 
 * .hpp format:
 * 
 * #ifndef #define {Name of Classhpp}
 * 
 * class {Name of Class} {
 * 
 * public
 * 
 * /constructors {Name of Class} (); {Name of Class} (const );
 * 
 * /destructor: ~{Name of Class}();
 * 
 * /copy constructor {Name of Class}( const {Name of Class}&);
 * 
 * }
 * 
 * #endif
 *
 */
public class CppFileGen {
    public static void main(String args[]) throws Exception {
        String nl = "\n";
        // String tl = ";";
        String fName = args[0];
        String l1 = "#ifndef" + nl + "#define " + fName + "hpp";
        String l2 = nl + nl;
        String l3 = "class " + fName + "{" + nl + nl;
        String l4 = "public: " + nl;
        String l5 = fName + "();" + nl;
        String l6 = fName + "(const );" + nl;
        String l7 = "~" + fName + "();" + nl;
        String l8 = fName + "( const " + fName + " & );" + nl;
        String l9 = nl + nl + nl + "};" + nl + nl + "#endif";
        String input = l1 + l2 + l3 + l4 + l5 + l6 + l7 + l8 + l9;
        String fileName = fName + ".hpp";

        File hpp = new File(fileName);
        BufferedWriter writer = new BufferedWriter(new FileWriter(hpp));
        writer.write(input);
        writer.close();
        String q = "\"";
        String c1 = "#include " + q + fName + ".hpp" + q + nl + nl;
        String c2 = fName + "::" + fName + "() {   } " + nl;
        String c3 = fName + "::" + fName + "(const ) {   } " + nl;
        String c4 = fName + "::" + fName + "(const " + fName + " & ) {  } " + nl;

        input = c1 + c2 + c3 + c4;
        fileName = fName + ".cpp";
        File cpp = new File(fileName);
        BufferedWriter writer1 = new BufferedWriter(new FileWriter(cpp));
        writer1.write(input);
        writer1.close();
    }
}
