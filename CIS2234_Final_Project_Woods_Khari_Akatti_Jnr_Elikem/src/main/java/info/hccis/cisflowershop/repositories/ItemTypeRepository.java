package info.hccis.cisflowershop.repositories;

import info.hccis.cisflowershop.entity.jpa.ItemType;
import java.util.ArrayList;
import org.springframework.data.repository.CrudRepository;

public interface ItemTypeRepository extends CrudRepository<ItemType, Integer> {

    ArrayList<ItemType> findAll();
}
