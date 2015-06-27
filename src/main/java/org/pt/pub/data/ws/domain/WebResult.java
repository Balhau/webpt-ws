package org.pt.pub.data.ws.domain;


public class WebResult<T> {
	
	public static final String OK="OK";
	public static final String ERROR="ERROR";
	
	private T message;
	private boolean error;
	private String statusMessage;
	
	public WebResult(){}
	
	public WebResult(T message){
		this.message=message;
	}
	
	public T getMessage() {
		return message;
	}
	public void setMessage(T message) {
		this.message = message;
	}
	public boolean isError() {
		return error;
	}
	public void setError(boolean error) {
		this.error = error;
	}
	public String getStatusMessage() {
		return statusMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.statusMessage = errorMessage;
	}
	
	public static <T> WebResult<T> ok(T message){
		return new WebResult<T>(message).error(false).statusMessage(WebResult.OK);
	}
	
	public static <T> WebResult<T> fail(String errorMessage){
		return new WebResult<T>().error(true).statusMessage(errorMessage);
	}
	
	//Builder Methods
	public WebResult<T> message(T message){
		this.message=message;
		return this;
	}
	
	public WebResult<T> error(boolean isError){
		this.error=isError;
		return this;
	}
	
	public WebResult<T> statusMessage(String statusMessage){
		this.statusMessage=statusMessage;
		return this;
	}
}
