package change;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;


public class ChangeMakerImpl_Moran implements ChangeMaker
{	
	
	private Set<Integer> denominations;
	
	//part of pre: i <= 0 ==> !denominations.contains(i)
	//Student should figure out other parts of the precondition
	public ChangeMakerImpl_Moran(Set<Integer> denominations)
	{
		this.denominations = denominations;
		validateDenominations();
 	}
	
	private List<Integer> getChangeMakerList(){
		Integer[] denominationsArray = new Integer[denominations.size()];
		int i = 0;
		for (Integer denomination : denominations) {
			assert denomination >= 0;
			denominationsArray[i++] = denomination; 
 		}
		Arrays.sort(denominationsArray);
		List<Integer> denArray = Arrays.asList(denominationsArray);
		Collections.reverse(denArray);
		return denArray;
	}
	
	public List<Integer> getDenominations()
	{
		return getChangeMakerList();
	}
	
	public void validateDenominations() {
		assert denominations != null:"Cannot have a null value passed into denominations";
		assert !denominations.contains(null):"Cannot have a null value passed into denominations";
	}
	
	public boolean canMakeExactChange(int valueInCents)
	{
		if(valueInCents < 0) {
			throw new RuntimeException("value cannot be negative");
		}
		List<Integer> exactChange = new ArrayList<Integer>();
		Iterator<Integer> iterator = getChangeMakerList().iterator();
		while (iterator.hasNext()) {
			int coin = iterator.next();
			exactChange.add(valueInCents / coin);
			valueInCents = valueInCents % coin;
		}		
		return 0 == valueInCents;
	}

	
	public int calculateValueOfChangeList(List<Integer> changeList)
	{
		assert changeList.size() == getChangeMakerList().size():"Size of change list must be the same size as the denominations list";
		int valueOfChange = 0;
		for (int i = 0; i < changeList.size(); i++) {
			if(changeList.get(i) < 0) {
				throw new RuntimeException("cannot pass in a negative number");
			}
			if(changeList.get(i) > getChangeMakerList().get(i)){
				throw new RuntimeException("change cannot be higher than the next largest denominator");
			}
 			valueOfChange += changeList.get(i) * getChangeMakerList().get(i);
 			}
		return valueOfChange;
	}


	public List<Integer> getExactChange(int valueInCents)
	{
		if(!canMakeExactChange(valueInCents)) {
			throw new RuntimeException("Must return exact change");
		}
		List<Integer> exactChange = new ArrayList<Integer>();
		Iterator<Integer> iterator = getChangeMakerList().iterator();
		while (iterator.hasNext()) {
			int coin = iterator.next();
			exactChange.add(valueInCents / coin);
			valueInCents = valueInCents % coin;
		}
		return exactChange;
	}
	
	public String toString()
	{
		throw new RuntimeException("NOT IMPLEMENTED YET!");
	}
}
