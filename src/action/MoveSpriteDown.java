package action;

import main.Constants;
import model.SpriteModel;

public class MoveSpriteDown implements Action {
	public MoveSpriteDown()
	{

	}
	
	public void performAction(SpriteModel sprite)
	{	//TODO: check the values for the following version	
		if((sprite.getYPosition() - 14) > Constants.LEFT_MARGIN.getValue())
			sprite.setYPosition(sprite.getYPosition() + 14);
	}

	@Override
	public void performAction(SpriteModel sprite1, SpriteModel sprite2){
		
	}	
}
