import org.junit.Test;

import java.util.LinkedList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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
        Bundle resultingScope = proof.getLastTruthInScope();
        assertEquals(1,resultingList.size());
        assertTrue(resultingList.contains(bundle2));
        assertEquals(resultingScope,resultingList.get(0));

    }

    @Test
    public void testAssume() throws Exception{
        Proof proof = new Proof();
        BinaryTree tree = new BinaryTree (new BinaryTree.TreeNode("=>", new BinaryTree.TreeNode("&", new BinaryTree.TreeNode("p"), new BinaryTree.TreeNode("q")), new BinaryTree.TreeNode("q")) );
        Bundle dummyBundle = new Bundle(null,new BinaryTree(),"show");       //Checks that the last bundle in truths is pulled
        Bundle mainBundle = new Bundle("1",tree,"show");
        // Bundle wrongThrmNameBundle = new Bundle("2",tree,"",) cant test yet

        proof.show(dummyBundle);
        proof.show(mainBundle);

        BinaryTree notTheRightTree = new BinaryTree();
        Bundle notTheRightBundle = new Bundle(null,notTheRightTree,null);
        //proof.assume(notTheRightBundle); //Throws exception


        BinaryTree notAssumeTree = new BinaryTree(new BinaryTree.TreeNode("~",new BinaryTree.TreeNode("=>", new BinaryTree.TreeNode("&", new BinaryTree.TreeNode("p"), new BinaryTree.TreeNode("q")), new BinaryTree.TreeNode("q")),null));
        Bundle assumeRightBundle1 = new Bundle("1",notAssumeTree,"2");

        proof.assume(assumeRightBundle1);
        assertEquals(assumeRightBundle1,proof.getLastTruthInScope());
//        assertEquals(assumeRightBundle1,proof.getTruths().get(proof.getTruths().size()-1));

        proof = new Proof();
        proof.show(dummyBundle);
        proof.show(mainBundle);

        BinaryTree leftAssumeTree = new BinaryTree(new BinaryTree.TreeNode("&", new BinaryTree.TreeNode("p"), new BinaryTree.TreeNode("q")));
        Bundle assumeRightBundle2 = new Bundle(null,leftAssumeTree,null);

        proof.assume(assumeRightBundle2);
        assertEquals(assumeRightBundle2,proof.getLastTruthInScope());
//        assertEquals(assumeRightBundle2,proof.getTruths().get(proof.getTruths().size()-1));


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
        Bundle notTheRightBundle = new Bundle(null,notTheRightTree,null);
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
    public void testCheckLine() {
        //Fill in after you have tested all of the other methods required for the routing that checkLine is doing.
    }
}
