package edu.wmich.cs3310.hw4.kubath.application;

public class Queue {
	private NodeQueue head;
	private NodeQueue tail;
	
	/**
	 * Default Constructor will create an empty queue
	 */
	public Queue() {
		head = null;
		tail = null;
	}
	
	/**
	 * Overloaded constructor will create a new queue and set the input NodeQueue
	 * object to the head node.
	 * @param newHead The NodeQueue object to set the head node as
	 */
	public Queue(NodeQueue newHead) {
		head = newHead;
		tail = head;
	}

	/**
	 * Getter for Data
	 * @return head The head node of the Queue
	 */
	public NodeQueue getHead() {
		return head;
	}

	/**
	 * Setter for Data
	 * @param head The node to set as the queue's head node
	 */
	public void setHead(NodeQueue head) {
		this.head = head;
	}

	/**
	 * Getter for Data
	 * @return NodeQueue The tail NodeQueue object
	 */
	public NodeQueue getTail() {
		return tail;
	}

	/**
	 * Setter for Data
	 * @param tail The NodeQueue to set as the tail Node
	 */
	public void setTail(NodeQueue tail) {
		this.tail = tail;
	}
	
	/**
	 * Take the Node object in and add a NodeQueue object to the queue with the 
	 * Node data
	 * @param newNode The new data to initialize a NodeQueue object with
	 */
	public void add(Node newNode) {
		NodeQueue temp = new NodeQueue(newNode);
		
		if(head == null) {
			head = temp;
			tail = temp;
		}
		else {
			tail.setNext(temp);
			tail = temp;
		}
		
	}
	
	/**
	 * Remove the first item from the queue and move the head pointer
	 * @return NodeQueue Returns the deleted NodeQueue object
	 */
	public NodeQueue delete() {
		if(head.getNext() == null) {
			//Do nothing
		}
		NodeQueue temp = head;
		head = head.getNext();
		return temp;
	}
	
	
}
