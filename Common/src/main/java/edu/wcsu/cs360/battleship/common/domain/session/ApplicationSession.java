package edu.wcsu.cs360.battleship.common.domain.session;

import edu.wcsu.cs360.battleship.common.domain.entity.User;

import java.io.Serializable;

/**
 * Stores the users information
 */
public class ApplicationSession implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private User user;
	
	/**
	 * Nullifies all fields of the object
	 */
	public void invalidate() {
		user = null;
	}
	
	public ApplicationSession() {
		this.user = null;
	}
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
}
