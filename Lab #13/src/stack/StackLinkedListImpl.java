package stack;

public class StackLinkedListImpl<ElementType> implements Stack<ElementType> {

	private MyNode<ElementType> first;
	private int count;
	private int max;
	
	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isFull() {
		// TODO Auto-generated method stub
		if(this.list.size()==max)
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
			throw new RuntimeException("Cannot push Full stack");
		}
		MyNode<ElementType>node = new MyNode<ElementType>(element);
		node.link = this.first;
		this.first = node;
		this.count ++;
	}

	@Override
	public ElementType pop() {
		// TODO Auto-generated method stub
		if(this.isEmpty())
		{
			throw new RuntimeException("Cannot pop empty stack");
		}
		ElementType retValue = first.data;
		first = first.link;
		count --;
		return retValue;
	}

	@Override
	public ElementType peek() {
		// TODO Auto-generated method stub
		if(this.isEmpty())
		{
			throw new RuntimeException("No push empty stack");
		}
		ElementType retValue = list.get(list.size();
		return retValue;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		this.count = 0;
		this.first = null;
	}
	
	public StackLinkedListImpl() {
	// default constructor sets max size to DEFAULT_MAX_SIZE
		this.count = 0;
		this.first = null;
		this.max = MAX_SIZE;
	}
	
	public StackLinkedListImpl(int aMaxSize) {
		//constructor that uses aMaxSize which
		// must be >=0 AND <= MAX_SIZE, otherwise uses 
		// MAX_SIZE
		this.count = 0;
		this.first = null;
		if(aMaxSize > MAX_SIZE)
		{
			aMaxSize = MAX_SIZE;
		}
		this.max = aMaxSize;
	}
	
	public String toString() {
		// creates a String of this instance from TOP to BOTTOM
		// 1 element per line
		String retValue = "";
		MyNode<ElementType> current = this.first;
		while(current != null)
		{
			retValue += current.data + "\n";
		}
		return retValue;
	}
	
}
