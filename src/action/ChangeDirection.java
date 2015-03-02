package action;

import model.SpriteModel;

/**
 * ChangeDirection class
 * 
 * Class to change the direction of sprite on collision with associated sprite
 * 
 * @author Team 2
 *
 */
public class ChangeDirection implements Action {

	private boolean UnittestFlag = false;

	
	/*
	 * performs the change direction action for a sprite on collision with
	 * associated sprite
	 * 
	 * @param sprite1 sets the first sprite
	 * @param sprite2 sets the second sprite
	 */
	@Override
	public void performAction(SpriteModel sprite1, SpriteModel sprite2) {

		if (isUnittestFlag()) {
			if ((sprite1.getRectangleTest()).intersects(sprite2
					.getRectangleTest())) {

				int sprite1Position = (int) sprite1.getRectangleTest()
						.getMinX();
				int sprite2Position = (int) sprite2.getRectangleTest()
						.getMinX();


				// Collision points are checked and the sprite is getting
				// deflected accordingly
				int first = sprite2Position + 8;
				int second = sprite2Position + 16;
				int third = sprite2Position + 24;
				int fourth = sprite2Position + 32;

				if (sprite1Position < first) {
					sprite1.setXDirection(-1);
					sprite1.setYDirection(-1);
				}

				if (sprite1Position >= first && sprite1Position < second) {
					sprite1.setXDirection(-1);
					sprite1.setYDirection(-1 * sprite1.getYDirection());
				}

				if (sprite1Position >= second && sprite1Position < third) {
					sprite1.setXDirection(0);
					sprite1.setYDirection(-1);
				}

				if (sprite1Position >= third && sprite1Position < fourth) {
					sprite1.setXDirection(1);
					sprite1.setYDirection(-1 * sprite1.getYDirection());
				}

				if (sprite1Position > fourth) {
					sprite1.setXDirection(1);
					sprite1.setYDirection(-1);
				}

			}
		}

		else {
			if ((sprite1.getRectangle(sprite1)).intersects(sprite2
					.getRectangle(sprite2))) {

				int sprite1Position = (int) sprite1.getRectangle(sprite1)
						.getMinX();
				int sprite2Position = (int) sprite2.getRectangle(sprite2)
						.getMinX();

				// Collision points are checked and the sprite is getting
				// deflected accordingly
				int first = sprite2Position + 8;
				int second = sprite2Position + 16;
				int third = sprite2Position + 24;
				int fourth = sprite2Position + 32;

				if (sprite1Position < first) {
					sprite1.setXDirection(-1);
					sprite1.setYDirection(-1);
				}

				if (sprite1Position >= first && sprite1Position < second) {
					sprite1.setXDirection(-1);
					sprite1.setYDirection(-1 * sprite1.getYDirection());
				}

				if (sprite1Position >= second && sprite1Position < third) {
					sprite1.setXDirection(0);
					sprite1.setYDirection(-1);
				}

				if (sprite1Position >= third && sprite1Position < fourth) {
					sprite1.setXDirection(1);
					sprite1.setYDirection(-1 * sprite1.getYDirection());
				}

				if (sprite1Position > fourth) {
					sprite1.setXDirection(1);
					sprite1.setYDirection(-1);
				}

			}

		}

	}

	@Override
	public void performAction(SpriteModel sprite) {

	}

	public boolean isUnittestFlag() {
		return UnittestFlag;
	}

	public void setUnittestFlag(boolean unittestFlag) {
		UnittestFlag = unittestFlag;
	}
}
