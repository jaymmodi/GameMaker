package controller;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import org.apache.logging.log4j.LogManager;

public class SaveGameMakerState implements Serializable{
	private static org.apache.logging.log4j.Logger log = LogManager
			.getLogger(SaveGameMakerState.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private FileOutputStream fileOutputStream;
	private ObjectOutputStream objectOutputStream;
	private String fileName;
	private SaveableObject saveableObject;

	public SaveGameMakerState() {
		this.setFileOutputStream(null);
		this.setObjectOutputStream(null);
		this.setFileName(null);
		this.setSaveableObjects(null);
	}

	public ObjectOutputStream getObjectOutputStream() {
		return objectOutputStream;
	}

	public void setObjectOutputStream(ObjectOutputStream objectOutputStream) {
		this.objectOutputStream = objectOutputStream;
	}

	public Object getSaveableObjects() {
		return saveableObject;
	}

	public void setSaveableObjects(SaveableObject saveableObject) {
		this.saveableObject = saveableObject;
	}

	FileOutputStream getFileOutputStream() {
		return fileOutputStream;
	}

	public void setFileOutputStream(FileOutputStream fileOutputStream) {
		this.fileOutputStream = fileOutputStream;
	}

	/*
	 * This method handles all the save functionality
	 */
	public void save() {
		try {
			fileOutputStream = new FileOutputStream(getFileName());
			objectOutputStream = new ObjectOutputStream(fileOutputStream);
			objectOutputStream.writeObject(saveableObject);
			objectOutputStream.close();
			fileOutputStream.close();
		} catch (FileNotFoundException e) {
			log.error("File Not found exception for save file");
		} catch (IOException e) {
			log.error("IOException in SaveGameMakerState class");
		}

	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
}
