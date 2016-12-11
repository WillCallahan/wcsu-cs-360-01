package edu.wcsu.cs360.battleship.common.domain.enumeration;

/**
 * Role that a user may have
 */
public enum Role {

	BANNED("banned"), PLAYER("player"), ADMINISTRATOR("administrator");

	private String role;

	private Role(String role) {
		this.role = role;
	}

	public String valueOf() {
		return role;
	}

}
