package be.enyed.repository;

import java.util.Set;

import org.springframework.stereotype.Service;

import be.enyed.model.Person;
import be.enyed.repository.PeopleRepository;

@Service
public class PeopleService {
	
	private final PeopleRepository peopleRepository;

	public PeopleService(PeopleRepository peopleRepository) {
		this.peopleRepository = peopleRepository;
	}
	
	public Set<Person> findAll() {
		return peopleRepository.findAll();
	}
 
}
