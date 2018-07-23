package com.beone.skillplan;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import com.beone.skillplan.bl.ISkillService;
import com.beone.skillplan.bl.mapper.Mapper;
import com.beone.skillplan.model.SkillModel;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Path("/skills")
@Api(value = "/skills", tags = "Skills")
public class SkillResource extends AbstractResource {
	@Inject
	private ISkillService skillService;
	@Inject
	private Mapper customMapper;

	//ZeitBuchungen
	@SuppressWarnings("unchecked")
	@GET
	@Path("/{azureId}")
	@Produces({ "application/json" })
	@ApiOperation(value = "readSkillsForUser", notes = "Read all skills for a specific user", response = SkillModel.class, responseContainer = "List")
	public List<SkillModel> readSkills(@ApiParam("The id of the user") @PathParam(ApiParameters.AZURE_ID) String azureId, 
			@ApiParam("Flag to read all skills or just the open") @QueryParam("closed") boolean closed) {
		return (List<SkillModel>) customMapper.mapAll(this.skillService.readSkillsForAzureId(azureId, closed), SkillModel.class, azureId);
	}
}
