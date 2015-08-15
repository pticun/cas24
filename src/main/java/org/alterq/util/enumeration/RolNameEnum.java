package org.alterq.util.enumeration;

import java.io.Serializable;

public enum RolNameEnum implements Serializable{
	//ROL_SUPER_ADMIN exists only in defect Company 
	ROL_SUPER_ADMIN(1000),ROL_ADMIN(100),ROL_USERADVANCED(20),ROL_USER(10),ROL_PUBLIC(0);
 
	private int value;

    private RolNameEnum(int value) {
            this.value = value;
    }
    public int getValue(){
    	return value;
    }
}
