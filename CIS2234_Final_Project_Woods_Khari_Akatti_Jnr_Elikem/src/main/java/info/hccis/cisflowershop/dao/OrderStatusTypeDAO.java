package info.hccis.cisflowershop.dao;

import info.hccis.cisflowershop.entity.jpa.OrderStatusType;
import info.hccis.cisflowershop.util.DatabaseUtility;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OrderStatusTypeDAO {
   public ArrayList<OrderStatusType> select() {
      ArrayList<OrderStatusType> OrderStatusTypes = new ArrayList();
      Connection conn = null;

      try {
         conn = DatabaseUtility.getConnection("");
         Statement statement = conn.createStatement();
         ResultSet rs = statement.executeQuery("select * from OrderStatusType");

         while(rs.next()) {
            int id = rs.getInt("id");
            String description = rs.getString("description");
            OrderStatusType OrderStatusType = new OrderStatusType();
            OrderStatusType.setId(id);
            OrderStatusType.setDescription(description);
            OrderStatusTypes.add(OrderStatusType);
         }
      } catch (SQLException var16) {
         Logger.getLogger(OrderStatusTypeDAO.class.getName()).log(Level.SEVERE, (String)null, var16);
      } finally {
         try {
            conn.close();
         } catch (SQLException var15) {
            System.out.println("Error closing the connection.");
         }

      }

      return OrderStatusTypes;
   }
}