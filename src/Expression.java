
//expression tree time!

public class Expression {
    
    protected String fullExpr;
    
    //FOR TESTING PURPOSES, CONSTRUCTOR DOES NOT THROW ILLEGAL LINE EXCEPTION YET
    // append throws IllegalLineException to the end of the constructor declaration later
        //public Expression (String s) throws IllegalLineException {
    	//    fullExpr = s;	
    	//}

    public Expression (String s) {
        fullExpr = s;	
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

}

