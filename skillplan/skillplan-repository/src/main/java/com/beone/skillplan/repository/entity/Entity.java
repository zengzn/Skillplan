/*
 * (c) zeng
 */
package com.beone.skillplan.repository.entity;

/**
 * Persistent entity interface
 *
 * @param <T> The type class of the primary key
 */
public interface Entity<T> {

	/**
	 * Returns the primary key of this entity. 
	 * @return The primary key
	 */
	T getId();
	
	/**
	 * Set the primary key of this entity.
	 * @param id The primary key object
	 */
	void setId(T id);
	
	/**
	 * Returns the version of the entity used for optimistic concurrency control
	 * @return The integer value of the version
	 */
	Integer getVersion();

}