package action;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import org.apache.logging.log4j.LogManager;

import controller.Utility;
import model.SpriteModel;
import main.Constants;

/**
 *
 * @author Team 2
 *
 */
public class MoveSprite implements Action {
	private static org.apache.logging.log4j.Logger log = LogManager.getLogger(MoveSprite.class);

	boolean UnitTestFlag = false;

	public void performAction(SpriteModel sprite) {
		log.info("MoveSprite : performAction : Enter");
		log.debug("MoveSprite : performAction : sprite : name - " + sprite.getName());
		int x = sprite.getXPosition() + sprite.getXDirection();
		int y = sprite.getYPosition() + sprite.getYDirection();
		sprite.setXPosition(sprite.getXPosition() + sprite.getXDirection());
		sprite.setYPosition(sprite.getYPosition() + sprite.getYDirection());

		if (UnitTestFlag == false) {
			sprite.setRectangleTest(x, y, sprite.getImage().getWidth(null), sprite.getImage().getHeight(null));
		}

		if (sprite.getXPosition() == Constants.LEFT_MARGIN) {
			sprite.setXDirection(ActionConstants.POSITIVE_X_DIRECTION.getOffsetValue());
		}

		if (sprite.getXPosition() == Constants.RIGHT_MARGIN) {
			sprite.setXDirection(ActionConstants.NEGATIVE_X_DIRECTION.getOffsetValue());
		}

		if (sprite.getYPosition() == Constants.LEFT_MARGIN) {
			sprite.setYDirection(ActionConstants.POSITIVE_X_DIRECTION.getOffsetValue());
		}

		if (isUnitTestFlag() == false) {
			// To account for Game Lost condition
			if (sprite.getRectangle(sprite).getMaxY() > ActionConstants.LAYOUT_UPPER_BOUND.getOffsetValue()) {
				Utility.getInstance().setGameFlag(ActionConstants.GAME_FLAG_LOSS.getOffsetValue());
				try {
					File soundFile = new File(getClass().getClassLoader().getResource("sounds/lost.wav").toURI());
					System.out.println(soundFile);
					AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
					Clip clip = AudioSystem.getClip();
					clip.open(audioIn);
					clip.start();
				} catch (Exception ex) {
					System.out.println(ex.getMessage());
				}
			}
		}
		log.info("MoveSprite : performAction : Exit");
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
