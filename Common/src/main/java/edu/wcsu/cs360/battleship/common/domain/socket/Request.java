package edu.wcsu.cs360.battleship.common.domain.socket;

import edu.wcsu.cs360.battleship.common.domain.enumeration.RequestMethod;

/**
 * Request object used to encapsulate data received from Socket Requests
 */
public class Request<T> {
	
	/**
	 * Content type of the {@link Request#body} (i.e. "application/json")
	 */
	private String contentType;
	
	/**
	 * Path to the target {@link java.lang.reflect.Method}
	 */
	private String target;
	
	/**
	 * {@link RequestMethod} of the request
	 */
	private RequestMethod method;
	
	/**
	 * Body of the request; the main content of the request
	 */
	private T body;
	
	public Request() {
		this.contentType = "application/json";
		this.method = RequestMethod.GET;
	}
	
	public Request(String target, RequestMethod method, T body) {
		this();
		this.target = target;
		this.method = method;
		this.body = body;
	}
	
	public Request(String target, T body) {
		this();
		this.target = target;
		this.body = body;
	}
	
	public Request(String contentType, String target, RequestMethod method, T body) {
		this.contentType = contentType;
		this.target = target;
		this.method = method;
		this.body = body;
	}
	
	public String getContentType() {
		return contentType;
	}
	
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	
	public String getTarget() {
		return target;
	}
	
	public void setTarget(String target) {
		this.target = target;
	}
	
	public RequestMethod getMethod() {
		return method;
	}
	
	public void setMethod(RequestMethod method) {
		this.method = method;
	}
	
	public T getBody() {
		return body;
	}
	
	public void setBody(T body) {
		this.body = body;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return String.format("Request"
				+ "%n\tContentType: " + contentType
				+ "%n\tTarget: " + target
				+ "%n\tMethod: " + method
				+ "%n\tBody: " + body);
	}
}
