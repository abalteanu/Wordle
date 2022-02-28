
/**
 * Extended Letter class that extends the Letter class (meaning it is a subclass of Letter)
 * This class uses the functionality of the Letter class while also 
 * @author Ana Balteanu
 */
public class ExtendedLetter extends Letter {
	private String content;
	private int family;	// +ve number that that assigns this letter to a family, of which other ExtendedLetter objects with the same family value are a part of
	private boolean related;
	private final int SINGLETON = -1;
	
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
		super('c');
		this.content = s;
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
			if((((ExtendedLetter)other).family) == this.family){
				
				this.related = true; 	// if they are part of the same family
				
				if(this.content == ((ExtendedLetter)other).content) {
					// comparing letter attributes of both objects
					return true;
				}
				else {return false;}
			}
			else {return false;}
		}
		else {return false;}
	}
	
	@Override 
	public String toString() {
		if(this.isUnused() && this.related) {
			return "." + this.content + ".";
		} 
		else {
			return this.decorator() + this.content + this.decorator();
		}
	}
	
	public static Letter[] fromStrings(String[] content, int[] codes) {
		Letter[] letters = new Letter[content.length];
		
		for(int i = 0; i<content.length; i++) {
			if(codes == null) {
				letters[i] = new ExtendedLetter(content[i]);
			} else {
				letters[i] = new ExtendedLetter(content[i], codes[i]);
			}
		}
		return letters;
	}
	

	
	public static void main(String[] args) {
		ExtendedLetter c1 = new ExtendedLetter("c");
		ExtendedLetter c2 = new ExtendedLetter("d", 9);
		
		//Equals
		System.out.println(c1.equals(c2));
		System.out.println(c1.related);
	}
}
