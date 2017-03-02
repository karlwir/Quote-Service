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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import se.kawi.quoteservice.model.Quote;
import se.kawi.quoteservice.service.QuoteService;

@Component
@Path("quotes")
@Produces({ MediaType.APPLICATION_JSON + "; charset=UTF-8", MediaType.APPLICATION_XML + "; charset=UTF-8" })
public class QuoteWebApi extends BaseWebApi<Quote, QuoteService> {


	@Autowired
	public QuoteWebApi(QuoteService quoteService) {
		super(quoteService);
	}

	@GET
	@Path("/{id}")
	public Response byId(@PathParam("id") Long id) {
		Quote quote = serviceRequest(() -> service.getById(id));

		if (quote == null) {
			return Response.status(404).build();
		}

		return Response.ok(quote).build();
	}

	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	public Response save(@Valid Quote quoteInput) {

		Quote quoteOutput = serviceRequest(() -> service.save(quoteInput));

		URI location = uriInfo.getAbsolutePathBuilder().path("{id}").resolveTemplate("id", quoteOutput.getId()).build();

		return Response.created(location).build();
	}
}
