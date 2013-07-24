public class Expression {

    public Expression (String s) throws IllegalLineException {
	}
}
	private ExpNode treeChecker (String k) throws IllegalLineException {
		
		// if there are no parens present, then you can only start with a character or '~' + character
		//Therefore, we check first to see what we would do if no parens were present: 
		if (k.charAt(0) != '(') {
			//Following line(s) written with help from(http://stackoverflow.com/questions/12715246/how-to-check-if-a-character-in-a-string-is-a-digit-or-letter)
		
			if (Character.isLetter(k.charAt(0))&&(k.length ==1)){
					return new ExpNode(k.charAt(0));
				}
			if (Character.isLetter(k.charAt(0))&&(k.length()==2)){
				return new ExpNode(k.charAt(0)), ExpNode(k.charAt(1));
			} else {
				throw new IllegalLineExpression();
			}
		}
		
		//To get to this step, your string k would have to contain the parenthesis. 
		//Here we take off the parenthesis and find an operator and create two subtrees.
		if 			
				
			
				
		}
