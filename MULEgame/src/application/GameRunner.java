package application;

import java.util.ArrayList;

public class GameRunner {
	private ArrayList<Player> playerList;
	
	
	public GameRunner() {
		playerList = new ArrayList<Player>();
		
	}

	
	public void addPlayer(Player plyr) {
		playerList.add(plyr);
	}
}
