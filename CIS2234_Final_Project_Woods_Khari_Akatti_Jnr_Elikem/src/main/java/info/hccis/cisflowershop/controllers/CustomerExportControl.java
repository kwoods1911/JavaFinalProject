package info.hccis.cisflowershop.controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import info.hccis.cisflowershop.entity.jpa.Customer;
import info.hccis.cisflowershop.repositories.CustomerRepository;
import info.hccis.cisflowershop.repositories.CustomerTypeRepository;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Customer export controller class
 *
 * @since 20201118
 * @author Ekikem Akatti Jnr
 */
@Controller
@RequestMapping("/export")
public class CustomerExportControl {

    private final CustomerRepository customerRepository;

    public CustomerExportControl(CustomerRepository cr) {
        this.customerRepository = cr;
    }

    /**
     * Loading customers
     *
     * @since 20201118
     * @author Ekikem Akatti Jnr
     */
    @RequestMapping("customerExport")
    public String loadCustomers(Model model, @ModelAttribute("customer") Customer customer) throws IOException {
        ArrayList<Customer> customers = (ArrayList<Customer>) customerRepository.findAll();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");

        //Date date = new Date();
        //String date1 = (simpleDateFormat.format(date)).toString();
        //String todayDate = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
        //https://www.javatpoint.com/java-get-current-date
        Path PATH = Paths.get("c:\\flowers\\" + "customers" + simpleDateFormat.format(new Date()) + ".json");

        if (!Files.exists(PATH)) {
            Files.createDirectories(PATH.getParent());
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(String.valueOf(PATH)))) {
            // https://howtodoinjava.com/gson/gson-gsonbuilder-configuration/
            // http://zetcode.com/java/gson/
            new GsonBuilder().setPrettyPrinting().create().toJson(customers, bw);
            System.out.println("Customers saved");

            String propFileName = "messages";
            ResourceBundle rb = ResourceBundle.getBundle(propFileName);
            String successAddString = rb.getString("message.customer.export");
            model.addAttribute("message", successAddString);

        } catch (Exception e) {
            System.out.println("Error occured while saving");
            System.out.println(e.getMessage());
        }

        model.addAttribute("customers", customers);

        return "customer/list";
    }

}
