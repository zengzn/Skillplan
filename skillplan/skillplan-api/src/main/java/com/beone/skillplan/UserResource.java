package com.beone.skillplan;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.CookieParam;
import javax.ws.rs.GET;

/**
 * @author zhen
 */

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;


import com.beone.skillplan.bl.IUserService;
import com.beone.skillplan.bl.mapper.Mapper;
import com.beone.skillplan.model.UserModel;

import com.beone.skillplan.repository.entity.User;
import com.beone.skillplan.utils.DateHelper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Path("/users")
@Api(value = "/users", tags = "Users")
public class UserResource extends AbstractResource {
	@Inject
	private IUserService userService;
	
	@Inject
	private Mapper customMapper;
	
	@SuppressWarnings("unchecked")
	@GET
	@Path("/userlist")
	@Produces({ "application/json" })
	@ApiOperation(value = "readUsers", notes = "Read all users of the app persists them in the database, if they do not exist", 
			response = UserModel.class, responseContainer = "List")
	@Transactional
	public List<UserModel> readUsers(){
		List<User> usersFromAzure = userService.readAllromAzure();
		return (List<UserModel>) customMapper.mapAll(usersFromAzure, UserModel.class, userId);
	}

	

	
	

}