package org.alterq.domain;

public enum RolNameEnum {
	ROL_ADMIN(10),ROL_USERADVANCED(1),ROL_USER(0);
 
	private int value;

    private RolNameEnum(int value) {
            this.value = value;
    }
    public int getValue(){
    	return value;
    }
}
