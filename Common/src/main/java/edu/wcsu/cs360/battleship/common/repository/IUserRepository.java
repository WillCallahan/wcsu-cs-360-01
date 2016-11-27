package edu.wcsu.cs360.battleship.common.repository;

import edu.wcsu.cs360.battleship.common.domain.entity.User;

/**
 * Interface Repository to access {@link User} data
 */
public interface IUserRepository extends ICrud<User, Long> {
	
	/**
	 * Finds a {@link User} with a matching {@link User#username}
	 *
	 * @param username {@link User#username}  to search by
	 * @return Matching {@link User} or null if there is no match
	 */
	User findOneByUsername(String username);
	
	/**
	 * Finds a {@link User} with a matching {@link User#username} and {@link User#password}
	 * @param username {@link User#username}  to search by
	 * @param password {@link User#password}  to search by
	 * @return Matching {@link User} or null if there is no match
	 */
	User findOneByUsernameAndPassword(String username, String password);
	
}