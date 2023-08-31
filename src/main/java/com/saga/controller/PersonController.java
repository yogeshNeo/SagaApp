package com.saga.controller;

import com.saga.domain.Person;
import com.saga.repository.PersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonRepository personRespository;

    @PostMapping
    public Mono<Person> savePerson(@RequestBody Person person) {
        log.info("Inside savePerson()");
        return personRespository.save(person);
    }

    @GetMapping
    public Flux<Person> getAllPersons() {
        log.info("Inside getAllPersons()");
        return personRespository.findAll();
    }

    @DeleteMapping
    public Mono<String> deletePerson(@RequestParam("id") Integer id) {
        log.info("deletePerson()");
        try {
            personRespository.deleteById(id).subscribe();
            log.info("Person Delete Successfully");
            return Mono.just("Person Delete Successfully..");
        } catch (Exception e) {
            e.printStackTrace();
            log.info("Error while deleting person !!!");
        }
        return Mono.just("Error while deleting person !!!");
    }

    @PutMapping
    public Mono<Person> updatePerson(@RequestBody Person person) {
        log.info("updatePerson()");
        try {
            return personRespository.save(person);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("Error while deleting person !!!");
        }
        return Mono.empty();
    }

}