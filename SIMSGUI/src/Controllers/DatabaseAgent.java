/**
 * DatabaseAgent Class
 * An agent which communicates between the Database and a Student Model to 
 * add/modify students in the Database.
 * Project 2: SIMS GUI Project
 * @author Daniel Dymond (Group 1 - ID# 17977610) 2020
 */
package Controllers;

import Models.Address;
import Models.Gender;
import Models.Student;
import Views.ExceptionWindow;
import Views.LoginExceptionWindow;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class DatabaseAgent 
{
    public Connection conn = null;
    String url;
    String username;
    Statement statement;
    DatabaseMetaData dbmd;
    
    /**
     * Creates a new Database Agent, attempting to perform a Login to the Database Server
     * @param username  Username
     * @param password  Password
     * @param url       URL to Database
     * @throws SQLException If there is a connection error to the Database
     * @throws IllegalArgumentException If the credentials provided do not meet the requirements to sign on
     */
    public DatabaseAgent(String username, String password, String url) throws SQLException, IllegalArgumentException
    {
        login(username, password, url);
    }
    
    /**
     * Login to the DerbyDB Database.
     * @param username  Username
     * @param password  Password
     * @param url       Database URL
     * @return          True if the login is successful
     * @throws SQLException If there is an error connecting to the Database
     * @throws IllegalArgumentException If there is an error with the details entered into the view.
     */
    public boolean login (String username, String password, String url) throws SQLException, IllegalArgumentException
    {   
        // Validate inputs, and throw applicable exceptions if any error occurs
        if (username == null || password == null)
            throw new IllegalArgumentException("The Username and Password cannot be undefined.");
        else if (username.trim().equals(""))
            throw new IllegalArgumentException("The Username field is empty.");
        else if (url == null || url.trim().equals(""))
            throw new SQLException("The Databse URL is empty.");
        
        // Otherwise, attempt to connect to the database    
        try 
        {
            conn = DriverManager.getConnection(url, username, password);
            statement = conn.createStatement();
            dbmd = conn.getMetaData();
            
            // Once connected, keep URL and Username (these might be useful in 
            // the interface), but do not keep the Password (for security)
            this.url = url;
            this.username = username;
        } catch (SQLException sqle) {
            throw sqle;
        }
        return true;
    }
    
    /**
     * Logs out of the database, and nullify any important connection information
     * before garbage collection for security.
     * @return true if successful
     */
    public boolean logout() throws SQLException
    {
        // Close the connection
        try {
            if (conn != null)
            {
                // Close the statement object and connection. Set all to null
                statement.close();
                statement = null;
                conn.close();
                conn = null;
                url = null;
                dbmd = null;
                
                // Once cleared, confirmed success with the calling function.
                return true;
            }
        } catch (SQLException ex) {
            throw ex;
        } 
        
        return false;
    }

    /**
     * Get the username of the user signed in with this DatabaseAgent
     * @return Username
     */
    public String getUsername()
    {
        return this.username;
    }
    
    /**
     * Generates the SQL Statement needed to Update a Student in the Database, 
     * and then executes the Update Query
     * @param student Student to Update
     * @throws SQLException If an error occurs during the Database Transaction
     */
    public void updateStudent(Student student) throws SQLException
    {
        String query = "UPDATE STUDENTS SET";
        query += " FIRST_NAME='" + student.getFirstName() + "',";
        query += " LAST_NAME='" + student.getLastName() + "',";     
        query += " DATE_OF_BIRTH='" + student.getDateOfBirth() + "',"; 
        query += " GENDER='" + student.getGender() + "',"; 
        query += " EMAIL='" + student.getEmail() + "',"; 
        query += " PHONE_NUMBER='" + student.getPhoneNumber() + "',";    
        query += " ADDRESS='" + student.getAddress() + "'";
        query += " WHERE STUDENT_ID='" + student.getStudentID() + "'";
        statement.executeUpdate(query);
    }
    
    /**
     * Generates the SQL Statement needed to Create a new Student in the Database, 
     * and then executes the Insert Query
     * @param student Student to Create
     * @throws SQLException If an error occurs during the Database Transaction
     */
    public void createStudent(Student student) throws SQLException
    {
        String query = "INSERT INTO STUDENTS (STUDENT_ID, FIRST_NAME, LAST_NAME, DATE_OF_BIRTH, GENDER, ADDRESS, EMAIL, PHONE_NUMBER) VALUES (";
        query += "'" + student.getStudentID() + "', ";
        query += "'" + student.getFirstName() + "', ";
        query += "'" + student.getLastName() + "', ";     
        query += "'" + student.getDateOfBirth() + "', "; 
        query += "'" + student.getGender() + "', "; 
        query += "'" + student.getAddress() + "', ";
        query += "'" + student.getEmail() + "', "; 
        query += "'" + student.getPhoneNumber() + "')";    
        
        statement.executeUpdate(query);
    }
    
    /**
     * Obtains a Student based on their Student ID from the Database
     * @param studentID Student ID of Student
     * @return  Student Object if Student is Found, null Otherwise
     * @throws SQLException If there is an issue connecting to the Database
     */
    public Student getStudent(String studentID) throws SQLException
    {
        ResultSet rS = null;
        Student toReturn = null;
        
        // Attempt to lookup the Student from the Database based on Student ID
        try {
            String query = "SELECT * FROM STUDENTS WHERE STUDENT_ID='" + studentID + "'";
            rS = statement.executeQuery(query);
            List<Student> results = generateStudentObjects(rS);
            
            // If a single student is found, the lookup is successful. Otheriwse, perform appropriate error measure
            if (results.size() > 1)
                throw new SQLException("Multiple Students with the same Student ID were found. Contact your system administrator to resolve this issue.");
            if (results.size() < 1)
                toReturn = null;
            else
                toReturn = results.get(0);
        } catch (SQLException sqle) {
            new ExceptionWindow(sqle.getMessage()).setVisible(true);
        }
        
        // Return the Student Object
        return toReturn;
    }
    
    /**
     * Obtains a Student based on a "contains" of the student's first and last name from the Database
     * @param firstName First Name "contains"
     * @param lastName Last Name "contains"
     * @return All Students from the Database which contain both the First Name and Last Name components specified
     * @throws SQLException If there is an issue connecting to the Database
     */
    public List<Student> getStudent(String firstName, String lastName) throws SQLException
    {
        ResultSet rS = null;
        List<Student> toReturn = null;
        
        // Attempt to lookup the Student from the Database based on Student ID
        try {
            String query = "SELECT * FROM STUDENTS WHERE LOWER(FIRST_NAME) LIKE '%" + firstName.toLowerCase() + "%' AND LOWER(LAST_NAME) LIKE '%" + lastName.toLowerCase() + "%'";
            rS = statement.executeQuery(query);
            List<Student> results = generateStudentObjects(rS);
            
            // If student(s) are found, the lookup is successful. Otheriwse, perform appropriate error measure
            if (results.size() < 1)
                toReturn = null;
            else
                toReturn = results;
        } catch (SQLException sqle) {
            new ExceptionWindow(sqle.getMessage()).setVisible(true);
        }
        
        // Return the Student Object
        return toReturn; 
    }
    
    /**
     * Takes a ResultSet and creates a list of Student Objects
     * @param rS    ResultSet from Database Query
     * @return      List of Student Objects
     * @throws SQLException 
     */
    public List generateStudentObjects(ResultSet rS) throws SQLException 
    {
        List<Student> results = new LinkedList<>();
        
        // If no results are found, return null
        if (rS == null)
            return null;

        // Otherwise, go through the results and create a Student Object
        while (rS.next()) {

            // Otherwise, extract the details for the student
            String fN = rS.getString("FIRST_NAME");
            String lN = rS.getString("LAST_NAME");
            String iD = rS.getString("STUDENT_ID");
            LocalDate dOB = rS.getDate("DATE_OF_BIRTH").toLocalDate();
            Gender g = Gender.valueOf(rS.getString("GENDER").toUpperCase());
            String email = rS.getString("EMAIL");
            String pN = rS.getString("PHONE_NUMBER");

            // For address
            String[] addrDetails = rS.getString("ADDRESS").split(", ");
            Address adr = new Address(addrDetails[0], addrDetails[1], addrDetails[2], addrDetails[3], addrDetails[4]);

            // Create the Student Object
            Student s = new Student(fN, lN, iD, dOB, g, adr, email, pN);
            results.add(s);
        }
        
        return results;
        
    }
    
    
    

    
    
    /**
     * Generates a new randomised Student ID, and checks to ensure the database does not have an existing student with an identical number
     */
    public String generateNewStudentID()
    {
        String newID;
        boolean uniqueStudentIDGenerated = false;
        
        // Generate a new Student ID, ensuring it does not exist already in the Database
        while (!uniqueStudentIDGenerated)
        {
            // Student ID's consist of the last two digits of the year, then the numerical value of the month, and then 5 extra random digits
            int year = LocalDate.now().getYear();
            int month = LocalDate.now().getMonthValue();
            
            String yearComp = Integer.toString(year).substring(2);
            String monthComp = Integer.toString(month);
            if (monthComp.length() == 1)
                monthComp = "0" + monthComp;
            
            int fiveRandDigits = new Random().nextInt(89999) + 10000;
            newID = yearComp + monthComp + Integer.toString(fiveRandDigits);
            
            // Now, check to see if the ID is unique
            try
            {
                String query = "SELECT * FROM STUDENTS WHERE STUDENT_ID='" + newID + "'";
                ResultSet rS = statement.executeQuery(query);
                if (!rS.next())
                    return newID;
            }
            catch (SQLException sqle) {
                return null;
            }
        }

        return null;
    }
    
}
