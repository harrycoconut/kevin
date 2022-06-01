public class Book {
    private String name;
    private String author;
    private String publisher;
    private int status; // 0:­É¥X¡A1:ÂkÁÙ
    private int quantity;

    public Book(String n, String a, String p, int q, int s) {
        setName(n);
        setAuthor(a);
        setPublisher(p);
        setQuantity(q);
        setStatus(s);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

}
