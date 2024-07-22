package myskyblogcom.example.myskyblog.repository;

import myskyblogcom.example.myskyblog.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataJpaMemberRepository extends JpaRepository<Person, Long>, PersonRepository {

    Optional<Person> findByUsername(String username);
}
