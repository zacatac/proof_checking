import junit.framework.TestCase;


public class ExpressionTest extends TestCase {
	
	public void testTree(){
		Expression myExpr = null;
		Expression myExpr2 = null;
		try{ 
			myExpr = new Expression("((p=>q)=>(c&d))");
			myExpr2 = new Expression("((~p=>q)=>c)");
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
		
		
	}
	
}
