/**
 * StudentSearchController Class
 * A Controller which facilitates user interactions with the SearchStudentWindow and Database
 * Project 2: SIMS GUI Project
 * @author Daniel Dymond (Group 1 - ID# 17977610) 2020
 */
package Controllers;

import Models.Student;
import Views.SearchStudentWindow;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import Views.SessionWindow;
import java.util.Observable;

public class SearchStudentController extends Observable
{    
    StudentManagementSession sMS;
    SessionWindow sW;
    SearchStudentWindow searchStudentWindow;
    SearchStudentWindowActionListener searchStudentWindowActionListener;
    DatabaseAgent dBA;
    DefaultTableModel searchResultsModel;
    
    /**
     * Create a new StudentSearchController with an associated StudentSearchWindow
     * @param sMS StudentManagementSession containing the Student Object
     */
    public SearchStudentController (StudentManagementSession sMS) {
        this.sMS = sMS;
        this.sW = sMS.sessionWindow;
        this.dBA = sMS.databaseAgent;
        spawnWindow();
    }
    
    /**
     * Generates and Displays the SearchStudentWindow for this StudentManagementSession.
     */
    private void spawnWindow() 
    {
        // Create a new Action Listener
        searchStudentWindowActionListener = new SearchStudentWindowActionListener();

        // Create a Table Model for the Student Search Results, and populate with columns
        searchResultsModel = new DefaultTableModel();
        searchResultsModel.addColumn("Student ID");
        searchResultsModel.addColumn("First Name");
        searchResultsModel.addColumn("Last Name");
        searchResultsModel.addColumn("Date of Birth");
           
        // Create the Search Student Window
        searchStudentWindow = new SearchStudentWindow(searchStudentWindowActionListener);
        
        addObserver(searchStudentWindow);

        // Add the window to the desktop and set visible
        sW.getDesktop().add(searchStudentWindow);
        searchStudentWindow.setVisible(true);
    }
    
    /**
     * Removes the StudentSearchWindow from the Desktop Environment and disposes the window.
     */
    private void destroyWindow()
    {
        searchStudentWindow.setVisible(false);
        sW.getDesktop().remove(searchStudentWindow);
        searchStudentWindow.dispose();
        searchStudentWindow = null;
    }
    
    /**
     * Instructs the Database to lookup a Student by ID, and if found, opens the View with the Student Object
     * @param id Student ID
     */
    private void searchStudentByID (String id) 
    {
        // If the Student ID is not in a valid format, show an error message
        id = id.trim();
        if (!id.replaceAll("[0-9]", "").equals(""))
        {
            sW.showExceptionMessage("The Student ID entered is in an invalid format. It should only consist of numbers.");
            return;
        }
            
        // Otherwise, attempt to lookup in the Database
        try {
            // Run Query
            sMS.student = dBA.getStudent(id);
            
            // If a student is found, create a new window
            if (sMS.student != null)
            {
                this.destroyWindow();
                sMS.createDetailsWindow();
            }
            
            // Otherwise, show an error message
            else {
                sW.showExceptionMessage("No Student was found with the Student ID: " + id + ". Please verify the ID number entered, or try locate the student by name.");
            }
        } catch (SQLException sqle) {
            sW.showExceptionMessage(sqle.getMessage());
        } 
    }
    
    /**
     * Instructs the Database to lookup a Student by Name, and finds matches to then be displayed on the results screen
     * @param firstName First Name "contains" expression
     * @param lastName  Last Name "contains" expression
     */
    private void searchStudentByName (String firstName, String lastName) 
    {
        try 
        {
            // Request the Database to search for Students containing the First and Last Name elements
            List<Student> searchResults = dBA.getStudent(firstName, lastName);
            
            // If student(s) are found, show the results in the table
            if (searchResults != null && searchResults.size() > 0)
            {
                setChanged();
                notifyObservers(searchResults);
                searchStudentWindow.studentSearchResultsTable.setRowSelectionAllowed(true);
            } 
            // Otherwise, if no students are found, show an error message
            else 
                sW.showExceptionMessage("No Student was found with their name containing: " + firstName + " " + lastName + ".");   
        }
        // If a Database Error occurs, show an error message
        catch (SQLException sqle) {
            sW.showExceptionMessage(sqle.getMessage());
        } 
    }
    
    /**
     * Toggles the View Fields between Student ID and Name
     */
    private void toggleViewFields()
    {
        boolean searchByID = searchStudentWindow.searchByIDRadio.isSelected();
        searchStudentWindow.studentIDField.setEnabled(searchByID);
        searchStudentWindow.searchByIDButton.setEnabled(searchByID);
        searchStudentWindow.firstNameField.setEnabled(!searchByID);
        searchStudentWindow.lastNameField.setEnabled(!searchByID);
        searchStudentWindow.searchByNameButton.setEnabled(!searchByID);
        searchStudentWindow.selectStudentButton.setEnabled(!searchByID);
        searchStudentWindow.studentSearchResultsTable.setEnabled(!searchByID);
    }

    /**
     * Action Listener Class used for Student Search Windows
     */
    public class SearchStudentWindowActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e)
        {
            // If the SearchByIDButton is selected, perform a search by Student ID
            if (e.getSource().equals(searchStudentWindow.searchByIDButton))
                searchStudentByID(searchStudentWindow.studentIDField.getText());

            // If the SearchByNameButton is selected, perform a search by Name
            else if (e.getSource().equals(searchStudentWindow.searchByNameButton))
            {
                String firstName = searchStudentWindow.firstNameField.getText();
                String lastName = searchStudentWindow.lastNameField.getText();                
                searchStudentByName(firstName, lastName);
            }
            
            // If the SelectStudentButton is selected, create a EditStudentController
            else if (e.getSource().equals(searchStudentWindow.selectStudentButton))
            {
                // Determine Selected Paper from the Table
                int selectedRow = searchStudentWindow.studentSearchResultsTable.getSelectedRow();
                if (selectedRow >= 0) {
                    String studentID = searchStudentWindow.studentSearchResultsTable.getValueAt(selectedRow, 0).toString();
                    searchStudentByID(studentID);
                }
            }
            
            // If either of the Radio controls are toggled, update the view to reflect the selected option.
            else if (e.getSource().equals(searchStudentWindow.searchByIDRadio) || e.getSource().equals(searchStudentWindow.searchByNameRadio))
                toggleViewFields();
        }
    
    }
    
}
