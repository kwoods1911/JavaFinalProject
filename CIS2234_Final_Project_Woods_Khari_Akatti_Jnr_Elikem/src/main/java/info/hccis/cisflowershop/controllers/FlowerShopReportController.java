package info.hccis.cisflowershop.controllers;

import info.hccis.cisflowershop.entity.jpa.FlowerOrder;
import info.hccis.cisflowershop.bo.FlowerOrderBO;
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
@RequestMapping({"/report"})
public class FlowerShopReportController {
    
    //KW - creating repositories
   private final FlowerOrderRepository flowerOrderRepository;
 
   


   public FlowerShopReportController(FlowerOrderRepository flor) {
      this.flowerOrderRepository = flor;
   }
   
   
   
   
   @RequestMapping({"orderReport"})
   public String getOrders(Model model){
       FlowerOrder flowerOrder = new FlowerOrder();
       model.addAttribute("flowerOrder",flowerOrder);
       return "report/orderReport";
   }
   
   
   
   @RequestMapping({"orderExport"})
   
   public String exportOrders(Model model){
       FlowerOrder flowerOrder = new FlowerOrder(); 
     model.addAttribute("flowerOrder",flowerOrder);
     FlowerOrderBO exportOrder = new FlowerOrderBO(flowerOrderRepository);
     exportOrder.setUpFile();
     exportOrder.load();
     exportOrder.writeAllOrdersToFile();
     model.addAttribute("filePath","File is located here: "+exportOrder.filePath());
    return "report/exportSuccessful";
}
   
   
   
   
//public ArrayList<Camper> loadCampersByDob(String dob) {
//        ArrayList<Camper> campers = (ArrayList<Camper>) camperRepository.findAllByDob(dob);
//        HashMap<Integer, String> camperTypesMap = CamperTypeBO.getCamperTypesMap();
//        for (Camper current : campers) {
//            current.setCamperTypeDescription(camperTypesMap.get(current.getCamperType()));
//        }
//        return campers;
//
//    }
   
   
   
   
   
   
   
   @RequestMapping({"submitReport"})
   public String getReport(Model model, @ModelAttribute("flowerOrder") FlowerOrder flowerOrder){
       
       model.addAttribute("flowerOrders",loadOrderByDates(flowerOrder.getOrderDate()));
       return "flowerOrder/list";
   }
   
   
   
   
   public ArrayList<FlowerOrder> loadOrderByDates(String orderDate){
//       ArrayList<FlowerOrder> orders = (ArrayList<FlowerOrder>) flowerOrderRepository.findAll();
       ArrayList<FlowerOrder> orders = (ArrayList<FlowerOrder>) flowerOrderRepository.findByOrderDate(orderDate);
       HashMap<Integer,String> orderDates = FlowerOrderBO.getFlowerOrderDateMaps();
       for(FlowerOrder current : orders){
               current.setOrderDate(orderDates.get(current.getOrderDate()));
       }
       System.out.println("size"+orders.size());
       return orders;
   }
   

   //KW - creating a public method to load all flower orders
   public ArrayList<FlowerOrder> loadFlowerOrders() {
      ArrayList<FlowerOrder> flowerOrders = this.flowerOrderRepository.findAll();
      return flowerOrders;
   }
   
 
       
   
}
    