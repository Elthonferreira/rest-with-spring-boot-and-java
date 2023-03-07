package br.com.elthon.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.elthon.exceptions.ResourceNotFoundException;
import br.com.elthon.data.vo.v1.PersonVO;
import br.com.elthon.repositories.PersonRepository;

@Service
public class PersonServices {
	
	private Logger logger = Logger.getLogger(PersonServices.class.getName());
	
	@Autowired
	PersonRepository personRepository;
	
	public List<PersonVO> findAll() {
		logger.info("Finding all people!");
		
		return personRepository.findAll();
	}

	public PersonVO findById(Long id) {
		logger.info("Finding one person!");
		
		return personRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
	}
	
	public PersonVO create(PersonVO person) {
		logger.info("Creating one person!");
		
		return personRepository.save(person);
	}
	
	public PersonVO update(PersonVO person) {
		logger.info("Updating one person!");
		
		var personToUpdate = personRepository.findById(person.getId())
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
		
		personToUpdate.setFirstName(person.getFirstName());
		personToUpdate.setLastName(person.getLastName());
		personToUpdate.setAddress(person.getAddress());
		personToUpdate.setGender(person.getGender());
		
		return personRepository.save(personToUpdate);
	}
	
	public void delete(Long id) {
		logger.info("Deleting one person!");
		
		var personToUpdate = personRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
		
		personRepository.delete(personToUpdate);
	}
	
}
