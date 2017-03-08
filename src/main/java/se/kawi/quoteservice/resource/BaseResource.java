package se.kawi.quoteservice.resource;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.URI;
import java.util.List;

import javax.validation.Valid;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
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
	
	protected GenericEntity<List<E>> wrap(List<E> entities) {
		GenericEntity<List<E>> entity = new GenericEntity<List<E>>(entities) {};
		return entity;
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
	
	protected Response all(PagingQueryBean pageingQuery) {
		List<E> entities = serviceRequest(() -> service.getAll(pageingQuery.getPage(), pageingQuery.getSize(), pageingQuery.getSort()));
		return Response.ok().entity(new GenericEntity<List<E>>(entities, getType())).build();
	}

	private Type getType() {
		Type type = ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		return new TypeWrapper(type);
	}
	
	private static class TypeWrapper implements ParameterizedType {

		Type type;

		TypeWrapper(Type type) {
			this.type = type;
		}
		
		@Override
		public Type[] getActualTypeArguments() {
			return new Type[] { type };
		}

		@Override
		public Type getRawType() {
			return List.class;
		}

		@Override
		public Type getOwnerType() {
			return BaseResource.class;
		}
	}

}
