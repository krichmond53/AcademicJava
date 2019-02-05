/*
 * Kevin Richmond
 * CMSC401 - Advanced Algorithms and Data Structures
 * Project 3 - Word Mining
 * Required files: cmsc401.java, BinaryTree.java, Node.java
 * 
 * Description - This program will take integer input to create the size of an array S[], where the values represent the last position of
 * 	a sentence where the first position of the sentence starts with 1.  A separate integer input will create the size of an array W[], where the
 * 	values represent the location of key words that are to be found.  The output of the program will identify how many sentences contain at least
 * 	1 key word, and print out the start and ending positions of that sentence.
 */
	public class Node {
			
		private int start;					// Start position of sentence
		private int end;					// End position of sentence
		private Node leftC;					// Value of left child
		private Node rightC;				// Value of right child
		
		/**
		 * Default constructor
		 */	
		public Node(){
			setStart(0);
			setEnd(0);
			setLeftC(null);
			setRightC(null);
		}
		
		/**
		 * @param start
		 * @param end
		 * @param leftC
		 * @param rightC
		 */	
		public Node(int s, int e){
			setStart(s);
			setEnd(e);
			setLeftC(null);
			setRightC(null);
		}
			
		/**
		 * @return the start
		 */
		public int getStart() {
			return start;
		}
		/**
		 * @return the end
		 */
		public int getEnd() {
			return end;
		}
		/**
		 * @return the leftC
		 */
		public Node getLeftC() {
			return leftC;
		}
		/**
		 * @return the rightC
		 */
		public Node getRightC() {
			return rightC;
		}
		/**
		 * @param start the start to set
		 */
		public void setStart(int start) {
			this.start = start;
		}
		/**
		 * @param end the end to set
		 */
		public void setEnd(int end) {
			this.end = end;
		}
		/**
		 * @param leftC the leftC to set
		 */
		public void setLeftC(Node leftC) {
			this.leftC = leftC;
		}
		/**
		 * @param rightC the rightC to set
		 */
		public void setRightC(Node rightC) {
			this.rightC = rightC;
		}
		
		/**
		 * toString method that has been overridden from Object
		 */
		public String toString(){
			return "Start: " + getStart() + "  End: " + getEnd() 
	    	+ "  L(" + getLeftC()+ ")  R(" + getRightC() + ")";
		}
			
		// Overrides the equals method inherited from Object
		public boolean equals(Object obj) {
			Node other;
				
			if (!(obj instanceof Node)) return false;
			else other = (Node) obj;
			if (this.start != other.getStart() || this.end != other.getEnd() ||
					 this.leftC != other.getLeftC() || this.rightC != other.getRightC()) {
			return false; 
			}
			return true;
		}
	
	}

