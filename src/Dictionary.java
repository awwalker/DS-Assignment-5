import java.util.ArrayList;

/**
 * The class represent a dictionary of words. 
 * It provides a way of searching through the dictionary.
 * It also can produce a dictionary in which the words are limited
 * to a particular length. 
 * 
 * @author Aaron Walker
 *
 */
public class Dictionary extends AVLTree<String> implements DictionaryInterface{
	//actual storage for the words
	private AVLTree<String> words;


	/**
	 * Creates an empty Dictionary object (no words).
	 */
	public Dictionary ( ) {
		words = new AVLTree <String> () ;
	}

	/**
	 * Creates a Dictionary object containing all words from the 
	 * listOfWords passed as a parameter.
	 * 
	 * @param listOfWords the list of words to be stored in the newly created 
	 * Dictionary object
	 */
	public Dictionary ( ArrayList <String> listOfWords ) {
		words = new AVLTree <String> ();

		for( String word : listOfWords ){
			words.insert( word );
		}
	}
	
	/**
	 * Checks whether or not a word is in a Dictionary
	 * Object
	 * 
	 * @param word the word to be looked for
	 * @return boolean true if the word is in the Dictionary
	 * false otherwise
	 */
	public boolean findWord ( String word ) {
		return words.contains( word );
	}

	/**
	 * Checks whether or not a prefix is in the
	 * Dictionary
	 * Utitlizes the recursive findPrefix(String prefix, AVLNode<String> node)
	 * method
	 * 
	 * @param prefix the prefix to be looked for
	 * @return a boolean value representing wether
	 * or not the prefix was found true if yes false otherwise
	 */
	public boolean findPrefix ( String prefix ){
		return findPrefix( prefix, words.root );
	}
	
	/**
	 * This method recursively searches an AVLTree for
	 * a given prefix. 
	 * 
	 * @param 
	 * 	prefix The prefix to be looked for
	 * 	node The node of the AVLTree to start the search
	 *	at
	 * @return a boolean value representing the presence 
	 * of the prefix true if it is in the AVLTree or false
	 * if it is not
	 */
	private boolean findPrefix ( String prefix, AVLNode<String> node ) {
		//just like binary search 
		if( node == null ){
			return false;
		}
		else{
			
			if( node.getData().startsWith( prefix ) ){
				return true;
			}
			//traverse to the left
			else if( prefix.compareTo( node.getData() )  < 0 ){
				return findPrefix( prefix, node.getLeft() );
			}
			//traverse to the right
			else if( prefix.compareTo( node.getData() ) > 0 ){
				return findPrefix( prefix, node.getRight() );
			}
			//found it
			return true;
		}
	}	
}
