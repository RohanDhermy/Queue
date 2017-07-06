import java.util.Scanner; 
import java.io.File; 
import java.io.FileNotFoundException;
import java.util.Random; 
import java.util.Timer;
import java.util.TimerTask;
/**
 * This program shows a simulation of how call and support queueing will work for
 * Sophocles Software's call center. It asks the user to input the number of 
 * customers that will be in queue to begin with, the time interval for each call, 
 * the day of the week, and how many calls to handle in the simulation. The program 
 * creates mock ArrayLists of Customers and Techs and then creates a queue of 
 * customers and techs to be paired with each other. Once a call is taken care of,
 * the tech is put back into the tech queue and the customer is removed from the 
 * customer queue. Then we add new random customer to the customer queue. 
 * @author Rohan Dhermy
 * @version (06/09/17)
 */
public class Proj07_Rohan_Dhermy {
    //static variable that will keep track of how many calls to handle using timer
    private static int callCount = 0; 
    private static int numberOfCalls = 0; 
    public static void main(String[] args) {
        //ArrayList to hold mock data of customer 
        ArrayList<Customer> customerList = new ArrayList<>(); 
        readCustomers(customerList);
        //ArrayList to hold mock data of techs 
        ArrayList<Tech> techList = new ArrayList<>(); 
        readTechs(techList);
        //Get customer input, make sure it is correct type of data, if not 
        //Ask for user to enter the correct data. Keep asking till they type 
        //The correct value in 
        Scanner userInput = new Scanner(System.in); 
        int customersInQueue = 0;
        System.out.print("Enter number of customers in queue: ");
        String tempQueue = userInput.next(); 
        while(tempQueue.matches("^.*[^0-9].*$")){
            System.out.print("\nOops! Input for customers in queue has to be a "
                    + "integer more than or equal to 0!");
            System.out.print("\nEnter the number of customers in a queue: ");
            tempQueue = userInput.next(); 
        }
        customersInQueue = Integer.valueOf(tempQueue);
       
        System.out.print("\nEnter time interval for calls (seconds): "); 
        double timeInterval = 0.0;
        String tempInterval = userInput.next(); 
        while(tempInterval.matches("^(0*[^1-9][0-9]*(\\.[^0-9]+)?|0+\\.[^0-9]*[^1-9][^0-9]*)$")){
            System.out.print("\nOops! Input for time interval has to be a real number more than 0!");
            System.out.print("\nEnter time interval for calls (seconds): ");
            tempInterval = userInput.next(); 
        }
        timeInterval = Double.valueOf(tempInterval); 
       
        System.out.print("\nEnter the day of the week (Monday-Friday): "); 
        String tempDay = userInput.next();
        Tech.DayOff day = null; 
        tempDay = tempDay.toUpperCase();
        while(!tempDay.equals("MONDAY") && !tempDay.equals("TUESDAY") && 
                !tempDay.equals("WEDNESDAY") && !tempDay.equals("THURSDAY") 
                && !tempDay.equals("FRIDAY")){
            System.out.print("\nOops! Input for day has to be Monday-Friday "
                    + "(check your spelling)!");
            System.out.print("\nEnter the day of the week (Monday-Friday): "); 
            tempDay = userInput.next();
            tempDay = tempDay.toUpperCase();
        }
        //Will use switch statement to determine day off for tech 
        switch(tempDay){
            case "MONDAY": day = Tech.DayOff.MONDAY;
                break;
            case "TUESDAY": day = Tech.DayOff.TUESDAY;
                break;
            case "WEDNESDAY": day = Tech.DayOff.WEDNESDAY;
                break;
            case "THURSDAY": day = Tech.DayOff.THURSDAY;
                break;
            case "FRIDAY": day = Tech.DayOff.FRIDAY;
                break; 
        }
        System.out.print("\nEnter the number of calls: ");
        numberOfCalls = 0;
        String tempCalls = userInput.next(); 
        while(tempCalls.matches("^.*[^0-9].*$")){
            System.out.print("\nOops! Input for customers in queue has to be an "
                    + "integer more than or equal to 0!");
            System.out.print("\nEnter the number of calls: ");
            tempCalls = userInput.next(); 
        }
        numberOfCalls = Integer.valueOf(tempCalls);
        System.out.println(); 
        //Create customer queue
        Queue<Customer> customerQueue = new Queue<>(); 
        createCustomerQueue(customersInQueue, customerList, customerQueue);
        //Create Tech queue
        Queue<Tech> techQueue = new Queue<>(); 
        createTechQueue(day, techList, techQueue); 
        //Create a timer that will call the run() method in the class that extends
        //the TimerTask interface 
        Timer callCenterTimer = new Timer(); 
        Random rand = new Random(); 
        class StartSimulation extends TimerTask{
        /**
         * Run method for TimerTask interface that will pair the next customer in 
         * queue with the next tech in queue 
         */
        @Override
        public void run() {
            //If we have not exceeded number of calls pair next customer in queue
            //with next tech in queue
            if(callCount < numberOfCalls){
                //pick a random customer to add to the customer queue
                int index = rand.nextInt(customerList.size()); 
                customerQueue.add(customerList.get(index));
                System.out.println(); 
                System.out.print(customerQueue.remove());
                System.out.println("Paired with"); 
                Tech tempTech = techQueue.remove();
                System.out.print(tempTech);
                //Add tech back to tech queue 
                techQueue.add(tempTech);
                callCount++;
            }
            else{
                System.out.println(); 
                 //Reached number of specified calls so cancel timer 
                 System.out.println("-----END OF SIMULATION------");
                 callCenterTimer.cancel();
            }
        }
        }
        //Schedule timer for pairing call 
        if(timeInterval == 0){
            timeInterval = 0.00001; 
        }
        callCenterTimer.schedule(new StartSimulation(), 0, (long)timeInterval*1000); 
    }
    
    /**
     * Method that will create a queue of the specified number of random customers 
     * from the ArrayList of customers. 
     * @param customersInQueue specified by the user input
     * @param customerList the ArrayList of customers 
     * @param customerQueue the customerQueue we will be adding customers to
     */
    public static void createCustomerQueue(int customersInQueue, 
        ArrayList<Customer> customerList, Queue<Customer> customerQueue){
        Random rand = new Random(); 
        for(int count = 1; count <= customersInQueue; count++){
            int index = rand.nextInt(customerList.size()); 
            customerQueue.add(customerList.get(index));
        }
    }
    
    /**
     * Method that will add all techs working the day specified by user input into 
     * the tech queue.
     * @param day the day the calls are being handled(specified by user input)
     * @param techList ArrayList of Techs
     * @param techQueue queue of Techs 
     */
    public static void createTechQueue(Tech.DayOff day, 
            ArrayList<Tech> techList, Queue<Tech> techQueue){
        for(int index = 0; index < techList.size();index++){
            //If tech dayOff is this day, do not add them to the queue
            if(techList.get(index).getDayOff().equals(day)){ 
            }
            else{
                techQueue.add(techList.get(index));
            }
        }
    }
    /**
     * Method that will read file line by line and create Customer objects and 
     * add them to the customer ArrayList 
     * @param customerList ArrayList of customers 
     */
    public static void readCustomers(ArrayList<Customer> customerList){
         try{
            Scanner inFile = new Scanner(new File("Customers.csv"));
            int number = 0; 
            String firstName = ""; 
            String lastName = ""; 
            String email = ""; 
            String phoneNumber = ""; 
            String line = ""; 
            while(inFile.hasNextLine()){
                //Read line
                line = inFile.nextLine(); 
                Scanner lineScanner = new Scanner(line); 
                lineScanner.useDelimiter(",");
                //Read data sperated by comma in line
                number = lineScanner.nextInt();
                firstName = lineScanner.next(); 
                lastName = lineScanner.next(); 
                email = lineScanner.next(); 
                phoneNumber = lineScanner.next();
                //Create customer object 
                Customer newCustomer = new Customer(number, firstName, lastName,
                        email, phoneNumber);
                //Add to customer ArrayList 
                customerList.add(newCustomer);
            }
        }
        catch(FileNotFoundException e){
            System.out.println("Exception: Customer File Not Found"); 
        }
    }
    
    /**
     * Method that will read file line by line and create Tech objects and 
     * add them to the Tech ArrayList
     * @param techList ArrayList of Techs
     */
    public static void readTechs(ArrayList<Tech> techList){
        try{
            Scanner inFile = new Scanner(new File("Techs.csv"));
            int idNumber = 0; 
            String firstName = ""; 
            String lastName = ""; 
            String userName = ""; 
            Tech.DayOff dayOff = null; 
            String line = "";  
            while(inFile.hasNextLine()){
                //Read line
                line = inFile.nextLine(); 
                Scanner lineScanner = new Scanner(line); 
                lineScanner.useDelimiter(",");
                //Read data sperated by comma 
                idNumber = lineScanner.nextInt(); 
                firstName = lineScanner.next(); 
                lastName = lineScanner.next(); 
                userName = lineScanner.next(); 
                String day = lineScanner.next();
                //Determine DayOff enum with switch statement 
                switch(day){
                    case "MONDAY": dayOff = Tech.DayOff.MONDAY;
                         break;
                    case "TUESDAY": dayOff = Tech.DayOff.TUESDAY;
                         break;
                    case "WEDNESDAY": dayOff = Tech.DayOff.WEDNESDAY;
                         break;
                    case "THURSDAY": dayOff = Tech.DayOff.THURSDAY;
                         break;
                    case "FRIDAY": dayOff = Tech.DayOff.FRIDAY;
                         break;    
                }
                Tech newTech = new Tech(idNumber, firstName, lastName,
                        userName, dayOff);
                techList.add(newTech);
            }
        }
        catch(FileNotFoundException e){
            System.out.println("Exception: Tech File Not Found"); 
        }
    }
    
    private static void inputValidation(String prompt){
        System.out.println("Error: Invalid entry"); 
    }
    
}
