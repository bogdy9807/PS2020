package model;
/**
 * Class Client
 * Clasa ce reprezinta modelul unui Client
 * @author Bogdan
 *
 */
public class Client {
    private int id;
    private String nume;
    private String datainreg;
    private String email;

    public Client() {

    }

    public Client(int id,String nume, String datainreg, String email) {
        this.id = id;
        this.nume = nume;
        this.setEmail(email);
        this.setDatainreg(datainreg);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getDatainreg() {
        return datainreg;
    }

    public void setDatainreg(String datainreg) {
        this.datainreg = datainreg;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
