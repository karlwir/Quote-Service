package se.kawi.quoteservice.resource;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.BeanParam;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Component;

import se.kawi.quoteservice.model.Author;
import se.kawi.quoteservice.service.AuthorService;
import se.kawi.quoteservice.service.ServiceException;


@Component
@Path("/authors")
@Produces({ MediaType.APPLICATION_JSON + "; charset=UTF-8", MediaType.APPLICATION_XML + "; charset=UTF-8" })
public class AuthorResource extends BaseResource<Author, AuthorService> {

	protected AuthorResource(AuthorService authorService) {
		super(authorService);
	}

	@GET
	@Path("/{id}")
	public Response byId(@PathParam("id") Long id) {
		return super.byId(id);
	}
	
	@GET
	public Response all(@BeanParam PagingQueryBean pageingQuery) {
		return super.all(pageingQuery);
	}
			
	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	public Response save(@Valid Author author) {
		return super.save(author);
	}
	
	
	@GET
	@Path("list")
	public List<Author> xmltest1() throws ServiceException {
		return service.getAll(0, 10, "asc");
	}
	
	@GET
	@Path("response")
	public Response xmltest2() throws ServiceException {
	    GenericEntity<List<Author>> entity = new GenericEntity<List<Author>>(service.getAll(0, 10, "asc")) {};
		return Response.ok(entity).build();
	}

}
