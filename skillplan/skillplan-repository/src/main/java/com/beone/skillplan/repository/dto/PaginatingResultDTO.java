/*
 * (c) zeng
 */
package com.beone.skillplan.repository.dto;

import java.util.List;

import com.google.common.primitives.UnsignedLong;

/**
 * Pagination Result DTO.
 */
public final class PaginatingResultDTO<T> {

	private List<T> resultList;
	private UnsignedLong maxEntries;

	public List<T> getResultList() {
		return this.resultList;
	}

	public void setResultList(final List<T> resultList) {
		this.resultList = resultList;
	}

	public UnsignedLong getMaxEntries() {
		return this.maxEntries;
	}

	public void setMaxEntries(final UnsignedLong maxEntries) {
		this.maxEntries = maxEntries;
	}
}
