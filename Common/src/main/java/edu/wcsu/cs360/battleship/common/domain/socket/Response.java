package edu.wcsu.cs360.battleship.common.domain.socket;

/**
 * Response object used as a wrapper for a response to Socket Requests
 */
public class Response {

	private String contentType;

	private int statusCode;

	private String message;

	private Object body;

	public Response() {

	}

	public Response(String contentType, int statusCode, String message, Object body) {
		this.contentType = contentType;
		this.statusCode = statusCode;
		this.message = message;
		this.body = body;
	}
	
	public Response(int statusCode) {
		
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
