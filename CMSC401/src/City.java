import java.util.ArrayList;

public class City implements Comparable<City>{ 
	
	private int name;			// City identifier
	private int motel;			// Cheap motel
	private ArrayList<Road> roads;	// List of roads
	private int temp;			// Temp var for searching
	private int previous;		// Previous city
	
	
	public City(){
		setName(0);
		setMotel(0);
		this.roads = new ArrayList<Road>();
		setTemp(-1);
	}
	
	public City(int n, int m){
		setName(n);
		setMotel(m);
		this.roads = new ArrayList<Road>();
		setTemp(-1);
	}
	
	public int getPrev(){
		return previous;
	}
	
	public void setPrev(int p){
		this.previous = p;
	}
	
	public int getName(){
		return name;
	}
	
	public void setName(int n){
		this.name = n;
	}
	
	public int getMotel(){
		return motel;
	}
	
	public void setMotel(int m){
		this.motel = m;
	}
	
	public int getNumRoads(){
		return roads.size();
	}
	
	public ArrayList<Road> getRoads(){
		return roads;
	}
	
	public void setRoads(int r, int cost){
		Road a = new Road(r, cost);
		roads.add(a);
	}
	
	public void setTemp(int t){
		if (this.temp == -1) this.temp = t;
		else this.temp = t;
	}
	
	public int getTemp(){
		return temp;
	}
	
	
	public String toString(){
		return "\nCity " + getName() + "  Motel Cost: $" + getMotel() + "\n\tCurrent Cost: $" + getTemp() 
				+ "\n\tPrevious : " + getPrev();
	}
	//*****************************************
	// Comparable interface method ************
	public int compareTo(City o){
		City other = null;
		int result = 0;
		other = (City) o;
		
		if (this.equals(other)) result = 0;
		else if (this.getName() > (other.getName())) result = 1;
		else if (this.getName() < (other.getName())) result = -1;
		
		return result;

	} 
	  //*****************************************
	// Overrides the equals method inherited from Object
	public boolean equals(Object obj) {
		City other;
		
		if (!(obj instanceof Road)) return false;
		else other = (City) obj;
		if (this.name != other.getName() || this.roads != other.getRoads())  {
		return false; 
		}
		return true;
	}
}
