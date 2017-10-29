package edu.wmich.cs3310.hw3.Kubath.application;

import LinkedList.CharList;
import LinkedList.CharNode;
import java.util.Random;
import java.util.Scanner;

public class Main {


	/**
	 * First piece of code to run
	 * @param args Arguments from the command line
	 */
	public static void main(String[] args) {
		//Initialize input from the user
		//Holds an array of characters
		String[] userInput = getInput();
		int length = Integer.parseInt(userInput[0]);
		String name = userInput[1];
		
		//int length = 16384;
		//String name = "jonah";
		
		double[] list = new double[4];
		double[] array = new double[4];
		long start = 0;
		long end = 0;
		long microseconds = 0;
		list[0] = 0;
		list[1] = 0;
		list[2] = 0;
		list[3] = 0;
		array[0] = 0;
		array[1] = 0;
		array[2] = 0;
		array[3] = 0;
		
		Character sorted[] = new Character[length];
		Character data[] = new Character[length];
		Character copy[] = new Character[data.length];
		copyArray(data, copy);
		
		//Create hashmap to sort with
		MyMap newMap = new MyMap(name);
		
		//Used to run the program a few times
		int timesRun = 0;
		int totalRun = 10;
		
		System.out.println("-----------------------------------------------------------------");
		System.out.println("The LinkedList and Array will only be printed on the first run.");
		System.out.println("The other 9 runs will re-generate random characters and the time");
		System.out.println("will be used in the average");
		System.out.println("-----------------------------------------------------------------\n");
		for(timesRun = 0 ; timesRun < totalRun; timesRun++){
		
		//Generate the random arrays
		CharList linkedList = generateList(length);
		data = copyListToArray(linkedList, data);
		
		
		//Print the original list
		
		if(timesRun == 0 && length < 1000){
			System.out.println("Original List");
			printList(linkedList);
		}
		
		//Copy the list
		CharList copyLi = copyList(linkedList);
		Sort.selectionSort(copyLi, newMap, 0);
		copyLi = copyList(linkedList);
		copyArray(data, copy);
		Sort.selectionSort(copy, newMap, 0);
		copyArray(data, copy);
		
		
		//Find the user's name
		//Linked List-----------------------------------------------------------------
		//Sort the lists
		start = System.nanoTime();
		Sort.selectionSort(copyLi, newMap, 0);
		end = System.nanoTime();
		microseconds = (end - start) / 1000;
		list[0] += microseconds;
		
		
		if(timesRun == 0 && length < 1000){
			System.out.println("Sorted List");
			printList(copyLi);
			System.out.printf("\nSelection List  - %d micro\n", microseconds);
			
		}
		
		//Copy the original List
		copyLi = copyList(linkedList);
//		System.out.println("CopyList");
//		printList(copyLi);
		
		//Bubble Sort
		start = System.nanoTime();
		Sort.bubbleSort(copyLi, newMap, 0);
		end = System.nanoTime();
		microseconds = (end - start) / 1000;
		list[1] += microseconds;
		
		if(timesRun == 0 && length < 1000){
			System.out.printf("Bubble List     - %d micro\n", microseconds);
			//printList(copyLi);
		}
		
		//Copy the original List
		copyLi = copyList(linkedList);
//		System.out.println("CopyList");
//		printList(copyLi);
				
		//Insertion Sort
		start = System.nanoTime();
		copyLi.setHead(Sort.insertionSort(copyLi, newMap, 0));
		end = System.nanoTime();
		microseconds = (end - start) / 1000;
		list[2] += microseconds;
		
		if(timesRun == 0 && length < 1000){
			System.out.printf("Insertion List  - %d micro\n", microseconds);
			//printList(copyLi);	
		}
		
		//Copy the original List
		copyLi = copyList(linkedList);
//		System.out.println("CopyList");
//		printList(copyLi);
		
		//Merge Sort
		//Go to the first node
		CharNode leftNode = copyLi.getHead();
		CharNode next = leftNode.getNext();
		start = System.nanoTime();
		try {
		leftNode.setNext((Sort.mergeSort(next, newMap)));
		}
		catch(Error e) {
			System.out.println("Too many stack calls.");
		}
		end = System.nanoTime();
		microseconds = (end - start) / 1000;
		list[3] += microseconds;
		
		if(timesRun == 0 && length < 1000){
			System.out.printf("Merge List      - %d micro\n", microseconds);
			//printList(copyLi);
		}
		//Array-----------------------------------------------------------------
//		System.out.println("Array");
//		printArray(data);
		
		
		//Bubble Sort
		start = System.nanoTime();
		Sort.bubbleSort(copy, newMap, 0);
		end = System.nanoTime();
		microseconds = (end - start) / 1000;
		array[0] += microseconds;
		
		if(timesRun == 0 && length < 1000){
			copyArray(copy, sorted);
			System.out.printf("Bubble Array    - %d micro\n", microseconds);
			//printArray(copy);
		}
		
		//Selection Sort
		copyArray(data, copy);
		start = System.nanoTime();
		Sort.selectionSort(copy, newMap, 0);
		end = System.nanoTime();
		microseconds = (end - start) / 1000;
		array[1] += microseconds;
		
		if(timesRun == 0 && length < 1000){
			System.out.printf("Selection Array - %d micro\n", microseconds);
			//printArray(copy);
		}
		
		//Insertion Sort
		copyArray(data, copy);
		start = System.nanoTime();
		Sort.insertionSort(copy, newMap, 0);
		end = System.nanoTime();
		microseconds = (end - start) / 1000;
		array[2] += microseconds;
		
		if(timesRun == 0 && length < 1000){
			System.out.printf("Insertion Array - %d micro\n", microseconds);
			//printArray(copy);
		}
	
		
		//Merge Sort
		copyArray(data, copy);
		Character[] temp = new Character[copy.length];
		start = System.nanoTime();
		try {
			Sort.mergeSort(copy, temp, newMap, 0, temp.length - 1);
		}
		catch(Error e) {
			System.out.println("Too many stack calls");
		}
		end = System.nanoTime();
		microseconds = (end - start) / 1000;
		array[3] += microseconds;
		
		if(timesRun == 0 && length < 1000){
			System.out.printf("Merge Array     - %d micro\n", microseconds);
			//printArray(copy);
		}
		
		}//End of for loop
		
		//Output---------------------------------------------------------
		System.out.println();
		System.out.println("Data");
		System.out.println("\tName  : " + name);
		System.out.println("\tLength: " + length);
		System.out.print("\tOriginal : ");
		printArray(data);
		System.out.print("\tSorted   : ");
		printArray(sorted);
		System.out.println();
		System.out.println("-------------Average of 10 runs (Microseconds 10^-6)-------------");
		System.out.println("\tList");
		System.out.printf("Selection - %6.2f micro\n", list[0] / totalRun);
		System.out.printf("Bubble    - %6.2f micro\n", list[1] / totalRun);
		System.out.printf("Insertion - %6.2f micro\n", list[2] / totalRun);
		System.out.printf("Merge     - %6.2f micro\n", list[3] / totalRun);
		System.out.println("\tArray");
		System.out.printf("Selection - %6.2f micro\n", array[0] / totalRun);
		System.out.printf("Bubble    - %6.2f micro\n", array[1] / totalRun);
		System.out.printf("Insertion - %6.2f micro\n", array[2] / totalRun);
		System.out.printf("Merge     - %6.2f micro\n", array[3] / totalRun);
		
		

	}//End of main
	
	
	/**
	 * Generates a random array of lower case characters the 
	 * size of length
	 * @param length The length the array should be
	 * @return The array generated
	 */
	public static Character[] generateArray(int length) {
		Character data[] = new Character[length];
		//Generate the array
		Random rand = new Random();
		for(int i = 0; i < length; i++){
			data[i] =(char) (97 + rand.nextInt(26));
		}
		
		return data;
	}
	
	/**
	 * Generates a random list of lower case characters
	 * the length of the parameter
	 * @param length The length of the list
	 * @return A pointer to the list
	 */
	public static CharList generateList(int length) {
		CharList linkedList = new CharList();
		//Generate the array
		Random rand = new Random();
		for(int i = 0; i < length; i++){
			linkedList.insertTail((char) (97 + rand.nextInt(26)));
		}
		
		return linkedList;
	}
	
	/**
	 * Prints the array given in
	 * @param array The array to print from
	 */
	public static void printArray(Character[] array){
		for(int i = 0; i < array.length; i++){
			System.out.print(array[i] + " ");
		}
		System.out.println();
	}
	
	/**
	 * Prints the list given in
	 * @param list The list to print from
	 * @return int Returns 0 on completion
	 */
	public static int printList(CharList list){
		CharNode tmp;
		tmp = list.getHead();
		if(list.length() == 0)
			return 0;
		else if(list.length() == 1) {
			System.out.println(tmp.getNext().getData());
			return 0;
		}
			
		while(tmp.getNext() != null){
			if(tmp.getData() != 0){
				System.out.print(tmp.getData() + " ");
			}
			tmp = tmp.getNext();
		}
		System.out.print(tmp.getData());
		System.out.println();
		return 0;
	}
	
	/**
	 * Gets the input from the user
	 * @return The name of the user and how long to make the array
	 */
	public static String[] getInput() {
		Scanner scan = new Scanner(System.in);
		String[] userInput = new String[2];
		boolean goodInput = false;
		while (!goodInput) {
			try {
				System.out.println("Enter the number of characters to generate");
				String input = scan.nextLine();
				Integer.parseInt(input);
				userInput[0] = input;
				goodInput = true;
			} catch (NumberFormatException e) {
				System.out.println("Enter only numbers");
			} 
		}
		goodInput = false;
		while (!goodInput) {
			try {
				System.out.println("Enter your name");
				String input1 = scan.nextLine();
				String inputCheck = input1.replaceAll("[^a-zA-Z]", "");
				if(inputCheck.length() != input1.length() || input1.length() == 0)
					throw new Exception();
				input1 = input1.toLowerCase();
				userInput[1] = input1;
				goodInput = true;
			} catch (Exception e) {
				System.out.println("Enter only letters");
			} 
		}
		scan.close();
		return userInput;
	}
	
	/**
	 * Copies the data array to the copy array
	 * @param data Original array
	 * @param copy Copy of the Data array
	 */
	public static void copyArray(Character[] data, Character[] copy){
		for(int i = 0; i < data.length; i++){
			copy[i] = data[i];
		}
	}
	
	/**
	 * Copies the list to another list
	 * @param orig Original List
	 * @return Pointer to the new list
	 */
	public static CharList copyList(CharList orig){
		CharList copy = new CharList();
		//Start at the first node with data
		CharNode begin = orig.getHead().getNext();
		while(begin != null){
			copy.insertTail(begin.getData());
			begin = begin.getNext();
		}
		
		return copy;
	}
	
	/**
	 * Copies the given list to the array
	 * @param list The list to get data from
	 * @param array The array to save data in
	 * @return A pointer to the array
	 */
	public static Character[] copyListToArray(CharList list, Character[] array){
		CharNode left = list.getHead().getNext();
		int count = 0;
		while(left != null){
			array[count] = left.getData();
			left = left.getNext();
			count++;
		}
		
		return array;
	}

}
