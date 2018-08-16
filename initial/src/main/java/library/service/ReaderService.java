package library.service;

import library.Exception.ReaderNotFoundException;
import library.domain.Reader;
import library.repository.ReaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReaderService {

    private ReaderRepository readerRepository;

    @Autowired
    public ReaderService(ReaderRepository customerRepository) {
        this.readerRepository = customerRepository;
    }

    public void addReader(String firstName, String lastName){
        readerRepository.save(new Reader(firstName,lastName));
    }

    public List<Reader> getByName(String name) {
        List<Reader> firstNames = readerRepository.findByFirstName(name);
        List<Reader> lastNames = readerRepository.findByLastName(name);

        firstNames.addAll(lastNames);

        return firstNames;
    }

    public Optional<Reader> getByID(String userId) throws ReaderNotFoundException {
        if(readerRepository.findById(userId).isPresent()){
            return readerRepository.findById(userId);
        }
        else {
            throw new ReaderNotFoundException("Reader is not present");
        }
    }

    public void updateReader(String userID, String name) throws ReaderNotFoundException {
        Optional<Reader> readerToBeUpdated = getByID(userID);
        if (readerToBeUpdated.isPresent()) {
            Reader reader = readerToBeUpdated.get();
            reader.setLastName(name);
            readerRepository.save(reader);
        }
        else {
            throw new ReaderNotFoundException("Reader is not present");
        }
    }

    public void removeReader(String userId) {
        readerRepository.deleteById(userId);
    }

    public boolean isValidReader(String memberId) {
        return readerRepository.existsById(memberId);
    }
}