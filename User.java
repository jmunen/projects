
import java.time.*;
import java.util.*;

/**
 * Represents a user in the appointment system with their own appointment diary.
 */
public class User {
    private String username;
    private List<Appointment> appointments;
    private static final LocalDate MAX_DATE = LocalDate.of(2026, 12, 31);
    
    /**
     * Creates a new user with the specified username.
     * @param username The username for this user
     */
    public User(String username) {
        this.username = username;
        this.appointments = new ArrayList<>();
    }
    
    public String getUsername() { 
        return username; 
    }
    
    /**
     * Schedules a new appointment for this user.
     * @param date The date of the appointment
     * @param startTime The start time
     * @param endTime The end time
     * @param purpose The purpose of the appointment
     * @return true if successfully scheduled, false otherwise
     */
    public boolean scheduleAppointment(LocalDate date, LocalTime startTime, 
                                     LocalTime endTime, String purpose) {
        // Validate date
        if (!isValidDate(date)) {
            return false;
        }
        
        // Check for time validity
        if (startTime.isAfter(endTime) || startTime.equals(endTime)) {
            return false;
        }
        
        // Check for conflicts
        if (hasConflict(date, startTime, endTime)) {
            return false;
        }
        
        appointments.add(new Appointment(date, startTime, endTime, purpose));
        sortAppointments();
        return true;
    }
    
    /**
     * Cancels an appointment at the specified date and time.
     * @param date The date of the appointment
     * @param startTime The start time of the appointment
     * @return true if cancelled successfully, false if not found
     */
    public boolean cancelAppointment(LocalDate date, LocalTime startTime) {
        return appointments.removeIf(apt -> 
            apt.getDate().equals(date) && apt.getStartTime().equals(startTime));
    }
    
    /**
     * Finds an appointment at the given date and time.
     * @param date The date to check
     * @param time The time to check
     * @return The appointment if found, null otherwise
     */
    public Appointment findAppointment(LocalDate date, LocalTime time) {
        for (Appointment apt : appointments) {
            if (apt.getDate().equals(date) && apt.containsTime(time)) {
                return apt;
            }
        }
        return null;
    }
    
    /**
     * Gets an appointment by its exact start date and time.
     * @param date The date of the appointment
     * @param startTime The exact start time
     * @return The appointment if found, null otherwise
     */
    public Appointment getAppointmentByStart(LocalDate date, LocalTime startTime) {
        for (Appointment apt : appointments) {
            if (apt.getDate().equals(date) && apt.getStartTime().equals(startTime)) {
                return apt;
            }
        }
        return null;
    }
    
    /**
     * Reschedules an existing appointment to a new date/time.
     * @param oldDate Current date of the appointment
     * @param oldStartTime Current start time
     * @param newDate New date
     * @param newStartTime New start time
     * @param newEndTime New end time
     * @return true if rescheduled successfully, false otherwise
     */
    public boolean rescheduleAppointment(LocalDate oldDate, LocalTime oldStartTime,
                                       LocalDate newDate, LocalTime newStartTime, 
                                       LocalTime newEndTime) {
        Appointment apt = getAppointmentByStart(oldDate, oldStartTime);
        if (apt == null) {
            return false;
        }
        
        String purpose = apt.getPurpose();
        
        // Remove old appointment
        if (!cancelAppointment(oldDate, oldStartTime)) {
            return false;
        }
        
        // Try to schedule new appointment
        if (scheduleAppointment(newDate, newStartTime, newEndTime, purpose)) {
            return true;
        } else {
            // Restore old appointment if new scheduling fails
            scheduleAppointment(oldDate, apt.getStartTime(), apt.getEndTime(), purpose);
            return false;
        }
    }
    
    /**
     * Gets all appointments for this user.
     * @return List of appointments (copy)
     */
    public List<Appointment> getAppointments() {
        return new ArrayList<>(appointments);
    }
    
    /**
     * Gets appointments for a specific date.
     * @param date The date to filter by
     * @return List of appointments on that date
     */
    public List<Appointment> getAppointmentsByDate(LocalDate date) {
        List<Appointment> result = new ArrayList<>();
        for (Appointment apt : appointments) {
            if (apt.getDate().equals(date)) {
                result.add(apt);
            }
        }
        return result;
    }
    
    // Private helper methods
    private boolean isValidDate(LocalDate date) {
        LocalDate today = LocalDate.now();
        return !date.isBefore(today) && !date.isAfter(MAX_DATE);
    }
    
    private boolean hasConflict(LocalDate date, LocalTime startTime, LocalTime endTime) {
        for (Appointment apt : appointments) {
            if (apt.getDate().equals(date) && apt.conflictsWith(startTime, endTime)) {
                return true;
            }
        }
        return false;
    }
    
    private void sortAppointments() {
        appointments.sort((a1, a2) -> {
            int dateCompare = a1.getDate().compareTo(a2.getDate());
            if (dateCompare != 0) return dateCompare;
            return a1.getStartTime().compareTo(a2.getStartTime());
        });
    }
}