/**
 * Author: Jonah Kubath
 * Assignment: CS3310 a2
 * Date: 10/10/2017
 * Summary: 
 * 	A file is opened and read from to get the strings from the user.
 * 	The strings are then passed through a queue and stack to check for
 * 	balanced parentheses.  
 * 
 */
package edu.wmich.cs3310.hw2.Kubath.application;

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
	 * @param setData
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






