package dominoes;

import java.util.List;

public class DominoLowDifferenceStringImpl_Moran implements Domino{
	private String lowDifferenceString;
    private static final char LOW_DIFFERENCE_DELIMITER = '*';

    public static boolean isLowDifferenceString(String str)
	{
		str = str.trim();
		boolean retBool = false; 
		int pos = find(LOW_DIFFERENCE_DELIMITER, str);
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
    
    private static int find(char ch, String str) {
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
    

    public DominoLowDifferenceStringImpl_Moran(int lowPlus8TimesHigh) {
    	assert (isLowPlus8TimesHighInteger(lowPlus8TimesHigh)) :"PLEASE SEND A VALID INTEGER";
		
		
		int lowPipCount = lowPlus8TimesHigh % 8;
		
		int highPipCount = lowPlus8TimesHigh / 8;
		
		int difference = highPipCount - lowPipCount;
		String lowDiff = lowPipCount + "*" + difference;
		
		lowDifferenceString = lowDiff;
    }
    	
    public static final int INDEX_OF_HIGH = 0;
    	public static final int INDEX_OF_SUM = 1;
    
    public DominoLowDifferenceStringImpl_Moran(List<Integer> highSum)
                                      
    {
    	assert (highSum.size() == 2): "PLEASE SEND A VALID LIST!!";
		assert (highSum.get(INDEX_OF_HIGH) <= MAXIMUM_PIP_COUNT && highSum.get(INDEX_OF_HIGH) >= MINIMUM_PIP_COUNT): "ERROR: HIGH PIP COUNT MUST BE <= 6";
		assert (highSum.get(INDEX_OF_SUM) >= MINIMUM_PIP_COUNT && highSum.get(INDEX_OF_SUM) <= 2 * MAXIMUM_PIP_COUNT): "ERROR: SUM OUT OF BOUNDS";
		assert (highSum.get(INDEX_OF_HIGH) <= highSum.get(INDEX_OF_SUM)): "ERROR: SUM CANNOT BE LESS THAN HIGH";
		assert ((highSum.get(INDEX_OF_SUM) - highSum.get(INDEX_OF_HIGH)) <= 6): "ERROR: SUM OUT OF BOUNDS";
		

		int highPipCount = highSum.get(INDEX_OF_HIGH);

		int sum = highSum.get(INDEX_OF_SUM);
		

		int lowPipCount = sum - highPipCount;

		int difference = highPipCount - lowPipCount;
		
		String lowDiff = lowPipCount + "*" + difference;
		
		lowDifferenceString = lowDiff;
	}

	@Override
	public int getHighPipCount() {
		int lowPipCount = Integer.parseInt(Character.toString(lowDifferenceString.charAt(0)));
		int difference = Integer.parseInt(Character.toString(lowDifferenceString.charAt(2)));
		return lowPipCount + difference;
	}

	@Override
	public int getLowPipCount() {
		int lowPipCount = Integer.parseInt(Character.toString(lowDifferenceString.charAt(0)));
		return lowPipCount;
	}

} 


