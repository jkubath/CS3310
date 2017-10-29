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
}
