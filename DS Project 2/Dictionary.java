import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The Dictionary class represents the collection of words read in from input file
 *      and is responsible for performing queries in the dictionary and storing all words in an ArrayList.
 *
 * @author Reah Rajmangal
 * @version March 2, 2017
 */

public class Dictionary {

    private ArrayList<String> dictionary= new ArrayList<String>();

    public Dictionary (File f) throws IllegalArgumentException {

        if (f==null) throw new IllegalArgumentException("File cannot be null.");

        Scanner dictionaryFile=null;

        //attempt to read file
        try {
            dictionaryFile= new Scanner (f);
        }catch (FileNotFoundException e){ //if does not exist/cannot be read
            throw new IllegalArgumentException("Error: "+f+" does not exist or cannot be read.");
        }

        //add to dictionary the word that is on each line from file
        while (dictionaryFile.hasNextLine()) {
            String word = dictionaryFile.nextLine();
            this.dictionary.add(word);
        }

        dictionaryFile.close();
    }

    /**
     * Gets the ArrayList containing all words from Dictionary file
     *
     * @return a String ArrayList object that contains all words from dictionary file and thus in this Dictionary object
     */
    public ArrayList<String> getDictionary(){
        return this.dictionary;
    }

    /**
     * Checks if a given String is a word using a helper method that does binary search on
     * this Dictionary object for word
     *
     * @param str a String that will be verified if it is a word or not
     *
     * @return a boolean if String is a word or not
     */
    public boolean isWord(String str){
        if (binarySearchForWord(str, 0, (this.dictionary.size()-1))==-1) return false;
        return true;
    }

    /**
     * Checks if a given String is a prefix using a helper method that does binary search on
     * this Dictionary object for prefix
     *
     * @param str a String that will be verified if it is a prefix or not
     *
     * @return a boolean if String is a prefix or not
     */
    public boolean isPrefix (String str){
        //use helper method that takes index 0 and last index of dictionary's ArrayList
        if (binarySearchForPrefix(str, 0, (this.dictionary.size()-1))==-1) return false;
        return true;
    }

    /**
     * Searches Dictionary object using recursive binary search for a given String (based off of Joanna's code demo)
     *
     * @param str a String that will be verified if it is a word or not
     * @param begin the beginning index of this Dictionary's ArrayList to be searched through
     * @param end the end search index of this Dictionary's ArrayList
     *
     * @return an integer that represents the index of where the word is located in Dictionary object's ArrayList
     */
    private int binarySearchForWord(String str, int begin, int end){

        if (begin > end) return -1;

        int middle= (begin+end)/2;

        if (this.dictionary.get(middle).equals(str)) return middle;

        //if middle is greater (alphabetically) than str than check from beginning to middle (not including middle)
        else if (this.dictionary.get(middle).compareTo (str) > 0) return binarySearchForWord(str, begin, middle-1 );

        //else do from middle to end (not including middle)
        return binarySearchForWord(str, middle+1, end);
    }

    /**
     * Searches Dictionary object for at least one prefix match using recursive binary search for a given String
     * (based off of Joanna's code demo)
     *
     * @param str a String that will be verified if it is a prefix or not
     * @param begin the beginning index of this Dictionary's ArrayList to be searched through
     * @param end the end search index of this Dictionary's ArrayList
     *
     * @return an integer that represents the index of where the first prefix match is located
     *      in Dictionary object's ArrayList
     */
    private int binarySearchForPrefix(String str, int begin, int end){

        if (begin > end) return -1;

        int middle= (begin+end)/2;

        if (this.dictionary.get(middle).startsWith(str)) return middle;

        //if middle is greater (alphabetically) than str than check from beginning to middle (not including middle)
        else if (this.dictionary.get(middle).compareTo (str)>0) return binarySearchForPrefix(str, begin, middle-1 );

        //else do from middle to end (not including middle)
        return binarySearchForPrefix(str, middle+1, end);
    }
}