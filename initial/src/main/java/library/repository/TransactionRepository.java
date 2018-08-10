package library.repository;

import library.domain.TransactionRegister;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TransactionRepository extends MongoRepository<TransactionRegister,String> {

    List<TransactionRegister> findByMemberId(String memberId);

    List<TransactionRegister> findByBookID(String bookId);
}
