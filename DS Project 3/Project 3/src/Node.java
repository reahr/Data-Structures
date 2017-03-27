/**
 * Created by reahr on 3/26/2017.
 */
public class Node<E> {
    private E data;
    private Node<E> next;

    public Node (E data){
        if (data==null) throw new IllegalArgumentException ("Data for node cannot be null.");
        this.data=data;
    }

    public Node(E data, Node<E> next){
        this(data);
        this.next=next;
    }

    public E getData() {
        return data;
    }

    public void setData(E data) {
        this.data = data;
    }

    public Node<E> getNext() {
        return next;
    }

    public void setNext(Node<E> next) {
        this.next = next;
    }
}
