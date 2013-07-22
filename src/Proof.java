import java.util.*;

public class Proof {
    ArrayList<Bundle>


    public Proof (TheoremSet theorems) {
    }

    public LineNumber nextLineNumber ( ) {
        return null;
    }

		// return line.next()
	}

	public void extendProof (String x) throws IllegalLineException, IllegalInferenceException {
		String[] splitted = x.split (" ");
		Stack characters = new Stack( );
			for(int k=0; k < splitted.length; k++) {
				if(splitted[k].charAt(0) == '(') { 
					Expression NewExpress = new Expression(splitted[k]);
					if (splitted[k].charAt(0) == '(') {
						charcters.push(parts[k].charAt(0));
						//opening parenthesis on stack
						//**Check for nested parenthesis (not done, maybe it would be easier to use a tree for this 
                        //or some kind of recursion)
					}
					if (splitted[k].charAt(0) == ')') {
						characters.pop();
						
						if (characters.empty() == false) {
							//stack should be empty at end
							//(same number of parens pushed on and popped off)
							throw new IllegalLineException();
							
							
						else if(splitted[0].equals("assume")) {
							//fill
							}
						else if(splitted[0].equals("co")) {
							//fill
							}
						else if(splitted[0].equals("show")) {
							//fill
							}
						else if(splitted[0].equals("mt")) {
							//fill
							}
						else if(splitted[0].equals("mp")) {
							//fill
							}
						else if(splitted[0].equals("repeat")) {
							//fill
							}
						
					}
				}
				
			}
		
			
		
	}

    public String toString ( ) {
        return "";
    }

    public boolean isComplete ( ) {
        return true;
    }

    public ArrayList<Bundle> showMethod(ExpTree showStatement) {
        ArrayList<Bundle> tempList = new ArrayList<Bundle>();
        Bundle showBundle = new Bundle(showExpTree,LINE,thmnAME)
        tempList.add(0, showBundle);

    }
}