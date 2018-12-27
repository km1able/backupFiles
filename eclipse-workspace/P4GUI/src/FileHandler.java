import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.StringTokenizer;

import javax.swing.JOptionPane;

/**
 * 
 * Filehandler.java
 * Main file handling class. Opens file if possible based on input string value. 
 * Generates return list of strings (ArrayList*ArrayList<String>)
 *
 */
public class FileHandler {
	
	private String myFileName; 
	private File myFile; 
	private ArrayList<ArrayList<String>> fileContentsByLine;
	private boolean bSuccessful; 
	
	BufferedReader reader; 
	
	FileHandler(String inputFileName) {
		//myFile = new File ("/Users/mac/eclipse-workspace/P4GUI/src/myText"); 
		bSuccessful = false; 
		fileContentsByLine = new ArrayList<ArrayList<String>>(); 
		try {
			myFile = new File (inputFileName); 
				myFileName = inputFileName; 
			System.out.println("Entering filehandler...");
			//opens file and reads contents 
			if (myFile.exists()) {
				System.out.println("File: " + myFileName + " has been found");
			}
			if (myFile.isFile() ) {
				System.out.println("Is a file");
			}//end if...
			
			try {
				reader = new BufferedReader(new FileReader (myFile)); 
			}
			catch (IOException e) {
				JOptionPane.showMessageDialog(null, "File Did Not Open"); 
			}
			
			System.out.println("Printing file contents: -------");
			String line; 
			while ((line = reader.readLine()) != null) {
				System.out.println(line);
				StringTokenizer st = new StringTokenizer(line, " "); 
				ArrayList<String> tempList_Line = new ArrayList<> (); 
				while (st.hasMoreTokens()) 
					tempList_Line.add(st.nextToken()); 
				
				fileContentsByLine.add(tempList_Line); 
			
			} //end while
			System.out.println("File Contents printed....======="); 

		}//end try 
		
		catch (IOException e) {
			System.out.println("Invalid file entry....failure");
			bSuccessful = false; 
			return; 
		}//end catch 
		catch (NullPointerException nullPtrXpn) {
			bSuccessful = false; 
			return; 
		}
		bSuccessful = true; 
	} //end constructor: FileHandler (String) 

	public ArrayList<ArrayList<String>> getFile () {
		return fileContentsByLine; 
	}
	
	public boolean isSuccessful() {
		return bSuccessful; 
	}

} //end class: FileHandler
