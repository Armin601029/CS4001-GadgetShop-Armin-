// MP3 class represents an MP3 player and extends the Gadget class
public class MP3 extends Gadget {

    
    // Instance variable
    
    private int memory; // Stores available memory in MB

    
    // Constructor
    
    public MP3(String model, double price, int weight, String size, int memory) {
        super(model, price, weight, size); // Call parent (Gadget) constructor
        this.memory = memory; // Initialise memory value
    }

    
    // Accessor method

    public int getMemory() { 
        return memory; // Returns current available memory
    }

    
    // Method to download music
    
    public String downloadMusic(int size) {

        // Check if there is enough memory available
        if (size <= memory) {
            memory -= size; // Reduce available memory
            return "Downloaded " + size + "MB. Remaining memory: " + memory; // Success message
        } else {
            return "Not enough memory."; // Error message
        }
    }

    
    // Method to delete music
    
    public String deleteMusic(int size) {

        memory += size; // Increase available memory after deleting music
        return "Deleted " + size + "MB. Memory now: " + memory; // Confirmation message
    }

    
    // Display method (overrides Gadget display)
    
    @Override
    public String display() {

        // Call parent display method and append memory information
        return super.display() + ", Memory: " + memory + "MB";
    }
}