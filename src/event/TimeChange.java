package event;

import java.util.ArrayList;
import java.util.HashMap;

import model.SpriteModel;
import action.Actions;
import action.MoveSprite;

public class TimeChange implements Event {

	private static TimeChange instance;
	//List of Sprites that have the KeyboardPress event
	private ArrayList<SpriteModel> spriteListeners = new ArrayList<SpriteModel>();

	private TimeChange() {
		// Constructor for Singleton Pattern
	}

	public static TimeChange getInstance() {
		if (instance == null)
			instance = new TimeChange();
		return instance;
	}

	@Override
	public void addSpriteListener(SpriteModel sprite) {
		if (!(spriteListeners.contains(sprite)))
			spriteListeners.add(sprite);
	}

	@Override
	public void associateAction(String eventAssociator) {
		String event = "TimeChange";
		
		//For each sprite identify the action to be performed and call the action
		for (SpriteModel sprite : spriteListeners) {
			HashMap<String, ArrayList<String>> eventActionDetails = sprite
					.getEventActionDetails();
			if (eventActionDetails.containsKey(event)) {
				for (String action : eventActionDetails.get(event)) {
					for (Actions actionList : Actions.values()) {
						if (actionList.name().equalsIgnoreCase(action))
							actionList.getValue().performAction(sprite);
					}
				}
			}
		}
	}
}
