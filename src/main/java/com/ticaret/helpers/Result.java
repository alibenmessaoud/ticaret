package com.ticaret.helpers;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Event", namespace="Event")
@XmlAccessorType(XmlAccessType.FIELD)
public class Result {
	
	private String operation;
	private String message;
	private String flagId;
	private int id;
	private String flagText;
	private String text;
	
	public Result() {		
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getFlagId() {
		return flagId;
	}

	public void setFlagId(String flagId) {
		this.flagId = flagId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFlagText() {
		return flagText;
	}

	public void setFlagText(String flagText) {
		this.flagText = flagText;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}	
	
	
}
