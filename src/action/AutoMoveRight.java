package action;

import org.apache.logging.log4j.LogManager;

import main.Constants;
import model.SpriteModel;

/**
 * This class has the implementation of the logic for moving the sprite right. It is an auto event
 * so sprite will move right all the time. Also, when it hits the right bound of the screen then 
 * it will be repositioned to left most position of the screen and will follow the movement from there.
 * */
public class AutoMoveRight implements Action {
	private static org.apache.logging.log4j.Logger log = LogManager
			.getLogger(AutoMoveRight.class);
	public void performAction(SpriteModel sprite) {
		log.info("AutoMoveRight : performAction : Enter");
		log.debug("AutoMoveRight : performAction : sprite : name - "+sprite.getName()+", XPosition - "+sprite.getXPosition());
		if ((sprite.getXPosition() + ActionConstants.X_VARIANT_OFFSET.getOffsetValue()) < Constants.RIGHT_MARGIN - ActionConstants.RIGHT_MARGIN_OFFSET.getOffsetValue()) {
			sprite.setXPosition(sprite.getXPosition() + ActionConstants.X_VARIANT_OFFSET.getOffsetValue());
			sprite.setRectangleTest(sprite.getXPosition(),
					sprite.getYPosition(), (int)sprite.getRectangleTest().getWidth(),
					(int)sprite.getRectangleTest().getHeight());
		} else{
			sprite.setXPosition(Constants.LEFT_MARGIN);
		}
		log.debug("AutoMoveRight : performAction : sprite : name - "+sprite.getName()+", XPosition updated to- "+sprite.getXPosition());
		log.info("AutoMoveRight : performAction : Exit");
	}

	@Override
	public void performAction(SpriteModel sprite1, SpriteModel sprite2) {

	}

}