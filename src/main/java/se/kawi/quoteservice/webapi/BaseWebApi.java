package se.kawi.quoteservice.webapi;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

import se.kawi.quoteservice.model.AbstractEntity;
import se.kawi.quoteservice.service.BaseService;
import se.kawi.quoteservice.service.ServiceException;

abstract class BaseWebApi<E extends AbstractEntity, S extends BaseService<E, ?>> {

	protected final S service;
	
	@Context
	protected UriInfo uriInfo;
	
	protected BaseWebApi(S service) {
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

}
