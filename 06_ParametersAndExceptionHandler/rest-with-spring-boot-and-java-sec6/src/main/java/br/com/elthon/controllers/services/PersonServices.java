package br.com.elthon.controllers.services;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

import org.springframework.stereotype.Service;

import br.com.elthon.model.Person;

@Service
public class PersonServices {

	private final AtomicLong counter = new AtomicLong();
	private Logger logger = Logger.getLogger(PersonServices.class.getName());
	
	public List<Person> findAll() {
		List<Person> persons = new ArrayList<>();
		
		logger.info("Finding all people!");
		for (int i = 0; i < 8; i++) {
			Person person = savePerson(i);
			persons.add(person);
		}
		
		return persons;
	}

	public Person findById(String id) {
		
		logger.info("Finding one person!");
		Person person = new Person();
		person.setId(counter.incrementAndGet());
		person.setFirstName("Elthon");
		person.setLastName("Ferreira");
		person.setAddress("Recife - PE - Brasil");
		person.setGender("Male");
		return person;
	}
	

	private Person savePerson(int i) {
		
		logger.info("Finding one person!");
		Person person = new Person();
		person.setId(counter.incrementAndGet());
		person.setFirstName("Person name " + (i+1));
		person.setLastName("Last name " + (i+1));
		person.setAddress("Some adress in Brazil");
		person.setGender("Male");
		return person;
	}
	
	
}
