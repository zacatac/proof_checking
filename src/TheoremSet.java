import java.util.LinkedList;

public class TheoremSet {
     private LinkedList<Bundle> myTheorems;

    public TheoremSet ( ) {
    }

    public LinkedList<Bundle> getMyTeorems(){
        return myTheorems;
    }

    public Expression put (String s, Expression e) {
         //so Expression class should have a method that calls Binary Tree's exprTreeHelper method
         // and we'll call that on e, and pass the resulting binary tree along with String s into a bundle constructor
         //and add this bundle into myTheorems.
        return null;
    }
}
