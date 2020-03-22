package businesslogic;

import dao.LoginDAO;
import model.Login;

import java.util.ArrayList;

public class LoginBLL {

    private LoginDAO loginDAO;

    public LoginBLL() {
        loginDAO = new LoginDAO();
    }

    public LoginBLL(LoginDAO dao){
        loginDAO = dao;
    }

    public ArrayList<Login> selectAll(){
        return loginDAO.selectAll();
    }

    public void insert(Login x) {
        loginDAO.insert(x);
    }

}
