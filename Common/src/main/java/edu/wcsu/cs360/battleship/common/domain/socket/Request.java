package edu.wcsu.cs360.battleship.common.domain.socket;

/**
 * Request object used to encapsulate data received from Socket Requests
 */
public class Request {

	private String contentType;

	private int statusCode;

	private String message;

	private Object body;

	public Request() {

	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}
}
