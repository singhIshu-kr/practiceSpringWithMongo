package library;

import library.domain.Reader;
import library.repository.ReaderRepository;
import library.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application implements CommandLineRunner {

//    @Autowired
//    private ReaderRepository repository;
//
//    @Autowired
//    private StockService stockService;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

//        repository.deleteAll();
//
//        // save a couple of customers
//        repository.save(new Reader("Alice", "Smith"));
//        repository.save(new Reader("Bob", "Smith"));
//
//        //save a couple of books
//
//
//        // fetch all customers
//        System.out.println("Customers found with findAll():");
//        System.out.println("-------------------------------");
//        for (Reader customer : repository.findAll()) {
//            System.out.println(customer);
//        }
    }

}