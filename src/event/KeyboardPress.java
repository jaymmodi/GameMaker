package event;

import java.util.ArrayList;
import java.util.HashMap;

import model.SpriteModel;
import action.MoveSpriteLeft;
import action.MoveSpriteRight;

public class KeyboardPress implements Event {

	private static KeyboardPress instance;
	//List of Sprites that have the KeyboardPress event
	private ArrayList<SpriteModel> spriteListeners = new ArrayList<SpriteModel>();

	private KeyboardPress() {
		// Constructor for Singleton Pattern
	}

	public static KeyboardPress getInstance() {
		if (instance == null)
			instance = new KeyboardPress();
		return instance;
	}

	@Override
	public void addSpriteListener(SpriteModel sprite) {
		if (!(spriteListeners.contains(sprite)))
			spriteListeners.add(sprite);

	}

	@Override
	public void associateAction(String eventAssociator) {
		String event = "KeyboardPress";
		
		//For each sprite identify the action to be performed and call the action
		for (SpriteModel sprite : spriteListeners) {
			HashMap<String, ArrayList<String>> hm = sprite
					.getEventActionDetails();
			if (hm.containsKey(event)) {
				//When Keyboard Left arrow is pressed
				if (eventAssociator.equalsIgnoreCase("Left Key")) {
					MoveSpriteLeft moveSpriteLeft = new MoveSpriteLeft();
					moveSpriteLeft.performAction(sprite);
				} 
				//When the Keyboard Right arrow is pressed
				else if (eventAssociator.equalsIgnoreCase("Right Key")){
					MoveSpriteRight moveSpriteRight = new MoveSpriteRight();
					moveSpriteRight.performAction(sprite);
				}
			}
		}
	}

}
