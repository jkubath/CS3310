package edu.wmich.cs3310.hw5.kubath.application;

public class Queue234<Key extends Comparable<? super Key>> {
	private NodeQueue234<Key> head;
	private NodeQueue234<Key> tail;
	
	/**
	 * Default Constructor will create an empty queue
	 */
	public Queue234() {
		head = null;
		tail = null;
	}
	
	/**
	 * Overloaded constructor will create a new queue and set the input NodeQueue
	 * object to the head node.
	 * @param newHead The NodeQueue object to set the head node as
	 */
	public Queue234(NodeQueue234<Key> newHead) {
		head = newHead;
		tail = head;
	}

	/**
	 * Getter for Data
	 * @return head The head node of the Queue
	 */
	public NodeQueue234<Key> getHead() {
		return head;
	}

	/**
	 * Setter for Data
	 * @param head The node to set as the queue's head node
	 */
	public void setHead(NodeQueue234<Key> head) {
		this.head = head;
	}

	/**
	 * Getter for Data
	 * @return NodeQueue The tail NodeQueue object
	 */
	public NodeQueue234<Key> getTail() {
		return tail;
	}

	/**
	 * Setter for Data
	 * @param tail The NodeQueue to set as the tail Node
	 */
	public void setTail(NodeQueue234<Key> tail) {
		this.tail = tail;
	}
	
	/**
	 * Take the Node object in and add a NodeQueue object to the queue with the 
	 * Node data
	 * @param newNode The new data to initialize a NodeQueue object with
	 */
	public void add(LeafNode234<Key> newNode) {
		NodeQueue234<Key> temp = new NodeQueue234<Key>(newNode);
		
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
	public NodeQueue234<Key> delete() {
		if(head == null) {
			return null;
			//Do nothing
		}
		NodeQueue234<Key> temp = head;
		//if(head.getNext() != null)
		head = head.getNext();
		return temp;
	}
	
	
}
