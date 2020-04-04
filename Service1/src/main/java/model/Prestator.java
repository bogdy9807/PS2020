package model;

public class Prestator {
    private int id;
    private String nume;
    private String data_inreg;
    private String email;

    public Prestator() {

    }

    public Prestator(int id, String nume, String data_inreg, String email) {
        this.id = id;
        this.nume = nume;
        this.data_inreg = data_inreg;
        this.email = email;
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

    public String getData_inreg() {
        return data_inreg;
    }

    public void setData_inreg(String data_inreg) {
        this.data_inreg = data_inreg;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
