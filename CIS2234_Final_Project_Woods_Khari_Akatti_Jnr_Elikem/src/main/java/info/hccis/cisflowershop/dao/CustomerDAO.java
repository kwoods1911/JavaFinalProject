package info.hccis.cisflowershop.dao;

import info.hccis.cisflowershop.entity.jpa.Customer;
import info.hccis.cisflowershop.entity.jpa.CustomerType;
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
 * Customer DAO class
 *
 * @since 20201118
 * @author Ekikem Akatti Jnr
 */
public class CustomerDAO {

    public static ArrayList<Customer> select() {
        ArrayList<Customer> customers = new ArrayList();

        CustomerTypeDAO customerTypeDAO = new CustomerTypeDAO();
        ArrayList<CustomerType> customerTypes = customerTypeDAO.select();
        Connection conn = null;

        try {
            conn = DatabaseUtility.getConnection("");
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("select * from Customer");

            while (rs.next()) {
                int id = rs.getInt("id");
                int customerTypeId = rs.getInt("customerTypeId");
                String fullName = rs.getString("fullName");
                String address1 = rs.getString("address1");
                String city = rs.getString("city");
                String province = rs.getString("province");
                String postalCode = rs.getString("postalCode");
                String phoneNumber = rs.getString("phoneNumber");
                String birthDate = rs.getString("birthDate");
                String loyaltyCard = rs.getString("loyaltyCard");

                Customer customer = new Customer();
                customer.setId(id);
                customer.setCustomerTypeId(customerTypeId);
                customer.setFullName(fullName);
                customer.setAddress1(address1);
                customer.setCity(city);
                customer.setProvince(province);
                customer.setPostalCode(postalCode);
                customer.setPhoneNumber(phoneNumber);
                customer.setBirthDate(birthDate);
                customer.setLoyaltyCard(loyaltyCard);

                for (CustomerType current : customerTypes) {
                    if (current.getId().equals(customerTypeId)) {
                        customer.setCustomerTypeId(current.getId());
                    }

                }
                customers.add(customer);
            }
        } catch (SQLException var25) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, (String) null, var25);
        } finally {
            try {
                conn.close();
            } catch (SQLException var24) {
                System.out.println("Error closing the connection.");
            }

        }

        return customers;
    }

    /**
     * Insert customer object into the database
     *
     * @since 20201118
     * @author Ekikem Akatti Jnr
     */
    public Customer insert(Customer customer) {
        Connection conn = null;

        try {
            conn = DatabaseUtility.getConnection("");
            String theStatement = "INSERT INTO Customer(customerId,fullName,address1, city, province, postalCode, phoneNumber, birthDate, loyaltyCard) VALUES (?,?,?,?,?,?,?,?,?)";
            PreparedStatement stmt = conn.prepareStatement(theStatement);
            stmt.setInt(1, customer.getCustomerTypeId());
            stmt.setString(2, customer.getFullName());
            stmt.setString(3, customer.getAddress1());
            stmt.setString(4, customer.getCity());
            stmt.setString(5, customer.getProvince());
            stmt.setString(6, customer.getPostalCode());
            stmt.setString(7, customer.getPhoneNumber());
            stmt.setString(8, customer.getBirthDate());
            stmt.setString(9, customer.getLoyaltyCard());
            stmt.executeUpdate();
        } catch (SQLException var5) {
            System.out.println("sql exception caught");
            var5.printStackTrace();
        }

        return customer;
    }

    /**
     * Updating customer info
     *
     * @since 20201118
     * @author Ekikem Akatti Jnr
     */
    public Customer update(Customer customer) {
        Connection conn = null;

        try {
            conn = DatabaseUtility.getConnection("");
            String theStatement = "UPDATE Customer set customerTypeId=?,fullName=?,address1=?, city=?, province=?, postalCode=?, phoneNumber=?,birthDate=?,loyaltyCard=?, WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(theStatement);
            stmt.setInt(1, customer.getCustomerTypeId());
            stmt.setString(2, customer.getFullName());
            stmt.setString(3, customer.getAddress1());
            stmt.setString(4, customer.getCity());
            stmt.setString(5, customer.getProvince());
            stmt.setString(6, customer.getPostalCode());
            stmt.setString(7, customer.getPhoneNumber());
            stmt.setString(8, customer.getBirthDate());
            stmt.setString(9, customer.getLoyaltyCard());
            stmt.setInt(10, customer.getId());
            stmt.executeUpdate();
        } catch (SQLException var5) {
            System.out.println("sql exception caught");
            var5.printStackTrace();
        }

        return customer;
    }

    /**
     * Deleting customer
     *
     * @since 20201118
     * @author Ekikem Akatti Jnr
     */
    public boolean delete(int id) {
        Connection conn = null;

        try {
            conn = DatabaseUtility.getConnection("");
            String theStatement = "DELETE FROM Customer WHERE id = ?";
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
