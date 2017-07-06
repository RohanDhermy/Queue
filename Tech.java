/**
 * Class that creates a Tech object, that holds their idNumber, firstName, 
 * lastName, userName, and dayOff. 
 * @author Rohan Dhermy
 * @version (06/09/17)
 */
public class Tech {
    private int idNumber; 
    private String firstName; 
    private String lastName; 
    private String userName; 
    private DayOff dayOff;
    
    /**
     * Constructor of Tech object 
     * @param idNumber Tech's id number
     * @param firstName Tech's first name 
     * @param lastName Tech's last name 
     * @param userName Tech's username 
     * @param dayOff Tech's day off 
     */
    public Tech(int idNumber, String firstName, String lastName, String userName, DayOff dayOff){
        this.idNumber = idNumber; 
        this.firstName = firstName; 
        this.lastName = lastName; 
        this.userName = userName; 
        this.dayOff = dayOff;
    }
    
    /**
     * Method that returns the Tech's day off 
     * @return Tech's day off
     */
    public DayOff getDayOff(){
        return this.dayOff; 
    }
    
    /**
     * Method that returns the Tech's if number 
     * @return Tech's id number 
     */
    public int getIdNumber(){
        return this.idNumber; 
    }
    
    /**
     * Method that returns the Tech's first name 
     * @return Tech's first name
     */
    public String getfristName(){
        return this.firstName; 
    }
    
    /**
     * Method that returns the Tech's last name 
     * @return Tech's last name 
     */
    public String getLastName(){
        return this.lastName; 
    }
    
    /**
     * Method that returns the Tech's username
     * @return Tech's username 
     */
    public String getUserName(){
        return this.userName; 
    }
    
    /**
     * toString method that returns a String representation of the Tech object 
     * with all of their important information 
     * @return string representation of Tech object 
     */
    public String toString(){
        String result = this.idNumber + " " + this.firstName + " " + this.lastName
                + " " + this.userName + " " + this.dayOff.toString() + "\n"; 
        return result; 
    }
    
    /**
     * Enum for the Tech's day off 
     */
    public enum DayOff {
    MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY
    }
}

