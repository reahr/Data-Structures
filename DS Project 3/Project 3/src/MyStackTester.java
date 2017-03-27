/**
 * Created by reahr on 3/26/2017.
 */
public class MyStackTester {
    public static void main(String[] args){
        MyStack<Integer> ints= new MyStack<Integer>();
        System.out.println(ints.empty());
        ints.push(1);
        System.out.println(ints.empty());
        System.out.println(ints.push(2));
        System.out.println(ints.pop());
        System.out.println(ints.peek());
        System.out.println(ints.pop());
        System.out.println(ints.empty());
    }
}
