package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

public class GeneralStoreController implements Initializable, ControlledScreen, Loadable {
	ScreensController myController;
	
	
	@Override
	public void onLoad() {
		// TODO Auto-generated method stub

	}

	@Override
	public void setScreenParent(ScreensController screenParent) {
		myController = screenParent;

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}

	@FXML 
	private Button sellGoodsBTN;
	
	@FXML 
	private Button landAuctionBTN;
	
	
	public void sellGoodsClick() {
		System.out.println("Sell Goods BTN Clicked ");
	}
	
	public void landAuctionClick() {
		System.out.println("Land Auction BTN Clicked ");
	}
	
}
