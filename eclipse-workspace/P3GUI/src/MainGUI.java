import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.StringTokenizer;

import javax.swing.*;

 /** Main GUI aspect of program. Generates GUI. 
  *  Handles entry into tree generation class 
  *  As well as logic processing for list generation/sort and tokenization/error handling
  *  */ 
public class MainGUI extends JFrame implements ActionListener {

	//GUI variables
	private JPanel input; 
	private JPanel output; 
	private JPanel sortPanel; 
	//private JPanel typeSelection; 
	private JPanel button; 
	private JPanel numericPanel; 
	
	private JLabel inDLabel; 
	private JLabel outDLabel; 
	
	private JTextField userIn;
	private JTextField userOut; 
	
	private JRadioButton sortOrderAscend;
	private JRadioButton sortOrderDescend; 
	private JRadioButton numericTypeInt;
	private JRadioButton numericTypeFraction; 
	
	private  ButtonGroup sortOrderBGroup; 
	private  ButtonGroup numericTypeBGroup; 
	
	private JLabel sortLabel; 
	private JLabel numericLabel; 
	
	//Constructor
	MainGUI () {
		super ("BST Gen"); 
		input = new JPanel (new FlowLayout()); 
		output= new JPanel (new FlowLayout()); 
		button = new JPanel (new FlowLayout()); 
		sortPanel = new JPanel (new GridLayout(3,1)); 
		numericPanel = new JPanel (new GridLayout(3,1)); 
		//typeSelection = new JPanel (new FlowLayout(); )
		
		sortOrderBGroup = new ButtonGroup(); 
		numericTypeBGroup = new ButtonGroup(); 
		sortOrderAscend = new JRadioButton ("Ascending");
		sortOrderDescend = new JRadioButton ("Descending"); 
		
		numericTypeInt = new JRadioButton ("Integer");
		numericTypeFraction = new JRadioButton ("Fraction"); 
		
		setLayout(new FlowLayout()); 
		setSize (400, 300); 
		setDefaultCloseOperation (EXIT_ON_CLOSE); 
		
		userIn = new JTextField (25); 
		userOut = new JTextField (25); 
		userOut.setEditable(false);
		
		inDLabel = new JLabel ("Original List", SwingConstants.LEFT); 
		outDLabel = new JLabel ("Sorted List", SwingConstants.LEFT); 
		
		sortLabel = new JLabel ("Sort Order"); 
		numericLabel = new JLabel ("Numeric Type"); 
		
		add (input); 
		add (output); 
		add (button); 
		add (sortPanel); 
		add (numericPanel); 
		
		sortOrderBGroup.add(sortOrderAscend);
		sortOrderBGroup.add(sortOrderDescend); 
		
		numericTypeBGroup.add(numericTypeInt);
		numericTypeBGroup.add(numericTypeFraction);
		
		numericTypeInt.setSelected(true);
		sortOrderAscend.setSelected(true); 
		
		input.add(inDLabel); 
		input.add(userIn); 
		//input.add(evalButton); 
		
		output.add(outDLabel); 
		output.add(userOut); 
		
		button.add(evalButton); 
		
		sortPanel.add(sortLabel);
		sortPanel.add(sortOrderAscend); 
		sortPanel.add(sortOrderDescend);
		
		numericPanel.add(numericLabel);
		numericPanel.add(numericTypeInt);
		numericPanel.add(numericTypeFraction);
		//action button listener
		
		evalButton.addActionListener(this); 
		setVisible(true); 
	} //end constructor: MainGUI () 
	
	//evalButton 
	private JButton evalButton = new JButton (new AbstractAction ("Perform Sort") {
		@Override
		public void actionPerformed (ActionEvent generateTree) {
			
			String myInput = userIn.getText(); 
			String myOutput= ""; 
			boolean isAscend = sortOrderAscend.isSelected(); 
			boolean isNumericInt = numericTypeInt.isSelected(); 
			 
			//main logic 
			//cases are for fractional input or integer
			//ascending or descending --> handle fractional/int first, determine valid
			if (isNumericInt) {
				ArrayList <Integer> list = new ArrayList<> (); 
				StringTokenizer st = new StringTokenizer(myInput, " "); 
				while (st.hasMoreTokens()) {
					try {
						Integer tempInt = Integer.parseInt(st.nextToken()); 
						list.add(tempInt); 
					} //end: try 
					catch (NumberFormatException nmbFmtXpn){
						JOptionPane.showMessageDialog(null, "NumberFormatException");
					}//end: catch 
				}//end while myStrings.length 
				
				//code for tree generation dependent on ascending/descending settings 
				//execute for Integer selected
				if (isAscend) {
					myOutput = evaluateList(list, true); 
				}
				else {
					myOutput = evaluateList(list, false); 
				}
			} //end if: isNumericInt
			
			//if JRadioButton is not selected for Integer, init Fractional portion
			else {
				String thisToken; 
				boolean atNumerator = true; 
				boolean completed = false; 
				ArrayList <Fraction> list = new ArrayList<> (); 
				Integer numerator =0, denominator=1; 
				for (String ret: myInput.split(" ")) {
					atNumerator = true; 
					completed = false; 
					//System.out.println("Printing  value..." + ret); 
					StringTokenizer st = new StringTokenizer(ret, "/"); 
					//if more than required tokens, throw MalformedFractionException 
					if (st.countTokens() != 2) {
						try {
							throw new MalformedFractionException("");
						} catch (MalformedFractionException e) {
							System.out.println("Error in formatted fraction");
						} //end: catch 
						
					} //end if (st.countTokens() !=2) 
					else { //if tokenCount is 2 then process fractions 
					while (st.hasMoreTokens()) {
						thisToken = st.nextToken(); 
						//parseInt(String) throws -> numberFormatException 
						try {
							Integer myInt = Integer.parseInt(thisToken); 
							if (atNumerator == true) {
								numerator = myInt; 
								atNumerator = false; 
							}
							else {
								denominator = myInt; 
								completed = true; 
							} //end: else
								
						}//end: try 
						catch (NumberFormatException nmbFmtXpn) {
							JOptionPane.showMessageDialog(null, "NumberFormatException at: " + thisToken);
							} //end: catch 
						
						if (completed) {
							Fraction tempFraction = new Fraction(numerator, denominator); 
							list.add(tempFraction); 
						} //end (if completed) 
					} //end: while (st.hasMoreTokens()
					
					//code for tree generation dependent on ascending/descending 
					if (isAscend) {
						myOutput = evaluateList(list, true); 
					}
					else {
						myOutput = evaluateList(list, false); 
					}//end else 
				}//end else 
			}//end: for String ret:
			
				
		} //end: else (isNumericInt) 
			String myInputString = userIn.getText(); 
			System.out.println(myInputString); 
			
			System.out.println("My output text is: "); 
			System.out.println(myOutput);
			userOut.setText(myOutput);
	} //end actionPerformed
		}); //end new JButton

	@Override
	public void actionPerformed(ActionEvent e) {	
	}
	
	//generic function --> 
	//given input <>ArrayList, iterate tree 
	private <T extends Comparable <T>> String evaluateList(ArrayList<T> thisList, boolean doAscend) {
		GenericBinaryTree myTree = new GenericBinaryTree(); 
		String returnVal; 
		Iterator iter = thisList.iterator(); 
		while (iter.hasNext()) {
			myTree.insert((Comparable) iter.next());
		}
		if (doAscend) 
			returnVal = myTree.inorderAscending(); 
		else 
			returnVal = myTree.inorderDescending(); 
		
		return returnVal; 
	}
	
	//custom MalformedFractionException 
	public class MalformedFractionException extends Exception {

		String thisID = "MalformedFractionException"; 
		public MalformedFractionException (String msg) {
			super(msg); 
			JOptionPane.showMessageDialog(null, thisID + msg);
		} //end constructor:  MalformedFractionException 
	} //end class: MalformedFractionException

}//end class: MainGUI