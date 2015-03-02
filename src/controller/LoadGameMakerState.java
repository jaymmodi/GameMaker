package controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.LinkedList;

public class LoadGameMakerState implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2195041392636469058L;

	private SaveableObject saveableObject;

	private String fileName;

	public LoadGameMakerState() {
		this.saveableObject = null;
		this.fileName = null;
	}

	public SaveableObject load() {

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

		return saveableObject;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

}
