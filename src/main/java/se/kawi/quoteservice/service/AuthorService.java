package se.kawi.quoteservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import se.kawi.quoteservice.model.Author;
import se.kawi.quoteservice.repository.AuthorRepository;

@Component
public class AuthorService extends BaseService<Author, AuthorRepository> {

	@Autowired
	public AuthorService(AuthorRepository authorRepository, ServiceTransaction serviceTransaction) {
		super(authorRepository, serviceTransaction);
	}

	public List<Author> query(int page, int size, String sort, String firstname, String lastname) throws ServiceException {
		return execute(() -> repository.query(createPageRequest(page, size, sort), firstname, lastname)).getContent();
	}
}
