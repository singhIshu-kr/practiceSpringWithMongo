
package library.repository;

import library.domain.BookStock;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StockRepository extends MongoRepository<BookStock,String> {
    List<BookStock> findByTypeId(String name);
}
