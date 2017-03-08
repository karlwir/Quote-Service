package se.kawi.quoteservice.resource;

import se.kawi.quoteservice.service.ServiceException;

interface ServiceRequest<T> {
	T request() throws ServiceException;
}
