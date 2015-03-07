package action;

import org.apache.logging.log4j.LogManager;

import model.SpriteModel;
import main.Constants;

//Move the Sprite right when keyboard right arrow is pressed
public class MoveSpriteRight implements Action {
	static org.apache.logging.log4j.Logger log = LogManager
			.getLogger(MoveSpriteRight.class);

	public void performAction(SpriteModel sprite) {
		log.info("MoveSpriteRight : performAction : Enter");
		log.debug("MoveSpriteRight : performAction : sprite : name - "+sprite.getName());
		if ((sprite.getXPosition() + 14) < Constants.RIGHT_MARGIN.getValue() - 56) {
			sprite.setXPosition(sprite.getXPosition() + 14);
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
