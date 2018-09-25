package library.repository;

import library.domain.Reader;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReaderRepository extends MongoRepository<Reader, String> {

    List<Reader> findByFirstName(String firstName);

    List<Reader> findByLastName(String lastName);

    void deleteById(String userId);
}