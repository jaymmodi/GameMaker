package main;

import static org.junit.Assert.assertEquals;
import model.SpriteModel;

import org.junit.Before;
import org.junit.Test;

import action.MoveSpriteDown;

public class MoveSpriteDownTest {
	private MoveSpriteDown moveSpriteDown;
	private SpriteModel sprite;

	@Before
	public void setUp() throws Exception {
		moveSpriteDown = new MoveSpriteDown();
		sprite = new SpriteModel();
		sprite.setName("spaceShuttle");
		sprite.setYPosition(100);
	}

	@Test
	public void testPerformActionSpriteModelWithinFrame() {
		moveSpriteDown.performAction(sprite);
		assertEquals(114, sprite.getYPosition());

	}
	/*@Test
	public void testPerformActionSpriteModelNotWithinFrame() {
		sprite
		moveSpriteDown.performAction(sprite);
		assertEquals(114, sprite.getYPosition());

	}*/

}
