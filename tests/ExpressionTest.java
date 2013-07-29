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
		myString5 = new String("$");
		Expression sdfgsdfg = new Expression(myString5);
		fail("Should have caught illegal character");
		} catch (IllegalLineException e) {
			
		}
		
		//String myString6;
		//try {
		//myString6 = new String("a => d");
		//Expression sdfgsdfg = new Expression(myString6);
		//fail("no parens");
		//} catch (IllegalLineException e) {
			
		//}
		
		String myString7;
		try {
		myString7 = new String("ap");
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
		
		String myString9;
		try {
		myString9 = "((a)";
		Expression sdfgsdfg = new Expression(myString9);
		fail("Should have caught unequal parens Left");
		} catch (IllegalLineException e) {
			
		}
		
		String myString10;
		try {
		myString10 = "(a";
		Expression sdfgsdfg = new Expression(myString10);
		fail("Should have caught unequal parens Right, things need to be closed with parens");
		} catch (IllegalLineException e) {
			
		}

		
		String myString11;
		try {
		myString11 = "(p=>(~p=>q))";
		Expression sdfgsdfg = new Expression(myString11);
		} catch (IllegalLineException e) {
			fail("What's supposed to work didn't work");
		}
			
		

		String myString12;
		try {
		myString12 = "(p=>(~p=>q))";
		Expression sdfgsdfg = new Expression(myString12);
		} catch (IllegalLineException e) {
			fail("What's supposed to work didn't work");
		}
	}
}
