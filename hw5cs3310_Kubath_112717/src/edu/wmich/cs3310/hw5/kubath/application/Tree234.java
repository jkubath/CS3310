package edu.wmich.cs3310.hw5.kubath.application;

import java.io.FileWriter;
import java.io.IOException;

public class Tree234<Key extends Comparable<? super Key>> {
		LeafNode234<Key> root;
		
		/**
		 * Default constructor
		 */
		public Tree234() {
			//Initialize the tree as empty
			root = null;
		}
		
		/**
		 * Inserts a new node into the tree
		 * @param current The current node to try and insert into
		 * @param newNode The new node to insert into the tree
		 */
		public void insert(LeafNode234<Key> current, LeafNode234<Key> newNode) {
			if(newNode.isLeaf) {
			//Tree is empty
			if(root == null) {
				root = newNode;
			}
			//Tree has one node
			else if(root.isLeaf) {
				//Create a new node to be the root
				LeafNode234<Key> newRoot = new LeafNode234<Key>();
				//The new node is less than the current root node
				if(newNode.getLdata().compareTo(root.getLdata()) < 0) {
					newRoot.setlNode(newNode);
					newRoot.setLkey(newNode.getLdata());
					newRoot.setM0Node(root);
					newRoot.setMkey(root.getLdata());
				}
				//New Node is greater than the current root
				else {
					newRoot.setlNode(root);
					newRoot.setLkey(root.getLdata());
					newRoot.setM0Node(newNode);
					newRoot.setMkey(newNode.getLdata());
				}
				
				root.setParent(newRoot);
				newNode.setParent(newRoot);
				root = newRoot;
			}
			//We must find the node to insert the new node at
			else if(newNode.isLeaf) {
				LeafNode234<Key> parent = search(current, newNode);
				
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
		 * Search for the parent node to insert the new node into
		 * @param current The current node
		 * @param newNode The new node to insert into the tree
		 * @return The parent to insert the new node into
		 */
		public LeafNode234<Key> search(LeafNode234<Key> current, LeafNode234<Key> newNode){
			//Once we hit a leaf node, we cannot traverse any further
			if(current.isLeaf) {
				return current.getParent();
			}
			//Move through the list
			else {
				Key value = newNode.getLdata();
				
				if(value.compareTo(current.getLkey()) < 0){
					return search(current.getlNode(), newNode);
				}
				//The middle left node is not completely full
				else if(current.getMkey() == null && current.getM0Node() != null) {
					return search(current.getM0Node(), newNode);
				}
				//No middle left node
				else if(current.getMkey() == null) {
					return current;
				}
				//New Node is in the middle node on the left sub tree
				else if(value.compareTo(current.getMkey()) < 0) {
					return search(current.getM0Node(), newNode);
				}
				//Middle node on the left is empty
				else if(current.getRkey() == null && current.getmNode() != null) {
					return search(current.getmNode(), newNode);
				}
				//No middle right node
				else if(current.getRkey() == null) {
						return current;
				}
				//New Node is in the middle right subtree
				else if(value.compareTo(current.getRkey()) < 0) {
					return search(current.getmNode(), newNode);
				}
				//No right node
				else if(current.getrNode() == null) {
					return current;
				}
				//New Node is in the right subtree
				else {
					return search(current.getrNode(), newNode);
				}
			}
		}
		
		/**
		 * Add a subtree to the tree
		 * @param parent The parent node to add the subtree to
		 * @param newNode The subtree to add
		 * @return 1 on success, 0 on failure
		 */
		public int addNode(LeafNode234<Key> parent, LeafNode234<Key> newNode) {
			//We have a spot to add the node
			if(parent.getM0Node() == null || parent.getmNode() == null || parent.getrNode() == null) {
				Key lowest = newNode.getMkey();
				
				if(newNode.getRkey() == null) {
					if(parent.getM0Node() == null) {
						if(lowest != null && lowest.compareTo(parent.getLkey()) < 0)
						{
							parent.setM0Node(parent.getlNode());
							parent.setlNode(newNode);
							newNode.setParent(parent);
						}
						else {
							parent.setM0Node(newNode);
							newNode.setParent(parent);
						}
						
					}
					//Node inserted at middle
					else if(parent.getmNode() == null) {
						if(lowest != null && lowest.compareTo(parent.getLkey()) < 0) {
							parent.setmNode(parent.getM0Node());
							parent.setM0Node(parent.getlNode());
							parent.setlNode(newNode);
							newNode.setParent(parent);
						}
						else if(lowest != null && lowest.compareTo(parent.getMkey()) < 0) {
							parent.setmNode(parent.getM0Node());
							parent.setM0Node(newNode);
							newNode.setParent(parent);
						}
						else {
							parent.setmNode(newNode);
							newNode.setParent(parent);
						}
					}
					//Node inserted at the right
					else {
						if(lowest != null && lowest.compareTo(parent.getLkey()) < 0) {
							parent.setrNode(parent.getmNode());
							parent.setmNode(parent.getM0Node());
							parent.setM0Node(parent.getlNode());
							parent.setlNode(newNode);
							newNode.setParent(parent);
						}
						else if(lowest != null && lowest.compareTo(parent.getMkey()) < 0) {
							parent.setrNode(parent.getmNode());
							parent.setmNode(parent.getM0Node());
							parent.setM0Node(newNode);
							newNode.setParent(parent);
						}
						else if(lowest != null && lowest.compareTo(parent.getRkey()) < 0){
							parent.setrNode(parent.getmNode());
							parent.setmNode(newNode);
							newNode.setParent(parent);
						}
						else {
							parent.setrNode(newNode);
							newNode.setParent(parent);
						}
					}
					
					
				}
				else {
					if(parent.getM0Node() == null) {
						parent.setM0Node(newNode);
						parent.setMkey(newNode.getRkey());
						newNode.setParent(parent);
					}
					//Node inserted at middle
					else if(parent.getmNode() == null) {
						//New node is less than the left node
						if(newNode.getRkey().compareTo(parent.getLkey()) < 0) {
							parent.setmNode(parent.getM0Node());
							parent.setRkey(parent.getMkey());
							parent.setM0Node(parent.getlNode());
							parent.setMkey(parent.getLkey());
							parent.setlNode(newNode);
							parent.setLkey(newNode.getRkey());
							newNode.setParent(parent);
						}
						else if(newNode.getRkey().compareTo(parent.getMkey()) < 0) {
							parent.setmNode(parent.getM0Node());
							parent.setRkey(parent.getMkey());
							parent.setM0Node(newNode);
							parent.setMkey(newNode.getRkey());
							newNode.setParent(parent);
						}
						else {
							parent.setmNode(newNode);
							parent.setRkey(newNode.getRkey());
							newNode.setParent(parent);
						}
					}
					//Node inserted at the right
					else {
						parent.setrNode(newNode);
						newNode.setParent(parent);
					}
					
					
				}
				
				setKeys(parent);
				return 1;
			}
			//Parent node is full and needs to be split
			else {
				return 0;
			}
		}
		
		/**
		 * Add a new individual node to the tree
		 * @param parent The parent node to add the new node to
		 * @param newNode The new node to add to the tree
		 * @return 1 on success, 0 on failure
		 */
		public int add(LeafNode234<Key> parent, LeafNode234<Key> newNode) {
			//We have a spot to add the node
			if(parent.getM0Node() == null || parent.getmNode() == null || parent.getrNode() == null) {
				if(parent.getM0Node() == null) {
					//New node is less than the left node
					if(newNode.getLdata().compareTo(parent.getLkey()) < 0) {
						parent.setM0Node(parent.getlNode());
						parent.setMkey(parent.getM0Node().getLdata());
						parent.setlNode(newNode);
						parent.setLkey(newNode.getLdata());
						newNode.setParent(parent);
					}
					else {
						parent.setM0Node(newNode);
						parent.setMkey(newNode.getLdata());
						newNode.setParent(parent);
					}
				}
				//Middle right node is null
				else if(parent.getmNode() == null) {
					if(newNode.getLdata().compareTo(parent.getLkey()) < 0) {
						parent.setmNode(parent.getM0Node());
						parent.setRkey(parent.getMkey());
						parent.setM0Node(parent.getlNode());
						parent.setMkey(parent.getLkey());
						parent.setlNode(newNode);
						parent.setLkey(newNode.getLdata());
						newNode.setParent(parent);
					}
					else if(newNode.getLdata().compareTo(parent.getMkey()) < 0) {
						parent.setmNode(parent.getM0Node());
						parent.setRkey(parent.getMkey());
						parent.setM0Node(newNode);
						parent.setMkey(newNode.getLdata());
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
						//Move the three current nodes to the right
						parent.setrNode(parent.getmNode());
						parent.setmNode(parent.getM0Node());
						parent.setM0Node(parent.getlNode());
						parent.setlNode(newNode);
						parent.setRkey(parent.getmNode().getLdata());
						parent.setMkey(parent.getM0Node().getLdata());
						parent.setLkey(newNode.getLdata());
						newNode.setParent(parent);
					}
					else if(newNode.getLdata().compareTo(parent.getMkey()) < 0) {
						//Move the right two nodes to the right
						parent.setrNode(parent.getmNode());
						parent.setmNode(parent.getM0Node());
						parent.setM0Node(newNode);
						parent.setRkey(parent.getmNode().getLdata());
						parent.setMkey(parent.getM0Node().getLdata());
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
		 * Splits the parent node and returns a new node
		 * @param parent The parent node to split
		 * @param newNode The new node to add
		 */
		public void split(LeafNode234<Key> parent, LeafNode234<Key> newNode) {
			LeafNode234<Key> right = new LeafNode234<Key>();
			//We are adding a leaf
			if(newNode.isLeaf) {
				//New Node goes to the left
				if(newNode.getLdata().compareTo(parent.getLkey()) < 0) {
					//Move the right node
					right.setM0Node(parent.getrNode());
					right.setMkey(right.getM0Node().getLdata());
					right.getM0Node().setParent(right);
					//Move the middle right node
					right.setlNode(parent.getmNode());
					right.setLkey(right.getlNode().getLdata());
					right.getlNode().setParent(right);
					//Move the remaining over
					parent.setrNode(null);
					parent.setmNode(parent.getM0Node());
					parent.setRkey(parent.getmNode().getLdata());
					parent.setM0Node(parent.getlNode());
					parent.setMkey(parent.getM0Node().getLdata());
					parent.setlNode(newNode);
					parent.setLkey(newNode.getLdata());
					newNode.setParent(parent);
				}
				//New node goes in the middle left position
				else if(newNode.getLdata().compareTo(parent.getMkey()) < 0) {
					//Right Node
					right.setM0Node(parent.getrNode());
					right.setMkey(right.getM0Node().getLdata());
					right.getM0Node().setParent(right);
					//Middle right node
					right.setlNode(parent.getmNode());
					right.setLkey(right.getlNode().getLdata());
					right.getlNode().setParent(right);
					//Left subtree
					parent.setrNode(null);
					parent.setmNode(parent.getM0Node());
					parent.setRkey(parent.getmNode().getLdata());
					parent.setM0Node(newNode);
					parent.setMkey(newNode.getLdata());
					newNode.setParent(parent);
				}
				//New node goes in the middle right position
				else if(newNode.getLdata().compareTo(parent.getRkey()) < 0) {
					//Right node
					right.setM0Node(parent.getrNode());
					right.setMkey(right.getM0Node().getLdata());
					right.getM0Node().setParent(right);
					//Let Node inserted
					right.setlNode(parent.getmNode());
					right.setLkey(right.getlNode().getLdata());
					right.getlNode().setParent(right);
					//Right subtree
					parent.setrNode(null);
					parent.setmNode(newNode);
					parent.setRkey(newNode.getLdata());
					newNode.setParent(parent);
				}
				//New Node is the left node in the right subtree
				else if(newNode.getLdata().compareTo(parent.getrNode().getLdata()) < 0) {
					//Right node
					right.setM0Node(parent.getrNode());
					right.setMkey(right.getM0Node().getLdata());
					right.getM0Node().setParent(right);
					//New Node inserted
					right.setlNode(newNode);
					right.setLkey(newNode.getLdata());
					newNode.setParent(right);
					parent.setrNode(null);
					
				}
				else {
					right.setM0Node(newNode);
					right.setMkey(newNode.getLdata());
					right.setlNode(parent.getrNode());
					right.setLkey(parent.getrNode().getLdata());
					right.getlNode().setParent(right);
					newNode.setParent(right);
					parent.setrNode(null);
				}
				
				right.setParent(parent.getParent());
				setKeys(parent.getParent());
				
				if(parent.getParent() == null) {
					makeNewRoot(parent, right);
				}
				else {
					//Add the new right subtree back
					insert(parent.getParent(), right);
				}
			}
			//The new node is a subtree
			else {
				right.setM0Node(newNode);
				right.setlNode(parent.getrNode());
				right.setMkey(newNode.getRkey());
				right.setLkey(right.getlNode().getRkey());
				newNode.setParent(right);
				right.getlNode().setParent(right);
				right.setParent(parent.getParent());
				parent.setrNode(null);
				
				setKeys(parent);
				
				if(parent == root) {
					//root must be split
					makeNewRoot(parent, right);
				}
				else {
					//Add the new right subtree back
					insert(parent.getParent(), right);
				}
			}
			
			
			
		}
		
		/**
		 * Takes in two nodes and splits the current root.  A new root is made and set
		 * @param left The current root
		 * @param right The right subtree
		 */
		public void makeNewRoot(LeafNode234<Key> left, LeafNode234<Key> right) {
			LeafNode234<Key> newRoot = new LeafNode234<Key>();
			newRoot.setlNode(left);
			newRoot.setLkey(left.getRkey());
			newRoot.setM0Node(right);
			newRoot.setMkey(right.getRkey());
			left.setParent(newRoot);
			right.setParent(newRoot);
			root = newRoot;
			
			
		}
		
		/**
		 * Sets the keys of the parent node
		 * @param parent The parent node
		 */
		public void setKeys(LeafNode234<Key> parent) {
			if(parent == null)
				return;
			
			if(parent.getlNode() != null)
				parent.setLkey(parent.getlNode().getRkey());
			else 
				parent.setLkey(null);
			if(parent.getM0Node() != null) 
				parent.setMkey(parent.getM0Node().getRkey());
			else
				parent.setMkey(null);
			if(parent.getmNode() != null)
				parent.setRkey(parent.getmNode().getRkey());
			else
				parent.setRkey(null);
		}

		/**
		 * Prints the tree in order, if test is found the parents are printed up to the root
		 * @param current The current node
		 * @param test The value to cause the upward printing
		 */
		public void printInOrder(LeafNode234<Key> current, Key test) {
			if(current == null)
				return;
			if(current.isLeaf) {
				System.out.println(current.getLdata());
				if(current.getLdata().compareTo(test) == 0)
					printUp(current);
			}else {
//				System.out.println("Left - " + current.getLkey());
//				System.out.println("Middle - " + current.getMkey());
//				System.out.println("Right - " + current.getRkey());
//				System.out.println("Parent - " + current.getParent());
				printInOrder(current.getlNode(),test);
				if(current.getM0Node() != null)
					printInOrder(current.getM0Node(),test);
				if(current.getmNode() != null)
					printInOrder(current.getmNode(),test);
				if(current.getrNode() != null)
					printInOrder(current.getrNode(),test);
			}
		}
		
		/**
		 * starts and current and prints the parent nodes until root is found
		 * @param current The current node
		 */
		public void printUp(LeafNode234<Key> current) {
			if(current == null)
				return;
			else
			{
				System.out.println("current - " + current.getLkey());
				printUp(current.getParent());
			}
		}
		
		/**
		 * Searches for the given value
		 * @param current The current node
		 * @param value The value to search for
		 * @return The value is retured on success, null on failure
		 */
		public Key search(LeafNode234<Key> current, Key value) {
			if(current == null)
				return null;
			else if(current.isLeaf) {
				if(current.getLdata().compareTo(value) == 0)
					return value;
				else
					return null;
			}
			else {
				//System.out.println("Searching - " + current.getLkey());
				if(current.getLkey() == null && current.getlNode() != null) {
					//System.out.println("Left");
					return search(current.getlNode(), value);
				}
				if(value.compareTo(current.getLkey()) <= 0) {
					//System.out.println("Moving left");
					return search(current.getlNode(), value);
				}
				else if(current.getMkey() == null) {
					//System.out.println("Middle left");
					return search(current.getM0Node(), value);
				}
				else if(value.compareTo(current.getMkey()) <= 0) {
					//System.out.println("Middle left");
					return search(current.getM0Node(), value);
				}
				else if(current.getRkey() == null) {
					//System.out.println("Middle right");
					return search(current.getmNode(), value);
				}
				else if(value.compareTo(current.getRkey()) <= 0) {
					//System.out.println("Moving right1");
					return search(current.getmNode(), value);
				}
				else {
					//System.out.println("Moving right");
					return search(current.getrNode(), value);
				}
			}
		}
		
		/**
		 * Level by level traversal of the tree
		 * @param file The file to write the traversal to
		 * @throws IOException Error writing to the file
		 */
		public void traversal(FileWriter file) throws IOException {
			Queue234<Key> list = new Queue234<Key>();
			//Add the tree to the list
			//addNodes(list, root);
			list.add(root);
			
			NodeQueue234<Key> front = list.delete();
			while(front != null) {
				//We are at a leaf node - print the data
				if(front.getData().isLeaf) {
					file.write("Leaf - " + front.getData().getLdata() + "\n");
//					System.out.println("Leaf - " + front.getData().getLdata());
				}
				//We are at an internal node
				else {
					file.write("Parent - " + front.getData().getParent() + "\n");
					file.write("Left - " + front.getData().getLkey() + "\n");
					file.write("Middle - " + front.getData().getMkey() + "\n");
					file.write("Right - " + front.getData().getRkey() + "\n");
//					System.out.println("Parent - " + front.getData().getParent());
//					System.out.println("Left - " + front.getData().getLkey());
//					System.out.println("Middle - " + front.getData().getMkey());
//					System.out.println("Right - " + front.getData().getRkey());
					if(front.getData().getlNode() != null)
						list.add(front.getData().getlNode());
					if(front.getData().getM0Node() != null)
						list.add(front.getData().getM0Node());
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
