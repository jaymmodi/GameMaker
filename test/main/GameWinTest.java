package main;

import static org.junit.Assert.*;
import model.SpriteModel;

import org.junit.Before;
import org.junit.Test;

import action.GameWin;

public class GameWinTest {
	private GameWin gameWin;
	private SpriteModel sprite1;
	private SpriteModel sprite2;

	@Before
	public void setUp() throws Exception {
		gameWin = new GameWin();
		sprite1 = new SpriteModel();
		sprite2 = new SpriteModel();
	}

	@Test
	public void testPerformActionSpriteModelSpriteModel() {
		sprite1.setRectangleTest(100, 100, 100, 100);
		sprite2.setRectangleTest(110, 110, 100, 100);
		gameWin.performAction(sprite1, sprite2);
		assertEquals(true, sprite1.isDestroyFlagEnabled());
		
	}

}
