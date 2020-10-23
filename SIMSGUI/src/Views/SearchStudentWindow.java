/**
 * SearchStudentWindow Class
 * A View which shows an editable panel with information about a Student.
 * Project 2: SIMS GUI Project
 * @author Daniel Dymond (Group 1 - ID# 17977610) 2020
 */
package Views;

import Models.Student;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import javax.swing.table.DefaultTableModel;

public class SearchStudentWindow extends javax.swing.JInternalFrame implements Observer
{
    ActionListener actionListener;
    DefaultTableModel tableModel;
    
    /**
     * Creates new form SearchStudentWindow
     */
    public SearchStudentWindow(ActionListener actionListener) {
        this.actionListener = actionListener;
        initialiseStudentSearchTableModel();
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        searchRadioGroup = new javax.swing.ButtonGroup();
        jSeparator1 = new javax.swing.JSeparator();
        heroLabel = new javax.swing.JLabel();
        searchByIDRadio = new javax.swing.JRadioButton();
        searchByIDRadio.addActionListener(actionListener);
        searchByNameRadio = new javax.swing.JRadioButton();
        searchByNameRadio.addActionListener(actionListener);
        studentIDField = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        firstNameField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        lastNameField = new javax.swing.JTextField();
        searchByIDButton = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        searchByNameButton = new javax.swing.JButton();
        searchByNameButton.addActionListener(actionListener);
        selectStudentButton = new javax.swing.JButton();
        selectStudentButton.addActionListener(actionListener);
        jScrollPane3 = new javax.swing.JScrollPane();
        studentSearchResultsTable = new javax.swing.JTable(){
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        tipText = new javax.swing.JLabel();
        tipText1 = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setTitle("Search Student");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/Views/img/user-32px.png"))); // NOI18N

        heroLabel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        heroLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Views/img/user-32px.png"))); // NOI18N
        heroLabel.setText("Search Student");

        searchRadioGroup.add(searchByIDRadio);
        searchByIDRadio.setSelected(true);
        searchByIDRadio.setText("Search by Student ID");
        searchByIDRadio.setToolTipText("Search for a Student by their 10-digit Student ID.");

        searchRadioGroup.add(searchByNameRadio);
        searchByNameRadio.setText("Search by Student Name");
        searchByNameRadio.setToolTipText("Search for a Student by their First and Last Name. Any Student which have names that contain the First and Last Name components will appear in the search results below.");

        studentIDField.setToolTipText("Enter a Student's 10-digit Student ID, which constists only of numbers.");

        jLabel1.setText("Student ID:");

        jLabel2.setText("First Name:");

        firstNameField.setToolTipText("Enter a Student's First Name.");
        firstNameField.setEnabled(false);

        jLabel3.setText("Last Name:");

        lastNameField.setToolTipText("Enter a Student's Last Name.");
        lastNameField.setEnabled(false);

        searchByIDButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Views/img/find-16px.png"))); // NOI18N
        searchByIDButton.setText("Search");
        searchByIDButton.setToolTipText("Search for Student by Student ID");
        searchByIDButton.addActionListener(actionListener);

        searchByNameButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Views/img/find-16px.png"))); // NOI18N
        searchByNameButton.setText("Search");
        searchByNameButton.setToolTipText("Search for a Student by First and Last Name");
        searchByNameButton.setEnabled(false);

        selectStudentButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Views/img/document-edit-16px.png"))); // NOI18N
        selectStudentButton.setText("Edit Selected Student");
        selectStudentButton.setToolTipText("Edit the Student selected in the table.");
        selectStudentButton.setEnabled(false);

        studentSearchResultsTable.setAutoCreateRowSorter(true);
        studentSearchResultsTable.setModel(tableModel);
        jScrollPane3.setViewportView(studentSearchResultsTable);
        studentSearchResultsTable.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        tipText.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Views/img/bulb-16px.png"))); // NOI18N
        tipText.setText("Search for a Student by their 10-digit Student ID");

        tipText1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Views/img/bulb-16px.png"))); // NOI18N
        tipText1.setText("Leave blank to show all Students.");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addComponent(jSeparator2)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(heroLabel)
                            .addComponent(searchByIDRadio)
                            .addComponent(searchByNameRadio))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(studentIDField))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lastNameField))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(firstNameField))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(selectStudentButton, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 421, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(tipText)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(searchByIDButton))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(tipText1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(searchByNameButton)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(heroLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(searchByIDRadio)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(studentIDField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(searchByIDButton)
                    .addComponent(tipText))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(searchByNameRadio)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(firstNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lastNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(searchByNameButton)
                    .addComponent(tipText1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(selectStudentButton)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JTextField firstNameField;
    private javax.swing.JLabel heroLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    public javax.swing.JTextField lastNameField;
    public javax.swing.JButton searchByIDButton;
    public javax.swing.JRadioButton searchByIDRadio;
    public javax.swing.JButton searchByNameButton;
    public javax.swing.JRadioButton searchByNameRadio;
    private javax.swing.ButtonGroup searchRadioGroup;
    public javax.swing.JButton selectStudentButton;
    public javax.swing.JTextField studentIDField;
    public javax.swing.JTable studentSearchResultsTable;
    private javax.swing.JLabel tipText;
    private javax.swing.JLabel tipText1;
    // End of variables declaration//GEN-END:variables

    /**
     * Observer Method to call for an update to the StudentSearchModel
     * @param o Observable which has triggered the update
     * @param arg Object passed by the observable
     */
    @Override
    public void update(Observable o, Object arg) 
    {
        if (arg instanceof List)
        {
            List list = (List<Student>) arg;
            populateStudentSearchTableModel((List<Student>) arg);
        }
    }
    
    /**
     * Initialises the DefaultTableModel used to display the Search Results
     */
    private void initialiseStudentSearchTableModel()
    {
        tableModel = new DefaultTableModel(new Object [][] {},
            new String [] {
                "Student ID", "First Name", "Last Name", "Date of Birth"
            }
        );
    }
    
    /**
     * Populates the Table Model used in Search Results for the SearchWindow
     * @param searchResults List of Students to display in the results table
     */
    private void populateStudentSearchTableModel(List<Student> searchResults) 
    {
        // Remove any exisitng rows from the Search Results Model
        while (tableModel.getRowCount() > 0)
            tableModel.removeRow(0);
        
        // Iterate through the Search Results parameter, adding each student to the model
        Iterator<Student> it = searchResults.iterator();
        while (it.hasNext())
        {
            Student stu = it.next();
            tableModel.addRow(new String[] {String.valueOf(stu.getStudentID()), stu.getFirstName(), stu.getLastName(), String.valueOf(stu.getDateOfBirth())});    
        }
    }
}
