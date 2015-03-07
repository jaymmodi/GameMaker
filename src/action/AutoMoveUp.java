package action;

import org.apache.logging.log4j.LogManager;

import main.Constants;
import model.SpriteModel;

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
		if((sprite.getYPosition() - 2) > Constants.LEFT_MARGIN.getValue()){
			sprite.setYPosition(sprite.getYPosition() - 2);
			sprite.setRectangleTest(sprite.getXPosition(), sprite.getYPosition(), sprite.getImage().getWidth(null),sprite.getImage().getHeight(null));
			sprite.setRectangleTest(sprite.getXPosition(),
					sprite.getYPosition(), (int)sprite.getRectangleTest().getWidth(),
					(int)sprite.getRectangleTest().getHeight());	
		}else{
				sprite.setYPosition(Constants.GAME_BOARD_PANEL_HEIGHT.getValue());
			}
		log.debug("AutoMoveUp : performAction : sprite : name - "+sprite.getName()+" ,YPosition updated to - "+sprite.getYPosition());
		log.info("AutoMoveUp : performAction : Exit");
	}

}
