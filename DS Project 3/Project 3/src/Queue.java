/**
 * Created by reahr on 3/26/2017.
 */
public interface Queue<E> {
    public E peek();

    public E poll();

    public boolean offer(E item) throws ClassCastException, NullPointerException, IllegalArgumentException;
}
