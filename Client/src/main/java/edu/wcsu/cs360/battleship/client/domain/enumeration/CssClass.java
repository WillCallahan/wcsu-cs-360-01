package edu.wcsu.cs360.battleship.client.domain.enumeration;

/**
 * Common CSS Classes
 */
public enum CssClass {
	
	INVALID("invalid"),
	VALID("valid");
	
	private final String css;
	
	private CssClass(String css) {
		this.css = css;
	}
	
	/**
	 * Gets the CSS class value of the enum
	 *
	 * @return CSS class
	 */
	public String valueOf() {
		return css;
	}
	
}
