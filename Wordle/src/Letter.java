
/**
 * Letter class which represents a single letter to be used in the WordLL game
 * @author Ana Balteanu
 *
 */

public class Letter {
	
	/// PRIVATE VARIABLES

	private char letter;
	
	private int label;
	
	private final int UNSET = 0, UNUSED = 1, USED = 2, CORRECT = 3;	// WHAT ARE THE VALUES OF THESE SUPPOSED TO BE 
	
	/// PUBLIC METHODS

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
	
	/**
	 * Method that returns the string representation of the letter, including its labels
	 * @return string representation of letter
	 */
	public String toString() {
		return this.decorator() + this.letter + this.decorator();
	}
	
	/**
	 * sets the label of the letter to unused, with the value 1
	 */
	public void setUnused() {
		this.label = this.UNUSED;
	}
	
	/**
	 * sets the label of the letter to used, with the value 2
	 */
	public void setUsed() {
		this.label = this.USED;
	}
	
	/**
	 * sets the label of the letter to unused, with the value 3
	 */
	public void setCorrect() {
		this.label = this.CORRECT;
	}
	
	/**
	 * Checks if the letter is set to unused or not 
	 * @return true or false depending on if the letter is used or not
	 */
	public boolean isUnused() {
		if (this.label == this.UNUSED) {
			return true;
		}
		else {return false;}
	}
	
	
	/**
	 * Function that makes an array of characters from a string
	 * @param s is the string from which the function creates the array of chars
	 * @return the char array
	 */
	public static Letter[] fromString(String s) {
		
		Letter[] letterArr = new Letter[s.length()];
		for (int i = 0; i < s.length(); i++){
			// iterating through string
		    char c = s.charAt(i);        
		    //Process char
		    letterArr[i] = new Letter(c);
		}
		return letterArr;
	}
	
}
