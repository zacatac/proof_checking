public class ListNode {
    //Consider boxing the fields here and implementing getters
    public Object myFirst;
    public ListNode myRest;

    public ListNode (Object first, ListNode rest) {
        myFirst = first;
        myRest = rest;
    }

    public ListNode (Object first) {
        myFirst = first;
        myRest = null;
    }

}