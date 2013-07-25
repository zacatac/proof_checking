/**
 * Created with IntelliJ IDEA.
 * User: Zack
 * Date: 7/25/13
 * Time: 10:58 AM
 * To change this template use File | Settings | File Templates.
 */
import junit.framework.TestCase;


public class ExpressionTest extends TestCase {
  
	public void testTree(){
		Expression myExpr = new Expression("((p=>q)=>(c&d))");
		
		System.out.print(myExpr.fullExpr);
		BinaryTree b = new BinaryTree();
		BinaryTree expressionTree = b.exprTree(myExpr.fullExpr);
		expressionTree.print();
	}
}
