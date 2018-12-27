import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EmptyStackException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

import javax.swing.*;





//import GUI.JBox;
//import GUI.MyButton;


public class P1GUI extends JFrame {
/*
 * P1GUI 
 * 
 * main container class
 * establishes panels and frames as well as button components for GUI utlity structure
 * also houses InfixEval class for algorithm development 
 */
	private static final int WIDTH = 400, HEIGHT = 450; 

	//main programmatic function
	//creates new instance of GUI
	public static void main(String[] args) 
	{
		P1GUI mainGUI = new P1GUI("Project 1");
	}
	
	//GUI class constructor
	P1GUI (String title) 
	{	
		super(title); 
		
		//main panel for hierarchical attachment 
		JPanel mainPanel = new JPanel(); 
		mainPanel.setSize(WIDTH-30, HEIGHT-30); 
		
		//creates layout for main panel and sub-attachments 
		GridLayout grid = new GridLayout (1, 2); 
		mainPanel.setLayout(grid);
		GridLayout gridPanel = new GridLayout(8, 1); 
				
		//subordinate JPanel for attachment to main panel 
		JPanel firstPanel = new JPanel(); 
		firstPanel.setSize(WIDTH/4, HEIGHT/4);
		firstPanel.setLayout(gridPanel);
		
		JPanel secondPanel = new JPanel(); 
		secondPanel.setSize(WIDTH/4, HEIGHT/4);
		secondPanel.setLayout(gridPanel); 

		//textfield array for student information declarations 
		JTextField inputString = new JTextField(); 
	
		
	JTextField calculatedValue = new JTextField(5); 
		//textfields: editable? 
		calculatedValue.setEditable(false);
		//tooltips
		calculatedValue.setToolTipText("Efficiency");
		//specialized Button-> extends Button class -> handles most of program functionality 
		MyButton ProcessReq = new MyButton ("Process Request", calculatedValue, inputString); 
		ProcessReq.setToolTipText("Calculate Values");
		
		//code block for attaching components to panels and panels to GUI frame

		firstPanel.add (inputString); 
		secondPanel.add(ProcessReq); 
		secondPanel.add(calculatedValue); 
		
		mainPanel.add(firstPanel); 
		mainPanel.add(secondPanel); 
		
		this.add(mainPanel);

		setFrame(WIDTH, HEIGHT); 
		
	}//end constructor: GUI
	
	

	//settings for frame 
	void setFrame (int width, int height) 
	{
		setSize(width, height); 
		setLocationRelativeTo(null); 
		setVisible(true); 
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	class MyButton extends JButton implements ActionListener
/* Main process handler --> serves as tie-in between main GUI component and InfixEval class 
 * (effectively a poor Controller in MVC) 
 */
	{
		private String id, name, major; 
		
		//awkward functionality again 
		//listener argument required in MyButton implementation outside of class constructor 
		public void actionPerformed (ActionEvent e) 
		{
		}
	
		//class constructor 
		MyButton (String title, JTextField thisTextField, JTextField inputText) 
		{
			super(title); 
			//define listener actions (insert, delete, find, update functions called when clicked based on what is selected
			ActionListener listener = new ActionListener () 
					{
						public void actionPerformed (ActionEvent e) 
						{
						
							//use to calculate/activate actions? 
							//i.e.  create class method "calculate" with input string
							//--> generate data/values, etc. 
							
							//set values after calculation 
							String thisString = inputText.getText(); 
							
							thisString = thisString.replace(" " , "");
							String[] outs = thisString.split("(?<=[-+*/\\(\\)])|(?=[-+*/\\(\\)])");
					
							//create InfixEval class and execute
						    InfixEval evaluator = new InfixEval(outs); 
						    evaluator.execute();
							thisTextField.setText(Integer.toString(evaluator.getValue()));
						
						}
					};
			this.addActionListener(listener);		
		}//end constructor: MyButton(String, JBox)

	
	} //end class: MyButton 

	private class InfixEval {
	/* InfixEval: 
	 * 
	 * InfixEval serves as focal point of problem. 
	 * Develops algorithm and declares main variables, esp. stack variables (Operand and Operator Stacks) 
	 * Receives text string from main GUI class and develops from there 
	 */
		private int result; 
		private String[] toCalculate; 
		
		private ArrayList<Character> valueList = new ArrayList<Character> () { {
			add ('-'); 
			add ('+'); 
			add ('/'); 
			add ('*'); 
		} }; //end : ArrayList : valueList

		
		//stacks are LIFO -> extends class 'Vector' with + five operations 
		//usual push and pop operations as well as 'peek' at top item
		// -> test is empty
		// -> search stack for item 
		// methods to use: 
		// boolean : empty() //tests if empty
		// E       : peek() //looks at top of stack
		// E       : push(E item) //pushes an item onto stack
		// E       : pop () //pops an item 
		 private Stack<Integer> operandStack = new Stack<Integer> () ; 
		 private Stack<Character> operatorStack = new Stack<Character> (); 
		
		InfixEval(String[] inputString) {
			
			// initialize
			System.out.println("init InfixEval");
			toCalculate = inputString; 
		}
		
		void execute () throws NumberFormatException { 
			System.out.println("Entered execute method");
		
			/* testing purposes 
			for (int i = 0; i<5; i++ ) 
				operandStack.push(i); 
			printOperandStack(operandStack); 
			/*
		/*
		 * tokenize the string containing the expression 
		 *while there are more tokens   
		 *get the next token
		 * - if it is an operand 
		 *      push it onto the operand stack  
		 * - else if it is a left parenthesis 
		 *      push it onto the operator stack  
		 * - else if it is a right parenthesis 
		 *       while top of the operator stack is not a left parenthesis 
		 *           pop two operands and an operator 
		 *           perform the calculation   
		 *           push the result onto the operand stack
		 *    !**pop top of the operator stack and ignore it (i.e. it is a left parenthesis) 
		 * - else if it is an operator 
		 *       while the operator stack is not empty 
		 *       	     and the operator at the top of the stack has higher 
		 *     	   	      or the same precedence than the current operator  
		 *     	      pop two operands and an operator and perform the calculation  
		 *      	 push the result onto the operand stack   
		 *       push the current operator on the operators stack 
		 *  while the operator stack is not empty 
		 *       pop two operands and an operator 
		 *       perform the calculation  
		 *       push the result onto the operand stack  
		 *the final result is at the top of the operand stack 
		 */
			System.out.println("----------------------------------------");
			for (String element : toCalculate)
			{
				System.out.println("Beginning of for - element iteration: ");
			    System.out.println(element);
			    try
			    {
			    	int value = Integer.parseInt(element); 
			      //  System.out.println(value); 
			        System.out.println("Pushing operand");
			        operandStack.push(value); 
			        System.out.println("Printing operand stack");
			        printOperandStack(operandStack); 
			      //  System.out.println("This occurred after evaluation of integer"); 
			    }// end : try 
			 //end : for -- element: toCalculate
				catch (NumberFormatException formXpn) 
				{
					//handle error 
					System.out.println("Entered Exception Block" );
					char currentChar = element.charAt(0); 
					if (currentChar == '('){
						System.out.println("Current char is '('"); 
						System.out.println("Pushing to operator stack");
						operatorStack.push(currentChar);
					} //end: if (currentChar == '('
					else if (currentChar == ')')
					{
						System.out.println("Current Char is ')'"); 
						//char nextChar = operatorStack.peek();
						try {
						while ((operatorStack.peek() != '(') )
						{
							//evaluate (function: 2 operands, 1 operator) 
							System.out.println("In while 'operatorStack.peek()'"); 
							int firstInt = operandStack.pop(); 
							int secondInt = operandStack.pop(); 
							char operator = operatorStack.pop(); 
							int evaluation = operate (firstInt, secondInt, operator); 
							System.out.println("Evaluation of Operands is: " + evaluation);
							operandStack.push(evaluation); 
						} //end: while (nextChar != '(' 
						
						operatorStack.pop(); //remove ')'		
						
						} //end: try 
						catch (EmptyStackException stackXPN) {
						 System.out.println("empty stack found");
						}
					} //end: else if (currentChar = ')'
					
					
					else if (valueList.contains(currentChar)) 
					{
						/*
						else if it is an operator 
						 *       while the operator stack is not empty 
						 *       	     and the operator at the top of the stack has higher 
						 *     	   	      or the same precedence than the current operator  
						 *     	      pop two operands and an operator and perform the calculation  
						 *      	 push the result onto the operand stack   
						 *       push the current operator on the operators stack 
						 *       
						 *       */
						while ((operatorStack.isEmpty()== false) && (testPrecedence(currentChar, operatorStack.peek())))
						{
							//test line
							System.out.println("In operator evaluation sequence...");
							System.out.println("Operand Stack Before Popping");
							printOperandStack(operandStack); 
							int firstInt = operandStack.pop(); 
							int secondInt = operandStack.pop(); 
							char operator = operatorStack.pop(); 
							int evaluation = operate(firstInt, secondInt, operator); 
							System.out.println("Evaluation of Operand is: " + evaluation);
							operandStack.push(evaluation); 
							System.out.println("Operator just popped");
							
						} //end: while (operatrStack.isEmpty == false) 
						System.out.println("Pushing operator...");
						operatorStack.push(currentChar); 
					} //end: else if (valueList.contains(currentChar) 
						
					
				/*	
					while the operator stack is not empty 
					 *       pop two operands and an operator 
					 *       perform the calculation  
					 *       push the result onto the operand stack  
					 *the final result is at the top of the operand stack
					*/ 
					try {
					while (operatorStack.isEmpty() == false ) {
						int firstInt = operandStack.pop(); 
						int secondInt = operandStack.pop(); 
						char operator = operatorStack.pop(); 
						int evaluation = operate(firstInt, secondInt, operator); 
						System.out.println("Evaluation of Operand is: " + evaluation);
						operandStack.push(evaluation); 
						System.out.println("Operator just popped");
					} //end while : operatorStack.isEmpty
					} //end: try 
					catch (EmptyStackException stackXPN ) {
						System.out.println("Empty Stack XPN");
					} //end: catch  
					 
					//final debug comment in flow: 
				//	System.out.println("Integer not found - continuing....");
					
					System.out.println("*************");
					
			 	} //end: catch Exception (FormatNumberException) 
			} //end : for (String element) 
					
			
		} //end method: execute () 
		
		int getValue () {
			return result; 
		}
		
		void printOperandStack (Stack<Integer> thisStack) {
			boolean isEmpty = thisStack.isEmpty(); 
			if (thisStack.isEmpty() ==false )  {
				String values = Arrays.toString(thisStack.toArray());
				System.out.println(values);
				System.out.println("Stack size: " + thisStack.size()) ;
			} //end: if 
				
			} //end: printOperandStack 
		
		//tests for operator precedence
		//first is working operator
		//second is stack operator 
		boolean testPrecedence (char first, char second) {
			boolean eval; 
			if (first =='*')
					return true; 
			else if((first == '/') && (second != '*'))
					return true; 
			else if ((second == '*') || (second == '/')) 
					return false; 
			else if (second =='(')
				return false; 
			else 
				return true; 
		}  //end : testPrecedence; 
		
		int operate (int first, int second, char operator) {
			int answer = 0; 
			if (operator == '+')
				answer = first + second; 
			else if (operator =='-')
				answer = second - first; 
			else if (operator == '/') {
				//check for division by zero --> throw custom exception 
				generateDialog ("Division by Zero Found"); 
				answer = first/second; 
			} //end : division (check for division by zero) 
			else if (operator == '*') 
				answer = first*second; 
			
			return answer; 
		}
		
	}//end class: InfixEval
	
	
	
	public void generateDialog (String msg) 
	{
		JFrame f = new JFrame(); 
		JOptionPane.showMessageDialog(f, msg);
	}

}// end class: GUI.java


