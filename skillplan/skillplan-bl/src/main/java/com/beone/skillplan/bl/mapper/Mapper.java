package com.beone.skillplan.bl.mapper;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.dozer.MappingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.beone.skillplan.bl.IUserService;
import com.beone.skillplan.model.SkillModel;
import com.beone.skillplan.model.UserModel;
import com.beone.skillplan.repository.entity.Skill;
import com.beone.skillplan.repository.entity.User;



/**
 * Mapper between model and entities classes. The mapping are done with dozer,
 * except in some cases where custom mappings with standard Java code are
 * performed.
 *
 */
@ApplicationScoped
public class Mapper {
	private Logger logger = LoggerFactory.getLogger(Mapper.class);
	
	@Inject
	private org.dozer.Mapper dozerMapper;
	@Inject
	private SkillMapper sMapper;
	@Inject
	private IUserService userService;
	
	
	
	/**
	 * Loops List of source objects and constructs new instance of destinationClass performing mapping
	 *
	 * @param source List
	 *            The source object
	 * @param destinationClass
	 *            The destination class
	 * @param <T>
	 * @return
	 */
	public <T> List<?> mapAll(List<?> sources, Class<T> destinationClass, long userId) {
		return sources.stream().map(source -> map(source, destinationClass, userId)).collect(Collectors.toList());
	}
	
	/**
	 * Constructs new instance of destinationClass and performs mapping from source
	 *
	 * @param source
	 *            The source object
	 * @param destinationClass
	 *            The destination class
	 * @param <T>
	 * @return
	 */
	public <T> T map(Object source, Class<T> destinationClass, long userId) {
		T destination = dozerMapper.map(source, destinationClass);
		customMappings(source, destination, userId);
		return destination;
	}

	/**
	 * Performs mapping between source and destination objects
	 *
	 * @param source
	 *            The source object
	 * @param destination
	 *            The destination class
	 */
	public void map(Object source, Object destination, long userId) {
		dozerMapper.map(source, destination);
		customMappings(source, destination, userId);
	}

	/**
	 * If the pair object and destination has some custom mappings which cannot be
	 * done with Dozer in a simple way, then a custom mappings will be applied
	 * between them.
	 * 
	 * @param source
	 *            The source object
	 * @param destination
	 *            The destination object
	 */
	public void customMappings(Object source, Object destination, long userId) {
		if (isMappingOf(source, SkillModel.class, destination, Skill.class)){
			setEntityExtras(source, destination, userId);
		}else if (isMappingOf(source, User.class, destination, UserModel.class)){
			User user = (User)source;
			UserModel um = (UserModel) destination;
			
		}
		
	}

	
	/**
	 * Returns true if the given combination of classes and objects types is
	 * matching.
	 * 
	 * @param source
	 *            The source object
	 * @param sourceClass
	 *            The source class
	 * @param destination
	 *            The destination object
	 * @param destinationClass
	 *            The destination class
	 * @return
	 */
	private boolean isMappingOf(Object source, Class<?> sourceClass, Object destination, Class<?> destinationClass) {
		return sourceClass.isInstance(source) && destinationClass.isInstance(destination);
	}
	
	

	
	private void setEntityExtras(Object sourceModel, Object destinationEntity, long id){
		User currentUser = userService.readById(id);
		if(isMappingOf(sourceModel, SkillModel.class, destinationEntity, Skill.class)){
			SkillModel source = (SkillModel) sourceModel;
			Skill destination = (Skill) destinationEntity;
			List<User> userList = new ArrayList<>();
			if (source.getUsers() != null) {
				source.getUsers().stream().forEach(skilledUser -> {
					User user = userService.readById(Long.valueOf(skilledUser));
					userList.add(user);
				});
			}
			
			destination.setSkilledUsers(userList);
		}
	}
	
	
}