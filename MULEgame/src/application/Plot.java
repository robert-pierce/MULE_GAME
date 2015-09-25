package application;

import java.awt.*;

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
	
	public Point getCenter() {
		double x = plotCoord.getX();
	    double y = plotCoord.getY();
	    int centerX = (int) Math.floor((x * 127) + 63.5);
	    int centerY = (int) Math.floor((y * 141) + 70.5);
	    return new Point(centerX, centerY);
	}
	
}
