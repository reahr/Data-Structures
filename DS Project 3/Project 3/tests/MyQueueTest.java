import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by reahr on 4/2/2017.
 */
public class MyQueueTest {

    //test queue is not empty after initialization
    @Test
    public void checkMyQueueNotNull(){
        MyQueue<Integer> newIntegers = new MyQueue<Integer>();
        assertNotNull("Reference null after constructor returns.", newIntegers);
    }

    //test constructor does not throw unexpected exception
    @Test
    public void testConstructor(){
        try {
            MyQueue<Integer> newIntegers = new MyQueue<Integer>();
            assertNotNull("Reference null after constructor returns.", newIntegers);
        } catch (Exception ex) {
            fail("Exception thrown incorrectly for default constructor call.");
        }
    }

    //test queue is empty by checking that item peeked is null
    @Test
    public void checkMyQueueIsEmpty(){
        MyQueue<Integer> newIntegers = new MyQueue<Integer>();
        assertTrue("Queue should be empty upon initialization", newIntegers.peek()==null);
    }

    //test peek method returns null on an empty queue (same outcome as test above but diff purpose)
    @Test
    public void testPeekIsNullWhenEmpty(){
        MyQueue<Integer> newIntegers = new MyQueue<Integer>();
        Integer peek=newIntegers.peek();
        assertTrue("peek() is not null when queue is empty.", peek==null);
    }

    //test peek does not throw exception since it returns null on an empty queue
    @Test
    public void testPeekDoesNotThrowException(){
        try{
            MyQueue<Integer> newIntegers = new MyQueue<Integer>();
            Integer peek=newIntegers.peek();
        }catch (Exception e){
            fail("Exception not supposed to be thrown for peek().");
        }
    }

    //test peek element returned is not null if an element is offered onto queue
    @Test
    public void testPeekNotNull(){
        //assuming offer() works
        MyQueue<Integer> newIntegers = new MyQueue<Integer>();
        newIntegers.offer(1);
        Integer peek=newIntegers.peek();
        assertTrue("peek() returns null for non-empty queue.", peek!=null);
    }

    //test peek returns item (data) of item offered
    @Test
    public void testPeekReturnsData(){
        MyQueue<Integer> newIntegers = new MyQueue<Integer>();
        newIntegers.offer(1);
        Integer peek=newIntegers.peek();
        assertTrue("peek() does not return data for non-empty queue.",
                peek.equals(1));
    }

    //test poll does not throw exception since null is returned if queue is empty
    @Test
    public void testPollDoesNotThrowException(){
        try{
            MyQueue<Integer> newIntegers = new MyQueue<Integer>();
            Integer pollInteger=newIntegers.poll();
        }catch (Exception e){
            fail("Exception thrown incorrectly for poll().");
        }
    }

    //test that poll method on an empty queue returns null
    @Test
    public void testPollReturnNull(){
        MyQueue<Integer> newIntegers = new MyQueue<Integer>();
        Integer pollInteger=newIntegers.poll();
        assertTrue("poll() does not return null when queue is empty.", pollInteger==null);
    }

    //test poll method does not return null on a non-empty queue
    @Test
    public void testPollDoesNotReturnNull(){
        MyQueue<Integer> newIntegers = new MyQueue<Integer>();
        newIntegers.offer(1);
        Integer pollInteger=newIntegers.poll();
        assertTrue("poll() returns null for non-empty queue.", pollInteger!=null);
    }

    //test that poll method returns item
    @Test
    public void testPollReturnsData(){
        MyQueue<Integer> newIntegers = new MyQueue<Integer>();
        newIntegers.offer(1);
        Integer pollInteger=newIntegers.poll();
        assertTrue("poll() does not return data for non-empty queue", pollInteger.equals(1));
    }

    //test that poll method returns correct item (when more than one element in queue [FIFO])
    @Test
    public void testPollReturnsCorrectData(){
        //test fifo, not lifo
        MyQueue<Integer> newIntegers = new MyQueue<Integer>();
        newIntegers.offer(1);
        newIntegers.offer(2);
        Integer pollInteger=newIntegers.poll();
        assertTrue("First poll() returns last element offered.", !pollInteger.equals(2));
    }

    //test that when an element is removed, the other elements in queue are not lost
    @Test
    public void testMyQueueIsLinked(){
        MyQueue<Integer> newIntegers = new MyQueue<Integer>();
        newIntegers.offer(1);
        newIntegers.offer(3);
        Integer firstPoll=newIntegers.poll();
        Integer secondPoll= newIntegers.poll();
        assertTrue("First poll() returns incorrect data.", firstPoll.equals(1));
        assertTrue("Second poll() is null when data should still exist in queue.", secondPoll!=null);
    }

    //test what order certain elements are removed (FIFO)
    @Test
    public void testMyQueueIsLinked2(){
        MyQueue<Integer> newIntegers = new MyQueue<Integer>();
        newIntegers.offer(1);
        newIntegers.offer(2);
        newIntegers.offer(3);
        Integer firstPoll=newIntegers.poll();
        Integer secondPoll= newIntegers.poll();
        assertTrue("First poll() returns incorrect data.", firstPoll.equals(1));
        assertTrue("Second poll() returns incorrect data.", secondPoll.equals(2));
    }

    //test after removing elements from queue, null is returned on an empty queue
    @Test
    public void testMyQueueReturnsNullAfterPolls(){
        MyQueue<Integer> newIntegers = new MyQueue<Integer>();
        newIntegers.offer(1);
        Integer firstPoll=newIntegers.poll();
        Integer secondPoll= newIntegers.poll();
        assertTrue("Second poll() does not return null for empty queue after polls.", secondPoll==null);
    }

    //test that offer method does not allow null elements to be added onto queue
    @Test
    public void testOfferThrowsNullPointerException(){
        try {
            MyQueue<Integer> newIntegers = new MyQueue<Integer>();
            newIntegers.offer(null);
            fail("NullPointerException not thrown for null type offered.");
        }catch (NullPointerException e) {
        }catch (Exception e){
            fail("Incorrect exception thrown for null type offered.");
        }
    }

    //test that ClassCastException is thrown when trying to offer an incorrect casted type onto a queue
    @Test
    public void testOfferThrowsClassCastException(){
        Object x= new String ("Cannot cast me into an Integer!");
        try{
            MyQueue<Integer> newIntegers = new MyQueue<Integer>();
            newIntegers.offer((Integer)x);
            fail("ClassCastException not thrown for class that cannot be casted into type for queue.");
        }catch (ClassCastException e){
        }catch (Exception e){
            fail("Incorrect exception thrown.");
        }
    }

    //test that offer works with accepted types (reference types)
    @Test
    public void testOfferWorksWithGoodData(){
        try {
            MyQueue<Integer> newIntegers = new MyQueue<Integer>();
            newIntegers.offer(1);
        }catch (Exception e){
            fail("Exception thrown incorrectly for offer().");
        }
    }

    //test offer method returns a boolean when adding onto queue
    @Test
    public void testOfferReturnsBoolean(){
        MyQueue<Integer> newIntegers = new MyQueue<Integer>();
        Boolean firstItem= newIntegers.offer(1);
        assertTrue("offer() does not return true for accepted data", firstItem);
    }

    //test that offer returns a boolean when adding multiple items onto queue
    @Test
    public void testOfferReturnsTrueForMultiple(){
        MyQueue<Integer> newIntegers = new MyQueue<Integer>();
        Boolean firstItem= newIntegers.offer(1);
        Boolean secondItem=newIntegers.offer(2);
        assertTrue("offer() does not return true for accepted data", firstItem && secondItem);
    }

    //test that offer adds onto the last element in queue (not LIFO)
    @Test
    public void testOfferAddsOnToLastElement(){
        MyQueue<Integer> newIntegers = new MyQueue<Integer>();
        newIntegers.offer(1);
        newIntegers.offer(2);
        newIntegers.offer(3);
        assertTrue("First element in queue should not be last one offered.", !newIntegers.peek().equals(3));
    }

    //test that element to be removed can be looked for using peek method (peek element=next element popped)
    @Test
    public void testPollEqualsPeek(){
        MyQueue<Integer> newIntegers = new MyQueue<Integer>();
        newIntegers.offer(1);
        newIntegers.offer(2);
        newIntegers.offer(3);
        Integer firstPeek= newIntegers.peek();
        Integer firstPoll=newIntegers.poll();
        assertTrue("poll() does not equal peek() ", firstPeek.equals(firstPoll));
    }

    //test that element first offered is element next to be removed (using peek method)
    @Test
    public void testOfferAddsOnToLastElement2(){
        MyQueue<Integer> newIntegers = new MyQueue<Integer>();
        newIntegers.offer(1);
        newIntegers.offer(2);
        newIntegers.offer(3);
        assertTrue("First element in queue is not first element offered.", newIntegers.peek().equals(1));
    }
}