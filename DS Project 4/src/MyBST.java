import java.util.NoSuchElementException;

/**
 * The MyBST class creates a Binary Search Tree
 *
 * @author Reah Rajmangal
 * @version April 22, 2017
 */
public class MyBST <E extends Comparable <E>> {

    protected BSTNode<E> root;
    protected int size; //create counter

    public MyBST(){
        this.root=null;
        this.size=0;
    }

    /**
     * Adds the specified element to this set if it is not already present.
     * More formally, adds the specified element e to this set if the set contains no element e2
     * such that (e==null ? e2==null : e.equals(e2)).
     * If this set already contains the element, the call leaves the set unchanged and returns false.
     *
     * @param e - element to be added to this set
     *
     * @return true if this set did not already contain the specified element
     *
     * @throws ClassCastException - if the specified object cannot be compared with the elements currently in this set
     * @throws NullPointerException - if the specified element is null and this set uses natural ordering,
     *      or its comparator does not permit null elements
     */
    public boolean add(E e) throws ClassCastException, NullPointerException {
        if (e == null) {
            throw new NullPointerException("Specified element cannot be null.");
        }else if (this.contains(e)) return false; //check if element already exists
        root=recAdd(root, e);
        this.size++;
        return true;
    }

    /**
     * Helper method for add(E e) that traverses through the tree using Nodes
     *
     * @param node - node to be visited and checked to see if it should be added "near" it
     * @param newData - data to be compared to in order to find a place on the tree
     *
     * @return the BSTNode that was added onto tree
     */
    private BSTNode<E> recAdd ( BSTNode<E> node, E newData ) {
        if (node == null) { //if root is null
            BSTNode<E> newNode = new BSTNode<E>(newData);
            return newNode;
        }if (newData.compareTo(node.getData()) < 0) {
            node.setLeft (recAdd(node.getLeft(), newData));
        }else
            node.setRight(recAdd(node.getRight(), newData));
        return node; //if set right or left node recursively
    }

    /**
     * Removes the specified element from this set if it is present.
     * More formally, removes an element e such that (o==null ? e==null : o.equals(e)),
     * if this set contains such an element. Returns true if this set contained the element (or equivalently,
     * if this set changed as a result of the call). (This set will not contain the element once the call returns.)
     *
     * @param o - object to be removed from this set, if present
     *
     * @return true if this set contained the specified element
     *
     * @throws ClassCastException - if the specified object cannot be compared with the elements currently in this set
     * @throws NullPointerException - if the specified element is null and this set uses natural ordering,
     *      or its comparator does not permit null elements
     */
    public boolean remove(Object o) throws ClassCastException, NullPointerException{
        if (o==null) throw new NullPointerException("Specified element cannot be null.");
        E item= (E) o; //cast into type
        if (!this.contains(item)) return false;
        else root=findToRemove(root, item);
        size--;
        return true;
    }

    /**
     * Helper method for remove(Object o) that finds the item by traversing through nodes
     *
     * @param node - BSTNode to visit and compare its data with
     * @param item - item that needs to be removed
     *
     * @return BSTNode node that is successfully removed
     */
    private BSTNode<E> findToRemove (BSTNode<E> node, E item){
        if (item.compareTo(node.getData()) < 0)
            node.setLeft(findToRemove(node.getLeft(), item));
        else if (item.compareTo(node.getData())> 0)
            node.setRight(findToRemove(node.getRight(), item));
        else node= remove(node);
        return node;
    }

    /**
     * Helper method for findToRemove(BSTNode<E> node, E item) that removes the actual Node
     *
     * @param node - BSTNode found and needed to remove
     *
     * @return BSTNode node that is successfully removed
     */
    private BSTNode<E> remove (BSTNode<E> node){
        if (node.getLeft()==null) return node.getRight();
        else if (node.getRight()==null) return node.getLeft();
        E data= getPredecessor (node);
        node.setData(data);
        node.setLeft(findToRemove(node.getLeft(),data));
        return node;
    }

    /**
     * Helper method for remove(BSTNode<E> node) that replaces node with its children (if it has any)
     *
     * @param node - BSTNode found and needed to removed/replaced
     *
     * @return E - item whose data needs to be set somewhere else in tree (
     */
    private E getPredecessor(BSTNode<E> node){
        if (node.getLeft()==null){
            throw new NullPointerException ("Node should not be null");
        }else{
            BSTNode<E> current= node.getLeft();
            while (current.getRight() !=null){
                current=current.getRight();
            }
            return current.getData();
        }
    }

    /**
     * Returns true if this set contains the specified element.
     * More formally, returns true if and only if this set contains an element e such that
     *(o==null ? e==null : o.equals(e)).
     *
     * @param o - object to be checked for containment in this set
     *
     * @return true if this set contains the specified element
     *
     * @throws ClassCastException - if the specified object cannot be compared with the elements currently in the set
     * @throws NullPointerException - if the specified element is null and this set uses natural ordering,
     *      or its comparator does not permit null elements
     */
    public boolean contains (Object o) throws ClassCastException, NullPointerException{
        if (o == null){
            throw new NullPointerException("Specified element cannot be null.");
        }
        E item= (E) o; //cast into type
        return recContains (item, root);
    }

    /**
     * Helper method for contains (Object o) that traverses through tree looking for specific data
     *
     * @param item - data to be found
     * @param currentNode - node whose data will be compared to
     *
     * @return true if data is found or false if not
     */
    private boolean recContains (E item, BSTNode <E> currentNode){
        if (currentNode==null) return false;
        else if (item.compareTo(currentNode.getData()) < 0) {
            return recContains(item, currentNode.getLeft());
        }else if (item.compareTo(currentNode.getData()) > 0){
            return recContains(item, currentNode.getRight());
        }else return true;
    }

    /**
     * Returns the first (lowest) element currently in this set.
     *
     * @return the first (lowest) element currently in this set
     *
     * @throws NoSuchElementException - if this set is empty
     */
    public E first() throws NoSuchElementException{
        if (root==null) throw new NoSuchElementException ("Tree is empty");
        if (root.getLeft()==null) return root.getData();
        BSTNode<E> current= root;
        while (current.getLeft() != null){
            current=current.getLeft(); //get (lowest) element
        }
        E first= current.getData();
        return first;
    }

    /**
     * Returns the last (highest) element currently in this set.
     *
     * @return the last (highest) element currently in this set
     *
     * @throws NoSuchElementException - if this set is empty
     */
    public E last() throws NoSuchElementException{
        if (root==null) throw new NoSuchElementException ("Tree is empty");
        if (root.getRight()==null) return root.getData();
        BSTNode<E> current= root;
        while (current.getRight() != null){
            current=current.getRight(); //get (lowest) element
        }
        E last= current.getData();
        return last;
    }

    /**
     * Returns a string representation of this collection. The string representation
     * consists of a list of the collection's elements in the order they are returned by its iterator,
     * enclosed in square brackets ("[]"). Adjacent elements are separated by the characters ", " (comma and space).
     * Elements are converted to strings as by String.valueOf(Object).
     *
     * @return a string representation of this collection
     */
    public String toString(){
        return "[" + toString (root) + "]";
    }

    /**
     * Helper method of toString() which performs an inOrder Traversal that visits and builds onto each return String
     *
     * @param node - node to be visited and added onto the ultimate String
     *
     * @return a string representation of this collection sans brackets
     */
    private String toString (BSTNode<E> node){
        String element="";
        if (node==null) return "";
        element+= toString(node.getLeft());

        //process node
        //take away comma if last element
        if (node.getData().equals(this.last())) element+= String.valueOf(node.getData());
        else element+= String.valueOf(node.getData())+", ";

        element+= toString(node.getRight());
        return element;
    }
}
