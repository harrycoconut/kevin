import java.time.*;
import java.time.temporal.*;

public class Record {
    private Book book;
    private LocalDate borrowDate;
    private LocalDate returnDate;
    private boolean bookStatus; // 是否已歸還 true:歸還
    private boolean dueStatus; // 逾期顯示 true:逾期
    private int fine;
    private String idenfication;

    public Record(Book b, LocalDate bd, LocalDate rd, boolean bs, boolean ds, int f, String i) {
        setBook(b);
        setBorrowDate(bd);
        setReturnDate(rd);
        setBookStatus(bs);
        setDueStatus(ds);
        setFine(f);
        setIdenfication(i);
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(LocalDate borrowDate) {
        this.borrowDate = borrowDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public boolean getBookStatus() {
        return bookStatus;
    }

    public void setBookStatus(boolean bookStatus) {
        this.bookStatus = bookStatus;
    }

    public boolean getDueStatus() {
        return dueStatus;
    }

    public void setDueStatus(boolean dueStatus) {
        this.dueStatus = dueStatus;
    }

    public int getFine() {
        calculusFine();
        return fine;
    }

    public void setFine(int fine) {
        this.fine = fine;
    }

    public String getIdenfication() {
        return idenfication;
    }

    public void setIdenfication(String idenfication) {
        this.idenfication = idenfication;
    }

    public void calculusFine() {
        int f = 0;
        LocalDate current = LocalDate.now();
        int difference = (int) ChronoUnit.DAYS.between(returnDate, current);
        if (difference > 0) {
            f += typeOfFine() + (difference - 1) * 5;
        }
        setFine(f);
    }

    public void checkDueStatus() {
        LocalDate current = LocalDate.now();
        int difference = (int) ChronoUnit.DAYS.between(returnDate, current);
        if (getBookStatus() == false) {
            if (difference > 0) {
                setDueStatus(true);
            }
        }
    }

    public int typeOfFine() {
        int x = 0;
        switch (idenfication) {
            case "學生":
                x = 100;
                break;
            case "老師":
                x = 90;
                break;
            case "職員":
                x = 80;
                break;
        }
        return x;
    }

    public int typeOfDays() {
        int x = 0;
        switch (idenfication) {
            case "學生":
                x = 14;
                break;
            case "老師":
                x = 21;
                break;
            case "職員":
                x = 28;
                break;
        }
        return x;
    }

    public String toString() {
        String text = String.format("%-30s%-15s%-15s%-10s%-10s%-10d", book.getName(), getBorrowDate(), getReturnDate(),
                ((getBookStatus()) ? "已歸還" : "尚未歸還"), ((getDueStatus()) ? "已歸還" : "尚未歸還"), getFine());
        return text;
    }
}
