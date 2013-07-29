import junit.framework.TestCase;
import java.util.*;


public class BinaryTreeTest extends TestCase{
	public void testTree() throws IllegalLineException{
		Expression myExpr = null;
		Expression myExpr2 = null;
		Expression myExpr3 = null;
		Expression myExpr4 = null;
		Expression myExpr5 = null;
		Expression myExpr6 = null;
		Expression parenest = null;
		

		try{ 
			myExpr = new Expression("((p=>q)=>(c&d))");
			myExpr2 = new Expression("((~p=>q)=>c)");
			myExpr3 = new Expression("(~a=>~~~b)");
			myExpr4 = new Expression("(~(a|b)=>~~b)");
			myExpr5 = new Expression("(a=>~(~b))");
			myExpr6 = new Expression("(~(b=>(a|b)))");
			parenest = new Expression("(~a=>b)");
		} catch (IllegalLineException e){
//			System.out.println("Hi");
//			System.out.println(e);
		}
		System.out.println(myExpr.getfullExpr());
		
		
		//BinaryTree b = new BinaryTree();
		BinaryTree expressionTree = myExpr.exprTree(myExpr.fullExpr);
		System.out.println("Testing Tree 1");
		expressionTree.print();
		

		System.out.println(myExpr2.fullExpr);
		//BinaryTree c = new BinaryTree();
		expressionTree = myExpr2.exprTree(myExpr2.fullExpr);
		System.out.println("Testing Tree 2");
		expressionTree.print();

		System.out.println(myExpr3.fullExpr);
		//BinaryTree d = new BinaryTree();
		expressionTree = myExpr3.exprTree(myExpr3.fullExpr);
		System.out.println();
		expressionTree.print();
		System.out.println("Testing tree 3");

		System.out.println(myExpr4.fullExpr);
		//BinaryTree d = new BinaryTree();
		expressionTree = myExpr4.exprTree(myExpr4.fullExpr);
		System.out.println("Testing tree 4");
		expressionTree.print();

		System.out.println(myExpr5.fullExpr);
		//BinaryTree d = new BinaryTree();
		expressionTree = myExpr5.exprTree(myExpr5.fullExpr);
		System.out.println("Testing tree 5");
		expressionTree.print();

		System.out.println(myExpr6.fullExpr);
		//BinaryTree d = new BinaryTree();
		expressionTree = myExpr6.exprTree(myExpr6.fullExpr);
		System.out.println("Testing tree 6");
		expressionTree.print();

		System.out.println(parenest.fullExpr);
		//BinaryTree d = new BinaryTree();
		expressionTree = parenest.exprTree(parenest.fullExpr);
		System.out.println("Testing tree7");
		expressionTree.print();
	}
}


/*
	
	
    public void testPatternMatching() {
        BinaryTree thm = new BinaryTree (new BinaryTree.TreeNode("=>", new BinaryTree.TreeNode("p"), new BinaryTree.TreeNode("&", new BinaryTree.TreeNode("p"), new BinaryTree.TreeNode("q"))));
        thm.print();
        BinaryTree test = new BinaryTree (new BinaryTree.TreeNode("=>", new BinaryTree.TreeNode("|", new BinaryTree.TreeNode("a"), new BinaryTree.TreeNode("b")), new BinaryTree.TreeNode("&", new BinaryTree.TreeNode("|", new BinaryTree.TreeNode("a"), new BinaryTree.TreeNode("b")), new BinaryTree.TreeNode("c"))));
        test.print();
        BinaryTree test2 = new BinaryTree (new BinaryTree.TreeNode("=>", new BinaryTree.TreeNode("|", new BinaryTree.TreeNode("a"), new BinaryTree.TreeNode("b")), new BinaryTree.TreeNode("&", new BinaryTree.TreeNode("|", new BinaryTree.TreeNode("c"), new BinaryTree.TreeNode("b")), new BinaryTree.TreeNode("c"))));
        test2.print();
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
        test.print();
        BinaryTree test2 = new BinaryTree (new BinaryTree.TreeNode("=>", new BinaryTree.TreeNode("|", new BinaryTree.TreeNode("a"), new BinaryTree.TreeNode("b")), new BinaryTree.TreeNode("&", new BinaryTree.TreeNode("|", new BinaryTree.TreeNode("c"), new BinaryTree.TreeNode("b")), new BinaryTree.TreeNode("c"))));
        test2.print();

        System.out.println("THM INORDER");
        System.out.println(thm.inOrderString());

        BinaryTree t = new BinaryTree (new BinaryTree.TreeNode("a", new BinaryTree.TreeNode("b", new BinaryTree.TreeNode("d"), null), new BinaryTree.TreeNode("c")) );
        t.print();
        System.out.println("DEBUGGING TREE");
        System.out.println(t.inOrderString());
    }
  
	public void testCanMakeTree1() {
	try {
	BinaryTree A = BinaryTree.exprTree("(((");
	fail("Should have caught mutiple parens");
	}
		catch (IllegalLineException e) {
		
		}
	}
	public void testCanMakeTree2() {
		try {
		BinaryTree A = BinaryTree.exprTree("(~a)");
		//fail("Should have caught parens and ~");
		}
			catch (IllegalLineException e) {
			fail("(~a) should not work"); 
			}
		}
	}

*/
    
    
 
