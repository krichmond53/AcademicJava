/**	Kevin Richmond
 * CMSC 435 - Intro to Data Science
 * Fall 2017
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class imputation {
    
	/**
     * Create a table from a CSV file
     * @param n Name of the input file
     * @param t Size of the table (row/col)
     * @return 2D array
     */
	public static String[][] make(String n, int[] t){
		Scanner test = inputFile(n);
		String[][] table = csvToArray(test,t);
		return table;		
	}
	
	public static void main(String[] args) {
	
//		Scanner s = inputFile("test.csv");
		Scanner s = inputFile("assignment2_dataset_missing004.csv"); 
		int[] tSize = findSize(s);  // 0 = # cols, 1 = # rows

//		System.out.println("Original File");								// TEST
//		String[][] missingValues = make("test.csv", tSize);					// TEST 
//		printArray(missingValues);
//
//		System.out.println("Complete File");								// TEST
//		String[][] complete = make("testComplete.csv", tSize);				// TEST
//		printArray(complete);

//		String[][] mean004 = mean(make("test.csv", tSize), tSize);			// TEST
//		printArray(mean004);												// TEST
//		computeMAE(mean004, complete, "MAE_004_mean = ");
//		
//		String[][] meanCond004 = meanCond(make("test.csv",tSize), tSize);	// TEST
//	    printArray(meanCond004);											// TEST - Check conditions
//	    computeMAE(meanCond004, complete, "MAE_004_mean_condtional = ");
		
//		String[][] hd004 = hd(make("test.csv",tSize), tSize);
//		printArray(hd004);
//	    computeMAE(hd004, complete, "MAE_004_hd_condtional = ");
	    
//		String[][] hdCond004 = hdCond(make("test.csv",tSize), tSize);
//		printArray(hdCond004);
//	    computeMAE(hdCond004, complete, "MAE_004_hdCond_condtional = ");


		// Makes a 2D array from CSV file that has missing values
		String[][] missingValues = make("assignment2_dataset_missing004.csv", tSize);
		
		// Makes a 2D array from CSV file that is COMPLETE
		String[][] complete = make("assignment2_dataset_complete.csv", tSize);
		
	    // Mean Imputation 
		String[][] mean004 = mean(make("assignment2_dataset_missing004.csv", tSize), tSize);
		computeMAE(mean004, complete, "MAE_004_mean = ");
	    outputToCSV(mean004, "V00370652_a2_missing004_imputed_mean.csv");	
	    System.out.println("");

		// Conditional Mean Imputation
	    String[][] meanCond004 = meanCond(make("assignment2_dataset_missing004.csv",tSize), tSize);
	    computeMAE(meanCond004, complete, "MAE_004_mean_conditional = ");
	    outputToCSV(meanCond004, "V00370652_a2_missing004_imputed_mean_conditional.csv");
	    System.out.println("");

	    // Hot Deck Imputation
		String[][] hd004 = hd(make("assignment2_dataset_missing004.csv",tSize), tSize);
		computeMAE(hd004, complete, "MAE_004_hd = ");
		outputToCSV(hd004, "V00370652_a2_missing004_imputed_hd.csv");
		System.out.println("");
	    
		// Conditional Hot Deck Imputation
		String[][] hdCond004 = hdCond(make("assignment2_dataset_missing004.csv",tSize), tSize);
		computeMAE(hdCond004, complete, "MAE_004_hd_conditional = ");
	    outputToCSV(hdCond004, "V00370652_a2_missing004_imputed_hd_conditional.csv");	   
	    System.out.println("");
	    
	    // Mean Imputation
	    String[][] mean20 = mean(make("assignment2_dataset_missing20.csv", tSize), tSize);
	    computeMAE(mean20, complete, "MAE_20_mean = ");
	    outputToCSV(mean20, "V00370652_a2_missing20_imputed_mean.csv");
	    System.out.println("");
	    
		// Conditional Mean Imputation 
	    String[][] meanCond20 = meanCond(make("assignment2_dataset_missing20.csv",tSize), tSize);
	    computeMAE(meanCond20, complete, "MAE_20_mean_conditional = ");
	    outputToCSV(meanCond20, "V00370652_a2_missing20_imputed_mean_conditional.csv");
	    System.out.println("");
	    
		// Hot Deck Imputation
		String[][] hd20 = hd(make("assignment2_dataset_missing20.csv",tSize), tSize);
		computeMAE(hd20, complete, "MAE_20_hd = ");
	    outputToCSV(hd20, "V00370652_a2_missing20_imputed_hd.csv");
	    System.out.println("");

	    // Conditional Hot Deck Imputation
		String[][] hdCond20 = hdCond(make("assignment2_dataset_missing20.csv",tSize), tSize);
		computeMAE(hdCond20, complete, "MAE_20_hd_conditional = ");	    
	    outputToCSV(hdCond20, "V00370652_a2_missing20_imputed_hd_conditional.csv");
	    
	    // DONT FORGET DOCUMENTATION OF 5 questions
		
	}
	/**
	 * Output a 2D array into a CSV file
	 * @param arr - 2D array
	 * @param fileName - File name to be created in string format
	 */
	private static void outputToCSV(String[][] arr, String fileName) {
		int row = arr.length;
		int col = arr[0].length;
		PrintWriter csvOut = null;
		
		try {
			csvOut = new PrintWriter(new File(fileName));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Iterate through all cells
		for (int i = 0; i<row; i++){
			for (int j = 0; j<col; j++){
				  csvOut.print(arr[i][j]);
				  csvOut.print(",");
			}
			csvOut.print("\n");
		}
//		System.out.println("Created file: " + fileName);
		csvOut.close();
	}
	
	/**
	 * Compute the Mean Absolute Error (MAE) between the imputed tables and the complete table
	 * @param impute	-	Table with imputed values
	 * @param complete	-	Table with complete values
	 * @param s			-	String to print out for specific MAE
	 */
	private static void computeMAE(String[][] impute, String[][] complete, String s) {
		int row = impute.length;
		int col = impute[0].length;
		double countDiff = 0;
		double mae = 0;
		
		// Iterate through all cells
		for (int i = 1; i<row; i++){
			for (int j = 0; j<col-1; j++){
				if ((impute[i][j].equals(complete[i][j]))){
//					System.out.println("Same");
				} else {
					double a = Double.parseDouble(impute[i][j]);
					double b = Double.parseDouble(complete[i][j]);
					double temp = Math.abs((a-b));
					mae += temp;
					countDiff++;
//					System.out.println(countDiff+") "+a +" - "+ b +"="+temp+" New sum: "+ mae);
					
				}
			}
		}
		mae = (1/countDiff)*mae;
		System.out.println(s + new DecimalFormat("#0.0000").format(mae));
	}

	/**
	 * Imputes missing values using hot deck imputation based on the CLASS condition
	 * @param hdCon - 2D array with missing values
	 * @param size - Size of array (row/col)
	 * @return - 2D array with values imputed based on CLASS
	 */
	private static String[][] hdCond(String[][] hdCon, int[] size) {
		int row = hdCon.length;
		int col = hdCon[0].length;
		int[] rowWithMissing = new int[row-1]; 	
		int num = 0;								// # of rows with missing values
		
//		System.out.println("Rows: " + row + "\t Cols: "+ col );
		
		// Look for rows with ? - If found, put all values in array and the final element is the row#
		for (int i = 1; i < row; i++){
			for (int j = 0; j< col;j++){
				if (hdCon[i][j].equals("?")){
					rowWithMissing[num] = i;
					num++;
//					System.out.println(num + ") Found ? in row " + i);
					j=col;
				}
			}
		}
		
//		// TEST - Show all rows which are missing values
//		for (int a : rowWithMissing){
//			if (a>0) 			System.out.print(a + " ");
//		}
//		System.out.println("\n");
//		// END TEST
		
		// Create 2D array for each row with missing.  Add extra column for row #
		String[][] rowsToFill = new String[num][col+1];
		int check = 0;
		for (int i=0;i<row;i++){

			if (i == rowWithMissing[check]){
//				System.out.println("\ni = " + i + "\trowWithMissing[i] = "+rowWithMissing[check]);
				rowsToFill[check][col] = String.valueOf(rowWithMissing[check]);
				
				for(int j=0;j<col;j++){
					rowsToFill[check][j] = hdCon[i][j];
					
				}
				check++;
			}
		}
		
		// TEST - Show all values of all rows with a missing value
		for (int i=0;i<num;i++){
			for(int j=0;j<col+1;j++){
//				System.out.print(rowsToFill[i][j]+ "\t");
			}
//			System.out.println("");
		}
		// END TEST
		
		// Determine distance from all other rows
		double[][] distance = new double[num][row];
		double temp = 0;
		double t2 = Double.MAX_VALUE;
		Stack<Integer> closestRow = new Stack<Integer>();

		for (int k=0; k<num; k++){
			
			for (int i=1;i<row;i++){
				if (i != Integer.parseInt(rowsToFill[k][col])){
					
					for(int j=0;j<col-1;j++){
						if (rowsToFill[k][j].equals("?")){
							temp = temp + 1;
						} else if (hdCon[i][j].equals("?")){
							temp = temp + 1;
						} else {
							double a = Double.parseDouble(rowsToFill[k][j]);
							double b = Double.parseDouble(hdCon[i][j]);
							temp = temp + ((a-b)*(a-b));
						}							
					}
				} else {
//					System.out.println("To be filled " + rowsToFill[k][col] + "\ti = " + i);
				}
				if (temp > 0){
					distance[k][i] = Math.sqrt(temp);
					if (distance[k][i] < t2 && hdCon[rowWithMissing[k]][col-1].equals(hdCon[i][col-1])){
//						System.out.println("Row: " +rowWithMissing[k] +"\tClass: " + hdCon[rowWithMissing[k]][col-1]);
//						System.out.println("Row: " +rowWithMissing[k] +"\tRow: "+i + "\tDistance: " + distance[k][i]);
//						System.out.println("Class " + hdCon[rowWithMissing[k]][col-1] + " vs " +hdCon[i][col-1]);
						t2 = distance[k][i];
						closestRow.push(i);
					}
					temp = 0;
				}
				int closeRow = 0;
				if (!(closestRow.isEmpty())&& i==row-1){
					closeRow = closestRow.pop();
					
					for (int n = 0; n<col;n++){
//						System.out.println("Row " + rowWithMissing[k] + " has ?\tCheck values of "+closeRow);
						if (hdCon[closeRow][n].equals("?") && hdCon[rowWithMissing[k]][n].equals("?")){
//							System.out.println("The two rows are missing the same value");
							closeRow = closestRow.pop();
							n = 0;
						} 
						if (!(hdCon[closeRow][n].equals("?")) && hdCon[rowWithMissing[k]][n].equals("?")){
//							System.out.println("Replace "+ rowWithMissing[k] +"/"+n+ ": " + hdCon[rowWithMissing[k]][n] +
//									"\twith: " + hdCon[closeRow][n] +"\tfrom " +closeRow+"/"+n);
							hdCon[rowWithMissing[k]][n] = hdCon[closeRow][n];
						}
					}
//					System.out.println("");
				}
			}

			t2 = Double.MAX_VALUE;
			
		}

//		for (int i=0;i<num;i++){
//			for (int j=0;j<row;j++){
//				int nextClose = closestRow.pop();
//				System.out.println("Row: " + rowWithMissing[i] + "\tCompare with row " + nextClose);
//			}
//		}
		
		
		return hdCon;
	}

	
//		System.out.println("Conditional Hot Deck Imputation");
//		int row = size[0];
//		int col = size[1];
//		String[][] n = new String[row][col]; 
//		n = hdCon;
//		int miss = 0;
//		int countMissing = 0;
//		int[] whereMissing = new int[(row-1)*col]; // Max values missing are ALL of them (- labels)
//		
//		// Skip first row of spreadsheet with titles, but then go through each row
//		for (int i = 1; i<row; i++){
//		
//			// Go through each column of the row (-1 removes the class row)
//			for (int j = 0; j<col-1; j++){
//				
//				// If the value is missing, count it, mark it, and add to missing pile
//				if (n[i][j].equals("?")) {
//					countMissing++;
//					whereMissing[miss] = i;
//					miss++;
//					whereMissing[miss] = j;
//					miss++;
//					
//				}
//			}
//		}
//		
//		// List where missing values are located (row/column)
////		Boolean endOfList = false;
////		System.out.print("\nLocations (row/col) for " + countMissing + " missing values:\n");
////		for (int i = 0; i< countMissing*2;i++){
////			if (i%2==0) {
////				System.out.print("\n"+ (i/2+1)+") ");
////			}
////
////			if (endOfList == false) {
////				System.out.print(whereMissing[i] + " ");
////			}
////		}
//		
//		int[] closestRowF = new int[whereMissing.length];
//		int[] closestRowC = new int[whereMissing.length];
//		for (int i = 0; i < countMissing*2;i=i+2){
//			int missingRow = whereMissing[i];
//			int missingCol = whereMissing[i+1];
//			
//			closestRowF[i] = getClose(n, missingRow, missingCol, 'F');
//			closestRowC[i] = getClose(n, missingRow, missingCol, 'C');
////			System.out.println("Cond F: The closest row to " + missingRow + " is row " + closestRowF[i]);
////			System.out.println("Cond C: The closest row to " + missingRow + " is row " + closestRowC[i]);
//			for (int j = 0; j< col;j++){
//				
//				if (n[whereMissing[i]][j].equals("?")){
////					System.out.println(n[whereMissing[i]][j] + " vs " + n[closestRowF[i]][j]);
////					System.out.println(n[whereMissing[i]][j] + " vs " + n[closestRowC[i]][j]);
//					n[whereMissing[i]][j] = n[closestRowF[i]][j];
//					n[whereMissing[i]][j] = n[closestRowC[i]][j];
//				}
//			
//			}
//		}
//		return n;
//	}
	
	/**
	 * Hot deck imputation finds Euclidian distance to next closest row and uses the value for the
	 * specific feature from that row
	 * @param hd - 2D array of values were some are missing (missing = "?")
	 * @param size - Size of the array (row/columns
	 * @return - 2D array with HD imputed values
	 */
	private static String[][] hd(String[][] hd, int[] size) {
		int row = hd.length;
		int col = hd[0].length;
		int[] rowWithMissing = new int[row-1]; 	
		int num = 0;								// # of rows with missing values
		
//		System.out.println("Rows: " + row + "\t Cols: "+ col );
		
		// Look for rows with ? - If found, put all values in array and the final element is the row#
		for (int i = 1; i < row; i++){
			for (int j = 0; j< col;j++){
				if (hd[i][j].equals("?")){
					rowWithMissing[num] = i;
					num++;
//					System.out.println(num + ") Found ? in row " + i);
					j=col;
				}
			}
		}
		
//		// TEST - Show all rows which are missing values
//		for (int a : rowWithMissing){
//			if (a>0) 			System.out.print(a + " ");
//		}
//		System.out.println("\n");
//		// END TEST
		
		// Create 2D array for each row with missing.  Add extra column for row #
		String[][] rowsToFill = new String[num][col+1];
		int check = 0;
		for (int i=0;i<row;i++){

			if (i == rowWithMissing[check]){
//				System.out.println("\ni = " + i + "\trowWithMissing[i] = "+rowWithMissing[check]);
				rowsToFill[check][col] = String.valueOf(rowWithMissing[check]);
				
				for(int j=0;j<col;j++){
					rowsToFill[check][j] = hd[i][j];
					
				}
				check++;
			}
		}
		
		// TEST - Show all values of all rows with a missing value
		for (int i=0;i<num;i++){
			for(int j=0;j<col+1;j++){
//				System.out.print(rowsToFill[i][j]+ "\t");
			}
//			System.out.println("");
		}
		// END TEST
		
		// Determine distance from all other rows
		double[][] distance = new double[num][row];
		double temp = 0;
		double t2 = Double.MAX_VALUE;
		Stack<Integer> closestRow = new Stack<Integer>();

		for (int k=0; k<num; k++){
			
			for (int i=1;i<row;i++){
				if (i != Integer.parseInt(rowsToFill[k][col])){
					
					for(int j=0;j<col-1;j++){
						if (rowsToFill[k][j].equals("?")){
							temp = temp + 1;
						} else if (hd[i][j].equals("?")){
							temp = temp + 1;
						} else {
							double a = Double.parseDouble(rowsToFill[k][j]);
							double b = Double.parseDouble(hd[i][j]);
							temp = temp + ((a-b)*(a-b));
						}							
					}
				} else {
//					System.out.println("To be filled " + rowsToFill[k][col] + "\ti = " + i);
				}
				if (temp > 0){
					distance[k][i] = Math.sqrt(temp);
					if (distance[k][i] < t2){
//						System.out.println("Row: " +rowWithMissing[k] +"\tRow: "+i + "\tDistance: " + distance[k][i]);
						t2 = distance[k][i];
						closestRow.push(i);
					}
					temp = 0;
				}
				int closeRow = 0;
				if (!(closestRow.isEmpty())&& i==row-1){
					closeRow = closestRow.pop();
					
					for (int n = 0; n<col;n++){
//						System.out.println("Row " + rowWithMissing[k] + " has ?\tCheck values of "+closeRow);
						if (hd[closeRow][n].equals("?") && hd[rowWithMissing[k]][n].equals("?")){
//							System.out.println("The two rows are missing the same value");
							closeRow = closestRow.pop();
							n = 0;
						} 
						if (!(hd[closeRow][n].equals("?")) && hd[rowWithMissing[k]][n].equals("?")){
//							System.out.println("Replace "+ rowWithMissing[k] +"/"+n+ ": " + hd[rowWithMissing[k]][n] +
//									"\twith: " + hd[closeRow][n] +"\tfrom " +closeRow+"/"+n);
							hd[rowWithMissing[k]][n] = hd[closeRow][n];
						}
					}
//					System.out.println("");
				}
			}

			t2 = Double.MAX_VALUE;
			
		}

//		for (int i=0;i<num;i++){
//			for (int j=0;j<row;j++){
//				int nextClose = closestRow.pop();
//				System.out.println("Row: " + rowWithMissing[i] + "\tCompare with row " + nextClose);
//			}
//		}
		
		
//		System.out.println("");
		return hd;
	}
	

	/**
	 * Compares a row with missing values to the entire array
	 * @param hd whole array with missing values
	 * @param comp string to compare with array
	 * @return row number of the closest row to the one compared for missing values.
	 */
	private static Stack<Integer> compareRows(String[][] hd, String[] comp) {
		int close = 0;
		double temp = 0;
		int row = hd.length;
		int col = hd[0].length;
		double distance = Double.MAX_VALUE;
		Stack<Integer> c = new Stack<Integer>();
		
		for (int i = 1; i<row; i++ ){
				for (int j = 0; j<col-1; j++){
			
					if (hd[i][j].equals("?") || comp[j].equals("?")){
						temp++;
//						System.out.println("Found ? at column " + j + " Distance = " + temp);
					} else {
						double a = Double.parseDouble(comp[j]);
						double b = Double.parseDouble(hd[i][j]);
						temp += (a-b)*(a-b);
//						System.out.println("("+a +"-"+b +")^2 = " + temp);
					}
				}
			temp = Math.sqrt(temp);
//			System.out.println("Row " + i + " is value "+ temp);
			if (temp < distance && temp != 1.0){
				distance = temp;
//				close = i;
				c.push(i);
//				System.out.println("Row " +i + " is closest with value "+ distance);
			}
			temp = 0;
		}
		
		return c;
	}

	/**
	 * Creates a 2D array of rows which have values missing
	 * @param hd - Original array
	 * @param missing - array of missing values (row/col)
	 * @return 2D array of rows with values missing
	 */
	private static String[][] createMissingRowArray(String[][] hd, int[] missing) {
		int numMissing = missing[missing.length-1];
		int col = hd[0].length;
		String[][] m = new String[numMissing][col];
		int val = 0;
		for (int i = 0; i<numMissing/2; i++ ){
			for (int j = 0; j<col; j++){
				m[i][j] = hd[missing[val]][j];
			}
			val=val+2;
		}
		return m;
	}

	/**
	 * Iterates through a 2D array to find any values that are missing.
	 * @param a 2D array
	 * @return Array of row/col which are features with missing values.  Last number in the array is
	 * twice the total number of values missing
	 */
	private static int[] findMissing(String[][] a){
		int[] missing = new int[a.length/2];
		int row = a.length;				 
		int col = a[0].length-1;
		int count = 0;

		
		for (int i = 0; i<row; i++){
			// Go through each column of the row (-1 removes the class row)
			for (int j = 0; j<col; j++){
				// If the value is missing, count it, mark it, and add to missing pile
				if (a[i][j].equals("?")) {
					missing[count] = i;
					count++;
					missing[count] = j;
					count++;
					if (count+2 == missing.length) {
						missing = growArray(missing);
					}
				}
			}
		}

		// Appends the count to the last value in the array
		if (missing[missing.length-1] == 0) {
			missing[missing.length-1] = count;
		} else {
			missing = growArray(missing);
			missing[missing.length-1] = count;
		}
		
		return missing;
	}
	
	/**
	 * Calculate distance and return closest row
	 * @param n - 2D array dataset
	 * @param row - Row with missing data that needs compared
	 * @return - Integer value of closes row
	 */
	private static int getClose(String[][] n, int row, int col, char condition) {
		double closest = Double.MAX_VALUE;
		double temp = 0;
		double a = 0;
		int closeRow = 0;
		String c = String.valueOf(condition);
		double[] x = new double[n[0].length-1];
		
		// Populate array with missing row values
		for (int i = 0; i<n[row].length-1; i++){
			if (n[row][i].equals("?")){
				x[i] = -1;
//				System.out.print(x[i] + "\t\t" );
			} else {
				x[i] = Double.parseDouble(n[row][i]);
//				System.out.print(x[i] + "\t\t" );
			}
		}
		
		// Calculate distance between that and each other row.
		for (int i = 1; i<n.length; i++){
			if (i != row){
				for (int j = 0; j < n[i].length-1; j++){

					if (n[i][n[i].length-1].equals(c)){						
//						System.out.println("Condition: " + c + " Actual: "+n[i][n[i].length-1]);
						if (n[i][j].equals("?") || x[j] == -1){
							temp += 1;
	//						System.out.println("Compare row " + row + " with " + i 
	//								+ "(added 1 for missing value): " + temp);
						} else {
							a = Double.parseDouble(n[i][j]);
							temp += ((x[j] - a) * (x[j] - a));
	//						System.out.println("Compare row " +row+ " with " + i+ ": " + x[j] + " - " + a + " = " + (x[j]-a) + "^2\n\t"
	//								+ " = " + Math.pow((x[j]-a), 2) + "\t\tTEMP IS: " + temp);
						}
					}
				}
			}
			temp = Math.sqrt(temp);
//			System.out.println("Comparing row " + row + " with i="+ i +
//					" After sqrt of sum of squares: " + temp + "\n");
			if (!(n[i][col].equals("?")) && temp < closest){
//				System.out.println("Between row " + row + " and " + i);
//				System.out.println("Closest row was " + closeRow + " with value of " + closest);
				closeRow = i;
				closest = temp;
//				System.out.println("Closest row to "+ row +" is now " + closeRow + " with value of " + closest +"\n");
			}
			temp = 0;
		}		
//		System.out.println("Closest row: " + closeRow + " with value of " + closest);
		return closeRow;
	}
	
	/**
	 * Finds the mean of each column conditioned upon whether the row is class F or C, 
	 * then imputes F means to F columns and C means to C columns
	 * @param mean - 2D array for imputation
	 * @param size - # of rows, # of columns
	 * @return - 2D array with missing values imputed with mean values
	 */
	private static String[][] meanCond(final String[][] mc, int[] size) {
//		System.out.println("Conditional Mean Imputation");
		int row = mc.length;
		int col = mc[0].length;
		double[] sumF = new double[col];
		double[] sumC = new double[col];
		double[] meanF = new double[col];
		double[] meanC = new double[col];
		int valuesC[] = new int[col];
		int valuesF[] = new int[col];
		
//		System.out.println("Row: " + row + "\tCol: " +col);
		
		// Add up all values in each column based on class F or C
		for (int i = 0; i<col-1; i++){
			for (int j = 1; j<row; j++){
				if (mc[j][col-1].equals("F")){
//					System.out.println("Class F at row " + j );
					if (!(mc[j][i].equals("?"))){						
						sumF[i] += Double.parseDouble(mc[j][i]);
						valuesF[i]++;
					}					
				} else {
//					System.out.println("Class C at row " + j );
					if (!(mc[j][i].equals("?"))){						
						sumC[i] += Double.parseDouble(mc[j][i]);
						valuesC[i]++;
					}
				}
			}
//			System.out.println("sumC["+i+"]: " + sumC[i] +"\tsumF["+i+"]: " + sumF[i] +"\n" );
//			System.out.println("sumC["+i+"]: " + (sumC[i]) + "\tsumF["+i+"]: " + (sumF[i]));
			meanF[i] = sumF[i]/valuesF[i];
			meanC[i] = sumC[i]/valuesC[i];
//			System.out.println("meanC["+i+"]: " + meanC[i] + "\tmeanF["+i+"]: " + meanF[i]);
						
			
		}
		
		
		
		// If there is a missing value in the column, impute the mean value based on class
		for (int i = 0; i<col-1; i++){
			for (int j = 1; j<row; j++){
				if (mc[j][col-1].equals("F")){
//					System.out.println("Class F at row " + j );
					if ((mc[j][i].equals("?"))){						
						mc[j][i] = String.format("%.5f",meanF[i]);	// Double.toString(meanF[i]);
//						System.out.println("Class F at " + j +"/"+i+ " was ? is now " + mc[j][i] );
					}					
				} else {
					if ((mc[j][i].equals("?"))){						
						mc[j][i] = String.format("%.5f",meanC[i]);	// Double.toString(meanF[i]);	
//						System.out.println("Class C at " + j +"/"+i+ " was ? is now " + mc[j][i] );
					}
				}
			}
		}

		return mc;
		
}	

	/**
	 * Finds the mean of each column then imputes it to all missing values of that row
	 * @param mean - 2D array for imputation
	 * @param size - # of rows, # of columns
	 * @return - 2D array with missing values imputed with mean values
	 */
	private static String[][] mean(final String[][] mean, int[] size) {
//		System.out.println("Mean Imputation");
		int row = size[0];
		int col = size[1];
		String[][] n = new String[row][col]; 
		n = mean;
		int miss = 0;
		int countMissing[] = new int[col];
		int whereMissing[] = new int[(row-1)*col]; // Max values missing are ALL of them (- labels)
		double[] colVal = new double[col];
		
		// Skip first row of spreadsheet with titles, but then go through each row
		for (int i = 1; i<row; i++){
			
			// Go through each column of the row (-1 removes the class row)
			for (int j = 0; j<col-1; j++){
				
				// If the value is missing, count it and mark it
				if (n[i][j].equals("?")) {
					countMissing[j]++;
					whereMissing[miss] = j;
					miss++;
					whereMissing[miss] = i;
					miss++;
					
				// Otherwise, add the value to the current column value
				} else {
					colVal[j] = colVal[j] + Double.parseDouble(n[i][j]);
				}
			}
		}
		
		int k = 0;
		for (double v : colVal){
			colVal[k] = v/(row-1-countMissing[k]);
			k++;
		}
		
		for (int i = 0; i < whereMissing.length; i= i+2){
			if (whereMissing[i] == 0 && whereMissing[i+1] == 0){
				break;
			}
			n[whereMissing[i+1]][whereMissing[i]] = String.format("%.5g",colVal[whereMissing[i]]);
		}
		
		return n;
		
	}

	/**
	 * Determines the number of columns and rows in a CSV files
	 * @param file - Scanner object of CSV file
	 * @return - Integer array where [0] is # of columns and [1] = # of rows
	 */
	private static int[] findSize(Scanner file) {
		
		int[] size = new int[2];
		String feature[] = file.next().split(",");
		size[1] = feature.length;
//		System.out.println("Number of columns: " + size[0]);
		
		while (file.hasNextLine()){
			size[0]++;
			file.nextLine();
		}
//		System.out.println("Number of rows: " + size[1]);
		
		return size;
	}

	/**
	 * Converts a CSV file into a 2D array
	 * @param file - Scanner object of CSV file
	 * @param size - Integer array of # of columns and # of rows
	 * @return - 2D array of strings representing the CSV file
	 */
	private static String[][] csvToArray(Scanner file, int[] size) {
    	int row = size[0];
    	int col = size[1];
    	String dataset[][] = new String[row][col];
    	
    	while(file.hasNextLine()){
	    	for (int i = 0; i < row; i++){
	    		String feature[] = file.next().split(",");
		    	
	    		for (int j = 0; j < col ;j++){
	    			dataset[i][j] = feature[j];
	    		}
	    	}
	    	break;
	    }		
	    return dataset;
	}

	/**
	 * Doubles an an array's size 
	 * @param a - Original array 
	 * @return - Larger array with all original information included
	 */
	private static int[] growArray(int[] a){
		int[] big = new int[a.length*2];
		int count = 0;
		
		for (int i : a){
			big[count] = i;
			count++;
		}
//		System.out.println("\nGrowing array from " + a.length +" to " + big.length);
		return big;
	}
	
	/**
	 *  Print dataset to screen
	 * @param n - 2D array of data
	 */
	private static void printArray(String[][] n){
		for (int i=0; i<n.length;i++){
			for(int j=0; j< n[i].length;j++){
				if (i == 0){ 
					System.out.print(n[i][j] + "\t");
				} else {
					System.out.print(n[i][j] + "\t\t");
				}
			}
			System.out.print("\n");
		}
		System.out.print("\n");
	}
	
	/**
	 * Prompts user for a an input file, verifies existing file and returns a
	 * Scanner object inFile.
	 * @return Input file inFile.
	*/
	public static Scanner inputFile(String userFile) {  
	Scanner inFile = null;
	   	try {
	   		FileReader inReader = new FileReader(userFile); 
	   		inFile = new Scanner(inReader);
	   	}
	   	catch (FileNotFoundException e){ 
	   		System.out.println("Not a good file");
	   	}
   	return inFile;
  }
}
