package se.kawi.quoteservice.service;

import java.util.List;

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

	public List<Quote> query(int page, int size, String sort, String content, String authorId) throws ServiceException {
		Integer parsedId = Integer.valueOf(authorId);
		if(parsedId > 0){
			return execute(() -> repository.queryWithAuthor(createPageRequest(page, size, sort), content, authorId)).getContent();			
		} else {
			return execute(() -> repository.query(createPageRequest(page, size, sort), content)).getContent();
		}
	}
}
