package stack;
/*
 * interface for a typical stack (LIFO list)
 * all expected methods.
 *  
 * @author: LJBaker
 */
public interface Stack <ElementType>
{
	//max size used by default if none given.
    public static final int DEFAULT_MAX_SIZE = 4;
    public static final int MAX_SIZE = 500;  // largest max size allowable
   /**receives: nothing
     *  task:  tests to see if the Stack has 0 elements
     *  returns: true if this StackList is empty (has 0 elements) false otherwise;
  */
  public boolean isEmpty();

  /**receives: nothing
   *  task:  tests to see if this StackList instance has getMaxSize() elements 
   *  returns: true if this StackList is full (has getMaxSize() elements) false otherwise;
*/
  public boolean isFull();

 

/** receives: nothing
 * returns: number of elements on this StackList (always <= to getMaxSize())
 */
  public int getSize();
  

/** receives: nothing
 * returns: max number of elements allowed on this StackList (always <= LARGEST)
 */
  public int getMaxSize();



/** receives: element to place on top of this StackList
 * task: received element is on top of this StackList instance if it is not full
 * returns: nothing
 *@throws RuntimeException if this StackList is full (has max size elements already)
 */
  public void push(ElementType element);

/** receives: nothing
   returns:  removes AND returns the top item from this StackList if  it is NOT empty. 
 * @throws - RuntimeException if attempt to pop an empty StackList
 */
  public ElementType pop();

/** receives: nothing
  *returns the top item from this StackList  if it is
 *         not empty.  DOES NOT POP IT.
 * @throws - RuntimeException if attempt to peek at an empty StackList instance
 */
  public ElementType peek();

/**receives: nothing
    returns: nothing
    task:  removes all items from this StackList, making it empty.
 */
  public void clear();
}
