package se.kawi.quoteservice.webapi;

import java.net.URI;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.stereotype.Component;

import se.kawi.quoteservice.model.Author;
import se.kawi.quoteservice.service.AuthorService;


@Component
@Path("authors")
@Produces({ MediaType.APPLICATION_JSON + "; charset=UTF-8", MediaType.APPLICATION_XML + "; charset=UTF-8" })
public class AuthorWebApi extends BaseWebApi<Author, AuthorService> {

	protected AuthorWebApi(AuthorService authorService) {
		super(authorService);
	}
	
	@GET
	@Path("/{id}")
	public Response byId(@PathParam("id") Long id) {
		Author author = serviceRequest(() -> service.getById(id));

		if (author == null) {
			return Response.status(404).build();
		}

		return Response.ok(author).build();
	}
	
	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	public Response save(@Valid Author authorIn) {

		Author authorOut = serviceRequest(() -> service.save(authorIn));

		URI location = uriInfo.getAbsolutePathBuilder().path("{id}").resolveTemplate("id", authorOut.getId()).build();

		return Response.created(location).build();
	}

}
