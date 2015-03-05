package action;

import main.Constants;
import model.SpriteModel;

public class MoveSpriteDown implements Action {
	public MoveSpriteDown()
	{

	}
	
	public void performAction(SpriteModel sprite)
	{		
		if((sprite.getYPosition() + 14) < Constants.GAME_BOARD_PANEL_HEIGHT.getValue())
			sprite.setYPosition(sprite.getYPosition() + 14);
	}

	@Override
	public void performAction(SpriteModel sprite1, SpriteModel sprite2){
		
	}	
}
