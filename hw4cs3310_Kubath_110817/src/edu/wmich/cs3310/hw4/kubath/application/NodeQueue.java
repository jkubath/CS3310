package edu.wmich.cs3310.hw4.kubath.application;

public class NodeQueue {
	private NodeQueue next;
	private Node data;
	
	/**
	 * Default constructor will set data and next to null
	 */
	public NodeQueue() {
		data = null;
		next = null;
	}
	
	/**
	 * Overloaded constructor will create a node and set the Node data to the
	 * input data
	 * @param newData The data to initialize the new node with
	 */
	public NodeQueue(Node newData) {
		data = newData;
		next = null;
	}

	/**
	 * Getter for Data
	 * @return NodeQueue The next node
	 */
	public NodeQueue getNext() {
		return next;
	}

	/**
	 * Setter for Data
	 * @param next The next node to set to
	 */
	public void setNext(NodeQueue next) {
		this.next = next;
	}

	/**
	 * Getter for Data
	 * @return Node The node data held
	 */
	public Node getData() {
		return data;
	}

	/**
	 * Setter for Data
	 * @param data The Node data to set the data to
	 */
	public void setData(Node data) {
		this.data = data;
	}
	
}
