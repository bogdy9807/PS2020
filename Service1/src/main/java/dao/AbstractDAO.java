package dao;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import dataAccess.Connections;
import model.*;
/**
 * Clasa pentru layerut de data access, in care se foloseste reflexia pentru a obtine sau a transmite date.
 * @author Bogdan
 *
 * @param <T>
 * Clasa generica ce reprezinta un tabel din baza de date.
 */
public class AbstractDAO<T> {
    protected static final Logger LOGGER = Logger.getLogger(AbstractDAO.class.getName());
    private final Class<T> type;

    @SuppressWarnings("unchecked")
    public AbstractDAO() {
        this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    /**
     * Metoda pentru crearea unei clauze de SELECT in functie de un camp dat ca parametru
     * @param s
     * 		Parametrul ce reprezinta coloana pentru conditie
     * @return
     * 		Clauza de SELECT data ca string.
     */

    public String createSelectQuery(String s) {
        return "SELECT * FROM " + type.getSimpleName().toLowerCase() + " WHERE " + s + " = ?";
    }

    /**
     * Metoda pentru crearea obiectelor in sine dupa un parametru de forma ResultSet in care se afla rezultatele din tabel.
     * Aceasta este o metoda foarte importanta si foloseste reflexia.
     * @param set
     * 		parametrul de tip ResultSet dupa care se construiesc obiectele
     * @return
     * 		O lista de obiecte de tip ArrayList daca exista rezultate,altfel null.
     */
    public ArrayList<T> creeazaObiecte(ResultSet set){
        ArrayList<T> resultList = new ArrayList<T>();
        try {
            while(set.next()) {
                T instance = type.newInstance();
                for(Field field : type.getDeclaredFields()) {
                    Object value = set.getObject(field.getName());
                    PropertyDescriptor desc = new PropertyDescriptor(field.getName(),type);
                    Method method = desc.getWriteMethod();
                    method.invoke(instance,value);
                }
                resultList.add(instance);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING,"ERROR WHILE CREATING OBJECT: " + e.getMessage());
        } catch (InstantiationException e) {
            LOGGER.log(Level.WARNING,"Cannot make new instance of " + type.getClass().getName() + "\n" + e.getMessage());
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            LOGGER.log(Level.WARNING,"Error while trying to acces instance of type" + type.getClass().getName() + "\n" + e.getMessage());
            e.printStackTrace();
        } catch (IntrospectionException e) {
            LOGGER.log(Level.WARNING,e.getMessage());
        } catch (IllegalArgumentException e) {
            LOGGER.log(Level.WARNING,e.getMessage());
        } catch (InvocationTargetException e) {
            LOGGER.log(Level.WARNING,e.getMessage());
        }

        return resultList;
    }

    /**
     * Metoda ce cauda in baza de date dupa un ID si foloseste metoda CreeazaObiecte pentru a crea obiectul in sine.
     * @param id
     * 		ID-ul dupa care se cauta
     * @return
     * 		Obiectul in caz ca exista altfel null
     */
    public T findById(int id) {
        Connection conn = Connections.getConnection();
        PreparedStatement statement = null;
        ResultSet dbResult = null;
        String query = createSelectQuery("id");
        //System.out.println(query);
        try {
            statement = conn.prepareStatement(query);
            statement.setInt(1, id);
            dbResult = statement.executeQuery();
            if(!dbResult.next()) {
                // not found
                //System.out.println("NULL in findById");
                return null;
            }
            dbResult.previous();
            return creeazaObiecte(dbResult).get(0);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING,type.getClass().getName() + "DAO:findById " + e.getMessage());
        } finally {
            Connections.close(conn);
            Connections.close(statement);
            Connections.close(dbResult);
        }
        return null;
    }
    /**
     * Metoda pentru inserarea in baza de date a unui obiect generic. Se foloseste reflexia.
     * @param object
     * 		Obiectul care trebuie inserat
     */
    public void insert(T object){
        String sql = "INSERT INTO `new_schema`.`" + type.getSimpleName().toLowerCase() + "`(";
        String values = "VALUES (";
        for(Field f : type.getDeclaredFields()) {
            Object obj = new Object();
            sql+="`" + f.getName() + "`,";
            try{
                PropertyDescriptor desc = new PropertyDescriptor(f.getName(),type);
                Method method = desc.getReadMethod();
                obj = method.invoke(object);
                if(obj instanceof String) {
                    values += "\"" + obj + "\",";
                }
                else {
                    values += obj.toString() + ",";
                }
            } catch (Exception e) {
                LOGGER.log(Level.WARNING,"ERROR IN INSERT METHOD: " + e.getMessage());
            }
        }
        sql = sql.substring(0, sql.length()-1);
        sql += ") ";
        values = values.substring(0,values.length()-1);
        values += ");";
        Connection conn = Connections.getConnection();
        PreparedStatement statement = null;
        try {
            //System.out.println(sql + values);
            statement = conn.prepareStatement(sql+values);
            statement.execute();
            System.out.println(type.getSimpleName() + " was inserted!");
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING,"SQL ERROR IN INSERT METHOD: " + e.getMessage());
        } finally {
            Connections.close(conn);
            Connections.close(statement);
        }
    }

    /**
     * Metoda pentru afisarea continutului tabelei
     * @return
     * 		Lista de tip ArrayList ce contine toate obiectele tabelului.
     */
    public ArrayList<T> selectAll(){
        Connection conn = Connections.getConnection();
        PreparedStatement statement = null;
        ResultSet dbRes = null;
        String sql = "SELECT * FROM " + type.getSimpleName();
        try {
            statement = conn.prepareStatement(sql);
            dbRes = statement.executeQuery(sql);
            return creeazaObiecte(dbRes);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING,e.getMessage());
        } finally {
            Connections.close(conn);
            Connections.close(statement);
            Connections.close(dbRes);
        }
        return null;
    }

    /**
     * Metoda pentru actualizarea unui tabel
     * @param obj
     * 		Tabelul care trebuie actualizat + informatiile
     */

    public void update(T obj) {
        Connection conn = Connections.getConnection();
        PreparedStatement statement = null;
        String sql = createUpdateQuery();
        try {
            statement = conn.prepareStatement(sql);
            int i=1;
            for(Field field: type.getDeclaredFields()) {
                Object obj1 = new Object();
                PropertyDescriptor desc = new PropertyDescriptor(field.getName(),type);
                Method method = desc.getReadMethod();
                obj1 = method.invoke(obj);
                statement.setString(i, obj1.toString());
                i++;
            }
            statement.setInt(i, getFirstInteger(statement.toString()));
            System.out.println(statement.toString());
            statement.execute();
            System.out.println("Successfully updated");
            // Success printing here
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING,e.getMessage());
        } catch (Exception e){
            System.out.println(e.getMessage());
        } finally {
            Connections.close(conn);
            Connections.close(statement);
        }
    }

    public int getFirstInteger(String string) {
        int i = 0;
        while (i < string.length() && !Character.isDigit(string.charAt(i))) i++;
        int j = i;
        while (j < string.length() && Character.isDigit(string.charAt(j))) j++;
        if(i == string.length() || j == string.length()) return -1;
        return Integer.parseInt(string.substring(i, j));
    }
    /**
     * Metoda pentru stergerea unui element din tabel dupa ID
     * @param id
     * 		ID-ul dupa care se sterge
     */
    public void deleteById(int id) {
        String sql = "DELETE FROM " + type.getSimpleName().toLowerCase() + " WHERE id = " + id;
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
    /**
     * Metoda ce creeaza o clauza de UPDATE in functie de tabelul in care se lucreaza
     * @return
     * 		Clauza de UPDATE data ca String
     */
    public String createUpdateQuery() {
        String sql = "UPDATE " + type.getSimpleName().toLowerCase() + " SET ";
        for(Field field: type.getDeclaredFields()) {
            sql += "`" + field.getName() + "` = ? , ";
        }
        sql = sql.substring(0,sql.length()-2);
        sql += " WHERE `id` = ?";
        return sql;
    }

}
