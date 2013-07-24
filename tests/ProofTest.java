import org.junit.Test;

import static org.junit.Assert.assertEquals;
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

        List resultingList = proof.getTruths();
        Bundle resultingScope = proof.getLastTruthInScope();
        System.out.println(resultingList);
        System.out.println(resultingScope);
        assertEquals(1,resultingList.size());
        assertTrue(resultingList.contains(bundle2));
        assertEquals(resultingScope,resultingList.get(0));

    }
}
