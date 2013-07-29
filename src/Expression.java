import java.util.*;
import static java.lang.Math.*;

public class Expression {

    protected String fullExpr;// input
	private boolean isValid; //protected BinaryTree expressionTree;
//    public String getfullExpr(){
//    	return fullExpr;
//    }
//    
    
    
	public Expression (String s) throws IllegalLineException {
		fullExpr = s;
		checkLegal();

	}
	public boolean checkLegal(){
		int leftParen = 0; 
		int rightParen = 0;
		int numOfLetters = 0;
		int numOfParen = leftParen + rightParen;
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
		
		if (leftParen != rightParen) {
			return false;
		}
		if (numOfParen != 2*(numOfLetters-1)) {	
			return false;
		} else if (fullExpr == null || fullExpr == ""){
			return false;
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
				return false;
			}

		}
		if (rightParen != leftParen){
			return false;
		}
		for (int i = 0; i < fullExpr.length()-1; i++) {
			if (fullExpr.charAt(i) == '|'||fullExpr.charAt(i) == '&') {
				if (fullExpr.charAt(i+1) == '|'||fullExpr.charAt(i+1) == '&') {
					return false;
				}
			}
			if (fullExpr.charAt(i) == '=') {
				if (fullExpr.charAt(i+1) == '=') {
					return false;
				}
			}
			if (Character.isLetter(fullExpr.charAt(i))) {	
				if (Character.isLetter(fullExpr.charAt(i+1))) {
					return false;
				}
			}

		}
		return true;
		
	}
	public static BinaryTree exprTree(Expression e) throws IllegalLineException {
		return BinaryTree.exprTree(e.fullExpr);
	}
}
