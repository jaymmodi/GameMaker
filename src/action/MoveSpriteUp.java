package action;

import org.apache.logging.log4j.LogManager;

import main.Constants;
import model.SpriteModel;

//Move the Sprite left when keyboard left arrow is pressed
public class MoveSpriteUp implements Action {

	static org.apache.logging.log4j.Logger log = LogManager
			.getLogger(MoveSpriteUp.class);
	
	public MoveSpriteUp() {

	}

	public void performAction(SpriteModel sprite) { 
		log.info("MoveSpriteUp : performAction : Enter");
		log.info("MoveSpriteUp : performAction : sprite : name - "+sprite.getName());
		if ((sprite.getYPosition() - 14) > Constants.LEFT_MARGIN ) {
			sprite.setYPosition(sprite.getYPosition() - 14);
			sprite.setRectangleTest(sprite.getXPosition(), sprite
					.getYPosition(),
					(int) sprite.getRectangleTest().getWidth(), (int) sprite
							.getRectangleTest().getHeight());
		}
		log.info("MoveSpriteUp : performAction : Exit");
	}

	@Override
	public void performAction(SpriteModel sprite1, SpriteModel sprite2) {

	}

}
