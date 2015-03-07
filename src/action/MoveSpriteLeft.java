package action;

import org.apache.logging.log4j.LogManager;

import model.SpriteModel;
import main.Constants;

//Move the Sprite left when keyboard left arrow is pressed
public class MoveSpriteLeft implements Action {

	static org.apache.logging.log4j.Logger log = LogManager
			.getLogger(MoveSpriteLeft.class);
	
	public MoveSpriteLeft() {

	}

	public void performAction(SpriteModel sprite) {
		log.info("MoveSpriteLeft : performAction : Enter");
		log.debug("MoveSpriteLeft : performAction : sprite : name - "+sprite.getName());
		if ((sprite.getXPosition() - 14) > Constants.LEFT_MARGIN.getValue()) {
			sprite.setXPosition(sprite.getXPosition() - 14);
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
