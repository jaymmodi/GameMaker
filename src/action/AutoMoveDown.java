package action;

import org.apache.logging.log4j.LogManager;

import main.Constants;
import main.GameMaker;
import model.SpriteModel;

public class AutoMoveDown implements Action {
	static org.apache.logging.log4j.Logger log = LogManager
			.getLogger(AutoMoveDown.class);
	public void performAction(SpriteModel sprite)
	{	
		log.info("AutoMoveDown : performAction : Enter");
		log.debug("performAction : sprite : name - "+sprite.getName()+", Yposition - "+ sprite.getYPosition());
		
			if((sprite.getYPosition() + 2) < Constants.GAME_BOARD_PANEL_HEIGHT){
				sprite.setYPosition(sprite.getYPosition() + 2);
				sprite.setRectangleTest(sprite.getXPosition(),
						sprite.getYPosition(), (int)sprite.getRectangleTest().getWidth(),
						(int)sprite.getRectangleTest().getHeight());
			}else{
				sprite.setYPosition(0);  
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
