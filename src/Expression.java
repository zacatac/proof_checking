import java.util.*;
//import static java.lang.Math.*;

public class Expression {

    protected String fullExpr;// input
	private boolean isValid; //protected BinaryTree expressionTree;
	private BinaryTree myExpression;
//    public String getfullExpr(){
//    	return fullExpr;
//    }
//    
    
    
	public Expression (String s) throws IllegalLineException {
		fullExpr = s;
		checkLegal(fullExpr);
	}
	
	
	
	public void checkLegal(String fullExpr) throws IllegalLineException{
		int leftParen = 0; 
		int rightParen = 0;
		int numOfLetters = 0;
		int numOfParen;
//		fullExpr.replaceAll("\\s","");
		for (int k = 0; k < fullExpr.length(); k++) {
			if (fullExpr.charAt(k) == '(') {
				leftParen++;
			}
			if (fullExpr.charAt(k) == ')') {
				rightParen++;
			} 
			if (Character.isLetter(fullExpr.charAt(k))){
				numOfLetters++;
			}
		}
		numOfParen = leftParen + rightParen;
		
		if (leftParen != rightParen) {
			throw new IllegalLineException("Unmatched Parens");
		}
		
		if (numOfParen != 2*(numOfLetters-1)) {	
			throw new IllegalLineException("Number of Parentheses unmatched to Number of expressions");
		} else if (fullExpr == null || fullExpr == ""){
			throw new IllegalLineException("Expression cannot be null or empty");
		}
		
		
		for (int i = 0; i < fullExpr.length(); i++) {

			if (fullExpr.charAt(i) == '('||
				fullExpr.charAt(i) == ')' ||
				Character.isLetter(fullExpr.charAt(i)) ||
				fullExpr.charAt(i) == '|' ||
				fullExpr.charAt(i) == '&' ||
				fullExpr.charAt(i) == '=' ||
				fullExpr.charAt(i) == '~' ||
				fullExpr.charAt(i) == '>'){
				continue;
			} else {
				throw new IllegalLineException("Illegal Character Detected");
			}

		}
		
		for (int i = 0; i < fullExpr.length()-1; i++) {
			if (fullExpr.charAt(i) == '|'||fullExpr.charAt(i) == '&') {
				if (fullExpr.charAt(i+1) == '|'||fullExpr.charAt(i+1) == '&') {
					throw new IllegalLineException("Consecutive comparison characters not allowed");
				}
			}
			if (fullExpr.charAt(i) == '=') {
				if (fullExpr.charAt(i+1) == '=') {
					throw new IllegalLineException("Consecutive Equals Not Allowed");
				}
			}
			if (Character.isLetter(fullExpr.charAt(i))) {	
				if (Character.isLetter(fullExpr.charAt(i+1))) {
					throw new IllegalLineException("Consecutive Letters Not Allowed");
				}
			}

		}
		
		
	}

    


	public static BinaryTree exprTree(Expression e) throws IllegalLineException {
		return BinaryTree.exprTree(e.fullExpr);
	}

}
