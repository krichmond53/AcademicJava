import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

/*
 * Kevin Richmond
 * CMSC401 - Advanced Algorithms and Data Structures
 * Project 4 - Road Trip
 * Required files: cmsc401.java, MapGraph.java, City.java
 * 
 * Description - This program will take in integer values for a number of cities, a number of highways between cities, price of motels in each city,
 * 	and the price of gas from one city to the next.  It will then determine that cheapest root from RVA to LA when visiting the given cities.
 */

public class A4_RoadTrip {

	public static void main(String[] args) {
		// Accept user input for size of arrays
		Scanner in = new Scanner(System.in);
		int numCities =  in.nextInt()+1;
		City[] cities = new City[numCities];
		// int[] motelCost = new int[numCities];
		// System.out.println("Number of cities: " + cities.length);
		int numRoads = in.nextInt();
		// int[] roadCost = new int[numRoads];
		
		cities[1] = new City(1, 0); 	//setName(1);
		cities[2] = new City(2, 0);		//setName(2);
		
		// Accept user input of integers for city's name (number) and motel cost
		for(int i = 3; i < numCities; i++) {
			int cName = in.nextInt();
			int mCost = in.nextInt();
			
			cities[i] = new City(cName, mCost);
		}
		
		// Accept user input of integers for two cities and the distance between them
		for(int i = 0; i < numRoads; i++){
			int cityA = in.nextInt();
			int cityB = in.nextInt();
			int gas = in.nextInt();
			
			cities[cityA].setRoads(cityB, gas);
			cities[cityB].setRoads(cityA, gas);
		}
		
		System.out.println(findShortestPath(cities));
		//printCities(cities);
		
		in.close();
	}
	
	private static int findShortestPath(City[] c) {
		// System.out.println("\n Find Shortest Path Function");
		int total = 0;
		c[1].setTemp(0);
		int currentCity = 1; 					// Start at city 1 (RVA)
		LinkedList<City> q = new LinkedList<City>();
		
		while (!q.isEmpty() || currentCity != 2){
			

			
			// Make list of roads out of city, make the fastCity/Road INF
			ArrayList<Road> roads = c[currentCity].getRoads();		
			int fastCity = Integer.MAX_VALUE;
			int fastRoad = Integer.MAX_VALUE;
			
			// Update the cost to all adjacent cities
			for (int i = 0; i < roads.size(); i++){		
				// System.out.println("\n*Current City: " + currentCity);
				int place = roads.get(i).getDestination();
				int cost = roads.get(i).getGas();
					
				// TEMP of destination cannot be 0 (SOURCE) - Update destination if it hasn't been visited (= -1) 
				// or if the travel + motel from current city is less than it's current TEMP value
				if (c[place].getTemp() !=0 && (c[place].getTemp() > (c[currentCity].getTemp() + cost + c[place].getMotel())
						|| c[place].getTemp() == -1)){	 
					c[place].setTemp(c[currentCity].getTemp() + cost + c[place].getMotel());
					c[place].setPrev(currentCity);
			
					//System.out.println("**Updated, not yet moved to." + c[place].toString());
				
				}
								
				// Check to see which city is closest and move to it.
				// TEMP cannot be 0, Destination must have it's previous city set to the current city
				// Destination temp must be less than the current fastRoad (initialized as MAX_VALUE)
				if (c[place].getTemp() !=0 && c[place].getTemp() < fastRoad && c[place].getPrev() == currentCity){
					//System.out.println("--Fast city was " + fastCity + " by cost $" + fastRoad);
					
					// Condition where the previous
					if (fastCity < Integer.MAX_VALUE){
						//System.out.println("QUEUED CITY: " + fastCity);
						q.add(c[fastCity]);
					}
					fastCity = place;
					fastRoad = c[place].getTemp();
					//System.out.println("--New fast city is " + fastCity + " by cost $" + fastRoad);
				// QUEUE cities that TEMP != 0, and whose parent city is the current city to be calculated later
				} else if (c[place].getTemp() !=0 && c[place].getPrev() == currentCity) {
					//System.out.println("QUEUED CITY: " + place);  //roads.get(i).getDestination() );
					q.add(c[place]);  //roads.get(i).getDestination()]);
				}
			}
			// Move to the city of total lowest cost
			// System.out.println("\n***Moving to city " + fastCity +" with cost of $"+ fastRoad);
			if (fastCity == Integer.MAX_VALUE && !q.isEmpty()){
				City revisit = q.remove();
				currentCity = revisit.getName();
				//System.out.println("Revisiting City " + currentCity);
			} else if (fastCity == Integer.MAX_VALUE && q.isEmpty()) {
				currentCity = 2;
				total = c[currentCity].getTemp();
				//System.out.println("Queue is empty - Going to city 2");
			} else {	
				currentCity = fastCity;
				total = c[currentCity].getTemp();
			}
			if (currentCity == 2 && !(q.isEmpty())){
				City revisit = q.remove();
				currentCity = revisit.getName();
				//System.out.println("Revisiting City " + currentCity);
				
			}
		}

		
		return total;
	}
	
	// Print each city w/ name, motel cost, and cost to next city
	private static void printCities(City[] c){
		for (int i = 1; i < c.length; i++){
			System.out.println(c[i].toString());
			System.out.print("Roads:");
/*			for (int city = 1; city < c[i].getRoads().length; city++) {
				int[] r = c[i].getRoads();
				if (r[city] > 0) {
*/
			ArrayList<Road> r = c[i].getRoads();
			for (int j = 0; j < r.size(); j++){
				
				int city = r.get(j).getDestination();
				int gas = r.get(j).getGas();
						
				System.out.println("\tTo city " + city + ": $" + gas);
				
			}
		}
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
