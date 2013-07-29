/**
 * This class is meant to define a single
 * line of user input. There are fields for the 5
 * possible user entries. The class can has three
 * constructors that take the 3 different sets of inputs:
 * no reference line,
 * one reference line,
 * two reference lines.
 * It has getter methods that are utilized by Proof to access
 * each Bundle's information.
 *
 *
 */
public class Bundle  {
    private String lineNumber;
    private BinaryTree myTree;
    private String thrmName;
    private String refLine1;
    private String refLine2;


    public Bundle(){
        this.lineNumber = null;
        this.myTree = null;
        this.thrmName = null;
        this.refLine2 = null;
        this.refLine1 = null;
    }


    public Bundle(String lineNumber,BinaryTree myTree,String thrmName,String refLine1, String refLine2){
        this.lineNumber = lineNumber;
        this.myTree = myTree;
        this.thrmName = thrmName;
        this.refLine1 = refLine1;
        this.refLine2 = refLine2;
    }
    public Bundle(String lineNumber,BinaryTree myTree,String thrmName,String refLine1){
        this(lineNumber,myTree,thrmName,refLine1,null);
    }
    public Bundle(String lineNumber,BinaryTree myTree,String thrmName){
        this(lineNumber,myTree,thrmName,null,null);


    }

    public String getLineNumber(){
        return lineNumber;
    }
    public BinaryTree getMyTree() {
        return myTree;
    }
    public String getThrmName() {
        return thrmName;
    }
    public String getRefLine1() {
        return refLine1;
    }
    public String getRefLine2() {
        return refLine2;
    }


    /**
     * The equals method for Bundle only checks that the
     * BinaryTrees stored in myTree of each passed in instance of
     * Bundle are equivalent. It is not concerned with the
     * equivalence of reference numbers or theorem names.
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj){
        Bundle testBundle;
        try {
            testBundle = (Bundle)obj;
        } catch (ClassCastException c){
            return false;
        }

        if (testBundle == null){
            return false;
        } else {
            return this.myTree.equals(testBundle.getMyTree());
        }

    }


    @Override
    public String toString(){
        String outStirng = "";

        outStirng += "LINE NUMBER: " + this.lineNumber + "\n";
        outStirng += "THRM NAME  : " + this.thrmName+ "\n";
        outStirng += "REF LINE 1 : " + this.refLine1+ "\n";
        outStirng += "REF LINE 2 : " + this.refLine2+ "\n";
        outStirng += "MY TREE: " + this.myTree;
        return outStirng;
    }
}
