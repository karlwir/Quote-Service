package se.kawi.quoteservice.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import se.kawi.quoteservice.model.Quote;

public interface QuoteRepository extends PagingAndSortingRepository<Quote, Long> {

}
