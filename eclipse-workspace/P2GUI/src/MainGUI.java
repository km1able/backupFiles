import java.awt.*;
import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;

import javax.swing.*;

 /** Main GUI aspect of program. Handles entry into tree generation class */ 
public class MainGUI extends JFrame implements ActionListener {

	//GUI variables
	private JPanel input; 
	private JPanel output; 
	
	private JLabel inDLabel; 
	private JLabel outDLabel; 
	
	private JTextField userIn;
	private JTextField userOut; 
	
	//Constructor
	MainGUI () {
		super ("Tree Gen"); 
		input = new JPanel (new FlowLayout()); 
		output= new JPanel (new FlowLayout()); 
		
		setLayout(new GridLayout(2,1)); 
		setSize (350, 300); 
		setDefaultCloseOperation (EXIT_ON_CLOSE); 
		
		userIn = new JTextField (25); 
		userOut = new JTextField (28); 
		userOut.setEditable(false);
		
		inDLabel = new JLabel ("Enter Postfix Expression"); 
		outDLabel = new JLabel ("Infix Expression: ", SwingConstants.LEFT); 
		
		add (input); 
		add (output); 
		
		input.add(inDLabel); 
		input.add(userIn); 
		input.add(evalButton); 
		
		output.add(outDLabel); 
		output.add(userOut); 
		
		//action button listener
		
		evalButton.addActionListener(this); 
		setVisible(true); 
	} //end constructor: MainGUI () 
	
	//evalButton 
	private JButton evalButton = new JButton (new AbstractAction ("Generate Tree") {
		@Override
		public void actionPerformed (ActionEvent generateTree) {
		
		  //generate tree here
		  //  String toEvaluate = "2 3 4 + +";
		  //  String toEvaluate = " 1 2 - 5 -6 5 / +";
		    String toEvaluate = userIn.getText(); 
		    //generate char array from input string and remove white space
		    ArrayList<Character> chars = new ArrayList<Character>(); 
		     
		    for (char element: toEvaluate.toCharArray()) {
		    	if (element != ' ')  
		    		//add element to char array
		    		chars.add(element); 
		    } //end: for char element 	 
		    		
		    for (int i=0; i<chars.size(); i++) 
		    	System.out.println(chars.get(i));
		    try { 
		    	PostToInfixTree myTree = new PostToInfixTree(); 
		  
			    Node root =  myTree.makeTree(chars);
			   
			    System.out.println("Value is " + root.evaluate());
			    System.out.println(root.preOrderWalk()); 
			    System.out.println(root.inOrderWalk());
			    System.out.println(root.postOrderWalk());
			    
			    System.out.println("-------Encoding-----");
			    System.out.println(root.encode(0)); 
			    String inOrder = root.inOrderWalk(); 
			    userOut.setText(inOrder); 
	  		} //end try 
		   	
			catch (InvalidTokenException e) {
		   		e.printStackTrace();
			   	} //end catch 
		   			
	} //end actionPerformed
		}); //end new JButton

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}//end class: MainGUI
