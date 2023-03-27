package Classes; /**
 *  Class WestminsterSkinConsultationManager
 *  5COSC019C Object-Oriented Programming – Coursework
 *  @author Devaka Alwis
 *  @version 1.0
 *  Date 2022-12-20
 *  */

import java.time.LocalDate;

public class Patient extends Person {

    //attribute
    private int patientID;

    /**
     * Constructor for Doctor
     * @param firstName assign first name
     * @param surName assign surname
     * @param dateOfBirth assign date of birth
     * @param mobileNumber assign mobile number
     * @param patientID assign patient ID
     * */
    public Patient(String firstName, String surName,
                   LocalDate dateOfBirth, String mobileNumber,
                   int patientID) {
        super(firstName, surName, dateOfBirth, mobileNumber);
        this.patientID=patientID;
    }

    //setter
    public void setPatientID(int patientID) {
        this.patientID = patientID;
    }

    //getter
    public int getPatientID() {
        return patientID;
    }
}
