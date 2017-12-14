package edu.wmich.cs3310.hw5.kubath.application;

public class NodeQueue23<Key extends Comparable<? super Key>> {
	private NodeQueue23<Key> next;
	private LeafNode<Key> data;
	
	/**
	 * Default constructor will set data and next to null
	 */
	public NodeQueue23() {
		data = null;
		next = null;
	}
	
	/**
	 * Overloaded constructor will create a node and set the Node data to the
	 * input data
	 * @param newData The data to initialize the new node with
	 */
	public NodeQueue23(LeafNode<Key> newData) {
		data = newData;
		next = null;
	}

	/**
	 * Getter for Data
	 * @return NodeQueue The next node
	 */
	public NodeQueue23<Key> getNext() {
		return next;
	}

	/**
	 * Setter for Data
	 * @param next The next node to set to
	 */
	public void setNext(NodeQueue23<Key> next) {
		this.next = next;
	}

	/**
	 * Getter for Data
	 * @return Node The node data held
	 */
	public LeafNode<Key> getData() {
		return data;
	}

	/**
	 * Setter for Data
	 * @param data The Node data to set the data to
	 */
	public void setData(LeafNode<Key> data) {
		this.data = data;
	}
	
}
