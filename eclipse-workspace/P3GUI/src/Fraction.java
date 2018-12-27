/**Defines fractions. Has: 
 * a constructor accepting string representation of a fraction 
 * a toString method 
 * Implements the Comparable interface (a compareTo method is also required) 
 * @author Kelly M McCutchen
 *
 */
public class Fraction implements Comparable<Fraction>{

	private String myFraction; 
	private Integer numerator; 
	private Integer denominator; 
	
	Fraction (String fraction) {
		myFraction = fraction; 
	}
	
	Fraction (Integer numerator, Integer denominator){
		this.numerator = numerator; 
		this.denominator = denominator; 
		myFraction = numerator.toString() + "/" + denominator.toString(); 
	}
	
	public String toString() {
		return myFraction; 
	}
	
	private float evaluate () {
		return (float)numerator/(float)denominator; 
	}
	
	@Override
	public int compareTo (Fraction compareFraction) {
		if (this.evaluate()>compareFraction.evaluate())
			return 1; 
		if (this.evaluate()==compareFraction.evaluate())
			return 0; 
		if (this.evaluate()<compareFraction.evaluate())
			return -1; 
		else 
			return -99; 
	}

}
