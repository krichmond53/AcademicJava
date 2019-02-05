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

public class BinaryTree extends Node {
	
	private Node root;
	
	/**
	 * Constructor to create a balanced Binary Tree
	 * @param input - Sorted array of integer inputs
	 */
	public BinaryTree(int[] input) {
		// TODO Auto-generated constructor stub
		root = null;
		BuildBinaryTree(input);
		
	}
	
	/**
	 * Method with 1 argument used to create nodes to assemble the balanced Binary Tree
	 * @param input - Sorted array of integer inputs
	 * @return recursive call to build the tree
	 */
	
	private Node BuildBinaryTree(int[] input){
		if (input.length == 0){
			return null;
		}
		
		return BuildTree(input, 0, input.length-1);
	}
	/**
	 * Recursive method to create nodes from a sorted array of integer inputs to create nodes to build a
	 * balanced binary tree.
	 * @param input - A sorted array of integer inputs
	 * @param low - First index of the sorted array
	 * @param high - Last index of the sorted array
	 * @return - A node containing the start and end integers of the input sentences
	 */

	private Node BuildTree(int[] input, int low, int high){
		Node sentence = new Node();
		int center = (low + high) /2;

		if (low > high){
			return null;
		}
	
		if (center-1 <0){
			sentence = new Node(1, input[center]);
		} else {
			sentence = new Node(input[center-1]+1, input[center]);
		}

		if (root == null)
			root = sentence;
//			System.out.println("\nRoot created: \t" + root.toString());
//		} else
//			 System.out.println("Added node: \t" + sentence.toString());
			
		sentence.setLeftC(BuildTree(input, low, center-1));
		sentence.setRightC(BuildTree(input, center+1, high));
		return sentence;
	}
	
	/**
	 * Searches through the balanced binary tree for sentences that contain the word at position w
	 * @param w - Position of the word being searched for
	 * @return - Node where the word was found
	 */
	
	public Node searchFor(int w){
		Node found = null;
		Node comp = root;
		
		if(w > comp.getStart() && w < comp.getEnd()){
			return comp;
		}
		
		while (found == null) {
			if (w >= comp.getStart() && w <= comp.getEnd()){
				found = comp;
			} else if (w < comp.getEnd()){
				comp = comp.getLeftC();
			} else {
				comp = comp.getRightC();
			}
		}
		
		return found;
	}
}