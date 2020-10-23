package Models;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * StudentTest Class
 * Class used for JUnitTests of the Student Class using the JUnit4 Testing Framework
 * @author Daniel Dymond (Project Group 1 - ID# 17977610)
 */
public class StudentTest {
    
    static Student testStudent;
    
    public StudentTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    /**
     * Before running each test, clear the testStudent Object and re-write with new details
     */
    @Before
    public void setUp() 
    {
        testStudent = new Student("Daniel", "Dymond", "17977610", LocalDate.parse("1998-03-08"), Gender.MALE, 
                new Address("55 Wellesley Street East", "Auckland City", "Auckland", "New Zealand", "1010"), "hello@dandymond.me", "09 921 9919");
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getFirstName method, of class Student.
     */
    @Test
    public void testGetFirstName()
    {
        System.out.println("TEST: getFirstName()");
        
        // Test Output expected from initiated object
        String expResult = "Daniel";
        String result = testStudent.getFirstName();
        assertEquals(expResult, result);
    }

    /**
     * Test of setFirstName method, of class Student.
     */
    @Test
    public void testSetFirstName()
    {
        System.out.println("TEST: setFirstName()");
        String firstName, expResult;
        String recievedException, expectedException;
        
        // Try a typical expected name, consisting of soley alphas
        firstName = "Richard";
        expResult = "Richard";
        testStudent.setFirstName(firstName);
        assertEquals(expResult, testStudent.getFirstName());
        
        // Now, try a more ellaborate name with alphas, spaces, and hyphens
        firstName = "Ricky De-True";
        expResult = "Ricky De-True";
        testStudent.setFirstName(firstName);
        assertEquals(expResult, testStudent.getFirstName());
        
        // Try to catch an exception when writing a blank string to the object
        expectedException = "You must enter a valid first name, consisting only of alphabetical and spaces.";
        recievedException = "";
        firstName = "";
        try {
            testStudent.setFirstName(firstName);
        } catch (IllegalArgumentException iae) {
            recievedException = iae.getMessage();
        }
        assertEquals(expectedException, recievedException);
        
        // Try to catch an exception when writing a name consisting of characters other than [a-zA-Z -]
        recievedException = "";
        firstName = "!";
        try {
            testStudent.setFirstName(firstName);
        } catch (IllegalArgumentException iae) {
            recievedException = iae.getMessage();
        }
        assertEquals(expectedException, recievedException);
        
        // Try to catch an exception when writing a name object which is null
        recievedException = "";
        firstName = null;
        try {
            testStudent.setFirstName(firstName);
        } catch (IllegalArgumentException iae) {
            recievedException = iae.getMessage();
        }
        assertEquals(expectedException, recievedException);
    }

    /**
     * Test of getLastName method, of class Student.
     */
    @Test
    public void testGetLastName() 
    {
        System.out.println("TEST: getLastName()");
        
        // Test Output expected from initiated object
        String expResult = "Dymond";
        String result = testStudent.getLastName();
        assertEquals(expResult, result);
    }

    /**
     * Test of setLastName method, of class Student.
     */
    @Test
    public void testSetLastName()
    {
        System.out.println("TEST: setLastName()");
        String lastName, expResult;
        String recievedException, expectedException;
        
        // Try a typical expected name, consisting of soley alphas
        lastName = "Whitehall";
        expResult = "Whitehall";
        testStudent.setLastName(lastName);
        assertEquals(expResult, testStudent.getLastName());
        
        // Now, try a more ellaborate name with alphas, spaces, and hyphens
        lastName = "Ku-re Matcha";
        expResult = "Ku-re Matcha";
        testStudent.setLastName(lastName);
        assertEquals(expResult, testStudent.getLastName());
        
        // Try to catch an exception when writing a blank string to the object
        expectedException = "You must enter a valid last name, consisting only of alphabetical and spaces.";
        recievedException = "";
        lastName = "";
        try {
            testStudent.setLastName(lastName);
        } catch (IllegalArgumentException iae) {
            recievedException = iae.getMessage();
        }
        assertEquals(expectedException, recievedException);
        
        // Try to catch an exception when writing a name consisting of characters other than [a-zA-Z -]
        recievedException = "";
        lastName = "/";
        try {
            testStudent.setLastName(lastName);
        } catch (IllegalArgumentException iae) {
            recievedException = iae.getMessage();
        }
        assertEquals(expectedException, recievedException);
        
        // Try to catch an exception when writing a name object which is null
        recievedException = "";
        lastName = null;
        try {
            testStudent.setLastName(lastName);
        } catch (IllegalArgumentException iae) {
            recievedException = iae.getMessage();
        }
        assertEquals(expectedException, recievedException);
    }

    /**
     * Test of getFullName method, of class Student.
     */
    @Test
    public void testGetFullName() 
    {
        System.out.println("TEST: getFullName()");
        String expResult = "Daniel Dymond";
        String result = testStudent.getFullName();
        assertEquals(expResult, result);
    }

    /**
     * Test of getDateOfBirth method, of class Student.
     */
    @Test
    public void testGetDateOfBirth() 
    {
        System.out.println("TEST: getDateOfBirth()");
        LocalDate expResult = LocalDate.parse("1998-03-08");
        LocalDate result = testStudent.getDateOfBirth();
        assertEquals(expResult, result);
    }

    /**
     * Test of setDateOfBirth method, of class Student.
     */
    @Test
    public void testSetDateOfBirth() 
    {
        LocalDate dateOfBirth;
        String recievedException, expectedException;
        System.out.println("TEST: setDateOfBirth()");
        
        // Test placing a valid birthday into the database
        dateOfBirth = LocalDate.parse("1967-03-08");
        testStudent.setDateOfBirth(dateOfBirth);
        assertEquals(dateOfBirth, testStudent.getDateOfBirth());
        
        // Test placing edge case - today's date - into the database
        dateOfBirth = LocalDate.now();
        testStudent.setDateOfBirth(dateOfBirth);
        assertEquals(dateOfBirth, testStudent.getDateOfBirth());
        
        // Test date in future - should throw an exception
        expectedException = "The Date of Birth cannot be a date in the future.";
        recievedException = "";
        dateOfBirth = LocalDate.parse("2100-01-01");
        try {
            testStudent.setDateOfBirth(dateOfBirth);
        } catch (IllegalArgumentException iae) {
            recievedException = iae.getMessage();
        }
        assertEquals(expectedException, recievedException);
        
        // Test null date - should throw an exception
        expectedException = "The Date of Birth cannot be null.";
        recievedException = "";
        dateOfBirth = null;
        try {
            testStudent.setDateOfBirth(dateOfBirth);
        } catch (IllegalArgumentException iae) {
            recievedException = iae.getMessage();
        }
        assertEquals(expectedException, recievedException);
    }

    /**
     * Test of getGender method, of class Student.
     */
    @Test
    public void testGetGender() {
        System.out.println("TEST: getGender()");
        Gender expResult = Gender.MALE;
        Gender result = testStudent.getGender();
        assertEquals(expResult, result);
    }

    /**
     * Test of setGender method, of class Student.
     */
    @Test
    public void testSetGender() 
    {
        System.out.println("TEST: setGender()");
        String expectedException, recievedException;
        
        // Set a Enumeration Gender Type
        Gender gender = Gender.FEMALE;
        testStudent.setGender(gender);
        assertEquals(gender, testStudent.getGender());

        // Test a null gender - should throw an exception
        expectedException = "A student must have a gender defined. If a gender cannot be defined, define as Other.";
        recievedException = "";
        gender = null;
        try {
            testStudent.setGender(gender);
        } catch (IllegalArgumentException iae) {
            recievedException = iae.getMessage();
        }
        assertEquals(expectedException, recievedException);
        
    }

    /**
     * Test of getEmail method, of class Student.
     */
    @Test
    public void testGetEmail() 
    {
        System.out.println("TEST: getEmail()");
        String expResult = "hello@dandymond.me";
        String result = testStudent.getEmail();
        assertEquals(expResult, result);
    }

    /**
     * Test of setEmail method, of class Student.
     */
    @Test
    public void testSetEmail()
    {
        System.out.println("TEST: setEmail()");
        String email;
        String expectedException = "The Email address provided is not valid.";
        String recievedException;
        
        // Test a fully-legal email address
        email = "daniel.dymond@dandymond.me";
        testStudent.setEmail(email);
        assertEquals(email, testStudent.getEmail());
        
        // Test a null email address
        recievedException = "";
        email = null;
        try {
            testStudent.setEmail(email);
        } catch (IllegalArgumentException iae) {
            recievedException = iae.getMessage();
        }
        assertEquals(expectedException, recievedException);
        
        // Test an address which does not contain "@"
        recievedException = "";
        email = "dandymond.me";
        try {
            testStudent.setEmail(email);
        } catch (IllegalArgumentException iae) {
            recievedException = iae.getMessage();
        }
        assertEquals(expectedException, recievedException);
        
        // Test an address which does not have content on either side of the "@"
        recievedException = "";
        email = "@";
        try {
            testStudent.setEmail(email);
        } catch (IllegalArgumentException iae) {
            recievedException = iae.getMessage();
        }
        assertEquals(expectedException, recievedException);
        
        // Test an address which does not have a valid domain
        recievedException = "";
        email = "test@dandymond";
        try {
            testStudent.setEmail(email);
        } catch (IllegalArgumentException iae) {
            recievedException = iae.getMessage();
        }
        assertEquals(expectedException, recievedException);
        
        // Test an address which has an illegal character "." at the start
        recievedException = "";
        email = ".test@dandymond.me";
        try {
            testStudent.setEmail(email);
        } catch (IllegalArgumentException iae) {
            recievedException = iae.getMessage();
        }
        assertEquals(expectedException, recievedException);
        
        // Test an address which has an illegal character "." at the end
        recievedException = "";
        email = "test@dandymond.me.";
        try {
            testStudent.setEmail(email);
        } catch (IllegalArgumentException iae) {
            recievedException = iae.getMessage();
        }
        assertEquals(expectedException, recievedException);
        
        // Test an address which has a double-period ".."
        recievedException = "";
        email = "daniel..dymond@dandymond.me";
        try {
            testStudent.setEmail(email);
        } catch (IllegalArgumentException iae) {
            recievedException = iae.getMessage();
        }
        assertEquals(expectedException, recievedException);
        
        // Test an address which has illegal characters (unicode)
        recievedException = "";
        email = "daniel.dymond@dandymond.me";
        try {
            testStudent.setEmail(email);
        } catch (IllegalArgumentException iae) {
            recievedException = iae.getMessage();
        }
        assertEquals(expectedException, recievedException);
        
        // Test an address which has an illegal domain
        recievedException = "";
        email = "daniel.dymond@dandymond$.me";
        try {
            testStudent.setEmail(email);
        } catch (IllegalArgumentException iae) {
            recievedException = iae.getMessage();
        }
        assertEquals(expectedException, recievedException);
        
    }

    /**
     * Test of getPhoneNumber method, of class Student.
     */
    @Test
    public void testGetPhoneNumber() 
    {
        System.out.println("TEST: getPhoneNumber()");
        String expResult = "09 921 9919";
        String result = testStudent.getPhoneNumber();
        assertEquals(expResult, result);
    }

    /**
     * Test of setPhoneNumber method, of class Student.
     */
    @Test
    public void testSetPhoneNumber() 
    {
        System.out.println("TEST: setPhoneNumber()");
        String expectedException = "The phone number provided is invalid.";
        String recievedException;
        String phoneNumber;
        
        // Test a valid phone number
        phoneNumber = "(09) 123-456789";
        testStudent.setPhoneNumber(phoneNumber);
        assertEquals(phoneNumber, testStudent.getPhoneNumber());
        
        // Test a null phone number
        recievedException = "";
        phoneNumber = null;
        try {
            testStudent.setPhoneNumber(phoneNumber);
        } catch (IllegalArgumentException iae) {
            recievedException = iae.getMessage();
        }
        assertEquals(expectedException, recievedException);
        
        // Test an empty phone number
        recievedException = "";
        phoneNumber = "";
        try {
            testStudent.setPhoneNumber(phoneNumber);
        } catch (IllegalArgumentException iae) {
            recievedException = iae.getMessage();
        }
        assertEquals(expectedException, recievedException);
        
        // Test a null phone number containing illegal characters
        recievedException = "";
        phoneNumber = "09$123#4567";
        try {
            testStudent.setPhoneNumber(phoneNumber);
        } catch (IllegalArgumentException iae) {
            recievedException = iae.getMessage();
        }
        assertEquals(expectedException, recievedException);
        
    }

    /**
     * Test of getAddress method, of class Student.
     */
    @Test
    public void testGetAddress()
    {
        System.out.println("getAddress");
        Address expResult = new Address("55 Wellesley Street East", "Auckland City", "Auckland", "New Zealand", "1010");
        Address result = testStudent.getAddress();
        assertEquals(expResult.toString(), result.toString());
    }

    /**
     * Test of setAddress method, of class Student.
     */
    @Test
    public void testSetAddress() 
    {
        System.out.println("TEST: setAddress()");
        
        String expectedException = "The Address cannot be null.";
        String recievedException;
        Address address;
        
        // Test a valid phone number
        address = new Address("Test", "Test", "Test", "Test", "1234");
        testStudent.setAddress(address);
        assertEquals(address, testStudent.getAddress());
        
        // Test a null phone number
        recievedException = "";
        address = null;
        try {
            testStudent.setAddress(address);
        } catch (IllegalArgumentException iae) {
            recievedException = iae.getMessage();
        }
        assertEquals(expectedException, recievedException);
    }

    /**
     * Test of getStudentID method, of class Student.
     */
    @Test
    public void testGetStudentID() 
    {
        System.out.println("TEST: getStudentID()");
        String expResult = "17977610";
        String result = testStudent.getStudentID();
        assertEquals(expResult, result);
    }

    /**
     * Test of getAge method, of class Student.
     */
    @Test
    public void testGetAge() 
    {
        System.out.println("TEST: getAge()");
        LocalDate dOB;
        String expResult, result;
        
        // Test with a valid Date of Birth in the past
        dOB = LocalDate.parse("1961-08-09");
        testStudent.setDateOfBirth(dOB);
        expResult = String.valueOf(ChronoUnit.YEARS.between(dOB, LocalDate.now()));
        result = testStudent.getAge();
        assertEquals(expResult, result);
        
        // Test with a Date of Birth which is today
        dOB = LocalDate.now();
        testStudent.setDateOfBirth(dOB);
        expResult = String.valueOf(ChronoUnit.YEARS.between(dOB, LocalDate.now()));
        result = testStudent.getAge();
        assertEquals(expResult, result);
        
        // Test with a Date of Birth which is exactly on one's birthday
        dOB = LocalDate.now().minusYears(1);
        testStudent.setDateOfBirth(dOB);
        expResult = String.valueOf(ChronoUnit.YEARS.between(dOB, LocalDate.now()));
        result = testStudent.getAge();
        assertEquals(expResult, result);
    }
    
}
