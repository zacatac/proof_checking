import junit.framework.TestCase;

//EVERYTHING WITH PARENS WORKS BUT NOTHING WITHOUT THEM WORKS THIS IS SO SAD IM NARRATING MY LIFE D:
public class ExpressionTest extends TestCase {

	public void testExpression() throws Exception{


		Expression expObj;
		String myString;
		myString = "((p=>q)=>~((p|~q)=>q))";
		try {
			expObj = new Expression(myString);
		}catch (IllegalLineException e) {}
		String myString_ = "(p=>(~p=>q))";
		
		BinaryTree myExp;
		myExp = BinaryTree.exprTree(myString_);
		System.out.println("asdfasdfa");
		myExp.print();
		
		
		try {
		myString = new String("(p && q)");
		expObj = new Expression(myString);
		fail("Should have caught double &");
		} catch (IllegalLineException e) {

		}
		
		try {
		myString = new String("(p |& q))");
		expObj = new Expression(myString);
		fail("Should have caught |&");
		} catch (IllegalLineException e) {

		}

		try {
		myString = new String("p || q");
		Expression sdfgsdfg = new Expression(myString);
		fail("Should have caught double ||");
		} catch (IllegalLineException e) {

		}

		try {
		myString = new String("p == q");
		Expression sdfgsdfg = new Expression(myString);
		fail("Should have caught double =");
		} catch (IllegalLineException e) {

		}

		try {
		myString = new String("$");
		Expression sdfgsdfg = new Expression(myString);
		fail("Should have caught illegal character");
		} catch (IllegalLineException e) {

		}

		String myString7;
		try {
		myString = new String("ap");
		Expression sdfgsdfg = new Expression(myString);
		fail("Should not have two characters that are not special characters next to eachother");
		} catch (IllegalLineException e) {

		}

		try {
		myString = "";
		Expression sdfgsdfg = new Expression(myString);
		fail("Should have said you cannot have an empty expression");
		} catch (IllegalLineException e) {

		}

		try {
		myString = "((a)";
		Expression sdfgsdfg = new Expression(myString);
		fail("Should have caught unequal parens Left");
		} catch (IllegalLineException e) {

		}

		try {
		myString = "(a";
		Expression sdfgsdfg = new Expression(myString);
		fail("Should have caught unequal parens Right, things need to be closed with parens");
		} catch (IllegalLineException e) {

		}

		try {
			myString = "((~p))";
			Expression sdfgsdfg = new Expression(myString);
			BinaryTree _mytree = Expression.exprTree(sdfgsdfg);
//			System.out.println(_myTree.)
//			System.out.println(_mytree.num);
			_mytree.print();
			
			fail("Should catch too many parens");
		} catch (IllegalLineException e) {
			
		}
		try {
			myString = "p=>q";
			Expression sdfgsdfg = new Expression(myString);
			fail("supposed to catch the lack of parens");
		} catch (IllegalLineException e) {
			
		}


		
	}


}
