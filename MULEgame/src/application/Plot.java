package application;

import java.awt.Point;

import application.GameRunner.PlotType;

public class Plot {
	
	private Player owner;
	private PlotType type;
	private Point plotCoord;
	
	public Plot(Point pltCrd, PlotType type) {
		this.type = type;
		plotCoord = pltCrd;
	}
	
	public void setOwner(Player player) {
		owner = player;
	}
	
	public Player getOwner() {
		return owner;
	}
	
	public PlotType getType() {
		return type;
	}
	
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append("Plot Coodinate: " + plotCoord + "\n");
		str.append("Plot owner: " + owner + "\n");
		str.append("Plot type: " + type);
		return str.toString();
	}
	
}
