/**
 * SessionWindowController Class
 * A Controller which facilitates user interactions with the SessionWindow to generate
 * new StudentManagementSessions
 * Project 2: SIMS GUI Project
 * @author Daniel Dymond (Group 1 - ID# 17977610) 2020
 */
package Controllers;

import Views.AboutWindow;
import Views.SessionWindow;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.JDesktopPane;

public class SessionWindowController 
{
    SessionWindow sW;
    DatabaseAgent dBA;
    AboutWindow aboutWindow;
    JDesktopPane desktop;
    
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
    
    private void showAboutWindow() 
    {
        if (AboutWindow != null)
            return;
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                desktop.add(new AboutWindow()).setVisible(true);
            }
        });
    }
    
    public void logOut() {
        // Perform cleanup and logout of connection
        boolean logout = false;
        try {
            logout = dBA.logout();
        } catch (SQLException sqle) {
            System.err.println(sqle);
        } finally {
            // If the logout is successful, return to login window
            if (logout) 
            {
                new LoginController();
                sW.dispose();
            } 
            else
            {
                //TODO add forced exit command
                System.err.println("Error");
            }
        }
    }
    
    /**
     * Action Listener Class used for Session Windows
     */
    public class SessionWindowActionListener implements ActionListener 
    {
        @Override
        public void actionPerformed(ActionEvent e) 
        {
            // If the SearchStudentButton is selected, create a new SMS with Search
            if (e.getSource().equals(sW.searchStudentButton))
                createStudentManagementSession(false);
            
            // If the CreateStudentButton is selected, create a new SMS with New Student
            else if (e.getSource().equals(sW.createStudentButton))
                createStudentManagementSession(true);
            
            // If the AboutButton is selected, show the About Window
            else if (e.getSource().equals(sW.aboutButton))
                showAboutWindow();
            
            // If the LogoutButton is selected, logout of the database
            else if (e.getSource().equals(sW.logoutButton))
                //
        }
    }
}
