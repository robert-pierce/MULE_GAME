package application;

public class Player {
	//-------------enums-----------------------------
	public enum Race {RACE1, RACE2, RACE3, RACE4};
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
	
	

}
