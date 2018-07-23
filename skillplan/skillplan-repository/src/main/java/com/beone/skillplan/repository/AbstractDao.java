package com.beone.skillplan.repository;

import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.SingularAttribute;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;
import javax.transaction.UserTransaction;

import com.beone.skillplan.repository.dto.PaginatingResultDTO;
import com.beone.skillplan.repository.entity.Entity;
import com.beone.skillplan.repository.util.EOrderDirection;
import com.google.common.collect.ImmutableList;
import com.google.common.primitives.UnsignedLong;

public class AbstractDao <T extends Entity<?>> implements Dao<T> {
	
	private Class<T> type;
	
	@PersistenceContext(name = "persistenceUnit", type = PersistenceContextType.EXTENDED)
	protected EntityManager em;
	
	@Inject
	protected UserTransaction utx;
	
	@SuppressWarnings("unchecked")
	@PostConstruct
	public void init() {
		this.type = (Class<T>) ((ParameterizedType) getClass().getSuperclass().getGenericSuperclass()).getActualTypeArguments()[0];
	}
	
	@Override
	public PaginatingResultDTO<T> readAll(final Integer from, final Integer to, final SingularAttribute<T, ?> sortColumn, final EOrderDirection eOrderDirection) {
		final PaginatingResultDTO<T> paginatingResultDto = new PaginatingResultDTO<>();
		final CriteriaBuilder criteriaBuilder = this.em.getCriteriaBuilder();
		// count all entries -> to inform the user
		final CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
		countQuery.select(criteriaBuilder.count(countQuery.from(this.type)));
		final Long count = this.em.createQuery(countQuery).getSingleResult();
		
		final CriteriaQuery<T> criteria = criteriaBuilder.createQuery(this.type);
		final Root<T> root = criteria.from(this.type);
		
		criteria.select(root);
		if(EOrderDirection.ASC.equals(eOrderDirection)) {
			criteria.orderBy(criteriaBuilder.asc(root.get(sortColumn)) , criteriaBuilder.asc(root.get("id")));
		} else {
			criteria.orderBy(criteriaBuilder.desc(root.get(sortColumn)), criteriaBuilder.asc(root.get("id")));
		}

		final TypedQuery<T> typedQuery = em.createQuery(criteria);
		typedQuery.setFirstResult(from);
		typedQuery.setMaxResults(to - from);

		final List<T> resultList = typedQuery.getResultList();

		paginatingResultDto.setResultList(ImmutableList.copyOf(resultList));
		paginatingResultDto.setMaxEntries(UnsignedLong.valueOf(count));
		return paginatingResultDto;
	}

	@Override
	public PaginatingResultDTO<T> readAllEntries() {
		final PaginatingResultDTO<T> paginatingResultDto = new PaginatingResultDTO<>();
		final CriteriaBuilder criteriaBuilder = this.em.getCriteriaBuilder();
		
		final CriteriaQuery<T> criteria = criteriaBuilder.createQuery(this.type);
		final Root<T> root = criteria.from(this.type);
		criteria.select(root);
		criteria.orderBy(criteriaBuilder.asc(root.get("id")));

		final TypedQuery<T> typedQuery = em.createQuery(criteria);
		final List<T> resultList =  typedQuery.getResultList();

		paginatingResultDto.setResultList(ImmutableList.copyOf(resultList));
		return paginatingResultDto;
	}

	@Override
	public Optional<T> read(final Object id) {
		return Optional.ofNullable(em.find(this.type, id));
	}

	@Override
	@Transactional(value = TxType.MANDATORY)
	public T create(final T entity) {
		em.persist(entity);
		return entity;
	}

	@Override
	@Transactional(value = TxType.MANDATORY)
	public void createAll(final Collection<T> entities) {
			entities.forEach(em::persist);
	}

	@Override
	@Transactional(value = TxType.MANDATORY)
	public T update(final T entity) {
		return em.merge(entity);
	}

	@Override
	@Transactional(value = TxType.MANDATORY)
	public void delete(final Object id) {
			final T managedEntity = em.find(this.type, id);
			if (managedEntity != null) {
				em.remove(managedEntity);
			}
	}

	@Override
	@Transactional(value = TxType.MANDATORY)
	public void delete(final Collection<T> entities) {
			entities.stream()
			.map(Entity::getId).map(id -> em.find(this.type, id))
			.filter(Objects::nonNull)
			.forEach(em::remove);
	}

	public T readWithHint(Long id, List<String> hintList) {
		final EntityGraph<T> entityGraph = em.createEntityGraph(type);
		hintList.stream().forEach(hint -> entityGraph.addSubgraph(hint));
		final Map<String, Object> hints = new HashMap<>();
		hints.put("javax.persistence.fetchgraph", entityGraph);
		return em.find(type, id, hints);
	}
}
