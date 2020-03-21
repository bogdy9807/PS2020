package model;

public class Service {
    private int id;
    private int prestator_id;
    private String informatii;
    private String nume;

    public Service() {

    }

    public Service(int id, int prestator_id, String informatii, String nume) {
        this.id = id;
        this.prestator_id = prestator_id;
        this.informatii = informatii;
        this.nume = nume;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPrestator_id() {
        return prestator_id;
    }

    public void setPrestator_id(int prestator_id) {
        this.prestator_id = prestator_id;
    }

    public String getInformatii() {
        return informatii;
    }

    public void setInformatii(String informatii) {
        this.informatii = informatii;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }
}
