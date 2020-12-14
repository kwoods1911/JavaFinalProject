package info.hccis.cisflowershop.bo;

import info.hccis.cisflowershop.dao.CustomerTypeDAO;
import info.hccis.cisflowershop.entity.jpa.CustomerType;
import info.hccis.cisflowershop.repositories.CustomerRepository;
import info.hccis.cisflowershop.repositories.CustomerTypeRepository;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Customer business object
 *
 * @author Elikem Akatti Jnr
 * @since 20201016
 */
public class CustomerTypeBO {

    private final CustomerTypeRepository customerTypeRepository;

    public CustomerTypeBO(CustomerTypeRepository ctr) {
        this.customerTypeRepository = ctr;
    }

    private static HashMap<Integer, String> customerTypesMap = new HashMap();

    public static HashMap<Integer, String> getCustomerTypesMap() {
        return customerTypesMap;
    }

    public static void setCustomerTypesMap(HashMap<Integer, String> customerTypesMap) {
       CustomerTypeBO.customerTypesMap = customerTypesMap;
    }

    /**
     * Connect to the data access object to get the customers from the
     * datasource.
     *
     * @since 20200923
     * @author Elikem Akatti Jnr
     */
    public ArrayList<CustomerType> load() {
        ArrayList<CustomerType> customerTypes = (ArrayList<CustomerType>) customerTypeRepository.findAll();
        
       // CustomerTypeDAO customerTypeDAO = new CustomerTypeDAO();
        //ArrayList<CustomerType> customerTypes = customerTypeDAO.select();
        return customerTypes;
    }
}
