package application;

public class Player {
	//-------------enums-----------------------------
	public enum Race {HUMAN, FLAPPER, BONZOID, UGAITE, BUZZITE};
	public enum Color {RED, GREEN, PINK, PURPLE};
	
	
	//--------------Instance Variables---------------
	private Race race;
	private Color color;
	private String playerName;
	private int playerNum;
	
	
	
	//-------------Constructor------------------------
	public Player(Race rce, Color clr, String plyrName, int plyrNum) {
		race = rce;
		color = clr;
		playerName = plyrName;
		playerNum = plyrNum;
	}
	
	public String toString() {
		String str =  "Player " + playerNum + "\n Name: " + playerName + "\n" + "Race: " + race + "\n" + "Color: " + color;
		return str;
	}
	

}
