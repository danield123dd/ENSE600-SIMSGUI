/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

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
    
    public class LoginWindowActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            
            // If the Login Button is selected, attempt to login
            if (e.getSource().equals(loginWindow.loginButton))
            {
                // Attempt to connect to the database with the details provided
            try {
                dBA = new Controllers.DatabaseAgent(usernameField.getText(), new String(passwordField.getPassword()), databaseURLField.getText());
                new SessionWindow(dBA).setVisible(true);
                this.dispose();
            } catch (SQLException sqle) {
                errorMessage.setText(sqle.getMessage());
                exceptionDialog.setVisible(true);
            } catch (IllegalArgumentException iie) {
                errorMessage.setText(iie.getMessage());
                exceptionDialog.setVisible(true);
            }
            }
        }
        
    }
    
}
