package info.hccis.cisflowershop.dao;

import info.hccis.cisflowershop.entity.jpa.ItemType;
import info.hccis.cisflowershop.util.DatabaseUtility;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author akatt
 */
public class ItemTypeDAO {

    /**
     * Select the records from the database
     *
     * @since 20201016
     * @author BJM
     */
    public ArrayList<ItemType> select() {
        ArrayList<ItemType> itemTypes = new ArrayList();

        Connection conn = null;
        try {

            //Select the campers from the database
            conn = DatabaseUtility.getConnection("");
            Statement statement = conn.createStatement();

            //***************************************************
            // Select using statement
            //***************************************************
            //Next select all the rows and display them here...
            ResultSet rs = statement.executeQuery("select * from ItemType");

            //Show all the campers
            while (rs.next()) {
                int id = rs.getInt("id");
                String description = rs.getString("description");
                Double cost = rs.getDouble("cost");

                ItemType itemType = new ItemType();
                itemType.setId(id);
                itemType.setDescription(description);
                itemType.setCost(cost);
                itemTypes.add(itemType);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ItemTypeDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                //Could not close.  
                System.out.println("Error closing the connection.");
            }
        }
        return itemTypes;

    }
}
