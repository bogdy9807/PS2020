package businesslogic;

import dao.ClientDAO;
import model.Client;

import java.util.ArrayList;

/**
 * Clasa de logica pentru lucrul cu clientii
 * @author Bogdan
 *
 */
public class ClientBLL {
    private ClientDAO clientDAO;
    public ClientBLL() {
        clientDAO = new ClientDAO();
    }

    public ClientBLL(ClientDAO dao){
        clientDAO = dao;
    }

    public Client findById(int id) {
        return clientDAO.findById(id);
    }

    public ArrayList<Client> selectAll(){
        return clientDAO.selectAll();
    }

    public void insert(Client x) {
        clientDAO.insert(x);
    }
}