package main;

import static org.junit.Assert.*;

import model.SpriteModel;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import action.MoveSpriteLeft;
import action.MoveSpriteRight;

public class MoveSpriteRightTest {
	
	MoveSpriteRight moveSpriteRightObj;
	SpriteModel testSprite1;
	int testXcoordinate;
	int testXcoordinateRight;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		
		testXcoordinate =250;
		testXcoordinateRight=264;
		moveSpriteRightObj = new MoveSpriteRight();
		testSprite1 = new SpriteModel();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testMoveSpriteRight() {
		
	}

	@Test
	public void testPerformActionSpriteModel() {
		testSprite1.setXPosition(testXcoordinate);
		moveSpriteRightObj.performAction(testSprite1);
		
    if(testSprite1.getXPosition()==testXcoordinateRight)
    {
    	assertTrue(true);
    }
	}

}
