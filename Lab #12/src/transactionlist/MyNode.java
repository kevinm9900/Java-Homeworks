package transactionlist;

/** Node.java
 *implements Node class generically
 * used for Linked List classes
 * @version 1.1
 */

public class MyNode <DataType> {
  public DataType data;       // the data portion of a Node (public since this is only used by use within a class implementation)
  public MyNode<DataType> link; // the link portion of a Node (public since this is only used by use within a class implementation)

/** creates an empty Node with both data and link null
 */
  public MyNode () {
    this.data=null;
    this.link = null;
  }
/** creates a Node with given data value, link is null
 *@param theData - the data value to use
 */
  public MyNode  (DataType theData) {
    this.data = theData;
    this.link = null;
  }
/** creates a Node with given link value, data is null
 *@param theLink - the link value to use
 */
  public MyNode  (MyNode<DataType> theLink) {
    this.data = null;
    this.link = theLink;
  }


/** creates a Node with given data and link
 *@param  - theData - the data value to place into Node's data
 *@param  - theLink - the value to link the Node to (next link)
*/
  public MyNode  (DataType theData, MyNode<DataType> theLink) {
    this.data = theData;
    this.link = theLink;
 }
}//end of Node<T> class

