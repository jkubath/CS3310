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

public class QueueCheckBalancedParentheses {
	private String text = "";
	private CharQueue que = new CharQueue();
	
	/**
	 * Default constructor for class QueueCheckBalancedParentheses
	 */
	public QueueCheckBalancedParentheses() {
		
	}
	
	/**
	 * Overloaded constructor for class QueueCheckBalancedParentheses
	 * @param userText Sets the input from the user
	 */
	public QueueCheckBalancedParentheses(String userText) {
		setText(userText);
	}
	
	/**
	 * Checks the string for balanced parentheses
	 * @return 0 if the string is balanced or a number of off balanced
	 * parentheses
	 */
	public int CheckBalancedParentheses() {
		int count = 0;
		for(int i = 0; i < text.length(); i++) {
			if(text.charAt(i) == '(') {
				que.enqueue(text.charAt(i));
				count++;
			}
			else {
				que.dequeue();
				count--;
			}
			
			if(count < 0)
				return -1;
		}
		
		return count;
	}

	/**
	 * Takes in a string and removes anything that is not ( or )
	 * @param newText input string from the user
	 */
	public void setText(String newText) {
		text = newText;
		text = text.replaceAll("[^()]", "");
	}
}
