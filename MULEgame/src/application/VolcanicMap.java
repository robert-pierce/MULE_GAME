package application;

import controller.ScreensController;

import java.awt.Point;

import application.GameRunner.PlotType;
import application.Map.MapSelection;


public class VolcanicMap extends Map {
	private static final long serialVersionUID = 5578973656638849370L;

	public VolcanicMap(ScreensController mainCntrl) {
		super(mainCntrl);
		setMapPlots();
		showMap();
	}

	@Override
	public MapSelection getMapSelection() {
		return MapSelection.VOLCANIC;
	}

	@Override
	public String getMapID() {
		return Main.volcanicMapID;
	}

	@Override
	public void showMap() {
		System.out.println("Loading Volcanic map");
		mainController.setScreen(application.Main.volcanicMapID);
		
	}

	private void setMapPlots() {
		plotMap.put(new Point(0, 0), new Plot(new Point(0,0), PlotType.RIVER));
        plotMap.put(new Point(1, 0), new Plot(new Point(1,0), PlotType.RIVER));
        plotMap.put(new Point(2, 0), new Plot(new Point(2,0), PlotType.RIVER));
        plotMap.put(new Point(3, 0), new Plot(new Point(3,0), PlotType.PLAIN));
        plotMap.put(new Point(4, 0), new Plot(new Point(4,0), PlotType.PLAIN));
        plotMap.put(new Point(5, 0), new Plot(new Point(5,0), PlotType.SWAMP));
        plotMap.put(new Point(6, 0), new Plot(new Point(6,0), PlotType.M3));
        plotMap.put(new Point(7, 0), new Plot(new Point(7,0), PlotType.PLAIN));
        plotMap.put(new Point(8, 0), new Plot(new Point(8,0), PlotType.VOLCANO));
        plotMap.put(new Point(0, 1), new Plot(new Point(0,1), PlotType.PLAIN));
        plotMap.put(new Point(1, 1), new Plot(new Point(1,1), PlotType.VOLCANO));
        plotMap.put(new Point(2, 1), new Plot(new Point(2,1), PlotType.RIVER));
        plotMap.put(new Point(3, 1), new Plot(new Point(3,1), PlotType.RIVER));
        plotMap.put(new Point(4, 1), new Plot(new Point(4,1), PlotType.M2));
        plotMap.put(new Point(5, 1), new Plot(new Point(5,1), PlotType.M1));
        plotMap.put(new Point(6, 1), new Plot(new Point(6,1), PlotType.PLAIN));
        plotMap.put(new Point(7, 1), new Plot(new Point(7,1), PlotType.PLAIN));
        plotMap.put(new Point(8, 1), new Plot(new Point(8,1), PlotType.M3));
        plotMap.put(new Point(0, 2), new Plot(new Point(0,2), PlotType.PLAIN));
        plotMap.put(new Point(1, 2), new Plot(new Point(1,2), PlotType.PLAIN));
        plotMap.put(new Point(2, 2), new Plot(new Point(2,2), PlotType.M3));
        plotMap.put(new Point(3, 2), new Plot(new Point(3,2), PlotType.RIVER));
        plotMap.put(new Point(4, 2), new Plot(new Point(4,2), PlotType.TOWN));
        plotMap.put(new Point(5, 2), new Plot(new Point(5,2), PlotType.M3));
        plotMap.put(new Point(6, 2), new Plot(new Point(6,2), PlotType.PLAIN));
        plotMap.put(new Point(7, 2), new Plot(new Point(7,2), PlotType.PLAIN));
        plotMap.put(new Point(8, 2), new Plot(new Point(8,2), PlotType.M1));
        plotMap.put(new Point(0, 3), new Plot(new Point(0,3), PlotType.PLAIN));
        plotMap.put(new Point(1, 3), new Plot(new Point(1,3), PlotType.PLAIN));
        plotMap.put(new Point(2, 3), new Plot(new Point(2,3), PlotType.SWAMP));
        plotMap.put(new Point(3, 3), new Plot(new Point(3,3), PlotType.M1));
        plotMap.put(new Point(4, 3), new Plot(new Point(4,3), PlotType.RIVER));
        plotMap.put(new Point(5, 3), new Plot(new Point(5,3), PlotType.RIVER));
        plotMap.put(new Point(6, 3), new Plot(new Point(6,3), PlotType.RIVER));
        plotMap.put(new Point(7, 3), new Plot(new Point(7,3), PlotType.RIVER));
        plotMap.put(new Point(8, 3), new Plot(new Point(8,3), PlotType.RIVER));
        plotMap.put(new Point(0, 4), new Plot(new Point(0,4), PlotType.PLAIN));
        plotMap.put(new Point(1, 4), new Plot(new Point(1,4), PlotType.M2));
        plotMap.put(new Point(2, 4), new Plot(new Point(2,4), PlotType.PLAIN));
        plotMap.put(new Point(3, 4), new Plot(new Point(3,4), PlotType.M1));
        plotMap.put(new Point(4, 4), new Plot(new Point(4,4), PlotType.PLAIN));
        plotMap.put(new Point(5, 4), new Plot(new Point(5,4), PlotType.SWAMP));
        plotMap.put(new Point(6, 4), new Plot(new Point(6,4), PlotType.M3));
        plotMap.put(new Point(7, 4), new Plot(new Point(7,4), PlotType.PLAIN));
        plotMap.put(new Point(8, 4), new Plot(new Point(8,4), PlotType.M2));	
	}

}
