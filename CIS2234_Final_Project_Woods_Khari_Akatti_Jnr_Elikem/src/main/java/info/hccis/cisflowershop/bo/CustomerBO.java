package info.hccis.cisflowershop.bo;

import info.hccis.cisflowershop.dao.CustomerDAO;
import info.hccis.cisflowershop.entity.jpa.Customer;
import info.hccis.cisflowershop.entity.jpa.CustomerType;
import info.hccis.cisflowershop.repositories.CustomerRepository;
import java.util.ArrayList;
import java.util.HashMap;

public class CustomerBO {
    
    public ArrayList<Customer> load() {
        
        //Read customers from the database
        CustomerDAO customerDAO = new CustomerDAO();
        ArrayList<Customer> customers = customerDAO.select();
        return customers;
    }

    public Customer save(Customer customer) {
        CustomerDAO customerDAO = new CustomerDAO();
        if (customer.getId() == null) {
            customer = customerDAO.insert(customer);
        } else {
            customerDAO.update(customer);
        }

        return customer;
    }

    public boolean delete(int id) {
        CustomerDAO customerDAO = new CustomerDAO();
        return customerDAO.delete(id);
    }
}
