/**
 * SessionWindowController Class
 * A Controller which facilitates user interactions with the SessionWindow to generate
 * new StudentManagementSessions
 * Project 2: SIMS GUI Project
 * @author Daniel Dymond (Group 1 - ID# 17977610) 2020
 */
package Controllers;

import Views.SessionWindow;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SessionWindowController 
{
    SessionWindow sW;
    DatabaseAgent dBA;
    
    public SessionWindowController (DatabaseAgent dBA)
    {
        this.dBA = dBA;
        spawnWindow();
    }
    
    private void spawnWindow()
    {
        
    }
    
    /**
     * Creates a new Runnable containing a StudentManagementSession
     * Automatically generates a View based on the Boolean parameter
     * @param newStudent If True, this SMS will created a New Student. If False, this SMS will open a view to search for an existing student.
     */
    private void createStudentManagementSession (boolean newStudent) 
    {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new StudentManagementSession(dBA, sW, newStudent);
            }
        });
    }
    
    /**
     * Action Listener Class used for Session Windows
     */
    public class SessionWindowActionListener implements ActionListener 
    {
        @Override
        public void actionPerformed(ActionEvent e) 
        {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
        
    }
}
