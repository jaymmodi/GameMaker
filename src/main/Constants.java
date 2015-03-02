package main;

import event.Event;

//public interface Constants {
//		int FRAME_WIDTH = 1000;
//		int FRAME_HEIGHT = 1000;
//		int GAME_BOARD_PANEL_WIDTH = 470;
//		int GAME_BOARD_PANEL_HEIGHT = 890;	
//		int LEFT_MARGIN = 0;
//		int RIGHT_MARGIN = 530;
//}
public enum Constants {
	FRAME_WIDTH(1000),
	FRAME_HEIGHT(1000),
	GAME_BOARD_PANEL_WIDTH(470),
	GAME_BOARD_PANEL_HEIGHT(890),	
	LEFT_MARGIN(0),
	RIGHT_MARGIN(530);
	
	private int value;
	private Constants(int value)
	{
		this.value = value;
	}
	
	public int getValue()
	{
		return this.value;
	}
	
}