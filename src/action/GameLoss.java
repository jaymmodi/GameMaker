package action;

import org.apache.logging.log4j.LogManager;

import controller.Utility;
import model.SpriteModel;

/**
 * Disappear class
 * 
 * Class to perform disappear action when a sprite collides with associated
 * sprite.
 * 
 * @author Team 2
 *
 */
public class GameLoss implements Action {
	private static org.apache.logging.log4j.Logger log = LogManager
			.getLogger(GameLoss.class);
	/*
	 * performs disappear action on one sprite when on collision with associated
	 * sprite.
	 * 
	 * @param sprite1 sets the first sprite
	 * 
	 * @param sprite2 sets the second sprite
	 */
	@Override
	public void performAction(SpriteModel sprite1, SpriteModel sprite2) {
		log.info("GameLoss : performAction : Enter");
		log.debug("GameLoss : performAction : sprite1 : name - "+sprite1.getName());
		log.debug("GameLoss : performAction : sprite2 : name - "+sprite2.getName());
		if ((sprite1.getRectangleTest()).intersects(sprite2.getRectangleTest())) {
			sprite1.setDestroySpriteFlag(true);
			Utility.getInstance().setGameFlag(ActionConstants.GAME_FLAG_LOSS.getOffsetValue());
		}
		log.info("GameLoss : performAction : Exit");

	}

	@Override
	public void performAction(SpriteModel sprite) {

	}

}
