package com.beone.skillplan.repository;

import com.beone.skillplan.repository.dto.PaginatingResultDTO;
import com.beone.skillplan.repository.entity.Skill;

public interface ISkillDao extends Dao<Skill> {

	PaginatingResultDTO<Skill> readSkilledUsers(int skillId);

	PaginatingResultDTO<Skill> readAllEntriesForUser(long userId);
}

