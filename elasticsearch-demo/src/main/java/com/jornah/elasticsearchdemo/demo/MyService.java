package com.jornah.elasticsearchdemo.demo;

import com.jornah.elasticsearchdemo.dao.PersonRepository;
import com.jornah.elasticsearchdemo.pojo.Person;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyService {

    private final PersonRepository repository;

    public MyService(PersonRepository repository) {
        this.repository = repository;
    }

    public void doWork() {

        repository.deleteAll();

        Person person = new Person();
        person.setFirstname("Oliver");
        person.setLastname("Gierke");
        repository.save(person);

        List<Person> lastNameResults = repository.findByLastname("Gierke");
        lastNameResults.forEach(System.out::println);
        List<Person> firstNameResults = repository.findByFirstnameLike("Oli");
        firstNameResults.forEach(System.out::println);
    }
}