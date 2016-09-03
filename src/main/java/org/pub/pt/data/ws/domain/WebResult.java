package org.pub.pt.data.ws.domain;


/**
 * This object represents a general response from the controllers. This is usefull to encapsulate the messaging
 * and error handling on the controller responses
 * @author balhau
 *
 * @param <T>
 */
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
	
	/**
	 * This is method encapsulates the actions that create {@link WebResult} with all the logic
	 * to deal with exception handling. This way the controllers will not need to manage the error handling 
	 * @param wrapper {@link Wrapper} Functional interface to ease the implementation of the actions
	 * @return {@link WebResult} To be returned by the controller
	 */
	public static <T> WebResult<T> wrap(Wrapper<T> wrapper){
		try{
			T res=wrapper.wrap();
			return WebResult.<T>ok(res);
		}catch(Exception ex){
			return WebResult.<T>fail(ex.getMessage());
		}
	}
	
	public static <T> WebResult<T> wrapWithException(Wrapper<T> wrapper, Exception ex){
		try{
			T res=wrapper.wrap();
			return WebResult.<T>ok(res);
		}catch(Exception exp){
			return WebResult.<T>fail(ex.getMessage());
		}
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
