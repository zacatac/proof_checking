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

	public static void main (String [ ] args) {
		BinaryTree t = new BinaryTree (new TreeNode("a", new TreeNode("b", new TreeNode("d"), null), new TreeNode("c")) );
		//t.print();
		//System.out.println(t.height());
		BinaryTree t2 = new BinaryTree (new TreeNode("a", new TreeNode("b", new TreeNode("d"), null), new TreeNode("c")) );
		//System.out.println(t.equals(t2));//yes this is True
		BinaryTree t3 = new BinaryTree (new TreeNode("a", new TreeNode("b", new TreeNode("e"), null), new TreeNode("c")) );
		//System.out.println(t.equals(t3));//yes this is False
		BinaryTree thm = new BinaryTree (new TreeNode("=>", new TreeNode("p"), new TreeNode("&", new TreeNode("p"), new TreeNode("q"))));
		thm.print();
		BinaryTree test = new BinaryTree (new TreeNode("=>", new TreeNode("|", new TreeNode("a"), new TreeNode("b")), new TreeNode("&", new TreeNode("|", new TreeNode("a"), new TreeNode("b")), new TreeNode("c"))));
				
				//new TreeNode(, new TreeNode("&", new TreeNode(new TreeNode("|", new TreeNode("a"), new TreeNode("b")), new TreeNode("c")))));
		test.print();
		BinaryTree test2 = new BinaryTree (new TreeNode("=>", new TreeNode("|", new TreeNode("c"), new TreeNode("b")), new TreeNode("&", new TreeNode("|", new TreeNode("a"), new TreeNode("b")), new TreeNode("c"))));
		System.out.println(test.canbematched(thm));//is True!
		System.out.println(test2.canbematched(thm));//is False!
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
		if (sec.myItem!=first.myItem) {
			return false;
		}
		return (equals(first.myLeft, sec.myLeft) && equals(first.myRight, sec.myRight));
	}

}
