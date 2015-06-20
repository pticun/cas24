package org.alterq.domain;

public enum CompanyTypeEnum {
	COMPANY_COLLABORATIVE(1),COMPANY_NON_COLLABORATIVE(2);
 
	private int value;

    private CompanyTypeEnum(int value) {
            this.value = value;
    }
    public int getValue(){
    	return value;
    }
}
