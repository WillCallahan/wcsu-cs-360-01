package edu.wcsu.cs360.battleship.common.domain.socket;

/**
 * Request object used to encapsulate data received from Socket Requests
 */
public class Request {

	private String contentType;

	private String target;

	private String method;

	private Object body;

	public Request() {

	}

	public Request(String contentType, String target, String method, Object body) {
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

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public Object getBody() {
		return body;
	}

	public void setBody(Object body) {
		this.body = body;
	}
}
