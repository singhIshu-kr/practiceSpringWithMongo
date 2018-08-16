package library.repository;

import library.domain.Reader;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ReaderRepository extends MongoRepository<Reader, String> {

    List<Reader> findByFirstName(String firstName);

    List<Reader> findByLastName(String lastName);

    void deleteById(String userId);
}