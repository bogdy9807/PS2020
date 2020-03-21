package businesslogic;

import java.util.ArrayList;

import dao.LoginDAO;
import model.Login;

public class LoginBLL {

    private LoginDAO loginDAO;

    public LoginBLL() {
        loginDAO = new LoginDAO();
    }

    public ArrayList<Login> selectAll(){
        return loginDAO.selectAll();
    }

    public void insert(Login x) {
        loginDAO.insert(x);
    }

}
