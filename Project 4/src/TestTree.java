import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by reahr on 4/18/2017.
 */
public class TestTree {
    public static void main (String[] args) throws FileNotFoundException {

        //handle potential problems with command line arg, file existence and readability

        //check if all command line arguments are there
        if ( args.length < 1 ) {
            System.err.printf("ERROR: the program expects a file name as an argument.\n");
            System.err.printf("Usage: java NYCStreetTrees [fileName]\n" );
            System.exit(1);
        }

        //set path to first argument and create file
        String path = args[0];
        File fileTreeCSV = new File (path);

        //check if the file exists on disk and is readable
        if (!fileTreeCSV.exists()) {
            System.err.printf("ERROR: file %s does not exist.\n", args[0]);
            System.exit(1);
        }

        if (!fileTreeCSV.canRead()){
            System.err.printf("ERROR: file %s cannot be read.\n", args[0]);
        }

        //check if file is empty based on size
        if (fileTreeCSV.length()==0){
            System.err.printf("ERROR: %s is empty.", args[0]);
            System.exit(1);
        }

        //otherwise, create a Scanner object for the file
        Scanner fileInput = new Scanner (fileTreeCSV);

        //create inventory to add trees from file using TreeList class
        TreeCollection treeCollection = new TreeCollection();

        for (int i = 0; fileInput.hasNextLine(); i++) {

            //create ArrayList that splits each line
            String newLine= fileInput.nextLine();
            ArrayList<String> treeData = splitCSVLine(newLine);

            if (i == 0) {
                //read first line which are headers but do not parse data from it
                //verify headers are correct so that each column in each line has entries that correspond with each header
                //if headers are incorrect then data will not be parsed accurately into Tree--exit program
                String[] treeSpecifications= {"tree_id","tree_dbh","status","health","spc_common","zipcode","boroname","x_sp","y_sp"};
                if (!treeData.get(0).equals(treeSpecifications[0]) || !treeData.get(3).equals(treeSpecifications[1]) ||
                        !treeData.get(6).equals(treeSpecifications[2]) || !treeData.get(7).equals(treeSpecifications[3]) ||
                        !treeData.get(9).equals(treeSpecifications[4]) || !treeData.get(25).equals(treeSpecifications[5] ) ||
                        !treeData.get(29).equals(treeSpecifications[6] ) || !treeData.get(39).equals(treeSpecifications[7]) ||
                        !treeData.get(40).equals(treeSpecifications[8])){
                    System.err.printf("ERROR: Headers for %s are invalid for use of tree database.", args[0]);
                    System.exit(1);
                }
                continue;
            }

            //if line does not have have 41 data entries, silently skip over line
            //put after header verification because if headers do not match up due to a missing column,
            //program will exit anyways
            if (treeData.size()!=41){
                //following line for debugging purposes
                System.err.println("Error parsing line " + (i+1) + " from " + args[0]);
                continue;
            }

            //declare constructor arguments for Tree object
            int id, diam, zip;
            String status, health, spc, boro;
            double x, y;

            //handle any line after header from file that may not have the correct data type for a specified line
            try {
                //parse data from 9 selected columns that have constructor arguments
                id = Integer.parseInt(treeData.get(0));
                diam = Integer.parseInt(treeData.get(3));
                status = treeData.get(6);
                health = treeData.get(7);
                spc = treeData.get(9);
                zip = Integer.parseInt(treeData.get(25));
                boro = treeData.get(29);
                x = Double.parseDouble(treeData.get(39));
                y = Double.parseDouble(treeData.get(40));
            } catch (IllegalArgumentException e) {
                //following line for debugging purposes
                System.err.println("Error parsing line " + (i+1) + " from " + args[0]);
                continue;
            }

            //handle any errors thrown by Tree class for invalid arguments
            try {
                //create tree object with the extracted data, then add it to inventory if not in list already
                Tree newTree = new Tree(id, diam, status, health, spc, zip, boro, x, y);
                if(!treeCollection.contains(newTree)) {
                    treeCollection.add(newTree);
                }
                //If desired, uncomment this for alphabetical listing of species matched
                //Collections.sort(treeList);
            }catch (IllegalArgumentException e){
                //following line for debugging purposes
                System.err.println("ERROR: Line " + (i+1) + ": "+e.getMessage());
                continue;
            }
        }

        //close file
        fileInput.close();

        System.out.println(treeCollection.getTotalNumberOfTrees());
        System.out.println(treeCollection.getCountByBorough("queens"));
        System.out.println(treeCollection.getCountByTreeSpecies(""));
    }

    public static  ArrayList<String> splitCSVLine (String textLine){
        ArrayList<String> entries = new ArrayList<String>();
        int lineLength = textLine.length();
        StringBuffer nextWord = new StringBuffer();
        char nextChar;
        boolean insideQuotes= false;
        boolean insideEntry=false;

        //iterate over all chars in the textLine
        for (int i=0; i < lineLength; i++){
            nextChar=textLine.charAt(i);

            if (nextChar == '"' || nextChar == '\u201C' || nextChar =='\u201D') {
                if (insideQuotes){
                    //change insideQuotes flag when nextChar is a quote
                    insideQuotes=false;
                    insideEntry=false;
                }
                else {
                    insideQuotes=true;
                    insideEntry=true;
                }
            }
            else if (Character.isWhitespace(nextChar)){
                if (insideQuotes || insideEntry){
                    //add to current entry (word(s) that are elements in Arraylist)
                    nextWord.append(nextChar);
                }
                else { //skip all spaces between entries
                    continue;
                }

            }
            else if (nextChar==','){
                if (insideQuotes) //find comma inside an entry
                    nextWord.append(nextChar);
                else { //end entry found
                    insideEntry=false;
                    entries.add(nextWord.toString());
                    nextWord= new StringBuffer();
                }
            }
            else {
                //add all other characters to the nextWord (current word)
                nextWord.append(nextChar);
                insideEntry=true;
            }
        }

        //add the last word (assuming not empty)
        //trim the white space before adding to the list;
        //**no need to trim any words in Arraylist created by splitLineCSV afterwards**
        if (!nextWord.toString().equals("")){
            entries.add(nextWord.toString().trim());
        }

        return entries;
    }

}
