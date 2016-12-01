package edu.wcsu.cs360.battleship.common.domain.singleton;

import edu.wcsu.cs360.battleship.common.domain.entity.User;

import java.io.Serializable;

public class ApplicationSession implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private User user;
	
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
