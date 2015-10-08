package application;

import controller.ScreensController;

public class EastWestMap extends Map {

	public EastWestMap(ScreensController mainCntrl) {
		super(mainCntrl);
		setMapPlots();
		showMap();
	
	}

	public void showMap() {		
		System.out.println("Loading East West map");
		mainController.setScreen(application.Main.eastWestMapID);	
	}

	private void setMapPlots() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public MapSelection getMapSelection() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getMapID() {
		// TODO Auto-generated method stub
		return null;
	}

}
