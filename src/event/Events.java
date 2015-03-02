package event;

//List of common events listed as enum.
public enum Events {
	TIMECHANGE(TimeChange.getInstance()),
	KEYBOARDPRESS(KeyboardPress.getInstance()),
	COLLISION(Collision.getInstance());
	
	private Event instance;
	private Events(Event value)
	{
		this.instance = value;
	}
	
	public Event getValue()
	{
		return this.instance;
	}
}
