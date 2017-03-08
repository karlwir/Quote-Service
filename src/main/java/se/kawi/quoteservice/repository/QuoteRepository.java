package se.kawi.quoteservice.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import se.kawi.quoteservice.model.Quote;

public interface QuoteRepository extends PagingAndSortingRepository<Quote, Long> {

	@Query("select q from #{#entityName} q where q.content like %:content%")
	Page<Quote> query(Pageable createPageRequest, @Param("content")String content);

}
