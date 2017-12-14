package edu.wmich.cs3310.hw4.kubath.application;

public class NodeStack {
	Node data;
	NodeStack next;
	
	public NodeStack() {
		
	}
	
	public NodeStack(Node newNode) {
		data = newNode;
		next = null;
	}
	
	public Node getData() {
		return data;
	}
	public void setData(Node data) {
		this.data = data;
	}
	public NodeStack getNext() {
		return next;
	}
	public void setNext(NodeStack next) {
		this.next = next;
	}
}
