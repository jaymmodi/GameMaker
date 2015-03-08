package action;

import org.apache.logging.log4j.LogManager;

import main.Constants;
import model.SpriteModel;
/**
 * This class has the logic for moving the respective sprite up on the up key stroke.
 * */
public class MoveSpriteUp implements Action {

	private static org.apache.logging.log4j.Logger log = LogManager
			.getLogger(MoveSpriteUp.class);
	
	public void performAction(SpriteModel sprite) { 
		log.info("MoveSpriteUp : performAction : Enter");
		log.info("MoveSpriteUp : performAction : sprite : name - "+sprite.getName());
		if ((sprite.getYPosition() - ActionConstants.Y_POSITION_OFFSET.getOffsetValue()) > Constants.LEFT_MARGIN ) {
			sprite.setYPosition(sprite.getYPosition() - ActionConstants.Y_POSITION_OFFSET.getOffsetValue());
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
