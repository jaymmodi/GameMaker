package controller;

import java.io.Serializable;
import java.util.ArrayList;

import model.SpriteModel;

public class SaveableObject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8270350564497773797L;
	private ArrayList<SpriteModel> spriteList;

	private boolean timerCheckIndicator;
	private int backgroundImageIndicator;

	public SaveableObject() {
		setSpriteList(spriteList);
		setBackgroundImageIndicator(0);
		setTimerCheckIndicator(false);
	}

	public ArrayList<SpriteModel> getSpriteList() {
		return spriteList;
	}

	public void setSpriteList(ArrayList<SpriteModel> spriteList) {
		this.spriteList = spriteList;
	}

	public int getBackgroundImageIndicator() {
		return backgroundImageIndicator;
	}

	public void setBackgroundImageIndicator(int backgroundImageIndicator) {
		this.backgroundImageIndicator = backgroundImageIndicator;
	}

	public boolean isTimerCheckIndicator() {
		return timerCheckIndicator;
	}

	public void setTimerCheckIndicator(boolean timerCheckIndicator) {
		this.timerCheckIndicator = timerCheckIndicator;
	}

}
