/*
 * (c) zeng
 */
package com.beone.skillplan.repository;

import java.util.Collection;
import java.util.Optional;

import javax.persistence.metamodel.SingularAttribute;

import com.beone.skillplan.repository.dto.PaginatingResultDTO;
import com.beone.skillplan.repository.entity.Entity;
import com.beone.skillplan.repository.util.EOrderDirection;

/**
 *
 */
interface Dao<T extends Entity<?>> {

	/**
	 *
	 * @param from
	 * @param to
	 * @param sortColumn
	 * @param eOrderDirection
	 * @return
	 */
	PaginatingResultDTO<T> readAll(final Integer from, final Integer to, final SingularAttribute<T, ?> sortColumn, EOrderDirection eOrderDirection);

	/**
	 * Create the given entity.
	 * @param entity
	 * @return
	 */
	T create(T entity);

	/**
	 * Attempt to read the entity with the given identifier.
	 * @param id Identifier of the entity
	 * @return 
	 */
	Optional<T> read(Object id);

	/**
	 * Update the content of the given entity
	 * @param entity
	 * @return
	 */
	T update(T entity);

	/**
	 * Delete the given entity
	 * @param id
	 */
	void delete(Object id);

	/**
	 * Delete a group of entities
	 * @param entities
	 */
	void delete(Collection<T> entities);

	/**
	 * Create a group of entities
	 * @param entities
	 */
	void createAll(Collection<T> entities);

	/**
	 * Read all entries with a pagination object
	 * @return
	 */
	PaginatingResultDTO<T> readAllEntries();
}
