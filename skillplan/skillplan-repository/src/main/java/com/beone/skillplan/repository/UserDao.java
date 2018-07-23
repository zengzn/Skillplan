package com.beone.skillplan.repository;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import com.beone.skillplan.repository.entity.User;
import com.beone.skillplan.repository.entity.User_;

public class UserDao extends AbstractDao<User> implements IUserDao {

	@Override
	public User readById(int id) {
		final CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();

		final CriteriaQuery<User> criteria = criteriaBuilder.createQuery(User.class);
		Root<User> user = criteria.from(User.class);
		Predicate[] predicates = new Predicate[] { criteriaBuilder.equal(user.get(User_.id), id) };
		criteria.select(user);
		criteria.where(predicates);
		
		final TypedQuery<User> typedQuery = em.createQuery(criteria);

		return typedQuery.getSingleResult();
	}
	
	
}
