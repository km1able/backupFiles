import javax.swing.JOptionPane;
/**
 * Class for the detection of cycles. Generates error message 
 * 
 *
 */
public class CycleDetectedException extends Exception{
	CycleDetectedException (String msg ) {
		super(msg); 
		String thisID = "Cycle Detected ";
		JOptionPane.showMessageDialog(null, thisID + msg);
	} //end constructor:  MalformedFractionException 
} //end class: InvalidClassName 





