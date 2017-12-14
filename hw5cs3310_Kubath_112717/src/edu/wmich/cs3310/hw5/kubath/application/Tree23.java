package edu.wmich.cs3310.hw5.kubath.application;

import java.io.FileWriter;
import java.io.IOException;

public class Tree23<Key extends Comparable<? super Key>> {
	LeafNode<Key> root;
	
	/**
	 * Default constructor
	 */
	public Tree23() {
		//Initialize the tree as empty
		root = null;
	}
	
	/**
	 * Inserts the node into the tree
	 * @param current Starting node
	 * @param newNode New node to insert
	 */
	public void insert(LeafNode<Key> current, LeafNode<Key> newNode) {
		if(newNode.isLeaf) {
		//Tree is empty
		if(root == null) {
			root = newNode;
		}
		//Tree has one node
		else if(root.isLeaf) {
			//Create a new node to be the root
			LeafNode<Key> newRoot = new LeafNode<Key>();
			//The new node is less than the current root node
			if(newNode.getLdata().compareTo(root.getLdata()) < 0) {
				newRoot.setlNode(newNode);
				newRoot.setLkey(newNode.getLdata());
				newRoot.setmNode(root);
				newRoot.setRkey(root.getLdata());
			}
			//New Node is greater than the current root
			else {
				newRoot.setlNode(root);
				newRoot.setLkey(root.getLdata());
				newRoot.setmNode(newNode);
				newRoot.setRkey(newNode.getLdata());
			}
			
			root.setParent(newRoot);
			newNode.setParent(newRoot);
			root = newRoot;
		}
		//We must find the node to insert the new node at
		else {
			LeafNode<Key> parent = search(current, newNode);
			
			int err = add(parent, newNode);
			if(err == 0) {
				split(parent, newNode);
			}
				
		}
		}
		//Insert a node into the tree
		else {
			int err = addNode(current, newNode);
			if(err == 0) {
				split(current, newNode);
			}
			
		}
		
	}
	
	/**
	 * Searches the tree for the correct parent node to insert the new node
	 * @param current The current node
	 * @param newNode The new node to insert into the tree
	 * @return The parent node to insert the new node into
	 */
	public LeafNode<Key> search(LeafNode<Key> current, LeafNode<Key> newNode){
		Key value = newNode.getLdata();
		//Once we hit a leaf node, we cannot traverse any further
		if(current.isLeaf) {
			return current.getParent();
		}
		//Move through the list
		else {
			//New Node is in the left subtree
			if(value.compareTo(current.getLkey()) < 0){
				return search(current.getlNode(), newNode);
			}
			//No middle node
			else if(current.getRkey() == null) {
					return current;
			}
			//New Node is in the middle subtree
			else if(value.compareTo(current.getRkey()) < 0) {
				return search(current.getmNode(), newNode);
			}
			//No right node
			else if(current.getrNode() == null) {
				if(current.getRkey() != null) {
					return search(current.getmNode(), newNode);
				}
				else
					return current;
			}
			//New Node is in the right subtree
			else {
				return search(current.getrNode(), newNode);
			}
		}
	}
	
	/**
	 * Adds a new subtree to the tree
	 * @param parent The parent to add the new subtree into
	 * @param newNode The new subtree
	 * @return 1 on success, 0 on failure
	 */
	public int addNode(LeafNode<Key> parent, LeafNode<Key> newNode) {
//		System.out.println("Left - " + newNode.getLkey());
//		System.out.println("Right - " + newNode.getRkey());
//		if(parent.getParent() != null) {
//			System.out.println("Current L - " + parent.getLkey());
//			System.out.println("Current R - " + parent.getRkey());
//		}
		//We have a spot to add the node
		if(parent.getmNode() == null || parent.getrNode() == null) {
			//Node inserted at middle
			if(parent.getmNode() == null) {
				parent.setmNode(newNode);
				parent.setRkey(newNode.getRkey());
				newNode.setParent(parent);
			}
			//Node inserted at the right
			else {
				parent.setrNode(newNode);
				newNode.setParent(parent);
			}
			return 1;
		}
		//Parent node is full and needs to be split
		else {
			//System.out.println("No open spot");
			return 0;
		}
	}
	
	/**
	 * Add a new individual node to the tree
	 * @param parent The parent node to add the new node into 
	 * @param newNode The new node to add to the tree
	 * @return 1 on success, 0 on failure
	 */
	public int add(LeafNode<Key> parent, LeafNode<Key> newNode) {
		//We have a spot to add the node
		if(parent.getmNode() == null || parent.getrNode() == null) {
			if(parent.getmNode() == null) {
				if(newNode.getLdata().compareTo(parent.getLkey()) < 0) {
					parent.setmNode(parent.getlNode());
					parent.setRkey(parent.getmNode().getLdata());
					parent.setlNode(newNode);
					parent.setLkey(newNode.getLdata());
					newNode.setParent(parent);
				}
				//New Node is greater than the left node
				else
				{
					parent.setmNode(newNode);
					parent.setRkey(newNode.getLdata());
					newNode.setParent(parent);
				}
			}
			//Right node is null
			else {
				//New Node is less than the left node
				if(newNode.getLdata().compareTo(parent.getLkey()) < 0) {
					parent.setrNode(parent.getmNode());
					parent.setmNode(parent.getlNode());
					parent.setRkey(parent.getmNode().getLdata());
					parent.setlNode(newNode);
					parent.setLkey(newNode.getLdata());
					newNode.setParent(parent);
				}
				//New node is less than the middle node
				else if(newNode.getLdata().compareTo(parent.getRkey()) < 0) {
					parent.setrNode(parent.getmNode());
					parent.setmNode(newNode);
					parent.setRkey(newNode.getLdata());
					newNode.setParent(parent);
				}
				else {
					parent.setrNode(newNode);
					newNode.setParent(parent);
				}
			}
			
			return 1;
		}
		//The parent node is full and needs to be split
		else {
			return 0;
		}
	}
	
	/**
	 * Splits the parent node and creates a new node
	 * @param parent The parent node to split
	 * @param newNode The new node to add
	 */
	public void split(LeafNode<Key> parent, LeafNode<Key> newNode) {
		LeafNode<Key> right = new LeafNode<Key>();
		//We are adding a leaf
		if(newNode.isLeaf) {
			//New Node goes to the left
			if(newNode.getLdata().compareTo(parent.getLkey()) < 0) {
				right.setmNode(parent.getrNode());
				right.setRkey(right.getmNode().getLdata());
				right.setlNode(parent.getmNode());
				right.setLkey(parent.getlNode().getLdata());
				parent.setrNode(null);
				parent.setmNode(parent.getlNode());
				parent.setRkey(parent.getmNode().getLdata());
				parent.setlNode(newNode);
				parent.setLkey(newNode.getLdata());
				newNode.setParent(parent);
			}
			//New node goes in the middle
			else if(newNode.getLdata().compareTo(parent.getRkey()) < 0) {
				right.setmNode(parent.getrNode());
				right.setRkey(right.getmNode().getLdata());
				right.setlNode(parent.getmNode());
				right.setLkey(parent.getlNode().getLdata());
				parent.setrNode(null);
				parent.setmNode(newNode);
				parent.setRkey(newNode.getLdata());
				newNode.setParent(parent);
			}
			//New Node is the left node in the right subtree
			else if(newNode.getLdata().compareTo(parent.getrNode().getLdata()) < 0) {
				right.setmNode(parent.getrNode());
				right.setRkey(right.getmNode().getLdata());
				right.setLkey(newNode.getLdata());
				right.setlNode(newNode);
				newNode.setParent(right);
				parent.setrNode(null);
				
			}
			else {
				right.setmNode(newNode);
				right.setRkey(newNode.getLdata());
				right.setlNode(parent.getrNode());
				right.setLkey(parent.getrNode().getLdata());
				newNode.setParent(right);
				parent.setrNode(null);
			}
			
			right.setParent(parent.getParent());
			
			if(parent.getParent() == null) {
				//System.out.println("Making new Root");
				makeNewRoot(parent, right);
			}
			else {
				//Add the new right subtree back
				//System.out.println("Re inserting");
				insert(parent.getParent(), right);
			}
		}
		//The new node is a subtree
		else {
			right.setmNode(newNode);
			right.setlNode(parent.getrNode());
			right.setRkey(newNode.getRkey());
			right.setLkey(parent.getrNode().getRkey());
			newNode.setParent(right);
			right.getlNode().setParent(right);
			parent.setrNode(null);
			
			if(parent == root) {
				//System.out.println("Making new root");
				//root must be split
				makeNewRoot(parent, right);
			}
			else {
				//Add the new right subtree back
				//System.out.println("Adding the right to the parent");
				insert(parent.getParent(), right);
			}
		}
		
		
		
	}
	
	/**
	 * Splits the left, creates and sets a new root node
	 * @param left The left node
	 * @param right The right node
	 */
	public void makeNewRoot(LeafNode<Key> left, LeafNode<Key> right) {
		LeafNode<Key> newRoot = new LeafNode<Key>();
		//LeafNode<Key> right = new LeafNode<Key>();
		newRoot.setlNode(left);
		newRoot.setLkey(left.getRkey());
		newRoot.setmNode(right);
		newRoot.setRkey(right.getRkey());
		left.setParent(newRoot);
		right.setParent(newRoot);
		root = newRoot;
		
	}

	/**
	 * Prints the tree in order
	 * @param current The current node
	 */
	public void printInOrder(LeafNode<Key> current) {
		if(current == null)
			return;
		if(current.isLeaf) {
			System.out.println(current.getLdata());
		}else {
//			System.out.println("Left - " + current.getLkey());
//			System.out.println("Right - " + current.getRkey());
//			System.out.println("Parent - " + current.getParent());
			printInOrder(current.getlNode());
			if(current.getmNode() != null)
				printInOrder(current.getmNode());
			if(current.getrNode() != null)
				printInOrder(current.getrNode());
		}
	}
	
	/**
	 * Search for a given value
	 * @param current The current node during the traversal
	 * @param value The value searching for
	 * @return The value is returned on success, null on failure
	 */
	public Key search(LeafNode<Key> current, Key value) {
		if(current == null)
			return null;
		else if(current.isLeaf) {
			if(current.getLdata().compareTo(value) == 0) {
				return value;
			}
			else
				return null;
		}
		else {
			//System.out.println("Left - " + current.getLkey());
			if(value.compareTo(current.getLkey()) <= 0) {
				return search(current.getlNode(), value);
			}
			else if(value.compareTo(current.getRkey()) <= 0) {
				return search(current.getmNode(), value);
			}
			else
			{
				return search(current.getrNode(), value);
			}
		}
	}
	
	/**
	 * Performs a level by level traversal
	 * @param file The file to write to
	 * @throws IOException Writing to file throws an error
	 */
	public void traversal(FileWriter file) throws IOException {
		Queue23<Key> list = new Queue23<Key>();
		//Add the tree to the list
		//addNodes(list, root);
		list.add(root);
		
		NodeQueue23<Key> front = list.delete();
		while(front != null) {
			//We are at a leaf node - print the data
			if(front.getData().isLeaf) {
				file.write("Leaf - " + front.getData().getLdata() + "\n");
//				System.out.println("Leaf - " + front.getData().getLdata());
			}
			//We are at an internal node
			else {
				file.write("Parent - " + front.getData().getParent() + "\n");
				file.write("Left - " + front.getData().getLkey() + "\n");
				file.write("Right - " + front.getData().getRkey() + "\n");
//				System.out.println("Parent - " + front.getData().getParent());
//				System.out.println("Left - " + front.getData().getLkey());
//				System.out.println("Middle - " + front.getData().getMkey());
//				System.out.println("Right - " + front.getData().getRkey());
				if(front.getData().getlNode() != null)
					list.add(front.getData().getlNode());
				if(front.getData().getmNode() != null)
					list.add(front.getData().getmNode());
				if(front.getData().getrNode() != null)
					list.add(front.getData().getrNode());
			}
			
			//Remove the nodes from front to back
			front = list.delete();
		}
		
		
		
	}
}
