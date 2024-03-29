package action;

import java.awt.Image;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;

import org.apache.logging.log4j.LogManager;

import controller.Utility;
import event.AssociateEvent;
import event.Collision;
import event.Events;
import model.SpriteModel;
import main.Constants;

/**
 * This behavior will be used by the sprite which is supposed to have shooting feature.
 * @author Team 2
 *
 */
public class FireAction implements Action {

	ArrayList<SpriteModel> fireActionSprites = new ArrayList<SpriteModel>();

	private static org.apache.logging.log4j.Logger log = LogManager.getLogger(FireAction.class);

	public void performAction(SpriteModel sprite) {
		log.info("FireAction : performAction : Enter");
		log.debug("FireAction : performAction : sprite : name - " + sprite.getName());
		SpriteModel tempSprite;
		ImageIcon fireActionImage;
		String[] eventNameSplit;
		HashMap<String, ArrayList<String>> eventActionDetails;
		AssociateEvent associateEvent;

		eventActionDetails = new HashMap<String, ArrayList<String>>();

		int x = sprite.getXPosition() + (sprite.getImage().getWidth(null)) / 2;
		int y = sprite.getYPosition() + (sprite.getImage().getHeight(null)) / 2;

		fireActionImage = new ImageIcon(getClass().getClassLoader().getResource("img/bullet.png"));

		x = x - (fireActionImage.getImage().getWidth(null) / 2);
		y = y - (fireActionImage.getImage().getHeight(null) / 2);

		tempSprite = new SpriteModel("Bullet", x, y, fireActionImage.getImage(), -1, true, eventActionDetails);
		tempSprite.setRectangleTest(x, y, tempSprite.getImage().getWidth(null), tempSprite.getImage().getHeight(null));
		fireActionSprites.add(tempSprite);

		log.info("FireAction : performAction : Exit");
	}

	public void performAction() {
		log.info("FireAction : performAction() : Enter");
		ArrayList<SpriteModel> copy = new ArrayList<SpriteModel>(fireActionSprites);
		int checkGameLossFlag = 0;

		if (!fireActionSprites.isEmpty()) {
			for (SpriteModel sprite : fireActionSprites) {
				if (sprite.getYPosition() >= 0) {
					sprite.setYPosition(sprite.getYPosition() + sprite.getYDirection());
					sprite.setRectangleTest(sprite.getXPosition(), sprite.getYPosition(), sprite.getImage().getWidth(null),
							sprite.getImage().getHeight(null));
				} else {
					copy.remove(sprite);
				}
			}
		}
		fireActionSprites = copy;

		ArrayList<SpriteModel> sprite2 = new ArrayList<SpriteModel>();

		for (SpriteModel sprite : Collision.getInstance().getSpriteListeners()) {

			HashMap<String, ArrayList<String>> eventActionDetails = sprite.getEventActionDetails();

			for (String keyEvent : eventActionDetails.keySet()) {
				String event = keyEvent.split("-")[0].trim();
				if (event.equals("Collision")) {
					SpriteModel sprite1 = sprite;
					String spriteString = keyEvent.split("-")[1].trim();
					for (SpriteModel secondSprite : Collision.getInstance().getSpriteListeners()) {
						if (secondSprite.getName().equalsIgnoreCase(spriteString)) {
							sprite2 = fireActionSprites;
							break;
						}
					}

					// For each pair of sprites identify the action to be
					// performed and call the action
					for (String action : eventActionDetails.get(keyEvent)) {
						for (Actions actionList : Actions.values()) {
							if (actionList.name().equalsIgnoreCase(action)) {
								for (SpriteModel childSprite : fireActionSprites) {
									if ((sprite1.getRectangleTest()).intersects(childSprite.getRectangleTest())
											&& !childSprite.isDestroyFlagEnabled()) {
										sprite1.setDestroySpriteFlag(true);
										childSprite.setDestroySpriteFlag(true);
									}
								}
							}
						}

					}
					if (!sprite1.isDestroyFlagEnabled()) {
						checkGameLossFlag = ActionConstants.GAME_FLAG_PROGRESS.getOffsetValue();
					}
				}

			}
		}

		// if all sprite objects got destroyed then game win
		if (checkGameLossFlag == 0) {
			Utility.getInstance().setGameFlag(ActionConstants.GAME_FLAG_WIN.getOffsetValue());
		}

		log.info("FireAction : performAction() : Exit");
	}

	public ArrayList<SpriteModel> getFireActionSprites() {
		return fireActionSprites;
	}

	public void setFireActionSprites(ArrayList<SpriteModel> fireActionSprites) {
		this.fireActionSprites = fireActionSprites;
	}

	@Override
	public void performAction(SpriteModel sprite1, SpriteModel sprite2) {

	}
}
