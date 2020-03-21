package dataAccess;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 * Clasa pentru conexiunea la baza de date
 * @author Bogdan
 *
 */
public class Connections {
    private static final String PASSWORD = "Destruct#10";
    private static final String USERNAME = "root";

    private static final String CONNECTION_STRING = "jdbc:mysql://localhost:3306/new_schema";
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final Logger LOGGER = Logger.getLogger(Connections.class.getName());

    private static Connections singleInst = new Connections();

    public Connections() {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            LOGGER.log(Level.WARNING, "An error occured while trying to connect to the database");
            System.out.println(e.getMessage());
        }
    }

    /**
     * Metoda ce creeaza o conexiune la baza de date folosind variabilele instanta ale clasei
     * @return
     * 		Conexiunea creata
     */
    private Connection createConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(CONNECTION_STRING, USERNAME, PASSWORD);
            return conn;
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        }
        return conn;
    }

    public static Connection getConnection() {
        return singleInst.createConnection();
    }

    /**
     * Metoda pentru inchiderea unei conexiuni
     * @param conn
     * 		conexiunea ce trebuie inchisa
     */
    public static void close(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                LOGGER.log(Level.WARNING, "Error when trying to close the DB connection !");
            }
        }
    }

    /**
     * Metoda pentru inchiderea unui Statement
     * @param st
     * 		Statement-ul ce trebuie inchis
     */
    public static void close(Statement st) {
        if(st != null) {
            try {
                st.close();
            } catch (SQLException e) {
                LOGGER.log(Level.WARNING, "Error when trying to close the statement");
            }
        }
    }

    /**
     * Metoda pentru inchiderea unui ResultSet
     * @param result
     * 		ResultSet-ul ce trebuie inchis
     */
    public static void close(ResultSet result) {
        if(result != null) {
            try {
                result.close();
            } catch (SQLException e) {
                LOGGER.log(Level.WARNING, "Error when trying to close the resultSet!");
            }
        }
    }

}
