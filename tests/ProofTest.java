import org.junit.Test;

import java.util.LinkedList;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: Zack
 * Date: 7/24/13
 * Time: 10:11 AM
 * To change this template use File | Settings | File Templates.
 */
public class ProofTest {


    @Test
    public void testShow() throws Exception {
        Proof proof = new Proof();
        BinaryTree tree = new BinaryTree(new BinaryTree.TreeNode("A"));
        Bundle bundle = new Bundle("1",tree,"2","3","4");
        Bundle bundle2 = new Bundle("1",tree,"2","3","4");

        proof.show(bundle);

        LinkedList<Bundle> resultingList = proof.getTruths();
        Bundle resultingScope = proof.getLastShow();
        assertEquals(1,resultingList.size());
        assertTrue(resultingList.contains(bundle2));
        assertEquals(resultingScope,resultingList.get(0));

    }

    @Test
    public void testAssume() throws Exception{
        Proof proof = new Proof();
        BinaryTree tree = new BinaryTree (new BinaryTree.TreeNode("=>", new BinaryTree.TreeNode("&", new BinaryTree.TreeNode("p"), new BinaryTree.TreeNode("q")), new BinaryTree.TreeNode("q")) );
        Bundle dummyBundle = new Bundle("2",new BinaryTree(),"show");       //Checks that the last bundle in truths is pulled
        Bundle mainBundle = new Bundle("1",tree,"show");
        // Bundle wrongThrmNameBundle = new Bundle("2",tree,"",) cant test yet

        proof.show(dummyBundle);
        proof.show(mainBundle);

        BinaryTree notTheRightTree = new BinaryTree();
        Bundle notTheRightBundle = new Bundle("1",notTheRightTree,"2");
        //proof.assume(notTheRightBundle); //Throws exception


        BinaryTree notAssumeTree = new BinaryTree(new BinaryTree.TreeNode("~",new BinaryTree.TreeNode("=>", new BinaryTree.TreeNode("&", new BinaryTree.TreeNode("p"), new BinaryTree.TreeNode("q")), new BinaryTree.TreeNode("q")),null));
        Bundle assumeRightBundle1 = new Bundle("1",notAssumeTree,"2");

        proof.assume(assumeRightBundle1);
        assertEquals(assumeRightBundle1,proof.getTruths().get(proof.getTruths().size()-1));

        proof = new Proof();
        proof.show(dummyBundle);
        proof.show(mainBundle);

        BinaryTree leftAssumeTree = new BinaryTree(new BinaryTree.TreeNode("&", new BinaryTree.TreeNode("p"), new BinaryTree.TreeNode("q")));
        Bundle assumeRightBundle2 = new Bundle("2",leftAssumeTree,"2");

        proof.assume(assumeRightBundle2);
        assertEquals(assumeRightBundle2,proof.getTruths().get(proof.getTruths().size()-1));


    }

    @Test
    public void testRepeat() throws IllegalLineException{

        Proof proof = new Proof();
        BinaryTree tree = new BinaryTree (new BinaryTree.TreeNode("=>", new BinaryTree.TreeNode("&", new BinaryTree.TreeNode("p"), new BinaryTree.TreeNode("q")), new BinaryTree.TreeNode("q")) );


        Bundle dummyBundle = new Bundle("3",new BinaryTree(),"show");       //Checks that the last bundle in truths is pulled
        Bundle mainBundle = new Bundle("1",tree,"show");

        proof.show(dummyBundle);
        proof.show(mainBundle);

        BinaryTree notTheRightTree = new BinaryTree();
        Bundle notTheRightBundle = new Bundle("2",notTheRightTree,"2");
//        proof.repeat(notTheRightBundle); // Should throw exception, check if you want

        BinaryTree theRightTree = new BinaryTree(new BinaryTree.TreeNode("=>", new BinaryTree.TreeNode("&", new BinaryTree.TreeNode("p"), new BinaryTree.TreeNode("q")), new BinaryTree.TreeNode("q")) );
//        theRightTree.print();
        theRightTree.getMyRoot();
        Bundle theRightBundle = new Bundle("2",theRightTree,"repeat","1");

        Bundle theWrongBundleReference = new Bundle("2",theRightTree,"repeat","2");
//        proof.repeat(theWrongBundleReference); // Throws exception for using the wrong line reference

        proof.repeat(theRightBundle);

        LinkedList<Bundle> truths = proof.getTruths();
        assertTrue(truths.get(truths.size()-1).equals(theRightBundle));
        for (int i = 0; i < truths.size()-1; i++){
            assertFalse(truths.get(i).equals(theRightBundle));
        }


    }

    @Test
    public void changeTruths() throws Exception{
        Proof proof = new Proof();
        BinaryTree tree = new BinaryTree(new BinaryTree.TreeNode("=>",new BinaryTree.TreeNode("q"),new BinaryTree.TreeNode("q")));
        Bundle dummyBundle = new Bundle("3",new BinaryTree(),"show");       //Checks that the last bundle in truths is pulled
        Bundle showBundle = new Bundle("1",tree,"show");
        Bundle assumeBundle = new Bundle("2",new BinaryTree(new BinaryTree.TreeNode("q")),"assume");
        Bundle ICBundle = new Bundle("3",tree,"ic","2");

        proof.show(dummyBundle);
        proof.show(showBundle);
        proof.assume(assumeBundle);
        proof.changeTruths();

        LinkedList<Bundle> truths = proof.getTruths();
        assertEquals("1", truths.getLast().getLineNumber());
        assertEquals("true", truths.getLast().getThrmName());
        assertEquals(tree,truths.getLast().getMyTree());
        assertNull(truths.getLast().getRefLine1());
        assertEquals(2,truths.size());

    }


    @Test
    public void testIC() throws Exception{
        Proof proof = new Proof();
        BinaryTree tree = new BinaryTree(new BinaryTree.TreeNode("=>",new BinaryTree.TreeNode("q"),new BinaryTree.TreeNode("q")));
        Bundle dummyBundle = new Bundle("3",new BinaryTree(),"show");       //Checks that the last bundle in truths is pulled
        Bundle showBundle = new Bundle("1",tree,"show");
        Bundle assumeBundle = new Bundle("2",new BinaryTree(new BinaryTree.TreeNode("q")),"assume");
        Bundle ICBundle = new Bundle("3",tree,"ic","2");

        proof.show(dummyBundle);
        proof.show(showBundle);
        proof.assume(assumeBundle);
        proof.IC(ICBundle);

        LinkedList<Bundle> truths = proof.getTruths();
        assertEquals("1", truths.getLast().getLineNumber());
        assertEquals("true", truths.getLast().getThrmName());
        assertEquals(tree,truths.getLast().getMyTree());
        assertNull(truths.getLast().getRefLine1());
        assertEquals(2,truths.size());

    }

    @Test
    public void testProofTwo() throws Exception{
        Proof proof = new Proof();
        BinaryTree tree = new BinaryTree(new BinaryTree.TreeNode("=>",
                new BinaryTree.TreeNode("p"),
                new BinaryTree.TreeNode("=>",
                        new BinaryTree.TreeNode("=>",
                                new BinaryTree.TreeNode("p"),
                                new BinaryTree.TreeNode("q")),
                        new BinaryTree.TreeNode ("q"))));

        Bundle show1Bundle = new Bundle("1",tree,"show");

        Bundle assume1Bundle = new Bundle("2",new BinaryTree(new BinaryTree.TreeNode("p")),"assume");
        Bundle show2Bundle = new Bundle("3",new BinaryTree(new BinaryTree.TreeNode("=>",
                new BinaryTree.TreeNode("=>",
                        new BinaryTree.TreeNode("p"),
                        new BinaryTree.TreeNode("q")),
                new BinaryTree.TreeNode ("q"))),"show");
        Bundle assume2Bundle = new Bundle("3.1",new BinaryTree(new BinaryTree.TreeNode("=>",new BinaryTree.TreeNode("p"),new BinaryTree.TreeNode("q"))),"assume");
        Bundle show3Bundle = new Bundle("3.2", new BinaryTree(new BinaryTree.TreeNode("q")),"show");


        Bundle MPBundle = new Bundle("3.2.1",new BinaryTree(new BinaryTree.TreeNode("q")),"mp","2","3.1");

        Bundle IC1Bundle = new Bundle("3.3",new BinaryTree(new BinaryTree.TreeNode("=>",
                new BinaryTree.TreeNode("=>",
                        new BinaryTree.TreeNode("p"),
                        new BinaryTree.TreeNode("q")),
                new BinaryTree.TreeNode ("q"))),"ic","3.2");
        Bundle IC2Bundle = new Bundle("4", tree,"ic","3");

        proof.show(show1Bundle);
        proof.assume(assume1Bundle);
        proof.show(show2Bundle);
        proof.assume(assume2Bundle);
        proof.show(show3Bundle);
        proof.MP(MPBundle);
        proof.IC(IC1Bundle);

        proof.IC(IC2Bundle);

        LinkedList<Bundle> truths = proof.getTruths();
        assertEquals(1,truths.size());
        assertEquals("true", truths.getFirst().getThrmName());
        assertEquals(tree,truths.getLast().getMyTree());

    }

    @Test
    public void testMP() throws Exception{
        Proof proof = new Proof();
        BinaryTree tree = new BinaryTree(new BinaryTree.TreeNode("=>",
                new BinaryTree.TreeNode("p"),
                new BinaryTree.TreeNode("=>",
                        new BinaryTree.TreeNode("=>",
                                new BinaryTree.TreeNode("p"),
                                new BinaryTree.TreeNode("q")),
                        new BinaryTree.TreeNode ("q"))));

        Bundle dummyBundle = new Bundle("1",new BinaryTree(),"show");       //Checks that the last bundle in truths is pulled
        Bundle show1Bundle = new Bundle("2",tree,"show");

        Bundle assume1Bundle = new Bundle("3",new BinaryTree(new BinaryTree.TreeNode("p")),"assume");
        Bundle show2Bundle = new Bundle("4",new BinaryTree(new BinaryTree.TreeNode("=>",
                new BinaryTree.TreeNode("=>",
                        new BinaryTree.TreeNode("p"),
                        new BinaryTree.TreeNode("q")),
                new BinaryTree.TreeNode ("q"))),"show");
        Bundle assume2Bundle = new Bundle("4.1",new BinaryTree(new BinaryTree.TreeNode("=>",new BinaryTree.TreeNode("p"),new BinaryTree.TreeNode("q"))),"assume");
        Bundle show3Bundle = new Bundle("4.2", new BinaryTree(new BinaryTree.TreeNode("q")),"show");

        Bundle MPBundle = new Bundle("4.2.1",new BinaryTree(new BinaryTree.TreeNode("q")),"mp","3","4.1");

        proof.show(dummyBundle);
        proof.show(show1Bundle);
        proof.assume(assume1Bundle);
        proof.show(show2Bundle);
        proof.assume(assume2Bundle);
        proof.show(show3Bundle);
        proof.MP(MPBundle);

        LinkedList<Bundle> truths = proof.getTruths();
        assertEquals("1", truths.getFirst().getLineNumber());
        assertEquals("true", truths.getLast().getThrmName());
        assertTrue((new BinaryTree(new BinaryTree.TreeNode("q"))).equals(truths.getLast().getMyTree()));
        assertNull(truths.getFirst().getRefLine1());
        assertEquals(6,truths.size());

    }
    @Test
    public void testMT() {

    }

    @Test
    public void testInputTheoremsChecking() {

    }
    @Test
    public void testCheckLine() {
        //Fill in after you have tested all of the other methods required for the routing that checkLine is doing.
    }

    @Test
    public void print() throws  Exception{
        Proof proof = new Proof();
        BinaryTree tree = new BinaryTree(new BinaryTree.TreeNode("=>",
                new BinaryTree.TreeNode("p"),
                new BinaryTree.TreeNode("=>",
                        new BinaryTree.TreeNode("=>",
                                new BinaryTree.TreeNode("p"),
                                new BinaryTree.TreeNode("q")),
                        new BinaryTree.TreeNode ("q"))));

        Bundle show1Bundle = new Bundle("1",tree,"show");

        Bundle assume1Bundle = new Bundle("2",new BinaryTree(new BinaryTree.TreeNode("p")),"assume");
        Bundle show2Bundle = new Bundle("3",new BinaryTree(new BinaryTree.TreeNode("=>",
                new BinaryTree.TreeNode("=>",
                        new BinaryTree.TreeNode("p"),
                        new BinaryTree.TreeNode("q")),
                new BinaryTree.TreeNode ("q"))),"show");
        Bundle assume2Bundle = new Bundle("3.1",new BinaryTree(new BinaryTree.TreeNode("=>",new BinaryTree.TreeNode("p"),new BinaryTree.TreeNode("q"))),"assume");
        Bundle show3Bundle = new Bundle("3.2", new BinaryTree(new BinaryTree.TreeNode("q")),"show");


        Bundle MPBundle = new Bundle("3.2.1",new BinaryTree(new BinaryTree.TreeNode("q")),"mp","2","3.1");

        Bundle IC1Bundle = new Bundle("3.3",new BinaryTree(new BinaryTree.TreeNode("=>",
                new BinaryTree.TreeNode("=>",
                        new BinaryTree.TreeNode("p"),
                        new BinaryTree.TreeNode("q")),
                new BinaryTree.TreeNode ("q"))),"ic","3.2");
        Bundle IC2Bundle = new Bundle("4", tree,"ic","3");

        proof.show(show1Bundle);
        proof.assume(assume1Bundle);
        proof.show(show2Bundle);
        proof.assume(assume2Bundle);
        proof.show(show3Bundle);
        proof.MP(MPBundle);
        proof.IC(IC1Bundle);

        proof.IC(IC2Bundle);
        proof.print();

        LinkedList<Bundle> truths = proof.getTruths();
        assertEquals(1,truths.size());
        assertEquals("true", truths.getFirst().getThrmName());
        assertEquals(tree,truths.getLast().getMyTree());

    }

}
