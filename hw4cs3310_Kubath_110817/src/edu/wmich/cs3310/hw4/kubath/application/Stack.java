package edu.wmich.cs3310.hw4.kubath.application;

public class Stack {
	private NodeStack head;
	private NodeStack tail;
	
	public Stack() {
		
	}
	
	public Stack(NodeStack node) {
		head = node;
		tail = head;
	}
	
	public void insert(Node newNode) {
		NodeStack stack = new NodeStack(newNode);
		//List was empty
		if(head == null) {
			head = stack;
			tail = stack;
		}
		else {
			tail.setNext(stack);
			tail = stack;
		}
	}
	
	public NodeStack delete() {
		if(head == null) {
			return null;
		}
		else {
			NodeStack temp = head;
			head = head.getNext();
			return temp;
		}
		
	}
	
	public NodeStack getHead() {
		return head;
	}
	public void setHead(NodeStack head) {
		this.head = head;
	}
	public NodeStack getTail() {
		return tail;
	}
	public void setTail(NodeStack tail) {
		this.tail = tail;
	}
	
}
