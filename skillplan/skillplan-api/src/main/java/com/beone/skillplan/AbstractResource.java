package com.beone.skillplan;

import javax.inject.Inject;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.beone.skillplan.bl.mapper.Mapper;

/**
 * Abstract JAX-RS resource class
 * @author Zhen
 *
 */
@Produces("application/json")
public abstract class AbstractResource {

	protected static final Response RESPONSE_STATUS_OK =  Response.ok().build();
	
	@Inject
	protected Mapper mapper;
	
}