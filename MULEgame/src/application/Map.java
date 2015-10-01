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

public class Map {
	//----------Enum--------------------------------------
	public enum MapSelection {STANDARD, EASTWEST, MAP3};
	//----------------------------------------------------
	
	//--------------Instance Variables--------------------
	private ScreensController mainController;
	final private MapSelection mapSelection;
	final private int XOFFSET = 127;
	final private int YOFFSET = 141;
	private HashMap<Point, Plot> plotMap;
	private ActivePlayer activePlayer;

	Point point;
	
	//----------------------------------------------------
	
	
	//------------Constructor------------------------------
	public Map(MapSelection map, ScreensController mainCntrl) {
		plotMap = new HashMap<>();
		mainController = mainCntrl;
		mapSelection = map;
		setMapPlots(mapSelection);
		showMap(mapSelection);
		
	}
	
	
	
	public void showMap(MapSelection mapSlct) {		
			if (mapSelection == MapSelection.STANDARD) {
				System.out.println("Loading " + mapSelection + " map");
				mainController.setScreen(application.Main.standardMapID);	
			} else if (mapSelection == MapSelection.EASTWEST) {
				System.out.println("Loading " + mapSelection + " map");
				mainController.setScreen(application.Main.eastWestMapID);
			} else if (mapSelection == MapSelection.MAP3) {
				System.out.println("Loading " + mapSelection + " map");
				mainController.setScreen(application.Main.standardMapID);
			}
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
		} else if (player.isPlotOwner(plotCoord)) {
			if (player.hasMule()) {
				plot.installMule(player.getMule());
			}
		}
		return plot;
	}
	
	
	
	private void showTown() {
		mainController.setScreen(application.Main.townID);
	}
	
	private Plot getMapPlot (Point pltCrd) {
		return plotMap.get(pltCrd);
	}
	
	public MapSelection getMapSelection() {
		return mapSelection; 
	}
	
	private void setMapPlots(MapSelection ms) {
        if (ms == MapSelection.STANDARD) {
            plotMap.put(new Point(0, 0), new Plot(new Point(0,0), PlotType.PLAIN));
            plotMap.put(new Point(1, 0), new Plot(new Point(1,0), PlotType.PLAIN));
            plotMap.put(new Point(2, 0), new Plot(new Point(2,0), PlotType.M1));
            plotMap.put(new Point(3, 0), new Plot(new Point(3,0), PlotType.PLAIN));
            plotMap.put(new Point(4, 0), new Plot(new Point(4,0), PlotType.RIVER));
            plotMap.put(new Point(5, 0), new Plot(new Point(5,0), PlotType.PLAIN));
            plotMap.put(new Point(6, 0), new Plot(new Point(6,0), PlotType.M3));
            plotMap.put(new Point(7, 0), new Plot(new Point(7,0), PlotType.PLAIN));
            plotMap.put(new Point(8, 0), new Plot(new Point(8,0), PlotType.PLAIN));
            plotMap.put(new Point(0, 1), new Plot(new Point(0,1), PlotType.PLAIN));
            plotMap.put(new Point(1, 1), new Plot(new Point(1,1), PlotType.M1));
            plotMap.put(new Point(2, 1), new Plot(new Point(2,1), PlotType.PLAIN));
            plotMap.put(new Point(3, 1), new Plot(new Point(3,1), PlotType.PLAIN));
            plotMap.put(new Point(4, 1), new Plot(new Point(4,1), PlotType.RIVER));
            plotMap.put(new Point(5, 1), new Plot(new Point(5,1), PlotType.PLAIN));
            plotMap.put(new Point(6, 1), new Plot(new Point(6,1), PlotType.PLAIN));
            plotMap.put(new Point(7, 1), new Plot(new Point(7,1), PlotType.PLAIN));
            plotMap.put(new Point(8, 1), new Plot(new Point(8,1), PlotType.M3));
            plotMap.put(new Point(0, 2), new Plot(new Point(0,2), PlotType.M3));
            plotMap.put(new Point(1, 2), new Plot(new Point(1,2), PlotType.PLAIN));
            plotMap.put(new Point(2, 2), new Plot(new Point(2,2), PlotType.PLAIN));
            plotMap.put(new Point(3, 2), new Plot(new Point(3,2), PlotType.PLAIN));
            plotMap.put(new Point(4, 2), new Plot(new Point(4,2), PlotType.TOWN));
            plotMap.put(new Point(5, 2), new Plot(new Point(5,2), PlotType.PLAIN));
            plotMap.put(new Point(6, 2), new Plot(new Point(6,2), PlotType.PLAIN));
            plotMap.put(new Point(7, 2), new Plot(new Point(7,2), PlotType.PLAIN));
            plotMap.put(new Point(8, 2), new Plot(new Point(8,2), PlotType.M1));
            plotMap.put(new Point(0, 3), new Plot(new Point(0,3), PlotType.PLAIN));
            plotMap.put(new Point(1, 3), new Plot(new Point(1,3), PlotType.M2));
            plotMap.put(new Point(2, 3), new Plot(new Point(2,3), PlotType.PLAIN));
            plotMap.put(new Point(3, 3), new Plot(new Point(3,3), PlotType.PLAIN));
            plotMap.put(new Point(4, 3), new Plot(new Point(4,3), PlotType.RIVER));
            plotMap.put(new Point(5, 3), new Plot(new Point(5,3), PlotType.PLAIN));
            plotMap.put(new Point(6, 3), new Plot(new Point(6,3), PlotType.M2));
            plotMap.put(new Point(7, 3), new Plot(new Point(7,3), PlotType.PLAIN));
            plotMap.put(new Point(8, 3), new Plot(new Point(8,3), PlotType.PLAIN));
            plotMap.put(new Point(0, 4), new Plot(new Point(0,4), PlotType.PLAIN));
            plotMap.put(new Point(1, 4), new Plot(new Point(1,4), PlotType.PLAIN));
            plotMap.put(new Point(2, 4), new Plot(new Point(2,4), PlotType.M2));
            plotMap.put(new Point(3, 4), new Plot(new Point(3,4), PlotType.PLAIN));
            plotMap.put(new Point(4, 4), new Plot(new Point(4,4), PlotType.RIVER));
            plotMap.put(new Point(5, 4), new Plot(new Point(5,4), PlotType.PLAIN));
            plotMap.put(new Point(6, 4), new Plot(new Point(6,4), PlotType.PLAIN));
            plotMap.put(new Point(7, 4), new Plot(new Point(7,4), PlotType.PLAIN));
            plotMap.put(new Point(8, 4), new Plot(new Point(8,4), PlotType.M2));
        }
	}

	
									
	}
	
