/**
 * StudentManagementSession Class
 * A Session Controller which facilitates user interactions with Students in the Database
 * and encapsulates references and work into one class for multi-threading.
 * Project 2: SIMS GUI Project
 * @author Daniel Dymond (Group 1 - ID# 17977610) 2020
 */
package Controllers;

import Models.Student;

public class StudentManagementSession 
{
    // References to the Session Window and DatabaseAgent for Controllers
    static SessionWindowController sessionWindowController;
    static DatabaseAgent databaseAgent;
    
    // Reference to the Controllers
    EditStudentController editStudentController;
    SearchStudentController searchStudentController;
    NewStudentController newStudentController;
    
    // Refernece to Model used
    Student student;
   
    /**
     * Creates a new StudentManagement Session, and spawns the corresponding window based on intended outcome.
     * @param sessionWindowController SessionWindowController containing the Desktop environment to display windows on
     * @param newStudent Set true if creating a New Student, or false to edit an Existing Student
     */
    public StudentManagementSession(SessionWindowController sessionWindowController, boolean newStudent)
    {
        // Set References
        this.sessionWindowController = sessionWindowController;
        this.databaseAgent = sessionWindowController.dBA;
        
        // If a New Student is being created, spawn the Create Student Window
        // Otherwise, show the Search Student Window
        if (newStudent)
            createNewWindow();
        else
            createSearchWindow();
    }
    
    /**
     * Create a new NewStudentController to create and handle the New Student View
     */
    private void createNewWindow()
    {
        newStudentController = new NewStudentController(this);  
    }
    
    /**
     * Create a new SearchStudentController to create and handle the Search Student View
     */
    private void createSearchWindow()
    {
        searchStudentController = new SearchStudentController(this);
    }
    
    /**
     * Create a new Edit Student Controller to create and handle the Edit Student View
     */
    public void createDetailsWindow() 
    {
        editStudentController = new EditStudentController(this);
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
 
    
    
    
}
