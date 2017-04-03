/**
 * The Dictionary class represents the collection of words read in from input file
 *      and is responsible for performing queries in the dictionary and storing all words in an ArrayList.
 *
 * @author Reah Rajmangal
 * @version April 6, 2017
 */

public class MyQueue<E> implements Queue<E>{

    private Node<E> head;

    public MyQueue(){
        this.head=null;
    }

    public E peek(){
        if (head==null) return null;
        return head.getData();
    }

    public E poll(){
        if (head==null) return null;
        Node<E> current=head;
        head= head.getNext();
        return current.getData();
    }

    public boolean offer(E item) throws ClassCastException,
            NullPointerException, IllegalArgumentException{
        if (item==null) throw new NullPointerException("Item offered onto queue cannot be null.");
        Node <E> n= new Node (item);
        if (head==null){
            head=n;
            return true;
        }
        Node <E> current=head;
        while(current.getNext()!=null){
            current=current.getNext();
        }

        current.setNext(n);
        return true;
    }
}
