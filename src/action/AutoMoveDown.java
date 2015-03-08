package action;

import org.apache.logging.log4j.LogManager;

import main.Constants;
import main.GameMaker;
import model.SpriteModel;

/**
 * This class has the implementation of the logic for moving the sprite down. It is an auto event
 * so sprite will move down all the time. Also, when it hits the lower bound of the screen then 
 * it will be repositioned to top the screen and will follow the movement from there.
 * */

public class AutoMoveDown implements Action {
	 private static org.apache.logging.log4j.Logger log = LogManager
			.getLogger(AutoMoveDown.class);
	public void performAction(SpriteModel sprite)
	{	
		log.info("AutoMoveDown : performAction : Enter");
		log.debug("performAction : sprite : name - "+sprite.getName()+", Yposition - "+ sprite.getYPosition());
		
			if((sprite.getYPosition() + ActionConstants.Y_VARIANT_OFFSET.getOffsetValue()) < Constants.GAME_BOARD_PANEL_HEIGHT){
				sprite.setYPosition(sprite.getYPosition() + ActionConstants.Y_VARIANT_OFFSET.getOffsetValue());
				sprite.setRectangleTest(sprite.getXPosition(),
						sprite.getYPosition(), (int)sprite.getRectangleTest().getWidth(),
						(int)sprite.getRectangleTest().getHeight());
			}else{
				sprite.setYPosition(ActionConstants.START_OFFSET.getOffsetValue());  
			}
			log.debug("performAction : sprite : name - "+sprite.getName()+", Yposition updated to - "+ sprite.getYPosition());
			log.info("AutoMoveDown : performAction : Exit");	
	}

	@Override
	//TODO: Refactor
	public void performAction(SpriteModel sprite1, SpriteModel sprite2) {
		// TODO Auto-generated method stub
		
	}
}
