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
public class Disappear implements Action {
	static org.apache.logging.log4j.Logger log = LogManager
			.getLogger(Disappear.class);
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
		log.info("Disappear : performAction : Enter");
	       log.debug("Disappear : performAction : sprite1 : name - "+sprite1.getName());
	       log.debug("Disappear : performAction : sprite2 : name - "+sprite2.getName());
		if ((sprite1.getRectangleTest()).intersects(sprite2.getRectangleTest())) {
			sprite1.setDestroySpriteFlag(true);
			//Utility.getInstance().setGameFlag(2);
		}
		log.info("Disappear : performAction : Exit");

	}

	@Override
	public void performAction(SpriteModel sprite) {

	}

}
