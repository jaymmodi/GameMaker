package action;

import org.apache.logging.log4j.LogManager;

import main.Constants;
import model.SpriteModel;

public class AutoMoveRight implements Action {
	static org.apache.logging.log4j.Logger log = LogManager
			.getLogger(AutoMoveRight.class);
	public void performAction(SpriteModel sprite) {
		log.info("AutoMoveRight : performAction : Enter");
		log.debug("AutoMoveRight : performAction : sprite : name - "+sprite.getName()+", XPosition - "+sprite.getXPosition());
		if ((sprite.getXPosition() + 2) < Constants.RIGHT_MARGIN - 56) {
			sprite.setXPosition(sprite.getXPosition() + 2);
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