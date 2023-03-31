package br.com.elthon.services;

import java.util.List;
import java.util.logging.Logger;

import br.com.elthon.controllers.PersonController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.elthon.exceptions.ResourceNotFoundException;
import br.com.elthon.mapper.DozerMapper;
import br.com.elthon.model.Person;
import br.com.elthon.data.vo.v1.PersonVO;
import br.com.elthon.repositories.PersonRepository;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class PersonServices {
	
	private Logger logger = Logger.getLogger(PersonServices.class.getName());
	
	@Autowired
	PersonRepository personRepository;
	
	public List<PersonVO> findAll() {
		logger.info("Finding all people!");
		
		var persons = DozerMapper.parseListObjects(personRepository.findAll(), PersonVO.class);

		persons.stream().forEach(p -> {
			try {
				p.add(linkTo(methodOn(PersonController.class).findById(p.getKey())).withSelfRel());
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		});

		return persons;
	}

	public PersonVO findById(Long id) throws Exception {
		logger.info("Finding one person!");
		
		var entity = personRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
		
		PersonVO vo = DozerMapper.parseObject(entity, PersonVO.class);
		vo.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
		return vo;
	}
	
	public PersonVO create(PersonVO person) throws Exception {
		logger.info("Creating one person!");
		
		var entity = DozerMapper.parseObject(person, Person.class);
		
		var vo = DozerMapper.parseObject(personRepository.save(entity), PersonVO.class);

		vo.add(linkTo(methodOn(PersonController.class).findById(vo.getKey())).withSelfRel());
		return vo;
	}
	
	public PersonVO update(PersonVO person) throws Exception {
		logger.info("Updating one person!");
		
		var personToUpdate = personRepository.findById(person.getKey())
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
		
		personToUpdate.setFirstName(person.getFirstName());
		personToUpdate.setLastName(person.getLastName());
		personToUpdate.setAddress(person.getAddress());
		personToUpdate.setGender(person.getGender());
		
		var vo = DozerMapper.parseObject(personRepository.save(personToUpdate), PersonVO.class);

		vo.add(linkTo(methodOn(PersonController.class).findById(vo.getKey())).withSelfRel());
		return vo;
	}
	
	public void delete(Long id) {
		logger.info("Deleting one person!");
		
		var personToUpdate = personRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
		
		personRepository.delete(personToUpdate);
	}
	
}
