package action;

/**
 * Actions enum
 * 
 * Holds values for Actions associated with objects from their respective
 * classes
 * 
 * @author Team 2
 *
 */
public enum Actions {
	MOVE(new MoveSprite()), LEFTMOVE(new MoveSpriteLeft()), RIGHTMOVE(
			new MoveSpriteRight()), DISAPPEAR(new Disappear()), CHANGEDIRECTION(
			new ChangeDirection()), SOUND(new PlaySound());

	private Action instance;

	private Actions(Action value) {
		this.instance = value;
	}

	public Action getValue() {
		return this.instance;
	}
}
