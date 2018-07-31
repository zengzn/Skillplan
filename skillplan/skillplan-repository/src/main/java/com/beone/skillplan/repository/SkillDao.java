/*
 * (c) zeng
 */

package com.beone.skillplan.repository;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.Dependent;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.ListJoin;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import com.beone.skillplan.repository.dto.PaginatingResultDTO;
import com.beone.skillplan.repository.entity.Skill;
import com.beone.skillplan.repository.entity.Skill_;
import com.beone.skillplan.repository.entity.User;
import com.beone.skillplan.repository.entity.User_;
import com.google.common.collect.ImmutableList;

/**
 * concrete repository implementation of the skill entity
 */
@Dependent
public class SkillDao extends AbstractDao<Skill> implements ISkillDao {

	
	@Override
	public PaginatingResultDTO<Skill> readSkilledUsers(int skillId) {
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<Skill> query = criteriaBuilder.createQuery(Skill.class);

		Root<Skill> root = query.from(Skill.class);

		ListJoin<Skill, User> join = root.join(Skill_.skilledUsers, JoinType.LEFT);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(criteriaBuilder.equal(join.get(User_.userSkills), skillId));
		
		
		query.where(predicates.toArray(new Predicate[] {}));

		TypedQuery<Skill> typedQuery = em.createQuery(query);
		PaginatingResultDTO<Skill> paginatingResultDto = new PaginatingResultDTO<>();
		paginatingResultDto.setResultList(ImmutableList.copyOf(typedQuery.getResultList()));

		return paginatingResultDto;
	}

	@Override
	public PaginatingResultDTO<Skill> readAllEntriesForUser(long userId) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
