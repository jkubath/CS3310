/**
 * Author: Jonah Kubath
 * Summary: A basic linked list class
 */
package LinkedList;

public class CharList {
	CharNode head;
	CharNode tail;
	
	/**
	 * Constructor for class CharList
	 * Creates a head node and points the tail node to the head
	 */
	public CharList() {
		head = new CharNode();
		tail = head;
	}
	
	
	/**
	 * Creates and inserts a node at the tail of the list
	 */
	//Insert a new node
	public void insertTail() {
		//Create a new node
		CharNode newNode = new CharNode();
		newNode.setPrevious(tail);
		//Add the node to the list
		tail.setNext(newNode);
		//Move the tail pointer
		tail = newNode;
		
	}
	
	/**
	 * Inserts the given node to the end of the list
	 * @param newChar The new Character to set in the added node
	 */
	//Overload insert
	//If a node is given, then add it to the end of the list
	public void insertTail(char newChar) {
		CharNode newNode = new CharNode();
		newNode.setData(newChar);
		newNode.setPrevious(tail);
		tail.setNext(newNode);
		tail = newNode;
	}
	
	/**
	 * Deletes the tail node of the list
	 * @return The deleted node or null if the list is empty
	 */
	public CharNode deleteTail() {
		if(tail == head) {
			//List is empty
			//System.out.println("List is empty");
		}
		else {
			CharNode temp = tail;
			//Get the previous node and set the tail to it
			if(tail.getPrevious() != null) {
				tail = tail.getPrevious();
			}
			else
				tail = head;
			return temp;
		}
		
		return null;
	}
	
	/**
	 * Deletes the head node of the list
	 * @return The deleted node or null if the list is empty
	 */
	public CharNode deleteHead() {
		if(tail == head) {
			//List is empty
			//System.out.println("List is empty");
		}
		else {
			CharNode temp = head;
			//Get the previous node and set the tail to it
			head = head.getNext();
			return temp;
		}
		
		return null;
	}
	
	/**
	 * Returns a pointer to the head node.  Returns null
	 * if the list is empty.
	 * @return A pointer to the head node
	 */
	public CharNode getHead() {
		if(tail == head)
			return null;
		else
			return head;
	}
	
	/**
	 * Sets the head node.
	 * @param newHead The new node to set as the head node
	 */
	public void setHead(CharNode newHead) {
		head = newHead;
	}
	
	/**
	 * Returns the length of the list
	 * @return The length of the list
	 */
	public int length() {
		if(head ==tail)
			return 0;
		if(head.getNext().getNext() == null)
			return 1;
		
		
		int count = 0;
		CharNode begin = getHead().getNext();
		while(begin != null){
			count++;
			begin = begin.getNext();
		}
		
		return count;
	}
}

