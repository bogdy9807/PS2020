package model;

public class Login {

    private int id;
    private int usertype;
    private String username;
    private String password;
    private int id_client;
    private int id_prestator;

    public Login() {

    }

    public Login(int id, int usertype, String username, String password, int id_client, int id_prestator) {
        this.id = id;
        this.username = username;
        this.usertype = usertype;
        this.password = password;
        this.id_client = id_client;
        this.id_prestator = id_prestator;
    }

    public int getId_client() {
        return id_client;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
    }

    public int getId_prestator() {
        return id_prestator;
    }

    public void setId_prestator(int id_prestator) {
        this.id_prestator = id_prestator;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUsertype() {
        return usertype;
    }

    public void setUsertype(int usertype) {
        this.usertype = usertype;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
