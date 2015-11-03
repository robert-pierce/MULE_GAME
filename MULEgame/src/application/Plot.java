package application;

import java.awt.*;
import java.io.Serializable;

import application.GameRunner.PlotType;

public class Plot implements Serializable{
	
	private static final long serialVersionUID = -4259329797261826661L;
	private Player owner;
	private PlotType type;
	private Point plotCoord;
	private Mule mule;
	
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
	
	public void installMule(Mule mul) {
		mule = mul;
	}
	
	public Mule getMule() {
		return mule;
	}
	
	public void generateResources() { }
	
	public Point getCenter() {
		double x = plotCoord.getX();
	    double y = plotCoord.getY();
	    int centerX = (int) Math.floor((x * 127) + 63.5);
	    int centerY = (int) Math.floor((y * 141) + 70.5);
	    return new Point(centerX, centerY);
	}

	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append("Plot Coodinate: " + plotCoord + "\n");
		str.append("Plot owner: " + owner + "\n");
		str.append("Plot type: " + type);
		return str.toString();
	}
	
}
