package org.alterq.domain;

public enum SequenceNameEnum {
	SEQUENCE_COMPANY("sequenceCompany"),SEQUENCE_OTHER("otherSequence");
 
	private String value;

    private SequenceNameEnum(String value) {
            this.value = value;
    }
    public String getValue(){
    	return value;
    }
}
