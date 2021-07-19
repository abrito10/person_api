package com.abrito10.personapi.controller;
import com.abrito10.personapi.dto.request.PersonDTO;
import com.abrito10.personapi.dto.response.MessageResponseDTO;
import com.abrito10.personapi.entity.Person;
import com.abrito10.personapi.exception.PersonNotFoundException;
import com.abrito10.personapi.repository.PersonRepository;
import com.abrito10.personapi.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/people")

public class PersonController {

    //private PersonRepository repository;
    private PersonService service;

    //@Autowired
    //public PersonController(PersonRepository repository) {
    //    this.repository = repository;
    //}

    @Autowired
    public PersonController(PersonService service) {
        this.service = service;
    }

    //@GetMapping
    //public String getBook() {
    //        return "API Test";
    //}

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResponseDTO createPerson(@RequestBody @Valid PersonDTO entityDTO) {
     //   Person save = repository.save(entity);
     //   return MessageResponseDTO
     //           .builder()
     //           .message("Criado pessoa com o ID " + save.getId())
     //           .build();
        return service.create(entityDTO);
    }

    @GetMapping
    public List<PersonDTO> listAll() {
        return service.listAll();
    }

    @GetMapping("/{id}")
    public PersonDTO findById(@PathVariable Long id) throws PersonNotFoundException {
        return service.findById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) throws PersonNotFoundException {
        service.delete(id);
    }
}
