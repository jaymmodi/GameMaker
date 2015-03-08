package action;

import org.apache.logging.log4j.LogManager;

import main.Constants;
import model.SpriteModel;

/**
 * This class has the implementation of the logic for moving the sprite left . It is an auto event
 * so sprite will move left all the time. Also, when it hits the left bound of the screen then 
 * it will be repositioned to right most position the screen and will follow the movement from there.
 * */
public class AutoMoveLeft implements Action{
	private static org.apache.logging.log4j.Logger log = LogManager
			.getLogger(AutoMoveLeft.class);
	public void performAction(SpriteModel sprite)
	{		
		log.info("AutoMoveLeft : performAction : Enter");
		log.debug("AutoMoveLeft : performAction : sprite : name - "+sprite.getName()+", XPosition - "+sprite.getXPosition());
		if((sprite.getXPosition() - ActionConstants.X_VARIANT_OFFSET.getOffsetValue()) > Constants.LEFT_MARGIN){
			sprite.setXPosition(sprite.getXPosition() - ActionConstants.X_VARIANT_OFFSET.getOffsetValue());
			sprite.setRectangleTest(sprite.getXPosition(),
					sprite.getYPosition(), (int)sprite.getRectangleTest().getWidth(),
					(int)sprite.getRectangleTest().getHeight());
		}else {
			sprite.setXPosition(Constants.RIGHT_MARGIN);
		}
		log.debug("AutoMoveLeft : performAction : sprite : name - "+sprite.getName()+", XPosition updated to - "+sprite.getXPosition());
		log.info("AutoMoveLeft : performAction : Exit");
	}

	@Override
	public void performAction(SpriteModel sprite1, SpriteModel sprite2){
		
	}	

}
