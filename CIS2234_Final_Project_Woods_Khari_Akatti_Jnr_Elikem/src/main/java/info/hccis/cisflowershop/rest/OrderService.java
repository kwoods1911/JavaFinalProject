package info.hccis.cisflowershop.rest;

import com.google.gson.Gson;
import info.hccis.cisflowershop.entity.jpa.Customer;
import info.hccis.cisflowershop.entity.jpa.FlowerOrder;
import info.hccis.cisflowershop.repositories.FlowerOrderRepository;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Optional;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.springframework.beans.factory.annotation.Autowired;

/**
* Customer service controller class .
*
* @since 20201125
* @author Elikem Akatti Jnr
*/
@Path("/OrderService/orders")
public class OrderService {
    
     private final FlowerOrderRepository fr;
     
         @Autowired
    public OrderService (FlowerOrderRepository fr){
        this.fr = fr;
    }

 @GET
@Produces("application/json")
public ArrayList<FlowerOrder> getAll() {
    
return    fr.findAll();
}



    @GET
    @Path("/{id}")
    @Produces("application/json")
    public Response getFlowerOrderByCusId(@PathParam("id")int id) throws URISyntaxException{
        Optional <FlowerOrder> flowerOrder = fr.findById(id);
        
        if(!flowerOrder.isPresent()){
            return Response.status(204).build();
        } else{
            return Response
                    .status(200)
                    .entity(flowerOrder).build();
        }
        
    }
    
    
//    @POST
//    @Produces(MediaType.APPLICATION_JSON)
//    @Consumes(MediaType.APPLICATION_JSON)
//    public Response createFlowerOrder(String flowerOrderJson){
//        try{
//            String temp = save(flowerOrderJson);
//            return Response.status(201).entity(temp).header("Access-Control-Allow-Origin", "*")
//                    .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT").build();
//        }catch(AllAttributesNeededException aane){
//            return Response.status(400).entity(aane.getMessage()).build();
//        }
//    }

    @PUT
    @Path("/{id}")
    @Consumes("/application/json")
    @Produces("application/json")
    
    public Response updateFlowerOrder(@PathParam("id") int id, String flowerOrderJson) throws URISyntaxException{
        try{
            String temp = save(flowerOrderJson);
             return Response.status(201).entity(temp).header("Access-Control-Allow-Origin", "*")
                    .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT").build();
        }catch(AllAttributesNeededException aane){
            return Response.status(400).entity(aane.getMessage()).build();
        }
    }
    
    






    public String save(String json) throws AllAttributesNeededException{
        Gson gson = new Gson();
        FlowerOrder flowerOders = gson.fromJson(json, FlowerOrder.class);
        
        if(flowerOders.getCustomerId() == null){
//            throw new AllAttributesNeededException("Please enter all the information");
            flowerOders.setCustomerId(0);
        }
        
        flowerOders = fr.save(flowerOders);
        
        String temp = "";
        temp = gson.toJson(flowerOders);
        
        return temp;  
    }    


}