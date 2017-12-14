package edu.wmich.cs3310.hw4.kubath.application;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ReadFile {
	static Scanner scan;
	static String fileName = "namelist.txt";
	
	/**
	 * Using the saved scanner to read through the file.
	 * @param headNode The root node to save data to
	 * @param list The ArrayList to add the student nodes to
	 */
	public static void readFile(Node headNode, ArrayList<Student> list) {
		int level = 0;
		int position = 1;
		//Set the head node data
		if(scan.hasNext()) {
			Student stud = new Student();
			stud.setLast(scan.next());
			stud.setFirst(scan.next());
			if(scan.hasNext())
				scan.nextLine();
			//Add to the list to hold the data in read order
			list.add(stud);
			headNode.setData(stud);
			headNode.setSize(1);
			headNode.setLevel(level);
			headNode.setPosition(position);
			
		}
		position++;
		//Read the remaining data
		while(scan.hasNext()) {
			if(position >= (Math.pow(2, level))) {
				level++;
				position = 0;
			}
			position++;
			
			Student temp = new Student();
			temp.setLast(scan.next());
			temp.setFirst(scan.next());
			//System.out.println(temp.getLast());
			if(scan.hasNext())
				scan.nextLine();
			Node newNode = new Node();
			//Add to the list
			list.add(temp);
			//Add to the min heap
			newNode.setData(temp);
			newNode.setLevel(level);
			//newNode.setSize(1);
			BinaryTree.insert(headNode, newNode);
		}
		
		scan.close();
	}
	
	/**
	 * Checks for the default file of namelist.txt.  If this file is not found, the user is
	 * prompted to input a new file.
	 */
	public static void getFile() {
		boolean goodFile = false;
		while(!goodFile) {
			try {
				File inFile = new File(fileName);
				scan = new Scanner(inFile);
				goodFile = true;
			}
			catch(FileNotFoundException e) {
				System.out.println("File Not Found");
				System.out.println("Enter a new file");
				scan = new Scanner(System.in);
				fileName = scan.nextLine();
				fileName = fileName.trim();
				goodFile = false;
			};
		
		}
		
		
	}
	
}
