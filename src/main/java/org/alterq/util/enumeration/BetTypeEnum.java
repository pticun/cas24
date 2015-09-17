package org.alterq.util.enumeration;

import java.io.Serializable;

public enum BetTypeEnum implements Serializable{
	BET_NORMAL(0),BET_FINAL(10),BET_AUTOMATIC(20);
 
	private int value;

    private BetTypeEnum(int value) {
            this.value = value;
    }
    public int getValue(){
    	return value;
    }
}
