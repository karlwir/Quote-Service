package se.kawi.quoteservice.service;

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

}
