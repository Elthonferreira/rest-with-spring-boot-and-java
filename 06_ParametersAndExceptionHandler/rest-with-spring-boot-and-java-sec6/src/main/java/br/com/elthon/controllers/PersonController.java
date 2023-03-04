package br.com.elthon.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	
	
	@RequestMapping(value = "/{id}", method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE) // RequestMapping vai mapear uma requisição para um método
	public Person findById(@PathVariable (value = "id") String id) throws Exception {
	
		return personServices.findById(id);
	}
	

}
