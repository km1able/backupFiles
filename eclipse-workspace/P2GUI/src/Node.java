/**
 * This interface allows a generic type of Node to be
 * defined. This allows the use of either OperandNodes or
 * OperatorNodes to be stored in the expression tree.
 *
 * Each node must implement methods to evaluate the node
 * (calculate the value of the node), and three tree walks.
 */

public interface Node {
	
		
	    public double evaluate();
	    public String preOrderWalk();
	    public String inOrderWalk();
	    public String postOrderWalk();
	    public Instruction encode  (int register); 
}
