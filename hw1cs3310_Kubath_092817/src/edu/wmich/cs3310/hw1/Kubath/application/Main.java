/**
 * Author: Jonah Kubath
 * Assignment: CS3310 a1
 * Date: 9/26/2017
 * Summary: 
 * 	Generate a random array.  Search this array with the user's name linearly.
 * 	Sort the array and search for the user's name with binary search.  Then, randomly change
 * 	letters in the user's name and re search the array using both linear search and binary search.
 * 	The time will be output each search, but the arrays will only be printed two times as long as
 * 	the length and width is <= 10.
 * 
 */
package edu.wmich.cs3310.hw1.Kubath.application;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Main {
	
	/**
	 * First block of code that is started when the program runs.  It controls if the user wants
	 * to run again or not.
	 * @param args Arguments from the command line
	 */
	public static void main(String[] args) {
		int runAgain = 1;
		long startTime;
		long endTime;
		long totalTime;
		Scanner scan = new Scanner(System.in);
		while(runAgain == 1) {
			//User input
			//String input[] = new String[4];
			String input[] = getArrayDimension(scan);
//			Test Data
//				input[0] = "8000";
//				input[1] = "zxywqruabcdefghi";
//				input[2] = "1";
//				input[3] = "1";
			//How many times to run the program
			for(int i = 0; i < Integer.parseInt(input[3]); i++) {
				startTime = System.currentTimeMillis();
				System.out.printf("\nProgram Run #%d --------------------------------------", (i+1));
				program(input);
				endTime = System.currentTimeMillis();
				totalTime = endTime - startTime;
				System.out.printf("Total Run Time - %.4f seconds\n",(double) totalTime / 1000);
			}
			
			boolean goodInput = false;
			while(!goodInput) {
				try {
					System.out.println("Would you like to run the program again?");
					System.out.println("1 to continue  |  2 to stop");
					runAgain = Integer.parseInt(scan.nextLine());
					if(runAgain == 1 || runAgain == 2)
						goodInput = true;
					else {
						System.out.println("Enter 1 or 2");
					}
				}
				catch (NumberFormatException e) {
					//Bad input was given
				}
			}
		
		}//End of while
		
		
	}// End of Main

	/**
	 * The main manager for the program.
	 * @param input The input from the user - array length, name, number of times to replace a letter,
	 * and how many times to run the program with the given data
	 */
	public static void program(String[] input) {
		Random rand = new Random();
		
		//Number of times to replace a letter in the name with a random letter
		int replaceNumber = Integer.parseInt(input[2]);
		//Decides whether or not to print to the stdout
		boolean outputArray = false;
		if(Integer.parseInt(input[0]) <= 10) {
			outputArray = true;
		}
		//Generate Random Array
		int randomArray[][] = generateArray(Integer.parseInt(input[0]));
		//Make space for sorted Array
		int sortedArray[][] = new int[randomArray.length][randomArray.length];
		//Temporary array to sort
		int[] tempArray = new int[randomArray.length * randomArray.length];
		//Array to hold data on if a character has already been read
		boolean found[][] = new boolean[randomArray.length][randomArray.length];
		String name = input[1].toLowerCase();
		
		//Linear Search -------------------------------------------------------------------------
		System.out.println("\nLinear Search ----------------------------------------");
		linearSearch(randomArray, found, name);
		
		//Copy to 1d array to sort
		int count = 0;
		for(int i = 0; i < found.length; i++) {
			for(int j = 0; j < found[i].length; j++) {
				tempArray[count] = randomArray[i][j];
				count++;
			}
		}
		
		//Sort Array
		//-------------------------------------------------------------------------
	
		//Sort Random array
		Arrays.sort(tempArray);
		
		System.out.println();
		
		//Copy array back to 2d and reset found to false
		count = 0;
		for(int i = 0; i < found.length; i++) {
			for(int j = 0; j < found[i].length; j++) {
				sortedArray[i][j] = tempArray[count];
				//Reset boolean found[][]
				found[i][j] = false;
				count++;
			}
		}
		
		//Print sorted array
		if(outputArray) {
			System.out.println("Sorted");
			for(int i = 0; i < found.length; i++) {
				for(int j = 0; j < found[i].length; j++) {
					System.out.printf("%c ", sortedArray[i][j]);
				}
				System.out.println();
			}
		}
		//-------------------------------------------------------------------------
		
		
		//Binary Search
		//-------------------------------------------------------------------------
		System.out.println("\nBinary Search ----------------------------------------");
		binarySearch(sortedArray, found, name);
		//-------------------------------------------------------------------------
		
		
		//Replace Letters
		double linearTotal = 0;
		double binaryTotal = 0;
		String randName = name;
		
		//Go through and replace a number in my name and re search
		//The number of times search / replaced is indicated by the user
		for(int i = 0; i < replaceNumber; i++) {
			int letterToReplace = 0;
			
			char nameArray[] = new char[randName.length()];
			//Choose a random letter to replace
			letterToReplace = rand.nextInt(randName.length());
			//Copy string to array
			for(int a = 0; a < nameArray.length; a++) {
				nameArray[a] = randName.charAt(a);
			}
			
			//Replace the letter with a random
			nameArray[letterToReplace] = (char) (97 + rand.nextInt(26));
			
			randName = "";
			//Rebuild the string to search for
			for(int j = 0; j < nameArray.length; j++) {
				randName += nameArray[j];
			}
			
			//Reset Found
			for(int k = 0; k < found.length; k++) {
				for(int j = 0; j < found[k].length; j++) {
					//Reset boolean found[][]
					found[k][j] = false;
				}
			}
			
			//Linear Search Random Name
			//-------------------------------------------------------------------------
			System.out.println("\nLinear Search - Random Name---------------------------");
			linearTotal += linearSearch(randomArray, found, randName);
			
			//Reset Found
			for(int h = 0; h < found.length; h++) {
				for(int j = 0; j < found[h].length; j++) {
					found[h][j] = false;
				}
			}
			
			//Binary Search Random Name
			//-------------------------------------------------------------------------
			System.out.println("\nBinary Search - Random Name---------------------------");
			binaryTotal += binarySearch(sortedArray, found, randName);
			
			
		}//End of random char replacement
		System.out.printf("Average Linear Time - %.4f seconds\n",(double) linearTotal / replaceNumber);
		System.out.printf("Average Binary Time - %.4f seconds\n",(double) binaryTotal / replaceNumber);
		
		
		
		
	}
	
	/**
	 * Receives the data from the user on array dimensions, their name, and how many times
	 * to replace letters in their name with randomly generate characters.
	 * @return array dimensions, user's name, and how many times to replace a char in their name
	 */
	private static String[] getArrayDimension(Scanner scan) {
		String input[] = new String[4];
		boolean goodInput = false;
		//Get input from user
		while(!goodInput) {
			try {
				System.out.println("What should the length of the array be?");
				input[0] = scan.nextLine();
				Integer.parseInt(input[0]);
				System.out.println("What is your name?");
				input[1] = scan.nextLine();
				input[1] = input[1].trim();
				input[1] = input[1].replaceAll(" ", "");
				System.out.println("How many times should we replace a letter in your name?");
				input[2] = scan.nextLine();
				System.out.println("How many times should the program run?");
				input[3] = scan.nextLine();
				Integer.parseInt(input[3]);
				goodInput = true;
			}
			catch(NumberFormatException e) {
				System.out.println("Enter only an integer.");
			}
		}
		
		return input;
	}
	
	/**
	 * Generates an array of characters from 97 - 123
	 * @param dimension The dimensions of the array to generate characters
	 * @return The randomly generated array
	 */
	private static int[][] generateArray(int dimension) {
		Random randomNum = new Random();
		int randomLetter[][] = new int[dimension][dimension];
		
		if(dimension < 11)
			System.out.println("\nRandom Matrix");
		//Generate Array
		for(int i = 0; i < dimension; i++) {
			for(int j = 0; j < dimension; j++) {
				randomLetter[i][j] = 97 + randomNum.nextInt(26);
			}
		}
		//Output Array
		if(dimension < 11) {
			for(int i = 0; i < dimension; i++) {
				for(int j = 0; j < dimension; j++) {
					System.out.print((char) randomLetter[i][j] + " ");
				}
				System.out.println();
			}
		}
		
		return randomLetter;
		
	}
	
	/**
	 * Linear search of an array given that the location has not already been used by an earlier
	 * letter of the user's name.
	 * @param array The array to search in
	 * @param found The boolean array to hold if a location has been used previously
	 * @param letter Which letter to search for in the array
	 * @return The coordinates in the array at which the letter was found or [-1, -1] for not found
	 */
	private static int[] searchArray(int[][] array, boolean[][] found, int letter) {
		int[] temp = new int[2];
		for(int i = 0; i < array.length; i++) {
			for(int j = 0; j < array[i].length; j++) {
				if(array[i][j] == letter && found[i][j] != true) {
					//Mark as found
					found[i][j] = true;
					//Return true;
					temp[0] = i;
					temp[1] = j;
					return temp;
				}
			}
		}
		temp[0] = -1;
		temp[1] = -1;
		return temp;
	}
	
	/**
	 * Binary search for the row a character would be contained in
	 * @param array The array to search in
	 * @param letter The letter to search for in the array
	 * @param beginning Start of the searched area in the array
	 * @param end End of the searched area in the array
	 * @return The row in which the letter was found or -1 if it was not found
	 */
	private static int binarySearchRow(int[][] array, int letter, int beginning, int end) {
		int middle = (beginning + end) / 2;
		int temp = -1;
		if(middle < 0 || middle >= array.length) {
			return -1;
		}
		else if(beginning > end) {
			return -1;
		}
		else if(end < beginning) {
			return -1;
		}
		else if(array[middle][0] <= letter && array[middle][array.length - 1] >= letter) {
			temp = middle;
			return temp;
		}
		else if(array[middle][0] > letter) {
			temp = binarySearchRow(array, letter, beginning, middle - 1);
		}
		else if(array[middle][array.length - 1] < letter) {
			temp = binarySearchRow(array, letter, middle + 1, end);
		}
		else
			temp = -1;
		
		return temp;
	}
	
	/**
	 * Binary search for a column a character would be contained in
	 * @param array The array to search in
	 * @param found The boolean array to hold if a location has been used previously
	 * @param letter Which letter to search for in the array
	 * @param beginning Start of the searched area in the array
	 * @param end End of the searched area in the array
	 * @param row The row in which the letter was contained
	 * @return The row in which the letter was found or -1 if it was not found
	 */
	private static int binarySearchCol(int[][] array, boolean[][] found, int letter, int beginning,
			int end, int row) {
		
		int middle = (beginning + end) / 2;
		int temp = -1;
		if(array[row][middle] == letter) {
			if(found[row][middle] != true) {
				temp = middle;
				return temp;
			}
			else {
				int middleCol = middle;
				
				while(middle < array.length - 1) {
					middle++;
					if(array[row][middle] == letter && found[row][middle] != true) {
						return middle;
					}
				}
				middle = middleCol;
				while(middle > 0) {
					middle--;
					if(array[row][middle] == letter && found[row][middle] != true) {
						return middle;
					}
				}
				
			}
		}
		else if(beginning > end) {
			return -1;
		}
		else if(end < beginning) {
			return -1;
		}
		else if(array[row][middle] > letter) {
			temp = binarySearchCol(array, found, letter, beginning, middle - 1, row);
		}
		else if(array[row][middle] < letter) {
			temp = binarySearchCol(array, found, letter, middle + 1, end, row);
		}
		else
			temp = -1;
		
		return temp;
	}
	
	/**
	 * Linear Search of an array
	 * @param randomArray The array to search in
	 * @param found The boolean array to hold if a location has been used previously
	 * @param name The user's name to search
	 */
	private static double linearSearch(int[][] randomArray, boolean[][] found, String name) {
		//Start time
		//Where in the 2d array a letter is found or [-1,-1] if not found
		int[] foundIndex = new int[2];
		long startTime = System.currentTimeMillis();
		for(int i = 0; i < name.length(); i++) {
			int letter = name.charAt(i);
			foundIndex = searchArray(randomArray, found, letter);
			System.out.printf("%c : [%d,%d]\n", letter, foundIndex[0], foundIndex[1]);
		}
		//End time
		long endTime = System.currentTimeMillis();
		//In seconds
		double totalTime =(double) (endTime - startTime) / 1000;
		System.out.printf("Time - %.4f seconds\n", totalTime);
		
		return totalTime;
	}
	
	/**
	 * Main operator for Binary Search.  binarySearchRow() and binarySearchCol() are called.
	 * @param sortedArray The sorted array of randomly generated characters
	 * @param found The boolean array to tell if a character has previously been 
	 * used by another character
	 * @param name The name of the user to search for in the array
	 */
	private static double binarySearch(int[][] sortedArray, boolean[][] found, String name) {
		long startTime, endTime;
		double totalTime = 0;
		startTime = System.currentTimeMillis();
		for(int i = 0; i < name.length(); i++) {
			
			//System.out.println("Binary Search");
			int col = -1;
			int row = binarySearchRow(sortedArray,(int) name.charAt(i), 0, sortedArray.length - 1);
			//System.out.println(row);
			if(row > -1) {
				col = binarySearchCol(sortedArray, found, (int) name.charAt(i), 0, sortedArray.length - 1, row);
			
			}
			//System.out.println(col + 1);
			
			if(row == -1 || col == -1) {
				System.out.printf("%c - [%d, %d]\n", name.charAt(i), -1, -1);
			}
			else {
				found[row][col] = true;
				System.out.printf("%c - [%d, %d]\n", name.charAt(i), row , col);
			}
			
		}
		//End time
		endTime = System.currentTimeMillis();
		totalTime =(double) (endTime - startTime) / 1000;
		
		System.out.printf("Time - %.4f seconds\n",(double) totalTime);
		
		return totalTime;
	}
	


}
