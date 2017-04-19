import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by reahr on 4/18/2017.
 */
public class TreeCollection extends MyBST<Tree> {

    public String[] getBoro() {
        return boro;
    }

    private String[] boro = {"manhattan", "bronx", "brooklyn", "queens", "staten island"};

    public int[] getBoroTotals() {
        return boroTotals;
    }

    private int[] boroTotals = {0, 0, 0, 0, 0};
    private ArrayList<String> species = new ArrayList<String>(); //to keep track of unique tree species

    //default constructor creates empty list
    public TreeCollection() {}

    public int getTotalNumberOfTrees() {
        return this.size;
    }

    public int getCountByTreeSpecies(String speciesName) {
        speciesName = speciesName.toLowerCase();
        ArrayList<String> speciesMatched = (ArrayList<String>) this.getMatchingSpecies(speciesName);
        int countByTreeSpecies = 0;
        for (int i = 0; i < speciesMatched.size(); i++) {
            countByTreeSpecies += getCount(root, speciesMatched.get(i));
        }
        return countByTreeSpecies;
    }

    private int getCount(BSTNode<Tree> treeNode, String name) {
        int addMe = 0;
        if (treeNode == null) return 0;
        //else get data out of node and get its spc name
        Tree tree = treeNode.getData();
        String treeSpcName = tree.getSpc_common().toLowerCase();
        if (treeSpcName.compareTo(name) < 0) {
            addMe += getCount(treeNode.getRight(), name);
        } else if (treeSpcName.compareTo(name) > 0) {
            addMe += getCount(treeNode.getLeft(), name);
        } else {

            addMe += 1 + getCount(treeNode.getRight(), name) + getCount(treeNode.getLeft(), name);
        }
        return addMe;
    }

    public Collection<String> getMatchingSpecies(String speciesName) {
        speciesName = speciesName.trim(); //just in case TreeList is created manually instead of by data from input file
        ArrayList<String> matchingSpecies = new ArrayList<String>();
        for (int i = 0; i < species.size(); i++) {
            String treeSpecies = species.get(i);
            //find if substring of an actual species
            //if so, check to see if that species is in matched list, if not, then add it
            if (treeSpecies.contains(speciesName.toLowerCase()) && !matchingSpecies.contains(treeSpecies)) {
                matchingSpecies.add(treeSpecies);
            }
        }
        return matchingSpecies;
    }

    public int getCountByTreeSpeciesBorough(String speciesName, String boroName) {
        speciesName = speciesName.toLowerCase();
        boroName = boroName.toLowerCase().trim();
        ArrayList<String> speciesMatched = (ArrayList<String>) this.getMatchingSpecies(speciesName);
        int countByTreeSpecies = 0;
        for (int i = 0; i < speciesMatched.size(); i++) {
            countByTreeSpecies += getCountByBoroTreeSpecies(root, speciesMatched.get(i), boroName);
        }
        return countByTreeSpecies;
    }

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

    public int getCountByBorough(String boroName) {
        boroName = boroName.trim().toLowerCase();
        for (int i = 0; i < boro.length; i++) {
            if (boroName.equalsIgnoreCase(boro[i])) return boroTotals[i];
        }
        return 0;
    }

    @Override
    public boolean add(Tree tree) {
        if (tree == null) {
            throw new NullPointerException("Specified element cannot be null.");
        } else if (this.contains(tree)) return false; //check if element already exists
        root = recAdd(root, tree);
        this.size++;

        //to keep count of borough
        for (int i = 0; i < boro.length; i++) {
            if (tree.getBoroname().equalsIgnoreCase(boro[i])) boroTotals[i]++;
        }

        if (!species.contains(tree.getSpc_common().toLowerCase()))
            species.add(tree.getSpc_common().toLowerCase());

        return true;
    }

    @Override
    public boolean remove(Object o) {
        if (o == null) throw new NullPointerException("Specified element cannot be null.");
        Tree tree = (Tree) o; //cast into type
        if (!this.contains(tree)) return false;
        else root = findToRemove(root, tree);
        String treeBoro= tree.getBoroname().toLowerCase();
        String treeSpc= tree.getSpc_common().toLowerCase();
        size--;

        for (int i = 0; i < boro.length; i++) {
            if (tree.getBoroname().equals(boro[i])) boroTotals[i]--;
        }

        //worst case scenario: check if TreeCollection contains a tree with removed tree's specific unique treespc
        //if no, we have to remove that tree species from the arraylist that contains unique species
        if (getCountByTreeSpecies(treeSpc)==0) species.remove(treeSpc);
        return true;
    }


}
