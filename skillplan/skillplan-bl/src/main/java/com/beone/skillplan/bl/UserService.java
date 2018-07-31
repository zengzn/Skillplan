/*
 * (c)zhen
 */
package com.beone.skillplan.bl;

import java.util.List;
import java.util.Optional;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.beone.skillplan.repository.IUserDao;
import com.beone.skillplan.repository.entity.User;


@RequestScoped
public class UserService extends AbstractService<User> implements IUserService {
	
	private static final Logger logger = LoggerFactory.getLogger(UserService.class);
	
	@Inject
	private IUserDao userDao;
	
	
	@Override
	public User create(final User user) {
		try {
			return readById(user.getId());
		} catch (NoResultException e) {
			logger.trace("User {} does not exist and it will be created",user.getId(),e);
			validateAndCorrectId(user);
			
			return userDao.create(user);
		}
	}

	@Override
	public User readById(final long id) {
		final Optional<User> userOptional = userDao.read(id);
		if (userOptional.isPresent()) {
			return userOptional.get();
		}
		throw new EntityNotFoundException("the user identified by id '" + id + "' couldn't be found");
	}


	@Override
	public User update(final User user) {
		// delegate to repository
		return userDao.update(user);
	}

	@Override
	public List<User> readAll() {
		return userDao.readAllEntries().getResultList();
	}

	
	
}
