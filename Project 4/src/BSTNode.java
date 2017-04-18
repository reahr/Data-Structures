import java.util.Comparator;

/**
 * Created by reahr on 4/17/2017.
 */
public class BSTNode <E extends Comparable <E> >
        implements Comparable < BSTNode<E> >{

    public void setData(E data) {
        this.data = data;
    }

    private E data;

    public void setLeft(BSTNode<E> left) {
        this.left = left;
    }

    private BSTNode<E> left;

    public void setRight(BSTNode<E> right) {
        this.right = right;
    }

    private BSTNode<E> right;

    public BSTNode(E data){
        this.data=data;
    }

    public BSTNode (E data, BSTNode <E> left, BSTNode<E> right){
        this.data=data;
        this.left=left;
        this.right=left;
    }

    public E getData() {
        return data;
    }

    public BSTNode<E> getLeft() {
        return left;
    }

    public BSTNode<E> getRight() {
        return right;
    }

    public int compareTo(BSTNode<E> other){
        return this.data.compareTo( other.data );
    }
}
