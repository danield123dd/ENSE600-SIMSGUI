/**
 * NewStudentController Class
 * A Controller which facilitates user interactions with the NewStudentWindow and Database
 * Project 2: SIMS GUI Project
 * @author Daniel Dymond (Group 1 - ID# 17977610) 2020
 */
package Controllers;

import Models.Address;
import Models.Gender;
import Models.Student;
import Views.NewStudentWindow;
import Views.SessionWindow;
import Views.StudentDetailPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class NewStudentController {
    
    private final StudentManagementSession sMS;
    private final SessionWindow sW;
    private NewStudentWindow newStudentWindow;
    private NewStudentActionListener newStudentActionListener;
    private static DatabaseAgent dBA;
    private Student student;
    
    /**
     * Create a NewStudentController and spawn a Window on the Desktop
     * @param sMS StudentManagementSession which contains the Student Object
     */
    public NewStudentController (StudentManagementSession sMS)
    {
        // Set Class Variables
        this.sMS = sMS;
        this.sW = sMS.sessionWindow;
        this.dBA = sMS.dBA;
        this.student = sMS.student;
        
        // Create Window
        spawnWindow();
    }
    
    /**
     * Create and display a NewStudentWindow on the Desktop
     */
    private void spawnWindow()
    {
        // Create the Action Listener
        newStudentActionListener = new NewStudentActionListener();
        
        // Create the Student Creation Window
        newStudentWindow = new NewStudentWindow(newStudentActionListener);
        
        // Set the Student ID Field to a Filler Value
        newStudentWindow.studentDetailsPanel.studentIDField.setText("##########");
        
        // Finally, add the window to the desktop manager and set visible
        sW.getDesktop().add(newStudentWindow);
        newStudentWindow.setVisible(true);
    }
    
    /**
     * Removes the NewStudentWindow from the Desktop and disposes it
     */
    private void destroyWindow()
    {
        this.newStudentWindow.setVisible(false);
        sW.getDesktop().remove(this.newStudentWindow);
        this.newStudentWindow.dispose();
        this.newStudentWindow = null;
    }
    
    /**
     * Uses information from the view to create a new Student Object
     * @return True if the Student can be created
     * @throws IllegalArgumentException If the Student object refuses the details entered
     */
    private boolean createStudentModel() throws IllegalArgumentException
    {
        // Extrapolate the details from the View
        StudentDetailPanel sDP = newStudentWindow.studentDetailsPanel;
        String firstName = sDP.firstNameField.getText();
        String lastName = sDP.lastNameField.getText();
        Gender gender = Gender.valueOf(sDP.genderComboField.getSelectedItem().toString().toUpperCase());
        Address address = new Address(sDP.streetAddressField.getText(), sDP.suburbAddressField.getText(), 
                sDP.cityAddressField.getText(), sDP.countryAddressField.getText(), sDP.zipAddressField.getText());
        String email = sDP.emailAddressField.getText();
        String phone = sDP.phoneNumberField.getText();
        
        // Attempt to parse a dateOfBirth - Show an error if this is not possible
        LocalDate dateOfBirth;
        try {
            dateOfBirth = LocalDate.parse(sDP.dateOfBirthField.getText());
        } catch (DateTimeParseException dtpe) {
            throw new IllegalArgumentException("The Date of Birth entered is not valid. Please enter the date of birth in YYYY-MM-DD format.");
        }
        
        // Generate a new Student ID from the Database
        String studentID = dBA.generateNewStudentID();
        
        // Create a new Student - This will either throw an exception, or this method will return true
        student = new Student(firstName, lastName, studentID, dateOfBirth, gender, address, email, phone);  
        return true;
    }
    
    /**
     * Requests the Database to push changes to the student object
     * @return True if the Database Operation completes successfully, otherwise false with exceptionDialog showing.
     */
    private boolean updateDatabase()
    {
        // Try to Update the Model, showing an Error Message if not successful
        try {
            createStudentModel();
        } catch (IllegalArgumentException iae) {
            sW.showExceptionMessage(iae.getMessage());
            return false;
        }
        
        // If successful, write the changes back to the database
        try {
            sMS.dBA.createStudent(student);
        } catch (SQLException sqle) {
            sW.showExceptionMessage(sqle.getMessage());
            return false;
        }
        
        // If successful, show Information Message and return true
        sW.showInformationMessage("The Student " + student.getFullName() + " has been created with Student ID: " + student.getStudentID() + ".");
        return true;
    }
    
    /**
     * Performs the Update and Close Operation of the NewStudentWindow
     */
    private void updateAndClose()
    {
        // If the Database is updated with the new student, destroy this window
        if (updateDatabase())
            destroyWindow(); 
    }
    
    /**
     * ActionListener used to listen to events from a NewStudentWindow
     */
    public class NewStudentActionListener implements ActionListener 
    {
        @Override
        public void actionPerformed(ActionEvent e) 
        {
            // If the Update and Close Button is pressed, perform an Update and Close operation
            if (e.getSource().equals(newStudentWindow.enrollStudentButton))
                updateAndClose();

            // Otherwise, if the Close Button is pressed, close the window
            else if (e.getSource().equals(newStudentWindow.cancelButton))
                destroyWindow();
        } 
    }
}
