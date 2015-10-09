package application;

import java.awt.Point;
import java.util.HashMap;

import application.GameRunner.ActivePlayer;
import application.GameRunner.PlotType;
import application.Map.MapSelection;
import controller.ScreensController;
import controller.playerConfigController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import jfx.messagebox.MessageBox;

public abstract class Map {
	//----------Enum--------------------------------------
	public enum MapSelection {STANDARD, EASTWEST, MAP3};
	//----------------------------------------------------
	
	//--------------Instance Variables--------------------
	protected ScreensController mainController;
	//final private MapSelection mapSelection;
	final private int XOFFSET = 127;
	final private int YOFFSET = 141;
	protected HashMap<Point, Plot> plotMap;
	private ActivePlayer activePlayer;

	Point point;
	
	//----------------------------------------------------
	
	
	//------------Constructor------------------------------
	public Map(ScreensController mainCntrl) {
		plotMap = new HashMap<>();
		mainController = mainCntrl;
		//mapSelection = map;
		//setMapPlots(mapSelection);
		//showMap(mapSelection);
		
	}
	
	
	public Plot purchasePlot(double xCor, double yCor, Player player, boolean firstTwoRoundsFlag) {
		Point plotCoord;
		Plot plot;
		int xCoord = (int) (xCor / XOFFSET);
		int yCoord = (int) (yCor / YOFFSET); 
		plotCoord = new Point(xCoord, yCoord);
		plot = getMapPlot(plotCoord);
		
		if (plot.getOwner() == null && !plot.getType().equals(PlotType.TOWN)) {
			if (firstTwoRoundsFlag) {
				plot.setOwner(player);
				player.addPlot(plotCoord, plot);
				System.out.println(plot);
			} else if (player.getMoney() >= 300) {
				plot.setOwner(player);
				player.addPlot(plotCoord, plot);
				player.payForPlot();
				System.out.println(plot);
			} else {
				System.out.println(plot);
				MessageBox.show(Main.game.getScene().getWindow(),
				         "Not Enough Money To Purchase a Plot",
				         "Information dialog",
				         MessageBox.ICON_INFORMATION | MessageBox.OK);
				return null;
			}
		} else if (plot.getType().equals(PlotType.TOWN)) {
			System.out.println(plot);
			MessageBox.show(Main.game.getScene().getWindow(),
			         "The Town is not for Sale!",
			         "Information dialog",
			         MessageBox.ICON_INFORMATION | MessageBox.OK);
			return null;
		} else {
			System.out.println(plot);
			MessageBox.show(Main.game.getScene().getWindow(),
			         "This Plot is Already Owned",
			         "Information dialog",
			         MessageBox.ICON_INFORMATION | MessageBox.OK);
			return null;
		}
		
		return plot;
	}
	
	
	
	public Plot placeMule(double xCor, double yCor, Player player) {
		Point plotCoord;
		Plot plot;
		int xCoord = (int) (xCor / XOFFSET);
		int yCoord = (int) (yCor / YOFFSET); 
		plotCoord = new Point(xCoord, yCoord);
		plot = getMapPlot(plotCoord);
		
		System.out.println("Trying to place mule on: " + plot);
		
		
		if (plot.getType().equals(PlotType.TOWN)) {
			System.out.println("Loading Town");
			showTown();
		
		} else if (player.hasMule()) {
			if (player.isPlotOwner(plotCoord)) {
				plot.installMule(player.getMule());
				player.removeMule();
				return plot;
			} else {
				player.removeMule();
				MessageBox.show(Main.game.getScene().getWindow(),
				         "Your Mule has run away",
				         "Information dialog",
				         MessageBox.ICON_INFORMATION | MessageBox.OK);
				return null;
			}
		} else {
			MessageBox.show(Main.game.getScene().getWindow(),
			         "You do not own a Mule",
			         "Information dialog",
			         MessageBox.ICON_INFORMATION | MessageBox.OK);
			return null;
		}
			
		return null;
	}
	
	
	
	private void showTown() {
		mainController.setScreen(application.Main.townID);
	}
	
	private Plot getMapPlot (Point pltCrd) {
		return plotMap.get(pltCrd);
	}
	
	public abstract MapSelection getMapSelection();
	
	public abstract String getMapID();
									
}
	
