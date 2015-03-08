package action;

public enum ActionConstants {
    START_OFFSET(0), Y_VARIANT_OFFSET(2),X_VARIANT_OFFSET(2),RIGHT_MARGIN_OFFSET(56),
    POSITIVE_X_DIRECTION(1),POSITIVE_Y_DIRECTION(1),NEGATIVE_X_DIRECTION(-1),NEGATIVE_Y_DIRECTION(-1),
    NO_CHANGE(0),COLLISION_VARIANT_TYPE_1(8),COLLISION_VARIANT_TYPE_3(24),COLLISION_VARIANT_TYPE_2(16)
    ,COLLISION_VARIANT_TYPE_4(32),GAME_FLAG_LOSS(2),GAME_FLAG_WIN(3),GAME_FLAG_PROGRESS(1),LAYOUT_UPPER_BOUND(600),
    Y_POSITION_OFFSET(14),X_POSITION_OFFSET(14);
    
    private Integer offsetVal = null;
    
    ActionConstants(Integer val){
    	offsetVal = val;
    }
    
    public Integer getOffsetValue(){
    	return this.offsetVal;
    }
}
