package main;

import static org.junit.Assert.assertEquals;
import model.SpriteModel;

import org.junit.Before;
import org.junit.Test;

import action.GameLoss;

public class GameLossTest {
	private GameLoss gameLoss;
	private SpriteModel sprite1;
	private SpriteModel sprite2;

	@Before
	public void setUp() throws Exception {
		gameLoss = new GameLoss();
		sprite1 = new SpriteModel();
		sprite2 = new SpriteModel();
	}

	@Test
	public void testPerformActionSpriteModel() {
		sprite1.setRectangleTest(100, 100, 100, 100);
		sprite2.setRectangleTest(110, 110, 100, 100);
		gameLoss.performAction(sprite1, sprite2);
		assertEquals(true, sprite1.isDestroyFlagEnabled());
		
	}

}
