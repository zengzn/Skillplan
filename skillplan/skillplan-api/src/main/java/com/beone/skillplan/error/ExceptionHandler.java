package com.beone.skillplan.error;

import java.text.ParseException;

import javax.persistence.EntityNotFoundException;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.beone.skillplan.model.ExceptionResponseModel;

/**
 * Exception handler for the skillplan API.
 * @author zhen
 *
 */
@Provider
public class ExceptionHandler implements ExceptionMapper<Throwable> {

	private static final Logger logger = LoggerFactory.getLogger(ExceptionHandler.class);

	@Override
	public Response toResponse(Throwable exception) {

		Throwable cause = getCause(exception);
		Response response;
		if (isNotFound(cause)) {
			logger.error("Could not find entity", cause);
			response = toResponse(cause, Status.NOT_FOUND);
		} else if (isBadRequest(cause)) {
			logger.warn("Bad request", cause);
			response = toResponse(cause, Status.BAD_REQUEST);
		}  else {
			logger.error("Unexpected internal error", cause);
			response = toResponse(cause, Status.INTERNAL_SERVER_ERROR);
		}
		return response;
	}

	private static Throwable getCause(Throwable throwable) {
		Throwable cause = throwable.getCause();
		if(cause != null) {
			return cause;
		}
		return throwable;
	}

	private boolean isBadRequest(Throwable exception) {
		return exception instanceof BadRequestException || exception instanceof ParseException
				|| exception instanceof IllegalArgumentException || exception instanceof InvalidFormatException;
	}

	private boolean isNotFound(Throwable exception) {
		return exception instanceof NotFoundException || exception instanceof EntityNotFoundException;
	}

	private Response toResponse(Throwable e, Status status) {
		ExceptionResponseModel entity = new ExceptionResponseModel(e.getMessage(), status);
		return Response.status(status).entity(entity).build();
	}
}