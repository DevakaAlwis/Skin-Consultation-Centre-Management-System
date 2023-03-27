package GUI;

import Classes.Consultation;
import Classes.Doctor;
import Classes.Person;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import static Classes.WestminsterSkinConsultationManager.countDoctorList;
import static Classes.WestminsterSkinConsultationManager.listOfDoctors;
import static GUI.MenuFrame.listOfConsultation;

public class CheckAvailabilityFrame extends JFrame implements ActionListener{

    ArrayList <Object> selectedDetails; //create an arraylist to selectedDetails

    //Initiate Panels
    JPanel availabilityBodyPanel;

    //Initiate Labels
    JLabel displayDoctorNameLabel;
    JLabel displayDoctorSpecializationLabel;
    JLabel chooseDateLabel;
    JLabel chooseStartTimeLabel;
    JLabel timeDurationLabel;

    //Initiate TextField
    JTextField displayDoctorNameText;
    JTextField displayDoctorSpecializationText;

    //Initiate DateChooser
    JDateChooser chooseDate;

    //Initiate ComboBox
    JComboBox chooseStartTimeComboBox;
    JComboBox timeDurationComboBox;

    //Initiate Buttons
    JButton backButton;
    JButton availabilityButton;

    //Initiate Colors
    Color buttonColor= new Color(199, 199, 203);
    Color fontColor=new Color(47, 47, 47);

    //Initiate Fonts
    Font buttonFont=new Font("Berlin Sans FB",Font.PLAIN,25);
    Font textFont=new Font("Berlin Sans FB",Font.PLAIN,17);

    public CheckAvailabilityFrame(ArrayList<Object> selectedDoctorDetails){
        selectedDetails=selectedDoctorDetails;  //assign selectedDoctorDetails to selectedDetails

        JPanel titlePanel= commonComponent.titleComponent();    //create the title panel

        availabilityPanel(selectedDoctorDetails);   //create the availability panel

        //add action listeners
        availabilityButton.addActionListener(this);
        backButton.addActionListener(this);

        this.setTitle("Westminster Skin Consultation Centre");  //set a title for the frame
        this.setSize(850, 600);  //set width and height
        this.setLocationRelativeTo(null);   //make the frame center
        this.setResizable(false);   //remove resizing the frame
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    //exit the application by clicking close icon
        ImageIcon icon=new ImageIcon("Images/logo.png");    //create an image icon
        this.setIconImage(icon.getImage()); //set the imageIcon to the frame icon
        this.setLayout(new BorderLayout()); //set layout for border layout

        this.add(titlePanel,BorderLayout.NORTH);    //set the title panel north
        this.add(availabilityBodyPanel,BorderLayout.CENTER);    //set the availabilityBodyPanel center of the layout
        this.setVisible(true);  //make the frame visible
    }


    private void availabilityPanel(ArrayList<Object> selectedDoctorDetails){

        displayDoctorNameLabel= new JLabel();
        displayDoctorNameLabel.setBounds(50,50,100,40); //set the position and the width and height
        displayDoctorNameLabel.setText("Doctor Name:"); //set the text
        displayDoctorNameLabel.setFont(textFont);   //set the font
        displayDoctorNameLabel.setForeground(fontColor);    //set the foreground

        displayDoctorNameText=new JTextField();
        displayDoctorNameText.setText("Dr. "+selectedDoctorDetails.get(1)+" "+selectedDoctorDetails.get(2));    //set the text
        displayDoctorNameText.setEditable(false);   //make is not editable
        displayDoctorNameText.setBounds(205,50,400,40); //set the position and the width and height
        displayDoctorNameText.setFont(textFont);    //set the font
        displayDoctorNameText.setForeground(fontColor); //set the foreground

        displayDoctorSpecializationLabel= new JLabel();
        displayDoctorSpecializationLabel.setBounds(50,100,200,40);  //set the position and the width and height
        displayDoctorSpecializationLabel.setText("Doctor Specialization:"); //set the text
        displayDoctorSpecializationLabel.setFont(textFont); //set the font
        displayDoctorSpecializationLabel.setForeground(fontColor);  //set the foreground

        displayDoctorSpecializationText=new JTextField();
        displayDoctorSpecializationText.setText(""+selectedDoctorDetails.get(3));   //set the text
        displayDoctorSpecializationText.setEditable(false); //make is not editable
        displayDoctorSpecializationText.setBounds(205,100,400,40);  //set the position and the width and height
        displayDoctorSpecializationText.setFont(textFont);  //set the font
        displayDoctorSpecializationText.setForeground(fontColor);   //set the foreground

        chooseDateLabel = new JLabel();
        chooseDateLabel.setBounds(50,150,200,40);   //set the position and the width and height
        chooseDateLabel.setText("Choose the Date:");    //set the text
        chooseDateLabel.setFont(textFont);  //set the font
        chooseDateLabel.setForeground(fontColor);   //set the foreground

        chooseDate = new JDateChooser();
        chooseDate.setDateFormatString("yyyy-MM-dd");   //set the dateFormat
        chooseDate.setBounds(205,150,225,30);   //set the position and the width and height
        chooseDate.setFont(textFont);   //set the foreground
        chooseDate.setForeground(fontColor);    //set the font
        JTextFieldDateEditor editor = (JTextFieldDateEditor) chooseDate.getDateEditor();
        editor.setEditable(false);  //make is not editable

        chooseStartTimeLabel= new JLabel();
        chooseStartTimeLabel.setBounds(50,200,200,40);  //set the position and the width and height
        chooseStartTimeLabel.setText("Choose a Time From:");    //set the text
        chooseStartTimeLabel.setFont(textFont); //set the font
        chooseStartTimeLabel.setForeground(fontColor);  //set the foreground

        String [] startTimeSlots= {"9:00 AM","10:00 AM","11:00 AM","12:00 PM","13:00 PM","14:00 PM","15:00 PM","16:00 PM"};
        chooseStartTimeComboBox=new JComboBox(startTimeSlots);
        chooseStartTimeComboBox.setBounds(205,200,100,40);  //set the position and the width and height
        chooseStartTimeComboBox.setFont(textFont);  //set the font
        chooseStartTimeComboBox.setForeground(fontColor);   //set the foreground

        timeDurationLabel=new JLabel();
        timeDurationLabel.setBounds(50,250,200,40); //set the position and the width and height
        timeDurationLabel.setText("Choose a Duration:");    //set the text
        timeDurationLabel.setFont(textFont);    //set the font
        timeDurationLabel.setForeground(fontColor); //set the foreground

        String []timeDurations={"1 Hour","2 Hour","3 Hour"};    //list of times
        timeDurationComboBox=new JComboBox(timeDurations);
        timeDurationComboBox.setBounds(205,250,100,40); //set the position and the width and height
        timeDurationComboBox.setFont(textFont); //set the font
        timeDurationComboBox.setForeground(fontColor);  //set the foreground

        backButton= new JButton();
        backButton.setBounds(50,325,100,50);    //set the position and the width and height
        backButton.setText("Back"); //set the text
        backButton.setFocusPainted(false);  //make the boarder remove when clicked
        backButton.setHorizontalTextPosition(JButton.CENTER);   //make the button center
        backButton.setFont(buttonFont); //set the font
        backButton.setForeground(fontColor);    //set the foreground
        backButton.setBackground(buttonColor);  //set the button color

        availabilityButton=new JButton();
        availabilityButton.setBounds(575,325,200,50);   //set the position and the width and height
        availabilityButton.setText("Available");    //set the text
        availabilityButton.setFocusPainted(false);  //make the boarder remove when clicked
        availabilityButton.setHorizontalTextPosition(JButton.CENTER);   //make the button center
        availabilityButton.setFont(buttonFont);     //set the font
        availabilityButton.setForeground(fontColor);    //set the foreground
        availabilityButton.setBackground(buttonColor);  //set the button color

        JLabel background =commonComponent.backgroundComponent ();  //add a background image

        availabilityBodyPanel=new JPanel();
        availabilityBodyPanel.setPreferredSize(new Dimension(850,450)); //set the dimensions
        availabilityBodyPanel.setLayout(null);  //make the layout null

        //add the components
        availabilityBodyPanel.add(displayDoctorNameLabel);
        availabilityBodyPanel.add(displayDoctorNameText);
        availabilityBodyPanel.add(displayDoctorSpecializationLabel);
        availabilityBodyPanel.add(displayDoctorSpecializationText);
        availabilityBodyPanel.add(chooseDateLabel);
        availabilityBodyPanel.add(chooseDate);
        availabilityBodyPanel.add(chooseStartTimeLabel);
        availabilityBodyPanel.add(chooseStartTimeComboBox);
        availabilityBodyPanel.add(timeDurationLabel);
        availabilityBodyPanel.add(timeDurationComboBox);
        availabilityBodyPanel.add(backButton);
        availabilityBodyPanel.add(availabilityButton);
        availabilityBodyPanel.add(background);
    }

    /**
     * openPatientDetailsFrame method
     */
    private void openPatientDetailsFrame(ArrayList<Object> selectedDetailsList){
        this.dispose(); //dispose the current frame
        PatientDetailsFrame patientDetailsFrame=new PatientDetailsFrame(selectedDetailsList);   //create a new frame of patientDetailsFrame
        patientDetailsFrame.setVisible(true);   //set it visible
    }

    /**
     * openPatientDetailsFrame method
     */
    private void goBackToDoctorList(){
        this.dispose(); //dispose the current frame
        DoctorListFrame doctorListFrame=new DoctorListFrame();  //create a new frame of doctorListFrame
        doctorListFrame.setVisible(true); //set it visible
    }

    /**
     * randomDoctor method
     */
    public int randomDoctor(int doctorCount, int doctorID, String consultationChooseDate, String consultationStartTime){
        boolean check;
        int randomInt;
        Set<Integer> doctorIDFromList = new HashSet<Integer>(); //create a HashSet for doctorIDFromList
        Set<Integer> haveSameDataTime = new HashSet<Integer>(); //create a hash set for haveSameDataTime
        doctorIDFromList.add(doctorID); //add the doctor id to doctorIDFromList
        outer: while (true) {
            Random random = new Random();
            randomInt = random.nextInt(doctorCount); //get a random number

            Person doctor = listOfDoctors[randomInt];   //get the doctor of the random number element
            int doctorMedicalLicenceNumber = ((Doctor) doctor).getMedicalLicenceNumber();   //get doctor ID of it
            doctorIDFromList.add(doctorMedicalLicenceNumber);   //add doctor ID to doctorIDFromList
            if (doctorMedicalLicenceNumber != doctorID) {   //check if the doctor IDs not match
                check=true; //make check true
                for (Consultation consultation : listOfConsultation) {  //run a for loop for consultations
                    if (consultation != null) { //check if the consultation is not null
                        if (consultation.getDoctor().getMedicalLicenceNumber()==doctorMedicalLicenceNumber) {   //check doctor IDs match
                            if(consultationChooseDate.equals(String.valueOf(consultation.getConsultationDate()))) { //check chooseDates match
                                if (consultationStartTime.equals(consultation.getConsultationTime())) { //check startTimes match
                                    check=false;    //if true make check false
                                }
                            }
                        }
                        haveSameDataTime.add(consultation.getDoctor().getMedicalLicenceNumber());   //add doctorId to haveSameDateTime
                    }
                }
                if(check){  //if true
                    break;  //break the while loop
                }
                if(doctorIDFromList.size()==doctorCount){   //if doctorIDFromList size match doctorCount
                    if(doctorIDFromList.size()==haveSameDataTime.size()){   //check if doctorIDFromList and haveSameDataTime botch sizes match
                        JOptionPane.showMessageDialog(null,"Please choose another date for the consultation.","Blank Input",JOptionPane.ERROR_MESSAGE); //display a OptionPane
                        randomInt=-2;// make randomInt -1
                        break;  //break the while loop
                    }
                }
            }
        }
        return randomInt;   //return randomInt
    }

    /**
     * actionPerformed method
     */
    @Override
    public void actionPerformed(ActionEvent event) {
        if(event.getActionCommand().equals("Available")){   //check Available event performed
            if(chooseDate.getDate()!=null) {    //check choose date is not null
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String consultationChooseDate = dateFormat.format(chooseDate.getDate());    //get the date
                String consultationStartTime= (String) chooseStartTimeComboBox.getSelectedItem();   //get the time
                String consultationDuration= (String) timeDurationComboBox.getSelectedItem();   //get the duration

                int doctorID = Integer.parseInt(String.valueOf(selectedDetails.get(0)));    //get the doctorID
                boolean condition=true;
                int doctorListSlotNumber=-1;
                if (listOfConsultation != null) {   //check if the listOfConsultation is not null
                    for (Consultation consultation : listOfConsultation) {  //run a for loop for each consultation
                        if (consultation != null) { //check consultation is not null
                            if (doctorID == consultation.getDoctor().getMedicalLicenceNumber()) {   //match doctor IDs
                                if(consultationChooseDate.equals(String.valueOf(consultation.getConsultationDate()))){  //match chooseDates
                                    if(consultationStartTime.equals(consultation.getConsultationTime())){   //matchTimes
                                        int doctorCount=countDoctorList();  //get the doctor count
                                        if(doctorCount>=2) {    //if the count is more than 2 continue
                                            doctorListSlotNumber= randomDoctor(doctorCount,doctorID,consultationChooseDate,consultationStartTime);  //call random doctor and get the doctor list slot number
                                        }
                                        else {
                                            JOptionPane.showMessageDialog(null,"Please choose another date for the consultation.","Blank Input",JOptionPane.ERROR_MESSAGE); //ask user to pick another person
                                            condition=false;
                                            break;  //break the for loop
                                        }
                                    }
                                    else {
                                        break;  //break the for loop
                                    }
                                }
                                else {
                                    break;  //break the for loop
                                }
                            }
                        }
                    }
                }
                if(condition) { //if true
                    if(doctorListSlotNumber>-1) {   //check doctorListSlotNumber is positive
                        String previousDoctorFirstName= String.valueOf(selectedDetails.get(1)); //get doctor first name
                        String previousDoctorSurName= String.valueOf(selectedDetails.get(2));   //get doctor last name
                        selectedDetails = new ArrayList<Object>();  //create a new array list
                        Person doctor = listOfDoctors[doctorListSlotNumber];    //get the doctor
                        int doctorMedicalLicenceNumber=((Doctor) doctor).getMedicalLicenceNumber(); //get the doctor ID
                        String doctorFirstName=doctor.getFirstName();   //get the doctor first name
                        String doctorSurName=doctor.getSurName();   //get the doctor surname
                        String doctorSpecialisation=((Doctor) doctor).getSpecialisation();  //get the doctor specialisation
                        //add details to the array list
                        selectedDetails.add(doctorMedicalLicenceNumber);
                        selectedDetails.add(doctorFirstName);
                        selectedDetails.add(doctorSurName);
                        selectedDetails.add(doctorSpecialisation);
                        selectedDetails.add(consultationChooseDate);
                        selectedDetails.add(consultationStartTime);
                        selectedDetails.add(consultationDuration);
                        //display all the relevant details
                        JOptionPane.showMessageDialog(null,"Dr. "+previousDoctorFirstName+" "+previousDoctorSurName+" is not available on "+consultationChooseDate+" at "+ consultationStartTime
                                +"\nDr."+doctorFirstName+" "+doctorSurName+" Specialisation on "+doctorSpecialisation+" will allocated on "+consultationChooseDate+" at "+ consultationStartTime);
                        openPatientDetailsFrame(selectedDetails);   //open openPatientDetailsFrame method
                    }
                    else if(doctorListSlotNumber==-1){  //if doctorListSlotNumber is -1
                        //add these details to selected Details array list
                        selectedDetails.add(consultationChooseDate);
                        selectedDetails.add(consultationStartTime);
                        selectedDetails.add(consultationDuration);
                        openPatientDetailsFrame(selectedDetails);   //open openPatientDetailsFrame method
                    }
                }
            }
            else{   //if date is not picked
                JOptionPane.showMessageDialog(null,"Please choose a date for the consultation.","Blank Input",JOptionPane.ERROR_MESSAGE);
            }
        }
        else if(event.getActionCommand().equals("Back")){
            goBackToDoctorList();   //go back to doctor list
        }
    }
}

//https://stackoverflow.com/questions/49964786/jdatechooser-does-not-appear
//https://stackoverflow.com/questions/69342332/how-to-get-date-from-jdatechooser-and-format-it
//http://www.java2s.com/Code/Jar/j/Downloadjcalendar14jar.htm#google_vignette
//