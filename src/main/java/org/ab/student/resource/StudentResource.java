package org.ab.student.resource;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.MediaType.APPLICATION_XML;

import java.util.List;
import java.util.Optional;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import org.ab.student.domain.Student;
import org.ab.student.domain.StudentSummary;
import org.ab.student.service.StudentFinder;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Path("students")
@Api("Finds students")
@Produces({ APPLICATION_JSON, APPLICATION_XML })
public class StudentResource {

	private final StudentFinder studentFinder;

	@Inject
	private StudentResource(final StudentFinder studentFinder) {
		this.studentFinder = studentFinder;
	}

	@GET
	@Path("/{studentId}")
	@ApiOperation(nickname = "findById", value = "Finds student details including classes and grades by student id", httpMethod = "GET")
	public Student findById(@PathParam("studentId") Long studentId) {
		return studentFinder.findById(studentId)
				.orElseThrow(() -> new WebApplicationException(Response.Status.NOT_FOUND));
	}

	@GET
	@ApiOperation(nickname = "findBy", value = "Finds student summaries by filtered by first and last names", httpMethod = "GET")
	public List<StudentSummary> findBy(@QueryParam("firstName") String first, @QueryParam("lastName") String last,
			@QueryParam("countMax") Integer countMax) {
		return studentFinder.findBy(Optional.ofNullable(first), Optional.ofNullable(last),
				Optional.ofNullable(countMax));
	}
	
	@GET
	@Path("/ping")
	@ApiOperation(nickname = "ping", value = "Resurce test method", httpMethod = "GET")
	public Response ping() {
		return Response.ok(studentFinder.ping()).build();

	}

}