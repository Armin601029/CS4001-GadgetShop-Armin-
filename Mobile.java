// Mobile class represents a mobile phone and extends the Gadget class
public class Mobile extends Gadget {

    
    // Instance variable
    
    private int credit; // Stores the available calling credit (in minutes)

    
    // Constructor
    
    public Mobile(String model, double price, int weight, String size, int credit) {
        super(model, price, weight, size); // Call parent (Gadget) constructor
        this.credit = credit; // Initialise credit value
    }

    
    // Accessor method

    public int getCredit() { 
        return credit; // Returns current credit
    }

    
    // Method to add credit
    
    public String addCredit(int amount) {

        // Check if amount is positive
        if (amount > 0) {
            credit += amount; // Add credit
            return "Credit added. New balance: " + credit; // Return success message
        } else {
            return "Enter a positive credit amount."; // Error message
        }
    }
    
    // Method to make a phone call
    
    public String makeCall(String number, int duration) {

        // Check if user has enough credit
        if (duration <= credit) {
            credit -= duration; // Deduct credit
            return "Calling " + number + " for " + duration +
                   " mins. Remaining credit: " + credit; // Success message
        } else {
            return "Not enough credit."; // Error message
        }
    }

    
    // Display method (overrides Gadget display)
    @Override
    public String display() {

        // Call parent display method and add credit info
        return super.display() + ", Credit: " + credit;
    }
}