/*
 * (c) Zhen
 */
package com.beone.skillplan.bl;

import java.util.List;

import com.beone.skillplan.repository.entity.User;

public interface IUserService {

	/**
	 * read all user
	 * 
	 * @return the user in the entity and a the list of found user
	 */
	List<User> readAll();

	/**
	 * create a new user
	 * 
	 * @param user
	 * @return the persistent user entity
	 */
	User create(User user);

	/**
	 * read the user identified by the given id. If the user couldn't be found
	 * then an EntityNotFoundException will be thrown
	 * 
	 * @param id
	 * @return the user found (User::id == id)
	 */
	User readById(long id);

	/**
	 * update the persistent user with the new values
	 * 
	 * @return the updated user
	 * @param user
	 */
	User update(User user);



	
}
