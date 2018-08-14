import java.util.Comparator;

/**
 * Created by reahr on 4/17/2017.
 */
public class BSTNode <E extends Comparable <E> >
        implements Comparable < BSTNode<E> >{

    private E data;
    private BSTNode<E> left;
    private BSTNode<E> right;

    public BSTNode(E data){
        this.data=data;
    }

    /**
     * Sets data to data specified
     *
     * @param data - data to be contained in this BSTNode
     */
    public void setData(E data) {
        this.data = data;
    }

    /**
     * Set's this BSTNode's left child to left node specified
     *
     * @param left - BSTNode to be left child of this BSTNode
     */
    public void setLeft(BSTNode<E> left) {
        this.left = left;
    }

    /**
     * Set's this BSTNode's right child to right node specified
     *
     * @param right - BSTNode to be right child of this BSTNode
     */
    public void setRight(BSTNode<E> right) {
        this.right = right;
    }

    /**
     * Gets this BSTNode's data
     *
     * @return E data contained in this BSTNode
     */
    public E getData() {
        return data;
    }

    /**
     * Gets this BSTNode's left child
     *
     * @return this BSTNode's left child
     */
    public BSTNode<E> getLeft() {
        return left;
    }

    /**
     * Gets this BSTNode's right child
     *
     * @return this BSTNode's right child
     */
    public BSTNode<E> getRight() {
        return right;
    }

    /**
     * Compares this object with the specified object for order.
     *
     * @param other the BSTNode object to be compared.
     *
     * @return a negative integer, zero, or a positive integer as this object is less than,
     *      equal to, or greater than the specified object.
     */
    public int compareTo(BSTNode<E> other){
        return this.data.compareTo( other.data );
    }
}
