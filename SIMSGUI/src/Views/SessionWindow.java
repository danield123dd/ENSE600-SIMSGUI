/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import Controllers.DatabaseAgent;
import Controllers.LoginController;
import Controllers.StudentManagementSession;
import java.sql.SQLException;
import javax.swing.JDesktopPane;

/**
 *
 * @author danie
 */
public class SessionWindow extends javax.swing.JFrame {
    
    static DatabaseAgent dBA;

    /**
     * Creates new form SessionWindow
     */
    public SessionWindow(DatabaseAgent dBA) {
        this.dBA = dBA;
        initComponents();
        setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        desktop = new javax.swing.JDesktopPane();
        toolbar = new javax.swing.JToolBar();
        searchStudentButton = new javax.swing.JButton();
        createStudentButton = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        aboutButton = new javax.swing.JButton();
        logoutButton = new javax.swing.JButton();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
        usernameLabel = new javax.swing.JLabel();
        filler2 = new javax.swing.Box.Filler(new java.awt.Dimension(5, 0), new java.awt.Dimension(5, 0), new java.awt.Dimension(5, 32767));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Student Information Management System");

        this.setTitle("Student Information Management System - \"" + dBA.getUsername() + "\"");

        javax.swing.GroupLayout desktopLayout = new javax.swing.GroupLayout(desktop);
        desktop.setLayout(desktopLayout);
        desktopLayout.setHorizontalGroup(
            desktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1101, Short.MAX_VALUE)
        );
        desktopLayout.setVerticalGroup(
            desktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 569, Short.MAX_VALUE)
        );

        toolbar.setFloatable(false);
        toolbar.setRollover(true);

        searchStudentButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Views/img/user-32px.png"))); // NOI18N
        searchStudentButton.setText("Search Student...");
        searchStudentButton.setToolTipText("Search for a Student by either their Student ID or via their name");
        searchStudentButton.setFocusable(false);
        searchStudentButton.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        searchStudentButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchStudentButtonActionPerformed(evt);
            }
        });
        toolbar.add(searchStudentButton);

        createStudentButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Views/img/user-add-32px.png"))); // NOI18N
        createStudentButton.setText("Create Student...");
        createStudentButton.setToolTipText("Create a new Student to add to the Institution");
        createStudentButton.setFocusable(false);
        createStudentButton.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        createStudentButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createStudentButtonActionPerformed(evt);
            }
        });
        toolbar.add(createStudentButton);
        toolbar.add(jSeparator1);

        aboutButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Views/img/info-32px.png"))); // NOI18N
        aboutButton.setText("About");
        aboutButton.setToolTipText("Change Application preferences and see About information");
        aboutButton.setFocusable(false);
        aboutButton.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        aboutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aboutButtonActionPerformed(evt);
            }
        });
        toolbar.add(aboutButton);
        aboutButton.getAccessibleContext().setAccessibleDescription("See information about this application");

        logoutButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Views/img/lock-32px.png"))); // NOI18N
        logoutButton.setText("Logout");
        logoutButton.setToolTipText("Create a new Class for the Institution");
        logoutButton.setFocusable(false);
        logoutButton.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        logoutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutButtonActionPerformed(evt);
            }
        });
        toolbar.add(logoutButton);
        toolbar.add(filler1);

        usernameLabel.setText("Username");
        usernameLabel.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        usernameLabel.setText(dBA.getUsername());
        toolbar.add(usernameLabel);
        toolbar.add(filler2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desktop)
            .addComponent(toolbar, javax.swing.GroupLayout.DEFAULT_SIZE, 1101, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(toolbar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(desktop))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * When the logout completes
     * @param evt 
     */
    private void logoutButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutButtonActionPerformed
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
                this.dispose();
            } 
            else
            {
                //TODO add forced exit command
                System.err.println("Error");
            }
        }
    }//GEN-LAST:event_logoutButtonActionPerformed

    private void searchStudentButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchStudentButtonActionPerformed
        // TODO add your handling code here:
        //desktop.add(new SearchStudentWindow()).setVisible(true);
        new StudentManagementSession(dBA, this, false);
    }//GEN-LAST:event_searchStudentButtonActionPerformed

    /**
     * Open the About Window
     * @param evt 
     */
    private void aboutButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aboutButtonActionPerformed
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                desktop.add(new AboutWindow()).setVisible(true);
            }
        });
    }//GEN-LAST:event_aboutButtonActionPerformed

    private void createStudentButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createStudentButtonActionPerformed
        // TODO add your handling code here:
        new StudentManagementSession(dBA, this, true);
    }//GEN-LAST:event_createStudentButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton aboutButton;
    private javax.swing.JButton createStudentButton;
    private javax.swing.JDesktopPane desktop;
    private javax.swing.Box.Filler filler1;
    private javax.swing.Box.Filler filler2;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JButton logoutButton;
    private javax.swing.JButton searchStudentButton;
    private javax.swing.JToolBar toolbar;
    private javax.swing.JLabel usernameLabel;
    // End of variables declaration//GEN-END:variables

    public JDesktopPane getDesktop()
    {
        return this.desktop;
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
}
