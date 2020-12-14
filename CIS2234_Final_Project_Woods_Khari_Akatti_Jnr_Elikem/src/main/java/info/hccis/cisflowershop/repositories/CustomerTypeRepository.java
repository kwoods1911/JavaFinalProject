   package info.hccis.cisflowershop.repositories;

import info.hccis.cisflowershop.entity.jpa.CustomerType;
import java.util.ArrayList;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerTypeRepository extends CrudRepository<CustomerType, Integer> {
   ArrayList<CustomerType> findAll();
}