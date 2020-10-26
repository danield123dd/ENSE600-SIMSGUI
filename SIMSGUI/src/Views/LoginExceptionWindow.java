/**
 * LoginExceptionWindow Class
 * A JFrame used to show Exceptions when error occur within the application
 * Project 2: SIMS GUI Project
 * @author Daniel Dymond (Group 1 - ID# 17977610) 2020
 */
package Views;

public class LoginExceptionWindow extends javax.swing.JFrame 
{
    String message;
    
    /**
     * Creates new form LoginExceptionWindow
     * @param message Message to Display in the Window
     */
    public LoginExceptionWindow(String message) {
        this.message = message;
        initComponents();
        getRootPane().setDefaultButton(acknowledgementButton);
        messageArea.setLineWrap(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        acknowledgementButton = new javax.swing.JButton();
        icon = new javax.swing.JLabel();
        errorHeader = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        messageArea = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Error");
        setAlwaysOnTop(true);
        setIconImages(null);
        setResizable(false);

        acknowledgementButton.setText("OK");
        acknowledgementButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                acknowledgementButtonActionPerformed(evt);
            }
        });

        icon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Views/img/error-32px.png"))); // NOI18N

        errorHeader.setText("An error occured while completing that action:");

        jScrollPane1.setMinimumSize(new java.awt.Dimension(166, 96));

        messageArea.setColumns(20);
        messageArea.setRows(5);
        messageArea.setText(message);
        jScrollPane1.setViewportView(messageArea);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(icon)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(errorHeader)
                        .addGap(0, 71, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(acknowledgementButton)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(icon)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(errorHeader)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(acknowledgementButton)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void acknowledgementButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_acknowledgementButtonActionPerformed
        this.setVisible(false);
        this.dispose();
    }//GEN-LAST:event_acknowledgementButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton acknowledgementButton;
    private javax.swing.JLabel errorHeader;
    private javax.swing.JLabel icon;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea messageArea;
    // End of variables declaration//GEN-END:variables
}
