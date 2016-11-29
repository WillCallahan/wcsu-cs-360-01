package edu.wcsu.cs360.battleship.common.repository;

import java.io.Serializable;

/**
 * Standardized interface to utilize CREATE, READ, UPDATE, and DELETE operations for domain model objects
 *
 * @param <T> Model to Access
 * @param <ID> Type of the Primary key of the model
 */
public interface ICrud<T, ID extends Serializable> {

	/**
	 * Finds a single instance of {@link T} with a matching {@link ID}
	 * @param id {@link ID} of {@link T} to search by
	 * @return Matching {@link T}
	 */
	T findOne(ID id);

	/**
	 * Finds all instances of {@link T}
	 * @return All instances of {@link T}
	 */
	Iterable<T> findAll();

	/**
	 * Gets the total number of {@link T}
	 * @return Total number of {@link T}
	 */
	Long count();

	/**
	 * Persists a new instance of {@link T} if the {@link ID} of {@link T} does not exist.
	 * If an instance of {@link T} exists for an {@link ID}, then the existing record of {@link T}
	 * is updated.
	 *
	 * @param type Object to saveAll
	 * @return Saved {@link T}
	 */
	T save(T type);

	/**
	 * Calls {@link ICrud#save} for each instance of {@link T} in the {@link Iterable}
	 *
	 * @see ICrud#saveAll
	 * @param typeList List of {@link T} to saveAll
	 * @return Saved list of {@link T}
	 */
	Iterable<T> saveAll(Iterable<T> typeList);

	/**
	 * Deletes an instance of {@link T} with a matching {@link ID}
	 * @param id {@link ID} of {@link T} to lookup for deletion
	 */
	void deleteById(ID id);

	/**
	 * Deletes an instance of {@link T} with a matching {@link ID}
	 * @param type {@link ID} of {@link T} to lookup for deletion
	 */
	void delete(T type);

}