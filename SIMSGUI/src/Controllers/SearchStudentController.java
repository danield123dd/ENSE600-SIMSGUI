/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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

/**
 *
 * @author danie
 */
public class SearchStudentController {
    
    StudentManagementSession sMS;
    SessionWindow sW;
    SearchStudentWindow searchStudentWindow;
    SearchStudentWindowActionListener searchStudentWindowActionListener;
    DatabaseAgent dBA;
    
    DefaultTableModel searchResultsModel;
    
    public SearchStudentController (StudentManagementSession sMS, SessionWindow sW) {
        this.sW = sW;
        this.sMS = sMS;
        this.dBA = sMS.dBA;
        spawnWindow();
    }
    
    /**
     * Generates and Displays the SearchStudentWindow
     */
    private void spawnWindow() 
    {
        // Create a new Action Listener
        searchStudentWindowActionListener = new SearchStudentWindowActionListener();

        // Create a Table Model for the Student Search Results
        searchResultsModel = new DefaultTableModel();
        searchResultsModel.addColumn("Student ID");
        searchResultsModel.addColumn("First Name");
        searchResultsModel.addColumn("Last Name");
        searchResultsModel.addColumn("Date of Birth");
           
        // Create the Search Student Window
        searchStudentWindow = new SearchStudentWindow(searchStudentWindowActionListener, searchResultsModel);

        // Add the window to the desktop and set visible
        sW.getDesktop().add(searchStudentWindow);
        searchStudentWindow.setVisible(true);
    }
    
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
     * @param firstName First Name to locate
     * @param lastName  Last Name to locate
     */
    private void searchStudentByName (String firstName, String lastName) 
    {
        // Attempt to Lookup Students in the Database
        try 
        {
            List<Student> searchResults = dBA.getStudent(firstName, lastName);
            
            // If a student is found, show the results in the table
            if (searchResults != null && searchResults.size() > 0)
            {
                populateStudentSearchTableModel(searchResults);
                searchStudentWindow.studentSearchResultsTable.setRowSelectionAllowed(true);
            }
                
            // Otherwise, show an error message
            else {
                sW.showExceptionMessage("No Student was found with their name containing: " + firstName + " " + lastName + ".");
            }
        } catch (SQLException sqle) {
            sW.showExceptionMessage(sqle.getMessage());
        } 
        
    }
    
    private void populateStudentSearchTableModel(List<Student> searchResults) 
    {
        while (searchResultsModel.getRowCount() > 0)
            searchResultsModel.removeRow(0);
        
        Iterator<Student> it = searchResults.iterator();
        
        while (it.hasNext())
        {
            Student stu = it.next();

            searchResultsModel.addRow(new String[] {String.valueOf(stu.getStudentID()), stu.getFirstName(), stu.getLastName(), String.valueOf(stu.getDateOfBirth())});    
        }

    }

    
    /**
     * Action Listener Class used for Student Search Windows
     */
    public class SearchStudentWindowActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource().equals(searchStudentWindow.searchByIDButton))
            {
                searchStudentByID(searchStudentWindow.studentIDField.getText());
            }
            else if (e.getSource().equals(searchStudentWindow.searchByNameButton))
            {
                String firstName = searchStudentWindow.firstNameField.getText();
                String lastName = searchStudentWindow.lastNameField.getText();
                searchResultsModel.addRow(new String[] {"", "", "", ""});                
                //searchStudentByName(firstName, lastName);
            }
            else if (e.getSource().equals(searchStudentWindow.selectStudentButton))
            {
                // Determine Selected Paper from the Table
                int selectedRow = searchStudentWindow.studentSearchResultsTable.getSelectedRow();
                if (selectedRow != -1) {
                    String studentID = searchStudentWindow.studentSearchResultsTable.getValueAt(selectedRow, 0).toString();
                    searchStudentByID(studentID);
                }
            }
           
            
            // TODO For FN, LN
        }
    
    }
    
}
