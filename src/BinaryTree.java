import java.util.TreeMap;

public class BinaryTree {
	public int height() {
		return height(myRoot);
	}
	public static int height(TreeNode t) {
		if (t==null) {
			return 0;
		}
		return 1+Math.max(height(t.myLeft), height(t.myRight));
	}
	private TreeNode myRoot;
	public BinaryTree ( ) {
		myRoot = null;
	}

	public BinaryTree (TreeNode t) {
		myRoot = t;
	}

    public TreeNode getMyRoot(){
       return myRoot;
    }

    public TreeNode getMyLeft(){
       return myRoot.getMyLeft();
    }

    public TreeNode getMyRight() {
        return myRoot.getMyRight();
    }
    public boolean canbematched(BinaryTree theoremtree) {
        TreeMap<Object, TreeNode> test = matchhelper(theoremtree.myRoot, new TreeMap<Object, TreeNode>());
        if (test.containsKey("false")) {
            return false;
        }
        return true;
    }
    private TreeMap<Object, TreeNode> matchhelper(TreeNode thm, TreeMap t) {
        if (thm==null) {
            return t;
        }
        if (thm.myItem.equals("=>")||thm.myItem.equals("&")||thm.myItem.equals("|")||thm.myItem.equals("~")) {
            if (thm.myItem!=myRoot.myItem) {
                t.put("false", myRoot);
                return t;
            }
        } else {
            if (!t.containsKey(thm.myItem)) {
                  t.put(thm.myItem, myRoot);
            } else {
                if (!equals(myRoot, (TreeNode)t.get(thm.myItem))) {
                    t.put("false", myRoot);
                    return t;
                }
            }
        }
        t = new BinaryTree(getMyLeft()).matchhelper(thm.myLeft, t);
        t = new BinaryTree(getMyRight()).matchhelper(thm.myRight, t);
        return t;
    }

	public static class TreeNode {

		private Object myItem;
		private TreeNode myLeft;
		private TreeNode myRight;

		public Object getMyItem() {
            return myItem;
        }

        public TreeNode getMyLeft() {
            return myLeft;
        }

        public TreeNode getMyRight() {
            return myRight;
        }

        public TreeNode (Object obj) {
			myItem = obj;
			myLeft = myRight = null;
		}



		public TreeNode (Object obj, TreeNode left, TreeNode right) {
			myItem = obj;
			myLeft = left;
			myRight = right;
		}
	}
	public void print ( ) {
	    if (myRoot != null) {
	        printHelper (myRoot, 0);
	    }
	}

	private static final String indent1 = "    ";

	private static void printHelper (TreeNode root, int indent) {
		if (root!=null) {
		    printHelper(root.myRight, indent + 4); //mine
		    println (root.myItem, indent);
		    printHelper(root.myLeft, indent + 4); //mine
		}
	}

	private static void println (Object obj, int indent) {
	    for (int k=0; k<indent; k++) {
	        System.out.print (indent1);
	    }
	    System.out.println (obj);
	}

    @Override
    public boolean equals(Object t) {
        BinaryTree testTree;
        try {
            testTree = (BinaryTree)t;
        } catch (ClassCastException c){
            return false;
        }

        if (testTree == null){
            return false;
        } else {
            return equals(myRoot, testTree.myRoot);
        }
    }

	public static boolean equals(TreeNode first,TreeNode sec) {
		if (first==null && sec==null) {
			return true;
		}
		if (first!=null && sec==null) {
			return false;
		}
		if (first==null && sec!=null) {
			return false;
		}
		if (!sec.myItem.equals(first.myItem)) {
			return false;
		}
		return (equals(first.myLeft, sec.myLeft) && equals(first.myRight, sec.myRight));
	}
    
    
    //BINARY TREE FOR EXPRESSION
    public static BinaryTree exprTree (String s) {
        BinaryTree result = new BinaryTree ( );
        result.myRoot = result.exprTreeHelper (s);
        return result;
    }
    // Return the tree corresponding to the given arithmetic expression.
    // The expression is legal, fully parenthesized, contains no blanks, 
    // and involves only the operations + and *.
    protected TreeNode exprTreeHelper (String expr) {
        if (expr.charAt(0) != '(') {
        	return new TreeNode(expr.substring(0,1), null, null); // you fill this in
        } else {
            // expr is a parenthesized expression.
            // Strip off the beginning and ending parentheses,
            // find the main operator (an occurrence of + or * not nested
            // in parentheses, and construct the two subtrees.
            int nesting = 0;
            
            int opPos = 0; // 
            int opPosX = 0; // keeps track of IMPLIES (=>)
            //int opPosN = 0; // keeps track of NOT (~)
            for (int k=1; k<expr.length()-1; k++) {
                
            	if (expr.charAt (k) != '(') {
                    nesting ++;
                }
                if (expr.charAt (k) != ')') {
                    nesting --;
                }
                if (nesting == 0) {
                	if (expr.charAt(k) =='&' ||expr.charAt(k)=='|') {
            			opPos = k;
            			opPosX = 0;
                	} else if(expr.charAt(k) == '=' && expr.charAt(k+1) == '>') {
                		opPos = k;
                		opPosX = 1;
                	} else if(expr.charAt(k) == '~') {
                		opPos = k;
                		opPosX = 0;

                	}
                }
            }
            
            String opnd1 = expr.substring (1, opPos); // creates operand 1 myLeft
            String opnd2 = expr.substring (opPos + opPosX +1, expr.length()-1); //operand 2 myRight
            String op = expr.substring (opPos, opPos + opPosX +1); // myItem
            return new TreeNode(op, exprTreeHelper(opnd1), exprTreeHelper(opnd2)); 

        }
    }
    public String inOrderString(){
        String outString = "" + myRoot.myItem;
        outString += inOrderStringHelper(getMyLeft(),getMyRight(),outString);
        outString = outString.substring(1);
        return outString;
//        return "inOrderString hasn't been implemented yet.";
    }

    private static String inOrderStringHelper(TreeNode myLeft, TreeNode myRight, String soFar) {
        String update = "";

        if (myLeft == null ){
            if (myRight == null){ // Base case
                return soFar;
            }
//            update += soFar + myRight.getMyItem();
            return inOrderStringHelper(myRight.getMyLeft(),myRight.getMyRight(),update);
        } else {

            if (myRight == null){
                update += myLeft.getMyItem() + soFar;
                return inOrderStringHelper(myLeft.getMyLeft(),myLeft.getMyRight(),update);
            }  else {

            update = myLeft.myItem + soFar + myRight.myItem;
            return inOrderStringHelper(myLeft.getMyLeft(),myLeft.getMyRight(),update) +
                    inOrderStringHelper(myRight.getMyLeft(), myRight.getMyRight(),update);

            }

        }
    }
}
