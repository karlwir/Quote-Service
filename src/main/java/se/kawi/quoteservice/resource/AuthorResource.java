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

@Component
@Path("/authors")
@Produces({ MediaType.APPLICATION_JSON + "; charset=UTF-8", MediaType.APPLICATION_XML + "; charset=UTF-8" })
@Consumes({ MediaType.APPLICATION_JSON + "; charset=UTF-8", MediaType.APPLICATION_XML + "; charset=UTF-8" })
public class AuthorResource extends BaseResource<Author, AuthorService> {

	protected AuthorResource(AuthorService authorService) {
		super(authorService);
	}
	
	@POST
	public Response save(@Valid Author author) {
		return super.save(author);
	}

	@GET
	@Path("/{id}")
	public Response getAuthor(@PathParam("id") Long id) {
		return super.byId(id);
	}
	
	@GET
	public Response getAuthors(@BeanParam AuthorQueryBean authorQuery) {
		List<Author> entities = serviceRequest(() -> service.query(authorQuery.getPage(), 
									   authorQuery.getSize(), 
									   authorQuery.getSort(), 
									   authorQuery.getFirstname(), 
									   authorQuery.getLastname()));
		
		return Response.ok().entity(wrap(entities)).build();
	}
	
	private GenericEntity<List<Author>> wrap(List<Author> entities) {
		GenericEntity<List<Author>> entity = new GenericEntity<List<Author>>(entities) {};
		return entity;
	}

}
