public class AccountData {
    private String name;
    private String ID;
    private String password;
    private int idenfication;

    public AccountData(String n, String a, String p, int i) {
        setName(n);
        setID(a);
        setPassword(p);
        setIdenfication(i);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getIdenfication() {
        return idenfication;
    }

    public void setIdenfication(int idenfication) {
        this.idenfication = idenfication;
    }

}
