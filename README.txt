# Appointment Management System - README

## System Requirements
- Java Development Kit (JDK) 8 or higher
- Command line interface (Terminal/Command Prompt)

## Files Included
- AppointmentManagementSystem.java - Main source code file containing all classes

## Compilation Instructions
1. Open a terminal/command prompt
2. Navigate to the directory containing AppointmentManagementSystem.java
3. Compile the program using the following command:
   ```
   javac AppointmentManagementSystem.java
   ```
   This will generate the necessary .class files

## Execution Instructions
1. After successful compilation, run the program using:
   ```
   java AppointmentManagementSystem
   ```
2. The program will start and display the welcome menu

## Program Features
The system supports the following operations:
- [a] Add new user - Creates a new user account
- [d] Delete an existing user - Removes a user and all their appointments
- [l] List existing users - Displays all registered users
- [s] Schedule an appointment - Books a new appointment for a user
- [c] Cancel an appointment - Removes an existing appointment
- [f] Check for appointment - Verifies if there's an appointment at a given time
- [p] Retrieve purpose - Gets the purpose/description of an appointment
- [r] Reschedule - Moves an appointment to a new date/time
- [x] Exit - Terminates the program

## Date and Time Format
- Date format: YYYY-MM-DD (e.g., 2023-04-16)
- Time format: H:MM AM/PM (e.g., 9:30 AM, 5:25 PM)

## Important Notes
1. The system manages appointments from the current date until December 31, 2026
2. Appointments cannot be scheduled for past dates
3. Overlapping appointments for the same user are not allowed
4. All input is case-insensitive for menu options
5. Time input accepts both uppercase and lowercase AM/PM

## Example Usage
```
Welcome to Appointment Management System! What would you like to do?
• [a] Add new user
• [d] Delete an existing user
• [l] List existing users
• [s] Schedule an appointment
• [c] Cancel an appointment
• [f] Check for appointment on certain date and time
• [p] Retrieve purpose of an appointment
• [r] Reschedule an existing appointment
• [x] Exit the system
Enter choice: a
Enter username: neu
User added successfully!

Welcome to Appointment Management System! What would you like to do?
[menu repeats]
Enter choice: s
Enter username: neu
Enter date (YYYY-MM-DD): 2023-04-16
Enter start time (H:MM AM/PM): 9:30 AM
Enter end time (H:MM AM/PM): 10:10 AM
Enter purpose: Team Meeting
Appointment scheduled successfully!
```

## System Design
The program uses a modular object-oriented approach with six main classes:

### Core Classes:
1. **Appointment**: Represents individual appointments with date, time, and purpose
   - Handles appointment data and time conflict detection
   
2. **User**: Manages appointments for each user
   - Handles appointment scheduling, cancellation, and retrieval
   - Validates dates and prevents scheduling conflicts
   
3. **UserManager**: Manages all users in the system
   - Handles user creation, deletion, and retrieval
   - Maintains the user database

### Utility Classes:
4. **DateTimeUtils**: Provides date and time parsing/formatting utilities
   - Standardizes date/time handling across the system
   - Validates input formats

5. **ConsoleUI**: Handles all console input/output operations
   - Manages user interaction and menu display
   - Delegates business logic to appropriate classes

6. **AppointmentManagementSystem**: Main application controller
   - Entry point for the program
   - Coordinates the main application loop

### Design Benefits:
- **Separation of Concerns**: Each class has a single, well-defined responsibility
- **Reusability**: Classes can be easily reused or extended
- **Maintainability**: Changes to one module don't affect others
- **Testability**: Individual classes can be tested in isolation
- **Scalability**: Easy to add new features or modify existing ones
