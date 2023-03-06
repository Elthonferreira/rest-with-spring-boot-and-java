package br.com.elthon.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.elthon.controllers.services.PersonServices;
import br.com.elthon.model.Person;

@RestController
@RequestMapping("/person")
public class PersonController {
	
	@Autowired
	private PersonServices personServices;
	//private PersonServices personServices = new PersonServices();
	
	@RequestMapping(method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE) // RequestMapping vai mapear uma requisição para um método
	public List<Person> findAll() throws Exception {
	
		return personServices.findAll();
	}
	
	@RequestMapping(value = "/{id}", method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Person findById(@PathVariable (value = "id") Long id) throws Exception {
	
		return personServices.findById(id);
	}
	
	@RequestMapping(method=RequestMethod.POST, 
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE) 
	public Person create(@RequestBody Person person) throws Exception {
	
		return personServices.create(person);
	}
	
	@RequestMapping(method=RequestMethod.PUT, 
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE) 
	public Person update(@RequestBody Person person) throws Exception {
	
		return personServices.update(person);
	}
	
	@RequestMapping(value = "/{id}", method=RequestMethod.DELETE) 
	public void delete(@PathVariable (value = "id") Long id) throws Exception {
	
		personServices.delete(id);
	}
	
	

}
