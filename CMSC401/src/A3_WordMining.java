import java.util.Scanner;

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

public class A3_WordMining {

	public static void main(String[] args) {
		// Accept user input for size of arrays
		Scanner in = new Scanner(System.in);
		int senSize =  in.nextInt();
		int[] s = new int[senSize];
		
		int wordSize = in.nextInt();
		int[] w = new int[wordSize];
		Node[] found = new Node[wordSize];
		
		// Accept user input of integers for ending position of sentence
		for(int i = 0; i < senSize; i++) {
			s[i] = in.nextInt();		
//			s[i] = i*10;
//			System.out.println(s[i]);
		}
		
		// Accept user input of integers for beginning position of word
		for(int i = 0; i < wordSize; i++){
			w[i] = in.nextInt();
		}
//		System.out.println("\nWord List\n");
//		w = wordList(wordSize, senSize);
		
//		System.out.println("\n*** Start Output ***");
		// Create new Balanced Binary Tree
		BinaryTree bbt = new BinaryTree(s);
		
		int numFound = 0;
		for (int i = 0; i < w.length; i++){			// Search through tree for words
			found[i] = bbt.searchFor(w[i]);
			if (found[i] != null){
				numFound++;
			} 
//			if (i > 0 && found[i] == found[i-1]){	// If 2 words are found in the same sentence, replace it with null 
//				numFound--;							// and decrement the count
//				found[i-1] = null;
//			}		
		}
		
		System.out.println(numFound);				// Print out start/end of every sentence with word
		for (Node n : found){
			if (n != null) System.out.println(n.getStart() + " " + n.getEnd());
		}
		in.close();
	}
	
	// Helper method to create word list - Unusued in normal operation
	private static int[] wordList(int num, int sen){
		int[] list = new int[num];
		
		for (int j = 0; j < num; j++){
			list[j] = 1 + (int)(Math.random()*(j+1)+ Math.random()*sen); 
			if (j>0)
				list[j] = list[j-1] + (int)(Math.random()*(j+1) + Math.random()*sen); 
			System.out.println(list[j]);
		}
		return list;
	}
}
