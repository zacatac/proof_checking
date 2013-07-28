import junit.framework.TestCase;

//EVERYTHING WITH PARENS WORKS BUT NOTHING WITHOUT THEM WORKS THIS IS SO SAD IM NARRATING MY LIFE D:
public class ExpressionTest extends TestCase {
	
	public void testTree(){
		Expression myExpr = null;
		Expression myExpr2 = null;
		Expression myExpr3 = null;
		
		try{ 
			myExpr = new Expression("((p=>q)=>(c&d))");
			myExpr2 = new Expression("((~p=>q)=>c)");
			myExpr3 = new Expression("(~a=>~~~b)");
		} catch (IllegalLineException e){
			
		}
		System.out.println(myExpr.fullExpr);		
		BinaryTree b = new BinaryTree();
		BinaryTree expressionTree = b.exprTree(myExpr.fullExpr);
		expressionTree.print();

		System.out.println(myExpr2.fullExpr);
		BinaryTree c = new BinaryTree();
		expressionTree = c.exprTree(myExpr2.fullExpr);
		expressionTree.print();
		
		System.out.println(myExpr3.fullExpr);
		BinaryTree d = new BinaryTree();
		expressionTree = d.exprTree(myExpr3.fullExpr);
		expressionTree.print();
		
		
	}
	
}

