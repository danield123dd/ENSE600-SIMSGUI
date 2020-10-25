/**
 * SessionWindowController Class
 * A Controller which facilitates user interactions with the SessionWindow to generate
 * new StudentManagementSessions
 * Project 2: SIMS GUI Project
 * @author Daniel Dymond (Group 1 - ID# 17977610) 2020
 */
package Controllers;

import Views.AboutWindow;
import Views.ExceptionWindow;
import Views.InformationWindow;
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
    SessionWindowActionListener sessionWindowActionListener;
    
    public SessionWindowController (DatabaseAgent dBA)
    {
        this.dBA = dBA;
        spawnWindow();
    }
    
    private void spawnWindow()
    {
        // Create a new Action Listener
        sessionWindowActionListener = new SessionWindowActionListener();
           
        // Create the Session Window
        sW = new SessionWindow(sessionWindowActionListener);
        sW.setTitle("Student Information Management System - \"" + dBA.getUsername() + "\"");
        
        // Show the Window
        sW.setVisible(true);
        
        // Set the Desktop reference
        this.desktop = sW.desktop;
    }
    
    /**
     * Creates a new Runnable containing a StudentManagementSession
     * Automatically generates a View based on the Boolean parameter
     * @param newStudent If True, this SMS will created a New Student. If False, this SMS will open a view to search for an existing student.
     */
    private void createStudentManagementSession (boolean newStudent) 
    {
        SessionWindowController sWC = this;
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new StudentManagementSession(dBA, sWC, newStudent);
            }
        });
    }
    
    /**
     * Show's the About Window in the SessionWindow
     */
    private void showAboutWindow() 
    {
        // If an AboutWindow already exists, set to visible and bring to front
        if (aboutWindow != null)
        {
            aboutWindow.setVisible(true);
            aboutWindow.toFront();
        }
        
        // Otherwise, create a new AboutWindow and display
        else
        {
            aboutWindow = new AboutWindow();
            desktop.add(aboutWindow);
            aboutWindow.setVisible(true);
        }
    }
    
    /**
     * Perform the Logout Sequence for disconnecting from the database.
     */
    public void logOut() 
    {
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
     * Creates a new Information Dialog in the Desktop Environment
     * @param message Message to Show
     */
    public void showInformationMessage(String message) {
        InformationWindow iW = new InformationWindow(message);
        desktop.add(iW);
        iW.setVisible(true);
    }

    /**
     * Creates a new Exception Dialog in the Desktop Environment
     * @param message Message to Show
     */
    public void showExceptionMessage(String message) {
        ExceptionWindow eW = new ExceptionWindow(message);
        desktop.add(eW);
        eW.setVisible(true);
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
                logOut();
        }
    }
    
    public JDesktopPane getDesktop()
    {
        return this.desktop;
    }
}
