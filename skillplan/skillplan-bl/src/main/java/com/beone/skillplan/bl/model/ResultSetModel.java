/*
 * (c) zhen
 */
package com.beone.skillplan.bl.model;

import java.util.List;

import com.google.common.primitives.UnsignedLong;
import com.beone.skillplan.repository.entity.Entity;

/**
 * The result set for all ui model lists.
 *
 */
public class ResultSetModel<T extends Entity<?>> {
	
	private String sortFields;
	private String sortOrder;
	private UnsignedLong maxResults;
	private int firstResult;

	private List<T> resultList;

	public String getSortFields() {
		return sortFields;
	}

	public void setSortFields(final String sortFields) {
		this.sortFields = sortFields;
	}

	public String getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(final String sortOrder) {
		this.sortOrder = sortOrder;
	}

	public UnsignedLong getMaxResults() {
		return maxResults;
	}

	public void setMaxResults(final UnsignedLong maxResults) {
		this.maxResults = maxResults;
	}

	public int getFirstResult() {
		return firstResult;
	}

	public void setFirstResult(final int firstResult) {
		this.firstResult = firstResult;
	}

	public List<T> getResultList() {
		return resultList;
	}

	public void setResultList(final List<T> resultList) {
		this.resultList = resultList;
	}

}