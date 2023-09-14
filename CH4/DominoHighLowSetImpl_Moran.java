package dominoes;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class DominoHighLowSetImpl_Moran implements Domino{
	private Set<Integer> highLowSet;
	
	public DominoHighLowSetImpl_Moran(int highPipCount, int lowPipCount) {
		assert (lowPipCount >= Domino.MINIMUM_PIP_COUNT && lowPipCount <= Domino.MAXIMUM_PIP_COUNT) : "ERROR: LOW PIP COUNT MUST BE BETWEEN 0 AND 6";

		assert(highPipCount >= Domino.MINIMUM_PIP_COUNT && highPipCount <= Domino.MAXIMUM_PIP_COUNT) : "ERROR: HIGH PIP COUNT MUST BE BETWEEN 0 AND 6";

		assert (lowPipCount <= highPipCount) : "ERROR: PLEASE SEND IN FORMAT: (HIGH, LOW)";

	
		highLowSet = new TreeSet<Integer>();

		highLowSet.add(highPipCount);
		highLowSet.add(lowPipCount);
	    }
	
	public static final char SUM_DIFFERENCE_DELIMITER = ',';
	
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
	
	public static boolean isSumDifferenceString(String str)
	{
		str = str.trim();
		boolean retBool = false; 
		int pos = find(SUM_DIFFERENCE_DELIMITER, str);
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
	
	public DominoHighLowSetImpl_Moran(String sumDifferenceString)
	{
		assert (isSumDifferenceString(sumDifferenceString)): "PLEASE SEND A VALID STRING!!";

		
		highLowSet = new TreeSet<Integer>();
	
		int pos = sumDifferenceString.indexOf(SUM_DIFFERENCE_DELIMITER);
	
		String tempSum = sumDifferenceString.substring(0, pos);
		int sum = Integer.parseInt(tempSum); 
		String tempDiff = sumDifferenceString.substring(pos + 1);
		int difference = Integer.parseInt(tempDiff); 

		int lowPipCount = (difference - sum) / (-2);
		int highPipCount = (difference + sum) / (2);

		highLowSet.add(lowPipCount);
		highLowSet.add(highPipCount);
	}
	
	
	public static boolean isLowPlus8TimesHighInteger(int k)
	{
		int remainder = k % 8; 
		int quotient = k / 8; 
		boolean retBool = false;
	
		if(remainder <= MAXIMUM_PIP_COUNT && quotient <= MAXIMUM_PIP_COUNT && quotient >= MINIMUM_PIP_COUNT)
		{
			retBool = true;
		}
		return retBool;
	}
	
	public DominoHighLowSetImpl_Moran(int lowPlus8TimesHigh) {
		assert (isLowPlus8TimesHighInteger(lowPlus8TimesHigh)): "PLEASE SEND A VALID INTEGER";

		highLowSet = new TreeSet<Integer>();
		
		int lowPipCount = lowPlus8TimesHigh % 8;
		
		int highPipCount = lowPlus8TimesHigh / 8;

		
		highLowSet.add(lowPipCount);
		highLowSet.add(highPipCount);

	
	}

	@Override
	public int getHighPipCount() {
		Iterator<Integer> iterator = highLowSet.iterator();
		int max = iterator.next();
		if(iterator.hasNext())
		{
			int num = iterator.next();
			max = getMax(max, num);
		}

		return max;
	}

	private int getMax(int a, int b) {
		int retVal = 0;
		if(a >= b)
		{
			retVal = a;
		}
		else
		{
			retVal = b;
		}
		return retVal;
	}

	@Override
	public int getLowPipCount() {
		Iterator<Integer> iterator = highLowSet.iterator();
		int min = iterator.next();
		if(iterator.hasNext())
		{
			int num = iterator.next();
			min = getMin(min, num);
		}

		return min;
	}

	private int getMin(int a, int b) {
		int retVal = 0;
		if(a <= b)
		{
			retVal = a;
		}
		else
		{
			retVal = b;
		}
		return retVal;
	}
	
}

