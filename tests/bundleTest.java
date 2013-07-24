import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created with IntelliJ IDEA.
 * User: Zack
 * Date: 7/24/13
 * Time: 9:50 AM
 * To change this template use File | Settings | File Templates.
 */
public class bundleTest {

    @Test
    public void testConstructionAndAccess(){
        BinaryTree myTree = new BinaryTree();
        Bundle myBundleNoArgs = new Bundle();
        Bundle myBundleThreeArgs = new Bundle("1",myTree,"and1");
        Bundle myBundleFourArgs = new Bundle("1",myTree,"and1","2");
        Bundle myBundleFiveArgs  = new Bundle("1",myTree,"and1","2","3");

        assertNull(myBundleNoArgs.getLineNumber());
        assertNull(myBundleNoArgs.getMyTree());
        assertNull(myBundleNoArgs.getThrmName());
        assertNull(myBundleNoArgs.getRefLine1());
        assertNull(myBundleNoArgs.getRefLine2());

        assertEquals("1",myBundleThreeArgs.getLineNumber());
        assertEquals("1",myBundleFourArgs.getLineNumber());
        assertEquals("1",myBundleFiveArgs.getLineNumber());

        assertEquals(myTree,myBundleThreeArgs.getMyTree());
        assertEquals(myTree,myBundleFourArgs.getMyTree());
        assertEquals(myTree,myBundleFiveArgs.getMyTree());

        assertEquals("and1",myBundleThreeArgs.getThrmName());
        assertEquals("and1",myBundleFourArgs.getThrmName());
        assertEquals("and1",myBundleFiveArgs.getThrmName());

        assertEquals(null,myBundleThreeArgs.getRefLine1());
        assertEquals("2",myBundleFourArgs.getRefLine1());
        assertEquals("2",myBundleFiveArgs.getRefLine1());

        assertEquals(null,myBundleThreeArgs.getRefLine2());
        assertEquals(null,myBundleFourArgs.getRefLine2());
        assertEquals("3",myBundleFiveArgs.getRefLine2());



    }
    @Test
    public void testEquals(){
        BinaryTree mainTree = new BinaryTree();
        Bundle mainBundle = new Bundle("1",mainTree,"2","3","4");

        List notEvenABundle = new List();
        assertFalse(mainBundle.equals(notEvenABundle));

        BinaryTree anotherTree = new BinaryTree(new BinaryTree.TreeNode("a"));
        Bundle wrongTreeBundle = new Bundle("1",anotherTree,"2","3","4");
        assertFalse(mainBundle.equals(wrongTreeBundle));

        Bundle sameBundle = new Bundle("1",mainTree,"2","3","4");
        assertTrue(mainBundle.equals(sameBundle));

        assertFalse(mainBundle.equals(null));

    }
}

