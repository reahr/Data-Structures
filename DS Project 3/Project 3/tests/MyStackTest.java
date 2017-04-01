import org.junit.Test;

import java.util.EmptyStackException;

import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;

/**
 * Created by reahr on 3/31/2017.
 */
public class MyStackTest {

    @Test
    public void checkMyStackIsNotNull(){
        MyStack<Integer> newIntegers=new MyStack<Integer>();
        assertNotNull("Reference null after constructor returns.", newIntegers);
    }

    @Test
    public void testConstructor(){
        try {
            MyStack<Integer> newIntegers=new MyStack<Integer>();
            assertNotNull("Reference null after constructor returns.", newIntegers);
        } catch (Exception ex) {
            fail("Exception thrown incorrectly for default constructor call. ");
        }
    }

    @Test
    public void checkMyStackIsEmpty(){
        MyStack<Integer> newIntegers=new MyStack<Integer>();
        assertTrue("Stack is not empty upon initialization", newIntegers.empty());
    }

    @Test
    public void testPushCanReturnItem(){
        MyStack<Integer> newIntegers=new MyStack<Integer>();
        Integer firstItem=newIntegers.push(1);
        assertTrue("Return value for push is not equal to 1", firstItem==1);
    }

    @Test
    public void testPushAndNotEmpty(){
        MyStack<Integer> newIntegers=new MyStack<Integer>();
        newIntegers.push(1);
        assertTrue("Stack is empty when pushing another item.", !newIntegers.empty());
    }

    @Test
    public void testPushAddsToMyStack(){
        //check item pushed is on stack using peek()
        MyStack<Integer> newIntegers=new MyStack<Integer>();
        Integer firstItem=newIntegers.push(1);
        assertTrue("Stack does not add on new item.", newIntegers.peek().equals(firstItem));
    }

    @Test
    public void testPushCannotLoseItems(){
        //checks to see that the second item pushed is linked to first item (assuming pop() works)
        MyStack<Integer> newIntegers=new MyStack<Integer>();
        Integer firstItem=newIntegers.push(1);
        newIntegers.push(2); //push another item and then remove it
        newIntegers.pop();
        assertTrue("Item 2 should be linked to item 1 which is 1.", newIntegers.peek().equals(firstItem) );
    }

    @Test
    public void testMyStackIsLastInFirstOut(){
        // uses peek() to check that last item added is at top of stack
        MyStack<Integer> newIntegers=new MyStack<Integer>();
        Integer firstItem=newIntegers.push(1);
        Integer secondItem=newIntegers.push(2);
        assertTrue("Stack peek() method does not return last item pushed onto it.",
                newIntegers.peek().equals(secondItem));
    }

    @Test
    public void testCannotPeekIntoEmptyStack(){
        try{
            MyStack<Integer> newIntegers=new MyStack<Integer>();
            newIntegers.peek();
            fail("Exception not thrown when peeking into empty stack.");
        }catch (EmptyStackException e){
            //expected behavior
        }catch (Exception e){
            fail ("Incorrect exception thrown.");
        }
    }

    @Test
    public void testPeekDoesNotRemove(){
        //proven before that when add one item, stack is not empty, item is in stack
        MyStack<Integer> newIntegers=new MyStack<Integer>();
        Integer firstItem=newIntegers.push(1);
        newIntegers.peek();
        assertTrue("Item is removed when peek() method is called.", !newIntegers.empty());
    }


}