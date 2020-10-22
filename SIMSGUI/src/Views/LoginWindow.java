package Views;

import Controllers.DatabaseAgent;
import Controllers.LoginController.LoginWindowActionListener;
import java.sql.SQLException;

/**
 *
 * @author danie
 */
public class LoginWindow extends javax.swing.JFrame {
    
    LoginWindowActionListener actionListener;

    /**
     * Creates new form LoginWindow
     * @param session
     */
    public LoginWindow(LoginWindowActionListener actionListener) {
        this.actionListener = actionListener;
        initComponents();
        getRootPane().setDefaultButton(loginButton);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        exceptionDialog = new javax.swing.JDialog();
        jPanel1 = new javax.swing.JPanel();
        errorHeader = new javax.swing.JLabel();
        icon = new javax.swing.JLabel();
        acknowledgementButton = new javax.swing.JButton();
        errorMessage = new javax.swing.JTextArea();
        headerPanel = new javax.swing.JPanel();
        headerGreeting = new javax.swing.JLabel();
        headerClock = new javax.swing.JLabel();
        usernameLabel = new javax.swing.JLabel();
        passwordLabel = new javax.swing.JLabel();
        usernameField = new javax.swing.JTextField();
        passwordField = new javax.swing.JPasswordField();
        loginButton = new javax.swing.JButton();
        loginButton.addActionListener(actionListener);
        closeButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        databaseURLField = new javax.swing.JTextField();
        defaultDatabaseCheckBox = new javax.swing.JCheckBox();

        exceptionDialog.setTitle("Login Error");
        exceptionDialog.setAlwaysOnTop(true);
        exceptionDialog.setIconImage(null);
        exceptionDialog.setMinimumSize(new java.awt.Dimension(400, 200));
        exceptionDialog.setName("loginExceptionDialog"); // NOI18N

        errorHeader.setText("An error occured while connecting to the Database:");

        icon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Views/img/error-32px.png"))); // NOI18N

        acknowledgementButton.setText("OK");
        acknowledgementButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                acknowledgementButtonActionPerformed(evt);
            }
        });

        errorMessage.setEditable(false);
        errorMessage.setBackground(javax.swing.UIManager.getDefaults().getColor("ColorChooser.background"));
        errorMessage.setColumns(20);
        errorMessage.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        errorMessage.setRows(5);
        errorMessage.setWrapStyleWord(true);
        errorMessage.setBorder(null);
        errorMessage.setMinimumSize(new java.awt.Dimension(160, 70));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(acknowledgementButton)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(icon)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(errorHeader)
                            .addComponent(errorMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(errorHeader)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(errorMessage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(icon))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(acknowledgementButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout exceptionDialogLayout = new javax.swing.GroupLayout(exceptionDialog.getContentPane());
        exceptionDialog.getContentPane().setLayout(exceptionDialogLayout);
        exceptionDialogLayout.setHorizontalGroup(
            exceptionDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(exceptionDialogLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        exceptionDialogLayout.setVerticalGroup(
            exceptionDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(exceptionDialogLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("SIMS GUI Login");
        setName("loginWindowFrame"); // NOI18N
        setResizable(false);

        headerGreeting.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Views/img/network-database-32px.png"))); // NOI18N
        headerGreeting.setText("Welcome to SIMS GUI");

        headerClock.setText("XX:YY ZM");

        javax.swing.GroupLayout headerPanelLayout = new javax.swing.GroupLayout(headerPanel);
        headerPanel.setLayout(headerPanelLayout);
        headerPanelLayout.setHorizontalGroup(
            headerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(headerGreeting)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 160, Short.MAX_VALUE)
                .addComponent(headerClock)
                .addContainerGap())
        );
        headerPanelLayout.setVerticalGroup(
            headerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(headerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(headerGreeting)
                    .addComponent(headerClock))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        usernameLabel.setText("Username:");

        passwordLabel.setText("Password:");

        loginButton.setText("Login");

        closeButton.setText("Close");
        closeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeButtonActionPerformed(evt);
            }
        });

        jLabel1.setText("Database URL:");

        databaseURLField.setText("jdbc:derby://localhost:1527/SIMSDB");
        databaseURLField.setEnabled(false);

        defaultDatabaseCheckBox.setSelected(true);
        defaultDatabaseCheckBox.setText("Use Default Database");
        defaultDatabaseCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                defaultDatabaseCheckBoxActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(headerPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(passwordLabel)
                            .addComponent(usernameLabel)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(passwordField)
                            .addComponent(usernameField)
                            .addComponent(databaseURLField)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(defaultDatabaseCheckBox)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(closeButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(loginButton)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(headerPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(usernameLabel)
                    .addComponent(usernameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(passwordLabel)
                    .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(databaseURLField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(loginButton)
                    .addComponent(closeButton)
                    .addComponent(defaultDatabaseCheckBox))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void closeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeButtonActionPerformed
        System.exit(0);
    }//GEN-LAST:event_closeButtonActionPerformed

    private void defaultDatabaseCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_defaultDatabaseCheckBoxActionPerformed
        if (defaultDatabaseCheckBox.isSelected())
            databaseURLField.setEnabled(false);
        else
            databaseURLField.setEnabled(true);
    }//GEN-LAST:event_defaultDatabaseCheckBoxActionPerformed

    private void acknowledgementButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_acknowledgementButtonActionPerformed
        exceptionDialog.setVisible(false);
    }//GEN-LAST:event_acknowledgementButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton acknowledgementButton;
    public javax.swing.JButton closeButton;
    public javax.swing.JTextField databaseURLField;
    public javax.swing.JCheckBox defaultDatabaseCheckBox;
    private javax.swing.JLabel errorHeader;
    private javax.swing.JTextArea errorMessage;
    private javax.swing.JDialog exceptionDialog;
    private javax.swing.JLabel headerClock;
    private javax.swing.JLabel headerGreeting;
    private javax.swing.JPanel headerPanel;
    private javax.swing.JLabel icon;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    public javax.swing.JButton loginButton;
    public javax.swing.JPasswordField passwordField;
    private javax.swing.JLabel passwordLabel;
    public javax.swing.JTextField usernameField;
    private javax.swing.JLabel usernameLabel;
    // End of variables declaration//GEN-END:variables
}