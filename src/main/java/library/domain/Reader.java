package library.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.data.annotation.Id;

import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Reader {

    @Id
    public String id;

    public String firstName;
    public String lastName;
    public Date dateOfBirth;
    public Date dateOfJoining;

    public Reader(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public String toString() {
        return String.format(
                "Reader[id=%s, firstName='%s', lastName='%s']",
                id, firstName, lastName, dateOfBirth);
    }

}