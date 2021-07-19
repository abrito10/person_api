package com.abrito10.personapi.service;

import com.abrito10.personapi.dto.response.MessageResponseDTO;
import com.abrito10.personapi.entity.Person;
import com.abrito10.personapi.repository.PersonRepository;
import com.abrito10.personapi.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class PersonService {

    private PersonRepository repository;

    @Autowired
    public PersonService(PersonRepository repository) {
        this.repository = repository;
    }

    public MessageResponseDTO create(Person entity) {
        Person save = repository.save(entity);

        return MessageResponseDTO
                .builder()
                .message("Criado pessoa com o ID " + save.getId())
                .build();
    }

}
