/**
 * Student Class
 * Object which stores the Personal and Academic information about a Student in the Institution
 * Project 2: SIMS GUI Project
 * @author Daniel Dymond (Group 1 - ID# 17977610) 2020
 */
package Models;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Student 
{
    private String firstName;
    private String lastName;
    private String studentID;
    private LocalDate dateOfBirth;
    private Gender gender;
    private Address address;
    private String email;
    private String phoneNumber;

    /**
     * Initialise a Student Object, validating the input as the object is created
     * @param firstName     First Name
     * @param lastName      Last Name
     * @param studentID     Student ID
     * @param dateOfBirth   Date of Birth
     * @param gender        Gender
     * @param address       Address
     * @param email         Email Address
     * @param phoneNumber   Phone Number
     * @throws IllegalArgumentException  If any details do not meet the correct format or criteria, or if left blank (All are required)
     */
    public Student(String firstName, String lastName, String studentID, LocalDate dateOfBirth, Gender gender, Address address, String email, String phoneNumber) throws IllegalArgumentException {
        setFirstName(firstName);
        setLastName(lastName);
        this.studentID = studentID;
        setDateOfBirth(dateOfBirth);
        setGender(gender);
        setAddress(address);
        setEmail(email);
        setPhoneNumber(phoneNumber);
    }

    /**
     * Get a student's First Name
     * @return  First Name
     */
    public String getFirstName()
    {
        return firstName;
    }

    /**
     * Set a student's First Name.
     * The first name should only consist of letters (a-z, A-z), dashes (-) or spaces ( )
     * @param firstName First Name
     * @throws IllegalArgumentException  Thrown if the first name is empty, or contains illegal characters
     */
    public void setFirstName(String firstName) throws IllegalArgumentException 
    {
        if (firstName == null || !firstName.replaceAll("[^a-zA-Z -]", "").equals(firstName) || firstName.trim().equals("")) {
            throw new IllegalArgumentException("You must enter a valid first name, consisting only of alphabetical and spaces.");
        }
        this.firstName = firstName;
    }

    /**
     * Get a student's Last Name
     * @return  Last Name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Set a student's Last Name.
     * The last name should only consist of letters (a-z, A-z), dashes (-) or spaces ( )
     * @param lastName Last Name
     * @throws IllegalArgumentException  Thrown if the last name is empty, or contains illegal characters
     */
    public void setLastName(String lastName) throws IllegalArgumentException {
        if (lastName == null || !lastName.replaceAll("[^a-zA-Z -]", "").equals(lastName) || lastName.trim().equals("")) {
            throw new IllegalArgumentException("You must enter a valid last name, consisting only of alphabetical and spaces.");
        }
        this.lastName = lastName;
    }

    /**
     * Get a student's Full Name
     * @return  Full Name
     */
    public String getFullName() {
        return this.firstName + " " + this.lastName;
    }

    /**
     * Get a student's Date of Birth as a LocalDate Object
     * @return  Date of Birth (LocalDate)
     */
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * Sets a student's Date of Birth
     * @param dateOfBirth   Date of Birth
     * @throws IllegalArgumentException  Thrown if the Date of Birth is in the Future
     */
    public void setDateOfBirth(LocalDate dateOfBirth) throws IllegalArgumentException {
        // Check the Date of Birth to see if it is a date in the past
        if (dateOfBirth == null)
            throw new IllegalArgumentException("The Date of Birth cannot be null.");
        if (dateOfBirth.compareTo(LocalDate.now()) > 0) {
            throw new IllegalArgumentException("The Date of Birth cannot be a date in the future.");
        } else {
            this.dateOfBirth = dateOfBirth;
        }
    }

    /**
     * Get a student's Gender
     * @return  Gender (Enumeration)
     */
    public Gender getGender() {
        return gender;
    }

    /**
     * Set a student's Gender. Gender must be a type of Enumeration
     * @param gender    Gender (Enumeration)
     * @throws IllegalArgumentException  Thrown if the Gender is null
     */
    public void setGender(Gender gender) throws IllegalArgumentException {
        if (gender == null) {
            throw new IllegalArgumentException("A student must have a gender defined. If a gender cannot be defined, define as Other.");
        } else {    
            this.gender = gender;
        }
    }

    /**
     * Get a student's Email Address
     * @return  Email Address
     */
    public String getEmail() {
        return email;
    }

    /**
     * Set a student's Email Address
     * @param email Email address
     * @throws IllegalArgumentException  Thrown if there is an issue with the email address not being legitimate
     */
    public void setEmail(String email) throws IllegalArgumentException {
        
        IllegalArgumentException iie = new IllegalArgumentException("The Email address provided is not valid.");

        // Using information from Stack Overflow to determine email address criteria:
        // https://stackoverflow.com/questions/2049502/what-characters-are-allowed-in-an-email-address#:~:text=The%20local-part%20of%20the,special%20characters%20!

        // Check if the email is null
        if (email == null) throw iie;
        
        // Check if the email contains a single @
        if (!email.replaceAll("[^@]", "").equals("@")) throw iie;

        // Check if the email contains content on either sides of the "@"
        if (email.split("@").length != 2) throw iie;

        // Check if the email contains a period on the "right" side of the "@" symbol
        if (!email.split("@")[1].contains(".")) throw iie;

        // Check that the first or last character is not an "."
        if (email.charAt(0) == '.' || email.charAt(email.length() - 1) == '.') throw iie;

        // Check if there is a duplication of periods in the email
        if (email.contains("..")) throw iie;

        // Check if there are abnormal characters in the first half of the email
        if (!email.split("@")[0].replaceAll("[a-zA-Z0-9!#$%&'*+-/=?^_`{|}~.]", "").equals("")) throw iie;

        // Check if the last half of the email is a valid domain
        if (!email.split("@")[1].replaceAll("[a-zA-Z0-9-.]", "").equals("")) throw iie;

        // With all check complete
        this.email = email;
    }

    /**
     * Get a student's Phone Number
     * @return  Phone Number
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Set a student's Phone Number
     * @param phoneNumber   Phone Number
     * @throws IllegalArgumentException  Thrown if the phone number contains characters other than numbers and "(", ")" "-" or " "
     */
    public void setPhoneNumber(String phoneNumber) throws IllegalArgumentException {
        if (phoneNumber == null || phoneNumber == "" || !phoneNumber.replaceAll("[0-9() -]", "").equals("")) {
            throw new IllegalArgumentException("The phone number provided is invalid.");
        } else {
            this.phoneNumber = phoneNumber.trim();
        }
    }

    /**
     * Get a student's Address
     * @return  Address object
     */
    public Address getAddress() {
        return address;
    }

    /**
     * Sets a student's Address with an Address Object
     * @param address   New Address
     */
    public void setAddress(Address address) throws IllegalArgumentException {
        if (address == null)
            throw new IllegalArgumentException("The Address cannot be null.");
        this.address = address;
    }

    /**
     * Get a student's ID Number
     * @return  Student ID Number
     */
    public String getStudentID() {
        return studentID;
    }

    /**
     * Calls upon the ChronoUnit Class to calculate the age of a Student based between their Date of Birth and the
     * current Date
     * @return  Age (int)
     */
    public String getAge() {
        LocalDate now = LocalDate.now();
        long years = ChronoUnit.YEARS.between(this.dateOfBirth, now);
        return String.valueOf(years);
    }
    
    /**
     * Generates a Summarised view of a Student's Information.
     * @return  Summarised String
     */
    @Override
    public String toString() {
        String output = "";
        output += "Name: " + getFullName()  + "   Student ID: " + this.studentID + "\r\n";
        output += "DOB: " + this.dateOfBirth + " (Age: " + this.getAge() + ")   Gender: " + this.gender + "\r\n";
        output += "Phone Number: " + this.phoneNumber + "\r\n";
        output += "Email: " + this.email + "\r\n";
        output += "Address: " + this.address;
        return output;
    }
}