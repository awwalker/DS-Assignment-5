/**
 * This interface defines the methods needed
 * to be implemented by the Dictionary class
 * @author Joanna Klukowska
 */

public interface DictionaryInterface {
	/**
	 * This method determines if a given word 
	 * is in this Dictionary.
	 * @param word the word to be checked
	 * @return true of the word is in this Dictionary,
	 * false otherwise
	 */
	boolean findWord ( String word );
	
	/**
	 * This method determines if a given
	 * prefix is a prefix of a word that exists in
	 * this Dictionary.
	 * @param prefix the prefix to be checked  
	 * @return true if the prefix is in this Dictionary,
	 * false otherwise 
	 */
	boolean findPrefix ( String prefix ); 
}
