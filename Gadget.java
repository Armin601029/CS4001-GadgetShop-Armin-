// Gadget class represents a general electronic device
public class Gadget {

    // Instance variables (attributes)
    
    private String model;   // Stores the model name of the gadget
    private double price;   // Stores the price in pounds
    private int weight;     // Stores the weight in grams
    private String size;    // Stores the size description (e.g., dimensions)

    // Constructor
    
    public Gadget(String model, double price, int weight, String size) {
        this.model = model;   // Initialise model using constructor parameter
        this.price = price;   // Initialise price
        this.weight = weight; // Initialise weight
        this.size = size;     // Initialise size
    }

    // Accessor (getter) method

    public String getModel() { 
        return model; // Returns the model name
    }

    public double getPrice() { 
        return price; // Returns the price
    }

    public int getWeight() { 
        return weight; // Returns the weight
    }

    public String getSize() { 
        return size; // Returns the size description
    }
    
    // Display method
    
    public String display() {
        // Returns a formatted string containing all gadget details
        return "Model: " + model +
               ", Price: £" + price +
               ", Weight: " + weight + "g" +
               ", Size: " + size;
    }
}