import java.util.EmptyStackException;

/**
 * Created by reahr on 3/26/2017.
 */
public interface Stack<E> {
    public E push(E item);

    public E pop() throws EmptyStackException;

    public E peek () throws EmptyStackException;

    public boolean empty();

}
