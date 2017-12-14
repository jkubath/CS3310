package edu.wmich.cs3310.hw5.kubath.application;

public class NodeQueue234<Key extends Comparable<? super Key>> {
	private NodeQueue234<Key> next;
	private LeafNode234<Key> data;
	
	/**
	 * Default constructor will set data and next to null
	 */
	public NodeQueue234() {
		data = null;
		next = null;
	}
	
	/**
	 * Overloaded constructor will create a node and set the Node data to the
	 * input data
	 * @param newData The data to initialize the new node with
	 */
	public NodeQueue234(LeafNode234<Key> newData) {
		data = newData;
		next = null;
	}

	/**
	 * Getter for Data
	 * @return NodeQueue The next node
	 */
	public NodeQueue234<Key> getNext() {
		return next;
	}

	/**
	 * Setter for Data
	 * @param next The next node to set to
	 */
	public void setNext(NodeQueue234<Key> next) {
		this.next = next;
	}

	/**
	 * Getter for Data
	 * @return Node The node data held
	 */
	public LeafNode234<Key> getData() {
		return data;
	}

	/**
	 * Setter for Data
	 * @param data The Node data to set the data to
	 */
	public void setData(LeafNode234<Key> data) {
		this.data = data;
	}
	
}
