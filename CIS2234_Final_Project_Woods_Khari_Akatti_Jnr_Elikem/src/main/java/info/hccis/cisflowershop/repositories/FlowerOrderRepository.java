   package info.hccis.cisflowershop.repositories;

import info.hccis.cisflowershop.entity.jpa.FlowerOrder;
import java.util.ArrayList;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlowerOrderRepository extends CrudRepository<FlowerOrder, Integer> {
   ArrayList<FlowerOrder> findByOrderDate(String orderDate);
   ArrayList<FlowerOrder> findAll();
   
   //EAKJ added this to look for specific order(s) by a customer on 20201204
   ArrayList<FlowerOrder> findAllByCustomerId(int customerId);
}
 