package be.enyed.config;

import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationStartingEvent;
import org.springframework.context.event.ApplicationContextEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import be.enyed.model.Person;
import be.enyed.repository.PeopleRepository;

@Component
public class DataInitializer {

	private static final Logger log = LoggerFactory.getLogger(DataInitializer.class);

	private final PeopleRepository peopleRepository;

	public DataInitializer(PeopleRepository peopleRepository) {
		this.peopleRepository = peopleRepository;
	}

	@EventListener(ApplicationContextEvent.class)
	public void init() {
		log.debug("setup data data");
		if (0 == peopleRepository.count()) {
			Stream.of("Dirk", "Mark", "Peter", "Bart", "Jhon", "Luc").map(name -> new Person(name))
				  .forEach(s -> peopleRepository.save(s));
			log.debug("inserted :" + peopleRepository.findAll());
		} else {
			log.debug("found :" + peopleRepository.findAll());
		}
	}

}
