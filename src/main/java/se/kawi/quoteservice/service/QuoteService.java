package se.kawi.quoteservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import se.kawi.quoteservice.model.Quote;
import se.kawi.quoteservice.repository.QuoteRepository;

@Component
public class QuoteService extends BaseService<Quote, QuoteRepository> {

	@Autowired
	public QuoteService(QuoteRepository quoteRepository, ServiceTransaction serviceTransaction) {
		super(quoteRepository, serviceTransaction);
	}
	
}
