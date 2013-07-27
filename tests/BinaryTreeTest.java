import junit.framework.TestCase;
import java.util.*;


public class BinaryTreeTest extends TestCase{
    public void testPatternMatching() {
        BinaryTree thm = new BinaryTree (new BinaryTree.TreeNode("=>", new BinaryTree.TreeNode("p"), new BinaryTree.TreeNode("&", new BinaryTree.TreeNode("p"), new BinaryTree.TreeNode("q"))));
//        thm.print();
        BinaryTree test = new BinaryTree (new BinaryTree.TreeNode("=>", new BinaryTree.TreeNode("|", new BinaryTree.TreeNode("a"), new BinaryTree.TreeNode("b")), new BinaryTree.TreeNode("&", new BinaryTree.TreeNode("|", new BinaryTree.TreeNode("a"), new BinaryTree.TreeNode("b")), new BinaryTree.TreeNode("c"))));
//        test.print();
        BinaryTree test2 = new BinaryTree (new BinaryTree.TreeNode("=>", new BinaryTree.TreeNode("|", new BinaryTree.TreeNode("a"), new BinaryTree.TreeNode("b")), new BinaryTree.TreeNode("&", new BinaryTree.TreeNode("|", new BinaryTree.TreeNode("c"), new BinaryTree.TreeNode("b")), new BinaryTree.TreeNode("c"))));
//        test2.print();
        assertTrue(test.canbematched(thm));
        assertFalse(test2.canbematched(thm));
    }
    public void testEquals() {
        BinaryTree t = new BinaryTree (new BinaryTree.TreeNode("a", new BinaryTree.TreeNode("b", new BinaryTree.TreeNode("d"), null), new BinaryTree.TreeNode("c")) );
        BinaryTree t2 = new BinaryTree (new BinaryTree.TreeNode("a", new BinaryTree.TreeNode("b", new BinaryTree.TreeNode("d"), null), new BinaryTree.TreeNode("c")) );
        assertTrue(t.equals(t2));
        assertTrue(t2.equals(t));
    }

    public void testInOrderPrint() {
        BinaryTree thm = new BinaryTree (new BinaryTree.TreeNode("=>", new BinaryTree.TreeNode("p"), new BinaryTree.TreeNode("&", new BinaryTree.TreeNode("p"), new BinaryTree.TreeNode("q"))));
        thm.print();
        BinaryTree test = new BinaryTree (new BinaryTree.TreeNode("=>", new BinaryTree.TreeNode("|", new BinaryTree.TreeNode("a"), new BinaryTree.TreeNode("b")), new BinaryTree.TreeNode("&", new BinaryTree.TreeNode("|", new BinaryTree.TreeNode("a"), new BinaryTree.TreeNode("b")), new BinaryTree.TreeNode("c"))));
//        test.print();
        BinaryTree test2 = new BinaryTree (new BinaryTree.TreeNode("=>", new BinaryTree.TreeNode("|", new BinaryTree.TreeNode("a"), new BinaryTree.TreeNode("b")), new BinaryTree.TreeNode("&", new BinaryTree.TreeNode("|", new BinaryTree.TreeNode("c"), new BinaryTree.TreeNode("b")), new BinaryTree.TreeNode("c"))));
//        test2.print();

        System.out.println("THM INORDER");
        System.out.println(thm.inOrderString());

        BinaryTree t = new BinaryTree (new BinaryTree.TreeNode("a", new BinaryTree.TreeNode("b", new BinaryTree.TreeNode("d"), null), new BinaryTree.TreeNode("c")) );
        t.print();
        System.out.println("DEBUGGING TREE");
        System.out.println(t.inOrderString());
    }
}