import java.util.*;

public class List {

    private ListNode myHead;
    private ListNode myTail;
    private int mySize;

    public List ( ) {
        myHead = null;
        myTail = null;
        mySize = 0;
    }

    public boolean isEmpty ( ) {
        return myHead == null;
    }



    public String toString ( ) {
        String rtn = "( ";
        for (ListNode p = myHead; p != null; p = p.myRest) {
            rtn = rtn + p.myFirst + " ";
        }
        return rtn + ")";
    }

    public int size() {
        return mySize;
    }

    // Return true if the list contains the object
    public boolean contains (Object obj) {
        for (ListNode p = myHead; p != null; p = p.myRest) {
            if (obj.equals (p.myFirst)) {
                return true;
            }
        }
        return false;
    }

    // Returns the element at the given position in this list.
    public Object get (int pos) {
        if (pos < 0) {
            throw new IllegalArgumentException (
                    "Argument to get must be at least 0.");
        }
        if (pos >= size ( )) {
            throw new IllegalArgumentException ("Argument to get is too large.");
        }
        int k = 0;
        for (ListNode p = myHead; p != null; p = p.myRest) {
            if (k == pos) {
                return p.myFirst;
            }
            k++;
        }
        return null;
    }

    public void addToFront (Object obj) {
        if (myHead != null){
            myHead = new ListNode (obj, myHead);
        } else {
            myHead = new ListNode(obj, myHead);
            myTail = myHead;
        }
        mySize++;
    }

    public boolean equals (Object obj) {
        ListNode q = ((List) obj).myHead;
        ListNode p = myHead;

        for (p = myHead; p!= null && q!= null; p = p.myRest) {
            if (!p.myFirst.equals(q.myFirst)) return false;
            q = q.myRest;
        }
        if (q == null && p == null) return true;
        else return false;
    }

    public void add (Object x) {
        ListNode addedNode = new ListNode(x,null);
        if (myHead != null){
            myTail.myRest = addedNode;
            myTail = addedNode;
        } else {
            myHead = addedNode;
            myTail =  addedNode;
        }
        mySize++;
    }

    public Iterator elements ( ) {
        return new ElementIterator ( );
    }

    public class ElementIterator implements Iterator {

        // State variable(s) to be provided.
        ListNode recentNode;

        public ElementIterator ( ) {
            recentNode = myHead;
        }

        public boolean hasNext ( ) {
            return recentNode != null;
        }

        public Object next ( ) {
            Object rtnObj= recentNode.myFirst;
            recentNode = recentNode.myRest;
            return rtnObj;
        }

        public void remove ( ) {
            // not used; do not implement
        }
    }

    public void appendInPlace (List l) {
        if (myHead != null){
            if (l.myHead == null){
                return;
            }
            myTail.myRest = l.myHead;
            myTail = l.myTail;
        } else {
            if (l.myHead == null){
                return;
            }
            myHead = l.myHead;
            myTail = l.myTail;
        }
        mySize += l.mySize;
        this.isOK();
    }

    public void doubleInPlace () {
        for (ListNode p = myHead; p!=null; p =p.myRest) {
            p.myRest = new ListNode(p.myFirst,p.myRest);
            p = p.myRest;

        }
        try {
            mySize *= 2;
            myTail = myTail.myRest;
        } catch (NullPointerException e) {
            return;
        }
        this.isOK();
    }

    public boolean isOK() {
        int count = 0;
        ListNode testTail = null;
        for (ListNode p = myHead; p!=null; p = p.myRest){
            if (p.myFirst == null){
                System.err.println("Error: Node initialization is inconsistent");
                return false;
            }
            testTail = p;
            count++;
        }
        if (mySize != count) {
            System.err.println("Error: mySize is inconsistent");
            return false;
        }
        if (testTail != myTail)  {
            System.err.println("Error: myTail is inconsistent");
            return false;
        }
        return true;
    }

    public void remove(Object x) {
        try {
            myHead.myFirst.equals(x);
        } catch (NullPointerException e) {
            return;
        }
        if (myHead.myFirst.equals(x)) {
            myHead = myHead.myRest;
        } else if (myHead.myRest == null){
            return;
        }else {
            for (ListNode p = myHead; p.myRest!=null; p = p.myRest){
                if (p.myRest == null) {
                    return;
                }else if (p.myRest.myFirst.equals(x) ){
                    p.myRest = p.myRest.myRest;
                }
            }
        }
    }

    public void reverse () {

    }

    public static void reverseHelper(ListNode L, ListNode soFar) {

    }
}