import java.util.ArrayList;
import java.util.Collections;

/**
 * The Dictionary class represents the sequence of letters from which all permutations and words
 *      should be constructed.
 *
 * @author Reah Rajmangal
 * @version March 2, 2017
 */

public class Permutations {

    private String letters;

    public Permutations (String letters) throws IllegalArgumentException{

        if (letters==null || letters.isEmpty() )
            throw new IllegalArgumentException("cannot compute any words, only provide letters.\n");

        int lengthOfLetters= letters.length();
        for (int i=0; i < lengthOfLetters; i++){
            if (!Character.isLetter(letters.charAt(i))) {
                throw new IllegalArgumentException("cannot compute any words, only provide letters.\n");
            }
        }

        this.letters=letters.toLowerCase();
    }

    /**
     * Gets the String letters from Permutations object
     *
     * @return a String object that will be permutated on
     */
    public String getLetters(){
        return this.letters;
    }

    /**
     * Check permutations of a given Permutation object by calling a helper method that takes an empty String
     *      to build permutations, letters of the Permutation object and ArrayList to add permutations
     *
     * @return an ArrayList object with all possible permutations
     *
     * @throws OutOfMemoryError possibly if Permutation's has a length greater than 10 letters
     */
    public ArrayList<String> getAllPermutations() {
        ArrayList<String> listOfPermutations= new ArrayList<String>();
        getAllPermutations(listOfPermutations, "", this.letters );
        Collections.sort(listOfPermutations); //alphabetically sort
        return listOfPermutations;
    }

    /**
     * Get all permutations of Permutation object using approach similar to getAllPermutations() (with a helper method)
     *      but also uses a Dictionary object to check if a permutation is a word.
     *
     * @param dictionary a Dictionary object that is used to check if completed permutations are words
     *
     * @return an ArrayList object with all possible permutations that are words
     */
    public ArrayList<String> getAllWords (Dictionary dictionary){
        ArrayList<String> listOfWords= new ArrayList<String>();
        getAllWords( dictionary, listOfWords, "", letters);
        Collections.sort(listOfWords); //alphabetically sort
        return listOfWords;
    }

    /**
     * Check permutations of a given Permutation object using a recursive call similiar to n! where n is the length
     *      of Permutation object's letters
     *
     * For example, String "reah", at index 1 in main for loop,
     *      add "e" to empty prefix and permutate on leftover "rah"
     *      6 recursive calls (3!) will be called under this for loop for index 1 to build up 6 permutated Strings
     *      that will be added to Arraylst.
     *
     * @param listOfPermutations an (empty) Arraylist that will be contained with all possible permutations
     * @param finishedSequence a (empty) String that will be built up to a permutated String
     * @param originalSequence a String that is to be permutated and will be taken apart
     *      letter-by-letter through recursive calls
     */
    private void getAllPermutations (ArrayList<String>listOfPermutations,
                                     String finishedSequence, String originalSequence){

        //base case: use subword equal to 0 (no more permutations of sub-word are needed)
        if (originalSequence.length()==0 && !listOfPermutations.contains(finishedSequence)){
            listOfPermutations.add(finishedSequence);
        }

        else{
            //create a for loop that iterates through original sequence string indexes
            for (int i=0; i< originalSequence.length(); i++){
                //in order to create different permutations of the word, allow recursive calls on "sub-word"
                //build prefix by adding on current string index of word/subword
                String prefix=finishedSequence+originalSequence.charAt(i);

                //create a new "word" or original sequence that does not include current index and do permutation on it
                String beforePrefix= originalSequence.substring(0, i);
                String afterPrefix= originalSequence.substring(i + 1); //length of string as index included
                String subword = beforePrefix+afterPrefix;

                //subword permutations will be added to original prefix once their own recursive calls are completed
                getAllPermutations(listOfPermutations, prefix , subword);
            }
        }
    }

    /**
     * Check permutations of a given Permutation object using a recursive call similiar to n! where n is the length
     *      of Permutation object but checks before each recursive call if each (potential) build-up of permutated words
     *      are prefixes
     *
     * @param dictionary a Dictionary object that is checked to verify if potential permutations and permutations are
     *      prefixes and words respectively
     * @param listOfWords an (empty) Arraylist that will be contained with all possible permutations that are words
     * @param finishedSequence a (empty) String that will be built up to a permutated String
     * @param originalSequence a String that is to be permutated and will be taken apart
     *      letter-by-letter through recursive calls
     */
    private void getAllWords (Dictionary dictionary, ArrayList<String>listOfWords,
                              String finishedSequence, String originalSequence ){

        //base case: use subword equal to 0 (no more permutations of sub-word are needed)
        //check if the permutation is a word because it can only be a prefix but not an actual word
        if (originalSequence.length()==0 && dictionary.isWord(finishedSequence)
                && !listOfWords.contains(finishedSequence)){
            listOfWords.add(finishedSequence);
        }

        else{
            //create a for loop that iterates through original sequence string indexes
            for (int i=0; i< originalSequence.length(); i++){
                //in order to create different permutations of the word, allow recursive calls of "sub-word"
                //build prefix by adding on current string index of word/subword
                String prefix=finishedSequence+originalSequence.charAt(i);

                //check if prefix is an actual prefix based on dictionary if not go to next iteration
                //(ignore all of the sub-words with that prefix)
                if (!dictionary.isPrefix(prefix)) continue;

                //create a new "word" or original sequence that does not include current index and do permutation on it
                String beforePrefix= originalSequence.substring(0, i);
                String afterPrefix= originalSequence.substring(i + 1); //length of string as an index does exist
                String subword = beforePrefix+afterPrefix;

                //subword permutations will be added to original prefix once their own recursive calls are completed
                getAllWords(dictionary, listOfWords, prefix , subword);
            }
        }
    }
}

