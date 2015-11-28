import java.util.ArrayList;

/**
 * The class represent a dictionary of words. 
 * It provides a way of searching through the dictionary.
 * It also can produce a dictionary in which the words are limited
 * to a particular length. 
 * 
 * @author Joanna Klukowska
 *
 */
public class Dictionary extends BST<String> implements DictionaryInterface{
	//actual storage for the words
	private BST< String > words;


	/**
	 * Creates an empty Dictionary object (no words).
	 */
	public Dictionary ( ) {
		words = new BST < String > () ;
	}

	/**
	 * Creates a Dictionary object containing all words from the 
	 * listOfWords passed as a parameter.
	 * 
	 * @param listOfWords the list of words to be stored in the newly created 
	 * Dictionary object
	 */
	public Dictionary ( ArrayList < String > listOfWords ) {
		words = new BST < String > ();

		for( String word : listOfWords ){
			words.insert(word);
		}
	}

	/**
	 * Creates a new Dictionary object from this Dictionary object that 
	 * contains words of a specified size.
	 * @param size length of the words that should be included in the new 
	 * Dictionary object
	 * @return a new Dictionary object containing only the words of specified 
	 * size
	 */
	public Dictionary getWordsBySize ( int size ) {
		ArrayList<String> allWords = words.inOrderArray();
		ArrayList<String> wordsBySize = new ArrayList<String>();

		for ( int i = 0; i < allWords.size(); i++ ){
			if( allWords.get(i).length() == size ){
				wordsBySize.add(allWords.get(i));
			}
		}

		return new Dictionary (wordsBySize);
	}


	/**
	 * 
	 */
	public boolean findWord ( String word ) {
		return words.contains(word);
	}

	public boolean findPrefix ( String prefix ){
		return findPrefix(prefix, words.root);
	}
	/**
	 * 
	 */

	private boolean findPrefix (String prefix, BSTNode<String> node ) {
		
		if( node == null ){
			return false;
		}
		else{
			String nodePrefix = node.getData().substring(0, prefix.length() );
			if( prefix.equals(( nodePrefix )) ){
				return true;
			}

			else if( prefix.compareTo( nodePrefix )  < 0 ){
				return findPrefix( prefix, node.getLeft() );
			}

			else if( prefix.compareTo( nodePrefix ) > 0 ){
				return findPrefix( prefix, node.getRight() );
			}
			return true;
		}
	}	

	public String toString(){
		StringBuilder b = new StringBuilder();
		for( int i = 0; i < words.inOrderArray().size(); i++ ){
			b.append(words.inOrderArray().get(i) + " ");
		}
		return b.toString();
	}
}
