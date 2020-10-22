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
    
    // Reference to the Controllers
    EditStudentController editStudentController;
    SearchStudentController searchStudentController;
    NewStudentController newStudentController;
    
    // Refernece to Models used
    Student student;
    
    
    
    
    // Reference to the Forms
    DatabaseAgent dBA;
   
    
    public StudentManagementSession(DatabaseAgent dBA, SessionWindow sessionWindow, boolean newStudent)
    {
        // Set References
        this.sessionWindow = sessionWindow;
        this.dBA = dBA;
        
        // If a New Student is being created, spawn the Create Student Window
        // Otherwise, show the Search Student Window
        if (newStudent)
            createNewWindow();
        else
            createSearchWindow();
    }
    
    
    private void createNewWindow()
    {
        newStudentController = new NewStudentController(this, sessionWindow);  
    }
    
    /**
     * Create a new Search Student Controller to create and handle the Search Student View
     */
    private void createSearchWindow()
    {
        searchStudentController = new SearchStudentController(this, sessionWindow);
    }
    
    /**
     * Create a new Edit Student Controller to create and handle the Edit Student View
     */
    public void createDetailsWindow() 
    {
        editStudentController = new EditStudentController(this, sessionWindow);
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
 
    
    
    
}
