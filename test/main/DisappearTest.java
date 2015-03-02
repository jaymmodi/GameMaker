package main;

import static org.junit.Assert.*;

import java.util.ArrayList;

import model.SpriteModel;
import observer.GameBoardPanel;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import action.Disappear;

public class DisappearTest {
	
	ArrayList<SpriteModel> spriteList;
	SpriteModel testSprite1,testSprite2;
	boolean testDisplayFlag;
	
	Disappear disappearObj;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		testSprite1 = new SpriteModel();
		testSprite2 = new SpriteModel();
		disappearObj = new Disappear();

		
		testSprite1.setRectangleTest(250, 250, 10, 10);
		testSprite2.setRectangleTest(258, 256, 10, 10);
		testSprite1.setDestroySpriteFlag(false);
		//spriteList.add(testSprite1);
		//spriteList.add(testSprite2);
		 disappearObj.performAction(testSprite1, testSprite2);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		 // TODO
		assertTrue(testSprite1.isDestroyFlagEnabled());
		
	}

}
