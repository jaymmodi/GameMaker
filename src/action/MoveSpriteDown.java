package action;

import org.apache.logging.log4j.LogManager;

import main.Constants;
import model.SpriteModel;

public class MoveSpriteDown implements Action {
	static org.apache.logging.log4j.Logger log = LogManager
			.getLogger(MoveSpriteDown.class);
	
	public MoveSpriteDown() {

	}

	public void performAction(SpriteModel sprite) {
		log.info("MoveSpriteDown : performAction : Enter");
		log.debug("MoveSpriteDown : performAction : sprite : name - "+sprite.getName());
		if ((sprite.getYPosition() + 14) < Constants.GAME_BOARD_PANEL_HEIGHT
				 ) {
			sprite.setYPosition(sprite.getYPosition() + 14);
			sprite.setRectangleTest(sprite.getXPosition(), sprite
					.getYPosition(),
					(int) sprite.getRectangleTest().getWidth(), (int) sprite
							.getRectangleTest().getHeight());
		}
		log.info("MoveSpriteDown : performAction : Exit");
	}

	@Override
	public void performAction(SpriteModel sprite1, SpriteModel sprite2) {

	}
}
