package edu.wmich.cs3310.hw4.kubath.application;

import java.util.ArrayList;

public class MaxHeap {
	Node[] heap;
	int last;
	
	/**
	 * Default constructor that sets the size to 0
	 */
	public MaxHeap() {
		last = 0;
	}
	
	/**
	 * Overloaded constructor to initialize the array
	 * @param list ArrayList of Student type to get the data from
	 */
	public MaxHeap(ArrayList<Student> list) {
		heap = new Node[list.size()];
		last = 0;
	}
	
	/**
	 * Add the new record to the end of the array and then max Heapify
	 * @param record The new record to be added
	 */
	public void insert(Student record) {
		Node temp = new Node();
		temp.setData(record);
		int parent = (last - 1) / 2;
		if((last - 1) / 2 >= 0){
			temp.setParent(heap[(last - 1) / 2]);
			//Left child
			if((parent * 2) + 1 < heap.length && (parent * 2) + 1 == last) {
				heap[parent].setLeftChild(temp);
			}
			else if((parent * 2) + 2 < heap.length && (parent * 2) + 2== last) {
				heap[parent].setRightChild(temp);
			}
		}
		else
		{
			temp.setParent(null);
		}
		
		int levelNumber = last;
		int level = 0;
		while(levelNumber != 0) {
			levelNumber = (levelNumber - 1) / 2;
			level++;
		}
		
		//Total nodes - the number of nodes held as interior nodes
		int position = last - (int) Math.pow(2, level) + 1;
		//Increment position because the array starts at 0, but
		//Furthest left node in each level starts at 1
		position++;
		
		temp.setLevel(level);
		temp.setPosition(position);
		
		heap[last] = temp;
		
		maxHeapify(last);
		last++;
		
	}
	
	/**
	 * Take a node and move it up the list until the parent is greater than the given node
	 * @param last The node to start at in the max heap array
	 */
	public void maxHeapify(int last) {
		int parent = (last - 1) / 2;
		//Array out of bounds
		if(parent < 0) {
			return;
		}
		else {
			//Parent is less than the added node
			if(heap[parent].getData().getFirst().compareTo(heap[last].getData().getFirst()) < 0) {
				Student temp = heap[last].getData();
				heap[last].setData(heap[parent].getData());
				heap[parent].setData(temp);
			}
			else
				//If the parent is already greater than the added node
				//Do nothing
				return;
			
		//Recursively move up the array	
		maxHeapify(parent);	
			
			
		}
	}
	
	/**
	 * Iterate through the list printing the array in-order
	 * @param print The index of the array current visiting
	 */
	public void printInOrder(int print) {
		int leftChild = (2 * print) + 1;
		if(leftChild >= last) {
			return;
			//Do nothing
			//Leaf Node found
		}else
			//Visit left child
			printInOrder(leftChild);
		
		//Visit Middle Node
		if(heap[print] != null)
			System.out.println(heap[print].getData().getLast() + " " + heap[print].getData().getFirst());
		
		if(leftChild >= last) {
			//Do nothing
			//Leaf Node found
		}else
			//Visit the right child
			printInOrder(leftChild + 1);
		
		
	}
	
	/**
	 * Iterate through the list printing the array pre-order
	 * @param print The index of the array current visiting
	 */
	public void printPreOrder(int print) {
		int leftChild = (2 * print) + 1;
		if(print >= heap.length || print < 0)
			return;
		//Visit Middle Node
		if(heap[print] != null)
			System.out.println(heap[print].getData().getLast() + " " + heap[print].getData().getFirst());
				
		if(leftChild >= last) {
			//Do nothing
			//Leaf Node found
		}else {
			//Visit left child
			printPreOrder(leftChild);
			//Visit the right child
			printPreOrder(leftChild + 1);
		}
		
	}
	
	/**
	 * Iterate through the list printing the array post-order
	 * @param print The index of the array current visiting
	 */
	public void printPostOrder(int print) {
		int leftChild = (2 * print) + 1;
		
				
		if(leftChild >= last) {
			return;
			//Do nothing
			//Leaf Node found
		}else {
			//Visit left child
			printPostOrder(leftChild);
			//Visit the right child
			printPostOrder(leftChild + 1);
		}
		
		//Visit Middle Node
		if(heap[print] != null)
			System.out.println(heap[print].getData().getLast() + " " + heap[print].getData().getFirst());
		
	}
	
	/**
	 * Deletes the first node (root) and returns it
	 * @return The deleted node
	 */
	public Node delete() {
		Node temp = heap[0];
		
		heap[0] = heap[last - 1];
		heap[last - 1] = null;
		last--;
		//Re order the array
		heapify(0);
		
		return temp;
	}
	
	/**
	 * Start and index and move the node until it is greater than the children nodes are finds
	 * a null spot or the array is out of bounds
	 * @param index The position to start the heapify
	 */
	public void heapify(int index) {
		Node temp;
		int lchild = (index * 2) + 1;
		//Array out of bounds
		if(lchild >= heap.length)
			return;
		
		if(heap[lchild] != null) {
			if(heap[lchild].getData().getFirst().compareTo(heap[index].getData().getFirst()) > 0) {
				//Left child is greater than the root
				temp = heap[lchild];
				heap[lchild] = heap[index];
				heap[index] = temp;
				heapify(lchild);
			}
		}
		else
		{
			if(heap[lchild + 1].getData().getFirst().compareTo(heap[index].getData().getFirst()) > 0) {
				//Right child is greater than the root
				temp = heap[lchild + 1];
				heap[lchild + 1] = heap[index];
				heap[index] = temp;
				heapify(lchild + 1);
			}
		}
		
	}
	
}
