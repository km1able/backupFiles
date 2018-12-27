import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.io.File; 

/** Instruction generates instructions utlizing Registers to bubble up values from 
 * bottom to top 
 * 
 *
 */
public class Instruction   {

	private Register left, right; 
	private Operator operator; 
	private int register; 
	private boolean useRelativeAddressL;
	private boolean useRelativeAddressR; 
	
	private Register carryRegister; 
	String printing; 
	  String fileName = ("Output.txt");
	     
	//carryRegister used when passing value up the chains
	//could implement a stack structure for registers
	//however, framework is in place for bubble-up method
	//use relativeAddressL/R is main signal of process management 
	
	Instruction (int reg, Operator op, Register left, Register right, boolean useRelativeAddressL, boolean useRelativeAddressR, Register carryRegister) {
		 register = reg; 
		 operator = op;
		 this.left = left; 
		 this.right = right; 
		 this.useRelativeAddressL = useRelativeAddressL; 
		 this.useRelativeAddressR = useRelativeAddressR; 
		 this.carryRegister = carryRegister; 
	}
	
	//give boolean flags for use of relative addressing 
	public Register processInstruction ()   {
		BufferedWriter writer = null; 
		String fileName = ("Output.txt");
	   
		//manage instructions for register values i.e
		//events where operands are stored in register values
		boolean leftRegisterType = left.determineRegisterType(); 
		boolean rightRegisterType = right.determineRegisterType(); 
		int leftIndex = left.getIndex(); 
		int rightIndex = right.getIndex(); 
		double leftVal = left.getValue(); 
		double rightVal = right.getValue();
		
		double evaluation = operator.evaluate(leftVal,  rightVal); 
		
		carryRegister.setIndex(register);
		carryRegister.setValue((carryRegister.getValue()+evaluation));
		
		//create register set/stack structure? 
		//encode instruction for bubble up 
		if((useRelativeAddressL==true) && (useRelativeAddressR==true)){
			//process evaluation differently....
			printToFile (register, operator.encodeString(), leftIndex, rightIndex); 
		}
		if ((useRelativeAddressL==true) && (useRelativeAddressR==false)) {
			printToFile (register, operator.encodeString(), leftIndex, rightVal);
		}
		if ((useRelativeAddressL==false) && (useRelativeAddressR==true)) {
			printToFile (register, operator.encodeString(), leftVal, rightIndex); 
		}
		if ((useRelativeAddressL==false) && (useRelativeAddressR==false)) {
			printToFile (register, operator.encodeString(), leftVal, rightVal); 
		}
			   try { 
			       File outFile = new File(fileName);
			       writer = new BufferedWriter(new FileWriter(fileName,  true)); 
		    writer.write(printing+ "\n"); }  //end : try 
		    catch (Exception e) {
		        e.printStackTrace();
	    } finally {
	        try {
	            // Close the writer regardless of what happens...
	            writer.close();
	        } catch (Exception e) { System.out.println("error closing..." );
	       } //end catch 
	    } //end finally 
		
		return carryRegister; 
	} //end: Register processInstructions (); 
	
	public String printToFile (int index, String op, double operandLeft, int indexRight) {
		//print here
		printing = op; printing += " "; printing += index; printing += " "; 
		printing += operandLeft; printing += " "; printing += indexRight; 
		return printing; 
	}
	public String printToFile (int index, String op, int indexLeft, double operandRight) {
		printing = op; printing += " "; printing += index; printing += " "; 
		printing += indexLeft; printing += " "; printing += operandRight; 
		return printing; 
	}
	public String printToFile (int index, String op, int indexLeft, int indexRight) {
		//print here
		printing = op; printing += " "; printing += index; printing += " "; 
		printing += indexLeft; printing += " "; printing += indexRight; 
		return printing; 
	}
	public String printToFile (int index, String op, double operandLeft, double operandRight) {
		//print here
		printing = op; printing += " "; printing += index; printing += " "; 
		printing += operandLeft; printing += " "; printing += operandRight; 
		
		
		return printing; 
	}
	
	public Register getLeftRegister () {
		return this.left; 
	}
	
	public Register getRightRegister() {
		return this.right; 
	}
	
	public Operator getOperator () {
		return this.operator; 
	}
	
	public int getMainRegister () {
		return register; 
	}
	
	public Register getCarryRegister () {
		return carryRegister; 
	}
	
}//end class: Instruction 

	