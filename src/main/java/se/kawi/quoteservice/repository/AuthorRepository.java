package se.kawi.quoteservice.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import se.kawi.quoteservice.model.Author;

public interface AuthorRepository extends PagingAndSortingRepository<Author, Long> {

	@Query("select a from #{#entityName} a where a.firstname like %:fName% and a.lastname like %:lName% or a.firstname like %:nQuery% or a.lastname like %:nQuery%")
	Page<Author> query(Pageable pageable, @Param("fName") String firstname, @Param("lName")String lastname, @Param("nQuery")String nameQuery);

}
