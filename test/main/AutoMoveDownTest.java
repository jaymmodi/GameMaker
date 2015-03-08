package main;

import static org.junit.Assert.*;
import model.SpriteModel;

import org.junit.Before;
import org.junit.Test;

import action.AutoMoveDown;

public class AutoMoveDownTest {

	private AutoMoveDown autoMoveDown;
	private SpriteModel sprite;

	@Before
	public void setUp() throws Exception {
		autoMoveDown = new AutoMoveDown();
		sprite = new SpriteModel();
		sprite.setName("car");
		sprite.setYPosition(600);
	}

	@Test
	public void testPerformActionSpriteModelWithinFrame() {
		autoMoveDown.performAction(sprite);
		assertEquals(602, sprite.getYPosition());
	}

	@Test
	public void testPerformActionSpriteModelNotWithinFrame() {
		sprite.setYPosition(900);
		autoMoveDown.performAction(sprite);
		assertEquals(0, sprite.getYPosition());
	}
	

}
