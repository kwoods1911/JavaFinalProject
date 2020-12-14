package info.hccis.cisflowershop.main;

import com.google.gson.Gson;
import info.hccis.cisflowershop.model.jpa.Customer;
import info.hccis.cisflowershop.model.jpa.FlowerOrder;
import info.hccis.cisflowershop.util.UtilityRest;
import java.util.Scanner;
import org.json.JSONArray;

/**
 * Controller class
 *
 * @author mmegha
 * @since 20201113
 * @modifiedBy - MSII - 20201117 - Implemented customer functionality
 */
public class Controller {

    final public static String MAIN_MENU = "\nMain Menu "
            + "\n1.) Orders "
            + "\n2.) Customers";
    final public static String MENU = "\nMenu \nA) Add\nU) Update\n"
            + "V) View\n"
            + "D) Delete\n"
            + "X) eXit";
    final static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        boolean endProgram = false;
        do {
            System.out.println(MAIN_MENU);
            String selection = input.nextLine();
            switch (selection.toUpperCase()) {
                case "1":
                    String URL_STRING = "http://localhost:8081/api/OrderService/orders";
                    System.out.println(MENU);
                    String choice = input.nextLine();
                    FlowerOrder order;
                    String url;
                    switch (choice.toUpperCase()) {
                        case "A":
                            order = createOrder();
                            url = URL_STRING;
                            System.out.println("Url=" + url);
                            UtilityRest.addUsingRest(url, order);
                            break;
                        case "U":
                            order = createOrder();
                            url = URL_STRING;
                            System.out.println("Url=" + url);
                            UtilityRest.addUsingRest(url, order);
                            break;
                        case "D":
                            System.out.println("Enter id to delete");
                            Scanner input = new Scanner(System.in);
                            int id = input.nextInt();
                            input.nextLine();  //burn
                            UtilityRest.deleteUsingRest(URL_STRING, id);
                            break;
                        case "V":
                            String jsonReturned = UtilityRest.getJsonFromRest(URL_STRING);
                            //**************************************************************
                            //Based on the json string passed back, loop through each json
                            //object which is a json string in an array of json strings.
                            //*************************************************************
                            JSONArray jsonArray = new JSONArray(jsonReturned);
                            //**************************************************************
                            //For each json object in the array, show the first and last names
                            //**************************************************************
                            System.out.println("Here are the rows");
                            Gson gson = new Gson();
                            for (int currentIndex = 0; currentIndex < jsonArray.length(); currentIndex++) {
                                FlowerOrder current = gson.fromJson(jsonArray.getJSONObject(currentIndex).toString(), FlowerOrder.class);
                                System.out.println(current.toString());
                            }
                            break;
                        case "X":
                            endProgram = true;
                            break;
                        default:
                            System.out.println("INVALID OPTION");
                    }
                    break;
                case "2":
                    String URL_STRING_CUSTOMER = "http://localhost:8081/api/CustomerService/customers/";
                    System.out.println(MENU);
                    String choiceC = input.nextLine();
                    Customer customer;
                    String urlC;
                    switch (choiceC.toUpperCase()) {
                        case "A":
                            customer = createCustomer();
                            url = URL_STRING_CUSTOMER;
                            System.out.println("Url=" + url);
                            UtilityRest.addUsingRest(url, customer);
                            break;
//                        TODO Need to finish implementation of update functionality
//                        case "U":
//                            url = URL_STRING_CUSTOMER;
//                            System.out.println("Enter id to delete");
//                            int id = input.nextInt();
//                            input.nextLine();  //burn
//                            customer = UtilityRest.getSpecificObjectUsingRest(url,id);
//                            UtilityRest.updateUsingRest(url, customer , id);
//                            break;
                        case "D":
                            System.out.println("Enter id to delete");
                            Scanner input = new Scanner(System.in);
                            int id2 = input.nextInt();
                            input.nextLine();  //burn
                            UtilityRest.deleteUsingRest(URL_STRING_CUSTOMER, id2);
                            break;
                        case "V":
                            String jsonReturned = UtilityRest.getJsonFromRest(URL_STRING_CUSTOMER);
                            JSONArray jsonArray = new JSONArray(jsonReturned);
                            System.out.println("Here are the rows");
                            Gson gson = new Gson();
                            for (int currentIndex = 0; currentIndex < jsonArray.length(); currentIndex++) {
                                Customer current = gson.fromJson(jsonArray.getJSONObject(currentIndex).toString(), Customer.class);
                                System.out.println(current.toString() + "\n");
                            }
                            break;
                        case "X":
                            endProgram = true;
                            break;
                        default:
                            System.out.println("INVALID OPTION");
                    }
                    break;
            }
        } while (!endProgram);
    }

    /**
     * Create a parking pass object by passing asking user for input.
     *
     * @return parkingPass
     * @since 20201113
     * @author mmegha
     */
    public static FlowerOrder createOrder() {
        FlowerOrder newOrder = new FlowerOrder();

        System.out.println("Enter id: (0 for add)");
        newOrder.setId(Integer.parseInt(input.nextLine()));

        System.out.println("Customer Id:");
        newOrder.setCustomerId(input.nextInt());
        input.nextLine();  //burn
        
        System.out.println("Order Date:");
        newOrder.setOrderDate(input.nextLine());

        System.out.println("Welcome Baby Boy:");
        newOrder.setItem1(input.nextInt());

        System.out.println("Welcome Baby Girl:");
        newOrder.setItem2(input.nextInt());
        
        System.out.println("Very Special Delivery:");
        newOrder.setItem3(input.nextInt());
        
        System.out.println("Small Stuffed Animal:");
        newOrder.setItem4(input.nextInt());
        
        System.out.println("Medium Stuffed Animal:");
        newOrder.setItem5(input.nextInt());
        
        System.out.println("Large Stuffed Animal:");
        newOrder.setItem6(input.nextInt());
        
        System.out.println("Order Status:");
        newOrder.setOrderStatus(input.nextInt());
        
        System.out.println("Amount paid:");
        newOrder.setAmountPaid(input.nextBigDecimal());
        
        return newOrder;
    }

    /**
     * Create a new customer
     * 
     * @author MSII
     * @since 20201117
     * @return Customer object
     */
    public static Customer createCustomer(){
        Customer customer = new Customer();
        customer.setId(0);
        boolean valid = false;
        while (!valid){
            System.out.println("Is the customer a preferred (2) or regular (1) customer?");
            int tempValue = input.nextInt();
            if(tempValue == 1 || tempValue == 2){
                customer.setCustomerTypeId(tempValue);
                valid = true;
            }else{
                System.out.println("Invalid Entry.  Please select 1 or 2.");
            }
        }
        input.nextLine(); //burn
        System.out.println("Customer Name : ");
        customer.setFullName(input.nextLine());
        System.out.println("Address : ");
        customer.setAddress1(input.nextLine());
        System.out.println("City : ");
        customer.setCity(input.nextLine());
        System.out.println("Province : ");
        customer.setProvince(input.nextLine());
        System.out.println("Postal Code : ");
        valid = false;
        //https://howtodoinjava.com/regex/java-regex-validate-canadian-postal-zip-codes/
        String regex = "^(?!.*[DFIOQU])[A-VXY][0-9][A-Z] ?[0-9][A-Z][0-9]$";
        do {
            String postalCode = input.nextLine().toUpperCase();
            if (postalCode.matches(regex)) {
                valid = true;
                customer.setPostalCode(postalCode);
            } else {
                System.out.println("Please enter a valid Canadian postal code: ");
            }
        } while (!valid);
        System.out.println("Phone Number : (xxx-xxx-xxxx)");
        String pattern = "(?:\\d{3}-){2}\\d{4}";
        valid = false;
        do {
            String phoneNumber = input.nextLine();
            if (phoneNumber.matches(pattern)) {
                valid = true;
                customer.setPhoneNumber(phoneNumber);
            } else {
                System.out.println("Please enter a valid phone number: ");
            }
        } while (!valid);
        pattern = "([12]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01]))";
        valid = false;
        while(!valid){
            System.out.println("Birth Date : (yyyy-mm-dd)");
            String birthday = input.nextLine();
            if(birthday.matches(pattern)){
                valid = true;
                customer.setBirthDate(birthday);
            }else{
                System.out.println("Invalid input.  Please enter a valid date.");
            }
        }
        if(customer.getCustomerTypeId() == 2){
            System.out.println("Loyalty Card # : ");
            customer.setLoyaltyCard(input.nextLine());
        }
        return customer;
    }
}
