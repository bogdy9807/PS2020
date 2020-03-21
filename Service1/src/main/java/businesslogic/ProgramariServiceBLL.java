package businesslogic;

import java.util.ArrayList;

import dao.ProgramariServiceDAO;
import model.ProgramariService;

public class ProgramariServiceBLL {

    private ProgramariServiceDAO programariServiceDAO;
    public ProgramariServiceBLL() {
        programariServiceDAO = new ProgramariServiceDAO();
    }

    public ProgramariService findById(int id) {
        return programariServiceDAO.findById(id);
    }

    public ArrayList<ProgramariService> selectByIdClient(int id){
        return programariServiceDAO.selectByIdClient(id);
    }

    public ArrayList<ProgramariService> selectByIdService(int id){
        return programariServiceDAO.selectByIdService(id);
    }

    public void insert(ProgramariService p) {
        programariServiceDAO.insert(p);
    }

    public ArrayList<ProgramariService> selectAll(){
        return programariServiceDAO.selectAll();
    }

    public void deleteById(int id) {
        programariServiceDAO.deleteById(id);
    }

    public void deleteByServiceId(int id) {
        programariServiceDAO.deleteByServiceId(id);
    }
}
