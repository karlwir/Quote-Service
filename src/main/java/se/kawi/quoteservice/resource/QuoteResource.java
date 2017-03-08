package se.kawi.quoteservice.resource;

import java.util.List;

import javax.validation.Valid;
import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.stereotype.Component;

import se.kawi.quoteservice.model.Quote;
import se.kawi.quoteservice.service.QuoteService;

@Component
@Path("/quotes")
@Produces({ MediaType.APPLICATION_JSON + "; charset=UTF-8", MediaType.APPLICATION_XML + "; charset=UTF-8" })
@Consumes({ MediaType.APPLICATION_JSON + "; charset=UTF-8", MediaType.APPLICATION_XML + "; charset=UTF-8" })
public class QuoteResource extends BaseResource<Quote, QuoteService> {

	public QuoteResource(QuoteService quoteService) {
		super(quoteService);
	}
	
	@POST
	public Response save(@Valid Quote quote) {
		return super.save(quote);
	}

	@GET
	@Path("/{id}")
	public Response getQuote(@PathParam("id") Long id) {
		return super.byId(id);
	}
	
	@GET
	public Response getQuotes(@BeanParam QuoteQueryBean quoteQuery) {
		List<Quote> entities = serviceRequest(() -> service.query(quoteQuery.getPage(),
																  quoteQuery.getSize(),
																  quoteQuery.getSort(),
																  quoteQuery.getContent()));
		
		return Response.ok().entity(wrap(entities)).build();
	}

		private GenericEntity<List<Quote>> wrap(List<Quote> entities) {
		GenericEntity<List<Quote>> entity = new GenericEntity<List<Quote>>(entities) {};
		return entity;
	}
	
}
