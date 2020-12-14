package info.hccis.cisflowershop.controllers;

import info.hccis.cisflowershop.entity.jpa.FlowerOrder;
import info.hccis.cisflowershop.entity.jpa.Customer;
import info.hccis.cisflowershop.entity.jpa.OrderStatusType;
import info.hccis.cisflowershop.entity.jpa.ItemType;
import info.hccis.cisflowershop.repositories.CustomerRepository;
import info.hccis.cisflowershop.repositories.FlowerOrderRepository;
import info.hccis.cisflowershop.repositories.OrderStatusTypeRepository;
import info.hccis.cisflowershop.repositories.ItemTypeRepository;
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
@RequestMapping({"/flowerOrder"})
public class FlowerOrderController {
    
    //KW - creating repositories
   private final FlowerOrderRepository flowerOrderRepository;
   private final CustomerRepository customerRepository;
   private final OrderStatusTypeRepository orderStatusTypeRepository;
   private final ItemTypeRepository itemTypeRepository;
   
   //KW creating array list to hold Item Quantity Selections.
   private ArrayList<Integer> itemQuantitySelections = new ArrayList<Integer>();

   
    //KW initializing repositories.
   public FlowerOrderController(FlowerOrderRepository flor,CustomerRepository cus,
      OrderStatusTypeRepository orderStatType, ItemTypeRepository itemTypeRep) {
      this.flowerOrderRepository = flor;
      this.customerRepository = cus;
      this.orderStatusTypeRepository = orderStatType;
      this.itemTypeRepository = itemTypeRep;
   }
   
   
   
   //KW - list page
   @RequestMapping({"/list"})
   public String list(Model model) {
      model.addAttribute("flowerOrders", this.loadFlowerOrders());//Kw - loading orders so that they can be displayed on the page.
      model.addAttribute("findNameMessage", "Flower Orders loaded");
      return "flowerOrder/list";
   }
   
   
   @RequestMapping({"/orderSummary"})
   public String orderSummary(Model model) {
       //KW - loading flower orders into the order summary page.
      model.addAttribute("flowerOrders", this.loadFlowerOrders());
      model.addAttribute("findNameMessage", "Flower Orders loaded");
      return "flowerOrder/orderSummary";
   }

   @RequestMapping({"/add"})
   public String add(Model model) {
      model.addAttribute("message", "Add a flower order");
      FlowerOrder flowerOrder = new FlowerOrder();//Kw creating flower object
      model.addAttribute("flowerOrder", flowerOrder);//KW
      
      
      model.addAttribute("customer",this.customerRepository.findAll());
      model.addAttribute("orderstatus",this.orderStatusTypeRepository.findAll());
      return "flowerOrder/add";
   }

   @RequestMapping({"/edit"})
   public String edit(Model model, HttpServletRequest request) {
      String idString = request.getParameter("id");
      int id = Integer.parseInt(idString);
      System.out.println("BJTEST - id=" + id);
      Optional<FlowerOrder> found = this.flowerOrderRepository.findById(id);

       model.addAttribute("flowerOrder", this.flowerOrderRepository.findAll());
      model.addAttribute("flowerOrder", found);
      return "flowerOrder/add";
   }

   @RequestMapping({"/delete"})
   public String delete(Model model, HttpServletRequest request) {
      String idString = request.getParameter("id");
      int id = Integer.parseInt(idString);
      System.out.println("BJTEST - id=" + id);
      String propFileName = "messages";
      ResourceBundle rb = ResourceBundle.getBundle(propFileName);

      String successString;
      try {
         this.flowerOrderRepository.deleteById(id);
         successString = rb.getString("message.flowerOrder.deleted") + " (" + id + ")";
      } catch (EmptyResultDataAccessException var9) {
         successString = rb.getString("message.flowerOrder.deleted.error") + " (" + id + ")";
      }

      model.addAttribute("message", successString);
      model.addAttribute("flowerOrders", this.loadFlowerOrders());
      return "flowerOrder/list";
   }

   @RequestMapping({"/addSubmit"})
   public String addSubmit(Model model, @Valid @ModelAttribute("flowerOrder") FlowerOrder flowerOrder, BindingResult result) {
      if (result.hasErrors()) {
         System.out.println("Validation error");
         return "flowerOrder/add";
      } else {
         this.flowerOrderRepository.save(flowerOrder);
         String propFileName = "messages";
         ResourceBundle rb = ResourceBundle.getBundle(propFileName);
         String successAddString = rb.getString("message.flowerOrder.saved");
         model.addAttribute("message", successAddString);
         model.addAttribute("flowerOrders", this.loadFlowerOrders());
         return "flowerOrder/list";
      }
   }

   
   
   

   
   //KW - creating a public method to load all flower orders
   public ArrayList<FlowerOrder> loadFlowerOrders() {
      ArrayList<FlowerOrder> flowerOrders = this.flowerOrderRepository.findAll();
      return flowerOrders;
   }
   
   //KW - creating a public method to load all customers
       public ArrayList<Customer> loadCustomers() {
        ArrayList<Customer> customers = (ArrayList<Customer>) customerRepository.findAll();
        return customers;
    }
      //KW - creating a public method to load all order status types
       public ArrayList<OrderStatusType> loadOrderStatusType() {
        ArrayList<OrderStatusType> orderStatus = (ArrayList<OrderStatusType>) orderStatusTypeRepository.findAll();
        return orderStatus;
    }
       
      //KW - creating a public method to load all order status types
       public ArrayList<ItemType> loadItemType() {
        ArrayList<ItemType> itemType = (ArrayList<ItemType>) itemTypeRepository.findAll();
        return itemType;
    }
       
       //KW - load information from fields
  
   
}
    