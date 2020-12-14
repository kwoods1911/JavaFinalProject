package info.hccis.cisflowershop.controllers;

import info.hccis.cisflowershop.bo.CustomerTypeBO;
import info.hccis.cisflowershop.entity.jpa.Customer;
import info.hccis.cisflowershop.entity.jpa.CustomerType;
import info.hccis.cisflowershop.repositories.CustomerRepository;
import info.hccis.cisflowershop.repositories.CustomerTypeRepository;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;
import java.util.ResourceBundle;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping({"/customer"})
public class CustomerController {

    private final CustomerRepository customerRepository;
    private final CustomerTypeRepository customerTypeRepository;

    public CustomerController(CustomerRepository cr, CustomerTypeRepository ctr) {
        this.customerRepository = cr;
        this.customerTypeRepository = ctr;
    }

    /**
     * Page to allow user to view customers
     *
     * @since 20201118
     * @author Elikem Akatti Jnr
     */
    @RequestMapping({"/list"})
    public String list(Model model) {
        model.addAttribute("customers", loadCustomers());
        model.addAttribute("findNameMessage", "Customers loaded: " + loadCustomers().size());

        return "customer/list";
    }

    /**
     * Page to allow user to add a customer
     *
     * @since 20201002
     * @author BJM (modified from Fred/Amro's project) : modified by EKAJ since
     * 20201118 to add a customer
     */
    @RequestMapping({"/add"})
    public String add(Model model) {
        model.addAttribute("message", "Add a customer");
        Customer customer = new Customer();
        model.addAttribute("customer", this.customerRepository.findAll());
        model.addAttribute("customer", customer);

        model.addAttribute("customerType", this.customerTypeRepository.findAll());

        return "customer/add";

    }

    /**
     * Page to allow user to edit a customer
     *
     * @since 20201002
     * @author BJM (modified from Fred/Amro's project : modified by EKAJ since
     * 20201118 to edit a customer
     */
    @RequestMapping({"/edit"})
    public String edit(Model model, HttpServletRequest request) {

        String idString = request.getParameter("id");

        int id = Integer.parseInt(idString);
        System.out.println("BJTEST - id=" + id);

        Optional<Customer> found = customerRepository.findById(id);
        model.addAttribute("customerType", this.customerTypeRepository.findAll());
        model.addAttribute("customer", found);
        return "customer/add";
    }

    /**
     * Page to allow user to delete a customer
     *
     * @since 20201009
     * @author BJM : modified by EKAJ since 20201118 to delete a customer
     */
    @RequestMapping({"/delete"})
    public String delete(Model model, HttpServletRequest request) {

        String idString = request.getParameter("id");
        int id = Integer.parseInt(idString);
        System.out.println("BJTEST - id=" + id);

        String propFileName = "messages";
        ResourceBundle rb = ResourceBundle.getBundle(propFileName);
        String successString;
        try {
            this.customerRepository.deleteById(id);
            successString = rb.getString("message.customer.deleted") + " (" + id + ")";
        } catch (EmptyResultDataAccessException var9) {
            successString = rb.getString("message.customer.deleted.error") + " (" + id + ")";
        }

        model.addAttribute("message", successString);
        model.addAttribute("customers", loadCustomers());
        return "customer/list";
    }

    /**
     * Page to allow user to submit the add a parking pass. It will put the
     * parking pass in the database.
     *
     * @since 20201002
     * @author BJM (modified from Fred/Amro's project : modified by EKAJ since
     * 20201118 to submit the add a customer.
     */
    @RequestMapping({"/addSubmit"})
    public String addSubmit(Model model, @Valid @ModelAttribute("customer") Customer customer, BindingResult result) {
        if (result.hasErrors()) {
            System.out.println("Validation error");
            model.addAttribute("customerType", this.customerTypeRepository.findAll());
            return "customer/add";
        }

        //Saving the customer with the SprintdDtaJPA
        customerRepository.save(customer);

        String propFileName = "messages";
        ResourceBundle rb = ResourceBundle.getBundle(propFileName);
        String successAddString = rb.getString("message.customer.saved");
        model.addAttribute("message", successAddString);
        model.addAttribute("customers", loadCustomers());
        return "customer/list";

    }

    /**
     * Customer List
     *
     * @since 20201118
     * @author Ekikem Akatti Jnr
     */
    public ArrayList<Customer> loadCustomers() {
        ArrayList<Customer> customers = (ArrayList<Customer>) customerRepository.findAll();
        HashMap<Integer, String> customerTypesMap = CustomerTypeBO.getCustomerTypesMap();

        for (Customer current : customers) {
            current.setCustomerTypeDescription(customerTypesMap.get(current.getCustomerTypeId()));
        }

        return customers;

    }

    /**
     * Customer type List
     *
     * @since 20201118
     * @author Ekikem Akatti Jnr
     */
    public ArrayList<CustomerType> loadCustomerType() {
        ArrayList<CustomerType> customerTypes = (ArrayList<CustomerType>) customerTypeRepository.findAll();
        return customerTypes;
    }
}
