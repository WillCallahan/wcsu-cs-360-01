package edu.wcsu.cs360.battleship.common.repository;

import edu.wcsu.cs360.battleship.common.domain.entity.User;

/**
 * Implementation of a {@link IUserRepository}
 */
public class UserRepository implements IUserRepository {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public User findOne(Long aLong) {
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Iterable<User> findAll() {
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Long count() {
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public User save(User type) {
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Iterable<User> save(Iterable<User> typeList) {
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void delete(Long aLong) {

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void delete(User type) {

	}
}
