/**
 * Created by reahr on 3/26/2017.
 */
public class MyQueueTester {

    public static void main (String[] args){
        MyQueue<Integer> ints= new MyQueue<Integer>();
        System.out.println(ints.offer(1));
        System.out.println(ints.offer(2));
        System.out.println(ints.offer(3));
        System.out.println(ints.offer(4));
        System.out.println(ints.poll());

        Integer x=0;
        while(x !=null){
            System.out.println(x=ints.poll());
        }
    }
}
