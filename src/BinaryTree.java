public class BinaryTree {
	//my code below
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

	public static void main (String [ ] args) {
		BinaryTree t = new BinaryTree (new TreeNode("a", new TreeNode("b", new TreeNode("d"), null), new TreeNode("c")) );
		t.print();
		System.out.println(t.height());
	}

	private static class TreeNode {
		
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
}

