import junit.framework.TestCase;

//EVERYTHING WITH PARENS WORKS BUT NOTHING WITHOUT THEM WORKS THIS IS SO SAD IM NARRATING MY LIFE D:
public class ExpressionTest extends TestCase {

	public void testExpression() {

		
		String myString;
		try {
		myString = new String("(p && q)");
		Expression sdfgsdfg = new Expression(myString);
		fail("Should have caught double &");
		} catch (IllegalLineException e) {
			
		}
		
		String myString2;
		try {
		myString2 = new String("(p |& q))");
		Expression sdfgsdfg = new Expression(myString2);
		fail("Should have caught |&");
		} catch (IllegalLineException e) {
			
		}
		
		String myString3;
		try {
		myString3 = new String("p || q");
		Expression sdfgsdfg = new Expression(myString3);
		fail("Should have caught double ||");
		} catch (IllegalLineException e) {
			
		}
		
		String myString4;
		try {
		myString4 = new String("p == q");
		Expression sdfgsdfg = new Expression(myString4);
		fail("Should have caught double =");
		} catch (IllegalLineException e) {
			
		}
		
		String myString5;
		try {
		myString5 = new String("$ == #");
		Expression sdfgsdfg = new Expression(myString5);
		fail("Should have caught illegal character");
		} catch (IllegalLineException e) {
			
		}
		
		String myString6;
		try {
		myString6 = new String("ap == bd");
		Expression sdfgsdfg = new Expression(myString6);
		fail("Should not have two characters that are not special characters next to eachother");
		} catch (IllegalLineException e) {
			
		}
		
		String myString7;
		try {
		myString7 = new String("ap == bd");
		Expression sdfgsdfg = new Expression(myString7);
		fail("Should not have two characters that are not special characters next to eachother");
		} catch (IllegalLineException e) {
			
		}
		
		String myString8;
		try {
		myString8 = "";
		Expression sdfgsdfg = new Expression(myString8);
		fail("Should have said you cannot have an empty expression");
		} catch (IllegalLineException e) {
			
		}
		
	}
	
	
	
	
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
		
		
		try{
			myExpr = new Expression ("(#)");
		} catch (IllegalLineException e){
			System.out.println(e);
			
		}

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
