package Classes;
/**
 *  Class WestminsterSkinConsultationManager
 *  5COSC019C Object-Oriented Programming – Coursework
 *  @author Devaka Alwis
 *  @version 1.0
 *  Date 2022-12-20
 *  */


import java.io.Serializable;
import java.time.LocalDate;

public class Doctor extends Person implements Serializable,Comparable<Doctor>{

    /**
     * Attributes
     * */
    private int medicalLicenceNumber;
    private String specialisation;

    // Constructors

    /**
     * Constructor for Doctor
     * @param firstName assign first name
     * @param surName assign surname
     * @param dateOfBirth assign date of birth
     * @param mobileNumber assign mobile number
     * @param medicalLicenceNumber assign medical number
     * @param specialisation assign specialisation
     * */
    public Doctor(String firstName, String surName,
                  LocalDate dateOfBirth, String mobileNumber,
                  int medicalLicenceNumber, String specialisation) {
        super(firstName, surName, dateOfBirth, mobileNumber);
        this.medicalLicenceNumber=medicalLicenceNumber;
        this.specialisation=specialisation;
    }

    //setters
    public void setMedicalLicenceNumber(int medicalLicenceNumber) {
        this.medicalLicenceNumber = medicalLicenceNumber;
    }
    public void setSpecialisation(String specialisation) {
        this.specialisation = specialisation;
    }

    //getters
    public int getMedicalLicenceNumber() {
        return medicalLicenceNumber;
    }
    public String getSpecialisation() {
        return specialisation;

    }

    /**
     * Comparing object to check the order
     * @return int + if this.surName larger or - doctor.surName larger
     * */
    @Override
    public int compareTo(Doctor doctor) {
        return (this.getSurName().compareTo(doctor.getSurName()));
    }

    /**
     * convert object to string
     * @return person object as String
     * */
    @Override
    public String toString() {
        return super.toString()+ "Medical Licence Number: " + medicalLicenceNumber + " | "+
                "Specialisation: " + specialisation;
    }
}
