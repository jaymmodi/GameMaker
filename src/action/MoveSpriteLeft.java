package action;

import model.SpriteModel;
import main.Constants;

//Move the Sprite left when keyboard left arrow is pressed
public class MoveSpriteLeft implements Action {

	public MoveSpriteLeft() {

	}

	public void performAction(SpriteModel sprite) {
		if ((sprite.getXPosition() - 14) > Constants.LEFT_MARGIN.getValue()) {
			sprite.setXPosition(sprite.getXPosition() - 14);
			sprite.setRectangleTest(sprite.getXPosition(),
					sprite.getYPosition(), (int)sprite.getRectangleTest().getWidth(),
					(int)sprite.getRectangleTest().getHeight());
		}
	}

	@Override
	public void performAction(SpriteModel sprite1, SpriteModel sprite2) {

	}

}
