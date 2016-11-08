package edu.wcsu.cs360.battleship.common.repository;

import edu.wcsu.cs360.battleship.common.domain.entity.User;

import javax.persistence.EntityManager;

/**
 * Implementation of a {@link IUserRepository}
 */
@SuppressWarnings("unchecked")
public class UserRepository implements IUserRepository {

	private EntityManager entityManager;

	public UserRepository(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

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
		return entityManager.createQuery("SELECT u FROM User u").getResultList();
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
