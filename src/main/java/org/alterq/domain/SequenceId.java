package org.alterq.domain;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class SequenceId implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8713100268923907075L;

	@Id
	private String id;

	private String sequence;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSequence() {
		return sequence;
	}

	public void setSequence(String sequence) {
		this.sequence = sequence;
	}

}
