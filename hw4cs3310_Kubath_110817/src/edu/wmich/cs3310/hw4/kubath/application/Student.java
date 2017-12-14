package edu.wmich.cs3310.hw4.kubath.application;

public class Student {
	private String last;
	private String first;
	
	/**
	 * Getter for data
	 * @return String Return the last name data
	 */
	public String getLast() {
		return last;
	}
	
	/**
	 * Setter for data
	 * @param last The last name
	 */
	public void setLast(String last) {
		this.last = last;
	}
	
	/**
	 * Getter for Data
	 * @return String The student node's first name data
	 */
	public String getFirst() {
		return first;
	}
	
	/**
	 * Setter for Data
	 * @param first The data to set as the first name data
	 */
	public void setFirst(String first) {
		this.first = first;
	}
	
	
}
