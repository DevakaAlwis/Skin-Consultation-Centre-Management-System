package Classes;

import java.time.LocalDate;
import java.util.ArrayList;

import static Classes.WestminsterSkinConsultationManager.listOfDoctors;
import static GUI.MenuFrame.listOfPatient;

public class Consultation {
    private Doctor doctor;
    private Patient patient;

    private LocalDate consultationDate;
    private String consultationTime;
    private String consultationDuration;
    private double consultationCost;
    private String consultationNote;
    private ArrayList<String> imageNames;

    public Consultation(int doctorID, int patientID,
            LocalDate date,String time,String duration, double cost, String note) {
        for (Person doctor:listOfDoctors) {
            if(doctor!=null){
                if(((Doctor)doctor).getMedicalLicenceNumber()==doctorID){
                    this.doctor= (Doctor) doctor;
                }
            }
        }

        for (Person patient:listOfPatient) {
            if(patient!=null){
                if(((Patient)patient).getPatientID()==patientID){
                    this.patient= (Patient) patient;
                }
            }
        }
        this.consultationDate =date;
        this.consultationTime =time;
        this.consultationDuration =duration;
        this.consultationCost = cost;
        this.consultationNote = note;
    }

    public Consultation(int doctorID, int patientID,
                        LocalDate date,String time,String duration, double cost, String note, ArrayList<String> imageNames) {
        for (Person doctor:listOfDoctors) {
            if(doctor!=null){
                if(((Doctor)doctor).getMedicalLicenceNumber()==doctorID){
                    this.doctor= (Doctor) doctor;
                }
            }
        }

        for (Person patient:listOfPatient) {
            if(patient!=null){
                if(((Patient)patient).getPatientID()==patientID){
                    this.patient= (Patient) patient;
                }
            }
        }
        this.consultationDate =date;
        this.consultationTime =time;
        this.consultationDuration =duration;
        this.consultationCost = cost;
        this.consultationNote = note;
        this.imageNames=imageNames;
    }

    public void setConsultationCost(double consultationCost) {
        this.consultationCost = consultationCost;
    }
    public void setConsultationNote(String consultationNote) {
        this.consultationNote = consultationNote;
    }
    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }
    public void setPatient(Patient patient) {
        this.patient = patient;
    }
    public void setConsultationDate(LocalDate consultationDate) {
        this.consultationDate = consultationDate;
    }
    public void setConsultationTime(String consultationTime) {
        this.consultationTime = consultationTime;
    }
    public void setConsultationDuration(String consultationDuration) {
        this.consultationDuration = consultationDuration;
    }
    public void setImageNames(ArrayList<String> imageNames) {
        this.imageNames = imageNames;
    }
    public Doctor getDoctor() {
        return doctor;
    }
    public Patient getPatient() {
        return patient;
    }
    public ArrayList<String> getImageNames() {
        return imageNames;
    }
    public String getConsultationDuration() {
        return consultationDuration;
    }
    public double getConsultationCost() {
        return consultationCost;
    }
    public String getConsultationNote() {
        return consultationNote;
    }
    public String getConsultationTime() {
        return consultationTime;
    }
    public LocalDate getConsultationDate() {
        return consultationDate;
    }
}
