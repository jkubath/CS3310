package edu.wmich.cs3310.hw3.Kubath.application;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

/**
 * MyMap is used to change the order of the alphabet.  This
 * allows sorts to sort as normal, but the values are changed
 * based on the HashMap.
 * @author Jonah Kubath
 *
 */
public class MyMap {
	//Holds the characters and their priority of sort
	HashMap<Character, Integer> map;
	//Used for iterating the list
	Set<?> entrySet;
	Iterator<?> it;
	
	/**
	 * Default constructor for MyMap
	 */
	public MyMap(){
		//Default constructor
	}
	
	/**
	 * Overloaded constructor for MyMap.  Takes the value of 
	 * name.  Sets the first values to be sorted as the characters
	 * in the name.  The final characters are alphabetical skipping
	 * the letters already used in the name.
	 * @param name The name to put at the front of the HashMap
	 */
	public MyMap(String name){
		int count = 0;
		map = new HashMap<Character, Integer>();
		
		
		for(int i = 0; i < name.length(); i++){
			if(map.containsKey(name.charAt(i))){
				//Skip that character as it is already in the map
			}
			else{
				//System.out.print(name.charAt(i));
				map.put(name.charAt(i), count);
				count++;
			}
		}
		
		for(int i = 0; i < 26; i++){
			if(map.containsKey((char) (97 + i))){
				//Skip that character as it is already in the map
			}
			else{
				//System.out.print((char) (97 + i));
				//Add the character to the map
				map.put((char) (97 + i), count);
				count++;
			}
		}
		
		entrySet = map.entrySet();
		it = entrySet.iterator();
		
		
		
	}
	
	/**
	 * Takes in two characters and compares their values in the 
	 * HashMap
	 * @param left Left Value
	 * @param right Right Value
	 * @return Positive for Left greater than Right
	 * Negative for Left less than Right
	 * Zero for Left equal Right
	 */
	public int test(char left, char right){
		if(map.get(left) < map.get(right)){
			return -1;
		}
		else if(map.get(left) > map.get(right)){
			return 1;
		}
		else
			return 0;
	}
	
	
}
