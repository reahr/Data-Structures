/**
 * Created by reahr on 4/17/2017.
 */
public class testBST {
    public static void main (String[] args){
        MyBST<Integer> hello= new MyBST<Integer>();
        System.out.println(hello.add(1));
        System.out.println(hello.remove(null));
        System.out.println(hello.contains(1));
    }
}
