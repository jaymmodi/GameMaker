package action;

import main.Constants;
import model.SpriteModel;

//Move the Sprite left when keyboard left arrow is pressed
public class AutoMoveLeft implements Action{
	
	public void performAction(SpriteModel sprite)
	{		
		if((sprite.getXPosition() - 2) > Constants.LEFT_MARGIN.getValue()){
			sprite.setXPosition(sprite.getXPosition() - 2);
		}else {
			sprite.setXPosition(Constants.RIGHT_MARGIN.getValue());
		}
	}

	@Override
	public void performAction(SpriteModel sprite1, SpriteModel sprite2){
		
	}	

}
