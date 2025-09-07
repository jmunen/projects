/**
 * Main application class for the Appointment Management System.
 * This class serves as the entry point and main controller for the application.
 */
public class AppointmentManagementSystem {
    private ConsoleUI ui;
    private boolean running;
    
    public AppointmentManagementSystem() {
        this.ui = new ConsoleUI();
        this.running = true;
    }
    
    /**
     * Processes the user's menu choice.
     * @param choice The user's menu selection
     */
    private void processChoice(String choice) {
        switch (choice.toLowerCase()) {
            case "a":
                ui.handleAddUser();
                break;
            case "d":
                ui.handleDeleteUser();
                break;
            case "l":
                ui.handleListUsers();
                break;
            case "s":
                ui.handleScheduleAppointment();
                break;
            case "c":
                ui.handleCancelAppointment();
                break;
            case "f":
                ui.handleCheckAppointment();
                break;
            case "p":
                ui.handleRetrievePurpose();
                break;
            case "r":
                ui.handleRescheduleAppointment();
                break;
            case "x":
                running = false;
                ui.displayMessage("Goodbye!");
                break;
            default:
                ui.displayMessage("Invalid Option");
        }
    }
    
    /**
     * Main run loop for the application.
     */
    public void run() {
        while (running) {
            ui.displayMenu();
            String choice = ui.getInput("");
            processChoice(choice);
        }
        
        ui.close();
    }
    
    /**
     * Main entry point for the application.
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        AppointmentManagementSystem system = new AppointmentManagementSystem();
        system.run();
    }
}