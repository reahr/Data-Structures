import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

/**
 * Created by reahr on 4/18/2017.
 */
public class TreeCollection extends MyBST<Tree> {

    //to keep track of tree counts in boroughs as they are added or removed
    private HashMap<String, Integer> boroCount=new HashMap<String, Integer>();
    ArrayList<String> species = new ArrayList<String>(); //to keep track of unique tree species
    private ArrayList<Integer> speciesCount= new ArrayList<Integer>();

    //default constructor creates empty list
    public TreeCollection() {
    }

    public HashMap<String, Integer> getBoroCount() {
        return boroCount;
    }

    public int getTotalNumberOfTrees() {
        return this.size;
    }

    public int getCountByTreeSpecies(String speciesName) {
        speciesName = speciesName.toLowerCase().trim();
        ArrayList<String> speciesMatched = (ArrayList<String>) this.getMatchingSpecies(speciesName);
        int countByTreeSpecies = 0;
        for (int i = 0; i < speciesMatched.size(); i++) {
            countByTreeSpecies += recgetCountByTreeSpecies(root, speciesMatched.get(i));
        }
        return countByTreeSpecies;
    }

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
        return boroCount.get(boroName);
    }

    @Override
    public boolean add(Tree tree) {
        super.add(tree);
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
            Integer count= speciesCount.get(index)+1;
            speciesCount.set(index, count);
        }

        return true;
    }

    @Override
    public boolean remove(Object o) {
        super.remove(o);

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


}
