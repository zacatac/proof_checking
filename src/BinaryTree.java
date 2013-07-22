import java.util.*;

public class BinaryTree {

	protected TreeNode myRoot;
	// Contains nodes already seen in the traversal.
	protected ArrayList alreadySeen;

	public BinaryTree ( ) {
		myRoot = null;
	}

	public BinaryTree (TreeNode t) {
		myRoot = t;
	}

	// Print the values in the tree in preorder: root value first,
	// then values in the left subtree (in preorder), then values
	// in the right subtree (in preorder).
	public void printPreorder ( ) {
		printPreorder (myRoot);
		System.out.println ( );
	}

	protected static void printPreorder (TreeNode t) {
		if (t != null) {
			System.out.print (t.myItem + " ");
			printPreorder (t.myLeft);
			printPreorder (t.myRight);
		}
	}

	// Print the values in the tree in inorder: values in the left
	// subtree first (in inorder), then the root value, then values
	// in the right subtree (in inorder).
	public void printInorder ( ) {
		printInorder (myRoot);
		System.out.println ( );
	}

	protected static void printInorder (TreeNode t) {
		if (t != null) {
			printInorder (t.myLeft);
			System.out.print (t.myItem + " ");
			printInorder (t.myRight);
		}
	}
	
	public void fillSampleTree1 ( ) {
		myRoot =
		    new TreeNode ("a",
			new TreeNode ("b"),
			new TreeNode ("c"));
	}

	public void fillSampleTree2 ( ) {
		myRoot =
		    new TreeNode ("a",
			new TreeNode ("b",
			    new TreeNode ("d",
				new TreeNode ("e"),
				new TreeNode ("f")),
			    null),
			new TreeNode ("c"));
	}

	public void fillSampleTree3 ( ) {
		myRoot = 
			new TreeNode("a", new TreeNode ("b"), 
				new TreeNode ("c", new TreeNode("d"), new TreeNode("e")));
		
	}
	
	public static void main (String [ ] args) {
		BinaryTree t;
		t = new BinaryTree ( );
		
		print (t, "the empty tree");
		
		t.fillSampleTree1 ( );
		print (t, "sample tree 1");
		t.print( );
		
		t.fillSampleTree2 ( );
		print (t, "sample tree 2");
		t.print( );
		
		t.fillSampleTree3 ( );
		print(t, "sample tree 3");
		t.print( );
	}


    public void add(Comparable key) {
        myRoot = add(myRoot, key);
    }

    protected int size(){
        if (this.myRoot != null){
            return sizeHelper(1,this.myRoot);
        }
        return 0;
    }

    private static int sizeHelper(int soFar, TreeNode node){
        if (node == null){
            return soFar;
        } else if (node.myRight != null) {
            int newSoFar = soFar + 1;
            return sizeHelper(newSoFar, node.myRight);
        } else if (node.myLeft != null){
            int newSoFar = soFar + 1;
            return sizeHelper(newSoFar, node.myLeft);
        }
        return soFar;
    }

    protected static TreeNode add(TreeNode t, Comparable key) {
        if (t == null) {
            return new TreeNode(key); // return the new leaf
        } else if (key.compareTo(t.myItem) < 0) {
            t.myLeft = add(t.myLeft, key);
            return t;
        } else {
            t.myRight = add(t.myRight, key);
            return t;
        }
    }


    protected static void print (BinaryTree t, String description) {
		System.out.println (description + " in preorder");
		t.printPreorder ( );
		System.out.println (description + " in inorder");
		t.printInorder ( );
		System.out.println ( );
	}

	protected static class TreeNode {
		
		public Object myItem;
		public TreeNode myLeft;
		public TreeNode myRight;
		
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
	
	// PRINT
	public void print ( ) {
	    if (myRoot != null) {
	        printHelper (myRoot, 0);
	    }
	}
		
	protected static final String indent1 = "    ";
		
	protected static void printHelper (TreeNode root, int indent) {
		
		if (root.myRight != null) {
			printHelper(root.myRight, indent + 1);
		}
		println(root.myItem, indent);
		if (root.myLeft != null) {
			printHelper(root.myLeft, indent+1);
		}
		
	}
			
	protected static void println (Object obj, int indent) {
	    for (int k=0; k<indent; k++) {
	        System.out.print (indent1);
	    }
	    System.out.println (obj);
	}
	
	//initialized alreadySeen at the top
	public boolean check ( ) { 
	    alreadySeen = new ArrayList ( ); 
	    try { 
	        isOK (myRoot); 
	        return true; 
	    } catch (IllegalStateException e) { 
	        return false; 
	    } 
	}

	protected void isOK (TreeNode t) throws IllegalStateException {
		if (alreadySeen.contains(t.myItem)) {
			throw new IllegalStateException("ERROR");
		} else {
			alreadySeen.add(t.myItem);
		}
	}

    // Method for the BinaryTree class
    public Iterator iterator(){
        return new InorderIterator();
    }

    // Inner class inside of the BinaryTree class.
    // Also, it uses java.util.Iterator and java.util.Stack.
    private class InorderIterator implements Iterator {
        private Stack<TreeNode> nodeStack;
        private TreeNode currentNode;

        public InorderIterator ( ) {
            nodeStack = new Stack<TreeNode> ( );
            currentNode = myRoot;
        }

        public boolean hasNext ( ) {
            return !nodeStack.isEmpty();
        }

        public Object next () {
            TreeNode nextNode = null;

            // find leftmost node with no left child
            while (currentNode != null) {
                nodeStack.push(currentNode);
                currentNode = currentNode.myLeft;
            }

            // get leftmost node, then move to its right subtree
            if (!nodeStack.isEmpty ()) {
                nextNode = nodeStack.pop();
                assert nextNode != null; // since nodeStack was not empty
                // before the pop
                currentNode = nextNode.myRight;
            } else {
                throw new NoSuchElementException();
            }
            return nextNode.myItem;
        }

        public void remove () {
            throw new UnsupportedOperationException();
        } // end remove
    } // end InorderIterator
}
