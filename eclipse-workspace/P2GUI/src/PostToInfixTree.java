import java.util.ArrayList;
import java.util.Stack;

import javax.swing.JOptionPane;
/**Generates nodal tree structure for accessing and printing */ 
public class PostToInfixTree  {
	
	static private boolean isOperator (char c) {
		return c == '*' || c=='+' || c=='-' || c=='/'; 
	} //end : isOperaator (char) 
	
	static private boolean isNumber (char ch) {
		return ch >='0' && ch <='9'; 
	} //end : isNumber (char) 
	
	 // Returns constructed tree root
      public Node makeTree(ArrayList<Character> myExpression) throws InvalidTokenException {
        Stack<Node> stack = new Stack();
        Node treeNode = null, leftNode, rightNode;
        int count =0;
        
        System.out.println("--Beginning debug on construct Tree: -------");
        //loops through input
        for (int i = 0; i < myExpression.size(); i++) {
        	
      //      System.out.println("----Expression Value is: " + myExpression.get(i));
            
            // If char is an operand, push onto stack 
            if (!isOperator(myExpression.get(i)) && isNumber(myExpression.get(i))) {
            	char myChar = myExpression.get(i); 
            	int x = Character.getNumericValue(myChar); 
           // 	System.out.println("After parsing, value is: " + x);
                treeNode = new OperandNode(x);
                stack.push(treeNode);
           //     System.out.println("Pushing operand to stack..." );
            } else if (isOperator(myExpression.get(i))){ 
               
                // Pop two top nodes
               rightNode = stack.pop();      
               leftNode = stack.pop();
               
               count ++; 
              
                switch (myExpression.get(i)){
                    case '+':
                        System.out.print("Add ");
                        treeNode = new OperatorNode (new AddOperator (),leftNode, rightNode); 
                        System.out.println(leftNode.evaluate()  + " " + rightNode.evaluate());
                        break;
                    case '-':
                        System.out.print("Sub ");
                        treeNode = new OperatorNode (new SubOperator () , leftNode, rightNode); 
                        System.out.println(leftNode.evaluate()  + " " + rightNode.evaluate());
                        break;
                    case '*':
                        System.out.print("Mul ");
                        treeNode = new OperatorNode (new MulOperator (), leftNode, rightNode); 
                        System.out.println( leftNode.evaluate()  + " " + rightNode.evaluate());
                        break;
                    case '/':
                        System.out.print("Div ");
                        if(rightNode.evaluate() == 0){
                            JOptionPane.showMessageDialog(null, "Can't divide by 0");
                        }
                        else {
                        	treeNode = new OperatorNode (new DivOperator (), leftNode, rightNode); 
                        	System.out.println(leftNode.evaluate()  + " " + rightNode.evaluate());
                            break;
                        }
                     default: 
                    	  treeNode = null; 
                } //end: switch 
                
                treeNode.toString();
                stack.push(treeNode);  
               
            } //end: else if 
            else {
            	throw new InvalidTokenException("Invalid character input");
            } //end else 
           
               }//end: for (postfix[i]
        
       // System.out.println(" ");
        
        //root of expression
        treeNode = stack.peek();
        return treeNode;
    } 

	
} //end: class PostToInfixTree
