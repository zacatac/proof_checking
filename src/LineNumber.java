import java.util.*;

public class LineNumber {
  private String nondecietfulpreviousnumber = "1";
	public LineNumber(String num){
		previousNumber = num;
	}
	private String nextnum;
	private String previousNumber = "0";
    private int nestCount = 0;
    public String addLine(boolean extendBlock, boolean notmatch) {
    	nondecietfulpreviousnumber = previousNumber;
    	if (previousNumber == "0") {
    		previousNumber = "1";
    		return(previousNumber);
    	}
    	if (previousNumber == "1") {
    		previousNumber = "2";
    		return(previousNumber);
    	} 
    	if (extendBlock){
    		nestCount++;
    		previousNumber  += "."+ nestCount;
    		return previousNumber;
    	} else {
    		nestCount = 0;
    		String end = previousNumber.substring(previousNumber.length()-1,previousNumber.length());
    		int endnum = Integer.parseInt(end);
    		endnum++;
    		String begin = previousNumber.substring(0,previousNumber.length()-1);
    		if (!notmatch) {
    			previousNumber = begin.substring(0, previousNumber.length()-1);
    			end = previousNumber.substring(previousNumber.length()-1,previousNumber.length());
        		endnum = Integer.parseInt(end);
        		endnum++;
        		begin = previousNumber.substring(0,previousNumber.length()-1);
        		return previousNumber;
    		}
    		previousNumber = begin + endnum;
    		return previousNumber;
    	}
    	
//    	return null;  //To change body of created methods use File | Settings | File Templates.
    }
    
    public LineNumber next() {
    	String nextnum = previousNumber;
    	if (nextnum.equals("0")) {
    		return new LineNumber("1");
    	}
    	return new LineNumber(nextnum);
    }
    public String toString(){
    	System.out.println(nondecietfulpreviousnumber);
    	return nondecietfulpreviousnumber;
    }

}
