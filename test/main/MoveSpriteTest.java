package main;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;

import model.SpriteModel;

import observer.GameBoardPanel;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import action.MoveSprite;

public class MoveSpriteTest {

	int testXCoordinate;
	int testYCordinate;
	int expectedXCoordinate;
	int expectedYCoordinate;
	int testXDirection;
	int testYDirection;
	int expectedXDirection;
	int expectedYDirection;

	boolean testDisplayFlag;

	MoveSprite moveSpriteObj;
	SpriteModel testSprite;

	HashMap<String, ArrayList<String>> testHashmap = new HashMap<String, ArrayList<String>>();
	ArrayList<String> testArraylist = new ArrayList<String>();

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {

		moveSpriteObj = new MoveSprite();
		testSprite = new SpriteModel();

	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testPerformActionSpriteModel() {
		// fail("Not yet implemented"); // TODO

		testXCoordinate = 250;
		testYCordinate = 250;

		testXDirection = 1;
		testYDirection = -1;

		expectedXCoordinate = 251;
		expectedYCoordinate = 249;

		expectedXDirection = 1;
		expectedYDirection = -1;

		testSprite.setName("Ball");
		testArraylist.add("Disappear");
		testArraylist.add("Move");
		testHashmap.put("Collision", testArraylist);

		testSprite.setDisplayFlagSprite(true);

		// assert(testSprite.isDisplayFlagSprite());

		// testSprite.getRectangle(testSprite);

		testSprite.setEventActionDetails(testHashmap);
		testSprite.setXPosition(testXCoordinate);
		testSprite.setYPosition(testYCordinate);

		testSprite.setXDirection(testXDirection);
		testSprite.setYDirection(testYDirection);
		moveSpriteObj.setUnitTestFlag(true);
		//testSprite.setRectangle(250, 250, 20, 20);
		moveSpriteObj.performAction(testSprite);

		if (expectedXCoordinate == testSprite.getXPosition()
				&& expectedYCoordinate == testSprite.getYPosition()
				&& expectedXDirection == testSprite.getXDirection()
				&& expectedYDirection == testSprite.getYDirection())
			assertTrue(true);
	}
}
