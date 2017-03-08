package se.kawi.quoteservice.resource;

import javax.validation.Valid;
import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.stereotype.Component;

import se.kawi.quoteservice.model.Quote;
import se.kawi.quoteservice.service.QuoteService;

@Component
@Path("/quotes")
@Produces({ MediaType.APPLICATION_JSON + "; charset=UTF-8", MediaType.APPLICATION_XML + "; charset=UTF-8" })
public class QuoteResource extends BaseResource<Quote, QuoteService> {

	public QuoteResource(QuoteService quoteService) {
		super(quoteService);
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
	public Response save(@Valid Quote quote) {
		return super.save(quote);
	}
	
}
