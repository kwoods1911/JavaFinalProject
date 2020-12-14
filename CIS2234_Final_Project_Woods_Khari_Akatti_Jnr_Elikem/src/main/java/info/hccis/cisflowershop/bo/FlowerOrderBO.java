package info.hccis.cisflowershop.bo;
import com.google.gson.Gson;
import info.hccis.cisflowershop.dao.FlowerOrderDAO;
import info.hccis.cisflowershop.entity.jpa.FlowerOrder;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import info.hccis.cisflowershop.entity.jpa.FlowerOrder;
import info.hccis.cisflowershop.repositories.FlowerOrderRepository;
import java.text.SimpleDateFormat;
import java.util.Date;


public class FlowerOrderBO {
    
    
    //Kw implementing a HashMap
    private static HashMap<Integer, String> flowerOrderMaps = new HashMap();
    
    //KW - declaring a date formate
    public static SimpleDateFormat fileDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
    
    //Kw import  orders from repository 
    
    private final FlowerOrderRepository ordersRepository;
    
    public FlowerOrderBO(FlowerOrderRepository fr){
        this.ordersRepository = fr;
    }
    
    
    
    //Load all orders from an array list.
    

    
    //KW getters and setters for HashMaps
    public static HashMap<Integer, String> getFlowerOrderDateMaps() {
        return flowerOrderMaps;
    }
    
    //KW setters for HashMaps
     public static void setFlowerOrderDateMaps(HashMap<Integer, String> flowerOrderMaps) {
        flowerOrderMaps = flowerOrderMaps;
    }
    
    
    
   public ArrayList<FlowerOrder> load() {
      FlowerOrderDAO flowerOrderDAO = new FlowerOrderDAO();
//      ArrayList<FlowerOrder> flowerOrders = flowerOrderDAO.select();
      ArrayList<FlowerOrder> flowerOrders = (ArrayList<FlowerOrder>) ordersRepository.findAll();//KW loading files from repository.
      return flowerOrders;
   }




   
   
   //KW Adding functionality to export flower orders
   //KW - note to self: replace yyyymmddhhmmss with date that the order was generated.
   public static final String PATH = "c:\\orders\\";
   public static final String FILE_NAME = "orders" + fileDateFormat.format(new Date()) + ".json";
   
   private ArrayList<FlowerOrder> flowerOrders = new ArrayList();
   
   
   
   public String filePath(){
       return  PATH + FILE_NAME;
   }
   
   
   //KW - function responsible for initially setting up the file.
   
    public void setUpFile(){
    File myfile; //KW - creaing a file object
    try{
        Path path = Paths.get(PATH); //KW - creating a PATH  object. 
        try{
            Files.createDirectories(path);//KW - creating directories with method from File object.
        } catch (IOException ex){
            System.out.println("There is an error with creating the directory");
        }
        
        myfile = new File(PATH + (FILE_NAME)); //KW - creating a new file and storing it in a variable.
        
        //KW - notify user wheter file is successfully created or not.
        if(myfile.createNewFile()){
            System.out.println("File is created!");
        } else {
            System.out.println("File exists already.");
        }
        
    }catch(IOException ex){
        System.out.println("There is an error with creating the directory");
    }
    
    }
    
    
    
    

    
    
    

    

    public void writeAllOrdersToFile() {
        //KW load all flower orders then save to file.
        //Create a new writer which will override the file containing the bookings.
        BufferedWriter writer = null;
        ArrayList<FlowerOrder> flowerOrderDetails = (ArrayList<FlowerOrder>) ordersRepository.findAll();//KW loading files from repository.
        try {
            writer = new BufferedWriter(new FileWriter(PATH + FILE_NAME, false));//KW writing information to file.
             //Use GSON to get a json string encoding for a booking
                Gson gson = new Gson();
                String orderDetails = gson.toJson(flowerOrderDetails);
                writer.write(orderDetails + "\n");//KW - writing
          
        } catch (IOException ex) {
            System.out.println("Error accessing the file." + ex);
        }
            
        
        try {
            writer.close();
            } catch (IOException ex) {
            System.out.println("There was an error when closing the file writer:"+ ex);
        }

    }
           
           
  
    
   
}
 