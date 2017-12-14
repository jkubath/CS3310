package edu.wmich.cs3310.hw4.kubath.application;

import java.util.HashMap;

public class BinaryTree {
	
	/**
	 * Insert a node while keeping a balanced tree
	 * @param head The head node of the tree
	 * @param insert The node to be inserted
	 */
	public static void insert(Node head, Node insert) {
		Node temp = head;
		while(temp.getLeftChild() != null && temp.getRightChild() != null) {
			//Go to the left child
			if(temp.getLeftChild().getSize() <= temp.getRightChild().getSize()) {
				temp = temp.getLeftChild();
			}
			else
				temp = temp.getRightChild();
		}
		//Now we are at a node with both children null or one child null
		if(temp.getLeftChild() == null)
			temp.setLeftChild(insert);
		else
			temp.setRightChild(insert);
		
		//Set the nodes parent pointer
		insert.setParent(temp);
		//Increment the size of the leaves
		increment(insert);
		//Heapify
		minHeap(insert);
		
		
	}
	
	/**
	 * Starts at the given leaf node and increments the size.
	 * The nodes are incremented until the root node is found (parent == null)
	 * @param leaf Node to start incrementing
	 */
	public static void increment(Node leaf) {
		Node temp = leaf;
		while(temp != null) {
			temp.setSize(temp.getSize() + 1);
			temp = temp.getParent();
		}
	}
	
	/**
	 * Starts at the given leaf node and decrements the size.
	 * The nodes are decremented until the root node is found (parent == null)
	 * @param leaf Node to start decrementing
	 */
	public static void decrement(Node leaf) {
		Node temp = leaf;
		while(temp != null) {
			temp.setSize(temp.getSize() - 1);
			temp = temp.getParent();
		}
	}
	
	/**
	 * Given a leaf node, heapify it using min heap algorithm
	 * @param leaf The node to start the heapify
	 */
	public static void minHeap(Node leaf) {
		Node temp = leaf;
		//While the leaf has a parent and data is < than the parent
		//Sorted by lastName of the Student object
		while(temp.getParent() != null && temp.getData().getLast().compareTo(temp.getParent().getData().getLast()) < 0) {
			//Hold the leaf data
			Student data = temp.getData();
			//Set the leaf data to the parent's data
			temp.setData(temp.getParent().getData());
			//Set the parent's data to the temp data
			temp.getParent().setData(data);
			temp = temp.getParent();
		}
	}
	
	/**
	 * Start at the head node and heapify down to the leaf nodes keeping the minHeap.
	 * @param head The root node of the tree
	 */
	public static void minHeapTopDown(Node head) {
		Node temp = head;
		Student swap = head.getData();
		//Already a leaf node
		if(head.getLeftChild() == null && head.getRightChild() == null)
			return;
		
		while(temp.getLeftChild() != null && temp.getRightChild() != null) {
			//If the left child is not null and has less last name than the right child
			if(temp.getLeftChild() != null)
			{	
				//If there is no right child, swap with right
				if(temp.getRightChild() == null) {
					swap = temp.getData();
					temp.setData(temp.getLeftChild().getData());
					temp.getLeftChild().setData(swap);
					temp = temp.getLeftChild();
				}
				//Go to the lesser side, sorted by last name
				else if(temp.getLeftChild().getData().getLast().compareTo(temp.getRightChild().getData().getLast()) < 0) {
					swap = temp.getData();
					temp.setData(temp.getLeftChild().getData());
					temp.getLeftChild().setData(swap);
					temp = temp.getLeftChild();
				}
				else {
					swap = temp.getData();
					temp.setData(temp.getRightChild().getData());
					temp.getRightChild().setData(swap);
					temp = temp.getRightChild();
				}
			}
			else {
				swap = temp.getData();
				temp.setData(temp.getRightChild().getData());
				temp.getRightChild().setData(swap);
				temp = temp.getRightChild();
			
			}
		
		}
		
		
	}
	
	/**
	 * Pre-Order traversal with printing of the nodes and size
	 * @param head The node to start printing at
	 */
	public static void printPreOrder(Node head) {
		//Visit the node
		System.out.println(head.getData().getLast() + " " + 
				head.getData().getFirst() + " " + head.getSize());
		//Visit left
		if(head.getLeftChild() != null)
			printPreOrder(head.getLeftChild());
		
		//Visit right
		if(head.getRightChild() != null)
			printPreOrder(head.getRightChild());
		
		
	}
	
	/**
	 * Post-Order traversal with printing of the nodes and size
	 * @param head The node to start printing at
	 */
	public static void printPostOrder(Node head) {
		Node temp = head;
		if(temp.getLeftChild() != null)
			printPostOrder(temp.getLeftChild());
		if(temp.getRightChild() != null)
			printPostOrder(temp.getRightChild());
		//Print data
		System.out.println(head.getData().getLast() + " " + head.getData().getFirst() + " " + head.getSize());
		
	}
	
	/**
	 * In-Order traversal with printing of the nodes and size
	 * @param head The node to start printing at
	 */
	public static void printInOrder(Node head) {
		Node temp = head;
		if(temp.getLeftChild() != null)
			printInOrder(temp.getLeftChild());
		//Print data
		System.out.println(head.getData().getLast() + " " + head.getData().getFirst() + " " + head.getPosition());
		if(temp.getRightChild() != null)
			printInOrder(temp.getRightChild());
		
		
	}
	
	/**
	 * Delete the head node, then heapify
	 * @param head The root of the tree
	 * @return The deleted node
	 */
	public static Student delete(Node head) {
		Node temp = head;
		Student returnNode = head.getData();
		
		while(temp.getLeftChild() != null && temp.getRightChild() != null) {
			//Go to the left child
			if(temp.getLeftChild().getSize() <= temp.getRightChild().getSize()) {
				temp = temp.getLeftChild();
			}
			else
				temp = temp.getRightChild();
		}
		temp = temp.getParent();
		//Now we are at a node with both children null or one child null
		//System.out.println("Temp " + temp.getData().getLast());
		Student swap;
		if(temp.getLeftChild() != null) {
			swap = temp.getLeftChild().getData();
			temp.getLeftChild().setData(head.getData());
			head.setData(swap);
			temp.getLeftChild().setParent(null);
			temp.setLeftChild(null);
		}
		else{
			swap = temp.getRightChild().getData();
			temp.getRightChild().setData(head.getData());
			head.setData(swap);
			temp.getRightChild().setParent(null);
			temp.setRightChild(null);
		}
		//System.out.println("Swapped " + head.getData().getLast());
		//Decrement the size of the leaves
		decrement(temp);
		//Heapify
		minHeapTopDown(head);
		
		return returnNode;
	}
	
	/**
	 * Take in a Node and enqueue the data into the a Queue
	 * Used for the min heap
	 * @param head Node object with Student data
	 * @param insert The queue to add the Student data to
	 */
	public static void enqueue(Node head, Queue insert, String name) {
		if(head == null) {
			return;
		}
		else {
			//Add the head to the Queue
			insert.add(head);
			//Only continue to enqueue data if the name is less than the current node
			//Otherwise we will be searching and the data will not be there
			//Data is sorted from high to low - so if the name is greater than the child it won't be beneath it either
			if(head.getLeftChild() != null && name.compareTo(head.getLeftChild().getData().getLast()) >= 0)
				enqueue(head.getLeftChild(), insert, name);
			if(head.getRightChild() != null && name.compareTo(head.getRightChild().getData().getLast()) >= 0)
				enqueue(head.getRightChild(), insert, name);
		}
			
	}
	
	/**
	 * Take in a student object and add it to the queue
	 * used for the Max heap
	 * @param maxHeap The container for the student objects
	 * @param insert The queue to insert the student object
	 * @param start The position to search for the student object in the array
	 */
	public static void enqueue(MaxHeap maxHeap, Queue insert, int start, String name) {
		//Array out of bounds
		if(start < 0 || start >= maxHeap.heap.length) {
			return;
		}
		else {
			//Add to the queue
			insert.add(maxHeap.heap[start]);
			//Only continue to enqueue data if the name is less than the current node
			//Otherwise we will be searching and the data will not be there
			//Data is sorted from high to low - so if the name is greater than the child it won't be beneath it either
			int left = (start * 2) + 1;
			if(left < maxHeap.heap.length && name.compareTo(maxHeap.heap[left].getData().getFirst()) <= 0)
				enqueue(maxHeap, insert, (start * 2) + 1, name);
			left++;
			if(left < maxHeap.heap.length && name.compareTo(maxHeap.heap[left].getData().getFirst()) <= 0)
				enqueue(maxHeap, insert, (start * 2) + 2, name);
		}
			
	}
	
	/**
	 * Iterate over the binary search tree and push the items to the stack
	 * Used for the min heap
	 * @param head The head of the binary search tree
	 * @param stack The stack to push the nodes to
	 */
	public static void pushStack(Node head, Stack stack, String name) {
		if(head == null) {
			return;
		}
		else {
			//Add the head to the Queue
			stack.insert(head);
			if(name.compareTo(head.getData().getLast()) >= 0 ) {
				if(head.getLeftChild() != null)
					pushStack(head.getLeftChild(), stack, name);
				if(head.getRightChild() != null)
					pushStack(head.getRightChild(), stack, name);
			}
		}
	}
	
	/**
	 * Push the items in the Node array to the stack
	 * @param array The array to pull the items from 
	 * @param stack The stack to push the items to
	 */
	public static void pushStack(Node[] array, Stack stack, String name) {
		for(int i =0; i < array.length; i++) {
			//If the name is greater than the current node
			//The data is sorted from high to low
			//The name will not be in the data below the current
			if(name.compareTo(array[i].getData().getFirst()) < 0)
				continue;
			stack.insert(array[i]);
		}
	}
	
	/**
	 * Process the queue and compare to the string name
	 * @param queue The queue to search for the name in
	 * @param last The last name
	 * @param first The first name
	 * @return The Node data if the name is found, null is returned if not found
	 */
	public static Node breadthFirst(Queue queue, String last, String first) {
		//Queue is empty
		if(queue.getHead() == null) {
			return null;
		}
		//System.out.println(queue.getHead().getData().getData().getLast());
		//Queue data is equal to our name
		if(queue.getHead().getData().getData().getLast().compareToIgnoreCase(last) == 0 &&
				queue.getHead().getData().getData().getFirst().compareToIgnoreCase(first) == 0) {
			return queue.getHead().getData();
		}
		//Iterate the queue
		else {
			//Delete the current item from the list
			queue.delete();
			return breadthFirst(queue, last, first);
		}
		
	}
	
	/**
	 * Process the stack and compare to the string name
	 * @param stack The stack to search for the name in
	 * @param last The last name
	 * @param first The first name
	 * @return The Node data if the name is found, null is returned if not found
	 */
	public static Node depthFirst(Stack stack, String last, String first) {
		//Stack is empty
		if(stack.getHead() == null) {
			return null;
		}
		//System.out.println(stack.getHead().getData().getData().getLast());
		//Stack data is equal to our name
		if(stack.getHead().getData().getData().getLast().compareToIgnoreCase(last) == 0 &&
				stack.getHead().getData().getData().getFirst().compareToIgnoreCase(first) == 0) {
			return stack.getHead().getData();
		}
		//Iterate the Stack
		else {
			//Delete the current item from the Stack
			stack.delete();
			return depthFirst(stack, last, first);
		}
	}
	
	/** Iterate over the binary tree and set the position
	 * Recursion is used to pass the number of nodes to the left of each node
	 * @param head The head node to the binary search tree
	 * @param map The HashMap to hold the number of nodes at each level
	 */
	public static void setPosition(Node head, HashMap<Integer, Integer> map) {
		//Leaf node is found
		if(head.getLeftChild() == null && head.getRightChild() == null) {
			//If that position already exists
			if(map.containsKey(head.getLevel())) {
				head.setPosition(map.get(head.getLevel()));
				//Increment the number of nodes at the current level
				map.replace(head.getLevel(), map.get(head.getLevel()) + 1);
			}
			//Create the key
			else {
				//Increment the number of nodes at that position
				map.put(head.getLevel(), 2);
				head.setPosition(1);
			}
			
//			System.out.println(head.getData().getLast() + " Level " + head.getLevel() + 
//					" Position " + head.getPosition());
			//Exit the recursion
			return;
		}
		
		//Go to left child
		if(head.getLeftChild() != null) {
			setPosition(head.getLeftChild(), map);
		}
		
		//Go to right child
		if(head.getRightChild() != null) {
			setPosition(head.getRightChild(), map);
		}
		
		//HashMap contains that key and position number
		if(map.containsKey(head.getLevel())) {
			//Set the position of the node
			head.setPosition(map.get(head.getLevel()));
			//Set the new number of nodes at the level to + 1
			map.replace(head.getLevel(), map.get(head.getLevel())+1);
		}
		//Create the key
		else {
			//Increment the number of nodes at that position
			map.put(head.getLevel(), 2);
			head.setPosition(1);
		}
		
//		System.out.println(head.getData().getLast() + " Level " + head.getLevel() + 
//				" Position " + head.getPosition());
		
	}
}
