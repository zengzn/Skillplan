/*
 * (c) zhen
 */
package com.beone.skillplan.bl;

import java.util.List;
import java.util.Optional;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.persistence.EntityNotFoundException;

import com.beone.skillplan.repository.ISkillDao;
import com.beone.skillplan.repository.entity.Skill;

@Dependent
public class SkillService extends AbstractService<Skill> implements ISkillService {
	
	@Inject
	private ISkillDao skillDao;

	
	@Override
	public Skill createSkill(final Skill skill) {
		if (idInitialized(skill)) {
			return readSkill(skill.getId());
		} else {
			return skillDao.create(skill);
		}
	}

	private boolean idInitialized(Skill skill) {
		return skill.getId() != null && skill.getId() != 0;
	}
	
	@Override
	public Skill readSkill(final Long id) {
		final Optional<Skill> skillOptional = skillDao.read(id);
		if (skillOptional.isPresent()) {
			return skillOptional.get();
		}
		throw new EntityNotFoundException("the skill identified by id '" + id + "' couldn't be found");
	}

	@Override
	public List<Skill> readSkillsForUserId(long userId) {
		
		return skillDao.readAllEntriesForUser(userId).getResultList();
		
	}

	@Override
	public void deleteSkill(Long id, Integer version) {
		final Skill skill = readSkill(id);
		super.checkTechVersion(skill, version);

		skillDao.delete(id);
	}
}
