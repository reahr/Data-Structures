import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

/**
 * The TreeCollection class should be used to store all the Tree objects
 *
 * @author Reah Rajmangal
 * @version April 22, 2017
 */
public class TreeCollection extends MyBST<Tree> {

    //to keep track of tree counts in boroughs as they are added or removed
    private HashMap<String, Integer> boroCount=new HashMap<String, Integer>();
    ArrayList<String> species = new ArrayList<String>(); //to keep track of unique tree species
    //cannot use HashMap for this because of need to select multiple "keys"
    private ArrayList<Integer> speciesCount= new ArrayList<Integer>();

    //default constructor creates empty list
    public TreeCollection() {}

    /**
     * Returns a HashMap that contains all boroughs and corresponding tree counts
     *
     * @return a HashMap that contains all boroughs and their corresponding tree counts
     */
    public HashMap<String, Integer> getBoroCount() {
        return boroCount;
    }


    /**
     * Get total amount of Tree objects in this TreeCollection object
     *
     * @return an integer that is the total number of Tree objects in this TreeCollection object
     */
    public int getTotalNumberOfTrees() {
        return this.size;
    }

    /**
     * Gets total amount of trees based on a specific tree species
     *
     * @param speciesName String that represents a tree species
     *
     * @return an integer that is the total number of Tree objects that are of the species provided
     */
    public int getCountByTreeSpecies(String speciesName) {
        speciesName = speciesName.toLowerCase().trim();
        ArrayList<String> speciesMatched = (ArrayList<String>) this.getMatchingSpecies(speciesName);
        int countByTreeSpecies = 0;
        for (int i = 0; i < speciesMatched.size(); i++) {
            countByTreeSpecies += recgetCountByTreeSpecies(root, speciesMatched.get(i));
        }
        return countByTreeSpecies;
    }

    /**
     * Helper method of getCountByTreeSpecies(String speciesName) that traverses through this TreeCollection
     * efficiently to find trees with specified species
     *
     * @param treeNode - current node that is being visited and comparing data to provided name
     * @param name - Species name to be found
     *
     * @return int - the number of trees that match the species name
     */
    private int recgetCountByTreeSpecies(BSTNode<Tree> treeNode, String name) {
        int addMe = 0;
        if (treeNode == null) return 0;
        //else get data out of node and get its spc name
        Tree tree = treeNode.getData();
        String treeSpcName = tree.getSpc_common().toLowerCase().trim();
        if (treeSpcName.compareTo(name) < 0) {
            addMe += recgetCountByTreeSpecies(treeNode.getRight(), name);
        } else if (treeSpcName.compareTo(name) > 0) {
            addMe += recgetCountByTreeSpecies(treeNode.getLeft(), name);
        } else {
            //get search from both children if necessary
            addMe += 1 + recgetCountByTreeSpecies(treeNode.getRight(), name)
                    + recgetCountByTreeSpecies(treeNode.getLeft(), name);
        }
        return addMe;
    }

    /**
     * Gets the different tree species that match a given species
     *
     * @param speciesName String that represents a tree species
     *
     * @return a String ArrayList object that contains all matched tree species based off of the species provided
     */
    public Collection<String> getMatchingSpecies(String speciesName) {
        speciesName = speciesName.toLowerCase().trim(); //just in case TreeList is created manually instead of by data from input file
        //ArrayList is a collection (cast to ArrayList if necessary)
        ArrayList<String> matchingSpecies = new ArrayList<String>();
        for (int i = 0; i < species.size(); i++) {
            String treeSpecies = species.get(i);
            //find if substring of an actual species
            //if so, check to see if that species is in matched list, if not, then add it
            if (treeSpecies.contains(speciesName) && !matchingSpecies.contains(treeSpecies)) {
                matchingSpecies.add(treeSpecies);
            }
        }
        return matchingSpecies;
    }

    /**
     * Gets total amount of trees based on a specific tree species and borough
     *
     * @param speciesName String that represents a tree species
     * @param boroName String that represents where src.Tree objects are "located"
     *
     * @return an integer that is the total number of src.Tree objects that are of the species provided and
     *      in the borough provided
     */
    public int getCountByTreeSpeciesBorough(String speciesName, String boroName) {
        speciesName = speciesName.toLowerCase().trim();
        boroName = boroName.toLowerCase().trim();
        ArrayList<String> speciesMatched = (ArrayList<String>) this.getMatchingSpecies(speciesName);
        int countByTreeSpecies = 0;
        for (int i = 0; i < speciesMatched.size(); i++) {
            countByTreeSpecies += getCountByBoroTreeSpecies(root, speciesMatched.get(i), boroName);
        }
        return countByTreeSpecies;
    }

    /**
     * Helper method of getCountByTreeSpeciesBorough(String speciesName, String boroName)
     * that traverses through this TreeCollection efficiently to find trees with specified species AND boroName provided
     *
     * @param treeNode - current node that is being visited and comparing data to provided name
     * @param name - Species name to be found
     * @param boro - Borough of tree that has matching species
     *
     * @return int - the number of trees that match the species name
     */
    private int getCountByBoroTreeSpecies(BSTNode<Tree> treeNode, String name, String boro) {
        int addMe = 0;
        if (treeNode == null) return 0;

        //else get data out of node and get its spc name
        Tree tree = treeNode.getData();
        String treeSpcName = tree.getSpc_common().toLowerCase();
        if (treeSpcName.compareTo(name) < 0) {
            addMe += getCountByBoroTreeSpecies(treeNode.getRight(), name, boro);
        } else if (treeSpcName.compareTo(name) > 0) {
            addMe += getCountByBoroTreeSpecies(treeNode.getLeft(), name, boro);
        } else if (treeSpcName.compareTo(name) == 0) {
            if (tree.getBoroname().toLowerCase().trim().equals(boro)) {
                addMe += 1 + getCountByBoroTreeSpecies(treeNode.getRight(), name, boro)
                        + getCountByBoroTreeSpecies(treeNode.getLeft(), name, boro);
            } else {
                addMe += getCountByBoroTreeSpecies(treeNode.getRight(), name, boro)
                        + getCountByBoroTreeSpecies(treeNode.getLeft(), name, boro);
            }
        }
        return addMe;
    }

    /**
     * Gets total amount of trees in a specific NYC borough
     *
     * @param boroName String that represents where src.Tree objects are "located"
     *
     * @return an integer that is the total number of src.Tree objects located in borough provided
     */
    public int getCountByBorough(String boroName) {
        boroName = boroName.trim().toLowerCase();
        return boroCount.get(boroName);
    }

    /**
     * Overrides MyBST add(E e) method so that it additionally keeps track of types of species, count species and
     * boroughs
     *
     * @param tree - tree to be added
     *
     * @return true if Tree was added into this TreeCollection
     */
    @Override
    public boolean add(Tree tree) {
        boolean addOrNo= super.add(tree);
        if (!addOrNo) return false;

        String boroNameOfTree=tree.getBoroname().toLowerCase().trim();
        String spcNameOfTree=tree.getSpc_common().toLowerCase().trim();

        if (boroCount.containsKey(boroNameOfTree)) {
            boroCount.put(boroNameOfTree, boroCount.get(boroNameOfTree)+1);
        }else boroCount.put(boroNameOfTree, 1);

        if (!species.contains(spcNameOfTree)) {
            species.add(spcNameOfTree);
            speciesCount.add(1); //add at same index
        }else {
            int index= species.indexOf(spcNameOfTree);
            speciesCount.set(index, speciesCount.get(index)+1);
        }
        return true;
    }

    /**
     * Overrides MyBST remove(Object o) method so that it additionally keeps track of types of species, count species
     * and boroughs
     *
     * @param o - tree (casted from Object) to be added
     *
     * @return true if Tree was removed from this TreeCollection
     */
    @Override
    public boolean remove(Object o) {
        boolean removeOrNo=super.remove(o);
        if (!removeOrNo) return false;

        //if no possible class cast exception:
        String treeBoro= ((Tree) o).getBoroname().toLowerCase().trim();
        String treeSpc= ((Tree) o).getSpc_common().toLowerCase().trim();
        boroCount.put(treeBoro, boroCount.get(treeBoro)-1);

        int indexOfSpeciesName= species.indexOf(treeSpc);
        int count= speciesCount.get(indexOfSpeciesName)-1;
        speciesCount.set(indexOfSpeciesName, count);
        if (count==0) species.remove(treeSpc);

        return true;
    }

    /**
     * Returns a string representation of this TreeCollection in the form of a list with each element String value
     * follows the other line by line
     *
     * @return a string representation of this TreeCollection
     */
    @Override
    public String toString(){
        return toString (this.root);
    }

    /**
     * Helper method of toString() which performs an inOrder Traversal that visits and builds onto each return String
     *
     * @param node - node to be visited and added onto the ultimate String
     *
     * @return a string representation of this collection
     */
    private String toString (BSTNode<Tree> node){
        String element="";
        if (node==null) return "";
        element+= toString(node.getLeft());

        //process node
        //take away comma if last element
        if (node.getData().equals(this.last())) element+= String.valueOf(node.getData());
        else element+= String.valueOf(node.getData())+"\n";

        element+= toString(node.getRight());
        return element;
    }


}
