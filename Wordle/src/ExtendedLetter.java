
/**
 * Extended Letter class that extends the Letter class (meaning it is a subclass of Letter)
 * This class uses the functionality of the Letter class while also adding other features such as classifying letters into families
 * @author Ana Balteanu
 */
public class ExtendedLetter extends Letter {


	/// PRIVATE VARIABLES
	
	/**
	 * String content of the letter
	 */
	private String content;
	/**
	 * number indicating the family of the letter
	 */
	private int family;	// +ve number that that assigns this letter to a family, of which other ExtendedLetter objects with the same family value are a part of
	private boolean related;
	private final int SINGLETON = -1;
	
	
	/// PUBLIC METHODS

	/**
	 * Constructor which calls the superconstructor from the letter class and initializes all
	 * the variables: content to s, related to false, and family to SINGLETON
	 * @param s a string that is the content of the ExtendedLetter
	 */
	public ExtendedLetter(String s) {
		super('c');	// initializing instance variables of the superclass using an arbitrary char (char doesn't matter since it will not be used)
		this.content = s;
		this.related = false;
		this.family = SINGLETON;
	}
	
	/**
	 * Constructor which may overload the constructor above. It assigns family to the parameter fam instead of the constant SINGLETON
	 * @param s a string that is the content of the ExtendedLetter
	 * @param fam an integer which represents the family of the ExtendedLetter
	 */
	public ExtendedLetter(String s, int fam) {
		super('c');	// assigning a random char to initialize the letter. this will not actually be the content of the letter. 
		this.content = s; // determines the content of the letter
		this.related = false;
		this.family = fam;
	}
	
	/**
	 * Equals method that compares one obj to this obj to see if:
	 * 1. they are in the same family, in which case this.related is turned to true
	 * 2. they are identical, in which case the method returns true. Otherwise the method returns false
	 * @return boolean of true or false depending on if the contents of the objects are identical or not
	 */
	public boolean equals(Object other) {
		if(other instanceof ExtendedLetter) {
			
			// checking if the type of other is of ExtendedLetter type.
			if(this.family == ((ExtendedLetter)other).family) {
				this.related = true; 	// if they are part of the same family
			}
			if(this.content.equals(((ExtendedLetter)other).content)) {
				// comparing letter attributes of both objects
				return true;
			}
			else {return false;}
		}
		else {return false;}
	}
	
	/**
	 * Gives a string representation of this extended letter object by using the decorator function.
	 * If this letter is unused and related, letter will be surrounded by periods
	 * @return the string representation of the labeled letter
	 */
	@Override 
	public String toString() {
		if((this.isUnused()) && (this.related == true)) {
			// if letter is unused and related, surround by periods
			return "." + this.content + ".";
		} 
		else {
			// else surround by whatever the letter class determines it to be
			return this.decorator() + this.content + this.decorator();
		}
	}
	
	/**
	 * Function that creates an array of letters of type Letter
	 * @param content is an array that determines the size of the array
	 * @param codes determines the family of the letters in the array
	 * @return the letter array depending on if codes is null or not
	 */
	public static Letter[] fromStrings(String[] content, int[] codes) {
		// creating an array for the letters of the right size
		Letter[] letters = new Letter[content.length];
		
		for(int i = 0; i<content.length; i++) {
			// iterating through the content
			if(codes == null) {
				letters[i] = new ExtendedLetter(content[i]);
			} else {
				letters[i] = new ExtendedLetter(content[i], codes[i]);
			}
		}
		return letters;
	}
	
}
