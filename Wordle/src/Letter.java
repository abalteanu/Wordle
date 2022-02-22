
/**
 * Letter class which represents a single letter to be used in the WordLL game
 * @author Ana Balteanu
 *
 */

public class Letter {
	
	
	private char letter;
	
	private int label;
	
	private final int UNSET = 0, UNUSED = 1, USED = 2, CORRECT = 3;	// WHAT ARE THE VALUES OF THESE SUPPOSED TO BE 
	
	
	/**
	 * Constructor that sets the letter to the paramater's character and the letter's label to UNSET
	 * @param c
	 */
	public Letter(char c) {
		this.label = this.UNSET;
		this.letter = c;
	}
	
	/**
	 * equals methods that compares another object to this letter to see if they are equal
	 * @param otherObject the object to compare to this letter
	 * @return boolean true if they are equal and false if otherObject is not of type letter or if the letters are not equal.
	 */
	public boolean equals(Object otherObject) {
		if(otherObject instanceof Letter) {
			// checking if the type of otherObject is of the same type as letter.
			if(this.letter == ((Letter)otherObject).letter) {
				// comparing letter attributes of both objects
				return true;
			}
			else {return false;}
		}
		else {return false;}
	}
	
	
	/**
	 * Decorator function that returns the indicator symbol for that specific letter
	 * @return String of +, -, !, or a space to indicate the guess to the user
	 */
	public String decorator() {
		if(this.label == this.USED) {
			// if this letter is used but in the incorrect location
			return "+";
		} else if (this.label == this.UNUSED) {
			// if this letter is not used
			return "-";
		} else if (this.label == this.CORRECT) {
			// if this letter is correct and in the right location
			return "!";
		} else {
			// if (this.label == this.UNSET) UNSET is the initial value of label so this can just be contained within a simple else statement
			return " ";
		}
	}
	
	public String toString() {
		// overridden method... does this mean i have to use @override?????????????????????
		return this.decorator() + this.letter + this.decorator();
	}
	
	public void setUnused() {
		this.label = this.UNUSED;
	}
	
	public void setUsed() {
		this.label = this.USED;
	}
	
	public void setCorrect() {
		this.label = this.CORRECT;
	}
	
	public boolean isUnused() {
		if (this.label == this.UNUSED) {
			return true;
		}
		else {return false;}
	}
	
	public static Letter[] fromString(String s) {
		
		Letter[] letterArr = new Letter[s.length()];
		for (int i = 0; i < s.length(); i++){
		    char c = s.charAt(i);        
		    //Process char
		    letterArr[i] = new Letter(c);
		}
		return letterArr;
	}
	
	public static void main(String[] args) {
		Letter letter = new Letter('a');
		letter.setUsed();
		System.out.println(letter.toString());
	}
}
