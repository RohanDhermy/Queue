/**
 * Class that creates a Customer object, that holds their number, firstName, 
 * lastName, email, and phoneNumber. 
 * @author Rohan Dhermy
 * @version (06/09/17)
 */
public class Customer {
    private int number; 
    private String firstName; 
    private String lastName; 
    private String email; 
    private String phoneNumber; 
    
    /**
     * Constructor of Customer object 
     * @param number customer number
     * @param firstName customer's first name
     * @param lastName customer's last name
     * @param email customer's email 
     * @param phoneNumber customer's phone number 
     */
    public Customer(int number, String firstName, String lastName, String email, String phoneNumber){
        this.number = number; 
        this.firstName = firstName; 
        this.lastName = lastName; 
        this.email = email; 
        this.phoneNumber = phoneNumber; 
    }
    
    /**
     * Method that returns the customer number 
     * @return customer number 
     */
    public int getNumber(){
        return this.number; 
    }
    
    /**
     * Method that returns the customer's first name
     * @return customer's first name 
     */
    public String getfristName(){
        return this.firstName; 
    }
    
    /**
     * Method that returns the customer's last name
     * @return customer's last name 
     */
    public String getLastName(){
        return this.lastName; 
    }
    
    /**
     * Method that returns the customer's email
     * @return customer's email
     */
    public String getEmail(){
        return this.email; 
    }
    
    /**
     * Method that returns the customer's phone number 
     * @return customer's phone number
     */
    public String getPhoneNumber(){
        return this.phoneNumber;
    }
    
    /**
     * toString method that returns a String representation of the Customer object 
     * with all of their important information 
     * @return string representation of Customer object 
     */
    public String toString(){
        String result = this.number + " " + this.firstName + " " + this.lastName
                + " " + this.email + " " + this.phoneNumber + "\n"; 
        return result; 
    }
}
