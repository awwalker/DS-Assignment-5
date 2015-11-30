import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class is designed to facilitate 
 * testing of the methods implemented in
 * the other classes of this project
 * @author Aaron Walker
 *
 */
public class Test extends AVLTree<Integer> {

	public static void main ( String[] args ){
		
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


		 
		/*
		BST<String> words = new BST<String>();
		ArrayList<String> w = new ArrayList<String>();
		String[] w1 = {"act", "acta", "ats", "cat", "cata", "catta", "catts", "sat", "scatt", "tac"};
		for(String word : w1){
			words.insert(word);
			w.add(word);
		}
		*/
	}
}
