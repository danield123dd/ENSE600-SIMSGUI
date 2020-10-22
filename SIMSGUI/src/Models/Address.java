package Models;

/*
 * Address Class
 * An object which validates and stores address details for a Student
 * Project 1: SIMS CLI Project
 * Daniel Dymond (ID# 17977610) 2020
 */

public class Address {

    private String streetAddress;
    private String suburb;
    private String townCity;
    private String country;
    private String zip;

    /**
     * Constructor for initialisation of a new Address Object. Calls upon the setAddress Method
     * @param sA    Street Address
     * @param s     Suburb
     * @param tC    Town or City
     * @param c     Country
     * @param z     ZIP Code
     * @throws IllegalArgumentException  If any are empty or null
     */
    public Address(String sA, String s, String tC, String c, String z) throws IllegalArgumentException
    {
        setAddress(sA, s, tC, c, z);
    }

    /**
     * Modifies the information for the address object. Also validates input to see if the details are valid.
     * @param sA    Street Address
     * @param s     Suburb
     * @param tC    Town or City
     * @param c     Country
     * @param z     ZIP Code
     * @throws IllegalArgumentException  If any of the information is not filled
     */
    public void setAddress(String sA, String s, String tC, String c, String z) throws IllegalArgumentException
    {
        // Check to see if the address details are completed. If any fields are not completed,
        // throw an AddressInformationInvalidException
        if (sA.isEmpty()) {
            throw new IllegalArgumentException("Street Address is Empty");
        } else if (s.isEmpty()) {
            throw new IllegalArgumentException("Suburb is Empty");
        } else if (tC.isEmpty()) {
            throw new IllegalArgumentException("Town or City is Empty");
        } else if (c.isEmpty()) {
            throw new IllegalArgumentException("Country is Empty");
        } else if (z.isEmpty()) {
            throw new IllegalArgumentException("Zip Code is Empty");
        }

        // Now, check to see if zip code is purely numerical characters. If not, throw an InformationInvalidException
        try {
            Integer.parseInt(z.trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Zip Code should only contain numbers");
        }

        // Passing all validation checks, add information to the address object.
        this.streetAddress = sA;
        this.suburb = s;
        this.townCity = tC;
        this.country = c;
        this.zip = z;
    }

    /**
     * Returns the Street Address line
     * @return  Street Address
     */
    public String getStreetAddress() {
        return streetAddress;
    }

    /**
     * Returns the Suburb line
     * @return  Suburb
     */
    public String getSuburb() {
        return suburb;
    }

    /**
     * Returns the City line
     * @return  City
     */
    public String getTownCity() {
        return townCity;
    }

    /**
     * Returns the Country line
     * @return  Country
     */
    public String getCountry() {
        return country;
    }

    /**
     * Returns the ZIP code
     * @return  ZIP
     */
    public String getZip() {
        return zip;
    }

    /**
     * Returns a formatted single string with full address details
     * @return  Full Address
     */
    @Override
    public String toString() {
        return this.streetAddress + ", " + this.suburb + ", " + this.townCity + ", " + this.country + ", " + this.zip;
    }
}
