package edu.wmich.cs3310.hw4.kubath.application;

public class Node {
	private Student data;
	private int size;
	private Node parent;
	private Node leftChild;
	private Node rightChild;
	int level = 9999;
	int position = 9999;
	
	/**
	 * Default constructor
	 */
	public Node() {
		
	}
	
	/**
	 * Overloaded constructor to create the head node
	 * @param head The head node
	 */
	public Node(String head) {
		size = 1;
		parent = null;
	}
	
	/**
	 * Getter for Data
	 * @return int The level of the node
	 */
	public int getLevel() {
		return level;
	}

	/**
	 * Setter for Data
	 * @param level The level to set the node
	 */
	public void setLevel(int level) {
		this.level = level;
	}

	/**
	 * Getter for Data
	 * @return int The position of the node in the current level
	 */
	public int getPosition() {
		return position;
	}

	/**
	 * Setter for Data
	 * @param position The position to set the node
	 */
	public void setPosition(int position) {
		this.position = position;
	}

	/**
	 * Getters and setters for private data
	 * @return Data called
	 */
	public Student getData() {
		return data;
	}
	
	/**
	 * Setter for Data
	 * @param data The student data to set in the node
	 */
	public void setData(Student data) {
		this.data = data;
	}
	
	/**
	 * Getter for Data
	 * @return size The size of the two sub trees + 1
	 */
	public int getSize() {
		return size;
	}
	
	/**
	 * Setter for Data
	 * @param size The size to set the node
	 */
	public void setSize(int size) {
		this.size = size;
	}
	
	/**
	 * Getter for Data
	 * @return node The parent node
	 */
	public Node getParent() {
		return parent;
	}
	
	/**
	 * Setter for Data
	 * @param parent The node to set as the parent node
	 */
	public void setParent(Node parent) {
		this.parent = parent;
	}
	
	/**
	 * Getter for Data
	 * @return node The left child
	 */
	public Node getLeftChild() {
		return leftChild;
	}
	
	/**
	 * Setter for Data
	 * @param leftChild The left child to set the left child to
	 */
	public void setLeftChild(Node leftChild) {
		this.leftChild = leftChild;
	}
	
	/**
	 * Getter for Data
	 * @return node The right child
	 */
	public Node getRightChild() {
		return rightChild;
	}
	
	/**
	 * Setter for Data
	 * @param rightChild The right child to set the right child to
	 */
	public void setRightChild(Node rightChild) {
		this.rightChild = rightChild;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
