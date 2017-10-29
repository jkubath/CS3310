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

import java.io.FileNotFoundException;

public class Main {

	/**
	 * The main method
	 * @param args Arguments from the command line
	 * @throws FileNotFoundException Thrown when the input file is not found
	 */
	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		StackQueueDemo demo = new StackQueueDemo();
		demo.checkDemo();
	}

}
