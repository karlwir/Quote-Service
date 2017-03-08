package se.kawi.quoteservice.resource;

import java.net.URI;

import javax.validation.Valid;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import se.kawi.quoteservice.model.AbstractEntity;
import se.kawi.quoteservice.service.BaseService;
import se.kawi.quoteservice.service.ServiceException;

abstract class BaseResource<E extends AbstractEntity, S extends BaseService<E, ?>> {

	protected final S service;
	
	@Context
	protected UriInfo uriInfo;
	
	protected BaseResource(S service) {
		this.service = service;
	}
	
	protected <T> T serviceRequest(ServiceRequest<T> serviceRequest) {
		try {
			return serviceRequest.request();			
		} catch (ServiceException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	protected Response save(@Valid E entity){
		E savedEntity = serviceRequest(() -> service.save(entity));
		URI location = uriInfo.getAbsolutePathBuilder().path("{id}").resolveTemplate("id", savedEntity.getId()).build();
		return Response.created(location).build();
	}

	protected Response byId(Long id) {
		E entity = serviceRequest(() -> service.getById(id));
		return entity == null ? Response.status(404).build() : Response.ok(entity).build();
	}

}
