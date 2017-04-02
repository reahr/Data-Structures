import org.junit.Test;

import java.util.EmptyStackException;

import static org.junit.Assert.*;

/**
 * Created by reahr on 3/31/2017.
 */
public class MyStackTest {

    //test Stack is not null when initialized
    @Test
    public void checkMyStackIsNotNull() {
        MyStack<Integer> newIntegers = new MyStack<Integer>();
        assertNotNull("Reference null after constructor returns.", newIntegers);
    }

    //test Stack does not throw an exception of any kind when initialized with default constructor
    @Test
    public void testConstructor() {
        try {
            MyStack<Integer> newIntegers = new MyStack<Integer>();
            assertNotNull("Reference null after constructor returns.", newIntegers);
        } catch (Exception ex) {
            fail("Unexpected exception thrown. ");
        }
    }

    //test Stack is empty when initialized
    @Test
    public void checkMyStackIsEmpty() {
        MyStack<Integer> newIntegers = new MyStack<Integer>();
        assertTrue("Stack is not empty upon initialization", newIntegers.empty());
    }

    //test push method returns data of the item pushed
    @Test
    public void testPushCanReturnItem() {
        MyStack<Integer> newIntegers = new MyStack<Integer>();
        Integer firstItem = newIntegers.push(1);
        assertTrue("Return value for push is not equal to 1", firstItem.equals(1));
    }

    //test stack is not empty after pushing an item onto stack
    @Test
    public void testPushAndNotEmpty() {
        MyStack<Integer> newIntegers = new MyStack<Integer>();
        newIntegers.push(1);
        assertFalse("Stack is empty after pushing an item.", newIntegers.empty());
    }

    //test push method adds item onto stack (LIFO, so using peek method)
    @Test
    public void testPushAddsToMyStack() {
        MyStack<Integer> newIntegers = new MyStack<Integer>();
        Integer firstItem = newIntegers.push(1);
        assertTrue("push() does not add on new item.", newIntegers.peek().equals(firstItem));
    }

    //test stack is not empty after pop method and verifying new element at top of stack is correct
    @Test
    public void testCannotLoseItems() {
        MyStack<Integer> newIntegers = new MyStack<Integer>();
        Integer firstItem = newIntegers.push(1);
        newIntegers.push(2); //push another item and then remove it
        newIntegers.pop();
        assertTrue("Stack loses previous items pushed after a pop().", !newIntegers.empty());
        //verify element remaining equals the element after pop()
        assertTrue("Item 2 should be linked to item 1 which is 1.", newIntegers.peek().equals(firstItem));
    }

    //test that peek method returns last element pushed onto stack
    @Test
    public void testMyStackIsLastInFirstOut() {
        // uses peek() to check that last item added is at top of stack
        MyStack<Integer> newIntegers = new MyStack<Integer>();
        Integer firstItem = newIntegers.push(1);
        Integer secondItem = newIntegers.push(2);
        assertTrue("Stack peek() method does not return last item pushed onto it.",
                newIntegers.peek().equals(secondItem));
    }

    //test that element pushed last is element on top of stack using peek and pop method, which should be equal
    @Test
    public void testMyStackIsLastInFirstOut2(){
        MyStack<Integer> newIntegers = new MyStack<Integer>();
        newIntegers.push(1);
        newIntegers.push(2);
        Integer firstPeek=newIntegers.peek();
        Integer firstPop= newIntegers.pop();
        assertTrue("peek() return element does not equal pop() return element.", firstPeek.equals(firstPop));
    }

    //test empty stack exception is thrown when peeking into empty stack
    @Test
    public void testCannotPeekIntoEmptyStack() {
        try {
            MyStack<Integer> newIntegers = new MyStack<Integer>();
            newIntegers.peek();
            fail("Exception not thrown when peeking into empty stack.");
        } catch (EmptyStackException e) {
            //expected behavior
        } catch (Exception e) {
            fail("Incorrect exception thrown.");
        }
    }

    //test empty stack exception thrown after adding and removing element and then peeking into stack
    @Test
    public void testCannotPeekIntoEmptyStack2() {
        try {
            MyStack<Integer> newIntegers = new MyStack<Integer>();
            newIntegers.push(1);
            newIntegers.pop();
            newIntegers.peek();
            fail("Exception not thrown when peeking into empty stack.");
        } catch (EmptyStackException e) {
            //expected behavior
        } catch (Exception e) {
            fail("Incorrect exception thrown.");
        }
    }

    //test if push method throws unexpected error
    @Test
    public void testPushThrowsException(){
        try{
            MyStack<Integer> newIntegers = new MyStack<Integer>();
            newIntegers.push(1);
        }catch (Exception e){
            fail("Unexpected exception thrown.");
        }
    }

    //test to ensure peek method does not remove element that it returns
    @Test
    public void testPeekDoesNotRemove() {
        //proven before that when add one item, stack is not empty, item is in stack
        MyStack<Integer> newIntegers = new MyStack<Integer>();
        newIntegers.push(1);
        newIntegers.peek();
        assertTrue("Item is removed when peek() method is called.", !newIntegers.empty());
    }

    //test pop element is not equal to following peek element and if peek element returned is correct
    @Test
    public void testPeekAfterPop() {
        MyStack<Integer> newIntegers = new MyStack<Integer>();
        Integer firstItem = newIntegers.push(1);
        Integer secondItem = newIntegers.push(2);
        Integer itemPopped= newIntegers.pop();
        Integer peekItem = newIntegers.peek();
        assertFalse("peek() method returns element already popped.",
                peekItem.equals(itemPopped));
        //may return some other element
        assertTrue("peek() method does not return the correct element after stack uses pop().",
                peekItem.equals(firstItem));
    }

    //test if empty stack exception thrown if attempt to pop on empty stack
    @Test
    public void testCannotPopOnEmptyStack() {
        try {
            MyStack<Integer> newIntegers = new MyStack<Integer>();
            newIntegers.pop();
            fail("Exception not thrown when popping on empty stack.");
        } catch (EmptyStackException e) {
            //expected behavior
        } catch (Exception e) {
            fail("Incorrect exception thrown.");
        }
    }

    //test if empty stack exception thrown after adding and removing element from stack
    @Test
    public void testCannotPopOnEmptyStack2(){
        try {
            MyStack<Integer> newIntegers = new MyStack<Integer>();
            newIntegers.push(1);
            newIntegers.pop();
            newIntegers.pop();
            fail("Exception not thrown when popping on empty stack.");
        } catch (EmptyStackException e) {
            //expected behavior
        } catch (Exception e) {
            fail("Incorrect exception thrown.");
        }
    }

    //test if pop removes element from stack
    @Test
    public void testPopRemovesElement() {
        MyStack<Integer> newIntegers = new MyStack<Integer>();
        newIntegers.push(1);
        newIntegers.pop();
        assertTrue("pop() does not remove element.", newIntegers.empty());
    }

    //test if element is removed using pop() and verify remaining element is not equal to it
    @Test
    public void testPopRemovesElement2() {
        MyStack<Integer> newIntegers = new MyStack<Integer>();
        newIntegers.push(1);
        newIntegers.push(2);
        Integer firstItemPopped = newIntegers.pop();
        assertFalse("pop() does not remove element.", firstItemPopped.equals(newIntegers.peek()));
    }

    //test that item popped is last element pushed onto stack
    @Test
    public void testPopEqualsElementRemoved() {
        MyStack<Integer> newIntegers = new MyStack<Integer>();
        Integer firstItem = newIntegers.push(1);
        Integer secondItem = newIntegers.push(2);
        Integer firstItemPopped = newIntegers.pop();
        assertTrue("pop() does not return correct item.", firstItemPopped.equals(secondItem));
    }

    //(test combination) test to verify pop element returned is correct and pop element is removed
    @Test
    public void testPopEqualsAndRemovesElement() {
        //checks to make sure peek does not equal second element once removed
        MyStack<Integer> newIntegers = new MyStack<Integer>();
        Integer firstItem = newIntegers.push(1);
        Integer secondItem = newIntegers.push(2);
        Integer firstItemPopped = newIntegers.pop();
        Integer peekItem = newIntegers.peek();
        assertTrue("pop() does not return correct item when popped.", firstItemPopped.equals(secondItem));
        assertFalse("pop() does not remove element.",
                peekItem.equals(firstItemPopped));

    }

    //test that after an element is popped, another element popped after can be returned
    @Test
    public void testConsecutivePops() {
        try {
            MyStack<Integer> newIntegers = new MyStack<Integer>();
            Integer firstItem = newIntegers.push(1);
            Integer secondItem = newIntegers.push(2);
            newIntegers.pop(); //first item popped
            Integer secondItemPopped = newIntegers.pop();
            assertTrue("pop() does not return correct item when popped.", secondItemPopped.equals(firstItem));
        }catch (Exception e){
            fail("Unexpected error thrown while popping on non-empty stack.");
        }
    }

    //(Combination) test to verify element popped is not in stack, element popped is correct
    //and stack is not empty after peeking into non-empty stack
    @Test
    public void testPopAndPeek(){
        MyStack<Integer> newIntegers = new MyStack<Integer>();
        Integer firstItem = newIntegers.push(1);
        Integer secondItem = newIntegers.push(2);
        Integer thirdItem= newIntegers.push(3);
        newIntegers.pop(); //thirdItem popped
        newIntegers.pop(); //secondItem popped
        Integer peek= newIntegers.peek();
        assertTrue("Item peek equals an element already popped",
                !peek.equals(thirdItem) && !peek.equals(secondItem));
        assertTrue("peek() does not equal correct element.", peek.equals(firstItem));
        //lastly assert stack is not empty after peek
        assertTrue("Stack should not be empty after peek() on non-empty stack.", !newIntegers.empty());
    }

}