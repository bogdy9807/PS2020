package businesslogic;

import dao.ServiceDAO;
import model.Service;

import java.util.ArrayList;

public class ServiceBLL {

    private ServiceDAO serviceDAO;



    public ServiceBLL() {
        serviceDAO = new ServiceDAO();
    }

    public ServiceBLL(ServiceDAO dao){
        serviceDAO = dao;
    }

    public void insert(Service s) {
        serviceDAO.insert(s);
    }

    public Service findById(int id) {
        return serviceDAO.findById(id);
    }

    public ArrayList<Service> selectAll(){
        return serviceDAO.selectAll();
    }

    public ArrayList<Service> selectByIdPrestator(int id){
        return serviceDAO.selectByIdPrestator(id);
    }

    public Service selectByName(String name) {
        return serviceDAO.selectByName(name).get(0);
    }

    public void deleteById(int id) {
        serviceDAO.deleteById(id);
    }

    public void update(Service x){
        serviceDAO.update(x);
    }
}
