/**
 * LoginController Class
 * A Controller which facilitates user interactions with the LoginWindow and the Database
 * Project 2: SIMS GUI Project
 * @author Daniel Dymond (Group 1 - ID# 17977610) 2020
 */
package Controllers;

import Views.LoginExceptionWindow;
import Views.LoginWindow;
import Views.SessionWindow;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class LoginController 
{
    
    DatabaseAgent dBA;
    LoginWindow loginWindow;
    LoginWindowActionListener loginWindowActionListener;
    
    // Default Embedded Derby Server URL
    static String DEFAULT_DATABASE_URL = "jdbc:derby://localhost:1527/SIMSDB";
    
    /**
     * Creates a new LoginController and spawns a new LoginWindow
     */
    public LoginController()
    {
        spawnWindow();
    }
    
    /**
     * Generates and Displays the LoginWindow
     */
    private void spawnWindow() 
    {
        // Create a new Action Listener
        loginWindowActionListener = new LoginWindowActionListener();
           
        // Create the Login Window
        this.loginWindow = new LoginWindow(loginWindowActionListener);

        // Add the window to the screen and set visible
        loginWindow.setVisible(true);
    }
    
    /**
     * Updates the controls of the window, particularly with the DefaultDatabaseCheckBox
     */
    private void updateWindow()
    {
        // Set the enabled state of the URL Bar based on the checkbox
        loginWindow.databaseURLField.setEnabled(!loginWindow.defaultDatabaseCheckBox.isSelected());

        // If disabled, re-set the text to the default URL
        if (!loginWindow.databaseURLField.isEnabled())
            loginWindow.databaseURLField.setText(DEFAULT_DATABASE_URL);
    }
    
    /**
     * Attempt to login to the database - where successful, create a new session window.
     * Otherwise, show LoginExceptionWindow.
     */
    private void login()
    {
        try 
        {
            // Attempt to connect to the database with the details provided
            dBA = new Controllers.DatabaseAgent(loginWindow.usernameField.getText(), new String(loginWindow.passwordField.getPassword()), loginWindow.databaseURLField.getText());
            
            // If dBA is successful, create a new Session Window
            new SessionWindow(dBA).setVisible(true);
            loginWindow.dispose();
        } 
        
        // If an exception is caught, show an error message
        catch (SQLException sqle) {
            new LoginExceptionWindow(sqle.getMessage()).setVisible(true);
        } catch (IllegalArgumentException iie) {
            new LoginExceptionWindow(iie.getMessage()).setVisible(true);
        }
    }
    
    /**
     * ActionListener used for LoginWindow to handle events
     */
    public class LoginWindowActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            
            // If the Login Button is selected, attempt to login
            if (e.getSource().equals(loginWindow.loginButton))
                login();
            
            // If the Close Button is selected, end the program on Exit Code 0
            else if (e.getSource().equals(loginWindow.closeButton))
                System.exit(0);
            
            // If the Default Database CheckBox is toggled, update the window
            else if (e.getSource().equals(loginWindow.defaultDatabaseCheckBox))
                updateWindow();
        }
        
    }
    
}
