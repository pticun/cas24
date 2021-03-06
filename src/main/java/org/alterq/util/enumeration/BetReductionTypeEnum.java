package org.alterq.util.enumeration;

import java.io.Serializable;

public enum BetReductionTypeEnum implements Serializable{
	BET_REDUCTION_ERROR(-1),
	BET_REDUCTION_NONE(0),//Quiniela Directa sin Reduccion
	BET_REDUCTION_FIRST(1),//Reduccion 1 (4T: 9 apuestas)
	BET_REDUCTION_SECOND(2),//Reduccion 2 (7D: 16 apuestas)
	BET_REDUCTION_THIRD(3),//Reduccion 3 (3T + 3D: 24 apuestas)
	BET_REDUCTION_FOURTH(4),//Reduccion 4 (2T + 6D: 64 apuestas)
	BET_REDUCTION_FIFTH(5),//Reduccion 5 (8T: 81 apuestas)
	BET_REDUCTION_SIXTH(6)//Reduccion 6 (11D: 132 apuestas)
	;

	private int value;

    private BetReductionTypeEnum(int value) {
            this.value = value;
    }
    public int getValue(){
    	return value;
    }
	public void setValue(int value) {
		this.value = value;
	}
}
