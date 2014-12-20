package DB;

import java.io.Serializable;
import java.sql.*;

/**
 *
 * @author nakim
 */
public abstract class BeanDBAccess implements Serializable, DBAccess
{
    // <editor-fold defaultstate="collapsed" desc="Constructor">
    public BeanDBAccess()
    {
        this.connection = null;
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Public methods">
    protected boolean initDriver(String driver)
    {
        try
        {
            // Chargement du driver
            Class.forName(driver);
        }
        catch (ClassNotFoundException ex)
        {
            System.err.println(ex);
            return false;
        }
        
        return true;
    }
    
    public void disconnect()
    {
        try
        {
            this.connection.close();
        }
        catch (SQLException ex)
        {
            System.err.println(ex);
        }
    }
    
    public ResultSet executeQuery(String query)
    {
        try
        {
            // Création d'une instance d'instruction pour cette connexion
            Statement instruc  = this.connection.createStatement();
            return instruc.executeQuery(query);
        }
        catch (SQLException ex)
        {
            System.err.println(ex);
            return null;
        }
    }
    
    public boolean executeUpdate(String query)
    {
        try
        {
            // Création d'une instance d'instruction pour cette connexion
            Statement instruc  = this.connection.createStatement();
            return instruc.executeUpdate(query) == 1;
        }
        catch (SQLException ex)
        {
            System.err.println(ex);
            return false;
        }
    }
    
    public int count(String table, String condition)
    {
        try
        {
            String query = "SELECT COUNT(*) AS countEntry FROM " + table;
            
            if (!condition.isEmpty())
                query += " WHERE " + condition;

            ResultSet rs = this.executeQuery(query);
            if (rs != null)
            {
                rs.next();
                return rs.getInt("countEntry"); 
            }
        }
        catch (SQLException ex) 
        {
            System.err.println(ex);
        }
        
        return -1;
    }
    
    public int count(String table)
    {
        return this.count(table, "");
    }
    
    public ResultSet selectAll(String table, String condition)
    {
        String query = "SELECT * FROM " + table;
        
        if (!condition.isEmpty())
            query += " WHERE " + condition;
        
        return this.executeQuery(query);
    }
    
    public ResultSet selectAll(String table)
    {
        return this.selectAll(table, "");
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Variables declaration">
    protected Connection connection;
    // </editor-fold>
}
