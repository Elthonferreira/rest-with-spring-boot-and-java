package br.com.elthon.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.elthon.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long>{}
