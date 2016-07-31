package org.alterq.util.enumeration;

public enum SequenceNameEnum {
	SEQUENCE_COMPANY("sequenceCompany"),
	SEQUENCE_OTHER("otherSequence"),
	SECRET_KEY("secretKey"),
	;
 
	private String value;

    private SequenceNameEnum(String value) {
            this.value = value;
    }
    public String getValue(){
    	return value;
    }
}
