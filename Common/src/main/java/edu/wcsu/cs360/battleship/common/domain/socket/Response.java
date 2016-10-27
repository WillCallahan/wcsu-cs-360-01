package edu.wcsu.cs360.battleship.common.domain.socket;

/**
 * Response object used as a wrapper for a response to Socket Requests
 */
public class Response {

	private String contentType;

	private String target;

	private String method;

	private Object body;

	public Response() {

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
