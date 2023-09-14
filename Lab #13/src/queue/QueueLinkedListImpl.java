package queue;

import stack.MyNode;

public class QueueLinkedListImpl<ElementType> implements Queue<ElementType> {

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isFull() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getMaxSize() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void add(ElementType element) {
		// TODO Auto-generated method stub
		if(this.isFull())
		{
			throw new RuntimeException("Full queue cannot add" + element);
		}
		MyNode<ElementType>node = new MyNode<ElementType>(element);
		if(this.last == null)
		{
			this.last = node;
			this.first = node;
		}
		else
		{
			this.last.link = node;
			this.last = node;
		}
		this.count ++;
	}

	@Override
	public ElementType remove() {
		// TODO Auto-generated method stub
		if(this.isEmpty())
		{
			throw new RuntimeException("Attempt to remove from an empty queue");
		}
		ElementType retValue = this.first.data; 
		this.first = this.getFirst().link;
		if(this.first == null)
		{
			this.last = null;
		}
		this.count --;
		return retValue;
		
	}

	@Override
	public ElementType getFirst() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ElementType getLast() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub

	}
	
	public QueueLinkedListImpl() {
		// default constructor sets max size to DEFAULT_MAX_SIZE
		this.first = null;
		this.last = null;
		this.maxSize = DEFAULT_MAX_SIZE;
		this.count = 0;
	}
	
	public QueueLinkedListImpl(int aMaxSize) {
		//constructor that uses aMaxSize which
		// must be >=0 AND <= MAX_SIZE 
		// otherwise uses MAX_SIZE
		this.first = null;
		this.last = null;
		if(aMaxSize > 0 && aMaxSize <= DEFAULT_MAX_SIZE)
		{
			this.maxSize = aSize;
		}
		else
		{
			this.maxSize = DEFAULT_MAX_SIZE;
		}
		this.count = 0;
	}
	
	public String toString() {
		String retValue = "";
		MyNode<ElementType> current = this.first;
		while(current != null)
		{
			retValue += current.data + "\n";
		}
		return retValue;
	}

}
