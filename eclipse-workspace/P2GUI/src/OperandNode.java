
/**
 *  This class implements the OperandNode.
 */
public class OperandNode implements Node {

	
    private double value;
    
    public OperandNode(double value) {
        this.value = value;
    }
    
    public double evaluate() {
        return value;
    }
    
    public String preOrderWalk() {
        return String.valueOf(value);
    }

    public String inOrderWalk() {
        return String.valueOf(value);
    }

    public String postOrderWalk() {
        return String.valueOf(value);
    }
    
    public Instruction encode (int register) {
    	System.out.println("in operand Instruction encode...lowest level..." );
    	AddOperator addOp = new AddOperator(); 
    	Register tempReg = new Register(); 
    	tempReg.setIndex(register);
    	tempReg.setValue(value);
    	Instruction instruction = new Instruction (register, addOp, tempReg, tempReg, false, false, tempReg); 
    
    	return instruction;
    }
}
