package businesslogic;

import dao.PrestatorDAO;
import model.Prestator;

import java.util.ArrayList;

public class PrestatorBLL {

    private PrestatorDAO prestatorDAO;

    public PrestatorBLL() {
        prestatorDAO = new PrestatorDAO();
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
}
