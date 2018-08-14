/**
 * The MyQueue class is a generic reference based implementation of a queue. It follows the
 * specification for Java 8's Queue class.
 *
 * @author Reah Rajmangal
 * @version April 6, 2017
 */

public class MyQueue<E> {

    private Node<E> head;

    public MyQueue() {
        this.head = null;
    }

    /**
     * Retrieves, but does not remove, the head of this queue, or returns null if this queue is empty.
     *
     * @return the head of this queue, or null if this queue is empty
     */
    public E peek() {
        if (head == null) return null;
        return head.getData();
    }

    /**
     * Retrieves and removes the head of this queue, or returns null if this queue is empty.
     *
     * @return the head of this queue, or null if this queue is empty
     */
    public E poll() {
        if (head == null) return null;
        Node<E> current = head;
        head = head.getNext();
        return current.getData();
    }

    /**
     * Inserts the specified element into this queue if it is possible to do
     *      so immediately without violating capacity restrictions.
     *
     * @return true if the element was added to this queue, else false
     *
     * @throws ClassCastException - if the class of the specified element prevents it from being added to this queue
     * @throws NullPointerException - if the specified element is null and this queue does not permit null elements
     * @throws IllegalArgumentException - if some property of this element prevents it from being added to this queue
     */
    public boolean offer(E item) throws ClassCastException,
            NullPointerException, IllegalArgumentException {
        if (item == null) throw new NullPointerException("Item offered onto queue cannot be null.");
        Node<E> n = new Node(item);
        if (head == null) {
            head = n;
            return true;
        }
        Node<E> current = head;
        while (current.getNext() != null) {
            current = current.getNext();
        }
        current.setNext(n);
        return true;
    }
}
