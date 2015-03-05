package action;

import main.Constants;
import model.SpriteModel;

public class AutoMoveRight implements Action {
	
	public void performAction(SpriteModel sprite)
	{
		if((sprite.getXPosition() + 14) < Constants.RIGHT_MARGIN.getValue() - 56)
			sprite.setXPosition(sprite.getXPosition() + 14);
	}

	@Override
	public void performAction(SpriteModel sprite1, SpriteModel sprite2) {
		
	}	
	
}