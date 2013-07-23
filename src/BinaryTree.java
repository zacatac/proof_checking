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

	public static void main (String [ ] args) {
		BinaryTree t = new BinaryTree (new TreeNode("a", new TreeNode("b", new TreeNode("d"), null), new TreeNode("c")) );
		t.print();
		System.out.println(t.height());
		BinaryTree t2 = new BinaryTree (new TreeNode("a", new TreeNode("b", new TreeNode("d"), null), new TreeNode("c")) );
		System.out.println(t.equals(t2));//yes this is True
		BinaryTree t3 = new BinaryTree (new TreeNode("a", new TreeNode("b", new TreeNode("e"), null), new TreeNode("c")) );
		System.out.println(t.equals(t3));//yes this is False
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
	public boolean equals(BinaryTree t) {
		return equals(myRoot, t.myRoot);
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
		if (sec.myItem!=first.myItem) {
			return false;
		}
		return (equals(first.myLeft, sec.myLeft) && equals(first.myRight, sec.myRight));
	}
}

