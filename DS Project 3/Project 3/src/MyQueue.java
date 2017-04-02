/**
 * Created by reahr on 3/26/2017.
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
