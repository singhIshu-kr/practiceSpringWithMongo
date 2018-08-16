
package library.repository;

import library.domain.BookStock;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface StockRepository extends MongoRepository<BookStock,String> {
    List<BookStock> findByTypeId(String name);
}
