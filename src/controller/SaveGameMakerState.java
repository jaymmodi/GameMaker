package controller;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class SaveGameMakerState implements Serializable{

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

	public void save() {
		try {
			fileOutputStream = new FileOutputStream(getFileName());
			objectOutputStream = new ObjectOutputStream(fileOutputStream);
			objectOutputStream.writeObject(saveableObject);
			objectOutputStream.close();
			fileOutputStream.close();
		} catch (FileNotFoundException e) {
		} catch (IOException e) {
		}

	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
}
