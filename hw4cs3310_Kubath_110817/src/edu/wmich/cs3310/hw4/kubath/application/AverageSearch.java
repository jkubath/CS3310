package edu.wmich.cs3310.hw4.kubath.application;

public class AverageSearch {
	String first = "";
	String last = "";
	long minHeapBreadth = 0;
	long maxHeapBreadth = 0;
	long minHeapDepth = 0;
	long maxHeapDepth = 0;
	long bst = 0;
	public long getMinHeapBreadth() {
		return minHeapBreadth;
	}
	public void setMinHeapBreadth(long minHeapBreadth) {
		this.minHeapBreadth = minHeapBreadth;
	}
	public long getMaxHeapBreadth() {
		return maxHeapBreadth;
	}
	public void setMaxHeapBreadth(long maxHeapBreadth) {
		this.maxHeapBreadth = maxHeapBreadth;
	}
	public long getMinHeapDepth() {
		return minHeapDepth;
	}
	public void setMinHeapDepth(long minHeapDepth) {
		this.minHeapDepth = minHeapDepth;
	}
	public long getMaxHeapDepth() {
		return maxHeapDepth;
	}
	public void setMaxHeapDepth(long maxHeapDepth) {
		this.maxHeapDepth = maxHeapDepth;
	}
	public long getBst() {
		return bst;
	}
	public void setBst(long bst) {
		this.bst = bst;
	}
	
	public String getFirst() {
		return first;
	}
	public void setFirst(String first) {
		this.first = first;
	}
	public String getLast() {
		return last;
	}
	public void setLast(String last) {
		this.last = last;
	}
}
