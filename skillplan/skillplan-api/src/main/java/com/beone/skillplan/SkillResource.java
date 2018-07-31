package com.beone.skillplan;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import com.beone.skillplan.bl.ISkillService;
import com.beone.skillplan.bl.mapper.Mapper;
import com.beone.skillplan.model.SkillModel;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Path("/skills")
@Api(value = "/skills", tags = "Skills")
public class SkillResource extends AbstractResource {
	@Inject
	private ISkillService skillService;
	@Inject
	private Mapper customMapper;

	//show skillplan
	@SuppressWarnings("unchecked")
	@GET
	@Path("/{userId}")
	@Produces({ "application/json" })
	@ApiOperation(value = "readSkillsForUser", notes = "Read all skills for a specific user", response = SkillModel.class, responseContainer = "List")
	public List<SkillModel> readUserSkills(
			   @PathParam("userId") long userId
			) {
		return (List<SkillModel>) customMapper.mapAll(this.skillService.readSkillsForUserId(userId), SkillModel.class);
	}
}
