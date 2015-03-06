package action;

import main.Constants;
import model.SpriteModel;

public class AutoMoveUp implements Action{
	@Override
	public void performAction(SpriteModel sprite1, SpriteModel sprite2) {
		
	}
	
	public void performAction(SpriteModel sprite)
	{	//TODO: check the values for the following version	
		if((sprite.getYPosition() - 2) > Constants.LEFT_MARGIN.getValue()){
			sprite.setYPosition(sprite.getYPosition() - 2);
			sprite.setRectangleTest(sprite.getXPosition(), sprite.getYPosition(), sprite.getImage().getWidth(null),sprite.getImage().getHeight(null));
			}else{
				sprite.setYPosition(Constants.GAME_BOARD_PANEL_HEIGHT.getValue());
			}
	}

}
