package library.domain;

import org.springframework.data.annotation.Id;

import java.util.Date;

public class TransactionRegister {
    @Id
    private String transactionId;

    public final String memberId;
    public final String bookID;
    public final Date borrowDate;
    public Date returnDate;

    public TransactionRegister(String memberId,String bookID,Date borrowDate,Date returnDate) {
        this.memberId = memberId;
        this.bookID = bookID;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
    }

    @Override
    public String toString() {
        return "TransactionRegister{" +
                "memberId='" + memberId + '\'' +
                ", bookID='" + bookID + '\'' +
                ", borrowDate=" + borrowDate +
                ", returnDate=" + returnDate +
                '}';
    }

    public void setReturnDate(Date date) {
        this.returnDate = date;
    }
}
