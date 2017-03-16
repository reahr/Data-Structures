import java.io.File;
import java.util.ArrayList;

/**
 * The class runs a program that takes two command line arguments from which words can be constructed based on a
 *      provided Dictionary and String of letters
 *
 * @author Reah Rajmangal
 * @version March 2, 2017
 */

public class ScrabbleHelper {
    public static void main(String[] args){

        //Check if command line args equal to 2 (if not checked
        // will cause following command line args to be ignored if intended to be part of String)
        if (args.length !=2) {
            System.err.println("Error: incorrect usage.");
            System.err.println("Usage: java ScrabbleHelper [inputFile] [letters]");
            System.exit(1);
        }

        File f = new File(args[0]);
        String letters=args[1];

        //create new Dictionary object using file, if file cannot be found/read, print out message and exit
        Dictionary dictionary = null;
        try {
            dictionary = new Dictionary(f);
        } catch (IllegalArgumentException e) {
            System.err.printf(e.getMessage());
            System.exit(1);
        }

        //create new Permutations object using args[1], if it contains characters besides letters,
        //print out message and exit
        Permutations permutations = null;
        try {
            permutations = new Permutations(letters);
        } catch (IllegalArgumentException e) {
            System.err.printf("Error for %s: " + e.getMessage(), letters);
            System.exit(1);
        }

        ArrayList<String> listOfWords = permutations.getAllWords(dictionary);

        if (listOfWords.size()>1) System.out.printf("Found %d words:\n", listOfWords.size());
        else if (listOfWords.size()==1) System.out.printf("Found %d word:\n", listOfWords.size());
        else if (listOfWords.size()==0) System.out.println("No words found.");

        for (int i = 0; i < listOfWords.size(); i++) {
            System.out.printf("     %s\n", listOfWords.get(i));
        }
    }
}