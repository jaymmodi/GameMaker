package action;

import org.apache.logging.log4j.LogManager;

import main.Constants;
import model.SpriteModel;

/**
 * This class has the implementation of the logic for moving the sprite up. It is an auto event
 * so sprite will move up all the time. Also, when it hits the upper bound of the screen then 
 * it will be repositioned to bottom of the screen and will follow the movement from there.
 * */
public class AutoMoveUp implements Action{
	
	static org.apache.logging.log4j.Logger log = LogManager
			.getLogger(AutoMoveUp.class);
	
	@Override
	public void performAction(SpriteModel sprite1, SpriteModel sprite2) {
		
	}
	
	public void performAction(SpriteModel sprite)
	{	
		log.info("AutoMoveUp : performAction : Enter");
		log.debug("AutoMoveUp : performAction : sprite : name - "+sprite.getName()+" ,YPosition - "+sprite.getYPosition());
		if((sprite.getYPosition() - ActionConstants.Y_VARIANT_OFFSET.getOffsetValue()) > Constants.LEFT_MARGIN){
			sprite.setYPosition(sprite.getYPosition() - ActionConstants.Y_VARIANT_OFFSET.getOffsetValue());
			sprite.setRectangleTest(sprite.getXPosition(),
					sprite.getYPosition(), (int)sprite.getRectangleTest().getWidth(),
					(int)sprite.getRectangleTest().getHeight());	
		}else{
				sprite.setYPosition(Constants.GAME_BOARD_PANEL_HEIGHT);
			}
		log.debug("AutoMoveUp : performAction : sprite : name - "+sprite.getName()+" ,YPosition updated to - "+sprite.getYPosition());
		log.info("AutoMoveUp : performAction : Exit");
	}

}
