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

		for (String eventName : sprite.getEventActionDetails().keySet()) {
			eventNameSplit = eventName.split("-");
			if (eventNameSplit[0].equalsIgnoreCase("Collision")) {
				eventActionDetails.put(eventName, sprite
						.getEventActionDetails().get(eventName));
			}
		}
		// ArrayList<String> tempActionList = new ArrayList<String>();
		// tempActionList.add("AUTOMOVEUP");
		// eventActionDetails.put("TIMECHANGE", tempActionList);

		for (Events event : Events.values()) {
			for (String eventName : eventActionDetails.keySet()) {
				if (event.name().equals(eventName.toUpperCase())) {
					associateEvent = new AssociateEvent(event.getValue());
					associateEvent.attachEvent(sprite);
				}
			}
		}

		int x = sprite.getXPosition() + (sprite.getImage().getWidth(null)) / 2;
		int y = sprite.getYPosition() + (sprite.getImage().getHeight(null)) / 2;

		fireActionImage = new ImageIcon(getClass().getClassLoader()
				.getResource("img/fire_ball.gif"));

		x = x - (fireActionImage.getImage().getWidth(null) / 2);
		y = y - (fireActionImage.getImage().getHeight(null) / 2);

		tempSprite = new SpriteModel("Bullet", x, y,
				fireActionImage.getImage(), -1, true, eventActionDetails);
		fireActionSprites.add(tempSprite);
	}

	public void performAction() {

	ArrayList<SpriteModel> copy = new ArrayList<SpriteModel>(fireActionSprites);
		if (!fireActionSprites.isEmpty()) {
			for (SpriteModel sprite : fireActionSprites) {
				if (sprite.getYPosition() >= 0) {
					sprite.setYPosition(sprite.getYPosition()
							+ sprite.getYDirection());
				} else {
					copy.remove(sprite);
				}
			}
		}
		fireActionSprites = copy;

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
