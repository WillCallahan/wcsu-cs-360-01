package edu.wcsu.cs360.battleship.common.domain.socket;

/**
 * Response object used as a wrapper for a response to Socket Requests
 */
public class Response<T> {

	private String contentType;

	private int statusCode = 200;

	private String message;

	private T body;

	public Response() {

	}

	public Response(String contentType, int statusCode, String message, T body) {
		this.contentType = contentType;
		this.statusCode = statusCode;
		this.message = message;
		this.body = body;
	}
	
	public Response(T body) {
		this.body = body;
	}
	
	public Response(T body, int statusCode) {
		this.body = body;
		this.statusCode = statusCode;
	}
	
	public Response(int statusCode) {
		this.statusCode = statusCode;
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

	public T getBody() {
		return body;
	}

	public void setBody(T body) {
		this.body = body;
	}

}
