/**
 * Author: Jonah Kubath
 * Summary: A node for the linked list class
 * 
 */
package LinkedList;

public class CharNode {
	private char myData;
	private CharNode nextNode;
	private CharNode previousNode;
	
	/**
	 * Default Constructor for class CharNode
	 */
	public CharNode() {
		
	}
	
	/**
	 * Overloaded constructor that takes in a character and makes a new
	 * node with that data.
	 * @param setData Information to set myData to
	 */
	//Overload constructor with data input to initialize node
	public CharNode(char setData) {
		setData(setData);
	}
	
	/**
	 * Setter for myData
	 * @param newData Information to set myData to
	 */
	//Setter for data
	public void setData(char newData) {
		myData = newData;
	}

	/**
	 * Getter for myData
	 * @return myData
	 */
	//Getter for data
	public char getData() {
		return myData;
	}
	
	/**
	 * Setter for the next node
	 * @param next The Node to set the current node to
	 */
	public void setNext(CharNode next) {
		nextNode = next;
	}
	
	/**
	 * Getter for the next node
	 * @return A pointer to the next node
	 */
	public CharNode getNext() {
		return nextNode;
	}
	
	/**
	 * Setter for the previous node
	 * @param previous Node to set the previous pointer to
	 */
	public void setPrevious(CharNode previous) {
		previousNode = previous;
	}
	
	/**
	 * Getter for the previous node
	 * @return A pointer to the previous node
	 */
	public CharNode getPrevious() {
		return previousNode;
	}
	
}
