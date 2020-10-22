/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author danie
 */
public class NewStudentController {
    
    StudentManagementSession sMS;
    SessionWindow sW;
    NewStudentWindow newStudentWindow;
    NewStudentActionListener newStudentActionListener;
    DatabaseAgent dBA;
    Student student;
    
    public NewStudentController (StudentManagementSession sMS, SessionWindow sW)
    {
        this.sW = sW;
        this.sMS = sMS;
        this.dBA = sMS.dBA;
        this.student = sMS.student;
        spawnWindow();
    }
    
    private void spawnWindow()
    {
        // Create the Action Listener
        newStudentActionListener = new NewStudentActionListener();
        
        // Create the Student Creation Window
        newStudentWindow = new NewStudentWindow(newStudentActionListener);
        
        // Populate
        newStudentWindow.studentDetailsPanel.studentIDField.setText("Will Be Populated on Entry");
        
        // Finally, add the window to the desktop manager and set visible
        sW.getDesktop().add(newStudentWindow);
        newStudentWindow.setVisible(true);
    }
    
    
    private void destroyWindow()
    {
        this.newStudentWindow.setVisible(false);
        sW.getDesktop().remove(this.newStudentWindow);
        this.newStudentWindow.dispose();
        this.newStudentWindow = null;
    }
    
    /**
     * Updates the Window with the current details from the Model
     */
    private void updateWindowDetails()
    {
        // Populate the Fields for the Student Detail Panel
        StudentDetailPanel sDP = newStudentWindow.studentDetailsPanel;
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
    private boolean createStudentModel() throws IllegalArgumentException
    {
        // Update the Student Object with Details from exisitng fields
        StudentDetailPanel sDP = newStudentWindow.studentDetailsPanel;
        String firstName = sDP.firstNameField.getText();
        String lastName = sDP.lastNameField.getText();
        LocalDate dateOfBirth;
        try {
            dateOfBirth = LocalDate.parse(sDP.dateOfBirthField.getText());
        } catch (DateTimeParseException dtpe) {
            throw new IllegalArgumentException("The Date of Birth entered is not valid. Please enter the date of birth in YYYY-MM-DD format.");
        }
        
        Gender gender = Gender.valueOf(sDP.genderComboField.getSelectedItem().toString().toUpperCase());
        Address address = new Address(sDP.streetAddressField.getText(), sDP.suburbAddressField.getText(), 
                sDP.cityAddressField.getText(), sDP.countryAddressField.getText(), sDP.zipAddressField.getText());
        String email = sDP.emailAddressField.getText();
        String phone = sDP.phoneNumberField.getText();
        
        // Now, update the Student Object
        // If there are any issues, the exception is thrown to the calling method
        
        String studentID = dBA.generateNewStudentID();
        // TODO
        
        student = new Student(firstName, lastName, studentID, dateOfBirth, gender, address, email, phone);
        
        return true;
    }
    
    /**
     * Requests the Database to push changes to the student object
     * @return 
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
        
        // If successful, return true
        sW.showInformationMessage("The Student " + student.getFullName() + " has been updated.");
        return true;
    }
    
    private void updateAndClose()
    {
        if (updateDatabase())
            destroyWindow(); 
    }
    
    public class NewStudentActionListener implements ActionListener {

     @Override
     public void actionPerformed(ActionEvent e) 
     {
         // If the Enroll Student Button is pressed, validate the input and display appropriate dialog box
         if (e.getSource().equals(newStudentWindow.enrollStudentButton))
         {
             // Try obtain the text from each field
             updateAndClose();

         }
     }
        
    }
    
}
