package info.hccis.cisflowershop.dao;

import info.hccis.cisflowershop.entity.jpa.Customer;
import info.hccis.cisflowershop.entity.jpa.CustomerType;
import info.hccis.cisflowershop.entity.jpa.FlowerOrder;
import info.hccis.cisflowershop.entity.jpa.ItemType;
import info.hccis.cisflowershop.util.DatabaseUtility;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Camper database access object
 *
 * @author bjm
 * @since 20200921
 */
public class FlowerOrderDAO {

    /**
     * Camper database access object
     *
     * @author bjm
     * @since 20200921
     */
    public ArrayList<FlowerOrder> select() {

        ArrayList<FlowerOrder> flowerOrders = new ArrayList();
        
        ArrayList<ItemType> itemTypes = new ArrayList();
        ItemTypeDAO itemTypeDAO = new ItemTypeDAO();
        itemTypes = itemTypeDAO.select();
        
        ArrayList<CustomerType> customerTypes = new ArrayList();
        CustomerTypeDAO customerTypeDAO = new CustomerTypeDAO();
        customerTypes = customerTypeDAO.select();
        
        ArrayList<Customer> customers = new ArrayList();
        CustomerDAO customerDAO = new CustomerDAO();
        customers = customerDAO.select();

        //FlowerOrderDAO customerTypeDAO = new CustomerTypeDAO();
        Connection conn = null;

        try {
            conn = DatabaseUtility.getConnection("");
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("select * from FlowerOrder");

            while (rs.next()) {
                int id = rs.getInt("id");
                int customerId = rs.getInt("customerId");
                String orderDate = rs.getString("orderDate");
                int item1 = rs.getInt("item1");
                int item2 = rs.getInt("item2");
                int item3 = rs.getInt("item3");
                int item4 = rs.getInt("item4");
                int item5 = rs.getInt("item5");
                int item6 = rs.getInt("item6");
                double cost = (double) rs.getInt("totalCost");
                BigDecimal totalCost = BigDecimal.valueOf(cost);
                double paid = (double) rs.getInt("amountPaid");
                BigDecimal amountPaid = BigDecimal.valueOf(paid);
                FlowerOrder flowerOrder = new FlowerOrder(id);
                flowerOrder.setId(id);
                flowerOrder.setCustomerId(customerId);
                flowerOrder.setOrderDate(orderDate);
                flowerOrder.setItem1(item1);
                flowerOrder.setItem2(item2);
                flowerOrder.setItem3(item3);
                flowerOrder.setItem4(item4);
                flowerOrder.setItem5(item5);
                flowerOrder.setItem6(item6);
                flowerOrder.setTotalCost(totalCost);
                flowerOrder.setAmountPaid(amountPaid);
                
                
                                
                flowerOrders.add(flowerOrder);
            }
        } catch (SQLException var31) {
            Logger.getLogger(FlowerOrderDAO.class.getName()).log(Level.SEVERE, (String) null, var31);
        } finally {
            try {
                conn.close();
            } catch (SQLException var30) {
                System.out.println("Error closing the connection.");
            }

        }

        return flowerOrders;
    }

    public FlowerOrder insert(FlowerOrder flowerOrder) {
        Connection conn = null;

        try {
            conn = DatabaseUtility.getConnection("");
            String theStatement = "INSERT INTO FlowerOrder (customerId,orderDate,item1, item2, item3, item4, item5, item6, orderStatus, totalCost,amountPaid)VALUES (?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement stmt = conn.prepareStatement(theStatement);
            stmt.setInt(1, flowerOrder.getCustomerId());
            stmt.setString(2, flowerOrder.getOrderDate());
            stmt.setInt(3, flowerOrder.getItem1());
            stmt.setInt(4, flowerOrder.getItem2());
            stmt.setInt(5, flowerOrder.getItem3());
            stmt.setInt(6, flowerOrder.getItem4());
            stmt.setInt(7, flowerOrder.getItem5());
            stmt.setInt(8, flowerOrder.getItem6());
            stmt.setInt(9, flowerOrder.getOrderStatus());
            stmt.setInt(10, flowerOrder.getTotalCost().intValue());
            stmt.setInt(11, flowerOrder.getAmountPaid().intValue());
            stmt.executeUpdate();
        } catch (SQLException var5) {
            System.out.println("sql exception caught");
            var5.printStackTrace();
        }

        return flowerOrder;
    }

    public FlowerOrder update(FlowerOrder flowerOrder) {
        Connection conn = null;

        try {
            conn = DatabaseUtility.getConnection("");
            String theStatement = "UPDATE FlowerOrder set customerId=?,orderDate=?,item1=?, item2=?, item3=?,item4=?, item5=?, item6=?,orderStatus=?, totalCost=?, ,amountPaid=?WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(theStatement);
            stmt.setInt(1, flowerOrder.getCustomerId());
            stmt.setString(2, flowerOrder.getOrderDate());
            stmt.setInt(3, flowerOrder.getItem1());
            stmt.setInt(4, flowerOrder.getItem2());
            stmt.setInt(5, flowerOrder.getItem3());
            stmt.setInt(6, flowerOrder.getItem4());
            stmt.setInt(7, flowerOrder.getItem5());
            stmt.setInt(8, flowerOrder.getItem6());
            stmt.setInt(9, flowerOrder.getOrderStatus());
            stmt.setInt(10, flowerOrder.getTotalCost().intValue());
            stmt.setInt(11, flowerOrder.getAmountPaid().intValue());
            stmt.setInt(12, flowerOrder.getId());
            stmt.executeUpdate();
        } catch (SQLException var5) {
            System.out.println("sql exception caught");
            var5.printStackTrace();
        }

        return flowerOrder;
    }

    public boolean delete(int id) {
        Connection conn = null;

        try {
            conn = DatabaseUtility.getConnection("");
            String theStatement = "DELETE FROM FlowerOrder WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(theStatement);
            stmt.setInt(1, id);
            stmt.executeUpdate();
            return true;
        } catch (SQLException var5) {
            System.out.println("sql exception caught");
            var5.printStackTrace();
            return false;
        }
    }
}
