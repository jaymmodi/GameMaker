package action;

import org.apache.logging.log4j.LogManager;

import model.SpriteModel;
import main.Constants;

/**
 * This class has the logic for moving the respective sprite left on the left key stroke.
 * */
public class MoveSpriteLeft implements Action {

	private static org.apache.logging.log4j.Logger log = LogManager
			.getLogger(MoveSpriteLeft.class);

	public void performAction(SpriteModel sprite) {
		log.info("MoveSpriteLeft : performAction : Enter");
		log.debug("MoveSpriteLeft : performAction : sprite : name - "+sprite.getName());
		if ((sprite.getXPosition() - ActionConstants.X_POSITION_OFFSET.getOffsetValue()) > Constants.LEFT_MARGIN ) {
			sprite.setXPosition(sprite.getXPosition() - ActionConstants.X_POSITION_OFFSET.getOffsetValue());
			sprite.setRectangleTest(sprite.getXPosition(),
					sprite.getYPosition(), (int)sprite.getRectangleTest().getWidth(),
					(int)sprite.getRectangleTest().getHeight());
		}
		log.info("MoveSpriteLeft : performAction : Exit");
	}

	@Override
	public void performAction(SpriteModel sprite1, SpriteModel sprite2) {

	}

}
