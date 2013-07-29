import java.util.*;


public class Expression {

    protected String fullExpr; // input
	//protected BinaryTree expressionTree;

	public Expression (String s) throws IllegalLineException {
		fullExpr = s;
		int leftParen = 0; 
		int rightParen = 0;
		
		fullExpr.replaceAll("\\s","");
		
		for (int k = 0; k < fullExpr.length(); k++) {
			if (fullExpr.charAt(k) == '=') {
				if (fullExpr.charAt(k+1) != '>') {
					throw new IllegalLineException("Misformed implication");
				} else {
					if ((fullExpr.charAt(0) !='(') || (fullExpr.charAt(fullExpr.length()-1) !=')')); 
					System.out.println(fullExpr.charAt(0));
					System.out.println("y");
						throw new IllegalLineException("Must have parens if => is present on beginning and end");					
				}			
		}	
	}	
		if (fullExpr == null || fullExpr == "") {
			throw new IllegalLineException("Cannot have a null expression or empty expression");
		}
		for (int i = 0; i < fullExpr.length(); i++) {
			if (fullExpr.charAt(i) == '(') {
				leftParen = leftParen +1;
			} else if (fullExpr.charAt(i) == ')') {
				rightParen = rightParen +1;
			} else if (fullExpr.charAt(i) == ' ') {
				fullExpr.replace (" ", "");
			} else if (fullExpr.charAt(i) != '('||
			fullExpr.charAt(i) != ')' ||
			fullExpr.charAt(i) != '|' ||
			fullExpr.charAt(i) != '&' ||
			fullExpr.charAt(i) != '(' ||
			fullExpr.charAt(i) != '=' ||
			fullExpr.charAt(i) != '>') {
				throw new IllegalLineException("Invalid character");
			}
		
		}
		if (rightParen != leftParen){
			throw new IllegalLineException("Uneven number of parentheses");
		}
		for (int i = 0; i < fullExpr.length()-1; i++) {
			if (fullExpr.charAt(i) == '|'||fullExpr.charAt(i) == '&') {
				if (fullExpr.charAt(i) == '|'||fullExpr.charAt(i) == '&') {
					throw new IllegalLineException("No consecutive operations");
				}
			}
			if (fullExpr.charAt(i) == '=') {
				if (fullExpr.charAt(i) == '=') {
					throw new IllegalLineException("No consecutive equals");
				}
			}
			
			if ((fullExpr.charAt(i) == 'm') && ((fullExpr.charAt(i+1) == 'p') || (fullExpr.charAt(i+1) =='t'))) {
				continue;
			}
			if ((fullExpr.charAt(i) == 'i') && (fullExpr.charAt(i+1) == 'c'))  {
				continue;
			}
			if ((fullExpr.charAt(i) == 'c') && (fullExpr.charAt(i+1) == 'o'))  {
				continue;
			} else {
				if (Character.isLetter(fullExpr.charAt(i))) {	
					if (Character.isLetter(fullExpr.charAt(i))) {
						throw new IllegalLineException("No two letters next to eachother");
				
					}
				}
			}
		}
		
    }
	
	
	public static BinaryTree exprTree(String fullExpr) {
		return BinaryTree.exprTree(fullExpr);
	}
}
	


    //public Expression (String s) throws IllegalLineException {
    //    fullExpr = s;	
    //}

//	private ExpNode treeChecker (String k) throws IllegalLineException {
//
//		private int depth = 0;
//		private int operatorLocation = 0;
//
//		// if there are no parens present, then you can only start with a character or '~' + character
//		//Therefore, we check first to see what we would do if no parens were present:
//		if (k.charAt(0) != '(') {
//			//Following line(s) written with help from(http://stackoverflow.com/questions/12715246/how-to-check-if-a-character-in-a-string-is-a-digit-or-letter)
//			if (Character.isLetter(k.charAt(0))&&(k.length ==1)){
//					return new ExpNode(k.charAt(0));
//				}
//			if (Character.isLetter(k.charAt(0))&&(k.length()==2)){
//				return new ExpNode(k.charAt(0)), ExpNode(k.charAt(1));
//			} else {
//				throw new IllegalLineExpression();
//			}
//		}
//
//		//To get to this step, your string k would have to contain the parenthesis.
//		//Here we take off the parenthesis and find an operator and create two subtrees.
//		//given operations |, &, =>, ~, (, )
//	} else if{
//		boolean nextValue = true;
//		for (int i=1; i<k.legnth()-1; i++){
//			if (k.charAt(i) == '(') {
//				depth = depth + 1;
//			}
//			if (k.charAt(i) == ')') {
//				depth = depth - 1
//			}
//			if (k.charAt(i) == '|' && depth == 0) {
//				operatorLocation == i;
//				break;
//			}
//			if (k.charAt(i) == '&' && depth == 0) {
//				operatorLocation == i;
//				break;
//			}
//			if (k.charAt(i) == '=' && depth == 0 && k.charAt(i+1)=='>'){
//				nextValue == True;
//			// This might have an error, I meant to say at position 0 and 1 there should be '=>'
//				//I'm not sure if this does that exactly. If someone could double check that would be
//				//appreciated
//				operatorLocation == i;
//
//				break;
//			}
//		}
//	new String operatorNode = (k.substring (1 , operatorLocation));
//	new String operatorNode2;
//	new String operatorNode3;
//	if (nextValue == True){
//		operatorNode2 = k.substring(operatorLocation += 2, k.length() - 1 );
//		operatorNode3 =  k.substring(operatorLocation, operatorLocation +=2);
//	} else {
//		operatorNode2 =  k.substring(operatorLocation += 1, k.length() - 1 );
//		operatorNode3 = k.substring(operatorLocation, operatorLocation +=1);

//	}
//	return new ExpNode (operatorNode3, treeChecker(operatorNode2), treeChecker(operatorNode));
//
//	}
