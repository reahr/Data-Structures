import java.util.ArrayList;

/**
 * This class creates a TreeList object that contains Tree objects
 *
 * @author Reah Rajmangal
 * @version February 14, 2017
 */

public class TreeList extends ArrayList<Tree>{

    //default [no-arg] constructor provided
    public TreeList(){}

    /**
     * Get total amount of Tree objects in TreeList object
     *
     * @return an integer that is the total number of Tree objects in TreeList object
     */
    public int getTotalNumberOfTrees(){
        return this.size();
    }

    /**
     * Gets total amount of trees in a specific NYC borough
     *
     * @param boroname String that represents where Tree objects are "located"
     *
     * @return an integer that is the total number of Tree objects located in borough provided
     */
    public int getCountByBorough (String boroname){
        int countByBorough=0;
        for (int i=0; i < this.size(); i++){
            if (this.get(i).getBoroname().equalsIgnoreCase(boroname)){
                countByBorough++;
            }
        }
        return countByBorough;
    }

    /**
     * Gets total amount of trees based on a specific tree species
     *
     * @param speciesName String that represents a tree species
     *
     * @return an integer that is the total number of Tree objects that are of the species provided
     */
    public int getCountByTreeSpecies (String speciesName){
        int countBySpecies=0;
        for (int i=0; i < this.size(); i++){
            if (this.get(i).getSpc_common().equalsIgnoreCase(speciesName)){
                countBySpecies++;
            }
        }
        return countBySpecies;
    }

    /**
     * Gets total amount of trees based on a specific tree species and borough
     *
     * @param speciesName String that represents a tree species
     * @param boroname String that represents where Tree objects are "located"
     *
     * @return an integer that is the total number of Tree objects that are of the species provided and
     *      in the borough provided
     */
    public int getCountByTreeSpeciesBorough(String speciesName, String boroname){
        int countByTreeSpeciesBorough=0;
        for (int i=0; i < this.size(); i++){
            if (this.get(i).getBoroname().equalsIgnoreCase(boroname) &&
                    this.get(i).getSpc_common().equalsIgnoreCase(speciesName)){
                countByTreeSpeciesBorough++;
            }
        }
        return countByTreeSpeciesBorough;

    }

    /**
     * Gets the different tree species that match a given species
     *
     * @param speciesName String that represents a tree species
     *
     * @return a String ArrayList object that contains all the matched tree species based off of the species provided
     */
    public ArrayList<String> getMatchingSpecies (String speciesName){
        speciesName=speciesName.trim(); //just in case TreeList is created manually instead of by data from input file
        ArrayList<String> matchingSpecies=new ArrayList<String>();
        for (int i=0; i < this.size(); i++){
            String treeSpecies= this.get(i).getSpc_common().toLowerCase();
            //find if substring of an actual species
            //if so, check to see if that species is in matched list, if not, then add it
            if (treeSpecies.contains(speciesName.toLowerCase()) && !matchingSpecies.contains(treeSpecies)){
                matchingSpecies.add(treeSpecies);
            }
        }
        return matchingSpecies;
    }

    /**
     * Creates a String for TreeList object with all its Tree objects' Tree ID,
     *      Species, Status Borough, and Zipcode
     *
     * @return a String that represents a list of all the Tree objects' with their information
     */
    public String toString(){
        String listOfTrees= "";
        for (int i=0; i< this.size(); i++){
            Tree tree= this.get(i);
            listOfTrees+=tree.toString()+"\n";
        }
        return listOfTrees;
    }
}
