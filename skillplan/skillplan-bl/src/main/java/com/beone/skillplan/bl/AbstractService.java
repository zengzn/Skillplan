/*
 * (c) zhen
 */
package com.beone.skillplan.bl;

import java.util.List;

import javax.persistence.OptimisticLockException;
import javax.persistence.metamodel.SingularAttribute;

import com.beone.skillplan.bl.model.ResultSetModel;
import com.beone.skillplan.repository.entity.Entity;
import com.beone.skillplan.repository.util.EOrderDirection;
import com.google.common.primitives.UnsignedLong;

/**
 * Abstract Service.
 *
 * @param <T>
 *            Entity managed by the service
 */
abstract class AbstractService<T extends Entity<?>> {

	protected void checkTechVersion(final T entity, final Integer techVersion) {
		Integer currentVersion = entity.getVersion();
		
		if (!currentVersion.equals(techVersion)) {
			String entityName = entity.getClass().getSimpleName();
			Object id = entity.getId();
			String message = String.format("Version %d of entity %s(%s) did not match current version %d", techVersion, entityName, id, currentVersion);
			throw new OptimisticLockException(message);
		}
	}

	/**
	 * the category may contain a initiated Long with 0 value. This will lead to an
	 * error APPLICATION ERROR: transaction still active in request with status 1
	 * 
	 * @param entity
	 */
	protected void validateAndCorrectId(final Entity<?> entity) {
		if (entity.getId() != null && entity.getId() instanceof Long && ((Long) entity.getId()) == 0) {
			entity.setId(null);
		}
	}

	protected ResultSetModel<T> createResultSetModel(final List<T> resultList, final Integer from,
			final UnsignedLong maxResults, final SingularAttribute<T, ?> sortColumn,
			final EOrderDirection eOrderDirection) {
		// Map the category to a result set
		final ResultSetModel<T> resultSet = new ResultSetModel<>();
		resultSet.setResultList(resultList);
		resultSet.setMaxResults(maxResults);
		resultSet.setFirstResult(from);
		resultSet.setSortFields(sortColumn.getName());
		resultSet.setSortOrder(eOrderDirection.toString());
		return resultSet;
	}

}
