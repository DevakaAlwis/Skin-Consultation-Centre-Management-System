package GUI;

import Classes.Consultation;
import Classes.Patient;
import Classes.Person;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import static Classes.EncryptDecrypt.encryptPatientNotes;
import static Classes.WestminsterSkinConsultationManager.validateDate;
import static GUI.MenuFrame.listOfConsultation;
import static GUI.MenuFrame.listOfPatient;

public class PaymentDetailsFrame extends JFrame implements ActionListener {
    boolean patientExists;  //create a boolean variable
    ArrayList<Object>consultationAllDetails;    //create an arraylist for consultationAllDetails

    //Initiate panels
    JPanel paymentDetailsPanel;

    //Initiate labels
    JLabel consultationDetailsLabel;
    JLabel patientNameLabel;
    JLabel doctorNameLabel;
    JLabel consultationDateLabel;
    JLabel consultationTimeLabel;
    JLabel paymentDetailLabel;
    JLabel paymentPerHourLabel;
    JLabel paymentPerHourAmountLabel;
    JLabel numberOfHoursLabel;
    JLabel numberOfHoursAmountLabel;
    JLabel totalPaymentLabel;
    JLabel totalPaymentAmountLabel;

    //Initiate buttons
    JButton bookNowButton;

    //Initiate colors
    Color buttonColor= new Color(199, 199, 203);
    Color fontColor=new Color(47, 47, 47);

    //Initiate fonts
    Font TitleFont=new Font("Berlin Sans FB",Font.PLAIN,28);
    Font buttonFont=new Font("Berlin Sans FB",Font.PLAIN,25);
    Font textFont=new Font("Berlin Sans FB",Font.PLAIN,17);

    public PaymentDetailsFrame(ArrayList<Object> consultationDetailList){

        consultationAllDetails=consultationDetailList;

        JPanel titlePanel= commonComponent.titleComponent();     //call titleComponent method to create the title panel

        paymentDetailsBodyPanel();  //call paymentDetailsBodyPanel method

        //create action listeners to buttons
        bookNowButton.addActionListener(this);

        this.setTitle("Westminster Skin Consultation Centre");  //set a title for the frame
        this.setSize(850, 600);  //set width and height
        this.setLocationRelativeTo(null);   //make the frame center
        this.setResizable(false);   //remove resizing the frame
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    //exit the application by clicking close icon
        ImageIcon icon=new ImageIcon("Images/logo.png");
        this.setIconImage(icon.getImage());
        this.setLayout(new BorderLayout());

        this.add(titlePanel,BorderLayout.NORTH);
        this.add(paymentDetailsPanel,BorderLayout.CENTER);
        this.setVisible(true);  //make the frame visible
    }

    /**
     * paymentDetailsBodyPanel method
     */
    public void paymentDetailsBodyPanel(){
        double total;   //create total variable double
        int hours=0;    //create hors variable and make it 0
        int hourlyFee=15;   //make the hourlyFee 15
        patientExists=false;    //set patientExists false
        if(consultationAllDetails.get(6).equals("1 Hour")){ //check duration is 1 hour
            hours=1;
        }
        else if(consultationAllDetails.get(6).equals("2 Hour")){    //check duration is 2 hour
            hours=2;
        }
        else if(consultationAllDetails.get(6).equals("3 Hour")){    //check duration is 3 hour
            hours=3;
        }

        if(listOfPatient!=null) {   //check if the listOfPatient is not null
            for (Person patient : listOfPatient) {  //run a for each loop for each patient in the listOfPatient
                if (patient != null) {  //check if the patient is not null
                    if (((Patient) patient).getPatientID() == (int) consultationAllDetails.get(11)) {   //check patient IDs are equal
                        hourlyFee = 25; //make the hourlyFee 25
                        patientExists=true; //make patientExists true
                    }
                }
            }
        }
        total=hourlyFee*hours;  //calculate the total
        consultationAllDetails.add(total);  //add the total to the list

        //create the details label
        consultationDetailsLabel = new JLabel();
        consultationDetailsLabel.setBounds(50,50,250,40);
        consultationDetailsLabel.setText("Consultation Details");
        consultationDetailsLabel.setFont(TitleFont);
        consultationDetailsLabel.setForeground(fontColor);

        //create the patient name
        patientNameLabel = new JLabel();
        patientNameLabel.setBounds(50,100,400,40);
        patientNameLabel.setText("Patient Name: "+consultationAllDetails.get(7)+" "+consultationAllDetails.get(8));
        patientNameLabel.setFont(textFont);
        patientNameLabel.setForeground(fontColor);

        //create the doctor name
        doctorNameLabel = new JLabel();
        doctorNameLabel.setBounds(50,150,400,40);
        doctorNameLabel.setText("Doctor Name: "+consultationAllDetails.get(1)+" "+consultationAllDetails.get(2));
        doctorNameLabel.setFont(textFont);
        doctorNameLabel.setForeground(fontColor);

        //create the date
        consultationDateLabel = new JLabel();
        consultationDateLabel.setBounds(50,200,400,40);
        consultationDateLabel.setText("Consultation Date: "+consultationAllDetails.get(4));
        consultationDateLabel.setFont(textFont);
        consultationDateLabel.setForeground(fontColor);

        //create the time
        consultationTimeLabel = new JLabel();
        consultationTimeLabel.setBounds(50,250,400,40);
        consultationTimeLabel.setText("Consultation Time: "+consultationAllDetails.get(5));
        consultationTimeLabel.setFont(textFont);
        consultationTimeLabel.setForeground(fontColor);

        //create the payment details
        paymentDetailLabel = new JLabel();
        paymentDetailLabel.setBounds(550,50,200,40);
        paymentDetailLabel.setText("Payment Details:");
        paymentDetailLabel.setFont(TitleFont);
        paymentDetailLabel.setForeground(fontColor);

        //create the hourly fee
        paymentPerHourLabel = new JLabel();
        paymentPerHourLabel.setBounds(550,100,150,40);
        paymentPerHourLabel.setText("Hourly Fee:");
        paymentPerHourLabel.setFont(textFont);
        paymentPerHourLabel.setForeground(fontColor);

        //create the hourly fee amount
        paymentPerHourAmountLabel = new JLabel();
        paymentPerHourAmountLabel.setBounds(700,100,150,40);
        paymentPerHourAmountLabel.setText("$"+hourlyFee);
        paymentPerHourAmountLabel.setFont(textFont);
        paymentPerHourAmountLabel.setForeground(fontColor);

        //create the number of hours
        numberOfHoursLabel = new JLabel();
        numberOfHoursLabel.setBounds(550,150,150,40);
        numberOfHoursLabel.setText("Number of Hours:");
        numberOfHoursLabel.setFont(textFont);
        numberOfHoursLabel.setForeground(fontColor);

        //create the number of hours amount
        numberOfHoursAmountLabel = new JLabel();
        numberOfHoursAmountLabel.setBounds(700,150,150,40);
        numberOfHoursAmountLabel.setText((String) consultationAllDetails.get(6));
        numberOfHoursAmountLabel.setFont(textFont);
        numberOfHoursAmountLabel.setForeground(fontColor);

        //create the total payment
        totalPaymentLabel = new JLabel();
        totalPaymentLabel.setBounds(550,200,150,40);
        totalPaymentLabel.setText("Total Fee:");
        totalPaymentLabel.setFont(textFont);
        totalPaymentLabel.setForeground(fontColor);

        //create the total amount
        totalPaymentAmountLabel = new JLabel();
        totalPaymentAmountLabel.setBounds(700,200,150,40);
        totalPaymentAmountLabel.setText("$"+total);
        totalPaymentAmountLabel.setFont(textFont);
        totalPaymentAmountLabel.setForeground(fontColor);

        //create the book now button
        bookNowButton =new JButton();
        bookNowButton.setBounds(525,325,250,50);
        bookNowButton.setText("Book Now");
        bookNowButton.setFocusPainted(false);
        bookNowButton.setHorizontalTextPosition(JButton.CENTER);
        bookNowButton.setFont(buttonFont);
        bookNowButton.setForeground(fontColor);
        bookNowButton.setBackground(buttonColor);

        JLabel background =commonComponent.backgroundComponent ();  //set the background image

        paymentDetailsPanel=new JPanel();
        paymentDetailsPanel.setPreferredSize(new Dimension(250,300));
        paymentDetailsPanel.setLayout(null);

        //add the components to the panel
        paymentDetailsPanel.add(consultationDetailsLabel);
        paymentDetailsPanel.add(patientNameLabel);
        paymentDetailsPanel.add(doctorNameLabel);
        paymentDetailsPanel.add(consultationDateLabel);
        paymentDetailsPanel.add(consultationTimeLabel);
        paymentDetailsPanel.add(paymentDetailLabel);
        paymentDetailsPanel.add(paymentPerHourLabel);
        paymentDetailsPanel.add(paymentPerHourAmountLabel);
        paymentDetailsPanel.add(numberOfHoursLabel);
        paymentDetailsPanel.add(numberOfHoursAmountLabel);
        paymentDetailsPanel.add(totalPaymentLabel);
        paymentDetailsPanel.add(totalPaymentAmountLabel);
        paymentDetailsPanel.add(bookNowButton);
        paymentDetailsPanel.add(background);
    }

    /**
     * saveData method
     */
    public void saveData() {
        String notes;   //create a note string variable
        if (consultationAllDetails.size() == 15) {  //check if the consultationAllDetails size is 15
            if (!patientExists) {   //check if the patient not exists to add the details
                //get all the patient details
                String patientFirstName = (String) consultationAllDetails.get(7);
                String patientSurName = (String) consultationAllDetails.get(8);
                String DOB = (String) consultationAllDetails.get(9);
                LocalDate patientDateOfBirth = validateDate(DOB);
                String patientPhoneNumber = (String) consultationAllDetails.get(10);
                int patientID=Integer.parseInt(String.valueOf(consultationAllDetails.get(11)));
                Person patient = new Patient(patientFirstName, patientSurName, patientDateOfBirth, patientPhoneNumber, patientID);  //create a new patient object
                listOfPatient.add(patient); //add patient to the list
            }
            //get other details
            int doctorID= Integer.parseInt(String.valueOf(consultationAllDetails.get(0)));
            int patientID=Integer.parseInt(String.valueOf(consultationAllDetails.get(11)));
            String consultationChooseDate= (String) consultationAllDetails.get(4);
            LocalDate date=validateDate(consultationChooseDate);
            String startTime= (String) consultationAllDetails.get(5);
            String duration= (String) consultationAllDetails.get(6);
            double cost=(double)consultationAllDetails.get(14);
            ArrayList<String> imagesFile = (ArrayList<String>) consultationAllDetails.get(13);
            notes= (String) consultationAllDetails.get(12) ;
            notes=encryptPatientNotes(notes);   //encrypt the note

            Consultation consultation = new Consultation(doctorID,patientID,date,startTime,duration,cost,notes,imagesFile); //create a new consultation object
            listOfConsultation.add(consultation);   //add to the list
            //call saveConsultationInFile method
            saveConsultationInFile(String.valueOf(consultationAllDetails.get(0)),String.valueOf(consultationAllDetails.get(11)),consultationChooseDate,startTime,duration,String.valueOf(consultationAllDetails.get(13)),notes);
        }
        else if(consultationAllDetails.size() == 14) {  //check if the consultationAllDetails size is 14
            if (!patientExists) {   //check if the patient not exists to add the details
                //get all the patient details
                String patientFirstName = (String) consultationAllDetails.get(7);
                String patientSurName = (String) consultationAllDetails.get(8);
                String DOB = (String) consultationAllDetails.get(9);
                LocalDate patientDateOfBirth = validateDate(DOB);
                String patientPhoneNumber = (String) consultationAllDetails.get(10);
                int patientID=Integer.parseInt(String.valueOf(consultationAllDetails.get(11)));
                Person patient = new Patient(patientFirstName, patientSurName, patientDateOfBirth, patientPhoneNumber, patientID);  //create a new patient object
                listOfPatient.add(patient); //add patient to the list
            }

            //get other details
            int doctorID= Integer.parseInt(String.valueOf(consultationAllDetails.get(0)));
            int patientID=Integer.parseInt(String.valueOf(consultationAllDetails.get(11)));
            String consultationChooseDate= (String) consultationAllDetails.get(4);
            LocalDate date=validateDate(consultationChooseDate);
            String startTime= (String) consultationAllDetails.get(5);
            String duration= (String) consultationAllDetails.get(6);
            double cost=(double)consultationAllDetails.get(13);
            notes= (String) consultationAllDetails.get(12);
            notes=encryptPatientNotes(notes);   //encrypt the note
            Consultation consultation = new Consultation(doctorID,patientID,date,startTime,duration,cost,notes);    //create a new consultation object
            listOfConsultation.add(consultation);   //add to the list
            //call saveConsultationInFile method
            saveConsultationInFile(String.valueOf(consultationAllDetails.get(0)),String.valueOf(consultationAllDetails.get(11)),consultationChooseDate,startTime,duration,String.valueOf(consultationAllDetails.get(13)),notes);
        }
    }

    /**
     * saveConsultationInFile method
     */
    public void saveConsultationInFile(String doctorID,String patientID,String consultationChooseDate,String startTime,String duration,String cost,String notes){
        try {
            File file=new File("ConsultationDetails.txt");  //create file  object
            FileWriter fileWriter=new FileWriter(file,true);    //create file writer for the file
            fileWriter.write(doctorID+","+patientID+","+consultationChooseDate+","+startTime+","+duration+","+cost+","+notes+"\n"); //write the relevant detials
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {   //catch exceptions
            e.printStackTrace();
        }
    }

    /**
     * goBackToMenu method
     */
    private void goBackToMenu(){
        this.dispose(); //dispose the current frame
        MenuFrame menuFrame =new MenuFrame();   //create a new frame of menuFrame
        menuFrame.setVisible(true); //set it visible
    }

    /**
     * actionPerformed method
     */
    @Override
    public void actionPerformed(ActionEvent event) {
        if(event.getActionCommand().equals("Book Now")){    //call book now event performed
            JOptionPane.showMessageDialog(null,"Thank You for paying.","Payment Success",JOptionPane.PLAIN_MESSAGE);
            saveData(); //call saveData method
            goBackToMenu(); //call goBackToMenu method
        }
    }
}
