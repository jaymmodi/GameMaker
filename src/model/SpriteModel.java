package model;

import java.awt.Image;
import java.awt.Rectangle;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

//import javafx.beans.binding.SetBinding;

public class SpriteModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -542951889071277334L;
	// Basic parameters for any sprite
	private String name;
	private int xPosition;
	private int yPosition;
	private UUID spriteID;
	private Rectangle rectangle;
	private int imagePathIndicator;

	private int xDirection = 1;
	private int yDirection = -1;
	private boolean destroySpriteFlag = false;

	transient private Image image;

	HashMap<String, ArrayList<String>> eventActionDetails = new HashMap<String, ArrayList<String>>();

	public SpriteModel(String name, int xPosition, int yPosition, Image image,
			int spriteImageIndicator, boolean diplayFlag,
			HashMap<String, ArrayList<String>> eventActionDetails) {
		this.spriteID = UUID.randomUUID();
		setXPosition(xPosition);
		setYPosition(yPosition);
		this.image = image;
		setName(name);
		setDisplayFlagSprite(diplayFlag);
		setEventActionDetails(eventActionDetails);
		setImagePathIndicator(spriteImageIndicator);
		this.eventActionDetails.putAll(eventActionDetails);
	}

	public SpriteModel() {
	}

	public int getXPosition() {
		return xPosition;
	}

	public void setXPosition(int x) {
		this.xPosition = x;
	}

	public int getYPosition() {
		return yPosition;
	}

	public void setYPosition(int y) {
		this.yPosition = y;
	}

	public void setXDirection(int x) {
		this.xDirection = x;
	}
	
	public int getXDirection() {
		return xDirection;
	}
	
	public void setYDirection(int y) {
		this.yDirection = y;
	}
	
	public int getYDirection() {
		return yDirection;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public HashMap<String, ArrayList<String>> getEventActionDetails() {
		return eventActionDetails;
	}

	public void setEventActionDetails(HashMap eventActionDetails) {
		this.eventActionDetails.putAll(eventActionDetails);
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public Image getImage() {
		return image;
	}

	public UUID getSpriteID() {
		return spriteID;
	}

	public Rectangle getRectangle(SpriteModel sprite) {
		Rectangle imageRectangle = new Rectangle(sprite.getXPosition(),
				sprite.getYPosition(), sprite.getImage().getWidth(null), sprite
						.getImage().getHeight(null));
		return imageRectangle;
	}

	public boolean isDestroyFlagEnabled() {
		return destroySpriteFlag;
	}

	public void setDisplayFlagSprite(boolean displayFlagSprite) {
	}

	public Rectangle getRectangle() {
		return new Rectangle(xPosition, yPosition, image.getWidth(null),
				image.getHeight(null));
	}

	public int getImagePathIndicator() {
		return imagePathIndicator;
	}

	public void setImagePathIndicator(int imagePathIndicator) {
		this.imagePathIndicator = imagePathIndicator;
	}
	public void setDestroySpriteFlag(boolean destroySpriteFlag) {
		this.destroySpriteFlag = destroySpriteFlag;
	}

	public Rectangle getRectangleTest()
	{
		return rectangle;
	}
	
	public void setRectangleTest(int x,int y,int w,int h) {
		this.rectangle = new Rectangle(x, y, w, h);
	}
}
