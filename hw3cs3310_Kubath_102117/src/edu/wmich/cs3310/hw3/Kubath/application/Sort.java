package edu.wmich.cs3310.hw3.Kubath.application;

import java.util.Comparator;

import LinkedList.CharList;
import LinkedList.CharNode;

public class Sort<T> {
	
	/**
	 * Sorts the Character array using the data held in map.  Starts
	 * sorting the array at the value entered in begin
	 * @param array The array to sort
	 * @param map The values used to compare two characters
	 * @param begin The position to start sorting at
	 * @return int Returns 0 on completion
	 */
	public static int bubbleSort(Character[] array, MyMap map, int begin){
		if(array.length == 0 || array.length == 1)
			return 0;
	
		for(int i = begin; i < array.length; i++){
			for(int j = begin; j < array.length - 1; j++){
				   if(map.test(array[j], array[j+1]) > 0){
					Character value = array[j];
					array[j] = array[j+1];
					array[j + 1] = value;
				}
			}
		}
		return 0;
	}
	
	/**
	 * Generic bubble sort for array starting at begin
	 * @param array The array to sort
	 * @param begin The position to start sorting at
	 * @param <T> generic type extends Comparable T
	 */
	public static <T extends Comparable<T>> void bubbleSort(T[] array, int begin){
		for(int i = begin; i < array.length; i++){
			for(int j = begin; j < array.length - 1; j++){
				   if(array[j].compareTo(array[j+1]) > 0){
					T value = array[j];
					array[j] = array[j+1];
					array[j + 1] = value;
				}
			}
		}
		
	}
	
	/**
	 * Generic bubble sort for the array using the super comparator
	 * @param array The array to sort
	 * @param comparator The comparator to compare two values
	 * @param <T> generic type
	 */
	public static <T> void bubbleSort(T[] array, Comparator<? super T> comparator){
		for(int i = 0; i < array.length; i++){
			for(int j = 0; j < array.length - 1; j++){
				   if(comparator.compare(array[j], array[j+1]) > 0){
					T value = array[j];
					array[j+1] = array[j];
					array[j] = value;
				}
			}
		}
		
	}
	
	/**
	 * Sorts the CharList using the data help in map.  Starts sorting
	 * the list at the value entered in begin
	 * @param list The list to sort
	 * @param map The values used to compare two characters
	 * @param begin The position to start sorting at
	 * @return The pointer to the sorted list
	 */
	public static CharList bubbleSort(CharList list, MyMap map, int begin){
		if(list.length() == 0) {}
            //System.out.println("List is currently empty.");
        else if (list.length() == 1) {}
            //System.out.println("List is already sorted.");
        else {
            CharNode leftNode = list.getHead().getNext();
    		for(int i = 0; i < begin; i++){
    			leftNode = leftNode.getNext();
    		}
    		//Current points to the first node after the name sort
    		CharNode current = leftNode;
            boolean isSorted = true;
            while(isSorted) {
                current = leftNode;
                isSorted = false;
  
                // Start at the beginning and loop over all elements                           
                while(current.getNext() != null) {
                    if(map.test(current.getData(), current.getNext().getData()) > 0) {
                    	char temp = current.getData();
                    	current.setData(current.getNext().getData());
                    	current.getNext().setData(temp);
                        isSorted = true;
                    }
                    current = current.getNext();
                }
                 
            }
            }
		
		return list;
	}
	
	/**
	 * Selection sort for the given array.  Using the values help in map
	 * starting at the position given in begin
	 * @param array The array to sort
	 * @param map The values used to compare two characters
	 * @param begin The position to start sorting at
	 * @return int Returns 0 on completion
	 */
	public static int selectionSort(Character[] array, MyMap map, int begin){
		if(array.length == 0 || array.length == 1)
			return 0;
		
		int index = 0;
		for(int i = begin; i < array.length - 1; i++){
			index = i;
			for(int j = i + 1; j < array.length; j++){
				   if(map.test(array[j], array[index]) < 0){
					index = j;
				}
			}
			Character temp = array[index];
			array[index] = array[i];
			array[i] = temp;
		}
		return 0;
	}
	
	/**
	 * Generic Selection sort for the given array.  Uses the comparator
	 * to compare two objects
	 * @param array The array to sort
	 * @param comparator The comparator to compare two objects
	 * @param <T> generic type
	 * @return int Returns 0 on completion
	 */
	public static <T> int selectionSort(T[] array, Comparator<? super T> comparator){
		if(array.length == 0 || array.length == 1)
			return 0;
		
		int begin = 0;
		int index = 0;
		for(int i = begin; i < array.length - 1; i++){
			index = i;
			for(int j = i + 1; j < array.length; j++){
				   if(comparator.compare(array[i], array[index]) < 0){
					index = j;
				}
			}
			T temp = array[index];
			array[index] = array[i];
			array[i] = temp;
		}
		return 0;
	}
	
	/**
	 * Selection Sort for the given list.  Uses the values held in map
	 * to compare two characters.  Begins sorting at the value of begin 
	 * @param list The list to sort
	 * @param map The values used to compare two characters
	 * @param begin The position to start sorting at
	 * @return int Returns 0 on completion
	 */
	public static int selectionSort(CharList list, MyMap map, int begin){
		if(list.length() == 0 || list.length() == 1) {
			return 0;
		}
		
		CharNode leftNode = list.getHead().getNext();
		CharNode rightNode;
		for(int i = 0; i < begin; i++){
			leftNode = leftNode.getNext();
		}
		rightNode = leftNode.getNext();
		//Iterate through the list
		while(leftNode.getNext() != null){
			//Find the lowest item in the list
			rightNode = leftNode.getNext();
			while(rightNode != null){
				if(map.test(rightNode.getData(), leftNode.getData()) < 0){
					char temp = rightNode.getData();
					rightNode.setData(leftNode.getData());
					leftNode.setData(temp);
				}
				rightNode = rightNode.getNext();
			}
			leftNode = leftNode.getNext();
		}
		return 0;
	}
	
	/**
	 * Swaps two elements in the CharList
	 * @param list The list to swap elements in
	 * @param left First node to swap
	 * @param right Second node to swap
	 */
	public static void swap(CharList list, int left, int right){

		//System.out.printf(" -- %d %d\n", left, right);
		if(left == right)
			return;
		
		int larger = 0;
		int smaller = 0;
		if(left > right){
			larger = left;
			smaller = right;
		}
		else
		{
			larger = right;
			smaller = left;
		}
		//Both point to the node right before the node to swap
		CharNode rightNode = list.getHead();
		CharNode leftNode = null;
		//Iterate through the list and get the data
		for(int i = 0; i < larger; i++){
			if(i == smaller){
				leftNode = rightNode;
			}
			rightNode = rightNode.getNext();
		}
		//System.out.println("Left - " + leftNode.getData());
		//System.out.println("Left - " + rightNode.getData());
		
		//Node right before the left node to swap
		char temp = leftNode.getData();
		leftNode.setData(rightNode.getData());
		rightNode.setData(temp);
		
		
		
	}
	
	/**
	 * Used to merge back the Character linked list
	 * @param map The MyMap object used to determine the sort order
	 * @param a The left node to start at
	 * @param b The right node to start at
	 * @return A node that holds the merged list after it
	 */
	public static CharNode sortedMerge(MyMap map, CharNode a, CharNode b) 
	    {
	        CharNode result = null;
	        /* Base cases */
	        if (a == null)
	            return b;
	        if (b == null)
	            return a;
	 
	        //If the left is <= the right
	        if (map.test(a.getData(), b.getData()) <= 0) 
	        {
	            result = a;
	            result.setNext(sortedMerge(map, a.getNext(), b));
	        } 
	        //Otherwise right <= the left
	        else
	        {
	            result = b;
	            result.setNext(sortedMerge(map, a, b.getNext()));
	        }
	        return result;
	 
	    }
	
	/**
	 * Splits the list into halves recursively and then merges the list back
	 * @param h The head node of the linked list
	 * @param map The MyMap object used to determine the sort order
	 * @return The node that points to the head of the linked list
	 */
	public static CharNode mergeSort(CharNode h, MyMap map) 
    {
        // Base case : if head is null
        if (h == null || h.getNext() == null)
        {
            return h;
        }
 
        // get the middle of the list
        CharNode middle = getMiddle(h);
        CharNode nextofmiddle = middle.getNext();
 
        // set the next of middle node to null
        middle.setNext(null);
 
        // Apply mergeSort on left list
        CharNode left = mergeSort(h, map);
 
        // Apply mergeSort on right list
        CharNode right = mergeSort(nextofmiddle, map);
 
        // Merge the left and right lists
        CharNode sortedlist = sortedMerge(map, left, right);
        return sortedlist;
    }
 
	/**
	 * Finds the middle of the linked list.  The fast ptr is iterated 2 times for every
	 * 1 time the slow ptr is iterated.  This will leave the slow ptr at the middle
	 * when the fast ptr hits the end of the list
	 * @param h The head node of the linked list
	 * @return The middle node of the linked list
	 */
    public static CharNode getMiddle(CharNode h) 
    {
        //Base case
        if (h == null)
            return h;
        CharNode fastptr = h.getNext();
        CharNode slowptr = h;
         
        // Move fastptr by two and slow ptr by one
        // Finally slowptr will point to middle node
        while (fastptr != null)
        {
            fastptr = fastptr.getNext();
            if(fastptr!=null)
            {
                slowptr = slowptr.getNext();
                fastptr=fastptr.getNext();
            }
        }
        return slowptr;
    }

    /**
     * Merge sort on the given array using the given temp array
     * @param array The source of the data
     * @param temp A play to store temporary data
     * @param map The MyMap object used to determine the sort order
     * @param left The left starting position to sort
     * @param right The right ending position to sort
     */
    public static void mergeSort(Character[] array, Character[] temp, MyMap map, int left, int right){
    	if(left == right){
    		return;
    	}
    	int middle = (left + right) / 2;
    	
    	//Merge the left side
    	mergeSort(array, temp, map, left, middle);
    	//Merge the right side
    	mergeSort(array, temp, map, middle + 1, right);
    	
    	for(int i = 0; i <= right; i++){
    		temp[i] = array[i];
    	}
    	
    	//Merge back
    	int l1 = left;
    	int l2 = middle + 1;
    	
    	for(int curr = left; curr <= right; curr++){
    		if(l1 >= middle + 1){
    			array[curr] = temp[l2++];
    		}
    		else if(l2 > right){
    			array[curr] = temp[l1++];
    		}
    		else if(map.test(temp[l1], temp[l2]) < 0){
    			array[curr] = temp[l1++];
    		}
    		else
    			array[curr] = temp[l2++];
    		
    	}
    }
    
    /**
     * Insertion sort for a Character[] array.  Uses data from MyMap map
     * to determine the sort order.  Begins sorting the array at the value
     * of begin
     * @param array The array to sort
     * @param map The data to determine the sort order
     * @param begin The value to start sorting at
     * @return Pointer to the sorted array
     */
    public static Character[] insertionSort(Character[] array, MyMap map, int begin) {
    	if(array.length == 0 || array.length == 1)
			return array;
    	
    	int lowest = 0;
    	char temp = ' ';
    	for(int i = 0; i < array.length; i++){
    		lowest = i;
    		for(int j = i + 1; j < array.length; j++){
    			//If array[j] is less than the current lowest
    			if(map.test(array[j], array[lowest]) < 0){
    				lowest = j;
    			}
    		}
    		//Swap the values
    		temp = array[i];
    		array[i] = array[lowest];
    		array[lowest] = temp;
    	}
    	
    	return array;
    }
    
    /**
     * Insertion sort for a CharList.  Uses data from MyMap map
     * to determine the sort order.  Begins sorting the list at the value
     * of begin
     * @param list The list to sort
     * @param map The data to determine the sort order
     * @param begin The value to start sorting at
     * @return Pointer to the sorted list
     */
    public static CharNode insertionSort(CharList list, MyMap map, int begin) {
    	//If the list is empty or only has 1 node
    	CharNode head = list.getHead().getNext();
    	if (head == null || head.getNext() == null)
			return head;
    	
    	//Create a node to hold the pointers
		CharNode newHead = new CharNode(head.getData());
		//Points to the second node
		CharNode pointer = head.getNext();
 
		// loop through each element in the list
		while (pointer != null) {
			// insert this element to the new list
			CharNode innerPointer = newHead;
			//Initially points to the third node
			CharNode next = pointer.getNext();
 
			//If the current value is less than the head, make the current value the head
			if (map.test(pointer.getData(), newHead.getData()) <= 0) {
				CharNode oldHead = newHead;
				newHead = pointer;
				newHead.setNext(oldHead);
			} else {
				while (innerPointer.getNext() != null) {
					//If the current position great than current pointer and less than the next pointer, swap
					if (map.test(pointer.getData(),  innerPointer.getData()) > 0 && map.test(pointer.getData(), innerPointer.getNext().getData()) <= 0) {
						CharNode oldNext = innerPointer.getNext();
						innerPointer.setNext(pointer);
						pointer.setNext(oldNext);
					}
					
					//Iterate to the next node
					innerPointer = innerPointer.getNext();
				}
				
				//
				if (innerPointer.getNext() == null && map.test(pointer.getData(), innerPointer.getData()) > 0) {
					innerPointer.setNext(pointer);
					pointer.setNext(null);
				}
			}
 
			// finally
			pointer = next;
		}
 
		return newHead;
    }
    
    
}
