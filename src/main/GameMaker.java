package main;

import org.apache.logging.log4j.LogManager;

import view.GameMakerView;
import controller.GameMakerController;

/**
 * GameMaker class
 * 
 * Main class for GameMaker
 * 
 *
 */
public class GameMaker {
	
	static org.apache.logging.log4j.Logger log = LogManager
			.getLogger(GameMaker.class);

	public static void main(String[] args) {
		log.info("GameMaker : Enter");
		GameMakerView theView = new GameMakerView();
		GameMakerController theController = new GameMakerController(theView);
		theView.setVisible(true);
		log.info("GameMaker : Exit");
	}
}