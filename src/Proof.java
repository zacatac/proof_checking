import java.util.*;

public class Proof {
    private LinkedList<Bundle> truths;
    private Bundle lastTruthInScope; //reassign tail in globalTruth to this after completing shows
//    TheoremSet myTheorems;

    public Proof (TheoremSet theorems) {
        truths = new LinkedList<Bundle>();
        lastTruthInScope = null;
//        myTheorems = theorems;
    }

    public Proof (){
        this(null);
    }
    public LineNumber nextLineNumber ( ) {
        return null;
    }

    public LinkedList<Bundle> getTruths(){
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
//					Expression NewExpress = new Expression(splitted[k]);
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
    public void show(Bundle showBundle) throws IllegalLineException{
        if (showBundle.getMyTree() == null) {
            throw new IllegalLineException("ERROR: Bundle cannot contain a null tree");
        }
        lastTruthInScope = showBundle;
        truths.add(lastTruthInScope); // updates the latest scope.
    }

    /**
     * Any assumption can be immediately added to the list of truths
     */
    public void assume(Bundle assumeBundle) throws Exception{
        BinaryTree assumeTree = assumeBundle.getMyTree();
        Bundle lastShow = null;
        BinaryTree lastShowTree = null;

        try{
            lastShow  = (Bundle)truths.get(truths.size()-1);
            lastShowTree = lastShow.getMyTree();
        } catch (NullPointerException n){
            System.err.println("ERROR: List has failed to call the last object");
        } catch (ClassCastException c){
            System.err.println("ERROR: The truth list is internally inconsistent.");
        }
        if (lastShow.getThrmName().equals("show")){
            BinaryTree notTree;
            BinaryTree leftTree;
            BinaryTree.TreeNode leftNode = lastShowTree.getMyLeft();
            BinaryTree.TreeNode leftLeftNode = leftNode.getMyLeft();
            BinaryTree.TreeNode rightLeftNode = leftNode.getMyRight();
            notTree = new BinaryTree(new BinaryTree.TreeNode("~",lastShowTree.getMyRoot(),null));
            leftTree = new BinaryTree(new BinaryTree.TreeNode(leftNode.getMyItem(),leftLeftNode,rightLeftNode));
            if (assumeTree.equals(notTree) || assumeTree.equals(leftTree)){
                lastTruthInScope = assumeBundle;
                truths.add(lastTruthInScope);
            } else {
                throw new IllegalInferenceException("Invalid assumption code 2");
            }
        } else {
            throw new IllegalInferenceException("Invalid assumption code 1");
        }

    }

    public void repeat(Bundle repeatBundle) throws IllegalLineException{
        if (truths.contains(repeatBundle)){
            Bundle oldBundle = truths.get(truths.indexOf(repeatBundle));
            if (oldBundle.getLineNumber().equals( repeatBundle.getRefLine1())){
                truths.remove(repeatBundle);
                truths.add(repeatBundle);
            } else {
                throw new IllegalLineException("Error: The repeat reference line did not match the expression at that line.");
            }
        } else {
            throw new IllegalLineException("Error: Repeat is only legal if the expression already exists");
        }
    }

    public void IC(Bundle ICBundle) throws IllegalLineException,IllegalInferenceException{
        BinaryTree expressionToProve = ICBundle.getMyTree();
        String lineReference = ICBundle.getRefLine1();
        BinaryTree rightTree = new BinaryTree(expressionToProve.getMyRight());
        Bundle rightICBundle = new Bundle(ICBundle.getLineNumber(),rightTree,ICBundle.getThrmName(),ICBundle.getRefLine1());
        if (expressionToProve.getMyRoot().getMyItem().equals("=>")){
            if (truths.contains(rightICBundle)){
                Bundle referencedBundle = truths.get(truths.indexOf(rightICBundle));
                if (lineReference.equals(referencedBundle.getLineNumber())){
                    truths.add(ICBundle);
                } else {
                    throw new IllegalInferenceException("Line number does not reference the \n right-hand side  of the implication");
                }
            } else {
                throw new IllegalInferenceException("Right-hand side must be proven already");
            }
        } else {
            throw new IllegalInferenceException("IC takes an implication");
        }
    }

    public void changeTruths(){
        Iterator<Bundle> iter = truths.iterator();
        Bundle lastShow = null;
        while (iter.hasNext()){
            Bundle nxtBundle = iter.next();
            if (nxtBundle.getThrmName().equals("show")){
                lastShow = nxtBundle;
            }
        }
        if (lastShow == null){
            System.err.println("ERROR: truths is internally inconsistent");
        } else {
            int lastIndex = truths.lastIndexOf(lastShow);
            lastShow = new Bundle(lastShow.getLineNumber(),lastShow.getMyTree(),"true");
            truths.add(lastIndex,lastShow);
            for (int i = lastIndex+1; i < truths.size(); i++){
                truths.remove(i);
            }
        }

    }

    /**
     * Takes in a check Bundle and asks if it is consistent
     * within the defined scope (defined by lastTruthInScope)
     */
    public void checkLine(Bundle checkBundle) throws Exception{

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