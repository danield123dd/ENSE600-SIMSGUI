/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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

/**
 *
 * @author danie
 */
public class EditStudentController {
    
    StudentManagementSession sMS;
    SessionWindow sW;
    EditStudentWindow editStudentWindow;
    EditStudentWindowActionListener editStudentWindowActionListener;
    Student student;
    
    
    public EditStudentController(StudentManagementSession sMS, SessionWindow sW) {
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
     * Removes the view from the desktop
     */
    private void destroyWindow()
    {
        this.editStudentWindow.setVisible(false);
        sW.getDesktop().remove(this.editStudentWindow);
        this.editStudentWindow.dispose();
        this.editStudentWindow = null;
    }
    
    /**
     * Updates the Window with the current details from the Model
     */
    private void updateWindowDetails()
    {
        // Populate the Fields for the Student Detail Panel
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
     * Updates the information of the Student Object from the Window
     * @return      True if the operation is successful
     * @throws IllegalArgumentException If the Student object refuses the details entered
     */
    private boolean updateStudentModel() throws IllegalArgumentException
    {
        // Update the Student Object with Details from exisitng fields
        StudentDetailPanel sDP = editStudentWindow.studentDetailsPanel;
        String firstName = sDP.firstNameField.getText();
        String lastName = sDP.lastNameField.getText();
        LocalDate dateOfBirth = LocalDate.parse(sDP.dateOfBirthField.getText());
        Gender gender = Gender.valueOf(sDP.genderComboField.getSelectedItem().toString().toUpperCase());
        Address address = new Address(sDP.streetAddressField.getText(), sDP.suburbAddressField.getText(), 
                sDP.cityAddressField.getText(), sDP.countryAddressField.getText(), sDP.zipAddressField.getText());
        String email = sDP.emailAddressField.getText();
        String phone = sDP.phoneNumberField.getText();
        
        // Now, update the Student Object
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
     * 
     */
    private void updateAndClose()
    {
        if (updateDatabase())
            destroyWindow(); 
    }
    
    /**
     * Requests the Database to push changes to the student object
     * @return 
     */
    private boolean updateDatabase()
    {
        // Boolean Markers to indicate Progress
        boolean modelUpdated = false;
        boolean dBUpdated = false;
        
        // Try to Update the Model, showing an Error Message if not successful
        try {
            modelUpdated = updateStudentModel();
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
        
        // If successful, return true
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
