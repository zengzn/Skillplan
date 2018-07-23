/*
 * (c) zeng
 */
package com.beone.skillplan.repository.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Version;

/**
 * Abstract persisntent entity with common properties.
 * 
 */
@MappedSuperclass
public abstract class AbstractEntity<T extends Serializable> implements Serializable, Entity<T> {

	private static final long serialVersionUID = 4556320739319524554L;
	private T id;
	private Integer version;
	private Timestamp updated;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.beone.repository.model.Entity#getId()
	 */
	@Override
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
	public T getId() {
		return id;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.proway.repository.model.Entity#setId(T)
	 */
	@Override
	public void setId(final T id) {
		this.id = id;
	}

	@Override
	@Version
	public Integer getVersion() {
		return this.version;
	}

	public void setVersion(final Integer version) {
		this.version = version;
	}

	/**
	 * Returns the skillplan of the last persistent modification.
	 * 
	 * @return
	 */
	@Column
	public Timestamp getUpdated() {
		return this.updated;
	}

	/**
	 * This method is called implicitly with persistent modifications and should
	 * not be called explicitly, except maybe for test cases.
	 * 
	 * @param updated
	 */
	protected void setUpdated(final Timestamp updated) {
		this.updated = updated;
	}

	/**
	 * Sets the {@link #getModDate() modification date} to the current
	 * timestamp. This callback method is called before the entity is persistet
	 * or updated to the database.
	 */
	@PrePersist
	@PreUpdate
	public void initModDate() {
		this.setUpdated(new Timestamp(System.currentTimeMillis()));
	}

}