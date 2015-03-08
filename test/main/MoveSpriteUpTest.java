package main;

import static org.junit.Assert.*;
import model.SpriteModel;

import org.junit.Before;
import org.junit.Test;

import action.MoveSpriteUp;

public class MoveSpriteUpTest {
	private MoveSpriteUp moveSpriteUp;
	private SpriteModel sprite;

	@Before
	public void setUp() throws Exception {
		moveSpriteUp = new MoveSpriteUp();
		sprite = new SpriteModel();
		sprite.setName("ball");
		sprite.setYPosition(114);
	}

	@Test
	public void testPerformActionSpriteModel() {
		moveSpriteUp.performAction(sprite);
		assertEquals(100, sprite.getYPosition());

	}

}
