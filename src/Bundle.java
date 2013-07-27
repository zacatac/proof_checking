/**
 * Created with IntelliJ IDEA.
 * User: Zack
 * Date: 7/22/13
 * Time: 12:39 PM
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
