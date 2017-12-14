package edu.wmich.cs3310.hw5.kubath.application;

public class LeafNode<Key extends Comparable<? super Key>>{
	private Key ldata;
	//private LeafNode<Key> rdata;
	//private LeafNode<Key> mdata;
	
	private Key lkey;
	private Key rkey;
	
	private LeafNode<Key> parent;
	private LeafNode<Key> lNode;
	private LeafNode<Key> mNode;
	private LeafNode<Key> rNode;
	
	boolean isLeaf;
	
	/**
	 * Default construct
	 */
	public LeafNode() {
		ldata = null;//rdata = mdata = null;
		lkey = rkey = null;
		lNode = mNode = rNode = parent = null;
		isLeaf = false;
	}
	
	/**
	 * Overloaded construct to set data value
	 * @param value data to set in the node
	 */
	public LeafNode(Key value) {
		ldata = value;
		lkey = rkey = null;
		lNode = mNode = rNode = parent = null;
		isLeaf = false;
	}

	/**
	 * Get ldata
	 * @return ldata
	 */
	public Key getLdata() {
		return ldata;
	}

	/**
	 * set ldata
	 * @param ldata node data
	 */
	public void setLdata(Key ldata) {
		this.ldata = ldata;
	}

	/**
	 * get lkey
	 * @return lkey
	 */
	public Key getLkey() {
		return lkey;
	}

	/**
	 * set lkey
	 * @param lkey left key
	 */
	public void setLkey(Key lkey) {
		this.lkey = lkey;
	}

	/**
	 * get r key
	 * @return rkey
	 */
	public Key getRkey() {
		return rkey;
	}

	/**
	 * set r key
	 * @param rkey right key
	 */
	public void setRkey(Key rkey) {
		this.rkey = rkey;
	}

	/**
	 * get parent
	 * @return parent
	 */
	public LeafNode<Key> getParent() {
		return parent;
	}

	/**
	 * set parent
	 * @param parent parent node
	 */
	public void setParent(LeafNode<Key> parent) {
		this.parent = parent;
	}

	/**
	 * get l node
	 * @return lnode
	 */
	public LeafNode<Key> getlNode() {
		return lNode;
	}

	/**
	 * set l node
	 * @param lNode left node
	 */
	public void setlNode(LeafNode<Key> lNode) {
		this.lNode = lNode;
	}

	/**
	 * get m node
	 * @return mnode
	 */
	public LeafNode<Key> getmNode() {
		return mNode;
	}

	/**
	 * set m node
	 * @param mNode middle node
	 */
	public void setmNode(LeafNode<Key> mNode) {
		this.mNode = mNode;
	}

	/**
	 * get r node
	 * @return rnode
	 */
	public LeafNode<Key> getrNode() {
		return rNode;
	}

	/**
	 * set r node
	 * @param rNode right node
	 */
	public void setrNode(LeafNode<Key> rNode) {
		this.rNode = rNode;
	}

	/**
	 * is the node a leaf node
	 * @return true if it is a leaf node, else false
	 */
	public boolean isLeaf() {
		return isLeaf;
	}

	/**
	 * set boolean
	 * @param isLeaf true if it is a leaf node
	 */
	public void setLeaf(boolean isLeaf) {
		this.isLeaf = isLeaf;
	}
	
}
