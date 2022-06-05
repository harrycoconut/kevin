public class Book {
    private String name;
    private String author;
    private String publisher;
    private int ID;
    private boolean status; // true:�i�ɡAfalse:�ɥX

    public Book(String n, String a, String p, int i, boolean s) {
        setName(n);
        setAuthor(a);
        setPublisher(p);
        setID(i);
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

    public int getID() {
        return ID;
    }

    public void setID(int iD) {
        ID = iD;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String toString() {
        return "�ѦW : " + getName() + " �@��: " + getAuthor() + " �X����: " + getPublisher() + " ISBN: " + getID()
                + ((getStatus() == true) ? " �i�ɾ\" : " �w�Q�ɾ\");
    }

}
