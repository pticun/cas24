package org.alterq.util.enumeration;

public enum CompanyTypeEnum {
	COMPANY_COLLABORATIVE_PUBLIC(1),COMPANY_NON_COLLABORATIVE_PUBLIC(2),
	COMPANY_COLLABORATIVE_PRIVATE(3),COMPANY_NON_COLLABORATIVE_PRIVATE(4)
	;
 
	private int value;

    private CompanyTypeEnum(int value) {
            this.value = value;
    }
    public int getValue(){
    	return value;
    }
}
