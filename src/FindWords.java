package proj2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class provides the user interface for Find Words game.
 * The program needs to be called with a single command line 
 * argument that contains the file name of a dictionary to
 * be used in the game.
 * The user is prompted for a set of letters and the program
 * produces all words that can be created from those letters.
 * 
 * @author Joanna Klukowska
 *
 */
public class FindWords {

	/**
	 * This method runs when the program starts. It validates the file name and 
	 * reads its content. It creates a Dictionary object to be used in the game.
	 * It obtains the collection of letters from the user and validates it. 
	 * This method displays the final results to the terminal window. 
	 * 
	 * @param args an array containing command line argument,
	 *             the program expects one command line argument
	 *             that contains the filename of a dictionary to be used
	 */
	public static void main(String[] args) {
		
		//validate existence of command line arguments
		if (args.length < 1 ) {
			System.err.printf("Error: invalid number of arguments.\n"
					+ "Usage:\n\tFindWords dictionaryFile\n\n");
			System.exit(1);
		}
		
		//verify existence of the dictionary file 
		File dictFile = new File(args[0]);
		if (!dictFile.canRead()) {
			System.err.printf("Error: cannot read the dictionary file %s\n\n",
					args[0]);
			System.exit(1);
		}
		
		//open the dictionary file for reading
		Scanner dictIn = null;
		try {
			 dictIn = new Scanner (dictFile);
		} catch (FileNotFoundException e) {
			System.err.printf("Error: cannot read the dictionary file %s\n\n",
					args[0]);
			System.exit(1);
		}
		
		//read in all the words into an ArrayList object
		ArrayList<String> dictWords = new ArrayList<String> ();
		while (dictIn.hasNext()) {
			dictWords.add(dictIn.next());
		}
		//create dictionary object
		Dictionary dict = new Dictionary(dictWords);
		
		
		/**********************************************************************/
		
		//prompt user for letters to be used
		System.out.printf("Enter letters (3-1000) to be used. Do not use spaces "
				+ "or any other non-letters. \n"
				+ "Hit enter to finish.\n\n");
		Scanner in = new Scanner (System.in);
		String lettersToUse = in.nextLine();
		
		//create a LetterBag object
		LetterBag letters = null ;
		
		try {
			letters = new LetterBag(lettersToUse);
		} catch (IllegalArgumentException e) {
			System.err.printf("\t%s\n\n", e.getMessage() );
			System.exit(-1);
		}
		
		//get a list of all words consisting of the given letters
		ArrayList <String> words  = letters.getAllWords( dict );
		
		//print the results 
		System.out.printf("There are %d words containing your letters: \n", words.size());
		for (int i = 0; i < words.size(); i++) {
			System.out.printf("\t%s%n", words.get(i));
		}
		
		in.close();
		
	}

}
