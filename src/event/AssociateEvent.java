package event;

import model.SpriteModel;

//To associate Event to Sprite.
public class AssociateEvent {
	private Event event;
	
	public AssociateEvent(Event event) {
			this.event = event;
		}

	//Each event class will have its own sprite list (Only Sprites that have the particular event associated with it)
	public void attachEvent(SpriteModel sprite)
	{
		event.addSpriteListener(sprite);
	}		
}

