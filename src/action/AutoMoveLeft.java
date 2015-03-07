package action;

import org.apache.logging.log4j.LogManager;

import main.Constants;
import model.SpriteModel;

//Move the Sprite left when keyboard left arrow is pressed
public class AutoMoveLeft implements Action{
	static org.apache.logging.log4j.Logger log = LogManager
			.getLogger(AutoMoveLeft.class);
	public void performAction(SpriteModel sprite)
	{		
		log.info("AutoMoveLeft : performAction : Enter");
		log.debug("AutoMoveLeft : performAction : sprite : name - "+sprite.getName()+", XPosition - "+sprite.getXPosition());
		if((sprite.getXPosition() - 2) > Constants.LEFT_MARGIN.getValue()){
			sprite.setXPosition(sprite.getXPosition() - 2);
			sprite.setRectangleTest(sprite.getXPosition(),
					sprite.getYPosition(), (int)sprite.getRectangleTest().getWidth(),
					(int)sprite.getRectangleTest().getHeight());
		}else {
			sprite.setXPosition(Constants.RIGHT_MARGIN.getValue());
		}
		log.debug("AutoMoveLeft : performAction : sprite : name - "+sprite.getName()+", XPosition updated to - "+sprite.getXPosition());
		log.info("AutoMoveLeft : performAction : Exit");
	}

	@Override
	public void performAction(SpriteModel sprite1, SpriteModel sprite2){
		
	}	

}
