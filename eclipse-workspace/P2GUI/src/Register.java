
	/**utility for handling of value/address for registers in instructions */ 
	public class Register {
		
		private double value; 
		private int index; 
		private boolean isRegisterAddress; 
		Register () {
			this.value = 0; 
			this.index = 0; 
			isRegisterAddress = false; 
		}
		Register (double value, int index) {
			this.value = value; 
			this.index = index; 
			determineRegisterType(); 
			
			
				
		} //end: constructor Register (value, index) 
	
	public double getValue () {
		return this.value; 
	}
	
	public int getIndex () {
		return this.index; 
	}
	
	public void setValue (double value) {
		this.value = value;
	}
	
	public void setIndex (int index) {
		this.index = index; 
	}
	//returns true if register is an address vs. a value (false for double values)
	public boolean determineRegisterType ()
		{
			if (index!= -1) {
				isRegisterAddress = true; 
			}
			else {
				isRegisterAddress = false; 
			}
			return isRegisterAddress; 
		} //end fn: determineRegisterType 
	
	} //end class: Register 

