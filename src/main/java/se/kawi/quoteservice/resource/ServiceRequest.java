package se.kawi.quoteservice.resource;

import javax.ws.rs.core.Response;

import se.kawi.quoteservice.service.ServiceException;

interface ServiceRequest {
	Response request() throws ServiceException;
}
