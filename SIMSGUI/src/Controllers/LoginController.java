/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Views.ExceptionWindow;
import Views.LoginExceptionWindow;
import Views.LoginWindow;
import Views.SessionWindow;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

/**
 *
 * @author danie
 */
public class LoginController {
    
    DatabaseAgent dBA;
    LoginWindow loginWindow;
    LoginWindowActionListener loginWindowActionListener;
    
    public LoginController()
    {
        spawnWindow();
    }
    
    
    /**
     * Generates and Displays the SearchStudentWindow
     */
    private void spawnWindow() 
    {
        // Create a new Action Listener
        loginWindowActionListener = new LoginWindowActionListener();
           
        // Create the Search Student Window
        this.loginWindow = new LoginWindow(loginWindowActionListener);

        // Add the window to the screen and set visible
        loginWindow.setVisible(true);
    }
    
    public class LoginWindowActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            
            // If the Login Button is selected, attempt to login
            if (e.getSource().equals(loginWindow.loginButton))
            {
                // Attempt to connect to the database with the details provided
            try {
                dBA = new Controllers.DatabaseAgent(loginWindow.usernameField.getText(), new String(loginWindow.passwordField.getPassword()), loginWindow.databaseURLField.getText());
                new SessionWindow(dBA).setVisible(true);
                loginWindow.dispose();
            } catch (SQLException sqle) {
                new LoginExceptionWindow(sqle.getMessage()).setVisible(true);
                //exceptionDialog.setVisible(true);
            } catch (IllegalArgumentException iie) {
                new LoginExceptionWindow(iie.getMessage()).setVisible(true);
                //errorMessage.setText(iie.getMessage());
                //exceptionDialog.setVisible(true);
            }
            }
        }
        
    }
    
}
