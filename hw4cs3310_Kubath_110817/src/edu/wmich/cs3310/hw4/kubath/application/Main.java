package edu.wmich.cs3310.hw4.kubath.application;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

	/**
	 * First lines of code to run
	 * @param args Arguments from the command line
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		//Initialization----------------------------
		int timesToRun = 50;
		ArrayList<Student> list = new ArrayList<Student>();
		Node minHeap = new Node("head");
		BST binarySearch = new BST();
		long start = 0;
		long end = 0;
		long total = 0;
		long[] totalArr = new long[5];
		totalArr[0] = totalArr[1] = totalArr[2] = totalArr[3] = totalArr[4] = 0;
		//Done Initializing-------------------------
		
		//Open the file for reading
		//Get a new file from the user if NameList.txt is not in the directory
		ReadFile.getFile();
		
		//Read the file
		//Create the minHeap and copy the read data to the ArrayList<Student> list
		ReadFile.readFile(minHeap, list);
		MaxHeap heap = new MaxHeap(list);
		
		//Build the list to hold the original data and build the BST
		int index = 0;
		while(index < list.size()) {
			//Add each Student node to the max heap
			heap.insert(list.get(index));
			//Add to the binary search tree
			binarySearch.insert(binarySearch.getHead(), list.get(index), 1);
			//System.out.println(list.get(index).getFirst());
			index++;
		}
		//Set the positions of the nodes in the BST
		binarySearch.setPosition(binarySearch.getHead());
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		BinaryTree.setPosition(minHeap, map);
		
//		/*Print the Data---------------------------------------------------------*/
//		//Print the minHeap in Pre-Order
//		System.out.println("Min Heap");
//		BinaryTree.printPreOrder(minHeap);
//		
//		//Print the maxHeap in Pre-Order
//		System.out.printf("\nMax Heap\n");
//		heap.printPreOrder(0);
//		
//		//Print the BST in In-Order - should be in increasing order
//		System.out.println("Binary Search Tree");
//		BinaryTree.printInOrder(binarySearch.getHead());
//		/*Print the Data---------------------------------------------------------*/
		
		/*Search the Data--------------------------------------------------------*/
		
		String first = "jonah";
		String last = "kubath";
		//String name = getSearchName();
		/*This data is used for printing the average search for every name*/
		//Create an array to hold each name's average
		AverageSearch[] average = new AverageSearch[list.size()];
		
		for(int j = 0; j < list.size(); j++) {
			//Create an object to hold their name and average time
			average[j] = new AverageSearch();
			average[j].setLast(list.get(j).getLast());
			average[j].setFirst(list.get(j).getFirst());
			last = list.get(j).getLast();
			first = list.get(j).getFirst();
			totalArr[0] = totalArr[1] = totalArr[2] = totalArr[3] = totalArr[4] = 0;
		//Calculate an average search time
		for(int i = 0; i < timesToRun; i++) {
			//Reset total Array
			
		/*Breadth First---------------------------------------------------------*/
		if(i < 1 && j < 1)
			System.out.println("Breadth First");
		//Enqueue the data from Max Heap
		Queue queue = new Queue();
		BinaryTree.enqueue(heap, queue, 0, first);
		//Search the Max Heap
		if(i < 1 && j < 1)
			System.out.println("Search Max Heap Breadth First for - " + last + " " + first);
		start = System.nanoTime();
		Node found = BinaryTree.breadthFirst(queue, last , first);
		end = System.nanoTime();
		total = end - start;
		if(i < 1 && j < 1) {
			System.out.println("Time - " + total + "ns");
			printNodeData(found);
		}
		totalArr[0] += total;
		
		
		//Enqueue the data from Min Heap
		Queue minQueue = new Queue();
		BinaryTree.enqueue(minHeap, minQueue, last);
		//Search the Min Heap
		if(i < 1 && j < 1)
			System.out.println("\nSearch Min Heap Breadth First for - " + last + " " + first);
		start = System.nanoTime();
		found = BinaryTree.breadthFirst(minQueue, last, first);
		end = System.nanoTime();
		total = end - start;
		if(i < 1 && j < 1) {
			System.out.println("Time - " + total + "ns");
			printNodeData(found);
		}
		totalArr[1] += total;
		
		/*Depth First-----------------------------------------------------------*/
		if(i < 1 && j < 1)
			System.out.println("\nDepth First");
		Stack stack = new Stack();
		//Push items onto the stack
		BinaryTree.pushStack(heap.heap, stack, first);
		//Search the stack for the name
		if(i < 1 && j < 1)
			System.out.println("Search MaxHeap Depth First for - " + last + " " + first);
		start = System.nanoTime();
		found = BinaryTree.depthFirst(stack, last, first);
		end = System.nanoTime();
		total = end - start;
		if(i < 1 && j < 1) {
			System.out.println("Time - " + total + "ns");
			printNodeData(found);
		}
		totalArr[2] += total;
		
		Stack stackMin = new Stack();
		//Push items onto the stack
		BinaryTree.pushStack(minHeap, stackMin, last);
		//Search the stack for the name
		if(i < 1 && j < 1)
			System.out.println("\nSearch MinHeap Depth First for - " + last + " " + first);
		start = System.nanoTime();
		found = BinaryTree.depthFirst(stackMin, last, first);
		end = System.nanoTime();
		total = end - start;
		if(i < 1 && j < 1) {
			System.out.println("Time - " + total + "ns");
			printNodeData(found);
		}
		totalArr[3] += total;
		
		/*BST Search------------------------------------------------------------*/
		if(i < 1 && j < 1)
			System.out.println("\nSearch BST for - " + last + " " + first);
		start = System.nanoTime();
		found = binarySearch.searchLast(binarySearch.getHead(), last, first);
		end = System.nanoTime();
		total = end - start;
		if(i < 1 && j < 1) {
			System.out.println("Time - " + total + "ns");
			printNodeData(found);
		}
		totalArr[4] += total;
		} //End of calculating average search time
		
			if(j < 1)
				printAverage(totalArr, timesToRun);
			average[j].setMaxHeapBreadth(totalArr[0] / timesToRun);
			average[j].setMinHeapBreadth(totalArr[1] / timesToRun);
			average[j].setMaxHeapDepth(totalArr[2] / timesToRun);
			average[j].setMinHeapDepth(totalArr[3] / timesToRun);
			average[j].setBst(totalArr[4] / timesToRun);
			
		
		}//Searching every name
		
		
		
		//Print the data with pre-order, in-order, post-order
		if(list.size() < 60) {
			//Print the data
			System.out.println("\nMax Heap-----------------------------------------");
			System.out.println("\nIn Order");
			heap.printInOrder(0);
			System.out.println("\nPost Order");
			heap.printPostOrder(0);
			System.out.println("\nPre Order");
			heap.printPreOrder(0);
			System.out.println("\nMin Heap-----------------------------------------");
			System.out.println("\nIn Order");
			BinaryTree.printInOrder(minHeap);
			System.out.println("\nPre Order");
			BinaryTree.printPreOrder(minHeap);
			System.out.println("\nPost Order");
			BinaryTree.printPostOrder(minHeap);
			System.out.println("\nBST-----------------------------------------");
			System.out.println("\nIn Order");
			BinaryTree.printInOrder(binarySearch.getHead());
			System.out.println("\nPre Order");
			BinaryTree.printPreOrder(binarySearch.getHead());
			System.out.println("\nPost Order");
			BinaryTree.printPostOrder(binarySearch.getHead());
		}
			//print data to a file
			printDataToFile(average);
		
	}
	
	/**
	 * Take in the Node object and print the data held
	 * @param data The node object to read data from
	 */
	public static void printNodeData(Node data) {
		if(data == null) {
			System.out.println("Name not found");
			return;
		}
		
		//Node data
		System.out.println("Level    - " + data.getLevel());
		System.out.println("Position - " + data.getPosition());
		
		//Parent
		if(data.getParent() != null) {
			System.out.println("Parent   - " + data.getParent().getData().getLast() + " " + data.getParent().getData().getFirst());
		}
		else
			System.out.println("Parent   - null");
		
		//Left Child
		if(data.getLeftChild() != null) {
			System.out.println("Left     - " + data.getLeftChild().getData().getLast() + " " + data.getLeftChild().getData().getFirst());
		}
		else {
				System.out.println("Left     - null");
		}
		
		//Right Child
		if(data.getRightChild() != null) {
			System.out.println("Right    - " + data.getRightChild().getData().getLast() + " " + data.getRightChild().getData().getFirst());
		}
		else {
			System.out.println("Right    - null");
		}
		
	}
	
	/**
	 * Get the search name from the user
	 * @return A string to search for
	 */
	public static String getSearchName() {
		boolean goodInput = false;
		Scanner scan = new Scanner(System.in);
		String name = "";
		
		while(!goodInput) {
		try {
			System.out.println("Enter the search name.");
			name = scan.nextLine();
			String check = name.replaceAll("[^a-zA-z]", "");
			if(check.length() != name.length())
				throw new Exception();
			else
				goodInput = true;
		}
		catch(Exception e) {
			System.out.println("Enter only letters");
		}
		
		}
		
		scan.close();
		return name;
	}
	
	/**
	 * Print the average run time for the Max and Min Heap and the BST
	 * @param array The array that holsd the total time data
	 * @param timesRun The number of times the program was run to collect the data
	 */
	public static void printAverage(long[] array, int timesRun) {
		System.out.println("\nAverage Time-----------------------------------------");
		System.out.println("Breadth First");
		System.out.println("\tMax Heap - " + array[0] / timesRun);
		System.out.println("\tMin Heap - " + array[1] / timesRun);
		System.out.println("Depth First");
		System.out.println("\tMax Heap - " + array[2] / timesRun);
		System.out.println("\tMin Heap - " + array[3] / timesRun);
		System.out.println("Binary Search Tree");
		System.out.println("\tBST      - " + array[4] / timesRun);
		
		
	}

	/**
	 * Search every name and record the average time
	 * Write that data to the data.csv file
	 * @param data The average search times for each name
	 * @throws IOException If there is an error writing to the file
	 */
	public static void printDataToFile(AverageSearch[] data) throws IOException {
		File newFile = new File("namelist.csv");
		FileWriter inFile = new FileWriter(newFile);
		String dataFormat = "%s %s,%d,%d,%d,%d,%d\n";
		String output = "";
		inFile.write("Name, MaxHeap Breadth, MinHeap Breadth, MaxHeap Depth, MinHeap Depth, BST\n");
		for(int i = 0; i < data.length; i++) {
			output = String.format(dataFormat, data[i].getLast(), data[i].getFirst(), data[i].getMaxHeapBreadth(), 
					data[i].getMinHeapBreadth(), data[i].getMaxHeapDepth(), data[i].getMinHeapDepth(), data[i].getBst());
			
			inFile.write(output);
		}
		
		inFile.close();
	}
}
