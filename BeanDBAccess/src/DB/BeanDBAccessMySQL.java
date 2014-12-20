package DB;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

/**
 *
 * @author nakim
 */
public class BeanDBAccessMySQL  extends BeanDBAccess
{
    // <editor-fold defaultstate="collapsed" desc="Constructor">
    public BeanDBAccessMySQL()
    {
        // Config file in user directory
        String dbAccessFileName = System.getProperty("user.home") +
                System.getProperty("file.separator") + "db_access.properties";
        
        try
        {
            this.prop = this.loadPropertiesFile(dbAccessFileName);
        }
        catch (IOException ex)
        {
            System.err.println("Properties file not loading : " + ex);
        }
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Public methods">
    @Override
    public boolean init()
    {
        String url = "jdbc:mysql://" + getHost() + ":" + getPort() + "/" + getDBName();
        
        // Initialize the driver
        if (!initDriver(getDriver()))
            return false;
        
        return connect(url, getUsername(), getPassword());
    }
    
    public boolean connect(String url, String user, String password)
    {
        try
        {
            this.connection = DriverManager.getConnection(url, user, password);
            return true;
        }
        catch (SQLException ex)
        {
            System.err.println(ex);
        }
        
        return false;
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Private methods">
    private Properties loadPropertiesFile(String filename) throws IOException,
                                                                  FileNotFoundException
    {
        Properties properties = new Properties();
        FileInputStream input = new FileInputStream(filename);
        
        try
        {
            properties.load(input);
            return properties;
        }
        finally
        {
            input.close();
        }
   }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Getters & Setters">
    public String getDriver()
    {
        return this.prop.getProperty("DRIVER", this.DRIVER);
    }
    
    public String getUsername()
    {
        return this.prop.getProperty("USERNAME", this.USERNAME);
    }
    
    public String getPassword()
    {
        return this.prop.getProperty("PASSWORD", this.PASSWORD);
    }
    
    public String getHost()
    {
        return this.prop.getProperty("HOST", this.HOST);
    }
    
    public String getPort()
    {
        return this.prop.getProperty("PORT", this.PORT);
    }
    
    public String getDBName()
    {
        return this.prop.getProperty("DBNAME", this.DBNAME);
    }
    
    public String getProperty(String property)
    {
        return this.prop.getProperty(property);
    }
    // </editor-fold>
            
    // <editor-fold defaultstate="collapsed" desc="Variables declaration">
    private final String DRIVER   = "com.mysql.jdbc.Driver";
    private final String USERNAME = "root";
    private final String PASSWORD = "m910719X";
    private final String HOST     = "localhost";
    private final String PORT     = "3306";
    private final String DBNAME   = "mysql";
    
    private Properties prop;
    // </editor-fold>
}
