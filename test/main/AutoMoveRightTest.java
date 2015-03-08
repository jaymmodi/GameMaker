package main;

import static org.junit.Assert.assertEquals;
import model.SpriteModel;

import org.junit.Before;
import org.junit.Test;

import action.AutoMoveRight;

public class AutoMoveRightTest {

	private AutoMoveRight autoMoveRight;
	private SpriteModel sprite;

	@Before
	public void setUp() throws Exception {
		autoMoveRight = new AutoMoveRight();
		sprite = new SpriteModel();
		sprite.setName("car");
		sprite.setXPosition(50);
	}

	@Test
	public void testPerformActionSpriteModelWithinFrame() {
		autoMoveRight.performAction(sprite);
		assertEquals(52, sprite.getXPosition());
	
	}
	@Test
	public void testPerformActionSpriteModelNotWithinFrame() {
		sprite.setXPosition(600);
		autoMoveRight.performAction(sprite);
		assertEquals(0, sprite.getXPosition());
	
	}

}
