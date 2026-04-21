import javafx.application.Application; // JavaFX base class for GUI applications
import javafx.scene.Scene; // Represents the content inside the window
import javafx.scene.control.*; // UI controls (Button, Label, TextField, etc.)
import javafx.scene.layout.*; // Layout managers (GridPane, HBox, etc.)
import javafx.stage.Stage; // Main window (stage)
import javafx.geometry.Insets; // Padding around layout
import java.util.ArrayList; // Dynamic list to store gadgets

public class GadgetShop extends Application { // Main GUI class

    // =========================
    // Text fields for user input
    // =========================
    private final TextField modelField = new TextField(); // Input for model name
    private final TextField priceField = new TextField(); // Input for price
    private final TextField weightField = new TextField(); // Input for weight
    private final TextField sizeField = new TextField(); // Input for size
    private final TextField creditField = new TextField(); // Input for mobile credit
    private final TextField memoryField = new TextField(); // Input for MP3 memory
    private final TextField phoneField = new TextField(); // Input for phone number
    private final TextField durationField = new TextField(); // Input for call duration
    private final TextField downloadField = new TextField(); // Input for download size
    private final TextField displayField = new TextField(); // Input for index number

    private final TextArea logArea = new TextArea(); // Area to display output logs

    private final ArrayList<Gadget> gadgets = new ArrayList<>(); // Stores all gadgets

    @Override
    public void start(Stage stage) { // Entry point for JavaFX GUI

        GridPane grid = new GridPane(); // Layout grid
        grid.setHgap(10); // Horizontal spacing
        grid.setVgap(10); // Vertical spacing
        grid.setPadding(new Insets(10)); // Padding around grid

        // =========================
        // Labels and fields
        // =========================
        grid.add(new Label("Model:"), 0, 0); // Label for model
        grid.add(modelField, 1, 0); // Text field for model

        grid.add(new Label("Price:"), 0, 1);
        grid.add(priceField, 1, 1);

        grid.add(new Label("Weight:"), 0, 2);
        grid.add(weightField, 1, 2);

        grid.add(new Label("Size:"), 0, 3);
        grid.add(sizeField, 1, 3);

        grid.add(new Label("Credit:"), 0, 4);
        grid.add(creditField, 1, 4);

        grid.add(new Label("Memory:"), 0, 5);
        grid.add(memoryField, 1, 5);

        grid.add(new Label("Phone Number:"), 0, 6);
        grid.add(phoneField, 1, 6);

        grid.add(new Label("Duration:"), 0, 7);
        grid.add(durationField, 1, 7);

        grid.add(new Label("Download Size:"), 0, 8);
        grid.add(downloadField, 1, 8);

        grid.add(new Label("Display Number:"), 0, 9);
        grid.add(displayField, 1, 9);

        // =========================
        // Buttons
        // =========================
        Button addMobileBtn = new Button("Add Mobile"); // Button to add mobile
        Button addMP3Btn = new Button("Add MP3"); // Button to add MP3
        Button displayBtn = new Button("Display All"); // Button to display all gadgets
        Button clearBtn = new Button("Clear"); // Button to clear inputs
        Button callBtn = new Button("Make A Call"); // Button to make call
        Button downloadBtn = new Button("Download Music"); // Button to download music

        // Place buttons in horizontal box
        HBox buttons = new HBox(10, addMobileBtn, addMP3Btn, displayBtn,
                clearBtn, callBtn, downloadBtn);
        grid.add(buttons, 0, 10, 2, 1); // Span across 2 columns

        // Log output area
        logArea.setPrefHeight(200); // Set height
        grid.add(logArea, 0, 11, 2, 1); // Add to grid

        // =========================
        // Button actions
        // =========================
        addMobileBtn.setOnAction(e -> addMobile()); // Calls addMobile method
        addMP3Btn.setOnAction(e -> addMP3()); // Calls addMP3 method
        displayBtn.setOnAction(e -> displayAll()); // Calls displayAll method
        clearBtn.setOnAction(e -> clearFields()); // Clears fields
        callBtn.setOnAction(e -> makeCall()); // Calls makeCall method
        downloadBtn.setOnAction(e -> downloadMusic()); // Calls downloadMusic method

        // Set scene and show window
        stage.setScene(new Scene(grid, 650, 650));
        stage.setTitle("Gadget Shop"); // Window title
        stage.show(); // Display window
    }

    // =========================
    // INPUT METHODS (with parsing)
    // =========================

    private double getPrice() throws Exception {
        return Double.parseDouble(priceField.getText()); // Convert price to double
    }

    private int getWeight() throws Exception {
        return Integer.parseInt(weightField.getText()); // Convert weight to int
    }

    private int getCredit() throws Exception {
        return Integer.parseInt(creditField.getText()); // Convert credit to int
    }

    private int getMemory() throws Exception {
        return Integer.parseInt(memoryField.getText()); // Convert memory to int
    }

    private int getDuration() throws Exception {
        return Integer.parseInt(durationField.getText()); // Convert duration to int
    }

    private int getDownloadSize() throws Exception {
        return Integer.parseInt(downloadField.getText()); // Convert download size
    }

    private int getDisplayNumber() {
        try {
            int index = Integer.parseInt(displayField.getText()); // Convert to integer

            // Check if index is within range
            if (index < 0 || index >= gadgets.size()) {
                showError("Display number out of range"); // Show error
                return -1; // Invalid
            }

            return index; // Valid index

        } catch (Exception e) {
            showError("Display number must be an integer"); // Error message
            return -1; // Invalid
        }
    }

    // =========================
    // BUTTON METHODS
    // =========================

    private void addMobile() {
        try {
            // Create Mobile object using user inputs
            Mobile m = new Mobile(
                    modelField.getText(),
                    getPrice(),
                    getWeight(),
                    sizeField.getText(),
                    getCredit()
            );

            gadgets.add(m); // Add to list
            log("Mobile added"); // Log message

        } catch (Exception e) {
            showError("Invalid Mobile input"); // Error popup
        }
    }

    private void addMP3() {
        try {
            // Create MP3 object
            MP3 mp3 = new MP3(
                    modelField.getText(),
                    getPrice(),
                    getWeight(),
                    sizeField.getText(),
                    getMemory()
            );

            gadgets.add(mp3); // Add to list
            log("MP3 added"); // Log message

        } catch (Exception e) {
            showError("Invalid MP3 input"); // Error popup
        }
    }

    private void displayAll() {
        logArea.clear(); // Clear previous logs

        // Loop through all gadgets
        for (int i = 0; i < gadgets.size(); i++) {
            String text = i + ": " + gadgets.get(i).display(); // Format output
            log(text); // Display in GUI
            System.out.println(text); // Display in terminal
        }
    }

    private void makeCall() {
        int index = getDisplayNumber(); // Get index
        if (index == -1) return; // Stop if invalid

        // Check if selected gadget is Mobile
        if (gadgets.get(index) instanceof Mobile) {
            try {
                Mobile m = (Mobile) gadgets.get(index); // Cast to Mobile

                // Call method
                String result = m.makeCall(
                        phoneField.getText(),
                        getDuration()
                );

                log(result); // Display result

            } catch (Exception e) {
                showError("Invalid call input"); // Error
            }

        } else {
            showError("Selected gadget is not a Mobile"); // Wrong type
        }
    }

    private void downloadMusic() {
        int index = getDisplayNumber(); // Get index
        if (index == -1) return; // Stop if invalid

        // Check if gadget is MP3
        if (gadgets.get(index) instanceof MP3) {
            try {
                MP3 mp3 = (MP3) gadgets.get(index); // Cast to MP3

                // Download music
                String result = mp3.downloadMusic(getDownloadSize());

                log(result); // Display result

            } catch (Exception e) {
                showError("Invalid download size"); // Error
            }

        } else {
            showError("Selected gadget is not an MP3"); // Wrong type
        }
    }

    private void clearFields() {
        // Clear all text fields
        modelField.clear();
        priceField.clear();
        weightField.clear();
        sizeField.clear();
        creditField.clear();
        memoryField.clear();
        phoneField.clear();
        durationField.clear();
        downloadField.clear();
        displayField.clear();
    }

    // =========================
    // HELPER METHODS
    // =========================

    private void log(String text) {
        logArea.appendText(text + "\n"); // Add text to log area
    }

    private void showError(String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR); // Create error popup
        alert.setContentText(msg); // Set message
        alert.showAndWait(); // Display popup
    }

    public static void main(String[] args) {
        launch(); // Launch JavaFX application
    }
}