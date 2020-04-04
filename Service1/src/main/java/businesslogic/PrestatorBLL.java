package businesslogic;

import com.first.ObserverProgramari;
import dao.PrestatorDAO;
import model.Prestator;
import model.ProgramariService;

import java.util.ArrayList;
import java.util.HashMap;

public class PrestatorBLL implements ObserverProgramari {

    private PrestatorDAO prestatorDAO;

    private HashMap<Integer,ArrayList<ProgramariService>> programariNeconfirmate;

    public PrestatorBLL() {
        prestatorDAO = new PrestatorDAO();
        programariNeconfirmate = new HashMap<Integer,ArrayList<ProgramariService>>();
    }

    public PrestatorBLL(PrestatorDAO dao){
        prestatorDAO = dao;
    }

    public Prestator findById(int id) {
        return prestatorDAO.findById(id);
    }

    public ArrayList<Prestator> selectAll(){
        return prestatorDAO.selectAll();
    }

    public void insert(Prestator x) {
        prestatorDAO.insert(x);
    }

    public void update(ProgramariService x, int id) {
        ArrayList<ProgramariService> list = programariNeconfirmate.get(id);
        if(list == null){
            list = new ArrayList<ProgramariService>();
        }
        list.add(x);
        programariNeconfirmate.put(id,list);
    }

    public ArrayList<ProgramariService> getProgramariNeconfirmateByPrestatorId(int id){
        return programariNeconfirmate.get(id);
    }

    public void update(Prestator x){
        prestatorDAO.update(x);
    }

    public void deleteById(int id){
        prestatorDAO.deleteById(id);
    }
}
