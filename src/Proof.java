import java.util.*;

public class Proof {
	private String var = "1";
	private LineNumber start = new LineNumber(var);
    private LinkedList<Bundle> truths;
    private Bundle lastShow; //reassign tail in globalTruth to this after completing shows
    private ArrayList<Bundle> allStatements;
    private final int numberOfUserTheorems;
    private String pre = "0";

    public Proof (TheoremSet theorems) {
        if (theorems == null || theorems.getMyTheorems() == null){
            truths = new LinkedList<Bundle>();
            
            numberOfUserTheorems = 0;
        } else {
            truths = theorems.getMyTheorems();
            numberOfUserTheorems = truths.size();
        }
        lastShow = null;
        allStatements = new ArrayList<Bundle>();
    }

    public Proof (){
        this(null);
    }
    public LineNumber nextLineNumber ( ) {
    	if (!isComplete()){
            this.pre = start.toString();
    		return start;
    	} else {
    		System.out.println("Proof is Complete");
    		return null;
    	}
    }
    
    public LinkedList<Bundle> getTruths(){
        return truths;
    }
    public Bundle getLastShow(){
        return lastShow;
    }
    
    //Parses the user input, and calls more methods for error checking
    public void extendProof (String x) throws IllegalLineException, IllegalInferenceException{
    	if (x == null){
            System.exit(1);
            throw new IllegalLineException("You must input something");
        }
    	String[] inputs = x.split("\\s+");
        if (inputs.length > 4){
            throw new IllegalLineException("Input has too many fields");
        }
        if (inputs.length == 0) {
        	throw new IllegalLineException("Must have input");
        }

        if (inputs.length == 1){
           if (inputs[0].equals("print")) {
               print();
               return;
           } else {
               throw new IllegalLineException("The only legal one field input is \"print\"");
           }
        }

        String reason = inputs[0];
        Bundle checkBundle = null;
        Bundle testBundle = findUserTheorem(reason);
        
        if (inputs.length == 2){
            String expression = inputs[1];
            if (reason.equals("show") || reason.equals("assume")){
            	checkBundle = new Bundle(null,makeTree(expression),reason);
                checkLine(checkBundle);
//                var = start.addLine(true, false);
            }
            else if (testBundle != null){
            	if (makeTree(expression).equals(lastShow.getMyTree())) {
            		checkBundle = new Bundle(null,makeTree(expression),reason);
            		checkLine(checkBundle);
//            		var = start.addLine(false, true);
            	} else {
            		checkBundle = new Bundle(null,makeTree(expression),reason);
            		checkLine(checkBundle);
            	}
//                var = start.addLine(false, false);
            } else {
                if (reason.equals("co")||
                        reason.equals("ic")||
                        reason.equals("mt")||
                        reason.equals("mp")||
                        reason.equals("repeat")){
                    throw new IllegalLineException(reason + " takes at least one line reference");
                } else {
                throw new IllegalLineException(reason + " is not a valid reason");
                }
            }
            
        } else if(inputs.length == 3){
            String expression = inputs[2];
            String refLine1 = inputs[1];
            if (reason.equals("repeat")){
            	if (makeTree(expression).equals(lastShow.getMyTree())) {
            		checkBundle = new Bundle(null,makeTree(expression),reason,refLine1);
                    checkLine(checkBundle);
//                    var = start.addLine(false, true);
            	} else {
            		checkBundle = new Bundle(null,makeTree(expression),reason,refLine1);
            		checkLine(checkBundle);
//            		var = start.addLine(false, false);
            	}
            } else if (reason.equals("ic")){
            	if (makeTree(expression).equals(lastShow.getMyTree())) {
            		checkBundle = new Bundle(null,makeTree(expression),reason,refLine1);
                    checkLine(checkBundle);
//            		var = start.addLine(false, false);
            	} else {
                	checkBundle = new Bundle(null,makeTree(expression),reason,refLine1);
                	checkLine(checkBundle);
//                	var = start.addLine(false, true);
            	}
            
            
            } else {
            	Bundle userBundle = findUserTheorem(reason);
            	if (userBundle != null ||
                    reason.equals("mt")||
                    reason.equals("mp") ||
                    reason.equals("co")) {
                    throw new IllegalLineException(reason + " does not accept a single line reference");
            	}else {
            		throw new IllegalLineException(reason + " is not a valid reason");
            	}
            }

        }
        
        
        if(inputs.length == 4){
            System.out.println(inputs[1]);
            System.out.println(inputs[2]);
            System.out.println(inputs[3]);
        	String refLine1 = inputs[1];
        	String refLine2 = inputs[2];
        	String expression = inputs[3];
        	if (reason.equals("mp") || reason.equals("mt") || reason.equals("co")){
        		if (makeTree(expression).equals(lastShow.getMyTree())) {
            		checkBundle = new Bundle(null,makeTree(expression),reason,refLine1,refLine2);
            		checkLine(checkBundle);
//            		var = start.addLine(false, false);
            	} else {
            		checkBundle = new Bundle(null,makeTree(expression),reason,refLine1,refLine2);
            		checkLine(checkBundle);
//            		var = start.addLine(false, true);
            	}
        		
        	} else {
            	Bundle userBundle = findUserTheorem(reason);
            	if (userBundle != null) {
                    throw new IllegalLineException(reason + " does not accept a single line reference");
            	}else {
            		throw new IllegalLineException(reason + " is not a valid reason");
            	}
            }
        }
	}

    /**
     * Takes in the String form of the expression and outputs the Binary Tree form
     * If the expression is erroneous in any way, this is where exceptions will be
     * thrown.
     * @param expression
     * @return BinaryTree
     * @throws IllegalLineException
     */
    private BinaryTree makeTree(String expression) throws  IllegalLineException{
        Expression e = new Expression(expression);
        return Expression.exprTree(e);
    }

    public String toString ( ) {
        return "";
    }

    public boolean isComplete( ) {
    	if (truths.size() - numberOfUserTheorems == 1){
            if (truths.get(numberOfUserTheorems).getThrmName().equals("true")){
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }

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
        this.lastShow = showBundle;
        start.addLine(1);
        Bundle lineBundle = new Bundle(pre,showBundle.getMyTree(),showBundle.getThrmName());
        allStatements.add(lineBundle);
        truths.add(lineBundle); // updates the latest scope.
    }

    /**
     * Any assumption can be immediately added to the list of truths
     */
    public void assume(Bundle assumeBundle) throws IllegalInferenceException,IllegalLineException{
        BinaryTree assumeTree = assumeBundle.getMyTree();
        Bundle lastShow = null;
        BinaryTree lastShowTree = null;

        try{
            lastShow  = truths.get(truths.size()-1);
            lastShowTree = lastShow.getMyTree();
        } catch (NullPointerException n){
            System.err.println("ERROR: List has failed to call the last object");
        } catch (ClassCastException c){
            System.err.println("ERROR: The truth list is internally inconsistent.");
        }
        if (!lastShow.getMyTree().getMyRoot().getMyItem().equals("=>")) {
        	BinaryTree notTree;
        	notTree = new BinaryTree(new BinaryTree.TreeNode("~",lastShowTree.getMyRoot(),null));
        	if (assumeTree.equals(notTree)) {
                start.addLine(2);
                Bundle lineBundle = new Bundle(pre,assumeBundle.getMyTree(),assumeBundle.getThrmName());
                allStatements.add(lineBundle);
                truths.add(lineBundle);
        	}
        	return;
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
                start.addLine(2);
                Bundle lineBundle = new Bundle(pre,assumeBundle.getMyTree(),assumeBundle.getThrmName());
                allStatements.add(lineBundle);
                truths.add(lineBundle);
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
                start.addLine(2);
                Bundle lineBundle = new Bundle(pre,repeatBundle.getMyTree(),repeatBundle.getThrmName(),repeatBundle.getRefLine1());
                allStatements.add(lineBundle);
                truths.add(lineBundle);
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
        if (expressionToProve.getMyRoot().getMyItem().equals("=>")){      //The expression must have an implication
            if (truths.contains(rightICBundle)){            // Checks if this expression is in the list of truths and shows
        		Bundle referencedBundle = truths.get(truths.indexOf(rightICBundle));
                if (!referencedBundle.getThrmName().equals("show")){    //Checks if you are trying to use an unporven statement
                    if (lineReference.equals(referencedBundle.getLineNumber())){   //Checks if you entered the wrong line
                        if (ICBundle.equals(lastShow)){
                            start.addLine(3);
                            Bundle lineBundle = new Bundle(pre,ICBundle.getMyTree(),ICBundle.getThrmName(),ICBundle.getRefLine1());
                            allStatements.add(lineBundle);
                            changeTruths(); //If you have proven the most recent show.
                        } else {
                            start.addLine(2);
                            Bundle lineBundle = new Bundle(pre,ICBundle.getMyTree(),ICBundle.getThrmName(),ICBundle.getRefLine1());
                            allStatements.add(lineBundle);
                            truths.add(lineBundle); // If you have proven something that isn't the most recent show
                        }
                    } else {
                        throw new IllegalInferenceException("Line number does not reference the \n right-hand side  of the implication");
                    }
                } else {
                    throw new IllegalInferenceException("ic: referenced line can't be an unproven show");
                }
            } else {
                throw new IllegalInferenceException("Right-hand side must be proven already");
            }
        } else {
            throw new IllegalInferenceException("IC takes an implication");
        }
    }


    public void MT(Bundle mtBundle) throws IllegalInferenceException, IllegalLineException {
    	Bundle firstReference = findReferences(mtBundle.getRefLine1());
        Bundle secondReference = findReferences(mtBundle.getRefLine2());

        //Testing that the references are already proved statements
        if (firstReference == null || secondReference == null){
            throw new IllegalInferenceException("mt: The lines referenced must already be proven");
        }


        //Testing that one of the tree roots for the reference arguments
        // is an implication;

        Bundle[] bothBundles = testRootDesired(firstReference, secondReference, "mt", "=>");
        Bundle implicationBundle = bothBundles[0];
        Bundle notRightArgumentBundle= bothBundles[1];
        if (implicationBundle == null || notRightArgumentBundle == null){
            throw new IllegalLineException("Inconsistency in checking for implication");
        }

        //The meat of the MT argument.
//        implicationBundle.getMyTree().print();

        BinaryTree implicationBranchLeft = new BinaryTree(implicationBundle.getMyTree().getMyLeft());
        BinaryTree implicationBranchRight = new BinaryTree(implicationBundle.getMyTree().getMyRight());
        BinaryTree notImplicationBranchRight = new BinaryTree(new BinaryTree.TreeNode("~",implicationBranchRight.getMyRoot(),null));
        Bundle notImplicationBundleLeft = new Bundle(implicationBundle.getLineNumber(),
                new BinaryTree(new BinaryTree.TreeNode("~",implicationBranchLeft.getMyRoot(),null)),
                implicationBundle.getThrmName());


        if(notImplicationBranchRight.equals(notRightArgumentBundle.getMyTree())){
            if(notImplicationBundleLeft.equals(mtBundle)){
                if (mtBundle.equals(lastShow)){
                    start.addLine(3);
                    Bundle lineBundle = new Bundle(pre,mtBundle.getMyTree(),mtBundle.getThrmName(),mtBundle.getRefLine1(),mtBundle.getRefLine2());
                    allStatements.add(lineBundle);
                    changeTruths();
                } else {
                    start.addLine(2);
                    Bundle lineBundle = new Bundle(pre,mtBundle.getMyTree(),mtBundle.getThrmName(),mtBundle.getRefLine1(),mtBundle.getRefLine2());
                    allStatements.add(lineBundle);
                    truths.add(lineBundle);
                }
            } else {
                throw new IllegalInferenceException("mt: the expression must be the negation of the \n" +
                        "left argument of the referenced implication of the referenced implication");
            }
        } else {
            throw new IllegalInferenceException("mt: the second proposition referenced is not the negation of the " +
                    "\n left argument of the implication referenced");
        }
    }

    public void CO(Bundle coBundle) throws IllegalInferenceException, IllegalLineException{
        Bundle firstReference = findReferences(coBundle.getRefLine1());
        Bundle secondReference = findReferences(coBundle.getRefLine2());

        //Testing that the references are already proved statements
        if (firstReference == null || secondReference == null){
            throw new IllegalInferenceException("co: The lines referenced must already be proven or assumed");
        }

        // Discovering which proposition referenced has a not symbol
        Bundle[] bothBundles = testRootDesired(firstReference,secondReference,"co","~");
        Bundle notPropositionBundle = bothBundles[0];
        Bundle propositionBundle = bothBundles[1];
        if (notPropositionBundle == null || propositionBundle == null){
            throw new IllegalLineException("Inconsistency in checking for not operator");
        }

        //The meat of the CO argument.
        
        
        BinaryTree newNotTree = new BinaryTree(new BinaryTree.TreeNode("~",propositionBundle.getMyTree().getMyRoot(),null));
        Bundle newNotPropositionBundle = new Bundle (
                propositionBundle.getLineNumber(),
                newNotTree,
                propositionBundle.getThrmName()
                );
        if (newNotPropositionBundle.equals(notPropositionBundle)){
            if(findEquivalentTree(newNotPropositionBundle).equals(findEquivalentTree(coBundle)) || 
            		findEquivalentTree(notPropositionBundle).equals(findEquivalentTree(coBundle))){
                if (coBundle.equals(lastShow)){
                    start.addLine(3);
                    Bundle lineBundle = new Bundle(pre,coBundle.getMyTree(),coBundle.getThrmName(),coBundle.getRefLine1(),coBundle.getRefLine2());
                    allStatements.add(lineBundle);
                    changeTruths();
                }else {
                    start.addLine(2);
                    Bundle lineBundle = new Bundle(pre,coBundle.getMyTree(),coBundle.getThrmName(),coBundle.getRefLine1(),coBundle.getRefLine2());
                    allStatements.add(lineBundle);
                    truths.add(coBundle);
                    }
            } else {
                throw new IllegalInferenceException("One of the lines referenced must be equivalent to the expression");
            }
        }   else {
            throw new IllegalInferenceException("The first line referenced by CO must be the negation of \n" +
                    "the second line number referenced");
        }
    }

    //Finds equivalent binaryTree from staggered not statements
    public BinaryTree findEquivalentTree(Bundle b) throws IllegalLineException{
    	int notCount = 0;
        BinaryTree eqTree = b.getMyTree();
        BinaryTree myEqTree;
    	while (eqTree.getMyRoot().equals("~")) {
        	notCount++;
        	eqTree = new BinaryTree(b.getMyTree().getMyLeft());
        }
        if ((notCount) % 2 == 0) {
        	myEqTree = Expression.exprTree(new Expression(eqTree.getMyRoot().toString()));
        	return myEqTree;
        } else {
        	myEqTree = Expression.exprTree(new Expression("~"+eqTree.getMyRoot().toString()));
        	return myEqTree;
        }
    }
    

    public Bundle[] testRootDesired(Bundle firstRef, Bundle secondRef, String inferenceType, String rootDesired) throws IllegalInferenceException {
        Bundle[] rtn = new Bundle[2];
        if (!firstRef.getMyTree().getMyRoot().getMyItem().equals(rootDesired)
                && !secondRef.getMyTree().getMyRoot().getMyItem().equals(rootDesired)){
            throw new IllegalInferenceException(inferenceType + " must reference at least one tree rooted in " + rootDesired);
        } else if ( firstRef.getMyTree().getMyRoot().getMyItem().equals(rootDesired)){
            rtn[0] = firstRef;
            rtn[1] = secondRef;
        } else {
            rtn[1] = firstRef;
            rtn[0] = secondRef;
        }
        return rtn;
    }

    public Bundle findReferences(String refLine){
        Bundle rtnBundle = null;
        for (Bundle truth:truths) {
            if (truth.getLineNumber() == null){
            } else {
                if (truth.getLineNumber().equals(refLine) && !truth.getThrmName().equals("show")){
                    return truth;
                }
            }
        }
        return null;
    }

    public Bundle findUserTheorem(String thrmName){
        Bundle rtnBundle = null;
        for (Bundle truth: truths) {
                    if (truth.getThrmName().equals(thrmName) && truth.getLineNumber()==null){
                        rtnBundle = truth;
                    } else {
                        continue;
            }
        }
        return rtnBundle;
    }


    public void MP(Bundle mpBundle) throws IllegalInferenceException,IllegalLineException {
//        System.out.println("");
//        System.out.println("REFLINE 1 " + mpBundle.getRefLine1());
//        System.out.println("");
//        System.out.println("REFLINE 2 " + mpBundle.getRefLine2());
//        System.out.println("");
        Bundle firstReference = findReferences(mpBundle.getRefLine1());
        Bundle secondReference = findReferences(mpBundle.getRefLine2());
        System.out.println();
        System.out.println();
        mpBundle.getMyTree().print();
        System.out.println();
        System.out.println();
        //Testing that the references are already proved statements
        if (firstReference == null || secondReference == null){
            throw new IllegalInferenceException("mp: The lines referenced must already be proven");
        }

        //Testing that one of the tree roots for the reference arguments
        // is an implication;
        Bundle[] bothBundles = testRootDesired(firstReference,secondReference,"mp","=>");
        Bundle implicationBundle = bothBundles[0];
        Bundle leftArgumentBundle = bothBundles[1];

        //The meat of the MP argument.
        BinaryTree implicationBranchLeft = new BinaryTree(implicationBundle.getMyTree().getMyLeft());
        BinaryTree implicationBranchRight = new BinaryTree(implicationBundle.getMyTree().getMyRight());
//        System.out.println("LEFT \n");
//        implicationBranchLeft.print();
//        System.out.println("RIGHT \n");
//        implicationBranchRight.print();
//        System.out.println("LEFT ARG BUNDLE  \n");
//        leftArgumentBundle.getMyTree().print();


        if(implicationBranchLeft.equals(leftArgumentBundle.getMyTree())){
            if(implicationBranchRight.equals(mpBundle.getMyTree())){
                    if (mpBundle.equals(lastShow)){
                	start.addLine(3);
                    Bundle lineBundle = new Bundle(pre,mpBundle.getMyTree(),mpBundle.getThrmName(),mpBundle.getRefLine1(),mpBundle.getRefLine2());
                    allStatements.add(lineBundle);
                    changeTruths();
                } else {
                    start.addLine(2);
                    Bundle lineBundle = new Bundle(pre,mpBundle.getMyTree(),mpBundle.getThrmName(),mpBundle.getRefLine1(),mpBundle.getRefLine2());
                    allStatements.add(lineBundle);
                    truths.add(lineBundle);
                }
            } else {
                throw new IllegalInferenceException("mp: the expression must be the right argument \n" +
                        "of the referenced implication");
            }
        } else {
            throw new IllegalInferenceException("mp: the second reference is not the left argument " +
                    "\n of the implication");
        }

    }

    public void changeTruths(){
        Iterator<Bundle> iter = truths.iterator();
        Bundle lastShow = null;
        while (iter.hasNext()){
            Bundle nxtBundle = iter.next();
            if (nxtBundle.  getThrmName().equals("show")){
                lastShow = nxtBundle;
            }
        }
        if (lastShow == null){
            System.err.println("ERROR: truths is internally inconsistent");
        } else {
            int lastIndex = truths.lastIndexOf(lastShow);
            lastShow = new Bundle(lastShow.getLineNumber(),lastShow.getMyTree(),"true");
            int truthsSize = truths.size();
            truths.add(lastIndex, lastShow);
            for (int i = lastIndex+1; i < truthsSize+1; i++){
                truths.removeLast();

            }
        }
        iter = truths.iterator();
        lastShow = null;
        while (iter.hasNext()){
            Bundle nxtBundle = iter.next();
            if (nxtBundle.getThrmName().equals("show")){
                lastShow = nxtBundle;
            }
        }

        this.lastShow = lastShow;

    }

    /**
     * Prints out the proof so far, line by line.
     * It uses the field allStatements which is not
     * decreased in size as show statements are proven,
     * unlike the truths field.
     */
    public void print() {
        Iterator<Bundle> iter = allStatements.iterator();
        while (iter.hasNext()){
            Bundle bundle = iter.next();
            String outString = "";
            outString += bundle.getLineNumber() + " ";
            outString += bundle.getThrmName()  + " ";
            if (bundle.getRefLine1() != null) {
                outString += bundle.getRefLine1() + " ";
            }
            if (bundle.getRefLine2() != null){
                outString += bundle.getRefLine2()+ " ";
            }
            outString += bundle.getMyTree().inOrderString() + " ";
            outString += "\n";
            System.out.print(outString);
        }
    }
    /**
     * Takes in a check Bundle and asks if it is consistent
     * within the defined scope (defined by lastTruthInScope)
     *
     * Set all methods called in checkline to private when you
     * get a chance
     */
    public void checkLine(Bundle checkBundle) throws IllegalLineException, IllegalInferenceException{

        String thrmName = checkBundle.getThrmName();
        //Purposely avoided using switch because the
        //java version of the testing systems was not
        //given.
        if (thrmName.equals("show")){
            show(checkBundle);
        } else if (thrmName.equals("assume")){
            assume(checkBundle);
        } else if (thrmName.equals("ic")){
            IC(checkBundle);
        } else if (thrmName.equals("repeat")) {
            repeat(checkBundle);
        } else if (thrmName.equals("co")){
            CO(checkBundle);
        } else if (thrmName.equals("mt")){
            MT(checkBundle);
        } else if (thrmName.equals("mp")){
            MP(checkBundle);
        } else {
            Bundle thrmBundle = findUserTheorem(thrmName);
            if (thrmBundle == null){
                throw new IllegalLineException("Theorem name is invalid");
            } else {
                userTheorems(thrmBundle,checkBundle);
            }
        }
        isComplete();

    }

    public void userTheorems(Bundle thrmBundle, Bundle checkBundle) throws
                                                                        IllegalInferenceException,
                                                                        IllegalLineException{
        if (thrmBundle == null || checkBundle == null){
            throw new IllegalLineException("Error: Inconsistency in processing user input line");
        }

//        System.out.println("THRM BUNDLE ");
//        thrmBundle.getMyTree().print();
//        System.out.println();
//        checkBundle.getMyTree().print();
        if (checkBundle.getMyTree().canbematched(thrmBundle.getMyTree())) {//checks trees within each Bundle for equivalence
//            lastShow.getMyTree().print();
//            checkBundle.getMyTree().print();
            if (checkBundle.getMyTree().equals(lastShow.getMyTree())){
                start.addLine(3);
                Bundle lineBundle = new Bundle(pre,checkBundle.getMyTree(),checkBundle.getThrmName());
                allStatements.add(lineBundle);
                changeTruths();
            } else {
                System.out.println("IT GETS HERE # TIMES PRINTED");
                start.addLine(2);
                Bundle lineBundle = new Bundle(pre,checkBundle.getMyTree(),checkBundle.getThrmName());
                allStatements.add(lineBundle);
                truths.add(lineBundle);
            }
        } else {
            throw new IllegalInferenceException("Expression given must be equivalent \n" +
                    "to the theorem's expression.");
        }
    }


}
