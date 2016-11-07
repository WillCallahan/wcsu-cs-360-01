package edu.wcsu.cs360.battleship.common.domain.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Stores information related to a specific User
 */
@Entity
@Table(name = "USER_PROFILE")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "USER_ID", unique = true)
	@GeneratedValue(generator = "USER_PROFILE_GEN", strategy = GenerationType.AUTO)
	@SequenceGenerator(sequenceName = "USER_PROFILE_SEQ", initialValue = 1, name = "USER_PROFILE_GEN")
	private long userId;

	@Column(name = "FIRST_NAME", length = 255)
	private String firstName;

	@Column(name = "MIDDLE_NAME", length = 255)
	private String middleName;

	@Column(name = "LAST_NAME", length = 255)
	private String lastName;

	@Column(name = "EMAIL", unique = true, length = 255)
	private String email;

	public User() {

	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
