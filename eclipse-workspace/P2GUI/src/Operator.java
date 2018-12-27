

/**
 *  This implements the operators for the expression tree.
 *  All operators will have 2 methods:
 *    1 - An evaluate method to use this object to calculate 
 *        a result.
 *    2 - A toString method to return a string representation
 *        of the operator.
 */


public abstract class Operator {

		   abstract public double evaluate(double x, double y);
		   abstract public String encodeString(); 
	}


class AddOperator extends Operator {
    public double evaluate(double d1, double d2) {
        return d1 + d2;
    }
    
    public String toString() {
        return "+";
    }
    
    public String encodeString() {
    	return "Add"; 
    }
}
   
class MulOperator extends Operator {
    public double evaluate(double d1, double d2) {
        return d1 * d2;
    }
    
    public String toString() {
        return "*";
    }
    
    public String encodeString() {
    	return "Mul"; 
    }
}
   
class SubOperator extends Operator {
    public double evaluate(double d1, double d2) {
        return d1 - d2;
    }
    
    public String toString() {
        return "-";
    }
    
    public  String encodeString() {
    	return "Sub"; 
    }
}
   
class DivOperator extends Operator {
    public double evaluate(double d1, double d2) {
        return d1 / d2;
    }
    
    public String toString() {
        return "/";
    }
    
    public String encodeString() {
    	return "Div"; 
    }
}
