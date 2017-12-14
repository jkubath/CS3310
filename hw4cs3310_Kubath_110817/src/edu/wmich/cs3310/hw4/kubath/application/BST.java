package edu.wmich.cs3310.hw4.kubath.application;

import java.util.HashMap;

public class BST {
	private Node head;
	//Hold the levels and the number of nodes in each level
	private HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
	
	/**
	 * Default constructor
	 */
	public BST() {
		head = new Node("head");
	}
	
	/**
	 * Given the student data, insert the information into the BST tree
	 * @param head The head node to the Binary Search Tree
	 * @param insert The student data to insert
	 * @param level The current level of the tree
	 */
	public void insert(Node head, Student insert, int level) {
		//If the tree is empty
		if(this.head.getData() == null) {
			this.head.setData(insert);
			this.head.setLevel(0);
			this.head.setPosition(1);
			return;
		}
		
		Node lchild = head.getLeftChild();
		Node rchild = head.getRightChild();
		//Add the size as the tree is traversed
		head.setSize(head.getSize() + 1);
		
		//Move left in the tree
		if(insert.getLast().compareTo(head.getData().getLast()) < 0) {
			if(lchild != null) {
				insert(lchild, insert, level + 1);
			}
			else {
				//System.out.println("Inserted Left");
				Node temp = new Node();
				temp.setData(insert);
				temp.setSize(1);
				temp.setLeftChild(null);
				temp.setRightChild(null);
				temp.setParent(head);
				head.setLeftChild(temp);
				temp.setLevel(level);
			}
		}
		//Move right in the tree
		else {
			if(rchild != null) {
				//If you move right, add 1 to the number of nodes on the left
//				if(lchild != null)
//					position = lchild.getPosition() + 1;
				insert(rchild, insert, level + 1);
			}
			else {
				Node temp = new Node();
				temp.setData(insert);
				temp.setSize(1);
				temp.setLeftChild(null);
				temp.setRightChild(null);
				temp.setParent(head);
				head.setRightChild(temp);
				temp.setLevel(level);
			}
		}
		
		//setPosition(this.head, 1);
			
	}
	
	/**
	 * Given the name to delete, search BST until found and then delete 
	 * that node
	 * @param head The head node to the Binary Search Tree
	 * @param name The last name of the Student to delete
	 * @return The deleted student information
	 */
	public Student delete(Node head, String name) {
		{
			//If the tree is empty return
			if(head.getData() == null)
				return head.getData();
			
			//Move to the left of the tree
			if(name.compareTo(head.getData().getLast()) < 0) {
				return delete(head.getLeftChild(), name);
			}
			//Move to the right of the tree
			else if(name.compareTo(head.getData().getLast()) > 0) {
				return delete(head.getRightChild(), name);
			}
			//You have found the node
			else {
				//System.out.println("Found " + head.getData().getLast());
				Student temp = head.getData();
				//Leaf node is to be deleted
				if(head.getLeftChild() == null && head.getRightChild() == null) {
					//System.out.println("Leaf");
					temp = head.getData();
					Node parent = head.getParent();
					if(parent.getLeftChild().getData().getLast().compareTo(name) == 0) {
						parent.setLeftChild(null);
					}
					else
						parent.setParent(null);
					
					BinaryTree.decrement(head);
				}
				//Only one child
				else if((head.getLeftChild() == null && head.getRightChild() != null) 
						|| (head.getLeftChild() != null && head.getRightChild() == null)) {
					//System.out.println("One Child");
					Node child = head.getLeftChild();
					//If the left child is null, then get the right child
					if(child == null) {
						child = head.getRightChild();
					}
					
					temp = head.getData();
					
					Node parent = head.getParent();
					if(parent != null) {
						if(parent.getLeftChild() != null) {
							//The left child matches
							if(parent.getLeftChild().getData().getLast().compareTo(name) == 0) {
								parent.setLeftChild(child);
							}
						}
						else if(parent.getRightChild() != null) {
							//The right child matches
							if(parent.getRightChild().getData().getLast().compareTo(name) == 0) {
								parent.setRightChild(child);
							}
						}
					}
					else {
						//System.out.println("Parent is null");
						if(head.getLeftChild() != null) {
							setHead(head.getLeftChild());
						}
						else
							setHead(head.getRightChild());
					}
					
					BinaryTree.decrement(head);
				}
				//Two children are found
				else {
					//System.out.println("Two Children");
					temp = head.getData();
					
					Node child = head.getRightChild();
					Node next = child.getLeftChild();
					while(next != null) {
						child = next;
						next = next.getLeftChild();
					}
					
					//Decrement the size of the trees
					BinaryTree.decrement(child);
					head.setData(child.getData());
					
					//Move the right side of the child to the left side of the parent
					//Otherwise set it to null to remove the swapped node
					if(child.getRightChild() != null) {
						child.getParent().setLeftChild(child.getRightChild());
					}
					else
						child.getParent().setLeftChild(null);
					
					
				}
				return temp;
				
			}//End of found the node
			
			
	    }
		
	}
	
	/**
	 * Getter for the local variable head
	 * @return Head The head node to the Binary Search Tree
	 */
	public Node getHead() {
		return head;
	}

	/**
	 * Setter for the local variable head
	 * @param head The head node to the Binary Search Tree
	 */
	public void setHead(Node head) {
		this.head = head;
	}
	
	/**
	 * Iterate over the binary tree and set the position
	 * Recursion is used to pass the number of nodes to the left of each node
	 * @param head The head node to the binary search tree
	 */
	public void setPosition(Node head) {
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
			setPosition(head.getLeftChild());
		}
		
		//Go to right child
		if(head.getRightChild() != null) {
			setPosition(head.getRightChild());
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
	
	/**
	 * Iterate the BST and search for the last name
	 * @param head The current node to compare to
	 * @param last The last name
	 * @param first The first name
	 * @return head The head object if it is found, null if the name is not found
	 */
	public Node searchLast(Node head, String last, String first) {
		
		//Name is equal
		if(last.compareToIgnoreCase(head.getData().getLast()) == 0 &&
				first.compareToIgnoreCase(head.getData().getFirst()) == 0) {
				return head;
		}
		
		//Last name is less than current node
		if(last.compareToIgnoreCase(head.getData().getLast()) < 0){
			if(head.getLeftChild() != null)
				return searchLast(head.getLeftChild(), last, first);
		}
		//Last name is greater than current node
		else {
			if(head.getRightChild() != null)
				return searchLast(head.getRightChild(), last, first);
		}
		
		return null;
	}
}
