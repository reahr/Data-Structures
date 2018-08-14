/**
 * The Node class is a generic class that is used to link elements in list-like structure. More specifically,
 * this class is used to create links between elements in stack and queue structures in package.
 *
 * @author Reah Rajmangal
 * @version April 6, 2017
 */
public class Node<E> {
    private E data;
    private Node<E> next;

    public Node (E data){
        this.data=data;
    }

    public Node(E data, Node<E> next){
        this(data);
        this.next=next;
    }

    /**
     * Returns the data of this node
     *
     * @return data put into this node.
     */
    public E getData() {
        return data;
    }

    /**
     * Sets the data of this node
     *
     * @param data the new data to put into this node.
     */
    public void setData(E data) {
        this.data = data;
    }

    /**
     * Gets the node next to this node
     *
     * @return node next to this node
     */
    public Node<E> getNext() {
        return next;
    }

    /**
     * Sets the node next to this node
     *
     * @param next the new node next to this node
     */
    public void setNext(Node<E> next) {
        this.next = next;
    }
}
