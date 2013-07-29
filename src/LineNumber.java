import java.util.*;

public class LineNumber {
    private ArrayList<String> lineNumbers;
//	private String nondecietfulpreviousnumber = "1";
	public LineNumber(String num){
        lineNumbers = new ArrayList<String>();
        lineNumbers.add("1");
	}
	private String previousNumber = "1";
    private int nestCount = 0;



    public String toString(){
        return lineNumbers.get(lineNumbers.size()-1);
    }

    public String addLine(int i) throws IllegalLineException{
        String  lastLine = lineNumbers.get(lineNumbers.size()-1);
        if (i == 1) {     //indent
            String newLine = lastLine + ".1";
            if (lineNumbers.get(lineNumbers.size()-1).equals("1")){
                newLine = "2";
            }
            lineNumbers.add(newLine);
            return newLine;

        }  else if (i == 2){  //updates
            String newLine;
            int lastNumber = Integer.parseInt("" + lastLine.charAt(lastLine.length()-1));
            lastNumber++;
            if (lastLine.length() < 2){
                newLine = "" +lastNumber;
            } else {
                newLine = lastLine.substring(0,lastLine.length()-2) + lastNumber;
            }
            lineNumbers.add(newLine);
            return newLine;

        } else if (i == 3)  {      //dedent
            String chopped = "" + lastLine.substring(0,lastLine.length()-2);
            int newNum = Integer.parseInt("" + chopped.charAt(chopped.length()-1));
            newNum++;
            String reallyChopped = chopped.substring(0,chopped.length()-1);
            String newLine = reallyChopped + newNum;
            lineNumbers.add(newLine);
            return(newLine);
        }
        return null;
//        }   else {
//            throw new IllegalLineException("Inconsistency in addLine");
//        }
    }

//    public String addLine(boolean extendBlock, boolean notmatch) {
//    	nondecietfulpreviousnumber = previousNumber;
//    	if (previousNumber == "0") {
//    		previousNumber = "1";
//    		nondecietfulpreviousnumber = previousNumber;
//    		return previousNumber;
//    	}
//    	if (previousNumber == "1") {
//    		previousNumber = "2";
//    		nondecietfulpreviousnumber = previousNumber;
//    		return previousNumber;
//    	}
//    	if (extendBlock){ // if this is true, we append a new subproof
//    		nestCount++;
//    		previousNumber  += "."+ nestCount;
//    		nondecietfulpreviousnumber = previousNumber;
//    		return previousNumber;
//    	} else { // if extendBlock is not true, we parse the lineNumber - "x.x.n" into "x.x." and "n"
//    			 // then, if notmatch is false, we change "x.x." into "x.x" and ultimately get "x.(x+1)"
//    			 // if notmatch is true we get x.x.(n+1)
//    		nestCount = 0;
//    		String end = previousNumber.substring(previousNumber.length()-1,previousNumber.length());
//    		int endnum = Integer.parseInt(end);
//    		endnum++;
//
//    		if (!notmatch) {
//    			if (previousNumber == end) {
//    				return null;
//    			}
//    			String begin = previousNumber.substring(0,previousNumber.length()-1);
//    			previousNumber = begin.substring(0, begin.length()-1);
//    			end = previousNumber.substring(previousNumber.length()-1,previousNumber.length());
//        		endnum = Integer.parseInt(end);
//        		endnum++;
//        		begin = previousNumber.substring(0,previousNumber.length()-1);
//        		previousNumber = begin + endnum;
//        		nondecietfulpreviousnumber = previousNumber;
//        		return previousNumber;
//
//    		}
//    		String begin = previousNumber.substring(0,previousNumber.length()-1);
//    		previousNumber = begin + endnum;
//    		nondecietfulpreviousnumber = previousNumber;
//    		return previousNumber;
//    	}
//
////    	return null;  //To change body of created methods use File | Settings | File Templates.
//    }
//

}
