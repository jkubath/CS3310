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

public class CharStack {
	CharList list = new CharList();
	
	/**
	 * Adds a new node to the tail of the stack
	 */
	public void push() {
		list.insertTail();
	}
	
	/**
	 * Adds a new node to the tail of the stack with the given data
	 * @param newChar The data to add to the new node
	 */
	public void push(char newChar) {
		list.insertTail(newChar);
	}
	
	/**
	 * Removes a node from the tail of the stack
	 * @return
	 */
	public CharNode pop() {
		return list.deleteTail();
	}
}
