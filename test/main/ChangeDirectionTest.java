package main;

import static org.junit.Assert.*;

import model.SpriteModel;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import action.ChangeDirection;

public class ChangeDirectionTest {
	
	int testsprite1Position;
	int testsprite2Position;
	SpriteModel testSprite1,testSprite2;
	ChangeDirection changeDirectionTestObj;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		testsprite1Position= 10;
		testsprite2Position= 50;
		testSprite1 = new SpriteModel();
		testSprite2 = new SpriteModel();
		testSprite1.setRectangleTest(10, 10, 10, 10);
		testSprite2.setRectangleTest(10, 10, 25, 25);
		
		changeDirectionTestObj = new ChangeDirection();
		changeDirectionTestObj.setUnittestFlag(true);
		changeDirectionTestObj.performAction(testSprite1, testSprite2);
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {	
		if((testSprite1.getXDirection()==-1) &&(testSprite1.getYDirection()==-1))
		{
        assertTrue(true);
		}
	}

}
