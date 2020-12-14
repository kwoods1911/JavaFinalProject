package info.hccis.cisflowershop.repositories;

import info.hccis.cisflowershop.entity.jpa.OrderStatusType;
import java.util.ArrayList;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderStatusTypeRepository extends CrudRepository<OrderStatusType, Integer> {

    ArrayList<OrderStatusType> findAll();
}
