package BeanDBAccessMain;

import DB.BeanDBAccessMySQL;
import java.beans.Beans;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author nakim
 */
public class Main
{
    public static void main(String[] args)
    {
        try
        {
            BeanDBAccessMySQL dbaMySQL;
            
            dbaMySQL = (BeanDBAccessMySQL)Beans.instantiate(
                null, "DB.BeanDBAccessMySQL");
            dbaMySQL.init();
            
            /* -------------------------------------------------------------- *
             *                         MySQL (MariaDB)                        *
             * -------------------------------------------------------------- */
            
            // Display the number of crypto providers
            System.out.println("Number of crypto providers : " +
                               dbaMySQL.count("crypto_provider"));
            
            // Afficher les informations de tous les providers
            ResultSet rs = dbaMySQL.selectAll("crypto_provider");
            for (int i = 0; rs.next(); ++i)
            {
                System.out.println("-----------------------------------------");
                System.out.println("Provider   : " + i);
                System.out.println("name       : " + rs.getString("name"));
                System.out.println("class_name : "  + rs.getString("class_name"));
            }
        }
        catch(IOException | ClassNotFoundException | SQLException ex) 
        {
            System.err.println(ex);
        }
    }
}
