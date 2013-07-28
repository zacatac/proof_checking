import junit.framework.TestCase;

//EVERYTHING WITH PARENS WORKS BUT NOTHING WITHOUT THEM WORKS THIS IS SO SAD IM NARRATING MY LIFE D:
public class ExpressionTest extends TestCase {
	
	public void testTree(){
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
			System.out.println(e);
		}
		System.out.println(myExpr.fullExpr);
		
		//BinaryTree b = new BinaryTree();
		BinaryTree expressionTree = myExpr.exprTree(myExpr.fullExpr);
		System.out.println();
		expressionTree.print();

		System.out.println(myExpr2.fullExpr);
		//BinaryTree c = new BinaryTree();
		expressionTree = myExpr2.exprTree(myExpr2.fullExpr);
		System.out.println();
		expressionTree.print();
		
		System.out.println(myExpr3.fullExpr);
		//BinaryTree d = new BinaryTree();
		expressionTree = myExpr3.exprTree(myExpr3.fullExpr);
		System.out.println();
		expressionTree.print();
		System.out.println("Testing tree 4");
		
		System.out.println(myExpr4.fullExpr);
		//BinaryTree d = new BinaryTree();
		expressionTree = myExpr4.exprTree(myExpr4.fullExpr);
		System.out.println();
		expressionTree.print();
		
		System.out.println(myExpr5.fullExpr);
		//BinaryTree d = new BinaryTree();
		expressionTree = myExpr5.exprTree(myExpr5.fullExpr);
		System.out.println();
		expressionTree.print();
		
		System.out.println(myExpr6.fullExpr);
		//BinaryTree d = new BinaryTree();
		expressionTree = myExpr6.exprTree(myExpr6.fullExpr);
		System.out.println();
		expressionTree.print();
		
		System.out.println(parenest.fullExpr);
		//BinaryTree d = new BinaryTree();
		expressionTree = parenest.exprTree(parenest.fullExpr);
		System.out.println();
		expressionTree.print();
	}
	
}

