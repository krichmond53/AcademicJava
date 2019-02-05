
public class Road implements Comparable<Road>{

	private int destination;
	private int gas;
	
	/**
	 * @param destination
	 * @param gas
	 */
	public Road(int dest, int g) {
		setDestination(dest);
		setGas(g);
	}

	/**
	 * @return the destination
	 */
	public int getDestination() {
		return destination;
	}

	/**c
	 * @return the gas
	 */
	public int getGas() {
		return gas;
	}

	/**
	 * @param destination the destination to set
	 */
	public void setDestination(int dest) {
		this.destination = dest;
	}

	/**
	 * @param gas the gas to set
	 */
	public void setGas(int g) {
		this.gas = g;
	}
	
	//*****************************************
	// Comparable interface method ************
	public int compareTo(Road o){
		Road other = null;
		int result = 0;
		other = (Road) o;
		
		if (this.equals(other)) result = 0;
		else if (this.getGas() > (other.getGas())) result = 1;
		else if (this.getGas() < (other.getGas())) result = -1;
		
		return result;

	} 
	  //*****************************************
	// Overrides the equals method inherited from Object
	public boolean equals(Object obj) {
		Road other;
		
		if (!(obj instanceof Road)) return false;
		else other = (Road) obj;
		if (this.destination != other.getDestination() || this.gas != other.getGas())  {
		return false; 
		}
		return true;
	}
	
	public String toString(){
		return "\nDestination City: " + this.destination + "  Cost: $" + this.gas;
	}
}
