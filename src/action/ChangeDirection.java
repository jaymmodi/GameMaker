package action;

import org.apache.logging.log4j.LogManager;

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

	private boolean unitTestFlag = false;

	private static org.apache.logging.log4j.Logger log = LogManager
			.getLogger(ChangeDirection.class);
	/*
	 * performs the change direction action for a sprite on collision with
	 * associated sprite
	 * 
	 * @param sprite1 sets the first sprite
	 * @param sprite2 sets the second sprite
	 */
	@Override
	public void performAction(SpriteModel sprite1, SpriteModel sprite2) {
       log.info("ChangeDirection : performAction : Enter");
       log.debug("ChangeDirection : performAction : sprite1 : name - "+sprite1.getName());
       log.debug("ChangeDirection : performAction : sprite2 : name - "+sprite2.getName());
		if (isUnittestFlag()) {
			if ((sprite1.getRectangleTest()).intersects(sprite2
					.getRectangleTest())) {

				int sprite1Position = (int) sprite1.getRectangleTest()
						.getMinX();
				int sprite2Position = (int) sprite2.getRectangleTest()
						.getMinX();


				// Collision points are checked and the sprite is getting
				// deflected accordingly
				int first = sprite2Position + ActionConstants.COLLISION_VARIANT_TYPE_1.getOffsetValue();
				int second = sprite2Position + ActionConstants.COLLISION_VARIANT_TYPE_2.getOffsetValue();
				int third = sprite2Position + ActionConstants.COLLISION_VARIANT_TYPE_3.getOffsetValue();
				int fourth = sprite2Position + ActionConstants.COLLISION_VARIANT_TYPE_4.getOffsetValue();

				if (sprite1Position < first) {
					sprite1.setXDirection(ActionConstants.NEGATIVE_X_DIRECTION.getOffsetValue());
					sprite1.setYDirection(ActionConstants.NEGATIVE_Y_DIRECTION.getOffsetValue());
				}

				if (sprite1Position >= first && sprite1Position < second) {
					sprite1.setXDirection(ActionConstants.NEGATIVE_X_DIRECTION.getOffsetValue());
					sprite1.setYDirection(ActionConstants.NEGATIVE_Y_DIRECTION.getOffsetValue() * sprite1.getYDirection());
				}

				if (sprite1Position >= second && sprite1Position < third) {
					sprite1.setXDirection(ActionConstants.NO_CHANGE.getOffsetValue());
					sprite1.setYDirection(ActionConstants.NEGATIVE_Y_DIRECTION.getOffsetValue());
				}

				if (sprite1Position >= third && sprite1Position < fourth) {
					sprite1.setXDirection(ActionConstants.POSITIVE_X_DIRECTION.getOffsetValue());
					sprite1.setYDirection(ActionConstants.NEGATIVE_Y_DIRECTION.getOffsetValue() * sprite1.getYDirection());
				}

				if (sprite1Position > fourth) {
					sprite1.setXDirection(ActionConstants.POSITIVE_X_DIRECTION.getOffsetValue());
					sprite1.setYDirection(ActionConstants.NEGATIVE_Y_DIRECTION.getOffsetValue());
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
				int first = sprite2Position + ActionConstants.COLLISION_VARIANT_TYPE_1.getOffsetValue();
				int second = sprite2Position +  ActionConstants.COLLISION_VARIANT_TYPE_2.getOffsetValue();
				int third = sprite2Position + ActionConstants.COLLISION_VARIANT_TYPE_3.getOffsetValue();
				int fourth = sprite2Position + ActionConstants.COLLISION_VARIANT_TYPE_4.getOffsetValue();

				if (sprite1Position < first) {
					sprite1.setXDirection(ActionConstants.NEGATIVE_X_DIRECTION.getOffsetValue());
					sprite1.setYDirection(ActionConstants.NEGATIVE_Y_DIRECTION.getOffsetValue());
				}

				if (sprite1Position >= first && sprite1Position < second) {
					sprite1.setXDirection(ActionConstants.NEGATIVE_X_DIRECTION.getOffsetValue());
					sprite1.setYDirection(ActionConstants.NEGATIVE_Y_DIRECTION.getOffsetValue() * sprite1.getYDirection());
				}

				if (sprite1Position >= second && sprite1Position < third) {
					sprite1.setXDirection(0);
					sprite1.setYDirection(ActionConstants.NEGATIVE_Y_DIRECTION.getOffsetValue());
				}

				if (sprite1Position >= third && sprite1Position < fourth) {
					sprite1.setXDirection(ActionConstants.POSITIVE_X_DIRECTION.getOffsetValue());
					sprite1.setYDirection(ActionConstants.NEGATIVE_Y_DIRECTION.getOffsetValue() * sprite1.getYDirection());
				}

				if (sprite1Position > fourth) {
					sprite1.setXDirection(ActionConstants.POSITIVE_X_DIRECTION.getOffsetValue());
					sprite1.setYDirection(ActionConstants.NEGATIVE_Y_DIRECTION.getOffsetValue());
				}

			}

		}
		log.info("ChangeDirection : performAction : Exit");
	}

	@Override
	public void performAction(SpriteModel sprite) {

	}

	public boolean isUnittestFlag() {
		return unitTestFlag;
	}

	public void setUnittestFlag(boolean unitTestFlag) {
		this.unitTestFlag = unitTestFlag;
	}
}
