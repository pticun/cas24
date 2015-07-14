package org.alterq.util.enumeration;

public enum RolNameEnum {
	ROL_ADMIN(100),ROL_USERADVANCED(20),ROL_USER(10),ROL_PUBLIC(0);
 
	private int value;

    private RolNameEnum(int value) {
            this.value = value;
    }
    public int getValue(){
    	return value;
    }
}
