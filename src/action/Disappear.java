package action;

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
		if ((sprite1.getRectangleTest()).intersects(sprite2.getRectangleTest())) {
			sprite1.setDestroySpriteFlag(true);
			Utility.getInstance().setGameFlag(3);
		}

	}

	@Override
	public void performAction(SpriteModel sprite) {

	}

}
