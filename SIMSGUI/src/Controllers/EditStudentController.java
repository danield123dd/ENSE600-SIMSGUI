/**
 * EditStudentController Class
 * A Controller which facilitates user interactions with the EditStudentWindow and Database
 * Project 2: SIMS GUI Project
 * @author Daniel Dymond (Group 1 - ID# 17977610) 2020
 */
package Controllers;

import Models.Address;
import Models.Gender;
import Models.Student;
import Views.EditStudentWindow;
import Views.StudentDetailPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.LocalDate;
import Views.SessionWindow;
import java.time.format.DateTimeParseException;

public class EditStudentController {
    
    StudentManagementSession sMS;
    SessionWindow sW;
    EditStudentWindow editStudentWindow;
    EditStudentWindowActionListener editStudentWindowActionListener;
    Student student;
    
    /**
     * Create a new EditStudentController and spawn a new EditStudentWindow
     * @param sMS StudentManagementSession which contains the Student to edit
     * @param sW SessionWindow which contains the desktop to add views to
     */
    public EditStudentController(StudentManagementSession sMS, SessionWindow sW)
    {
        this.sMS = sMS;
        this.sW = sW;
        this.student = sMS.student;
        spawnWindow();
    }
    
    /**
     * Generates and Displays the EditStudentWindow
     */
    private void spawnWindow() 
    {
        // Create a new Action Listener
        editStudentWindowActionListener = new EditStudentWindowActionListener();
        
        // Create the Edit Student Window, and set the Title to reflect the active Student
        editStudentWindow = new EditStudentWindow(editStudentWindowActionListener);
        editStudentWindow.setTitle("Student Details - \"" + student.getFullName() + "\"");
        
        // Update the details on the window
        updateWindowDetails();
        
        // Add the window to the desktop and set visible
        sW.getDesktop().add(editStudentWindow);
        editStudentWindow.setVisible(true);
    }
    
    /**
     * Removes the view from the desktop, and disposes it.
     */
    private void destroyWindow()
    {
        this.editStudentWindow.setVisible(false);
        sW.getDesktop().remove(this.editStudentWindow);
        this.editStudentWindow.dispose();
        this.editStudentWindow = null;
    }
    
    /**
     * Updates the View with the current details from the Student Model
     */
    private void updateWindowDetails()
    {
        StudentDetailPanel sDP = editStudentWindow.studentDetailsPanel;
        sDP.firstNameField.setText(student.getFirstName());
        sDP.lastNameField.setText(student.getLastName());
        sDP.dateOfBirthField.setText(student.getDateOfBirth().toString());
        sDP.emailAddressField.setText(student.getEmail());
        sDP.phoneNumberField.setText(student.getPhoneNumber());
        sDP.genderComboField.setSelectedItem(student.getGender().toString());
        sDP.streetAddressField.setText(student.getAddress().getStreetAddress());
        sDP.suburbAddressField.setText(student.getAddress().getSuburb());
        sDP.cityAddressField.setText(student.getAddress().getTownCity());
        sDP.countryAddressField.setText(student.getAddress().getCountry());
        sDP.zipAddressField.setText(student.getAddress().getZip());
        sDP.studentIDField.setText(student.getStudentID());
    }
    
    /**
     * Uses information from the View to update the details of the Student Model
     * @return True if all parameters of the Student Object can be updated
     * @throws IllegalArgumentException If the Student object refuses the details entered
     */
    private boolean updateStudentModel() throws IllegalArgumentException
    {
        // Extrapolate the details from the View
        StudentDetailPanel sDP = editStudentWindow.studentDetailsPanel;
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
        
        // Now, update the Student Object and return true if successful
        // If there are any issues, the exception is thrown to the calling method
        student.setFirstName(firstName);
        student.setLastName(lastName);
        student.setDateOfBirth(dateOfBirth);
        student.setGender(gender);
        student.setAddress(address);
        student.setEmail(email);
        student.setPhoneNumber(phone);
        return true;
    }
    
    /**
     * Update the Database with the changes, and if successful, close the EditStudentWindow
     */
    private void updateAndClose()
    {
        if (updateDatabase())
            destroyWindow(); 
    }
    
    /**
     * Updates the Student Model in the StudentManagementSession using details from the view,
     * and then attempts to push changes to the Database
     * @return True if successful, or ExceptionMessageWindow is generated and displayed.
     */
    private boolean updateDatabase()
    {
        // Try to Update the Model, showing an Error Message if not successful
        try {
            updateStudentModel();
        } catch (IllegalArgumentException iae) {
            sW.showExceptionMessage(iae.getMessage());
            return false;
        }
        
        // If successful, write the changes back to the database
        try {
            sMS.dBA.updateStudent(student);
        } catch (SQLException sqle) {
            sW.showExceptionMessage(sqle.getMessage());
            return false;
        }
        
        // If successful, show a confirmation and return true
        sW.showInformationMessage("The Student " + student.getFullName() + " has been updated.");
        return true;
    }
    
    /**
     * Action Listener Class used for Student Search Windows
     */
    public class EditStudentWindowActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) 
        {
            // If the Cancel Button is pressed, close the window
            if (e.getSource().equals(editStudentWindow.cancelButton))
                destroyWindow();
            
            // Else If the Save Button is pressed, save the current data
            else if (e.getSource().equals(editStudentWindow.saveButton))
                updateDatabase();
            
            // Else If the Save and Close Button is pressed, save the data and close the window
            else if (e.getSource().equals(editStudentWindow.saveAndCloseButton))
                updateAndClose();
        }
    
    }
    
    /**
     * Returns the state of the Window
     * @return True if the window exists & is visible
     */
    public boolean isVisible()
    {
        if (editStudentWindow != null)
            return editStudentWindow.isVisible();
        else
            return false;
    }
    
}
