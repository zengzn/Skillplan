/*
 * (c) Zhen
 */
package com.beone.skillplan.bl;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import com.beone.skillplan.repository.entity.Skill;

public interface ISkillService {

	/**
	 * read the skill identified by the given id. If the skill couldn't be found then an EntityNotFoundException will be thrown
	 * 
	 * @param id
	 * @return the skill found (Skill::id == id)
	 * @throws EntityNotFoundException
	 */
	Skill readSkill(Long id);

	/**
	 * find all skills associated with the given userId
	 * 
	 * @param azureId
	 *            of the user
	 * @return the skill found
	 * @throws EntityNotFoundException
	 */
	List<Skill> readSkillsForUserId(long userId);

	/**
	 * delete the persistent skill
	 * 
	 * @param id
	 * @param version
	 */
	void deleteSkill(Long id, Integer version);

	/**
	 * create skill
	 * 
	 * @return created Skill
	 * @param skill
	 */
	Skill createSkill(Skill skill);
}
