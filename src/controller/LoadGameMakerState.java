package controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.LinkedList;

import org.apache.logging.log4j.LogManager;

public class LoadGameMakerState implements Serializable {
	static org.apache.logging.log4j.Logger log = LogManager
			.getLogger(LoadGameMakerState.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = -2195041392636469058L;

	private SaveableObject saveableObject;

	private String fileName;

	public LoadGameMakerState() {
		log.info("LoadGameMakerState : LoadGameMakerState() : Enter");
		this.saveableObject = null;
		this.fileName = null;
		log.info("LoadGameMakerState : LoadGameMakerState() : Exit");
	}

	public SaveableObject load() {
		log.info("LoadGameMakerState : load : Enter");
		try {
			FileInputStream fileInputStream = new FileInputStream(getFileName());
			ObjectInputStream objectInputStream = new ObjectInputStream(
					fileInputStream);
			saveableObject = (SaveableObject) objectInputStream.readObject();
			objectInputStream.close();
			fileInputStream.close();
		} catch (FileNotFoundException e) {
		} catch (ClassNotFoundException e) {
		} catch (IOException e) {
		}
		log.info("LoadGameMakerState : load : Exit");
		return saveableObject;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

}
