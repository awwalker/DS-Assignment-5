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
public class Dictionary extends AVLTree<String> implements DictionaryInterface{
	//actual storage for the words
	private AVLTree< String > words;


	/**
	 * Creates an empty Dictionary object (no words).
	 */
	public Dictionary ( ) {
		words = new AVLTree < String > () ;
	}

	/**
	 * Creates a Dictionary object containing all words from the 
	 * listOfWords passed as a parameter.
	 * 
	 * @param listOfWords the list of words to be stored in the newly created 
	 * Dictionary object
	 */
	public Dictionary ( ArrayList < String > listOfWords ) {
		words = new AVLTree < String > ();

		for( String word : listOfWords ){
			words.insert(word);
		}
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

	private boolean findPrefix (String prefix, AVLNode<String> node ) {
		
		if( node == null ){
			return false;
		}
		else{
			
			if(node.getData().startsWith(( prefix )) ){
				return true;
			}

			else if( prefix.compareTo( node.getData() )  < 0 ){
				return findPrefix( prefix, node.getLeft() );
			}

			else if( prefix.compareTo( node.getData() ) > 0 ){
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
