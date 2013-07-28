import java.util.*;

public class LineNumber {
	private String nondecietfulpreviousnumber = "1";
	public LineNumber(String num){
		nondecietfulpreviousnumber = num;
	}
	private String previousNumber = "1";
    private int nestCount = 0;
    public String addLine(boolean extendBlock, boolean notmatch) {
    	nondecietfulpreviousnumber = previousNumber;
    	if (previousNumber == "0") {
    		previousNumber = "1";
    		nondecietfulpreviousnumber = previousNumber;
    		return previousNumber;
    	}
    	if (previousNumber == "1") {
    		previousNumber = "2";
    		nondecietfulpreviousnumber = previousNumber;
    		return previousNumber;
    	} 
    	if (extendBlock){ // if this is true, we append a new subproof
    		nestCount++;
    		previousNumber  += "."+ nestCount;
    		nondecietfulpreviousnumber = previousNumber;
    		return previousNumber;
    	} else { // if extendBlock is not true, we parse the lineNumber - "x.x.n" into "x.x." and "n"
    			 // then, if notmatch is false, we change "x.x." into "x.x" and ultimately get "x.(x+1)"
    			 // if notmatch is true we get x.x.(n+1)
    		nestCount = 0;
    		String end = previousNumber.substring(previousNumber.length()-1,previousNumber.length());
    		int endnum = Integer.parseInt(end);
    		endnum++;
    		String begin = previousNumber.substring(0,previousNumber.length()-1);
    		if (!notmatch) { 
    			previousNumber = begin.substring(0, begin.length()-1);
    			end = previousNumber.substring(previousNumber.length()-1,previousNumber.length());
        		endnum = Integer.parseInt(end);
        		endnum++;
        		begin = previousNumber.substring(0,previousNumber.length()-1);
        		previousNumber = begin + endnum;
        		nondecietfulpreviousnumber = previousNumber;
        		return previousNumber;
        		
    		}
    		previousNumber = begin + endnum;
    		nondecietfulpreviousnumber = previousNumber;
    		return previousNumber;
    	}
    	
//    	return null;  //To change body of created methods use File | Settings | File Templates.
    }
    
    
    public String toString(){
    	return nondecietfulpreviousnumber;
    }

}
