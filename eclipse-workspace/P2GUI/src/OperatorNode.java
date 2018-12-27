import java.io.IOException;
import java.util.Stack;

import javax.swing.JOptionPane;



/**
 *  This is the OperatorNode class. It evaluates the node
 *  to calculate its value, and it prints the tree
 *  in preorder, inorder, and postorder formats.
 */
public class OperatorNode implements Node  {

    Node left, right;
    Operator operator;
   
    public OperatorNode(Operator operator, Node left,
                        Node right){
        this.operator = operator;
        this.left = left;
        this.right = right;
    }

    public double evaluate() {
        double leftValue = left.evaluate();
        double rightValue = right.evaluate();
        return operator.evaluate(leftValue, rightValue);
    } 
    
    public String preOrderWalk() {
        String leftValue = left.preOrderWalk();
        String rightValue = right.preOrderWalk();
        return "" + operator + " " + leftValue + " "
                  + rightValue;
    }

    public String inOrderWalk() {
        String leftValue = left.inOrderWalk();
        String rightValue = right.inOrderWalk();
        
        return "(" + leftValue + " " + operator + " "
                   + rightValue + ")";
    }

    public String postOrderWalk() {
        String leftValue = left.postOrderWalk();
        String rightValue = right.postOrderWalk();
        return leftValue + " " + rightValue + " " + operator;
        
    }
    
    /*	Encodes this Operator section 
     *  begins with register 0 --> if sequences with two operator nodes are found, register count 
     *  will increase for evaluation
     * encode will function to create 3 byte addressing
    first, determines whether children are operators/operands
    if both operators, increases register count for branching needs
    if both operands, evaluates, encodes, and returns to higher level/root
    if either/or, continues on either side until evaluation is found 
    returns evaluation of current operation
    
    Supposedly, registers will bubble up to the top
    */

    public Instruction encode (int register) {
    	
    	//Stack<Instruction.Register> registerStack = new Stack<Instruction.Register> (); 
    	System.out.println("entering encode...");
    	int currentRegister = register; 
    	boolean isLeftOperator, isRightOperator;
    	
    	Instruction leftInstruction; 
    	Instruction rightInstruction; 
  
    	if (left instanceof OperatorNode) isLeftOperator =true; 
    		else isLeftOperator=false; 
    	if (right instanceof OperatorNode) isRightOperator = true; 
    		else isRightOperator= false; 
    //	System.out.println("Left is an operator node: " + isLeftOperator);
   // 	System.out.println("Right is an operator node: " + isRightOperator);
    	//next nodes are both operators --> increase register count for right side (independent processing from mainline) 
    	if (isLeftOperator && isRightOperator) 
    	{
    	//	System.out.println("...both are operators....");
    		//increase register block 
    		leftInstruction  =  left.encode(currentRegister); 
    		rightInstruction = right.encode(currentRegister+1); 
    		//could simply evaluate expressions --> 
    		//process instructions and evaluate
    		Register leftCarry = leftInstruction.processInstruction(); 
    		Register rightCarry = rightInstruction.processInstruction(); 
    		
    		double fromLeft = leftCarry.getValue(); 
    		double fromRight = rightCarry.getValue(); 
    		double evaluation = this.operator.evaluate(fromLeft,  fromRight);
    	
    		Register carryRegister = new Register(evaluation, currentRegister); 
    		Instruction thisInstruction = new Instruction(currentRegister, this.operator, leftCarry, rightCarry, false, false, carryRegister); 
    		
    		thisInstruction.processInstruction();
    		return thisInstruction; 
    	}
    	
    	
    	//neither children are operators --> evaluate them with current operator, return instruction as follows: 
    	//Instruction = (current register, current operator, left value, right value, evaluation) 
    	else if (!isLeftOperator && !isRightOperator) {
    		//operand sequence --> evaluate current instruction and return 
    	//	System.out.println("...both are operands...");
    		double leftValue = left.evaluate(); 
    		double rightValue = right.evaluate(); 
    		double evaluation = operator.evaluate(leftValue, rightValue); 
    		
    		Register leftRegister = new Register (leftValue, currentRegister); 
    		Register rightRegister = new Register (rightValue, currentRegister); 
    		Register carryRegister = new Register (evaluation, currentRegister); 
    		
    		//String myOperation = operator.encodeString(); 
    		Instruction instruction = new Instruction (currentRegister, operator, leftRegister, rightRegister , false , false, carryRegister);
    		instruction.processInstruction(); 
    			
    		return instruction; 
    		
    	}
    	
    	else if (isLeftOperator && !isRightOperator) {
    		//operand on right side (is not operator) 
    		//traverse left, encode and return value
    		//System.out.println("....left is an operator...");
    		//this instruction should have : one return value 
    		leftInstruction = left.encode(currentRegister); 
    	//	leftInstruction.processInstruction(); 
    		
    		double rightValue = right.evaluate(); 
    		//get leftInstruction register, 
    		Register leftReg = leftInstruction.getCarryRegister(); 
    		double evaluation = this.operator.evaluate(leftReg.getValue(),  rightValue); 
    		Register rightReg = new Register (rightValue, currentRegister); 
    		Register carryReg = new Register (evaluation, currentRegister); 
    		Instruction instruction = new Instruction (currentRegister, operator, leftReg, rightReg, true, false, carryReg );
    		return instruction; 
    	}
    	
    	else if (!isLeftOperator && isRightOperator) {
    		//operand on left side (is not operator) 
    		//traverse right, encode and return value
    		//System.out.println("....right is an operator....");
    		//this instruction should have : one return value 
    		rightInstruction = right.encode(currentRegister); 
    	//	rightInstruction.processInstruction(); 
    		
    		double leftValue = left.evaluate(); 
    		//get leftInstruction register, 
    		Register rightReg = rightInstruction.getCarryRegister(); 
    		double evaluation = this.operator.evaluate(leftValue,  rightReg.getValue()); 
    		Register leftReg = new Register (leftValue, currentRegister); 
    		Register carryReg = new Register (evaluation, currentRegister); 
    		Instruction instruction = new Instruction (currentRegister, operator, leftReg, rightReg, false, true, carryReg );
    		return instruction; 
    	}
    	
    	else {
    		System.out.println("Error...neither...");
    		Register tempReg = new Register(); 
    		Instruction discardInstr = new Instruction (currentRegister, operator, tempReg, tempReg, false, false, tempReg); 
    		return discardInstr; 
    	}
    		//error
    		
    	
    	
    }//end: public Instruction encode (int register) 
}