package org.alterq.util.enumeration;

public enum AccountTypeEnum {
	ACCOUNT_BET(1),
	ACCOUNT_PRIZE(2),
	ACCOUNT_DEPOSIT(3),
	ACCOUNT_WITHDRAWAL(4);
 
	private int value;

    private AccountTypeEnum(int value) {
            this.value = value;
    }
    public int getValue(){
    	return value;
    }
}