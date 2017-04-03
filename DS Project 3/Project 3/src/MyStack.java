import java.util.EmptyStackException;

/**
 * The Dictionary class represents the collection of words read in from input file
 *      and is responsible for performing queries in the dictionary and storing all words in an ArrayList.
 *
 * @author Reah Rajmangal
 * @version April 6, 2017
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
