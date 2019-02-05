import java.util.Scanner;

/*
 * Kevin Richmond
 * CMSC401 - Advanced Algorithms and Data Structures
 * Project 2 - Optimal Power Line Placement
 * 
 * Description - This program will take an input as an integer number of houses from 2-999,999 with the y-coordinates
 * 		of 0 to 1,000,000,000 which is their distance from the x-axis.  The program will return a single number which
 * 		will be the optimal placement of a main power line between all houses.
 */

public class A2_OptimalPowerLine {

	public static void main(String[] args) {
		
		// Accept user input for size of arrays
		Scanner in = new Scanner(System.in);
		int aSize =  in.nextInt();
		int[] numHouses = new int[aSize];
		int mLocation = aSize/2;
		int len = numHouses.length;
		
		// Accept user input of integers for numHouses array
		for(int i = 0; i < aSize; i++)
		{
			numHouses[i] = in.nextInt();		
		}
		

		if (aSize%2==1){							// When the array size is odd, there is an actual median value 
			System.out.println(findMed(numHouses, 0, len-1, mLocation));
		}
		else{										// When the array size is even, take the average of the two middle values
			int evenResult = (int) Math.ceil((findMed(numHouses, 0, len-1, mLocation)+findMed(numHouses, 0, len-1, mLocation-1))/2.0);
			System.out.println(evenResult);
		}
		in.close();
	}
	/**
	 * Find the median number in an unsorted array
	 * @param arr - array of integers between 0 and 999,999,999
	 * @param l - lowest index of the array (usually 0)
	 * @param h - highest index of the array (up to 1,000,000)
	 * @param median - the index of the arrary's median
	 * @return - the value of the array's median
	 */
	private static int findMed(int[] arr, int l, int h, int median){
		if(l == h)									// When the high and low are the same, there is only 1 element in the array
				return arr[l];						// Since it's only 1 item, that is the median
		int pivot = (int) (Math.random()*h);		//0; 
		pivot = partition(arr, l, h, h);			// Call the partition method to sort based on a pivot, and return pivot
		
		if (median == pivot)						// When the median is the same as the pivot's index, then that is the median value
				return arr[median];
		else if (median > pivot) 					// When the median is greater than the pivot, then search the upper half of array
			return findMed(arr, pivot+1, h, median);	
		else 										// When the median is less than the pivot, then search the lower half of the array
			return findMed(arr, l, pivot-1, median);		
	}
	
	/**
	 * Creates a partition in an array with all lower numbers to the right side of the pivot and
	 * all lower numbers to the left side of the pivot/
	 * @param arr - An array of integers
	 * @param l - Lowest index of the array
	 * @param h - Highest index of the array
	 * @param pIndex - The index of the pivot value
	 * @return - The pivot value
	 */
	private static int partition(int[] arr, int l, int h, int pIndex) {
		int value = arr[pIndex];			// Get the value of the pivot for comparing 
		int toSwap = l;						// Set the index at lower end of array to swap when integers are below the pivot value
		for(int i = l; i < h; i++) {
			if(arr[i] < value) {
				swap(arr, toSwap, i);		// Swap the value with index so that the low is on the left, and high on the right
				toSwap++;					// Move to next low integer that may be swapped
			}
		}
		swap(arr, h, toSwap);				// Put the pivot in the correct position, between the high and low values
		return toSwap;						// Return the middle value
	}
	
	/**
	 * Swaps two values in an array
	 * @param arr - An array of integers
	 * @param index1 - Index of first integer to be swapped
	 * @param index2 - Index of 2nd integer to be swapped
	 */
	private static void swap(int arr[], int index1, int index2){
		int temp = arr[index1];
		arr[index1] = arr[index2];
		arr[index2] = temp;
		
	}

}
