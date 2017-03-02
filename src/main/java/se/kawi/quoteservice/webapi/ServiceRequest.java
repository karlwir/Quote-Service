package se.kawi.quoteservice.webapi;

import se.kawi.quoteservice.service.ServiceException;

interface ServiceRequest<T> {
	T request() throws ServiceException;
}
