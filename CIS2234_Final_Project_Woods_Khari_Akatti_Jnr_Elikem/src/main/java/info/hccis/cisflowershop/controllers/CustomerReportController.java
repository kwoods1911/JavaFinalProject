package info.hccis.cisflowershop.controllers;

import info.hccis.cisflowershop.bo.CustomerTypeBO;
import info.hccis.cisflowershop.bo.ItemTypeBO;
import info.hccis.cisflowershop.entity.jpa.Customer;
import info.hccis.cisflowershop.entity.jpa.FlowerOrder;
import info.hccis.cisflowershop.repositories.CustomerRepository;
import info.hccis.cisflowershop.repositories.CustomerTypeRepository;
import info.hccis.cisflowershop.repositories.FlowerOrderRepository;
import java.util.ArrayList;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import java.math.BigDecimal;

/**
 * Customer report controller class
 *
 * @since 20201118
 * @author Ekikem Akatti Jnr
 */
@Controller
@RequestMapping("/report")
public class CustomerReportController {

    private final CustomerRepository customerRepository;
    private final FlowerOrderRepository flowerOrderRepository;

    public CustomerReportController(CustomerRepository cr, FlowerOrderRepository flor) {
        this.customerRepository = cr;
        this.flowerOrderRepository = flor;
    }

    /**
     * Customer report method
     *
     * @since 20201118
     * @author Ekikem Akatti Jnr
     */
    @RequestMapping("/customerReport")
    public String getCustomers(Model model) {
        Customer customer = new Customer();

        model.addAttribute("customer", customer);
        model.addAttribute("customers", customerRepository.findAll());

        return "report/customerReport";
    }

    /**
     * Submit customer report class which also calculates all the
     *
     * @since 20201118
     * @author Ekikem Akatti Jnr
     */
    @RequestMapping("/submitCustomerReport")
    public String addSubmit(Model model, HttpServletRequest request) {

        String idString = (String) request.getParameter("id");
        int id = Integer.parseInt(idString);

        System.out.println("EAKJTEST - id= " + id);

        //https://stackoverflow.com/questions/3413448/double-vs-bigdecimal
        BigDecimal totalCost = new BigDecimal(0);
        ArrayList<FlowerOrder> flowerOrders = loadFlowerOrdersByCusId(id);
        for (FlowerOrder flowerOrder : flowerOrders) {
            totalCost = totalCost.add(flowerOrder.getTotalCost());
        }

        model.addAttribute("customers", customerRepository.findAll());
        model.addAttribute("totalCost", totalCost);
        model.addAttribute("flowerOrders", flowerOrders);

        return "customer/customerOrders";
    }

    /**
     * Customers list
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
     * Flower Order list by customer Id
     *
     * @since 20201203
     * @author Ekikem Akatti Jnr
     */
    public ArrayList<FlowerOrder> loadFlowerOrdersByCusId(int id) {
        ArrayList<FlowerOrder> flowerOrders = (ArrayList<FlowerOrder>) flowerOrderRepository.findAllByCustomerId(id);

        HashMap<Integer, String> orderStatusTypesMap = ItemTypeBO.getItemsTypesMap();
        for (FlowerOrder current : flowerOrders) {
            current.setItemStatusTypeDescription(orderStatusTypesMap.get(current.getOrderStatus()));
        }
        return flowerOrders;
    }
}
