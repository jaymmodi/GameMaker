package event;

import model.SpriteModel;

//Event Interface implemented by all the Events
public interface Event {
	public void addSpriteListener(SpriteModel sprite);
	public void associateAction(String eventAssociator);
}
