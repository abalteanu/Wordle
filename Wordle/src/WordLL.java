
/**
 * Central Repository for the Wordle Game
 * @author Ana Balteanu
 *
 */
public class WordLL {
	
	private Word mysteryWord;
	private LinearNode<Word> history;
	
	/**
	 * Helper method that adds a guess to the history of guesses list
	 * @param word
	 */
	private void prependHistory(Word word) {
		//System.out.println(this.history.getNext());
		LinearNode<Word> newNode = new LinearNode<Word>(word);
		newNode.setNext(this.history.getNext());
		this.history.setNext(newNode);
	}
	
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
	
	public WordLL(Word mystery) {
		this.history = new LinearNode<Word>();
		this.mysteryWord = mystery;
	}
	
	public boolean tryWord(Word guess) {
		// append guess into history
		prependHistory(guess);
		
		// format: guess.labelWord(mysteryWord)
		// return true if the word in guess is identical to the mysteryWord, false otherwise
		return guess.labelWord(this.mysteryWord);
	}
	
	public String toString() {
		String histList = "";
		
		LinearNode<Word> curr = history;
		while(curr.getNext() != null) {
			histList += curr.getNext().getElement().toString();
			histList += "\n";
			curr = curr.getNext();
			//System.out.println(histList);
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
