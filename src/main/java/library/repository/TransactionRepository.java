package library.repository;

import library.domain.TransactionRegister;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends MongoRepository<TransactionRegister,String> {

    List<TransactionRegister> findByMemberId(String memberId);

    List<TransactionRegister> findByBookID(String bookId);
}
