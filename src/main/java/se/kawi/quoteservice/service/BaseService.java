package se.kawi.quoteservice.service;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.repository.PagingAndSortingRepository;

import se.kawi.quoteservice.service.ServiceException;
import se.kawi.quoteservice.service.ServiceTransaction.Action;
import se.kawi.quoteservice.model.AbstractEntity;

public abstract class BaseService<E extends AbstractEntity, R extends PagingAndSortingRepository<E, Long>> {

	protected R repository;
	protected ServiceTransaction serviceTransaction;
	
	protected BaseService(R repository, ServiceTransaction serviceTransaction) {
		this.repository = repository;
		this.serviceTransaction = serviceTransaction;
	}
	
	protected <T> T execute(Action<T> action) throws ServiceException {
		try {
			return action.execute();			
		} catch (DataAccessException e) {
			throw new ServiceException("Execute failed: " + e.getMessage());
		}
	}
	
	protected <T> T transaction(Action<T> action) throws ServiceException {
		try {
			return serviceTransaction.execute(() -> action.execute());			
		} catch (DataAccessException e) {
			throw new ServiceException("Transaction failed: " + e.getMessage());
		}
	}
	
	public E save(E entity) throws ServiceException {
		return execute(() -> repository.save(entity));
}
	
	public E getById(Long id) throws ServiceException {
		return execute(() -> repository.findOne(id));
	}
	
	public List<E> getAll(int page, int size, String sort) throws ServiceException {
		return execute(() -> repository.findAll(createPageRequest(page, size, sort))).getContent();
	}
	
	
	protected Pageable createPageRequest(int page, int size, String sort) {
		if(sort.equals("desc")) {
			return new PageRequest(page, size, Direction.DESC, "id");
		}
		return new PageRequest(page, size, Direction.ASC, "id");

	}
}
