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

public class CharQueue {
	private CharList list = new CharList();
	
	/**
	 * Adds a node to the tail of the queue
	 */
	public void enqueue() {
		list.insertTail();
	}
	
	/**
	 * Adds a node to the tail with the given character as data
	 * @param newChar The character to set in the node
	 */
	public void enqueue(char newChar) {
		list.insertTail(newChar);
	}
	
	/**
	 * Removes a node from the queue
	 * @return
	 */
	public CharNode dequeue() {
		return list.deleteHead();
	}

}
