package controller;

import java.io.Serializable;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;

import model.SpriteModel;

public class SaveableObject implements Serializable {
	private static org.apache.logging.log4j.Logger log = LogManager
			.getLogger(SaveableObject.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = -8270350564497773797L;
	private ArrayList<SpriteModel> spriteList;

	private boolean timerCheckIndicator;
	private int backgroundImageIndicator;

	/*  This method only sets the object to be saved.
	 */
	public SaveableObject() {
		log.info("SaveableObject : SaveableObject() : Enter");
		if(null!= spriteList){
		 log.info("SaveableObject : SaveableObject() : spriteList count - "+spriteList.size());
		}
		setSpriteList(spriteList);
		setBackgroundImageIndicator(0);
		setTimerCheckIndicator(false);
		log.info("SaveableObject : SaveableObject() : Exit");
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
