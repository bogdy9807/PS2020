package businesslogic;

import java.util.ArrayList;

import dao.PrestatorDAO;
import model.Prestator;

public class PrestatorBLL {

    private PrestatorDAO prestatorDAO;

    public PrestatorBLL() {
        prestatorDAO = new PrestatorDAO();
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
