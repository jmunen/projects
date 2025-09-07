import java.time.*;
import java.time.format.*;

/**
 * Utility class for date and time parsing and formatting.
 */
public class DateTimeUtils {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("h:mm a");
    private static final DateTimeFormatter DISPLAY_TIME_FORMATTER = DateTimeFormatter.ofPattern("h:mm a");
    
    /**
     * Parses a date string in YYYY-MM-DD format.
     * @param dateStr The date string to parse
     * @return LocalDate object, or null if parsing fails
     */
    public static LocalDate parseDate(String dateStr) {
        try {
            return LocalDate.parse(dateStr.trim(), DATE_FORMATTER);
        } catch (DateTimeParseException e) {
            return null;
        }
    }
    
    /**
     * Parses a time string in H:MM AM/PM format.
     * @param timeStr The time string to parse
     * @return LocalTime object, or null if parsing fails
     */
    public static LocalTime parseTime(String timeStr) {
        try {
            // Convert to uppercase for AM/PM
            return LocalTime.parse(timeStr.trim().toUpperCase(), TIME_FORMATTER);
        } catch (DateTimeParseException e) {
            return null;
        }
    }
    
    /**
     * Formats a LocalDate for display.
     * @param date The date to format
     * @return Formatted date string
     */
    public static String formatDate(LocalDate date) {
        return date.format(DATE_FORMATTER);
    }
    
    /**
     * Formats a LocalTime for display.
     * @param time The time to format
     * @return Formatted time string
     */
    public static String formatTime(LocalTime time) {
        return time.format(DISPLAY_TIME_FORMATTER);
    }
    
    /**
     * Validates if a time interval is valid (start before end).
     * @param startTime Start time
     * @param endTime End time
     * @return true if valid interval, false otherwise
     */
    public static boolean isValidTimeInterval(LocalTime startTime, LocalTime endTime) {
        return startTime != null && endTime != null && startTime.isBefore(endTime);
    }
    
    /**
     * Gets the current date.
     * @return Current date
     */
    public static LocalDate getCurrentDate() {
        return LocalDate.now();
    }
    
    /**
     * Gets the maximum allowed date for appointments.
     * @return Maximum date (Dec 31, 2026)
     */
    public static LocalDate getMaxDate() {
        return LocalDate.of(2026, 12, 31);
    }
}