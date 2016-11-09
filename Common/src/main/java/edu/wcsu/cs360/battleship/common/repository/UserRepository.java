package edu.wcsu.cs360.battleship.common.repository;

import edu.wcsu.cs360.battleship.common.domain.entity.User;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of a {@link IUserRepository}
 */
public class UserRepository implements IUserRepository {

	private EntityManager entityManager;
	private Log log = LogFactory.getLog(this.getClass());

	public UserRepository(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public User findOne(Long aLong) {
		try {
			return entityManager.createQuery("SELECT u FROM User u WHERE u.id = :id", User.class).setParameter("id", aLong).getSingleResult();
		} catch (NoResultException e) {
			log.warn("No result found; returning null. " + e.getMessage());
			return null;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Iterable<User> findAll() {
		return entityManager.createQuery("SELECT u FROM User u", User.class).getResultList();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Long count() {
		try {
			return entityManager.createQuery("SELECT COUNT(u) FROM User u", Long.class).getSingleResult();
		} catch (NoResultException e) {
			log.warn("No result found; returning 0L. " + e.getMessage());
			return 0L;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public User save(User type) {
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		if (entityManager.find(User.class, type.getUserId()) != null)
			return entityManager.merge(type);
		entityManager.persist(type);
		entityManager.flush();
		entityTransaction.commit();
		return type;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Iterable<User> save(Iterable<User> typeList) {
		List<User> userIterable = new ArrayList<>();
		for (User user : typeList)
			userIterable.add(save(user));
		return userIterable;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void delete(Long aLong) {
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		entityManager.remove(entityManager.find(User.class, aLong));
		entityTransaction.commit();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void delete(User type) {
		if (type == null)
			throw new NullPointerException();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		entityManager.remove(entityManager.find(User.class, type.getUserId()));
		entityTransaction.commit();
	}
}
