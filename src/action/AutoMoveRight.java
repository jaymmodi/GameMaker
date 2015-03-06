package action;

import main.Constants;
import model.SpriteModel;

public class AutoMoveRight implements Action {
	
	public void performAction(SpriteModel sprite)
	{
		if((sprite.getXPosition() + 2) < Constants.RIGHT_MARGIN.getValue() - 56)
			sprite.setXPosition(sprite.getXPosition() + 2);
		else
			sprite.setXPosition(Constants.LEFT_MARGIN.getValue());
	}

	@Override
	public void performAction(SpriteModel sprite1, SpriteModel sprite2) {
		
	}	
	
}