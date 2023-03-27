/**
 *  Class WestminsterSkinConsultationManager
 *  5COSC019C Object-Oriented Programming – Coursework
 *  @author Devaka Alwis
 *  @version 1.0
 *  Date 2022-12-20
 *  */

package Classes;

import GUI.MenuFrame;

import java.io.*;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.io.File;


public class WestminsterSkinConsultationManager implements SkinConsultationManager {
    public static final WestminsterSkinConsultationManager manager = new WestminsterSkinConsultationManager();
    public static final int numberOfDoctors=10;   //assigning number of doctors
    public static final Person []listOfDoctors=new Doctor[numberOfDoctors];   //creating a person array of doctor class elements

    public static void main(String[] args) {


        //loadingObjectsFromFile();  //call the loadingObjectsFromFile to load the previous data object to the program
        loadingTextsFromFile();    //call the loadingTextsFromFile to load the previous data object to the program
        menu();    //call the menu

    }

    /**
     * Display menu of the console
     */
    public static void menu() {
        Scanner input= new Scanner((System.in));
        boolean condition=true;
        while (condition) { //run a while loop for the menu
            System.out.println(
                    """
                            --------------Menu--------------
                            A: Add a new doctor
                            D: Delete a doctor
                            P: Print the list of the doctors
                            S: Save in a file
                            O: Open GUI
                            Q: Quit
                            --------------------------------
                            """
            ); //print the menu options
            System.out.print("Please choose an option: ");
            String option = input.nextLine().toUpperCase(); //getting the user input for the menu option and convert is to uppercase
            switch (option) {
                case "A": {
                    manager.AddNewDoctor(); //if user insert "A" call the AddNewDoctor method
                    break;
                }
                case "D": {
                    manager.DeleteDoctor(); //if user insert "D" call the DeleteDoctor method
                    break;
                }
                case "P": {
                    manager.PrintDoctors(); //if user insert "P" call the PrintDoctors method
                    break;
                }
                case "S": {
                    manager.SaveInFile(); //if user insert "S" call the SaveInFile method
                    break;
                }
                case "O": {
                    OpenGUI();  //if user insert "G" call the OpenGUI method
                    break;
                }
                case "Q": {
                    condition = false;  //if user insert "Q" break the while loop and end the program
                    break;
                }
                default: {
                    System.out.println("Invalid option. Please enter a correct option.\n"); //if user enter any input print invalid
                    break;
                }
            }
        }
    }
    /**
     * Adding a new doctor to the list
     */
    @Override
    public void AddNewDoctor() {
        System.out.println("\n -------------- Add a new doctor -------------- \n");
        int doctorListAllocateNumber=isDoctorListFull();    //checking if the list is full or not and return an integer
        if(doctorListAllocateNumber!=-1){   //if returned -1 list is full, else list is not full yet
            String firstName=inputFirstName();  //call the inputFirstName method
            String surName=inputSurName();  //call the inputSurName method
            LocalDate dateOfBirth= inputDateOfBirth();  //call the dateOfBirth method
            String mobileNumber= inputMobileNumber();   //call the mobileNumber method
            int medicalLicenceNumber=inputMedicalLicenceNumber(); //call the medicalLicenceNumber method
            String specialisation=inputSpecialisation();    //call the specialisation method

            Person doctor= new Doctor(firstName,surName,dateOfBirth,mobileNumber,medicalLicenceNumber,specialisation);  //create a new doctor
            listOfDoctors[doctorListAllocateNumber]=doctor; //add the doctor to the list
            System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println("Doctor: "+listOfDoctors[doctorListAllocateNumber].toString()+" added successfully.\n\n");   //print the added doctor details
        }
        else{
            System.out.println("Doctor list is full.\n");   //print doctor list is full
        }
    }

    /**
     * Deleting a doctor from the list
     */
    @Override
    public void DeleteDoctor() {
        Scanner input=new Scanner(System.in);
        System.out.println(" ------------------------------ Delete a doctor ------------------------------ \n");
        boolean isEmpty=isDoctorListEmpty();    //check if the doctor list is empty
        if(!isEmpty) {  //if the doctor list is not empty continue
            outer: while (true) {   //run a while loop until use gets correct input
                showDoctors();  //run a list of doctors currently onboard
                System.out.println("----------------------------------------------------------------------------------------------------------------------------------------");
                System.out.print("Enter the Medical Licence Number to delete a doctor OR press B to go BACK to the Menu: ");

                if (input.hasNextInt()) {   //check if the input is an integer
                    int medicalLicenceNumber = input.nextInt(); //get the input
                    for (int i=0;i<listOfDoctors.length;i++) {  //run a for loop through the list of doctors
                        if (listOfDoctors[i] != null) { //check is the list doctor element is null
                            int medicalLicenceID = ((Doctor) listOfDoctors[i]).getMedicalLicenceNumber();   //get the medical licence ID
                            if (medicalLicenceID == medicalLicenceNumber) { //check if the user input and the medical id is matches
                                System.out.println("Dr. "+listOfDoctors[i].toString()+" has Deleted.\n");  //print doctor details that are going to delete
                                listOfDoctors[i] = null;    //make the list element null
                                int numberOfDoctors= countDoctorList(); //count the number of doctors available
                                System.out.println("Total number of doctors available: "+numberOfDoctors+"\n"); //print the number of doctors available
                                break outer;    //break the while loop
                            }
                            else {  //if the user input id is not matched
                                if(listOfDoctors[i+1]==null){   //check if the for loop is in the last element of the list
                                    System.out.println("There is no Doctor with Medical Licence Number: " + medicalLicenceNumber);  //print no doctor from the user inputted number
                                    System.out.println("-----------------------------------------------------------------------------------------------------------------------\n");
                                }
                            }
                        }
                    }

                } else {    //if string entered
                    String value=input.next();  //get the value
                    if (value.equals("b") || value.equals("B")) {   //check if the user enter B or b to go Back
                        System.out.println();
                        break;  //break the while loop and go to the main menu
                    } else {
                        System.out.println("Invalid Medical Licence Number!, Please try again.");   //else print try again
                        System.out.println("-----------------------------------------------------------------------------------------------------------------------\n");
                    }
                }
            }
        }
        else {  //if the list is empty print no doctors in the list
            System.out.println("There are no Doctors on the list.\n");
        }
    }

    /**
     * Printing the doctor list
     */
    @Override
    public void PrintDoctors() {
        System.out.println(" ---------- Print the list of the doctors ---------- \n");
        boolean isEmpty=isDoctorListEmpty();    //check is the list empty
        if(!isEmpty) {  //if the list is not empty continue
            ArrayList<Doctor> doctorsList = new ArrayList<Doctor>();    //create a new doctor array list
            for (Person doctor:listOfDoctors) { //run a for each loop through the list of doctors
                if (doctor != null) {   //check if the doctor element is not null
                    doctorsList.add((Doctor) doctor);   //if not null add the doctor to the array list
                }
            }
            Collections.sort(doctorsList);  //call the Collections.sort method to sort surnames alphabetical order
            for (Doctor doctor:doctorsList){    //run a for loop to get the doctor element
                System.out.println(doctor.toString());  //print the doctors in alphabetical order
            }
            System.out.println("-----------------------------------------------------------------------------------------------------------------------\n");
        }
        else {
            System.out.println("There are no Doctors on the list.");    //if the list is empty print no doctors on the list
        }
        System.out.println();
    }

    /**
     * Saving doctors to a file
     */
    @Override
    public void SaveInFile() {
        System.out.println(" ---------- Saving file ---------- \n");
        boolean isEmpty=isDoctorListEmpty();    //check if the list is empty
        if(!isEmpty) {  //if the list is not empty continue
            savingData();   //call the savingData method to save the doctor list
            System.out.println("TextData.txt file saved successfully"); //print txt file saved successfully
            System.out.println("ObjectData.txt file saved successfully");   //print object file saved successfully
            System.out.println("-----------------------------------------------------------------------------------------------------------------------\n");
        }
        else {
            System.out.println("There are no Doctors allocated on the list.");  //if there are no doctors in the list print no doctors in the list
        }
        System.out.println();   //print a new line
    }

    /**
     * Open the GUI
     */
    public static void OpenGUI() {
        if(!isDoctorListEmpty()) {  //check if the doctor list is not empty
            System.out.println("Opening Java GUI");
            MenuFrame menuFrame = new MenuFrame();  //create a new frame and run the GUI
            menuFrame.setVisible(true); //make the frame visible
        }
        else {
            System.out.println("There are no Doctors allocated on the list.");
        }
    }

    /**
     * Getting Firstname
     * @return String firstName
     */
    public String inputFirstName(){
        Scanner input= new Scanner((System.in));
        while (true) {  //run a while until user enter the correctly
            System.out.print("Enter the doctor's First Name: ");
            String firstName = input.nextLine();    //getting the firstname
            if (!firstName.equals("")) {    //check if the name is not empty
                firstName = firstName.substring(0, 1).toUpperCase() + firstName.substring(1).toLowerCase(); //Capital the first letter and rest simple letters
                return firstName;   //return the first name
            } else {
                System.out.println("Invalid, no input entered.");   //print invalid input entered
            }
        }
    }

    /**
     * Getting SurName
     * @return String lastName
     */
    public String inputSurName(){
        Scanner input= new Scanner((System.in));
        while (true) {  //run a while until user enter the correctly
            System.out.print("Enter the doctor's Surname: ");
            String surName = input.nextLine();  //getting the surname
            if (!surName.equals("")) {  //check if the name is not empty
                surName = surName.substring(0, 1).toUpperCase() + surName.substring(1).toLowerCase(); //Capital the first letter and rest simple letters
                return surName; //return the surname
            } else {
                System.out.println("Invalid, no input entered.");   //print invalid input entered
            }
        }
    }

    /**
     * Getting DateOfBirth
     * @return LocalDate DateOfBirth
     */
    public LocalDate inputDateOfBirth(){
        Scanner input= new Scanner((System.in));
        System.out.print("Enter the doctor's Date Of Birth: ");
        while (true) {  //run a while until user enter the correctly
            System.out.print("Enter the date as YYYY-MM-DD: ");
            String dateString = input.nextLine();   //getting the date are a string input
            LocalDate dateOfBirth = validateDate(dateString);   //call the validateDate to check the date is correct
            if(dateOfBirth!=null){  //if the dateOfBirth is not null
                return dateOfBirth; //return it
            }
        }
    }

    /**
     * Validate if the dateOfBirth is correctly formatted
     * @return LocalDate.of(y,m,d)  return year, month and day in order
     */
    public static LocalDate validateDate(String dateString) {
        if (dateString.length() == 10) {    //check if the string length is 10 including separators
            if (dateString.charAt(4) == '-' && dateString.charAt(7) == '-') {   //check whether the user inserted the date in order as year-month-day
                String[] dateOfBirth = dateString.split("-");   //split the date string from '-' separator and assign to a string array
                int y = 0;
                if (dateOfBirth[0].length() == 4) { //check the length of the year value is 4
                    y = Integer.parseInt(dateOfBirth[0]);   //convert the string year to integer
                }
                int m = 0;
                if (dateOfBirth[1].length() == 2) { //check the length of the month value is 2
                    m = Integer.parseInt(dateOfBirth[1]); //convert the string month to integer
                }
                int d = 0;
                if (dateOfBirth[2].length() == 2) { //check the length of the day value is 2
                    d = Integer.parseInt(dateOfBirth[2]); //convert the string day to integer
                }
                try {
                    return LocalDate.of(y, m, d); //return LocalDate from the input year, month and day
                } catch (DateTimeException e) { //if date is wrong throw and exception
                    System.out.println(e.getMessage()); //print the error message
                }
            } else {
                System.out.println("Invalid separator, Please separate the DATE using '-' to separate the date. Please format as YYYY-MM-DD"); //print if user insert invalid separator
            }
        } else {
            System.out.println("Invalid Format, Please insert the DATE as YYYY-MM-DD example 2022-12-01."); //print if user insert date invalid format
        }
        return null;
    }

    /**
     * Validate if the mobileNumber is correctly inserted
     * @return mobileNumber as a string variable
     */
    public static String inputMobileNumber() {
        while (true) {
            Scanner input = new Scanner(System.in);
            System.out.print("Enter the doctor's Mobile Number: ");
            String mobileNum = input.nextLine();    //getting the user input as a string
            if (mobileNum.length() == 10) { //check if the length of the mobileNumber is 10
                try {
                    Long.parseLong(mobileNum);  //check if the string can convert to long number
                    return mobileNum;   //return mobileNumber as a string

                } catch (NumberFormatException e) { //if the string can't convert to long number throw an exception
                    System.out.println("Invalid input, Please use Integers '0123456789'.");
                }
            } else { //print if the mobile number length is wrong
                System.out.println("Invalid length Range, Please enter 10 integers");
            }
        }
    }

    /**
     * Getting the medicalLicenceNumber
     * @return medicalLicenceNumber as int
     */
    public static int inputMedicalLicenceNumber() {
        Scanner input = new Scanner((System.in));
        while (true) {  //run a while until user enter the correctly
            try {
                System.out.print("Enter the doctor's Medical Licence Number: ");
                String stringMedicalLicenceNumber = input.nextLine();   //get the user input
                int medicalLicenceNumber=Integer.parseInt(stringMedicalLicenceNumber);  //convert string into integer
                boolean valid= checkDoctorID(medicalLicenceNumber); //checking if the doctor id is unique and check the same doctor id entered before
                if(valid) {
                    return medicalLicenceNumber;    //if the doctor id is unique return medical licence number
                }
            } catch (NumberFormatException e){
                System.out.println("Invalid doctor's Medical Licence Number. Please enter integers");   //catch an exception if the string can't convert to integer
            }
        }
    }

    /**
     * Checking doctorsID for check same id was inserted before
     * @param medicalLicenceNumber user inserted medicalLicenceNumber
     * @return boolean return false if the same ID inserted before else return true if the ID is new
     */
    public static boolean checkDoctorID(int medicalLicenceNumber){
        for (Person doctor : listOfDoctors) { //iterate through the listOfDoctors to check each and every element
            if(doctor!=null) {
                if (medicalLicenceNumber == ((Doctor) doctor).getMedicalLicenceNumber()) {    //check the medicalLicenceNumber is equal to the list element medicalID
                    System.out.println("Invalid. A doctor has the same ID as " + medicalLicenceNumber + ".");    //print if there is a same ID
                    return false;   //return false
                }
            }
        }
        return true;    //else return true
    }

    /**
     * Getting the specialisation
     * @return specialisation as int
     */
    public String inputSpecialisation() {
        Scanner input = new Scanner((System.in));
        while (true) {  //run a while until user enter the correctly
            System.out.print("Enter the doctor's Specialisation: ");
            String specialisation= input.nextLine();    //get the user input
            if (!specialisation.equals("")) {   //check if the user input is not empty
                specialisation= specialisation.substring(0,1).toUpperCase()+specialisation.substring(1).toLowerCase();  //Capital the first letter and rest simple letters
                return specialisation;  //return specialisation
            } else {
                System.out.println("Invalid, no input entered.");   //print invalid input
            }
        }
    }

    /**
     * Checking if the listOfDoctors array is full or not
     * @return variable(i) index number of the empty element in doctor or if array is full return -1
     */
    public static int isDoctorListFull(){
        for(int i=0; i<listOfDoctors.length;i++){ //run a for loop to iterate through the doctor list to check if the array is full
            if(listOfDoctors[i]==null){ //check if the list item is null
                return i;   //if one array element is nul return the index of the empty array element
            }
        }
        return -1;  //if the array is full return -1
    }

    /**
     * Checking if the doctor list is empty or not
     * @return boolean true if the list is empty and false if the list is not empty
     */
    public static boolean isDoctorListEmpty(){
        for (Person doctor : listOfDoctors) {   //iterate through the listOfDoctors to check each and every element
            if (doctor != null) {   //check if the doctor is not empty
                return false;   //return false
            }
        }
        return true;    //else return true
    }

    /**
     * Printing list of doctors details name, id and specialization
     */
    public static void showDoctors(){
        System.out.println("List of doctors");
        for(Person doctor:listOfDoctors) { //iterate through the listOfDoctors to check each and every element
            if (doctor != null) {   //check if the doctor is not empty
                String doctorDetail=doctor.toString();
                System.out.println(doctorDetail);
            }
        }
        System.out.println();   //print a new line
    }

    /**
     * Counting the number of doctors available in the list
     * @return int number of doctors in the list
     */
    public static int countDoctorList(){
        int count=0;
        for (Person doctor : listOfDoctors) { //iterate through the listOfDoctors to check each and every element
            if (doctor != null) {   //check if the doctor is not empty
                count++; //increase the count by 1
            }
        }
        return count;   //return the count
    }

    /**
     * Saving Data in files
     */
    public static void savingData() {
        try {
            File file = new File("TextData.txt");   //create the text file
            FileWriter textFile = new FileWriter(file); //create the file writer

            FileOutputStream fileObject = new FileOutputStream("ObjectData.txt");   ///create the object text file
            ObjectOutputStream objectWrite = new ObjectOutputStream(fileObject);    //create the object writer

            for (Person doctor : listOfDoctors) {   //run a for loop for each doctor in the list
                if (doctor != null) {   //check if the doctor element is not null
                    String firstName = doctor.getFirstName();   //get the firstname
                    String surName = doctor.getSurName();   //get the surname
                    LocalDate dateOfBirth = doctor.getDateOfBirth();    //get the dataOfBirth
                    String mobileNumber = doctor.getMobileNumber();     //get the mobileNumber
                    int licenceNumber = ((Doctor) doctor).getMedicalLicenceNumber();    //get the licenceNumber
                    String specialisation = ((Doctor) doctor).getSpecialisation();  //get the specialisation

                    textFile.write(firstName + " ," + surName + " ," + dateOfBirth + " ,"  + mobileNumber + " ," + licenceNumber + " ," + specialisation + "\n");   //write the doctor details in the text file
                    objectWrite.writeObject(doctor);    //write the object to the object text file
                }
            }
            textFile.flush();   //flush the text file
            textFile.close();   //and close it
            fileObject.flush(); //flush the object file
            fileObject.close(); //and close it
            objectWrite.close();//close the objectWriter
        } catch (IOException e) {
            System.out.println("Error! File could not created.\n"); //catch if any IO errors occur
        }
    }

    /**
     * Loading the object file
     */
    public static void loadingObjectsFromFile(){
        try {
            File file = new File("ObjectData.txt"); //create the file
            if(file.exists()) { //if the file exist continue
                FileInputStream fileObject = new FileInputStream(file); //create a file input stream
                ObjectInputStream objectRead = new ObjectInputStream(fileObject);   //create a object input stream
                while (fileObject.available()>=0) {    //or condition while(true)   //check if the fileObject are there or minus number if the file is read fully
                    Doctor obj = (Doctor) objectRead.readObject();  //get the object from file and cast it to doctor object
                    for (int i = 0; i < listOfDoctors.length; i++) {    //run a for loop to insert doctors
                        if (listOfDoctors[i] == null) { //check if the list element is null
                            listOfDoctors[i] = obj; //if null insert the doctor object
                            System.out.println("Doctor: "+listOfDoctors[i].toString()+" loaded from "+file.getName());  //print the doctor details that added
                            break;  //break the for loop to add the next object
                        }
                    }
                }
            }
        } catch (EOFException e){   // End of File exception if the file ended
            System.out.println("File has loaded successfully.\n");
        } catch (IOException e) {   //catch IO errors
            System.out.println("Error. Could not read the file.\n");
        } catch (ClassNotFoundException e) {    //catch if the class is not found
            System.out.println("Class Not found. "+e.getMessage()+"\n");
        }
    }

    /**
     * Loading text file
     */
    public static void loadingTextsFromFile(){
        try {
            File file = new File("TextData.txt");   //create the file
            if(file.exists()) {
                Scanner read = new Scanner(file);   //read input from file
                //run a while loop until reading file does not have content.
                while (read.hasNextLine()){
                    String fileLine = read.nextLine();  //getting the data of the line
                    if(fileLine!=null) {    //check if the fileLne is not null
                        String[] doctorDetails = fileLine.split(",");   //create a string list by separating by "'"
                        LocalDate dateOfBirth = validateDate(doctorDetails[2].strip()); //convert the string in to Local Date by calling validateDate
                        int medicalLicenceNumber = Integer.parseInt(doctorDetails[4].strip());  //convert string to integer
                        Person doctor = new Doctor(doctorDetails[0].strip(), doctorDetails[1].strip(), dateOfBirth, doctorDetails[3].strip(), medicalLicenceNumber, doctorDetails[5].strip());  //create a new doctor object
                        for (int i = 0; i < listOfDoctors.length; i++) {    //for loop to add the object to the list
                            if (listOfDoctors[i] == null) { //check if the list element is null
                                listOfDoctors[i] = doctor;  //if null add the doctor to the list
                                System.out.println("Doctor: "+listOfDoctors[i].toString()+" loaded from "+file.getName());  //print the added doctor
                                break;  //break the for loop to add the rest of the objects
                            }
                        }
                    }
                    else {
                        System.out.println("File is empty.\n"); //print if the file is empty
                    }
                }
                System.out.println("Data have been loaded from file successfully.\n");  //after data has added print loaded successfully
            }
            else {
                System.out.println(file.getName()+" file not created.\n");  //if the file is not there print not created
            }
        } catch (IOException e) {
            System.out.println("Error. Could not read the file.\n");    //catch IO errors
        }
    }
}