package info.hccis.cisflowershop.rest;

import com.google.gson.Gson;
import info.hccis.cisflowershop.entity.jpa.Customer;
import info.hccis.cisflowershop.repositories.CustomerRepository;
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
@Path("/CustomerService/customers")
public class CustomerService {

    private final CustomerRepository cr;

    @Autowired
    public CustomerService(CustomerRepository cr) {
        this.cr = cr;
    }

    @GET
    @Produces("application/json")
    public ArrayList<Customer> getAll() {
        ArrayList<Customer> customers = (ArrayList<Customer>) cr.findAll();
        return customers;
    }

    @GET
    @Path("/{id}")
    @Produces("application/json")
    public Response getCustomerById(@PathParam("id") int id) throws URISyntaxException {

        Optional<Customer> customer = cr.findById(id);

        if (!customer.isPresent()) {
            return Response.status(204).build();
        } else {
            return Response
                    .status(200)
                    .entity(customer).build();
        }
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createCustomer(String customerJson) {
        try {
            String temp = save(customerJson);
            return Response.status(201).entity(temp).header("Access-Control-Allow-Origin", "*")
                    .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT").build();
        } catch (AllAttributesNeededException aane) {
            return Response.status(400).entity(aane.getMessage()).build();
        }
    }
//    
//    @DELETE
//    @Path("/{id}")
//    public Response deleteBooking(@PathParam("id") int id) throws URISyntaxException {
//        Optional<Booking> booking = br.findById(id);
//        if(booking != null) {
//            br.deleteById(id);
//            return Response.status(HttpURLConnection.HTTP_CREATED).build();
//        }
//        return Response.status(404).build();
//    }
//

    @PUT
    @Path("/{id}")
    @Consumes("application/json")
    @Produces("application/json")
    public Response updateCustomer(@PathParam("id") int id, String customerJson) throws URISyntaxException {

        try {
            String temp = save(customerJson);
            return Response.status(201).entity(temp).header("Access-Control-Allow-Origin", "*")
                    .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT").build();
        } catch (AllAttributesNeededException aane) {
            return Response.status(400).entity(aane.getMessage()).build();
        }

    }

    public String save(String json) throws AllAttributesNeededException {

        Gson gson = new Gson();
        Customer customer = gson.fromJson(json, Customer.class);

        if (customer.getFullName()== null || customer.getFullName().isEmpty()
                || customer.getAddress1() == null || customer.getAddress1().isEmpty()
                || customer.getPostalCode()== null || customer.getPostalCode().isEmpty()
                || customer.getPhoneNumber()== null || customer.getPhoneNumber().isEmpty()) {

            throw new AllAttributesNeededException("Please provide all mandatory inputs");
        }

        if (customer.getId() == null) {
            customer.setId(0);
        }

        customer = cr.save(customer);

        String temp = "";
        temp = gson.toJson(customer);

        return temp;
    }
  
}
