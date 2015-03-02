package controller;

public class Utility {
	private static Utility instance;
	private int gameFlag;
	private Utility()
	{
		
	}
	
	public static Utility getInstance()
	{
		if(instance == null)
			instance = new Utility();
		return instance;
	}
	
	public void setGameFlag(int gameFlag)
	{
		this.gameFlag = gameFlag;
	}
	
	public int getGameFlag()
	{
		return this.gameFlag;
	}
}
