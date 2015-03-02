package action;

import model.SpriteModel;

/**
 * Action interface
 * 
 * Defines functions to be used in different Action classes
 * 
 * @author Team 2
 *
 */
public interface Action {
	public void performAction(SpriteModel sprite);

	public void performAction(SpriteModel sprite1, SpriteModel sprite2);
}
