/**
 * Word class represents a word in the wordle, which is made up of any amount of letters (each represented by a Letter object)
 * @author Ana Balteanu
 *
 */

public class Word {
	
	/**
	 * Instance variable: reference to the first node in the linked list of Letters
	 */
	private LinearNode<Letter> firstLetter; // letters are stored in a linked list formed by objects of the class LinearNode, where each node is an obj of class Letter

		
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
	private void checkLetters(Word checkWord) {
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
	
	
	/**
	 * Constructor which initializes the Word object. 
	 * @param letters is an array of letters that will be transformed into a linked structure in the method
	 */
	public Word(Letter[] letters) {
		
		firstLetter = new LinearNode<Letter>();
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

		LinearNode<Letter> curr = firstLetter;
		while(curr.getNext() != null) {
			word += curr.getElement().toString();
			word += " ";
			curr = curr.getNext();
		}
		return "Word: " + word;
	}
	
	public boolean labelWord(Word mystery) {

		if(this.equals(mystery)) {
			this.checkLetters(mystery);
			return true;
		} else {
			//check letters
			this.checkLetters(mystery);
			return false;
		}
	}
	
	public static void main(String[] args) {
		Word word1 = new Word(Letter.fromString("OBJECT"));
		Word word3 = new Word(Letter.fromString("OJBA"));
		//Word word2 = new Word(Letter.fromString("CLASS"));
		//word2.labelWord(word1);

		System.out.println(word1.toString());

		System.out.println((word1).toString().equals("Word:  O   B   J   E   C   T  "));
		
		
		System.out.println(word1.labelWord(word3));

		System.out.println(word1.toString());

		//System.out.println(word1.equals(word2));
	}
	
}