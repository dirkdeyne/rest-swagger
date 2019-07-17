package be.enyed.repository;

import java.util.Set;

import org.springframework.data.repository.PagingAndSortingRepository;
import be.enyed.model.Person;

public interface PeopleRepository extends PagingAndSortingRepository<Person, Long> {
	
	Set<Person> findAll();
}
