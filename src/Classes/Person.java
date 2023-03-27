package Classes; /**
 *  Class Person
 *  5COSC019C Object-Oriented Programming – Coursework
 *  @author Devaka Alwis
 *  @version 1.0
 *  Date 2022-12-20
 *  */

import java.io.Serializable;
import java.time.LocalDate;

public abstract class Person implements Serializable {

    /**
     * Attributes
     * */
    private String firstName;
    private String surName;
    private LocalDate dateOfBirth;
    private String mobileNumber;

    // Constructors

    /**
     * Constructor for Person
     * @param firstName assign first name
     * @param surName assign surname
     * @param dateOfBirth assign date of birth
     * @param mobileNumber assign mobile number
     * */
    public Person(String firstName,String surName,LocalDate dateOfBirth,String mobileNumber){
        this.firstName=firstName;
        this.surName=surName;
        this.dateOfBirth=dateOfBirth;
        this.mobileNumber=mobileNumber;
    }
    //no argument constructor
    public Person(){
    }

    //Setters
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setSurName(String surName) {
        this.surName = surName;
    }
    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    //Getters
    public String getFirstName() {
        return firstName;
    }
    public String getSurName() {
        return surName;
    }
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }
    public String getMobileNumber() {
        return mobileNumber;
    }


    /**
     * convert object to string
     * @return person object as String
     * */
    @Override
    public String toString() {
        return firstName + " " + surName + " | " +
                "Date Of Birth: " + dateOfBirth + " | "+
                "Mobile Number: " + mobileNumber + " | ";
    }
}
