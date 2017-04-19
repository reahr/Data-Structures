import java.util.NoSuchElementException;

/**
 * Created by reahr on 4/17/2017.
 */
public class MyBST <E extends Comparable <E>> {

    protected BSTNode<E> root;
    protected int size; //create counter

    public MyBST(){
        this.root=null;
        this.size=0;
    }
    public boolean add(E item) {
        if (item == null) {
            throw new NullPointerException("Specified element cannot be null.");
        }else if (this.contains(item)) return false; //check if element already exists
        root=recAdd(root, item);
        this.size++;
        return true;
    }

    protected BSTNode<E> recAdd ( BSTNode<E> node, E newData ) {
        if (node == null) { //if root is null
            BSTNode<E> newNode = new BSTNode<E>(newData);
            return newNode;
        }if (newData.compareTo(node.getData()) < 0) {
            node.setLeft (recAdd(node.getLeft(), newData));
        }else
            node.setRight(recAdd(node.getRight(), newData));
        return node; //if set right or left node recursively
    }

    protected boolean remove(Object o){
        if (o==null) throw new NullPointerException("Specified element cannot be null.");
        E item= (E) o; //cast into type
        if (!this.contains(item)) return false;
        else root=findToRemove(root, item);
        size--;
        return true;
    }

    protected BSTNode<E> findToRemove (BSTNode<E> node, E item){
        if (item.compareTo(node.getData()) < 0)
            node.setLeft(findToRemove(node.getLeft(), item));
        else if (item.compareTo(node.getData())> 0)
            node.setRight(findToRemove(node.getRight(), item));
        else node= remove(node);
        return node;
    }

    private BSTNode<E> remove (BSTNode<E> node){
        if (node.getLeft()==null) return node.getRight();
        else if (node.getRight()==null) return node.getLeft();
        E data= getPredecessor (node);
        node.setData(data);
        node.setLeft(findToRemove(node.getLeft(),data));
        return node;
    }

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

    public boolean contains(Object o){
        if (o == null){
            throw new NullPointerException("Specified element cannot be null.");
        }
        E item= (E) o; //cast into type
        return recContains (item, root);
    }

    private boolean recContains (E item, BSTNode <E> currentNode){
        if (currentNode==null) return false;
        else if (item.compareTo(currentNode.getData()) < 0) {
            return recContains(item, currentNode.getLeft());
        }else if (item.compareTo(currentNode.getData()) > 0){
            return recContains(item, currentNode.getRight());
        }else return true;
    }

    public E first(){
        if (root==null) throw new NoSuchElementException ("Tree is empty");
        if (root.getLeft()==null) return root.getData();
        BSTNode<E> current= root;
        while (current.getLeft() != null){
            current=current.getLeft(); //get (lowest) element
        }
        E first= current.getData();
        return first;
    }

    public E last(){
        if (root==null) throw new NoSuchElementException ("Tree is empty");
        if (root.getRight()==null) return root.getData();
        BSTNode<E> current= root;
        while (current.getRight() != null){
            current=current.getRight(); //get (lowest) element
        }
        E last= current.getData();
        return last;
    }

    public String toString(){
        return "[" + toString (root) + "]";
    }

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
