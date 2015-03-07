package action;

import java.awt.Image;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;

import controller.Utility;
import event.AssociateEvent;
import event.Collision;
import event.Events;
import model.SpriteModel;
import main.Constants;

/**
 * 
 * @author Team 2
 *
 */
public class FireAction implements Action {

	ArrayList<SpriteModel> fireActionSprites = new ArrayList<SpriteModel>();

	public void performAction(SpriteModel sprite) {

		SpriteModel tempSprite;
		ImageIcon fireActionImage;
		String[] eventNameSplit;
		HashMap<String, ArrayList<String>> eventActionDetails;
		AssociateEvent associateEvent;

		eventActionDetails = new HashMap<String, ArrayList<String>>();

		int x = sprite.getXPosition() + (sprite.getImage().getWidth(null)) / 2;
		int y = sprite.getYPosition() + (sprite.getImage().getHeight(null)) / 2;

		fireActionImage = new ImageIcon(getClass().getClassLoader().getResource("img/fire_ball.gif"));

		x = x - (fireActionImage.getImage().getWidth(null) / 2);
		y = y - (fireActionImage.getImage().getHeight(null) / 2);

		tempSprite = new SpriteModel("Bullet", x, y, fireActionImage.getImage(), -1, true, eventActionDetails);
		tempSprite.setRectangleTest(x, y, tempSprite.getImage().getWidth(null), tempSprite.getImage().getHeight(null));
		fireActionSprites.add(tempSprite);
	}

	public void performAction() {

		ArrayList<SpriteModel> copy = new ArrayList<SpriteModel>(fireActionSprites);
		if (!fireActionSprites.isEmpty()) {
			for (SpriteModel sprite : fireActionSprites) {
				if (sprite.getYPosition() >= 0) {
					sprite.setYPosition(sprite.getYPosition() + sprite.getYDirection());
					sprite.setRectangleTest(sprite.getXPosition(), sprite.getYPosition(), sprite.getImage().getWidth(null), sprite.getImage().getHeight(null));
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
									actionList.getValue().performAction(sprite1, childSprite);
								}
							}
						}

					}
				}

			}
		}

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
