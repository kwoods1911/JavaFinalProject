package info.hccis.cisflowershop.repositories;

import info.hccis.cisflowershop.entity.jpa.Customer;
import java.util.ArrayList;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Integer> {

    ArrayList<Customer> findAll();

}
