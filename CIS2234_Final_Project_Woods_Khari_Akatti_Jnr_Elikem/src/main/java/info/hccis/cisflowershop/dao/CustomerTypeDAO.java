package info.hccis.cisflowershop.dao;

import info.hccis.cisflowershop.entity.jpa.CustomerType;
import info.hccis.cisflowershop.util.DatabaseUtility;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Customer type DAO
 *
 * @since 20201118
 * @author Ekikem Akatti Jnr
 */
public class CustomerTypeDAO {

    /**
     * Select the records from the database
     *
     * @since 20201016
     * @author BJM
     */
    public ArrayList<CustomerType> select() {
        ArrayList<CustomerType> customerTypes = new ArrayList();

        Connection conn = null;
        try {

            //Select the campers from the database
            conn = DatabaseUtility.getConnection("");
            Statement statement = conn.createStatement();

            //***************************************************
            // Select using statement
            //***************************************************
            //Next select all the rows and display them here...
            ResultSet rs = statement.executeQuery("select * from CustomerType");

            //Show all the campers
            while (rs.next()) {
                int id = rs.getInt("id");
                String description = rs.getString("description");

                CustomerType customerType = new CustomerType();
                customerType.setId(id);
                customerType.setDescription(description);
                customerTypes.add(customerType);
            }

        } catch (SQLException ex) {
            Logger.getLogger(CustomerTypeDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                //Could not close.  
                System.out.println("Error closing the connection.");
            }
        }
        return customerTypes;

    }

}
