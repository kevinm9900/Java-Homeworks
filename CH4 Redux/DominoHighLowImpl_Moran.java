package dominoes;

import java.util.Iterator;
import java.util.Set;

public class DominoHighLowImpl_Moran implements Domino{
	 private int highPipCount;
     private int lowPipCount;
     
     public DominoHighLowImpl_Moran (int highPipCount, int lowPipCount) {
            assert (lowPipCount >= Domino.MINIMUM_PIP_COUNT && lowPipCount <= Domino.MAXIMUM_PIP_COUNT) : "ERROR: LOW PIP COUNT MUST BE BETWEEN 0 AND 6";
    		
    		assert(highPipCount >= Domino.MINIMUM_PIP_COUNT && highPipCount <= Domino.MAXIMUM_PIP_COUNT) : "ERROR: HIGH PIP COUNT MUST BE BETWEEN 0 AND 6";
    	
    		assert (lowPipCount <= highPipCount) : "ERROR: PLEASE SEND IN FORMAT: (HIGH, LOW)";
    		
    		this.highPipCount = highPipCount;
    		this.lowPipCount = lowPipCount;
     }
     public static final char HIGH_LOW_STRING_SEPARATOR = ':';
     
     private static int find(char ch, String str)
 	{
 		int pos = -1;
 		for(int i = 0; i < str.length(); i++)
 		{
 			if(str.charAt(i) == ch)
 			{
 				pos = i;
 				break;
 			}
 		}
 		return pos;
 	}
     
     public static boolean isHighLowString(String str) {
    	 str = str.trim();
 		boolean retBool = false; 
 		int pos = find(HIGH_LOW_STRING_SEPARATOR, str);
 		if(pos != -1) 
 		{									
 			for(int i = 0; i < str.length(); i++)
 			{
 				char ch = str.charAt(i);		
 				if(i != pos)		
 				{
 					if(Character.isDigit(ch)) 
 					{
 						retBool = true; 
 					}
 					else
 					{
 						retBool = false; 
 						break;
 					}
 				}
 			} 
 		} 
 		
 		return retBool; 
     }
     
     public DominoHighLowImpl_Moran(String highLowString) {
    	 assert (isHighLowString(highLowString)) : "PLEASE SEND A VALID STRING!!";
 		
 		String highChar = Character.toString(highLowString.charAt(0));
 		String lowChar = Character.toString(highLowString.charAt(2));
 		
 		int highInt = Integer.parseInt(highChar);
 		int lowInt = Integer.parseInt(lowChar);
 		
 		if(highInt >= lowInt)
 		{
 			highPipCount = highInt;
 			lowPipCount = lowInt;
 		}
 		else					
 		{
 			highPipCount = lowInt;
 			lowPipCount = highInt;
 		}
     }
     
     public static final int INDEX_OF_SUM = 0;
     public static final int INDEX_OF_DIFFERENCE = 1; 
//part of pre: sumDifference.length == 2
//part of pre: sumDifference[INDEX_OF_SUM] >=
//sumDifference[INDEX_OF_DIFFERENCE] 
     public DominoHighLowImpl_Moran(int[] sumDifference) {
    	 assert (sumDifference.length == 2 && sumDifference[INDEX_OF_SUM] >= sumDifference[INDEX_OF_DIFFERENCE]): "PLEASE SEND A VALID ARRAY!!";
 		
 		
 		int sum = sumDifference[INDEX_OF_SUM];
 		int diff = sumDifference[INDEX_OF_DIFFERENCE];
 		
 		diff = Math.abs(diff);
 		
 		
 		lowPipCount = (diff - sum) / (-2);
 		highPipCount = (diff + sum) / (2);
     }
//part of pre: 1<= highLowSet.size() <= 2
//part of pre: ! highLowSet.contains(null)
     public DominoHighLowImpl_Moran(Set<Integer> highLowSet) {
    	 assert highLowSet.isEmpty() != true : "Set is empty";
 		assert highLowSet != null : "Set is null!";
 		assert highLowSet .size() >0 : "Set is incorrecct size";
 		assert !highLowSet.contains(null) : "Set contains null";
 		assert highLowSet.size() <= 2 : "Set greater than size 2";
 		assert highLowSet.size() >=1 : "Set less than size 1";
 	
 		
 		int highLowSetElement1 = -1;
 		int highLowSetElement2 = -1;
 		
 		Iterator<Integer> highLowSetIterator = highLowSet.iterator();
 		
 		if (highLowSet.size() ==1){
 			while( highLowSetIterator.hasNext()){
 				highLowSetElement1 = highLowSetIterator.next();
 				highLowSetIterator.remove();
 			}
 			
 			assert highLowSetElement1 >= MINIMUM_PIP_COUNT && highLowSetElement1 <= MAXIMUM_PIP_COUNT : "Set element not valid!";
 			
 			highPipCount = highLowSetElement1;
 			lowPipCount = highLowSetElement1;
 		}
 		
 		if (highLowSet.size() ==2){
 			while(highLowSetIterator.hasNext()){
 				highLowSetElement1 = highLowSetIterator.next();
 				highLowSetIterator.remove();
 				highLowSetElement2 = highLowSetIterator.next();
 				highLowSetIterator.remove();
 			}
 			assert highLowSetElement1 >= MINIMUM_PIP_COUNT && highLowSetElement1 <= MAXIMUM_PIP_COUNT : "Set element" + highLowSetElement1+ " not valid!";
 			assert highLowSetElement2 >= MINIMUM_PIP_COUNT && highLowSetElement2 <= MAXIMUM_PIP_COUNT : "Set element" + highLowSetElement2+ " not valid!";
 			
 			if (highLowSetElement1 > highLowSetElement2){
 				highPipCount = highLowSetElement1;
 			    lowPipCount = highLowSetElement2;
 			}
 			
 			if (highLowSetElement1 < highLowSetElement2){
 				highPipCount = highLowSetElement2;
 				lowPipCount = highLowSetElement1;
 			}
 		}
 		
 	}

 	
 	public int getHighPipCount() {
 		
 		return highPipCount;
 	}


 	public int getLowPipCount() {
 	
 		return lowPipCount;
 	}
 	
 	public String toString(){
 	
 		String temp= "";
 		
 		temp = "High Pip Count:" + highPipCount + "Low Pip Count:" + lowPipCount;
 		
 		return temp;
 	}
 	
 	public DominoHighLowImpl_Moran(int highPipCountDivisionBy2Quotient, int highPipCountDivisionBy3Remainder, int lowPipCountDivisionBy2Quotient, int lowPipCountDivisionBy3Remainder)
	{
		
		assert (MINIMUM_PIP_COUNT/2) <= highPipCountDivisionBy2Quotient:"Not a domino";
		assert highPipCountDivisionBy2Quotient <= (MAXIMUM_PIP_COUNT/2):"Not a domino";
		assert  highPipCountDivisionBy3Remainder >= 0:"Not a domino";
		assert  highPipCountDivisionBy3Remainder < 3:"Not a domino";
		assert (MINIMUM_PIP_COUNT/2) <= lowPipCountDivisionBy2Quotient:"Not a domino";
		assert lowPipCountDivisionBy2Quotient <= (MAXIMUM_PIP_COUNT/2):"Not a domino";
		assert lowPipCountDivisionBy3Remainder >= 0:"Not a domino";
		assert lowPipCountDivisionBy3Remainder < 3:"Not a domino";
		
		if(highPipCountDivisionBy2Quotient == 3)
		{
			assert highPipCountDivisionBy3Remainder == 0;
		}
		if(lowPipCountDivisionBy2Quotient == 3)
		{
			assert lowPipCountDivisionBy3Remainder == 0;
		}
		
		if(highPipCountDivisionBy2Quotient == 0)
		{
			assert (highPipCountDivisionBy3Remainder == 0 || highPipCountDivisionBy3Remainder == 1);
		}
		if(lowPipCountDivisionBy2Quotient == 0)
		{
			assert (lowPipCountDivisionBy3Remainder == 0 || lowPipCountDivisionBy3Remainder == 1);
		}
		
		if(highPipCountDivisionBy2Quotient == 1)
		{
			assert (highPipCountDivisionBy3Remainder == 2 || highPipCountDivisionBy3Remainder == 0);
		}
		if(lowPipCountDivisionBy2Quotient == 1)
		{
			assert (lowPipCountDivisionBy3Remainder == 2 || lowPipCountDivisionBy3Remainder == 0);
		}
		if(highPipCountDivisionBy2Quotient == 2)
		{
			assert (highPipCountDivisionBy3Remainder == 1 || highPipCountDivisionBy3Remainder == 2);
		}
		if(lowPipCountDivisionBy2Quotient == 2)
		{
			assert (lowPipCountDivisionBy3Remainder == 1 || lowPipCountDivisionBy3Remainder == 2);
		}
		
		
		int integerDivisionLowPossibility = highPipCountDivisionBy2Quotient * 2;
		int integerDivisionHighPossibility = (highPipCountDivisionBy2Quotient * 2) + 1;
		int highPipCountHolder = 0;
		if(integerDivisionLowPossibility % 3 == highPipCountDivisionBy3Remainder)
		{
			highPipCountHolder = integerDivisionLowPossibility;
		}
		if(integerDivisionHighPossibility % 3 == highPipCountDivisionBy3Remainder)
		{
			highPipCountHolder = integerDivisionHighPossibility;
		}
		
		int integerDivisionLowPossibility2 = lowPipCountDivisionBy2Quotient * 2;
		int integerDivisionHighPossibility2 = (lowPipCountDivisionBy2Quotient * 2) + 1;
		int lowPipCountHolder = 0;
		if(integerDivisionLowPossibility2 % 3 == lowPipCountDivisionBy3Remainder)
		{
			lowPipCountHolder = integerDivisionLowPossibility2;
		}
		if(integerDivisionHighPossibility2 % 3 == lowPipCountDivisionBy3Remainder)
		{
			lowPipCountHolder = integerDivisionHighPossibility2;
		}
		
		highPipCount = highPipCountHolder;
		lowPipCount = lowPipCountHolder;
		
		
	}
 	
 }