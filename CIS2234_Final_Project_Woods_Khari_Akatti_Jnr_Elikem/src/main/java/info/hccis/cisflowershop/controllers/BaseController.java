package info.hccis.cisflowershop.controllers;

import info.hccis.cisflowershop.bo.CustomerTypeBO;
import info.hccis.cisflowershop.entity.jpa.CustomerType;
import info.hccis.cisflowershop.entity.jpa.OrderStatusType;
import info.hccis.cisflowershop.repositories.CustomerTypeRepository;
import info.hccis.cisflowershop.repositories.ItemTypeRepository;
import info.hccis.cisflowershop.util.CisUtility;
import info.hccis.cisflowershop.util.UtilityRest;
import java.util.ArrayList;
import java.util.HashMap;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BaseController {

    private final CustomerTypeRepository customerTypeRepository;

    public BaseController(CustomerTypeRepository ctr) {
        customerTypeRepository = ctr;
    }

    @RequestMapping("/")
    public String home(HttpSession session) {

        //EAKJ added api on 20201125
        String urlString = "http://localhost:8081/api/CustomerService/customers/123";
        try {
            UtilityRest.getJsonFromRest(urlString);
        } catch (Exception e) {
            System.out.println("There was an error accessing that rest service.");
        }

        //BJM 20200602 Issue#1 Set the current date in the session
        String currentDate = CisUtility.getCurrentDate("yyyy-MM-dd");
        session.setAttribute("currentDate", currentDate);

        //EAKJ added list of customer types on 20201208
        ArrayList<CustomerType> customerTypes = (ArrayList<CustomerType>) customerTypeRepository.findAll();
        session.setAttribute("customerTypes", customerTypes);
        System.out.println("EAKJ loaded " + customerTypes.size() + " customer types");

        //EAKJ added a map of customer types on 20201208
        HashMap<Integer, String> customerTypesMap = CustomerTypeBO.getCustomerTypesMap();
        customerTypesMap.clear();
        for (CustomerType current : customerTypes) {
            customerTypesMap.put(current.getId(), current.getDescription());
        }
        return "index";
    }

    @RequestMapping("/about")
    public String about() {
        return "other/about";
    }
}
