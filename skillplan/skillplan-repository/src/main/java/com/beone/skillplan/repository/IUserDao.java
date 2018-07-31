package com.beone.skillplan.repository;

import com.beone.skillplan.repository.entity.User;

public interface IUserDao extends Dao<User> {

	User readById(int id);

	
}
