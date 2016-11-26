package edu.wcsu.cs360.battleship.common.domain.enumeration;

public enum RequestMethod {

	GET(2), POST(4), UPDATE(8), DELETE(16);
	
	private final int requestMethod;
	
	private RequestMethod(int requestMethod) {
		this.requestMethod = requestMethod;
	}
	
	public int getValue() {
		return requestMethod;
	}
	
	public String toString() {
		switch (this) {
			case GET:
				return "GET";
			case POST:
				return "POST";
			case UPDATE:
				return "UPDATE";
			case DELETE:
				return "DELETE";
			default:
				throw new IllegalArgumentException("Invalid RequestMethod: " + requestMethod);
		}
	}

}
