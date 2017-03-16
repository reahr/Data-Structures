/**
 * This class stores information on a particular tree that grows in NYC.
 *
 * @author Reah Rajmangal
 * @version February 14, 2017
 */

public class Tree implements Comparable<Tree>{

    //data fields are private
    //Getters (public) to allow user to get data field information; setters (private) used ONLY in constructor
    private int tree_id, tree_dbh, zipcode;
    private String status, health, spc_common, boroname;
    private double x_sp, y_sp;

    public Tree (int id, int diam, String status, String health, String spc, int zip, String boro, double x, double y){
        setTree_id(id);
        setTree_dbh(diam);
        setStatus(status);
        setHealth(health);
        setSpc_common(spc);
        setZipcode(zip);
        setBoroname(boro);
        this.x_sp=x;
        this.y_sp=y;
    }

    /**
     * Sets Tree object's ID
     *
     * @param tree_id an integer representing tree identification number
     *
     * @throws IllegalArgumentException if tree object's id is negative
     *
     */
    private void setTree_id(int tree_id) {
        if (tree_id <0){
            throw new IllegalArgumentException("This tree has an invalid ID.");
        }
        this.tree_id = tree_id;
    }

    /**
     * Sets Tree object's diameter
     *
     * @param tree_dbh an integer representing tree diameter
     *
     * @throws IllegalArgumentException if tree object's id is negative
     */
    private void setTree_dbh(int tree_dbh) {
        if (tree_dbh < 0){
            throw new IllegalArgumentException("This tree has an invalid diameter");
        }
        this.tree_dbh = tree_dbh;
    }

    /**
     * Sets Tree object's health
     *
     * @param health A String representing Tree object's health
     *
     * @throws IllegalArgumentException if String health is not [case insensitive] "good", "fair", "poor", empty string, or null
     */
    private void setHealth(String health) {
        //Separate null String from other Strings because .equals works only on initialized Strings
        if (health==null){
            this.health=health;
            return;
        }
        //Create an array of accepted values and check through it to see if health is equal to an element in it
        String[] treeHealthList= {"Good","Fair","Poor",""};
        for (String treeHealthElement:treeHealthList){
            if (treeHealthElement.equalsIgnoreCase(health)) {
                this.health = health;
                return;
            }
        }
        throw new IllegalArgumentException("This tree's health is invalid.");
    }

    /**
     * Sets Tree object's status
     *
     * @param status A String representing Tree object's status
     *
     * @throws IllegalArgumentException if String status is not [case insensitive] "Alive", "Dead", "Stump", empty string, or null
     */
    private void setStatus(String status) {
        //Separate null String from other Strings because .equals works only on initialized Strings
        if (status==null){
            this.status=status;
            return;
        }
        //Create an array of accepted values and check through it to see if status is substring of element in list
        String[] treeStatusList= {"Alive","Dead","Stump",""};
        for (String treeStatusElement:treeStatusList){
            if (status.equalsIgnoreCase(treeStatusElement)) {
                this.status = status;
                return;
            }
        }
        throw new IllegalArgumentException("This tree's status is invalid.");
    }

    /**
     * Sets Tree object's species name
     *
     * @param spc_common A String representing Tree object's common species name
     *
     * @throws IllegalArgumentException if String spc_common is null
     */
    private void setSpc_common(String spc_common) {
        if (spc_common==null){
            throw new IllegalArgumentException("Tree's species type cannot be null");
        }
        this.spc_common = spc_common;
    }

    /**
     * Sets original tree object's zipcode, if zipcode is less than 5 digits
     *
     * @param zipcode an integer representing Zipcode of the Tree object
     *
     * @throws IllegalArgumentException if int zipcode is negative or more than 5 digits
     */
    private void setZipcode(int zipcode) {
        if (zipcode < 0 || zipcode > 99999){
            throw new IllegalArgumentException("Tree has an invalid zipcode.");
        }
        this.zipcode = zipcode;
    }

    /**
     * Sets Tree object's borough
     *
     * @param boro A String representing borough where Tree object resides
     *
     * @throws IllegalArgumentException if Tree object's boroname is not [case insensitive] "Manhattan", "Bronx", "Queens", "Brooklyn",
     * or "Staten Island"
     */
    private void setBoroname(String boro) {
        String[] treeBoroList= {"Queens","Brooklyn","Manhattan","Bronx", "Staten Island"};
        for (String treeBoroElement:treeBoroList){
            if (boro.equalsIgnoreCase(treeBoroElement)) {
                this.boroname = boro;
                return;
            }
        }
        throw new IllegalArgumentException("Invalid borough for Tree object.");
    }

    /**
     * Gets Tree object's ID
     *
     * @return an integer representing Tree object's ID
     */
    public int getTree_id() {
        return tree_id;
    }

    /**
     * Gets Tree object's diameter size
     *
     * @return an integer representing Tree object's diameter
     */
    public int getTree_dbh() {
        return tree_dbh;
    }

    /**
     * Gets Tree object's zipcode
     *
     * @return an integer representing Tree object's zipcode
     */
    public int getZipcode() {
        return zipcode;
    }

    /**
     * Gets Tree object's status
     *
     * @return an integer representing Tree object's status
     */
    public String getStatus() {
        return status;
    }

    /**
     * Gets Tree object's health
     *
     * @return an integer representing Tree object's health
     */
    public String getHealth() {
        return health;
    }

    /**
     * Gets Tree object's borough
     *
     * @return an integer representing Tree object's borough location
     */
    public String getBoroname() {
        return boroname;
    }

    /**
     * Gets Tree object's species
     *
     * @return an integer representing Tree object's species type
     */
    public String getSpc_common() {
        return spc_common;
    }

    /**
     * Gets Tree object's x_sp
     *
     * @return an integer representing Tree object's x_sp
     */
    public double getX_sp() {
        return x_sp;
    }

    /**
     * Gets Tree object's y_sp
     *
     * @return an integer representing Tree object's y_sp
     */
    public double getY_sp() {
        return y_sp;
    }

    /**
     * Compares Tree object to another Tree object
     * Uses species name as the primary key (alphabetical order); if species name is the same, then tree_id is used
     *
     * @param otherTree other Tree object that is used to compare the Tree in question
     *
     * @return integer, if tree is "greater than" otherTree, return 1
     *      if tree is "less than" otherTree, return -1
     */
    public int compareTo(Tree otherTree){
        if (this.spc_common.compareToIgnoreCase(otherTree.spc_common)==0){
            return this.tree_id-otherTree.tree_id; //will return negative if lower, positive if higher, 0 if the same
        }
        return this.spc_common.compareToIgnoreCase(otherTree.spc_common);
    }

    @Override
    /**
     * Verify if Tree object in question is equal to this Tree object by checking its ID and species
     *
     * @param object Object that is casted into a Tree object
     *
     * @return a boolean, if Tree object in question is equal to this Tree
     *
     * @throws IllegalArgumentException if IDs are the same but species are not
     */
    public boolean equals(Object object) {
        //check if object is null if not cast into Tree object
        if (object == null) return false;

        if (object instanceof Tree) {
            Tree tree = (Tree) object;
            if (this.tree_id == tree.tree_id && this.spc_common.equalsIgnoreCase(tree.spc_common)) return true;
            else {
                if (this.tree_id == tree.tree_id && !this.spc_common.equals(tree.spc_common)) {
                    throw new IllegalArgumentException("These two trees have the same ID but are not of the same species!");
                }
            }
        }
        return false;
    }

    /**
     * Creates a String object for Tree object with its Tree ID, Species, Status Borough, and Zipcode
     *
     * @return a String that represents Tree object's (more important) information
     */
    public String toString(){
        //add leading 0's to zipcode
        return String.format("%12s: %-12d %12s: %-17s %12s: %-12s %s: %-16s %12s: %-12s",
                "Tree ID", this.tree_id, "Species", this.spc_common, "Status", this.status,
                "Borough", this.boroname, "Zipcode", String.format("%05d", this.zipcode));
    }
}
