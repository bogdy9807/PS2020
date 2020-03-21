package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;

import dataAccess.Connections;
import model.Service;

public class ServiceDAO extends AbstractDAO<Service>{

    public ArrayList<Service> selectByIdPrestator(int id){
        String sql = "SELECT * FROM service WHERE prestator_id = " + id;
        Connection conn = Connections.getConnection();
        PreparedStatement statement = null;
        ResultSet dbResult = null;
        try {
            statement = conn.prepareStatement(sql);
            dbResult = statement.executeQuery();
            return this.creeazaObiecte(dbResult);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING,e.getMessage());
        } finally {
            Connections.close(conn);
            Connections.close(statement);
            Connections.close(dbResult);
        }
        return null;
    }

    public ArrayList<Service> selectByName(String name) {
        String sql = "SELECT * FROM new_schema.service WHERE nume = \"" + name + "\"";
        Connection conn = Connections.getConnection();
        PreparedStatement statement = null;
        ResultSet dbResult = null;
        try {
            statement = conn.prepareStatement(sql);
            dbResult = statement.executeQuery();
            return this.creeazaObiecte(dbResult);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING,e.getMessage());
        } finally {
            Connections.close(conn);
            Connections.close(statement);
            Connections.close(dbResult);
        }
        return null;
    }
}
