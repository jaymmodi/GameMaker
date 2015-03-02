package action;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import controller.Utility;
import model.SpriteModel;
import main.Constants;

/**
 * 
 * @author Team 2
 *
 */
public class MoveSprite implements Action {
	
	boolean UnitTestFlag = false;

	public void performAction(SpriteModel sprite){

		int x=sprite.getXPosition() + sprite.getXDirection();
		int y=sprite.getYPosition() + sprite.getYDirection();
		sprite.setXPosition(sprite.getXPosition() + sprite.getXDirection());
		sprite.setYPosition(sprite.getYPosition() + sprite.getYDirection());

		if(UnitTestFlag==false)
		{
		sprite.setRectangleTest(x, y, sprite.getImage().getWidth(null), sprite.getImage().getHeight(null));
		}

		if (sprite.getXPosition() == Constants.LEFT_MARGIN.getValue()) {
			sprite.setXDirection(1);
		}

		if (sprite.getXPosition() == Constants.RIGHT_MARGIN.getValue()) {
			sprite.setXDirection(-1); 
		}

		if (sprite.getYPosition() == Constants.LEFT_MARGIN.getValue()) {
			sprite.setYDirection(1);
		}
		
		
		if(isUnitTestFlag()==false)
		{
		//To account for Game Lost condition
		if (sprite.getRectangle(sprite).getMaxY() > 600) {
			Utility.getInstance().setGameFlag(2);		
			try {
				File soundFile = new File(getClass().getClassLoader().getResource("sounds/lost.wav").toURI());
				System.out.println(soundFile);
				AudioInputStream audioIn = AudioSystem
					.getAudioInputStream(soundFile);
				Clip clip = AudioSystem.getClip();
				clip.open(audioIn);
				clip.start();
			} catch (Exception ex) {
				System.out.println(ex.getMessage());
			}
		}
		}
	}

	@Override
	public void performAction(SpriteModel sprite1, SpriteModel sprite2) {
		
	}

	public boolean isUnitTestFlag() {
		return UnitTestFlag;
	}

	public void setUnitTestFlag(boolean unitTestFlag) {
		UnitTestFlag = unitTestFlag;
	}
}
