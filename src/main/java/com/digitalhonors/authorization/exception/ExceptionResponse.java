package com.digitalhonors.authorization.exception;

import java.util.Date;

public class ExceptionResponse {

	private String message;
    private String details;
    private int status;
    private Date timestamp;
    
    public  ExceptionResponse(Date date,String msg,String details,int status){
    	this.message=msg;
    	this.details=details;
    	this.status=status;
    	this.timestamp=date;
    }
	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
}
