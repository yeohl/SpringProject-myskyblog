package myskyblogcom.example.myskyblog.repository;

import myskyblogcom.example.myskyblog.domain.Person;

import java.util.Optional;

public interface PersonRepository {

    Optional<Person> findByUsername(String username);
}
