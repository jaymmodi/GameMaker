package action;

import org.apache.logging.log4j.LogManager;

import model.SpriteModel;
import main.Constants;
/**
 * This class has the logic for moving the respective sprite right on the right key stroke.
 * */
public class MoveSpriteRight implements Action {
	private static org.apache.logging.log4j.Logger log = LogManager
			.getLogger(MoveSpriteRight.class);

	public void performAction(SpriteModel sprite) {
		log.info("MoveSpriteRight : performAction : Enter");
		log.debug("MoveSpriteRight : performAction : sprite : name - "+sprite.getName());
		if ((sprite.getXPosition() + ActionConstants.X_POSITION_OFFSET.getOffsetValue()) < Constants.RIGHT_MARGIN  - ActionConstants.RIGHT_MARGIN_OFFSET.getOffsetValue()) {
			sprite.setXPosition(sprite.getXPosition() + ActionConstants.X_POSITION_OFFSET.getOffsetValue());
			sprite.setRectangleTest(sprite.getXPosition(),
					sprite.getYPosition(), (int)sprite.getRectangleTest().getWidth(),
					(int)sprite.getRectangleTest().getHeight());
		}
		log.info("MoveSpriteRight : performAction : Exit");
	}

	@Override
	public void performAction(SpriteModel sprite1, SpriteModel sprite2) {

	}

}
