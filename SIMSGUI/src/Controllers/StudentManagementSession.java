package Controllers;

import Models.Student;
import Views.SessionWindow;

/**
 *
 * @author danie
 */
public class StudentManagementSession {
    
    // References to the Session Window to create elements on
    static SessionWindow sessionWindow;
    static DatabaseAgent databaseAgent;
    
    // Reference to the Controllers
    EditStudentController editStudentController;
    SearchStudentController searchStudentController;
    NewStudentController newStudentController;
    
    // Refernece to Models used
    Student student;
   
    
    public StudentManagementSession(DatabaseAgent databaseAgent, SessionWindow sessionWindow, boolean newStudent)
    {
        // Set References
        this.sessionWindow = sessionWindow;
        this.databaseAgent = databaseAgent;
        
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
