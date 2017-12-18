package com.emhc.error;

/**
 * UI message object
 * 
 * @author jerry.wang
 *
 */
public class Message {
	public static final String SUCCESS = "alert alert-success";
	public static final String ERROR = "alert alert-danger";
	public static final String JSON_SUCCESS = "Success";
	public static final String JSON_ERROR = "Error";

	private String status;
	private String message;

	public Message() {
		this.status = "";
		this.message = "";
	}

	public Message(String status, String message) {
		this.status = status;
		this.message = message;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
