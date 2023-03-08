package br.com.elthon.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.elthon.exceptions.ResourceNotFoundException;
import br.com.elthon.mapper.DozerMapper;
import br.com.elthon.mapper.custom.PersonMapper;
import br.com.elthon.model.Person;
import br.com.elthon.data.vo.v1.PersonVO;
import br.com.elthon.data.vo.v2.PersonVOV2;
import br.com.elthon.repositories.PersonRepository;

@Service
public class PersonServices {

	private Logger logger = Logger.getLogger(PersonServices.class.getName());

	@Autowired
	PersonRepository personRepository;
	
	@Autowired
	PersonMapper personMapper;

	public List<PersonVO> findAll() {
		logger.info("Finding all people!");

		return DozerMapper.parseListObjects(personRepository.findAll(), PersonVO.class);
	}

	public PersonVO findById(Long id) {
		logger.info("Finding one person!");

		var entity = personRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

		return DozerMapper.parseObject(entity, PersonVO.class);
	}

	public PersonVO create(PersonVO person) {
		logger.info("Creating one person!");

		var entity = DozerMapper.parseObject(person, Person.class);

		var vo = DozerMapper.parseObject(personRepository.save(entity), PersonVO.class);

		return vo;
	}

	public PersonVOV2 createV2(PersonVOV2 person) {
		logger.info("Creating one person with V2!");

		var entity = personMapper.convertVoV2ToEntity(person);

		var vo =  personMapper.convertEntityToVoV2(personRepository.save(entity));

		return vo;
	}

	public PersonVO update(PersonVO person) {
		logger.info("Updating one person!");

		var personToUpdate = personRepository.findById(person.getId())
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

		personToUpdate.setFirstName(person.getFirstName());
		personToUpdate.setLastName(person.getLastName());
		personToUpdate.setAddress(person.getAddress());
		personToUpdate.setGender(person.getGender());

		var vo = DozerMapper.parseObject(personRepository.save(personToUpdate), PersonVO.class);

		return vo;
	}

	public void delete(Long id) {
		logger.info("Deleting one person!");

		var personToUpdate = personRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

		personRepository.delete(personToUpdate);
	}

}
