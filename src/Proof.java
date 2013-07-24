import java.util.*;

public class Proof {
    private List truths;
    private Bundle lastTruthInScope; //reassign tail in globalTruth to this after completing shows
    TheoremSet myTheorems;

    public Proof (TheoremSet theorems) {
        truths = new List();
        lastTruthInScope = null;
        myTheorems = theorems;
    }

    public Proof (){
        this(null);
    }
    public LineNumber nextLineNumber ( ) {
        return null;
    }

    public List getTruths(){
        return truths;
    }
    public Bundle getLastTruthInScope(){
        return lastTruthInScope;
    }
	public void extendProof (String x) throws IllegalLineException, IllegalInferenceException {
		String[] splitted = x.split (" ");
		Stack characters = new Stack( );
			for(int k=0; k < splitted.length; k++) {
				if(splitted[k].charAt(0) == '(') { 
					Expression NewExpress = new Expression(splitted[k]);
					if (splitted[k].charAt(0) == '(') {
//						charcters.push(parts[k].charAt(0));
						//opening parenthesis on stack
						//**Check for nested parenthesis (not done, maybe it would be easier to use a tree for this 
                        //or some kind of recursion)
					}
					if (splitted[k].charAt(0) == ')') {
						characters.pop();

                        /**
                         * All of these must be made into Bundles,
                         * check out the Bundle class for more details
                         *
                         * All legal bundles (NO IllegalLineException cases)
                         * can be passed directly to checkLine so that the line
                         * can begin to be checked for inference consistency
                         */
						if (characters.empty() == false) {
							//stack should be empty at end
							//(same number of parens pushed on and popped off)
							throw new IllegalLineException("");
							
							
                        } else if(splitted[0].equals("assume")) {
							//fill
							}
						else if(splitted[0].equals("co")) {
							//fill
							}
						else if(splitted[0].equals("show")) {
							//call to Proof.show(thisLinesBundle)
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
        return false;
    }

    /**
     * Called when a show is struck, should be the first line of the proof.
     * These show statements must be included in the truths list. After each
     * interior show statement is proven the lastProofInScope field will be
     * updated to that show statement, because the level above is now allowed to
     * use that show statement as a truth.
     */
    public void show(Bundle showBundle) {
        lastTruthInScope = showBundle;
        truths.add(lastTruthInScope); // updates the latest scope.
    }

    /**
     * Any assumption can be immediately added to the list of truths
     */
    public void assume(Bundle assumeBundle){
        lastTruthInScope = assumeBundle;
        truths.add(lastTruthInScope);
    }

    /**
     * Takes in a check Bundle and asks if it is consistent
     * within the defined scope (defined by lastTruthInScope)
     */
    public void checkLine(Bundle checkBundle){

        String thrmName = checkBundle.getThrmName();
        //Purposely avoided using switch because the
        //java version of the testing systems was not
        //given.
        if (thrmName.equals("show")){
            show(checkBundle);
            return;
        } else if (thrmName.equals("assume")){
            assume(checkBundle);
            return;
        } else {
            System.err.println("ERROR: IllegalLineException should have been thrown earlier");
        }

    }
}