/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.cisflowershop.order.main;
import java.util.Scanner;
import info.cisflowershop.model.jpa.FlowerOrder;

/**
 *
 * @author Khari Woods
 * Sprint 4 Final Commit
 */
public class Controller {

    /**
     * @param args the command line arguments
     */
    
           final public static String MENU = "\nMain Menu \nA) Add\n U) Update"
                   + "V) View\n D) Delete\n X) eXit";
           
           //URL string for API call 
                  final static Scanner input = new Scanner(System.in);//KW creating scanner objects for 
                   
                   
           public static String userSelection;
           private static final String URL_STRING = "http://localhost:8081/api/OrderService/orders/";
    public static void main(String[] args) {

        // TODO code application logic here
       boolean programComplete = false;
       while(!programComplete){
           System.out.println(MENU);//KW - Display menu
           userSelection = input.nextLine();
           FlowerOrder flowerOrder;
           switch(userSelection.toUpperCase()){
               case "A":
                   flowerOrder = createFlowerOrder();
                   break;
               
           }
           
           
       }
        
        //KW implement switch statement
      
    }
    
    
    public static FlowerOrder createFlowerOrder(){
        FlowerOrder flowerOrder = new FlowerOrder();
        //Kw set variables
        System.out.println("Enter a customer id:");
        flowerOrder.setCustomerId(input.nextInt());
        
        System.out.println("Enter a order number");
        flowerOrder.setId(input.nextInt());
        
        System.out.println("Enter the amount for Item 1:");
        flowerOrder.setItem1(input.nextInt());
        
        System.out.println("Enter the amount for Item 2: ");
        flowerOrder.setItem2(input.nextInt());
        
        System.out.println("Enter the amount for Item 3: ");
        flowerOrder.setItem3(input.nextInt());
        
        System.out.println("Enter the amount for Item 4: ");
        flowerOrder.setItem4(input.nextInt());
        
        System.out.println("Enter the amount for Item 5: ");
        flowerOrder.setItem5(input.nextInt());
        
        System.out.println("Enter the amount for Item 6: ");
        flowerOrder.setItem6(input.nextInt());
        
        System.out.println("Set order status 1 - complete 0- Incomplete:");
        flowerOrder.setOrderStatus(input.nextInt());
        
        System.out.println("Enter total Cost:");
        flowerOrder.setTotalCost(input.nextBigDecimal());
        
        
        System.out.println("Enter amount customer paid:");
        flowerOrder.setAmountPaid(input.nextBigDecimal());

        return flowerOrder;
    }
    
}
