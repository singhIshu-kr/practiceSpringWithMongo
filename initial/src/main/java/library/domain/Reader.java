package library.domain;

import org.springframework.data.annotation.Id;

import java.util.Date;


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