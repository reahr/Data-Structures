/**
 * Created by reahr on 2/1/2017.
 */
public class TreeTest {
    public static void main(String[]args){

        Tree tree1= new Tree(123, 3, "dead", "goOd", "London planetree", 76, "Queens", 23.3, 23.2);
        Tree tree2= new Tree(123, 323, "alive", "goOd", "London", 76, "Queens", 234.3, 233.2);
        Tree tree3= new Tree(23747598, 2332, "ALIVE", "", "1", 76, "Brooklyn", 234.3, 233.2);

        System.out.println(tree1.getBoro());

    }
}
