package edu.wcsu.cs360.battleship.common.repository;

import edu.wcsu.cs360.battleship.common.domain.entity.User;

/**
 * Interface Repository to access {@link User} data
 */
public interface IUserRepository extends ICrud<User, Long> {
}