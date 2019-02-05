import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;
/**
 * Kevin Richmond
 * CMSC 403 - Programming Languages
 * Chapter 3, #8
 * 
 * Create a collection in Java to store information about soccer teams.  
 * You will import the information from the soccerStats.txt file given.  
 * This file contains the following categories of data: Team Name, League, Goals, Shots per game, 
 * Possession Percentage, Pass Success Percentage, and Aerials Won.  You will use lambda expressions
 *  to perform the following tasks:
 *  - Print the team names and Leagues
 *  - Print the name and league of the teams that have scored over 5 goals
 *  - Average the possession percentage
 *  - Sort the teams by league
 *  - Sort the teams by pass success percentage
 *  - Sort the team by league and aerials won
 *  - Reset each team’s goals to 0.
 *
 */

class SoccerTeam{
	private String team;
	private String league;
	private int goals;
	private double spg;
	private double posPct;
	private double passSuccess;
	private double aerials;
	
	public SoccerTeam(String team, String league, int goals, double spg, double posPct, double passSuccess, double aerials) {
		this.team = team;
		this.league = league;
		this.goals = goals;
		this.spg = spg;
		this.posPct = posPct;
		this.passSuccess = passSuccess;
		this.aerials = aerials;
	}
	
	
	
	public String getTeam() {
		return team;
	}



	public void setTeam(String team) {
		this.team = team;
	}



	public String getLeague() {
		return league;
	}



	public void setLeague(String league) {
		this.league = league;
	}



	public int getGoals() {
		return goals;
	}



	public void setGoals(int goals) {
		this.goals = goals;
	}
	public int resetGoals() {
		this.goals = 0;
		return this.goals;
	}



	public double getSpg() {
		return spg;
	}



	public void setSpg(double spg) {
		this.spg = spg;
	}



	public double getPosPct() {
		return posPct;
	}



	public void setPosPct(double posPct) {
		this.posPct = posPct;
	}



	public double getPassSuccess() {
		return passSuccess;
	}



	public void setPassSuccess(double passSuccess) {
		this.passSuccess = passSuccess;
	}



	public double getAerials() {
		return aerials;
	}



	public void setAerials(double aerials) {
		this.aerials = aerials;
	}
	public void printTeam(){
		System.out.println(this.team + ", " +this.league);
	}



	/**
	 * toString method that has been overridden from Object
	 */
	public String toString(){
		return this.team + ", " +this.league;

	}
	
}

class lambdaSoccer {
	
	public static void main(String[] args) {
		ArrayList<SoccerTeam> teams = new ArrayList<SoccerTeam>();
		
		// Import file 
		Scanner file = inputFile("SoccerStats.txt");
		
		// Parse data & create ArrayList of SoccerTeam objects
		teams = parseTeams(file);
		
		// Lambda - Print the team names and Leagues
		System.out.println("- Team names and leagues");
		teams.forEach(System.out::println);
		
		// Lambda - Print the name and league of the teams that have scored over 5 goals
		System.out.println("\n- Name and league of teams who made more than 5 goals");
		
		teams.stream().filter(
				(t) -> {
					int z=t.getGoals();
					if ( z > 5)
						return true;
					else
						return false;
				
					}
				).forEach(System.out::println);
		
		// Lambda - Average the possession percentage
		double avgPosPct = teams.stream()
				.mapToDouble(u -> u.getPosPct())
				.average()
				.getAsDouble();
		System.out.println("\n- Average of possession percentage: " + String.format("%.1f", avgPosPct));
		
		// Lambda - Sort the teams by league
		System.out.println("\n- Teams sorted by league:");
		Collections.sort(teams, (s1, s2)->s1.getLeague().compareTo(s2.getLeague()));
		for (SoccerTeam sorted:teams){
			System.out.println(sorted);
		}
		
		// Lambda - Sort the teams by pass success percentage
		System.out.println("\n- Teams sorted by pass %:");
		Collections.sort(teams, (s1, s2)-> Double.compare(s2.getPassSuccess(), s1.getPassSuccess()));
		for (SoccerTeam sorted:teams){
			System.out.println(sorted + "\nPass Success: " + String.format("%.1f", sorted.getPassSuccess()));
		}
				
		// Lambda - Sort the team by league and aerials won
		System.out.println("\n- Teams sorted by league and aerials won:");
		Collections.sort(teams, (s1, s2)->{
		    int h = s1.getLeague().compareTo(s2.getLeague());
		    if (h == 0)
		        h = Double.compare(s2.getAerials(), s1.getAerials());
		    return h;
		});
		for (SoccerTeam sorted:teams){
			System.out.println(sorted + "\nAerials won: " + String.format("%.1f",sorted.getAerials()));
		}	
		
		// Lambda - Reset each team’s goals to 0.
		System.out.println("\n- Reset each team's goals to 0");
		teams.forEach(
				(g) -> {g.resetGoals();}
				);
		for (SoccerTeam sorted:teams){
			System.out.println(sorted + "\nGoals: " + sorted.getGoals());
		}	

	}
	
	/**
	 * Takes in a scanner object from a file and creates an array list of Soccer Teams
	 * @param s Scanner of an input file
	 * @return ArrayList of SoccerTeam objects
	 */
	private static ArrayList<SoccerTeam> parseTeams(Scanner s) {
		ArrayList<SoccerTeam> t = new ArrayList<SoccerTeam>();
		
		while (s.hasNextLine()){
            String thisLine = s.nextLine();
        	Scanner temp = new Scanner(thisLine);
            
            String team = temp.useDelimiter("\t").next();
        	String league = temp.useDelimiter("\t").next();
        	int goals = temp.nextInt();
        	double spg = temp.nextFloat();
        	double posPct = temp.nextFloat();
        	double passSuccess = temp.nextFloat();
        	double aerials = temp.nextFloat();
	    	
        	SoccerTeam teamStats = new SoccerTeam(team, league, goals, spg, posPct, passSuccess, aerials);
			t.add(teamStats);
			if (!(s.hasNextLine())){
				temp.close();
			}
		}
		
		return t;
	}
	
	/**
	 * Finds an input file, verifies existing file and returns a
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
