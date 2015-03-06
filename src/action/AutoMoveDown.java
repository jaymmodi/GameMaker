package action;

import main.Constants;
import model.SpriteModel;

public class AutoMoveDown implements Action {
	public void performAction(SpriteModel sprite)
	{	
		//TODO: check the values for the following version	
			if((sprite.getYPosition() + 2) < Constants.GAME_BOARD_PANEL_HEIGHT.getValue()){
				sprite.setYPosition(sprite.getYPosition() + 2);
			}else{
				sprite.setYPosition(0);
			}
	}

	@Override
	public void performAction(SpriteModel sprite1, SpriteModel sprite2) {
		// TODO Auto-generated method stub
		
	}
}
