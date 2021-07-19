package com.abrito10.personapi.service;

import com.abrito10.personapi.dto.request.PersonDTO;
import com.abrito10.personapi.dto.response.MessageResponseDTO;
import com.abrito10.personapi.entity.Person;
import com.abrito10.personapi.exception.PersonNotFoundException;
import com.abrito10.personapi.mapper.PersonMapper;
import com.abrito10.personapi.repository.PersonRepository;
import com.abrito10.personapi.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PersonService {

    private PersonRepository repository;

    private final PersonMapper personMapper = PersonMapper.INSTANCE;

    @Autowired
    public PersonService(PersonRepository repository) {
        this.repository = repository;
    }

    public MessageResponseDTO create(PersonDTO entityDTO) {

        Person entityToSave = personMapper.toModel(entityDTO);

        Person save = repository.save(entityToSave);

        return createMessageResponse(save.getId(), "Created person with ID ");
    }

    public List<PersonDTO> listAll() {
        List<Person> listAllPeople = repository.findAll();
        return listAllPeople.stream()
                .map(personMapper :: toDTO)
                .collect(Collectors.toList());
    }

    public PersonDTO findById(Long id) throws PersonNotFoundException {
        //Optional<Person> optionalEntity = repository.findById(id);
        //if(optionalEntity.isEmpty()){
        //    throw new PersonNotFoundException(id);
        //}
        //return personMapper.toDTO(optionalEntity.get());
        Person entity = verifyIfExists(id);

        return personMapper.toDTO(entity);

    }

    public void delete(Long id) throws PersonNotFoundException {
        verifyIfExists(id);

        repository.deleteById(id);
    }

    public MessageResponseDTO updateById(Long id, PersonDTO entityDTO) throws PersonNotFoundException {

        verifyIfExists(id);

        Person entityToUpdate = personMapper.toModel(entityDTO);

        Person updatedEntity = repository.save(entityToUpdate);
        return createMessageResponse(updatedEntity.getId(), "Updated person with ID ");
    }

    private Person verifyIfExists(Long id) throws PersonNotFoundException {
        return repository.findById(id)
                .orElseThrow(() -> new PersonNotFoundException(id));
    }

    private MessageResponseDTO createMessageResponse(Long id, String message) {
        return MessageResponseDTO
                .builder()
                .message(message + id)
                .build();
    }

}
