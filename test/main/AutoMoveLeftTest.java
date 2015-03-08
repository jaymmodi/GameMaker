package main;

import static org.junit.Assert.assertEquals;
import model.SpriteModel;

import org.junit.Before;
import org.junit.Test;

import action.AutoMoveLeft;

public class AutoMoveLeftTest {

	private AutoMoveLeft autoMoveLeft;
	private SpriteModel sprite;

	@Before
	public void setUp() throws Exception {
		autoMoveLeft = new AutoMoveLeft();
		sprite = new SpriteModel();
		sprite.setName("car");
		sprite.setXPosition(60);
	}

	@Test
	public void testPerformActionSpriteModelWithinFrame() {
		autoMoveLeft.performAction(sprite);
		assertEquals(58, sprite.getXPosition());
	}
	
	@Test
	public void testPerformActionSpriteModelNotWithinFrame() {
		sprite.setXPosition(-600);
		autoMoveLeft.performAction(sprite);
		assertEquals(530, sprite.getXPosition());
	}

}
