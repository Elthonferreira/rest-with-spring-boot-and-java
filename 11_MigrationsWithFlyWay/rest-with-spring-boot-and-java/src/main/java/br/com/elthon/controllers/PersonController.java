package br.com.elthon.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.elthon.data.vo.v1.PersonVO;
import br.com.elthon.services.PersonServices;

@RestController
@RequestMapping("/api/person/v1")
public class PersonController {
	
	@Autowired
	private PersonServices personServices;
	//private PersonServices personServices = new PersonServices();

	@GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}) // RequestMapping vai mapear uma requisição para um método
	public List<PersonVO> findAll() throws Exception {
	
		return personServices.findAll();
	}

	@GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public PersonVO findById(@PathVariable (value = "id") Long id) throws Exception {

		return personServices.findById(id);
	}

	@PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
			     produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}) 
	public PersonVO create(@RequestBody PersonVO person) throws Exception {
	
		return personServices.create(person);
	}

	@PutMapping(consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
			    produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public PersonVO update(@RequestBody PersonVO person) throws Exception {
	
		return personServices.update(person);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> delete(@PathVariable (value = "id") Long id) throws Exception {
	
		personServices.delete(id);
		return ResponseEntity.noContent().build();
	}
}
