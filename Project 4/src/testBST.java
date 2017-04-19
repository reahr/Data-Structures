/**
 * Created by reahr on 4/17/2017.
 */
public class testBST {
    public static void main (String[] args){
        MyBST<Integer> hello= new MyBST<Integer>();
        System.out.println(hello.add(5));
        System.out.println(hello.add(4));
        System.out.println(hello.add(15));
        System.out.println(hello.contains(1));
        System.out.println(hello.first());
        System.out.println(hello.last());
        System.out.println(hello.remove(4));
        System.out.println(hello.remove(20));
        System.out.println(hello.first());
        System.out.println(hello.last());
        System.out.println(hello.toString());
    }
}
