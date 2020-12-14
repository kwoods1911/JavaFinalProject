package info.hccis.cisflowershop.bo;

import info.hccis.cisflowershop.dao.ItemTypeDAO;
import info.hccis.cisflowershop.entity.jpa.ItemType;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Item type business object class
 * @author Elikem Akatti Jnr
 * @since 20201030
 */
public class ItemTypeBO {
    
     private static HashMap<Integer, String> itemTypesMap = new HashMap();

    public static HashMap<Integer, String> getItemsTypesMap() {
        return itemTypesMap;
    }

    public static void setItemsTypesMap(HashMap<Integer, String> itemTypesMap) {
        itemTypesMap = itemTypesMap;
    }

    
    
    
    
    /**
     * Connect to the data access object to get the item types from the datasource.
     *
     * @since 20200923
     * @author BJM
     */
    public ArrayList<ItemType> load() {

        //Read from the database
        ItemTypeDAO itemTypeDAO = new ItemTypeDAO();
        ArrayList<ItemType> itemTypes = itemTypeDAO.select();

        
        
        return itemTypes;
    }
}
