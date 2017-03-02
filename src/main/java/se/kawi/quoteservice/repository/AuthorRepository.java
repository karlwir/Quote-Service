package se.kawi.quoteservice.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import se.kawi.quoteservice.model.Author;

public interface AuthorRepository extends PagingAndSortingRepository<Author, Long> {

}
