package event;

import java.util.ArrayList;
import java.util.HashMap;

import model.SpriteModel;
import action.Actions;

//Checks whether collision happened between the sprites and calls corresponding action associated with it.
public class Collision implements Event {

	private static Collision instance;
	//List of Sprites that have the Collision event
	private ArrayList<SpriteModel> spriteListeners = new ArrayList<SpriteModel>();
	
	private Collision() {
		// Constructor for Singleton Pattern
	}

	public static Collision getInstance() {
		if (instance == null)
			instance = new Collision();
		return instance;
	}

	@Override
	public void addSpriteListener(SpriteModel sprite) {
		if (!(spriteListeners.contains(sprite)))
			spriteListeners.add(sprite);
	}

	@Override
	//Associates the particular action with the Collision. (Maybe Disappear or Change Direction)
	public void associateAction(String eventAssociator) {
		
		//Two Sprites required to check for Collision.
		SpriteModel sprite2 = new SpriteModel();
		
		//Gets the List of Sprites a sprite can collide with
		for (SpriteModel sprite : spriteListeners) {

			HashMap<String, ArrayList<String>> eventActionDetails = sprite.getEventActionDetails();
			
			for (String keyEvent : eventActionDetails.keySet()) {
				String event = keyEvent.split("-")[0].trim();
				if (event.equals("Collision")) {
					SpriteModel sprite1 = sprite;
					String spriteString = keyEvent.split("-")[1].trim();
					for (SpriteModel secondSprite : spriteListeners) {
						if (secondSprite.getName().equalsIgnoreCase(
								spriteString)) {
							sprite2 = secondSprite;
							break;
						}
					}
					
					//For each pair of sprites identify the action to be performed and call the action
					for (String action : eventActionDetails.get(keyEvent)) {
						for (Actions actionList : Actions.values()) {
							if (actionList.name().equalsIgnoreCase(action))
								actionList.getValue().performAction(sprite1,
										sprite2);
						}

					}
				}

			}
		}
	}
}
