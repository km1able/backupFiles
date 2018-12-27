import javax.swing.JOptionPane;

/**
 * 
 * InvalidClassName exception. 
 * Generate when invalid class name 
 *
 */
public class InvalidClassName extends Exception{

			InvalidClassName (String msg ) {
				super(msg); 
				String thisID = "InvalidClassName Error";
				JOptionPane.showMessageDialog(null, thisID);
			} //end constructor:  MalformedFractionException 
		} //end class: InvalidClassName 
	

	

