package action;

import main.Constants;
import model.SpriteModel;

//Move the Sprite left when keyboard left arrow is pressed
public class MoveSpriteUp implements Action {

	public MoveSpriteUp() {

	}

	public void performAction(SpriteModel sprite) { // TODO: check the values
													// for the following version
		if ((sprite.getYPosition() - 14) > Constants.LEFT_MARGIN.getValue()) {
			sprite.setYPosition(sprite.getYPosition() - 14);
			sprite.setRectangleTest(sprite.getXPosition(),
					sprite.getYPosition(), sprite.getImage().getWidth(null),
					sprite.getImage().getHeight(null));
		}
	}

	@Override
	public void performAction(SpriteModel sprite1, SpriteModel sprite2) {

	}

}
