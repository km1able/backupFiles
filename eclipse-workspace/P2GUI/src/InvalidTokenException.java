import javax.swing.JOptionPane;
/** Extends exception. Handles incorrect values in input text */ 
public class InvalidTokenException extends Exception {

		public InvalidTokenException (String msg) {
			super(msg); 
			JOptionPane.showMessageDialog(null, msg);
		}
}
