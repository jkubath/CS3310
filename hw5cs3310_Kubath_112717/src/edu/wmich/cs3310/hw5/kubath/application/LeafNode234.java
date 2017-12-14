package edu.wmich.cs3310.hw5.kubath.application;

public class LeafNode234<Key extends Comparable<? super Key>> {
		private Key ldata;
		
		private Key lkey;
		private Key mkey;
		private Key rkey;
		
		private LeafNode234<Key> parent;
		private LeafNode234<Key> lNode;
		private LeafNode234<Key> mNode;
		private LeafNode234<Key> m0Node;
		private LeafNode234<Key> rNode;
		
		boolean isLeaf;
		
		public LeafNode234() {
			ldata = null;
			lkey = rkey = null;
			lNode = mNode = m0Node = rNode = parent = null;
			isLeaf = false;
		}
		
		public LeafNode234(Key value) {
			ldata = value;
			lkey = mkey = rkey = null;
			lNode = mNode = rNode = parent = null;
			isLeaf = false;
		}

		public Key getLdata() {
			return ldata;
		}

		public void setLdata(Key ldata) {
			this.ldata = ldata;
		}

		public Key getLkey() {
			return lkey;
		}

		public void setLkey(Key lkey) {
			this.lkey = lkey;
		}

		public Key getMkey() {
			return mkey;
		}

		public void setMkey(Key mkey) {
			this.mkey = mkey;
		}

		public Key getRkey() {
			return rkey;
		}

		public void setRkey(Key rkey) {
			this.rkey = rkey;
		}

		public LeafNode234<Key> getParent() {
			return parent;
		}

		public void setParent(LeafNode234<Key> parent) {
			this.parent = parent;
		}

		public LeafNode234<Key> getlNode() {
			return lNode;
		}

		public void setlNode(LeafNode234<Key> lNode) {
			this.lNode = lNode;
		}

		public LeafNode234<Key> getmNode() {
			return mNode;
		}

		public void setmNode(LeafNode234<Key> mNode) {
			this.mNode = mNode;
		}

		public LeafNode234<Key> getM0Node() {
			return m0Node;
		}

		public void setM0Node(LeafNode234<Key> m0Node) {
			this.m0Node = m0Node;
		}

		public LeafNode234<Key> getrNode() {
			return rNode;
		}

		public void setrNode(LeafNode234<Key> rNode) {
			this.rNode = rNode;
		}

		public boolean isLeaf() {
			return isLeaf;
		}

		public void setLeaf(boolean isLeaf) {
			this.isLeaf = isLeaf;
		}
	}
