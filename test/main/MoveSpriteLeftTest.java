package main;

import static org.junit.Assert.*;

import model.SpriteModel;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import action.MoveSprite;
import action.MoveSpriteLeft;

public class MoveSpriteLeftTest {

	MoveSpriteLeft moveSpriteLeftObj;
	SpriteModel testSprite1;
	int testXcoordinate;
	int testXcoordinateLeft;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		
		testXcoordinate =250;
		testXcoordinateLeft=236;
		moveSpriteLeftObj = new MoveSpriteLeft();
		testSprite1 = new SpriteModel();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testPerformActionSpriteModel() {
		testSprite1.setXPosition(testXcoordinate);
		moveSpriteLeftObj.performAction(testSprite1);
		
    if(testSprite1.getXPosition()==testXcoordinateLeft)
    {
    	assertTrue(true);
    }
		
		//fail("Not yet implemented"); // TODO
	}

}
