package stack;

import java.util.ArrayList;
import java.util.List;

public class StackArrayListImpl<ElementType> implements Stack<ElementType> {

	private Stack<ElementType> list;
	private int max;
	private int count;
	private Object first;
	
	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isFull() {
		// TODO Auto-generated method stub
		if(this.list.getSize()==max)
			return true;
		else
			return false;
	}
	

	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		return count;
	}

	@Override
	public int getMaxSize() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void push(ElementType element) {
		// TODO Auto-generated method stub
		if(this.isFull())
		{
			throw new RuntimeException("Full stack cannot push" + element);
		}
		this.list.add(element);
		
	}

	@Override
	public ElementType pop() {
		// TODO Auto-generated method stub
		if(this.isEmpty())
		{
			throw new RuntimeException("No push empty stack");
		}
		ElementType retValue = list.remove(list.getSize();
		return retValue;
	}

	@Override
	public ElementType peek() {
		// TODO Auto-generated method stub
		if(this.isEmpty())
		{
			throw new RuntimeException("No push empty stack");
		}
		ElementType retValue = list.get(list.getSize();
		return retValue;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		this.count = 0;
		this.first = null;
	}
	
	public StackArrayListImpl() {
		// default constructor sets max size to DEFAULT_MAX_SIZE
		this.list = new Stack<ElementType> (MAX_SIZE);
		this.max = MAX_SIZE;
		
	}
	public StackArrayListImpl(int aMaxSize) { 
		//constructor that uses aMaxSize which
		// must be >=0 AND <= MAX_SIZE, otherwise uses 
		// MAX_SIZE
		if(aMaxSize > MAX_SIZE)
		{
			aMaxSize = MAX_SIZE;
		}
		this.list = new Stack<ElementType>(aMaxSize);
		this.max = aMaxSize;
	}
	
	public String toString() {
		// creates a String of this instance from TOP to BOTTOM
		// 1 element per line
		String retValue = "";
		for(int i = list.getSize()-1; i>=0; i--)
		{
			retValue += list.get(i) + "\n";
		}
		return retValue;
	}
	
}
