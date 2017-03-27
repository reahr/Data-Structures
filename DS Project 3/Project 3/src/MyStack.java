import java.util.EmptyStackException;

/**
 * Created by reahr on 3/26/2017.
 */
public class MyStack<E> implements Stack<E> {

    private Node<E> top;

    public MyStack(){
        this.top=null;
    }

    public E push(E item){
        Node<E> n= new Node (item);
        n.setNext(top);
        top=n;
        return item;
    }

    public E pop() throws EmptyStackException{
        if (this.empty()) throw new EmptyStackException();
        Node <E> current=top;
        top= top.getNext();
        return current.getData();
    }

    public E peek () throws EmptyStackException{
        if (this.empty()) throw new EmptyStackException();
        return top.getData();
    }

    public boolean empty(){
        return top==null;
    }
}
