
/**
 * Central Repository for the Wordle Game
 * @author Ana Balteanu
 *
 */
public class WordLL {
	
	/// PRIVATE VARIABLES
	
	private Word mysteryWord;
	private LinearNode<Word> history;
	
	/// PRIVATE METHODS
	
	/**
	 * Helper method that adds a guess to the front of the history of guesses list
	 * @param word which is the guess to be added to the list
	 */
	private void prependHistory(Word word) {
		LinearNode<Word> newNode = new LinearNode<Word>(word);	// making a new node for the word
		newNode.setNext(this.history.getNext());	// setting the new node's next node to the head's next node
		this.history.setNext(newNode);	// setting the head's next node to the new node
	}
	
	/**
	 * Helper method used during testing that adds a guess to the back of the history of guesses list
	 * @param word the guess to be added to the list
	 */
	private void appendHistory(Word word) {
		if(this.history.getNext() == null) {
			this.history.setNext(new LinearNode<Word>(word));
		}
		else {
			while(this.history.getNext() != null) {
				this.history = history.getNext();
			}
			this.history.setNext(new LinearNode<Word>(word)); /// test hereeeeeeeeeee

		}
	}
	
	
	/// PUBLIC METHODS
	
	/**
	 * Constructor which assigns a new beginning to the history node and the mysteryWord to its parameter
	 * @param mystery is the mystery word to be used during this game, which will be compared to the guesses
	 */
	public WordLL(Word mystery) {
		this.history = new LinearNode<Word>();
		this.mysteryWord = mystery;
	}
	
	
	/**
	 * Method that compares a guess word to the mystery word, adjusts the letter labels accordingly,
	 * and prepends the guess to the front of the history list
	 * @param guess
	 * @return
	 */
	public boolean tryWord(Word guess) {
		// append guess into history
		prependHistory(guess);
		
		// format: guess.labelWord(mysteryWord)
		// return true if the word in guess is identical to the mysteryWord, false otherwise
		return guess.labelWord(this.mysteryWord);
	}
	
	/**
	 * toString method that prints the history of guesses
	 * @return histList, the list of historical guesses
	 */
	public String toString() {
		String histList = "";
		
		LinearNode<Word> curr = history;
		while(curr.getNext() != null) {
			histList += curr.getNext().getElement().toString();
			histList += "\n";
			curr = curr.getNext();
		}
		
		return histList;
	}
	
	public static void main(String[] args) {
		Word mystery = new Word(Letter.fromString("OBJACT"));
		Word word2 = new Word(Letter.fromString("OJBA"));
		WordLL wordle = new WordLL(mystery);
		wordle.prependHistory(word2);
		wordle.prependHistory(mystery);
		//System.out.println(wordle.history.getElement());
		String words = wordle.history.getNext().getElement().toString();
		//System.out.println(wordle.history.getElement().toString());
		//System.out.println(words);
		
		wordle.tryWord(word2);
		//System.out.println(word2.toString());
		
		System.out.println(wordle.toString());
	}
}
