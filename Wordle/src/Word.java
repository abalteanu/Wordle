/**
 * Word class represents a word in the wordle, which is made up of any amount of letters (each represented by a Letter object)
 * @author Ana Balteanu
 *
 */

public class Word {
	
	/// PRIVATE VARIABLES

	/**
	 * Instance variable: reference to the first node in the linked list of Letters
	 */
	private LinearNode<Letter> firstLetter; // letters are stored in a linked list formed by objects of the class LinearNode, where each node is an obj of class Letter

	/// PRIVATE METHODS

	/**
	 * Helper method that will compare this word to another word by iterating through linked list of letter\s
	 * @param otherWord paramater to be compared to this word
	 * @return true or false based on whether or not the words are determined to be equal
	 */
	private boolean equals(Word otherWord) {
		
		LinearNode<Letter> curr = this.firstLetter;
		LinearNode<Letter> currOther = otherWord.firstLetter;
		
		boolean equal = true;
		while(curr.getNext() != null && currOther.getNext() != null) {
			if(!curr.getElement().equals(currOther.getElement())){
				equal = false;
			}
			// to iterate through the nodes (moving to the next node)
			curr = curr.getNext();
			currOther = currOther.getNext();
		}
		return equal;
	}
	
	/**
	 * Helper method that compares the current word (user's guess) to the mystery word.
	 * This method will adjust each letter based on the Letter class's attributes USED, UNUSED, and CORRECT.
	 * @param checkWord is the mystery word
	 */
	private void labelLetters(Word checkWord) {
		LinearNode<Letter> curr = this.firstLetter;
		LinearNode<Letter> currOther = checkWord.firstLetter;
		
		// for the positions of the letters being checked
		int pos = 0;
		int posOther = 0; // only updates if the end of the mystery word has not been reached yet
		
		while(curr.getNext() != null) {
			
			//CASE 1: CORRECT
			
			// checking that the positions of the letters are the same when checking for the CORRECT attribute.
			// this aspect is important when the checkWord is shorter than this word
			if(curr.getElement().equals(currOther.getElement()) && pos==posOther){
				// comparing this letter to the letter at the same position in the mystery word
				curr.getElement().setCorrect();
			}
			else {
				
				LinearNode<Letter> currOtherTemp = checkWord.firstLetter;

				boolean used = false;
				
				while(currOtherTemp.getNext() != null) {
					
					// comparing this letter to every letter in the mystery word
					if(curr.getElement().equals(currOtherTemp.getElement())){
						//CASE 2: USED
						curr.getElement().setUsed();
						used = true;
					}
					
					if(!used) {
						//CASE 3: UNUSED
						curr.getElement().setUnused();
					}
					
					currOtherTemp = currOtherTemp.getNext();
				}
			}
			// moving to the next node
			curr = curr.getNext();
			pos++;
			if(currOther.getNext() != null) {
				posOther++;
				currOther = currOther.getNext();
			}
		}
	}
	
	/// PUBLIC METHODS

	/**
	 * Constructor which initializes the Word object. 
	 * @param letters is an array of letters that will be transformed into a linked structure in the method
	 */
	public Word(Letter[] letters) {
		
		this.firstLetter = new LinearNode<Letter>();
		LinearNode<Letter> curr = firstLetter;
		LinearNode<Letter> next = new LinearNode<Letter>();	//  the new next node (after the current letter)

		for(int i=0; i<letters.length; i++) {
			curr.setElement(letters[i]);	// putting that element into the node
			next = new LinearNode<Letter>();	//  the new next node (after the current letter)
			curr.setNext(next);	// setting the current's reference to the next node's address
			curr = next;	// shifting to the next item in the linear node
		}
		curr.setNext(null);	// setting the curr node's next reference to null, indicating the end of the list
	}
	
	
	/**
	 * toString method to print to the user
	 * @return string with the entire word and its decorator around each letter
	 */
	public String toString() {
		String word = "";

		LinearNode<Letter> curr = firstLetter;	// setting the current node to the first letter in order to iterate through the letters
		while(curr.getNext() != null) {
			// adding each letter to the string of the word
			word += curr.getElement().toString();
			word += " ";
			curr = curr.getNext();
		}
		return "Word: " + word;
	}
	
	/**
	 * Function that labels the letters in the guess word with respect to its accuracy against the mystery word
	 * @param mystery is the mystery word with which the guess word is compared
	 * @return true or false based on if the guess was exactly correct compared to the mystery word, or if it is incorrect
	 */
	public boolean labelWord(Word mystery) {
		
		this.labelLetters(mystery);	//labeling the letters
		
		if(this.equals(mystery)) {
			// if the words are equal, still label the letters but return true
			return true;
		} else {
			// if the guess is not equal to the mystery word, return false and label the letters
			return false;
		}
	}
	
}