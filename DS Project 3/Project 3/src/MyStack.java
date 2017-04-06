import java.util.EmptyStackException;

/**
 * The MyStack class is a generic reference based implementation of a stack. It follows the specification
 * for Java 8's Stack class.
 *
 * @author Reah Rajmangal
 * @version April 6, 2017
 */

public class MyStack<E> {

    private Node<E> top;

    public MyStack () {
        this.top = null;
    }

    /**
     * Pushes an item onto the top of this stack.    *
     *
     * @param item Pushes an item onto the top of this stack.
     *
     * @return the item argument.
     */
    public E push(E item) {
        Node<E> n = new Node(item);
        n.setNext(top);
        top = n;
        return item;
    }

    /**
     * Removes the object at the top of this stack and returns that object as the value of this function.     *
     *
     * @return The object at the top of this stack.
     *
     * @throws EmptyStackException if this stack is empty.
     */
    public E pop() throws EmptyStackException {
        if (this.empty()) throw new EmptyStackException();
        Node<E> current = top;
        top = top.getNext();
        return current.getData();
    }

    /**
     * Looks at the object at the top of this stack without removing it from the stack.*
     *
     * @return The object at the top of this stack.
     *
     * @throws EmptyStackException if this stack is empty.
     */
    public E peek() throws EmptyStackException {
        if (this.empty()) throw new EmptyStackException();
        return top.getData();
    }

    /**
     * Tests if this stack is empty.
     *
     * @return true if and only if this stack contains no items; false otherwise.
     */
    public boolean empty() {
        return top == null;
    }
}
