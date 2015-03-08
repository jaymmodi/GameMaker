package main;

import static org.junit.Assert.*;
import model.SpriteModel;

import org.junit.Before;
import org.junit.Test;

import action.AutoMoveLeft;
import action.AutoMoveUp;

public class AutoMoveUpTest {

	private AutoMoveUp autoMoveUp;
	private SpriteModel sprite;

	@Before
	public void setUp() throws Exception {
		autoMoveUp = new AutoMoveUp();
		sprite = new SpriteModel();
		sprite.setName("car");
		sprite.setYPosition(60);
	}

	@Test
	public void testPerformActionSpriteModelWithinFrame() {
		autoMoveUp.performAction(sprite);
		assertEquals(58, sprite.getYPosition());
	
	}
	@Test
	public void testPerformActionSpriteModelNotWithinFrame() {
		sprite.setYPosition(-600);
		autoMoveUp.performAction(sprite);
		assertEquals(1000, sprite.getYPosition());
	
	}

}
