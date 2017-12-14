package edu.wmich.cs3310.hw5.kubath.application;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
	
	static Scanner scan = new Scanner(System.in);

	/**
	 * First code to run
	 * @param args Arguments from the command line
	 * @throws IOException Writing to files fail
	 */
	public static void main(String[] args) throws IOException {
		final int ASCII_LENGTH = 256;
		Character characters[] = new Character[ASCII_LENGTH];
		int decimal[] = new int[ASCII_LENGTH];
		String octal[] = new String[ASCII_LENGTH];
		String hex[] = new String[ASCII_LENGTH];
		
		//Create the data to make the trees
		for(int i = 0; i < ASCII_LENGTH; i++) {
			characters[i] = (char) i;
			decimal[i] = i;
			octal[i] = Integer.toOctalString(i);
			
			hex[i] = Integer.toHexString(i);
			//System.out.println(hex[i]);
		}
		
		
		//******************************************************************************************
		//Create the 2-3 B Tree
		Tree23<Integer> decimalTree = new Tree23<Integer>();
		Arrays.sort(octal);
		Tree23<String> octalTree = new Tree23<String>();
		Arrays.sort(hex);
		Tree23<String> hexTree = new Tree23<String>();
		Arrays.sort(characters);
		Tree23<Character> charTree = new Tree23<Character>();
		for(int i = 0; i < ASCII_LENGTH; i++) {
			//System.out.println("Adding - " + decimal[i]);
			LeafNode<Integer> decimal1 = new LeafNode<Integer>(decimal[i]);
			decimal1.isLeaf = true;
			LeafNode<String> octal1 = new LeafNode<String>(octal[i]);
			octal1.isLeaf = true;
			LeafNode<String> hex1 = new LeafNode<String>(hex[i]);
			hex1.isLeaf = true;
			LeafNode<Character> chara = new LeafNode<Character>(characters[i]);
			chara.isLeaf = true;
			//Add the new Node
			decimalTree.insert(decimalTree.root, decimal1);
			octalTree.insert(octalTree.root, octal1);
			hexTree.insert(hexTree.root, hex1);
			charTree.insert(charTree.root, chara);
			
		}
		//******************************************************************************************

		
		
		//******************************************************************************************
		//Create the 2-3-4 B Tree
		Tree234<Integer> decimalTree1 = new Tree234<Integer>();
		Tree234<String> octalTree1 = new Tree234<String>();
		Tree234<String> hexTree1 = new Tree234<String>();
		Tree234<Character> charTree1 = new Tree234<Character>();
		for(int i = 0; i < ASCII_LENGTH; i++) {
			LeafNode234<Integer> decimal1 = new LeafNode234<Integer>(decimal[i]);
			decimal1.isLeaf = true;
			LeafNode234<String> octal1 = new LeafNode234<String>(octal[i]);
			octal1.isLeaf = true;
			LeafNode234<String> hex1 = new LeafNode234<String>(hex[i]);
			hex1.isLeaf = true;
			LeafNode234<Character> chara = new LeafNode234<Character>(characters[i]);
			chara.isLeaf = true;
			//Add the new Node
			decimalTree1.insert(decimalTree1.root, decimal1);
			octalTree1.insert(octalTree1.root, octal1);
			hexTree1.insert(hexTree1.root, hex1);
			charTree1.insert(charTree1.root, chara);
			
		}
		//******************************************************************************************
		
		//Print the Tree
		System.out.println("Tree Traversal were written to files - Length " + ASCII_LENGTH);
		writeFiles(decimalTree, hexTree, octalTree, charTree, decimalTree1, hexTree1, octalTree1, charTree1);
		
		long total[] = new long[4];
		total = searchBothTrees(decimalTree, octalTree, hexTree, charTree, decimalTree1, octalTree1, hexTree1, charTree1);
		
		writeAverage(total);
	}
	
	/**
	 * Retrieves search value from the user
	 * @return input Input from the user
	 */
	public static String getSearchValue() {
		String input = "";
		boolean good = false;
		
		while(!good) {
			try {
				System.out.println("Enter the search value - Quit to stop");
				input = scan.nextLine();
				if(input.compareTo("") == 0)
					throw new Exception();
				else
					good = true;
			}
			catch(Exception e) {
				System.out.println("Enter a value");
			}
		}
		return input;
		
	}
	
	/**
	 * Searches both B trees for the user's input
	 * @param decimal 2-3 decimal tree
	 * @param octal 2-3 octal tree
	 * @param hex 2-3 hex tree
	 * @param character 2-3 character tree
	 * @param decimal1 2-3-4 decimal tree
	 * @param octal1 2-3-4 octal tree
	 * @param hex1 2-3-4 hex tree
	 * @param character1 2-3-4 character tree
	 * @return total search time and total search count for both trees
	 */
	public static long[] searchBothTrees(Tree23<Integer> decimal, Tree23<String> octal, Tree23<String> hex, Tree23<Character> character, 
			Tree234<Integer> decimal1, Tree234<String> octal1, Tree234<String> hex1, Tree234<Character> character1) {
		String input = getSearchValue();
		//0 = 2-3 Tree total time    1 = 2-3 Tree total count
		//2 = 2-3-4 Tree total time    3 = 2-3-4 Tree total count
		long totalTime[] = new long[4];
		
		while(input.compareTo("Quit") != 0 && input.compareTo("quit") != 0) {
			long temp[];
			Integer search[] = getValue(input);
			//Search the 2-3 Tree
			System.out.println("2-3 Tree search");
			temp = search23(decimal, octal, hex, character, search);
			totalTime[0] += temp[0];
			totalTime[1] += temp[1];
			//Search the 2-3-4 Tree
			System.out.println("\n2-3-4 Tree search");
			temp = search(decimal1, octal1, hex1, character1, search);
			totalTime[2] += temp[0];
			totalTime[3] += temp[1];
			System.out.println("");
			input = getSearchValue();
		}
		
		return totalTime;
	}
	
	//Not used
	public static void searchForValues(Tree234<Integer> decimal, Tree234<String> octal, 
			Tree234<String> hex, Tree234<Character> character) {
		
		
		String input = getSearchValue();
		while(input.compareTo("Quit") != 0 && input.compareTo("quit") != 0) {
			Integer search[] = getValue(input);
			//Search the 2-3-4 Tree
			search(decimal, octal, hex, character, search);
			System.out.println("");
			input = getSearchValue();
		}
		
	}
	
	/**
	 * Traverses the tree searching for the user's input
	 * @param decimal 2-3-4 decimal tree
	 * @param octal 2-3-4 octal tree
	 * @param hex 2-3-4 hex tree
	 * @param character 2-3-4 character tree
	 * @param value Value to search for
	 * @return Time spent searching and searches performed
	 */
	public static long[] search(Tree234<Integer> decimal, Tree234<String> octal, 
			Tree234<String> hex, Tree234<Character> character, Integer[] value ) {
		int decimalVal = 0;
		String hexVal = "";
		String octVal = "";
		char charVal = ' ';
		long startTime = 0;
		long end = 0;
		long total = 0;
		int count = 0;
		
		//Search for the decimal
		if(value[0] != null) {
			System.out.println("Value as Decimal - " + value[0]);
			decimalVal = value[0];
			hexVal = Integer.toHexString(decimalVal);
			octVal = Integer.toOctalString(decimalVal);
			charVal = (char) decimalVal;
			
			if(decimal.search(decimal.root, decimalVal) != null) {
				startTime = System.nanoTime();
				System.out.print("\tHx: " + hex.search(hex.root, hexVal) + " ");
				end = System.nanoTime() - startTime;
				total += end;
				count++;
				System.out.print("Octal: " + octal.search(octal.root, octVal) + " ");
				end = System.nanoTime() - startTime;
				total += end;
				count++;
				System.out.println("Char: " + character.search(character.root, charVal));
				end = System.nanoTime() - startTime;
				total += end;
				count++;
			}
			else
				System.out.println("--Value not found as decimal");
		}
		else {
			//System.out.println("Value did not convert to decimal");
		}
		
		//Search for the hex
		if(value[1] != null) {
			decimalVal = value[1];
			//decimalVal =(int) Long.parseLong("" + value[1], 16);
			hexVal = Integer.toHexString(decimalVal);
			System.out.println("Value as Hex - " + hexVal);
			octVal = Integer.toOctalString(decimalVal);
			charVal = (char) decimalVal;
			
			if(hex.search(hex.root, hexVal) != null) {
				startTime = System.nanoTime();
				System.out.print("\tDecimal: " + decimal.search(decimal.root, decimalVal) + " ");
				end = System.nanoTime() - startTime;
				total += end;
				count++;
				startTime = System.nanoTime();
				System.out.print("Octal: " + octal.search(octal.root, octVal) + " ");
				end = System.nanoTime() - startTime;
				total += end;
				count++;
				startTime = System.nanoTime();
				System.out.println("Char: " + character.search(character.root, charVal));
				end = System.nanoTime() - startTime;
				total += end;
				count++;
			}
			else
				System.out.println("--Value not found as Hex");
		}
		else {
			//System.out.println("Value did not convert to Hex");
		}
		
		//Search for the hex
		if(value[2] != null) {
			decimalVal = value[2];
			//decimalVal =(int) Long.parseLong("" + value[1], 16);
			hexVal = Integer.toHexString(decimalVal);
			octVal = Integer.toOctalString(decimalVal);
			System.out.println("Value as Octal - " + octVal);
			charVal = (char) decimalVal;
			
			if(octal.search(octal.root, octVal) != null) {
				startTime = System.nanoTime();
				System.out.print("\tDecimal: " + decimal.search(decimal.root, decimalVal) + " ");
				end = System.nanoTime() - startTime;
				total += end;
				count++;
				startTime = System.nanoTime();
				System.out.print("Hex: " + hex.search(hex.root, hexVal) + " ");
				end = System.nanoTime() - startTime;
				total += end;
				count++;
				startTime = System.nanoTime();
				System.out.println("Char: " + character.search(character.root, charVal));
				end = System.nanoTime() - startTime;
				total += end;
				count++;
			}
			else
				System.out.println("--Value not found as Octal");
		}
		else {
			//System.out.println("Value did not convert to Octal");
		}
		
		//Search for the hex
		if(value[3] != null) {
			decimalVal = value[3];
			//decimalVal =(int) Long.parseLong("" + value[1], 16);
			hexVal = Integer.toHexString(decimalVal);
			octVal = Integer.toOctalString(decimalVal);
			charVal = (char) decimalVal;
			System.out.println("Value as Char - " + charVal);
			
			if(character.search(character.root, charVal) != null) {
				startTime = System.nanoTime();
				System.out.print("\tDecimal: " + decimal.search(decimal.root, decimalVal) + " ");
				end = System.nanoTime() - startTime;
				total += end;
				count++;
				startTime = System.nanoTime();
				System.out.print("Hex: " + hex.search(hex.root, hexVal) + " ");
				end = System.nanoTime() - startTime;
				total += end;
				count++;
				startTime = System.nanoTime();
				System.out.println("Octal: " + octal.search(octal.root, octVal));
				end = System.nanoTime() - startTime;
				total += end;
				count++;
			}
			else
				System.out.println("--Value not found as Char");
		}
		else {
			//System.out.println("Value did not convert to Char");
		}
				
		long time[] = new long[2];
		time[0] = total;
		time[1] = count;
		
		return time;
	}
	
	//Not used
	public static void searchForValues23(Tree23<Integer> decimal, Tree23<String> octal, 
			Tree23<String> hex, Tree23<Character> character) {
		
		
		String input = getSearchValue();
		while(input.compareTo("Quit") != 0 && input.compareTo("quit") != 0) {
			Integer search[] = getValue(input);
			//Search the 2-3-4 Tree
			search23(decimal, octal, hex, character, search);
			System.out.println("");
			input = getSearchValue();
		}
		
	}
	
	/**
	 * Traverses the tree searching for the user's input
	 * @param decimal 2-3 decimal tree
	 * @param octal 2-3 octal tree
	 * @param hex 2-3 hex tree
	 * @param character 2-3 character tree
	 * @param value Value to search for
	 * @return Time spent searching and searches performed
	 */
	public static long[] search23(Tree23<Integer> decimal, Tree23<String> octal, 
			Tree23<String> hex, Tree23<Character> character, Integer[] value ) {
		int decimalVal = 0;
		String hexVal = "";
		String octVal = "";
		char charVal = ' ';
		long startTime = 0;
		long end = 0;
		long total = 0;
		int count = 0;
		
		//Search for the decimal
		if(value[0] != null) {
			System.out.println("Value as Decimal - " + value[0]);
			decimalVal = value[0];
			hexVal = Integer.toHexString(decimalVal);
			octVal = Integer.toOctalString(decimalVal);
			charVal = (char) decimalVal;
			
			if(decimal.search(decimal.root, decimalVal) != null) {
				startTime = System.nanoTime();
				System.out.print("\tHx: " + hex.search(hex.root, hexVal) + " ");
				end = System.nanoTime() - startTime;
				total += end;
				count++;
				startTime = System.nanoTime();
				System.out.print("Octal: " + octal.search(octal.root, octVal) + " ");
				end = System.nanoTime() - startTime;
				total += end;
				count++;
				startTime = System.nanoTime();
				System.out.println("Char: " + character.search(character.root, charVal));
				end = System.nanoTime() - startTime;
				total += end;
				count++;
			}
			else
				System.out.println("--Value not found as decimal");
		}
		else {
			//System.out.println("Value did not convert to decimal");
		}
		
		//Search for the hex
		if(value[1] != null) {
			decimalVal = value[1];
			//decimalVal =(int) Long.parseLong("" + value[1], 16);
			hexVal = Integer.toHexString(decimalVal);
			System.out.println("Value as Hex - " + hexVal);
			octVal = Integer.toOctalString(decimalVal);
			charVal = (char) decimalVal;
			
			if(hex.search(hex.root, hexVal) != null) {
				startTime = System.nanoTime();
				System.out.print("\tDecimal: " + decimal.search(decimal.root, decimalVal) + " ");
				end = System.nanoTime() - startTime;
				total += end;
				count++;
				startTime = System.nanoTime();
				System.out.print("Octal: " + octal.search(octal.root, octVal) + " ");
				end = System.nanoTime() - startTime;
				total += end;
				count++;
				startTime = System.nanoTime();
				System.out.println("Char: " + character.search(character.root, charVal));
				end = System.nanoTime() - startTime;
				total += end;
				count++;
			}
			else
				System.out.println("--Value not found as Hex");
		}
		else {
			//System.out.println("Value did not convert to Hex");
		}
		
		//Search for the hex
		if(value[2] != null) {
			decimalVal = value[2];
			//decimalVal =(int) Long.parseLong("" + value[1], 16);
			hexVal = Integer.toHexString(decimalVal);
			octVal = Integer.toOctalString(decimalVal);
			System.out.println("Value as Octal - " + octVal);
			charVal = (char) decimalVal;
			
			if(octal.search(octal.root, octVal) != null) {
				startTime = System.nanoTime();
				System.out.print("\tDecimal: " + decimal.search(decimal.root, decimalVal) + " ");
				end = System.nanoTime() - startTime;
				total += end;
				count++;
				startTime = System.nanoTime();
				System.out.print("Hex: " + hex.search(hex.root, hexVal) + " ");
				end = System.nanoTime() - startTime;
				total += end;
				count++;
				startTime = System.nanoTime();
				System.out.println("Char: " + character.search(character.root, charVal));
				end = System.nanoTime() - startTime;
				total += end;
				count++;
			}
			else
				System.out.println("--Value not found as Octal");
		}
		else {
			//System.out.println("Value did not convert to Octal");
		}
		
		//Search for the hex
		if(value[3] != null) {
			decimalVal = value[3];
			//decimalVal =(int) Long.parseLong("" + value[1], 16);
			hexVal = Integer.toHexString(decimalVal);
			octVal = Integer.toOctalString(decimalVal);
			charVal = (char) decimalVal;
			System.out.println("Value as Char - " + charVal);
			
			if(character.search(character.root, charVal) != null) {
				startTime = System.nanoTime();
				System.out.print("\tDecimal: " + decimal.search(decimal.root, decimalVal) + " ");
				end = System.nanoTime() - startTime;
				total += end;
				count++;
				startTime = System.nanoTime();
				System.out.print("Hex: " + hex.search(hex.root, hexVal) + " ");
				end = System.nanoTime() - startTime;
				total += end;
				count++;
				startTime = System.nanoTime();
				System.out.println("Octal: " + octal.search(octal.root, octVal));
				end = System.nanoTime() - startTime;
				total += end;
				count++;
			}
			else
				System.out.println("--Value not found as Char");
		}
		else {
			//System.out.println("Value did not convert to Char");
		}
				
		
		long totalTime[] = new long[2];
		totalTime[0] = total;
		totalTime[1] = count;
		
		return totalTime;
	}
	
	/**
	 * Takes the user's input and converts it to the 4 forms - decimal, hex, octal, character
	 * @param input The user's input
	 * @return An array of the converted values
	 */
	public static Integer[] getValue(String input) {
		Integer values[] = new Integer[4];
		
		if(input.compareTo("NUL") == 0) {
			values[0] = values[1] = values[2] = null;
			values[3] = 0;
			return values;
		}
		else if(input.compareTo("SOH") == 0) {
			values[0] = values[1] = values[2] = null;
			values[3] = 1;
			return values;
		}
		else if(input.compareTo("STX") == 0) {
			values[0] = values[1] = values[2] = null;
			values[3] = 2;
			return values;
		}
		else if(input.compareTo("ETX") == 0) {
			values[0] = values[1] = values[2] = null;
			values[3] = 3;
			return values;
		}
		else if(input.compareTo("EOT") == 0) {
			values[0] = values[1] = values[2] = null;
			values[3] = 4;
			return values;
		}
		else if(input.compareTo("ENQ") == 0) {
			values[0] = values[1] = values[2] = null;
			values[3] = 5;
			return values;
		}
		else if(input.compareTo("ACK") == 0) {
			values[0] = values[1] = values[2] = null;
			values[3] = 6;
			return values;
		}
		else if(input.compareTo("BEL") == 0) {
			values[0] = values[1] = values[2] = null;
			values[3] = 7;
			return values;
		}
		else if(input.compareTo("BS") == 0) {
			values[0] = values[1] = values[2] = null;
			values[3] =(int) Long.parseLong("10", 8) ;
			return values;
		}
		else if(input.compareTo("TAB") == 0) {
			values[0] = values[1] = values[2] = null;
			values[3] =(int) Long.parseLong("11", 8) ;
			return values;
		}else if(input.compareTo("LF") == 0) {
			values[0] = values[1] = values[2] = null;
			values[3] =(int) Long.parseLong("12", 8) ;
			return values;
		}
		else if(input.compareTo("VT") == 0) {
			values[0] = values[1] = values[2] = null;
			values[3] =(int) Long.parseLong("13", 8) ;
			return values;
		}
		else if(input.compareTo("FF") == 0) {
			values[0] = values[1] = values[2] = null;
			values[3] =(int) Long.parseLong("14", 8) ;
			return values;
		}
		else if(input.compareTo("CR") == 0) {
			values[0] = values[1] = values[2] = null;
			values[3] =(int) Long.parseLong("15", 8) ;
			return values;
		}
		else if(input.compareTo("SO") == 0) {
			values[0] = values[1] = values[2] = null;
			values[3] =(int) Long.parseLong("16", 8) ;
			return values;
		}
		else if(input.compareTo("SI") == 0) {
			values[0] = values[1] = values[2] = null;
			values[3] =(int) Long.parseLong("17", 8) ;
			return values;
		}
		else if(input.compareTo("DLE") == 0) {
			values[0] = values[1] = values[2] = null;
			values[3] =(int) Long.parseLong("20", 8) ;
			return values;
		}else if(input.compareTo("DC1") == 0) {
			values[0] = values[1] = values[2] = null;
			values[3] =(int) Long.parseLong("21", 8) ;
			return values;
		}
		else if(input.compareTo("DC2") == 0) {
			values[0] = values[1] = values[2] = null;
			values[3] =(int) Long.parseLong("22", 8) ;
			return values;
		}
		else if(input.compareTo("DC3") == 0) {
			values[0] = values[1] = values[2] = null;
			values[3] =(int) Long.parseLong("23", 8) ;
			return values;
		}
		else if(input.compareTo("DC4") == 0) {
			values[0] = values[1] = values[2] = null;
			values[3] =(int) Long.parseLong("24", 8) ;
			return values;
		}
		else if(input.compareTo("NAK") == 0) {
			values[0] = values[1] = values[2] = null;
			values[3] =(int) Long.parseLong("25", 8) ;
			return values;
		}
		else if(input.compareTo("SYN") == 0) {
			values[0] = values[1] = values[2] = null;
			values[3] =(int) Long.parseLong("26", 8) ;
			return values;
		}
		else if(input.compareTo("ETB") == 0) {
			values[0] = values[1] = values[2] = null;
			values[3] =(int) Long.parseLong("27", 8) ;
			return values;
		}else if(input.compareTo("CAN") == 0) {
			values[0] = values[1] = values[2] = null;
			values[3] =(int) Long.parseLong("30", 8) ;
			return values;
		}
		else if(input.compareTo("EM") == 0) {
			values[0] = values[1] = values[2] = null;
			values[3] =(int) Long.parseLong("31", 8) ;
			return values;
		}
		else if(input.compareTo("SUB") == 0) {
			values[0] = values[1] = values[2] = null;
			values[3] =(int) Long.parseLong("32", 8) ;
			return values;
		}
		else if(input.compareTo("ESC") == 0) {
			values[0] = values[1] = values[2] = null;
			values[3] =(int) Long.parseLong("33", 8) ;
			return values;
		}
		else if(input.compareTo("FS") == 0) {
			values[0] = values[1] = values[2] = null;
			values[3] =(int) Long.parseLong("34", 8) ;
			return values;
		}else if(input.compareTo("GS") == 0) {
			values[0] = values[1] = values[2] = null;
			values[3] =(int) Long.parseLong("35", 8) ;
			return values;
		}
		else if(input.compareTo("RS") == 0) {
			values[0] = values[1] = values[2] = null;
			values[3] =(int) Long.parseLong("36", 8) ;
			return values;
		}
		else if(input.compareTo("US") == 0) {
			values[0] = values[1] = values[2] = null;
			values[3] =(int) Long.parseLong("37", 8) ;
			return values;
		}
		else if(input.compareTo("Space") == 0) {
			values[0] = values[1] = values[2] = null;
			values[3] =(int) Long.parseLong("40", 8) ;
			return values;
		}
		else if(input.compareTo("DEL") == 0) {
			values[0] = values[1] = values[2] = null;
			values[3] =(int) Long.parseLong("177", 8) ;
			return values;
		}
		
		
		
		//Convert to decimal
		try {
			values[0] = Integer.parseInt(input);
		}
		catch(NumberFormatException e) {
			values[0] = null;
		}
		
		//Convert from hex
		try {
			values[1] =(int) Long.parseLong(input, 16);
		}
		catch(NumberFormatException e) {
			values[1] = null;
		}
				
		//Convert from octal
		try {
			values[2] =(int) Long.parseLong(input, 8);
		}
		catch(NumberFormatException e) {
			values[2] = null;
		}
		
		
		
		
		//Convert from character
		try {
			if(input.length() != 1)
				throw new NumberFormatException();
			char one = input.charAt(0);
			values[3] = (int) one;
		}
		catch(NumberFormatException e) {
			values[3] = null;
		}
		
	
		return values;
	}
	
	/**
	 * Writes the level traversals to files
	 * @param decimal 2-3 decimal tree
	 * @param octal 2-3 octal tree
	 * @param hex 2-3 hex tree
	 * @param character 2-3 character tree
	 * @param decimal1 2-3-4 decimal tree
	 * @param octal1 2-3-4 octal tree
	 * @param hex1 2-3-4 hex tree
	 * @param character1 2-3-4 character tree
	 * @throws IOException Error writing to files
	 */
	public static void writeFiles(Tree23<Integer> decimal, Tree23<String> octal, Tree23<String> hex, Tree23<Character> character, 
			Tree234<Integer> decimal1, Tree234<String> octal1, Tree234<String> hex1, Tree234<Character> character1) throws IOException {
		File newFile = new File("decimal23.txt");
		File newFile1 = new File("octal23.txt");
		File newFile2 = new File("hex23.txt");
		File newFile3 = new File("char23.txt");
		File newFile10 = new File("decimal234.txt");
		File newFile11 = new File("octal234.txt");
		File newFile12 = new File("hex234.txt");
		File newFile13 = new File("char234.txt");
		FileWriter writeFile = new FileWriter(newFile);
		FileWriter writeFile1 = new FileWriter(newFile2);
		FileWriter writeFile2 = new FileWriter(newFile1);
		FileWriter writeFile3 = new FileWriter(newFile3);
		FileWriter writeFile10 = new FileWriter(newFile10);
		FileWriter writeFile11 = new FileWriter(newFile12);
		FileWriter writeFile12 = new FileWriter(newFile11);
		FileWriter writeFile13 = new FileWriter(newFile13);
		
		//Write the 2-3-4 traversals
		writeFile.write("Decimal 2-3 Tree Traversal" + "\n");
		decimal.traversal(writeFile);
		writeFile1.write("Octal 2-3 Tree Traversal" + "\n");
		octal.traversal(writeFile1);
		writeFile2.write("Hex 2-3 Tree Traversal" + "\n");
		hex.traversal(writeFile2);
		writeFile3.write("Characters 2-3 Tree Traversal" + "\n");
		character.traversal(writeFile3);
				
				
		//Write the 2-3-4 traversals
		writeFile10.write("Decimal 2-3-4 Tree Traversal" + "\n");
		decimal1.traversal(writeFile10);
		writeFile11.write("Octal 2-3-4 Tree Traversal" + "\n");
		octal1.traversal(writeFile11);
		writeFile12.write("Hex 2-3-4 Tree Traversal" + "\n");
		hex1.traversal(writeFile12);
		writeFile13.write("Characters 2-3-4 Tree Traversal" + "\n");
		character1.traversal(writeFile13);
		
		
		
		
		
		
		writeFile.close();
		writeFile1.close();
		writeFile2.close();
		writeFile3.close();
		writeFile10.close();
		writeFile11.close();
		writeFile12.close();
		writeFile13.close();
	}
	
	/**
	 * Prints the average search times to the console
	 * @param total Array of total search time and number of searches performed by each tree
	 */
	public static void writeAverage(long[] total) {
		//2-3 Tree search time
		System.out.println("\n\n2-3 Tree Average Search Time - Runs " + total[1]);
		if(total[1] == 0)
			System.out.println("\t 0 searches");
		else
			System.out.println("\t" + (total[0] / total[1]) + " ns");
		
		//2-3-4 Tree search time
		System.out.println("2-3-4 Tree Average Search Time - Runs " + total[3]);
		if(total[1] == 0)
			System.out.println("\t 0 searches");
		else
			System.out.println("\t" + (total[2] / total[3]) + " ns");
		
		
		
		
		
		
	}

}
