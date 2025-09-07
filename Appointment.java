import java.time.*;
import java.time.format.*;

/**
 * Represents an individual appointment with date, time, and purpose.
 */
public class Appointment {
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private String purpose;
    
    /**
     * Creates a new appointment.
     * @param date The date of the appointment
     * @param startTime The start time of the appointment
     * @param endTime The end time of the appointment
     * @param purpose The purpose/description of the appointment
     */
    public Appointment(LocalDate date, LocalTime startTime, LocalTime endTime, String purpose) {
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.purpose = purpose;
    }
    
    // Getters
    public LocalDate getDate() { 
        return date; 
    }
    
    public LocalTime getStartTime() { 
        return startTime; 
    }
    
    public LocalTime getEndTime() { 
        return endTime; 
    }
    
    public String getPurpose() { 
        return purpose; 
    }
    
    // Setters for rescheduling
    public void setDate(LocalDate date) { 
        this.date = date; 
    }
    
    public void setStartTime(LocalTime startTime) { 
        this.startTime = startTime; 
    }
    
    public void setEndTime(LocalTime endTime) { 
        this.endTime = endTime; 
    }
    
    /**
     * Checks if this appointment conflicts with the given time interval.
     * @param otherStart Start time to check
     * @param otherEnd End time to check
     * @return true if there's a conflict, false otherwise
     */
    public boolean conflictsWith(LocalTime otherStart, LocalTime otherEnd) {
        return !(endTime.isBefore(otherStart) || endTime.equals(otherStart) || 
                 startTime.isAfter(otherEnd) || startTime.equals(otherEnd));
    }
    
    /**
     * Checks if the given time falls within this appointment.
     * @param time The time to check
     * @return true if the time is within the appointment, false otherwise
     */
    public boolean containsTime(LocalTime time) {
        return !time.isBefore(startTime) && time.isBefore(endTime);
    }
    
    @Override
    public String toString() {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("h:mm a");
        return String.format("%s from %s to %s - %s", 
            date, 
            startTime.format(timeFormatter), 
            endTime.format(timeFormatter), 
            purpose);
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        
        Appointment that = (Appointment) obj;
        return date.equals(that.date) && 
               startTime.equals(that.startTime) && 
               endTime.equals(that.endTime);
    }
    
    @Override
    public int hashCode() {
        return date.hashCode() * 31 + startTime.hashCode();
    }
}