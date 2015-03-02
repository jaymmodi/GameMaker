package main;

import view.GameMakerView;
import controller.GameMakerController;

/**
 * GameMaker class
 * 
 * Main class for GameMaker
 * 
 * @author Amritanshu
 *
 */
public class GameMaker {

	public static void main(String[] args) {
		GameMakerView theView = new GameMakerView();
		GameMakerController theController = new GameMakerController(theView);
		theView.setVisible(true);
	}
}