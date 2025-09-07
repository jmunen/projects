import java.util.*;
import java.time.*;

/**
 * Handles all console input/output operations for the appointment system.
 */
public class ConsoleUI {
    private Scanner scanner;
    private UserManager userManager;
    
    public ConsoleUI() {
        this.scanner = new Scanner(System.in);
        this.userManager = new UserManager();
    }
    
    /**
     * Displays the main menu.
     */
    public void displayMenu() {
        System.out.println("\nWelcome to Appointment Management System! What would you like to do?");
        System.out.println("• [a] Add new user");
        System.out.println("• [d] Delete an existing user");
        System.out.println("• [l] List existing users");
        System.out.println("• [s] Schedule an appointment");
        System.out.println("• [c] Cancel an appointment");
        System.out.println("• [f] Check for appointment on certain date and time");
        System.out.println("• [p] Retrieve purpose of an appointment");
        System.out.println("• [r] Reschedule an existing appointment");
        System.out.println("• [x] Exit the system");
        System.out.print("Enter choice: ");
    }
    
    /**
     * Gets user input.
     * @param prompt The prompt to display
     * @return The user's input
     */
    public String getInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }
    
    /**
     * Displays a message to the user.
     * @param message The message to display
     */
    public void displayMessage(String message) {
        System.out.println(message);
    }
    
    /**
     * Handles adding a new user.
     */
    public void handleAddUser() {
        String username = getInput("Enter username: ");
        
        if (userManager.addUser(username)) {
            displayMessage("User added successfully!");
        } else {
            displayMessage("User already exists or invalid username!");
        }
    }
    
    /**
     * Handles deleting a user.
     */
    public void handleDeleteUser() {
        String username = getInput("Enter username: ");
        
        if (userManager.deleteUser(username)) {
            displayMessage("User deleted successfully!");
        } else {
            displayMessage("User not found!");
        }
    }
    
    /**
     * Handles listing all users.
     */
    public void handleListUsers() {
        if (!userManager.hasUsers()) {
            displayMessage("No users in the system.");
            return;
        }
        
        displayMessage("Existing users:");
        for (String username : userManager.getAllUsernames()) {
            displayMessage("• " + username);
        }
    }
    
    /**
     * Handles scheduling an appointment.
     */
    public void handleScheduleAppointment() {
        String username = getInput("Enter username: ");
        User user = userManager.getUser(username);
        
        if (user == null) {
            displayMessage("User not found!");
            return;
        }
        
        String dateStr = getInput("Enter date (YYYY-MM-DD): ");
        LocalDate date = DateTimeUtils.parseDate(dateStr);
        if (date == null) {
            displayMessage("Invalid date format!");
            return;
        }
        
        String startTimeStr = getInput("Enter start time (H:MM AM/PM): ");
        LocalTime startTime = DateTimeUtils.parseTime(startTimeStr);
        if (startTime == null) {
            displayMessage("Invalid time format!");
            return;
        }
        
        String endTimeStr = getInput("Enter end time (H:MM AM/PM): ");
        LocalTime endTime = DateTimeUtils.parseTime(endTimeStr);
        if (endTime == null) {
            displayMessage("Invalid time format!");
            return;
        }
        
        if (!DateTimeUtils.isValidTimeInterval(startTime, endTime)) {
            displayMessage("Invalid time interval!");
            return;
        }
        
        String purpose = getInput("Enter purpose: ");
        
        if (user.scheduleAppointment(date, startTime, endTime, purpose)) {
            displayMessage("Appointment scheduled successfully!");
        } else {
            displayMessage("Cannot schedule appointment. Time slot may be taken or date is invalid.");
        }
    }
    
    /**
     * Handles cancelling an appointment.
     */
    public void handleCancelAppointment() {
        String username = getInput("Enter username: ");
        User user = userManager.getUser(username);
        
        if (user == null) {
            displayMessage("User not found!");
            return;
        }
        
        String dateStr = getInput("Enter date (YYYY-MM-DD): ");
        LocalDate date = DateTimeUtils.parseDate(dateStr);
        if (date == null) {
            displayMessage("Invalid date format!");
            return;
        }
        
        String startTimeStr = getInput("Enter start time (H:MM AM/PM): ");
        LocalTime startTime = DateTimeUtils.parseTime(startTimeStr);
        if (startTime == null) {
            displayMessage("Invalid time format!");
            return;
        }
        
        if (user.cancelAppointment(date, startTime)) {
            displayMessage("Appointment cancelled successfully!");
        } else {
            displayMessage("No appointment found at the specified time!");
        }
    }
    
    /**
     * Handles checking for an appointment.
     */
    public void handleCheckAppointment() {
        String username = getInput("Enter username: ");
        User user = userManager.getUser(username);
        
        if (user == null) {
            displayMessage("User not found!");
            return;
        }
        
        String dateStr = getInput("Enter date: ");
        LocalDate date = DateTimeUtils.parseDate(dateStr);
        if (date == null) {
            displayMessage("Invalid date format!");
            return;
        }
        
        String timeStr = getInput("Enter start time: ");
        LocalTime time = DateTimeUtils.parseTime(timeStr);
        if (time == null) {
            displayMessage("Invalid time format!");
            return;
        }
        
        Appointment apt = user.findAppointment(date, time);
        if (apt != null) {
            String message = String.format("Appointment found on %s between %s to %s.",
                DateTimeUtils.formatDate(apt.getDate()),
                DateTimeUtils.formatTime(apt.getStartTime()),
                DateTimeUtils.formatTime(apt.getEndTime()));
            displayMessage(message);
        } else {
            displayMessage("No appointment found!");
        }
    }
    
    /**
     * Handles retrieving appointment purpose.
     */
    public void handleRetrievePurpose() {
        String username = getInput("Enter username: ");
        User user = userManager.getUser(username);
        
        if (user == null) {
            displayMessage("User not found!");
            return;
        }
        
        String dateStr = getInput("Enter date (YYYY-MM-DD): ");
        LocalDate date = DateTimeUtils.parseDate(dateStr);
        if (date == null) {
            displayMessage("Invalid date format!");
            return;
        }
        
        String startTimeStr = getInput("Enter start time (H:MM AM/PM): ");
        LocalTime startTime = DateTimeUtils.parseTime(startTimeStr);
        if (startTime == null) {
            displayMessage("Invalid time format!");
            return;
        }
        
        Appointment apt = user.getAppointmentByStart(date, startTime);
        if (apt != null) {
            displayMessage("Purpose: " + apt.getPurpose());
        } else {
            displayMessage("No appointment found at the specified time!");
        }
    }
    
    /**
     * Handles rescheduling an appointment.
     */
    public void handleRescheduleAppointment() {
        String username = getInput("Enter username: ");
        User user = userManager.getUser(username);
        
        if (user == null) {
            displayMessage("User not found!");
            return;
        }
        
        // Get current appointment details
        String oldDateStr = getInput("Enter current date (YYYY-MM-DD): ");
        LocalDate oldDate = DateTimeUtils.parseDate(oldDateStr);
        if (oldDate == null) {
            displayMessage("Invalid date format!");
            return;
        }
        
        String oldStartTimeStr = getInput("Enter current start time (H:MM AM/PM): ");
        LocalTime oldStartTime = DateTimeUtils.parseTime(oldStartTimeStr);
        if (oldStartTime == null) {
            displayMessage("Invalid time format!");
            return;
        }
        
        // Check if appointment exists
        if (user.getAppointmentByStart(oldDate, oldStartTime) == null) {
            displayMessage("No appointment found at the specified time!");
            return;
        }
        
        // Get new appointment details
        String newDateStr = getInput("Enter new date (YYYY-MM-DD): ");
        LocalDate newDate = DateTimeUtils.parseDate(newDateStr);
        if (newDate == null) {
            displayMessage("Invalid date format!");
            return;
        }
        
        String newStartTimeStr = getInput("Enter new start time (H:MM AM/PM): ");
        LocalTime newStartTime = DateTimeUtils.parseTime(newStartTimeStr);
        if (newStartTime == null) {
            displayMessage("Invalid time format!");
            return;
        }
        
        String newEndTimeStr = getInput("Enter new end time (H:MM AM/PM): ");
        LocalTime newEndTime = DateTimeUtils.parseTime(newEndTimeStr);
        if (newEndTime == null) {
            displayMessage("Invalid time format!");
            return;
        }
        
        if (!DateTimeUtils.isValidTimeInterval(newStartTime, newEndTime)) {
            displayMessage("Invalid time interval!");
            return;
        }
        
        if (user.rescheduleAppointment(oldDate, oldStartTime, newDate, newStartTime, newEndTime)) {
            displayMessage("Appointment rescheduled successfully!");
        } else {
            displayMessage("Cannot reschedule. Time slot may be taken or date is invalid.");
        }
    }
    
    /**
     * Closes the scanner.
     */
    public void close() {
        scanner.close();
    }
}