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

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class StackQueueDemo {
	private String userInput = "";
	private QueueCheckBalancedParentheses que;
	private StackCheckBalancedParentheses stack;
	private Scanner scan;
	
	/**
	 * Default constructor for StackQueueDemo
	 */
	public StackQueueDemo() {
		
	}
	
	/**
	 * Opens a file and reads in the strings.  The strings are then checked
	 * for balanced parentheses using the appropriate classes
	 * @throws FileNotFoundException The file was not found
	 */
	public void checkDemo() throws FileNotFoundException {
		
		double totalStack = 0;
		double totalQueue = 0;
		int trials = 0;
		openFile();
		
		while(scan.hasNext()) {
			
			getUserInput();
			System.out.println(userInput);
			int count = 0;
			
			que = new QueueCheckBalancedParentheses(userInput);
			stack = new StackCheckBalancedParentheses(userInput);
			
			long start = System.nanoTime();
			
			count = que.CheckBalancedParentheses();
			if(count != 0) {
			System.out.printf("Queue Unbalanced\n");
			}
			else
				System.out.println("Queue is balanced");
			long end = System.nanoTime();
			long microseconds = (end - start) / 1000;
			totalQueue += microseconds;
			System.out.printf("Time for Queue - %d micro s\n", microseconds);
			
			
			start = System.nanoTime();
			count = stack.CheckBalancedParentheses();
			if(count != 0) {
				System.out.printf("Stack Unbalanced\n");
				}
				else
					System.out.println("Stack is balanced");
			
			end = System.nanoTime();
			microseconds = (end - start) / 1000;
			totalStack += microseconds;
			System.out.printf("Time for Stack - %d micro s\n\n", microseconds);
			trials++;
		}
		
		System.out.printf("Average Queue - %f micro s\n", (totalQueue / trials));
		System.out.printf("Average Stack - %f micro s\n", (totalStack / trials));
		scan.close();
	}
	
	/**
	 * Gets the input from the user or the file
	 */
	public void getUserInput() {
		userInput = scan.nextLine();
		
	}
	
	/**
	 * Try / catch to open the default file - balancedParenCheckInputs.txt
	 * The user is prompted for a new input file if the default file is
	 * not found.
	 */
	public void openFile() {
		String defaultFile = "balancedParenCheckInputs.txt";
		Scanner newScan = new Scanner(System.in);;
		boolean goodFile = false;
		while(!goodFile) {
			try {
				File inFile = new File(defaultFile);
				scan = new Scanner(inFile);
				goodFile = true;
			}
			catch (FileNotFoundException e) {
				System.out.println("File not found.");
				System.out.println("Enter an input file");
				defaultFile = newScan.nextLine();
				
			}
			
		}//End of while
		
		//Close the scanner
		newScan.close();
	}
	
}
