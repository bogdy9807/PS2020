package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;

import dataAccess.Connections;
import model.ProgramariService;

public class ProgramariServiceDAO extends AbstractDAO<ProgramariService>{

    public ArrayList<ProgramariService> selectByIdClient(int idClient){
        String sql = "SELECT * FROM programariservice WHERE client_id = " + idClient;
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

    public ArrayList<ProgramariService> selectByIdService(int id){
        String sql = "SELECT * FROM programariservice WHERE service_id = " + id;
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

    public void deleteByServiceId(int id) {
        String sql = "DELETE FROM new_schema.programariservice WHERE service_id = " + id;
        Connection conn = Connections.getConnection();
        PreparedStatement statement = null;
        try {
            statement = conn.prepareStatement(sql);
            statement.execute();
            // Success printing
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING,e.getMessage());
        } finally {
            Connections.close(conn);
            Connections.close(statement);
        }
    }
}
