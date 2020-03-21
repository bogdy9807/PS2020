package model;

public class ProgramariService {

    private int id;
    private int service_id;
    private int client_id;
    private String data_programare;
    private String data_creare;
    private float cost_estimat;
    private String data_estimata_fin;

    public ProgramariService() {

    }

    public ProgramariService(int id, int service_id, int client_id, String data_programare, String data_creare, float cost_estimat, String data_estimata_fin) {
        this.id = id;
        this.service_id = service_id;
        this.cost_estimat = cost_estimat;
        this.client_id = client_id;
        this.data_creare = data_creare;
        this.data_programare = data_programare;
        this.setData_estimata_fin(data_estimata_fin);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getService_id() {
        return service_id;
    }

    public void setService_id(int service_id) {
        this.service_id = service_id;
    }

    public int getClient_id() {
        return client_id;
    }

    public void setClient_id(int client_id) {
        this.client_id = client_id;
    }

    public String getData_programare() {
        return data_programare;
    }

    public void setData_programare(String data_programare) {
        this.data_programare = data_programare;
    }

    public String getData_creare() {
        return data_creare;
    }

    public void setData_creare(String data_creare) {
        this.data_creare = data_creare;
    }

    public float getCost_estimat() {
        return cost_estimat;
    }

    public void setCost_estimat(float cost_estimat) {
        this.cost_estimat = cost_estimat;
    }

    public String getData_estimata_fin() {
        return data_estimata_fin;
    }

    public void setData_estimata_fin(String data_estimata_fin) {
        this.data_estimata_fin = data_estimata_fin;
    }
}
